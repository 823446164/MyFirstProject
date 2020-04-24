package com.amarsoft.app.ems.system.service.impl;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.amarsoft.aecd.acct.constant.TermUnit;
import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.aecd.system.constant.UserEventType;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arem.util.MessageHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.cs.dto.allauthchangesquery.AllAuthChangesQueryReq;
import com.amarsoft.app.ems.system.cs.dto.allauthchangesquery.AllAuthChangesQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.allauthchangesquery.AuthChange;
import com.amarsoft.app.ems.system.cs.dto.alllogininfoquery.AllLoginInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.alllogininfoquery.AllLoginInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.alllogininfoquery.LoginInfo;
import com.amarsoft.app.ems.system.cs.dto.allsecurityinfoquery.AllSecurityInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.onlinenumquery.OnlineNumQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.onlineuserlist.OnlineUserListReq;
import com.amarsoft.app.ems.system.cs.dto.onlineuserlist.OnlineUserListRsp;
import com.amarsoft.app.ems.system.cs.dto.userquery.User;
import com.amarsoft.app.ems.system.entity.OrgInfo;
import com.amarsoft.app.ems.system.entity.UserEvent;
import com.amarsoft.app.ems.system.entity.UserInfo;
import com.amarsoft.app.ems.system.service.AuditService;
import com.amarsoft.app.ems.system.util.UserHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 安全审计的接口实现类
 * @author hzhang23
 *
 */
@Slf4j
@Service
public class AuditServiceImpl implements AuditService {
    
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    
    @Value("${authorization}")
    private String authorization;
    
    public final static String ONLINE_PEOPLE_NUM="onlinePeopleNum"; 
    public final static String ONLINE_PEOPLE_LIST="onlinePeopleList"; 
    public final static String ALL_SECURITY_INFO="ALL_SECURITY_INFO"; 
    public final static String KEY_FRE="audit:"; 
    public static final String DATE_START_TIME = " 00:00:00";
    private final static String ACCESS_TOKEN = "access_to_refresh:";

    
    @Override
    @Cacheable(value=KEY_FRE, key="#root.target.ALL_SECURITY_INFO")
    public AllSecurityInfoQueryRsp allSecurityInfoQuery() {
        AllSecurityInfoQueryRsp rsp = new AllSecurityInfoQueryRsp();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        //当天登录总次数
        BusinessObjectAggregate<BusinessObject> loginResult = bomanager.selectBusinessObjectsBySql("select count(EVENTID) as cnt from UserEvent where (userEventType =:loginSuccess or userEventType = :loginFailed) "
                + "and eventTime between :dateStartTime and :dateEndTime",
                "loginSuccess",UserEventType.LoginSuccess.id,"loginFailed",UserEventType.LoginFailed.id,
                "dateStartTime",getDateStartTime(),"dateEndTime",getDateEndTime());
        rsp.setLoginTimesCount(loginResult.getBusinessObjects().get(0).getInt("cnt"));
        
        
        //当天登录成功总次数
        BusinessObjectAggregate<BusinessObject> loginSuccessResult = bomanager.selectBusinessObjectsBySql("select count(EVENTID) as cnt from UserEvent where userEventType =:loginSuccess "
                + "and eventTime between :dateStartTime and :dateEndTime",
                "loginSuccess",UserEventType.LoginSuccess.id,
                "dateStartTime",getDateStartTime(),"dateEndTime",getDateEndTime());
        rsp.setLoginSuccessTimesCount(loginSuccessResult.getBusinessObjects().get(0).getInt("cnt"));
        
        //当日登录总人数
        BusinessObjectAggregate<BusinessObject> loginPeopleCount = bomanager.selectBusinessObjectsBySql("select count(distinct userId) as cnt from UserEvent"
                + " where (userEventType =:loginSuccess or userEventType = :loginFailed) and eventTime between :dateStartTime and :dateEndTime",
                "loginSuccess",UserEventType.LoginSuccess.id,"loginFailed",UserEventType.LoginFailed.id,
                "dateStartTime",getDateStartTime(),"dateEndTime",getDateEndTime());
        rsp.setLoginPeopleCount(loginPeopleCount.getBusinessObjects().get(0).getInt("cnt"));
        
        //当天登出总次数
        BusinessObjectAggregate<BusinessObject> logoutResult = bomanager.selectBusinessObjectsBySql("select count(EVENTID) as cnt from UserEvent where (userEventType =:logoutSuccess or userEventType = :logoutFailed) "
                + "and eventTime between :dateStartTime and :dateEndTime",
                "logoutSuccess",UserEventType.LogoutSuccess.id,"logoutFailed",UserEventType.LogoutFailed.id,
                "dateStartTime",getDateStartTime(),"dateEndTime",getDateEndTime());
        rsp.setLogoutTimesCount(logoutResult.getBusinessObjects().get(0).getInt("cnt"));
        
        //当日登出总人数
        BusinessObjectAggregate<BusinessObject> logoutPeopleCount = bomanager.selectBusinessObjectsBySql("select count(distinct userId) as cnt from UserEvent "
                + "where (userEventType =:logoutSuccess or userEventType = :logoutFailed)and eventTime between :dateStartTime and :dateEndTime",
                "logoutSuccess",UserEventType.LogoutSuccess.id,"logoutFailed",UserEventType.LogoutFailed.id,
                "dateStartTime",getDateStartTime(),"dateEndTime",getDateEndTime());
        rsp.setLogoutPeopleCount(logoutPeopleCount.getBusinessObjects().get(0).getInt("cnt"));
        
        return rsp;
    }
    
    @Scheduled(cron="0 0/5 * * * ?")
    @CacheEvict(value=KEY_FRE, key="#root.target.ALL_SECURITY_INFO")
    public void clearAllSecurityInfo() {
        /**
         * 在线人数统计定时清除缓存接口，按实际需要设置执行频率
         */
        if(log.isDebugEnabled()) {
            log.debug("Clear ALL SECURITY INFO Cache");
        }
    }
    
    @Override
    @Cacheable(value=KEY_FRE, key="#root.target.ONLINE_PEOPLE_NUM")
    public OnlineNumQueryRsp onlineNumQuery() {
        //统计
        OnlineNumQueryRsp rsp = new OnlineNumQueryRsp();
        int totalCount = 0;
        Set<String> tokens = redisTemplate.keys(ACCESS_TOKEN + "*");
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance si = loadBalancerClient.choose("aoas-server");
        for (String token : tokens) {
            //1.调用aoas服务检查token
            URI uri = UriComponentsBuilder.fromUri(si.getUri()).path("/aoas/oauth/check_token").queryParam("token", token.split(ACCESS_TOKEN)[1]).build(false).toUri();
            
            //2.请求头拼装
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            requestHeaders.add("Authorization", authorization);

            //3.请求体拼装
            HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

            //通过token获取用户相关信息
            
            try {
                //4.发送http请求
                ResponseEntity<Map> result= restTemplate.exchange(uri, HttpMethod.POST, requestEntity, Map.class);
            } catch (HttpClientErrorException e) {
                //如果token无效则跳过
                continue;
            }
            //总数统计
            totalCount++;
        }
        rsp.setOnlineNum(totalCount);
        return rsp;
    }
    
    @Scheduled(cron="0 0/5 * * * ?")
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="#root.target.ONLINE_PEOPLE_NUM"),
            @CacheEvict(value=KEY_FRE, key="#root.target.ONLINE_PEOPLE_LIST")
    })
    
    public void clearOnlineNumCache() {
        /**
         * 在线人数统计定时清除缓存接口，按实际需要设置执行频率
         */
        if(log.isDebugEnabled()) {
            log.debug("Clear Online Num Cache");
            log.debug("Clear Online List Cache");
        }
    }

    @Override
    public AllLoginInfoQueryRsp allLoginInfoQuery(AllLoginInfoQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        AllLoginInfoQueryRsp rsp = new AllLoginInfoQueryRsp();
        String[] searchAttributes = {"userId","userName","os","browser","ipAddress"};//查询条件
        rsp.setInfos(new ArrayList<LoginInfo>());
        BusinessObjectAggregate<BusinessObject> loginInfoAggre = null;
        
        if (StringUtils.isEmpty(req.getSearchAttribute()) && StringUtils.isEmpty(req.getSearchContent())) {//不走查询条件
            if (StringUtils.isEmpty(req.getSearchBeginTime()) && StringUtils.isEmpty(req.getSearchEndTime())) {//不走查询时间范围
                loginInfoAggre = bomanager.selectBusinessObjectsByNativeSql(req.getBegin(),req.getPageSize(),"select EVENTID,USERID,ORGID,USEREVENTTYPE,EVENTTIME,LOG,OS,BROWSER,IPADDRESS,STATUS from SYS_USER_EVENT  where "+ 
                        "userEventType =:loginSuccess or userEventType = :loginFailed order by eventTime",
                        "loginSuccess",UserEventType.LoginSuccess.id,"loginFailed",UserEventType.LoginFailed.id);
            } else {
                loginInfoAggre = bomanager.selectBusinessObjectsByNativeSql(req.getBegin(),req.getPageSize(), "select EVENTID,USERID,ORGID,USEREVENTTYPE,EVENTTIME,LOG,OS,BROWSER,IPADDRESS,STATUS from SYS_USER_EVENT  where "+
                        "(userEventType =:loginSuccess or userEventType = :loginFailed) and eventTime between :dateStartTime and :dateEndTime  order by eventTime",
                        "loginSuccess",UserEventType.LoginSuccess.id,"loginFailed",UserEventType.LoginFailed.id,
                        "dateStartTime",dateFormate(req.getSearchBeginTime()),"dateEndTime",dateFormate(req.getSearchEndTime()));
            }
        }else {
            if (Stream.of(searchAttributes).anyMatch(searchAttribute -> searchAttribute.equalsIgnoreCase(req.getSearchAttribute()))) {//验证查询条件
                if (StringUtils.isEmpty(req.getSearchBeginTime()) && StringUtils.isEmpty(req.getSearchEndTime())) { // 是否按时间搜索
                    String[] searchUserIds = {};
                    if (req.getSearchAttribute().equals("userName")) {
                        List<UserInfo> users = bomanager.loadBusinessObjects(UserInfo.class, "1 = 1 and username like :username","username","%" + req.getSearchContent()+ "%") ;
                        searchUserIds = users.stream()
                                .distinct()
                                .map(user -> user.getUserId())
                                .toArray(String[]::new);
                        if (searchUserIds.length == 0) {
                            searchUserIds = null;//sql优化报错
                        }
                        loginInfoAggre  = bomanager.selectBusinessObjectsByNativeSql(req.getBegin(),req.getPageSize(),"select EVENTID,USERID,ORGID,USEREVENTTYPE,EVENTTIME,LOG,OS,BROWSER,IPADDRESS,STATUS from SYS_USER_EVENT  where "+
                                "userId in ( :searchValue ) and (userEventType =:loginSuccess or userEventType = :loginFailed) order by eventTime",
                                "searchValue",searchUserIds,"loginSuccess",UserEventType.LoginSuccess.id,"loginFailed",UserEventType.LoginFailed.id);
                    }else {
                        loginInfoAggre  = bomanager.selectBusinessObjectsByNativeSql(req.getBegin(),req.getPageSize(),"select EVENTID,USERID,ORGID,USEREVENTTYPE,EVENTTIME,LOG,OS,BROWSER,IPADDRESS,STATUS from SYS_USER_EVENT  where "+
                                req.getSearchAttribute() + " like :searchContent and (userEventType =:loginSuccess or userEventType = :loginFailed) order by eventTime",
                                "searchContent","%"+req.getSearchContent()+"%","loginSuccess",UserEventType.LoginSuccess.id,"loginFailed",UserEventType.LoginFailed.id);
                    }
                }else {
                    String[] searchUserIds = {};
                    if (req.getSearchAttribute().equals("userName")) {
                        List<UserInfo> users = bomanager.loadBusinessObjects(UserInfo.class, "1 = 1 and username like :username","username","%" + req.getSearchContent() + "%");
                        searchUserIds = users.stream()
                                .distinct()
                                .map(user -> user.getUserId())
                                .toArray(String[]::new);
                        if (searchUserIds.length == 0) {
                            searchUserIds = null;//sql优化报错
                        }
                        loginInfoAggre  = bomanager.selectBusinessObjectsByNativeSql(req.getBegin(),req.getPageSize(),"select EVENTID,USERID,ORGID,USEREVENTTYPE,EVENTTIME,LOG,OS,BROWSER,IPADDRESS,STATUS from SYS_USER_EVENT  where "+
                                "userId in ( :searchValue ) and (userEventType =:loginSuccess or userEventType = :loginFailed) and eventTime between :dateStartTime and :dateEndTime order by eventTime",
                                "searchValue",searchUserIds,"loginSuccess",UserEventType.LoginSuccess.id,"loginFailed",UserEventType.LoginFailed.id,
                                "dateStartTime",dateFormate(req.getSearchBeginTime()),"dateEndTime",dateFormate(req.getSearchEndTime()));
                    }else {
                        loginInfoAggre  = bomanager.selectBusinessObjectsByNativeSql(req.getBegin(),req.getPageSize(),"select EVENTID,USERID,ORGID,USEREVENTTYPE,EVENTTIME,LOG,OS,BROWSER,IPADDRESS,STATUS from SYS_USER_EVENT  where "+
                                req.getSearchAttribute() + " like :searchContent and (userEventType =:loginSuccess or userEventType = :loginFailed)"
                                + "and eventTime between :dateStartTime and :dateEndTime order by eventTime",
                                "searchContent","%" + req.getSearchContent() + "%","loginSuccess",UserEventType.LoginSuccess.id,"loginFailed",UserEventType.LoginFailed.id,
                                "dateStartTime",dateFormate(req.getSearchBeginTime()),"dateEndTime",dateFormate(req.getSearchEndTime()));
                    }
                }
            
            }else {
                throw new ALSException("902032");
            }
        }
        rsp.setTotalCount(loginInfoAggre.getAggregate(" count(EVENTID) as cnt ").getInt("cnt"));
        for (UserEvent loginInfo : loginInfoAggre.getBusinessObjects().stream().map(bo -> this.convertBusinessObjectAggrateToUser(bo))
                .collect(Collectors.toList())) {//组装报文
            LoginInfo login = new LoginInfo();
            UserInfo user = bomanager.keyLoadBusinessObject(UserInfo.class, loginInfo.getUserId());
            login.setUserId(loginInfo.getUserId());
            if (user == null) {
                continue;
            }
            login.setUserName(user.getUserName());
            login.setOrgId(loginInfo.getOrgId());
            OrgInfo org = bomanager.keyLoadBusinessObject(OrgInfo.class, loginInfo.getOrgId());
            if (org != null) {
                login.setOrgName(org.getOrgName());
            }
            login.setUserEventType(MessageHelper.messageConvert(UserEventType.getName(loginInfo.getUserEventType())));
            login.setPasswordTime(user.getPasswordTime());
            login.setLogonTime(user.getLogonTime());
            login.setLogoutTime(user.getLogoutTime());
            login.setErrorTimes(user.getErrorTime().toString());
            login.setOs(loginInfo.getOs());
            login.setBrowser(loginInfo.getBrowser());
            login.setIpAddress(loginInfo.getIpAddress());
            login.setEventTime(loginInfo.getEventTime().format(DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format)));
            rsp.getInfos().add(login);
        }
        return rsp;
    }

    @Override
    public AllAuthChangesQueryRsp allAuthChangesQuery(AllAuthChangesQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        AllAuthChangesQueryRsp rsp = new AllAuthChangesQueryRsp();
        String[] searchAttributes = {"userId","userName","os","browser","ipAddress"};//查询条件
        rsp.setChanges(new ArrayList<AuthChange>());
        BusinessObjectAggregate<BusinessObject> changeInfoAggre = null;
        
        if (StringUtils.isEmpty(req.getSearchAttribute()) && StringUtils.isEmpty(req.getSearchContent())) {//不走查询条件
            if (StringUtils.isEmpty(req.getSearchBeginTime()) && StringUtils.isEmpty(req.getSearchEndTime())) {//不走查询时间范围
                changeInfoAggre = bomanager.selectBusinessObjectsByNativeSql(req.getBegin(),req.getPageSize(),"select EVENTID,USERID,ORGID,USEREVENTTYPE,EVENTTIME,LOG,OS,BROWSER,IPADDRESS,STATUS from SYS_USER_EVENT  where "+ 
                        "userEventType =:changeRole or userEventType = :changeGroup order by eventTime",
                        "changeRole",UserEventType.ChangeRole.id,"changeGroup",UserEventType.ChangeGroup.id);
            } else {
                changeInfoAggre = bomanager.selectBusinessObjectsByNativeSql(req.getBegin(),req.getPageSize(), "select EVENTID,USERID,ORGID,USEREVENTTYPE,EVENTTIME,LOG,OS,BROWSER,IPADDRESS,STATUS from SYS_USER_EVENT  where "+
                        "(userEventType =:changeRole or userEventType = :changeGroup) and eventTime between :dateStartTime and :dateEndTime  order by eventTime",
                        "changeRole",UserEventType.ChangeRole.id,"changeGroup",UserEventType.ChangeGroup.id,
                        "dateStartTime",dateFormate(req.getSearchBeginTime()),"dateEndTime",dateFormate(req.getSearchEndTime()));
            }
        }else {
            if (Stream.of(searchAttributes).anyMatch(searchAttribute -> searchAttribute.equalsIgnoreCase(req.getSearchAttribute()))) {//验证查询条件
                if (StringUtils.isEmpty(req.getSearchBeginTime()) && StringUtils.isEmpty(req.getSearchEndTime())) { // 是否按时间搜索
                    String[] searchUserIds = {};
                    if (req.getSearchAttribute().equals("userName")) {
                        List<UserInfo> users = bomanager.loadBusinessObjects(UserInfo.class, "1 = 1 and username like :username","username","%" + req.getSearchContent() + "%");
                        searchUserIds = users.stream()
                                .distinct()
                                .map(user -> user.getUserId())
                                .toArray(String[]::new);
                        if (searchUserIds.length == 0) {
                            searchUserIds = null;//sql优化报错
                        }
                        changeInfoAggre  = bomanager.selectBusinessObjectsByNativeSql(req.getBegin(),req.getPageSize(),"select EVENTID,USERID,ORGID,USEREVENTTYPE,EVENTTIME,LOG,OS,BROWSER,IPADDRESS,STATUS from SYS_USER_EVENT  where "+
                                "userId in ( :searchValue ) and (userEventType =:changeRole or userEventType = :changeGroup) order by eventTime",
                                "searchValue",searchUserIds,"changeRole",UserEventType.ChangeRole.id,"changeGroup",UserEventType.ChangeGroup.id);
                    }else {
                        changeInfoAggre  = bomanager.selectBusinessObjectsByNativeSql(req.getBegin(),req.getPageSize(),"select EVENTID,USERID,ORGID,USEREVENTTYPE,EVENTTIME,LOG,OS,BROWSER,IPADDRESS,STATUS from SYS_USER_EVENT  where "+
                                req.getSearchAttribute() + " like :searchContent and (userEventType =:changeRole or userEventType = :changeGroup) order by eventTime",
                                "searchContent","%"+req.getSearchContent()+"%","changeRole",UserEventType.ChangeRole.id,"changeGroup",UserEventType.ChangeGroup.id);
                    }
                }else {
                    String[] searchUserIds = {};
                    if (req.getSearchAttribute().equals("userName")) {
                        List<UserInfo> users = bomanager.loadBusinessObjects(UserInfo.class, "1 = 1 and instr(username,:username) > 0","username",req.getSearchContent());
                        searchUserIds = users.stream()
                                .distinct()
                                .map(user -> user.getUserId())
                                .toArray(String[]::new);
                        if (searchUserIds.length == 0) {
                            searchUserIds = null;//sql优化报错
                        }
                        changeInfoAggre  = bomanager.selectBusinessObjectsByNativeSql(req.getBegin(),req.getPageSize(),"select EVENTID,USERID,ORGID,USEREVENTTYPE,EVENTTIME,LOG,OS,BROWSER,IPADDRESS,STATUS from SYS_USER_EVENT  where "+
                                "userId in ( :searchValue ) and (userEventType =:changeRole or userEventType = :changeGroup) and eventTime between :dateStartTime and :dateEndTime order by eventTime",
                                "searchValue",searchUserIds,"changeRole",UserEventType.ChangeRole.id,"changeGroup",UserEventType.ChangeGroup.id,
                                "dateStartTime",dateFormate(req.getSearchBeginTime()),"dateEndTime",dateFormate(req.getSearchEndTime()));
                    }else {
                        changeInfoAggre  = bomanager.selectBusinessObjectsByNativeSql(req.getBegin(),req.getPageSize(),"select EVENTID,USERID,ORGID,USEREVENTTYPE,EVENTTIME,LOG,OS,BROWSER,IPADDRESS,STATUS from SYS_USER_EVENT  where "+
                                req.getSearchAttribute() + " like :searchContent and (userEventType =:changeRole or userEventType = :changeGroup)"
                                + "and eventTime between :dateStartTime and :dateEndTime order by eventTime",
                                "searchContent","%"+req.getSearchContent()+"%","changeRole",UserEventType.ChangeRole.id,"changeGroup",UserEventType.ChangeGroup.id,
                                "dateStartTime",dateFormate(req.getSearchBeginTime()),"dateEndTime",dateFormate(req.getSearchEndTime()));
                    }
                }
            }else {
                throw new ALSException("902032");
            }
        }
        rsp.setTotalCount(changeInfoAggre.getAggregate("count(EVENTID) as cnt").getInt("cnt"));
        for (UserEvent changeInfo : changeInfoAggre.getBusinessObjects().stream().map(bo -> this.convertBusinessObjectAggrateToUser(bo))
                .collect(Collectors.toList())) {//组装报文
            AuthChange login = new AuthChange();
            UserInfo user = bomanager.keyLoadBusinessObject(UserInfo.class, changeInfo.getUserId());
            login.setUserId(changeInfo.getUserId());
            login.setUserName(user.getUserName());
            login.setOrgId(changeInfo.getOrgId());
            OrgInfo org = bomanager.keyLoadBusinessObject(OrgInfo.class, changeInfo.getOrgId());
            if (org != null) {
                login.setOrgName(org.getOrgName());
            }
            login.setUserEventType(MessageHelper.messageConvert(UserEventType.getName(changeInfo.getUserEventType())));
            login.setLog(changeInfo.getLog());
            login.setOs(changeInfo.getOs());
            login.setBrowser(changeInfo.getBrowser());
            login.setIpAddress(changeInfo.getIpAddress());
            login.setEventTime(changeInfo.getEventTime().format(DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format)));
            rsp.getChanges().add(login);
        }
        return rsp;
    }
    
    private LocalDateTime getDateStartTime() {
        return LocalDateTime.parse(DateHelper.getBusinessDate() + DATE_START_TIME, DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format));
    }
    
    private LocalDateTime getDateEndTime() {
        return LocalDateTime.parse(DateHelper.getRelativeDate(DateHelper.getBusinessDate(), TermUnit.Day.id,1) + DATE_START_TIME, DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format));
    }
    
    private LocalDateTime dateFormate(String time) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format));
    }
    
    private UserEvent convertBusinessObjectAggrateToUser(BusinessObject aggregate) {
        UserEvent event = new UserEvent();
        event.setEventId(aggregate.getString("EventId"));
        event.setUserId(aggregate.getString("UserId"));
        event.setOrgId(aggregate.getString("OrgId"));
        event.setUserEventType(aggregate.getString("UserEventType"));
        event.setEventTime(LocalDateTime.parse(aggregate.getString("EventTime"), DateTimeFormatter.ofPattern(FormatType.DateTimeLongFormat.format)));
        event.setOs(aggregate.getString("OS"));
        event.setBrowser(aggregate.getString("BROWSER"));
        event.setIpAddress(aggregate.getString("IPADDRESS"));
        event.setStatus(aggregate.getString("STATUS")); 
        return event;
    }

    @Override
    @Cacheable(value=KEY_FRE, key="#root.target.ONLINE_PEOPLE_NUM")
    public OnlineUserListRsp getOnlineUserList(OnlineUserListReq reqMsg) {
        OnlineUserListRsp rsp = new OnlineUserListRsp();
        rsp.setOnlineUsers(new ArrayList<>());
        Set<String> tokens = redisTemplate.keys(ACCESS_TOKEN + "*");
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance si = loadBalancerClient.choose("aoas-server");
        for (String token : tokens) {
            //1.调用aoas服务检查token
            URI uri = UriComponentsBuilder.fromUri(si.getUri()).path("/aoas/oauth/check_token").queryParam("token", token.split(ACCESS_TOKEN)[1]).build(false).toUri();
            
             //2.请求头拼装
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            requestHeaders.add("Authorization", authorization);

            //3.请求体拼装
            HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

            //通过token获取用户相关信息
            ResponseEntity<Map> result;
            try {
                //4.发送http请求
                result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, Map.class);
            } catch (HttpClientErrorException e) {
                continue;
            }
            String userId = (String)result.getBody().get("UserId");
            User user = UserHelper.getUser(userId);
            Boolean addFlag = null;
            if (!StringUtils.isEmpty(reqMsg.getOrgId()) || !StringUtils.isEmpty(reqMsg.getRoleId())) {
                if (!StringUtils.isEmpty(reqMsg.getOrgId()) && !StringUtils.isEmpty(reqMsg.getRoleId())) {
                    if (user.getUserBelongs().stream().anyMatch(ub -> ub.getOrgId().equals(reqMsg.getOrgId())) && user.getRoles().stream().anyMatch(r -> r.getRoleId().equals(reqMsg.getRoleId()))) {
                        addFlag = true;
                    }
                }else if (!StringUtils.isEmpty(reqMsg.getOrgId())) {
                    if (user.getUserBelongs().stream().anyMatch(ub -> ub.getOrgId().equals(reqMsg.getOrgId()))) {
                        addFlag = true;
                    }
                }else if (!StringUtils.isEmpty(reqMsg.getRoleId())) {
                    if (user.getRoles().stream().anyMatch(r -> r.getRoleId().equals(reqMsg.getRoleId()))) {
                        addFlag = true;
                    }
                }
                if (addFlag == null) {
                    addFlag = false;
                }
            }
            if (addFlag == null || addFlag) {
                rsp.getOnlineUsers().add(user);
            }
        }
        rsp.setOnlineTotalCount(rsp.getOnlineUsers().size());
        return rsp;
    }
}
