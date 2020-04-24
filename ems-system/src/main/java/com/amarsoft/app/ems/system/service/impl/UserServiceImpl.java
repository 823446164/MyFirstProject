package com.amarsoft.app.ems.system.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.aecd.common.constant.Status;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.aecd.system.constant.AuthType;
import com.amarsoft.aecd.system.constant.Layout;
import com.amarsoft.aecd.system.constant.MigrationStatus;
import com.amarsoft.aecd.system.constant.Skin;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.aecd.system.constant.UserEventType;
import com.amarsoft.aecd.system.constant.UserLeaveStatus;
import com.amarsoft.aecd.system.constant.UserStatus;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arem.util.SpringHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.arpe.util.CleanPathUtil;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.cs.dto.adduser.AddUserReq;
import com.amarsoft.app.ems.system.cs.dto.changeuserleave.ChangeUserLeaveReq;
import com.amarsoft.app.ems.system.cs.dto.deleteuser.DeleteUserReq;
import com.amarsoft.app.ems.system.cs.dto.groupallquery.GroupAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.menuallquery.MenuAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.multitoggleuserstatus.MultiToggleUserStatusReq;
import com.amarsoft.app.ems.system.cs.dto.multiuserresetpasswd.MultiUserResetPasswdReq;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleauth.RoleAuth;
import com.amarsoft.app.ems.system.cs.dto.setdefaultorg.SetDefaultOrgReq;
import com.amarsoft.app.ems.system.cs.dto.toggleuserstatus.ToggleUserStatusReq;
import com.amarsoft.app.ems.system.cs.dto.updateuser.UpdateUserReq;
import com.amarsoft.app.ems.system.cs.dto.userbelongquery.UserBelongQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userbelongquery.UserBelongQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.usercancelleave.UserCancelLeaveReq;
import com.amarsoft.app.ems.system.cs.dto.usercancelsubst.UserCancelSubstReq;
import com.amarsoft.app.ems.system.cs.dto.usergroup.Group;
import com.amarsoft.app.ems.system.cs.dto.usergroup.UserGroupReq;
import com.amarsoft.app.ems.system.cs.dto.userleave.UserLeaveReq;
import com.amarsoft.app.ems.system.cs.dto.userleavequery.Leave;
import com.amarsoft.app.ems.system.cs.dto.userleavequery.UserLeaveQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userleavequery.UserLeaveQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.usermenu.Menu;
import com.amarsoft.app.ems.system.cs.dto.usermenu.UserMenuRsp;
import com.amarsoft.app.ems.system.cs.dto.userpasswdquery.UserPasswdQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.userquery.User;
import com.amarsoft.app.ems.system.cs.dto.userquery.UserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userquery.UserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.userquerybyorg.SimpleUser;
import com.amarsoft.app.ems.system.cs.dto.userquerybyorg.UserQueryByOrgReq;
import com.amarsoft.app.ems.system.cs.dto.userquerybyorg.UserQueryByOrgRsp;
import com.amarsoft.app.ems.system.cs.dto.userquerybyparentorg.UserQueryByParentOrgReq;
import com.amarsoft.app.ems.system.cs.dto.userquerybyparentorg.UserQueryByParentOrgRsp;
import com.amarsoft.app.ems.system.cs.dto.userresetpasswd.UserResetPasswdReq;
import com.amarsoft.app.ems.system.cs.dto.userrole.Role;
import com.amarsoft.app.ems.system.cs.dto.userrole.UserRoleReq;
import com.amarsoft.app.ems.system.cs.dto.usersetpasswd.UserSetPasswdReq;
import com.amarsoft.app.ems.system.cs.dto.usersubst.UserSubstReq;
import com.amarsoft.app.ems.system.cs.dto.usersubstquery.Subst;
import com.amarsoft.app.ems.system.cs.dto.usersubstquery.UserSubstQueryReq;
import com.amarsoft.app.ems.system.cs.dto.usersubstquery.UserSubstQueryRsp;
import com.amarsoft.app.ems.system.entity.GroupInfo;
import com.amarsoft.app.ems.system.entity.OrgInfo;
import com.amarsoft.app.ems.system.entity.RoleInfo;
import com.amarsoft.app.ems.system.entity.SubstInfo;
import com.amarsoft.app.ems.system.entity.SubstRole;
import com.amarsoft.app.ems.system.entity.UserBelong;
import com.amarsoft.app.ems.system.entity.UserGroup;
import com.amarsoft.app.ems.system.entity.UserInfo;
import com.amarsoft.app.ems.system.entity.UserLayout;
import com.amarsoft.app.ems.system.entity.UserLeave;
import com.amarsoft.app.ems.system.entity.UserRole;
import com.amarsoft.app.ems.system.entity.UserTeam;
import com.amarsoft.app.ems.system.service.GroupService;
import com.amarsoft.app.ems.system.service.MenuService;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.RoleService;
import com.amarsoft.app.ems.system.service.UserService;
import com.amarsoft.app.ems.system.util.UserEventHelper;
import com.amarsoft.app.ems.system.util.UserHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户服务逻辑的处理类
 * @author hzhang23
 */
@Slf4j
@Service
@RefreshScope
public class UserServiceImpl implements UserService {

    private final static String KEY_FRE="user:";   
    private final static String KEY_USERLOGON="userlogon:";
    private final static String KEY_USERP5D="uerp5d:";
    public final static String KEY_FRE_SUBST = "userSubst:";
    
    
    @Value("${global.url.allowed}")
    String allowedUrl;

    @Value("${global.system.systemId}")
    String systemId;
    
    @Value("${global.fileoperations.image.max-file-size}")
    String fileSize;
    
    @Value("${global.fileoperations.headportrait.save-path}")
    String headPortraitSavePath;
    
    @Value("${global.fileoperations.headportrait.default-path}")
    String defaultHeadPortraitPath;
    
    @Value("${global.business.org.default-length}")
    String orgDefaultLength;
    
    @Value("${global.business.user.delete.bpm-in-transit-business}")
    boolean bpmFlag;
    @Value("${global.business.user.delete.apms-in-transit-business}")
    boolean apmsFlag;
    
    @Autowired
    OrgService orgService;
    
    /*@Autowired
    ProductCommonClient productCommonClient;*/
    
    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="'"+KEY_FRE+"'+ #req.getUserId()"),
            @CacheEvict(value=KEY_USERLOGON, key="'"+KEY_USERLOGON+"' + #req.getLogonId()"),
            @CacheEvict(value=KEY_USERP5D, key="'"+KEY_USERP5D+"' + #req.getUserId()")}
    )
    public void addUser(HttpHeaders header,AddUserReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserInfo ui = bomanager.keyLoadBusinessObject(UserInfo.class, req.getUserId());
        if(ui != null) //数据库不存在该用户
            throw new ALSException("900902");//不要重复保存
        ui = new UserInfo();
        ui.setUserId(req.getUserId());
        ui.setLogonId(req.getLogonId());
        ui.setUserName(req.getUserName());
        ui.setCounter(req.getCounter());
        ui.setEmail(req.getEmail());
        ui.setEmpNo(req.getEmpNo());
        ui.setPhoneNo(req.getPhoneNo());
        ui.setJobTitle(req.getJobTitle());
        if (!StringUtils.isEmpty(req.getUuid())) {
            this.copyStorageUserHeadportrait(req.getUuid(), ui);//将原上传的用户头像复制到自己的目录下
        }
        if(!StringUtils.isEmpty(req.getLanguage()))
            ui.setLanguage(req.getLanguage());//不为空才设置
        if(!StringUtils.isEmpty(req.getSkin()))
            ui.setSkin(req.getSkin());//不为空才设置
        if(!StringUtils.isEmpty(req.getLayout()))
            ui.setLayout(req.getLayout());//不为空才设置
        ui.setOfficeTel(req.getOfficeTel());
        ui.setStatus(req.getStatus());
        bomanager.updateBusinessObject(ui);
        
        addUserBelong(bomanager, req.getUserId(), req.getUserBelongs());
        role(header,bomanager,req.getUserId(),req.getRoles());
        group(header,bomanager,req.getUserId(),req.getGroups());
        
        bomanager.updateDB();
    }
    
    /**
     * 将原上传的用户头像复制到自己的目录下
     * @param uuid 图片uuid
     * @param ui 用户实体
     */
    private void copyStorageUserHeadportrait(String uuid,UserInfo ui){
        String resourceFolderName = headPortraitSavePath+ File.separator + "storage" + File.separator + uuid + File.separator;
        String targetFolderName = headPortraitSavePath+ File.separator + ui.getUserId() + File.separator + uuid + File.separator;
        File resource = null;
        File folder = new File(resourceFolderName);//原始数据
        File target = new File(targetFolderName);//用户头像获取文件数据
        if (!target.exists()) {
            target.getParentFile().mkdirs();//创建父级文件夹
        }
        resource = new File(resourceFolderName + folder.list()[0]);
        target = new File(targetFolderName + folder.list()[0]);
        if (!target.exists()) {
            try {
                target.getParentFile().mkdirs();//创建父级文件夹
                target.createNewFile();
            } catch (IOException e) {
                if (log.isErrorEnabled()) {
                    log.error("创建{"+ui.getUserId()+"}头像文件时出错：",e);
                }
                throw new ALSException("900931",e);
            }
        }
        try(FileInputStream fis = new FileInputStream(resource);FileOutputStream fos = new FileOutputStream(target);) {
            byte[] b = new byte[1024];
            int len = -1;
                while ((len = fis.read(b)) != -1) {
                    fos.write(b, 0, len);
                    fos.flush();
                }
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("复制用户头像时出错：",e);
            }
            throw new ALSException("900931",e);
        }
        if (target.getParentFile().listFiles().length > 0) {
            ui.setHeadPortrait(uuid);
        }else {
            if (log.isWarnEnabled()) {
                log.warn("添加{"+ui.getUserId()+"}用户时，头像未正常引入。");
            }
        }
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="'"+KEY_FRE+"'+ #req.getUserId()"),
            @CacheEvict(value=KEY_USERLOGON, key="'"+KEY_USERLOGON+"' + #req.getLogonId()"),
            @CacheEvict(value=KEY_USERP5D, key="'"+KEY_USERP5D+"' + #req.getUserId()")}
    )
    public boolean updateUser(HttpHeaders header,UpdateUserReq req) {
        boolean flag = false;
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserInfo ui = bomanager.keyLoadBusinessObject(UserInfo.class, req.getUserId());
        if(ui == null) {//数据库不存在该用户
            ui = new UserInfo();
        }else {
            SpringHelper.getBean(UserServiceImpl.class).clearUserLogon(ui.getLogonId());
        }
        ui.setUserId(req.getUserId());
        ui.setLogonId(req.getLogonId());
        ui.setUserName(req.getUserName());
        ui.setCounter(req.getCounter());
        ui.setEmail(req.getEmail());
        ui.setEmpNo(req.getEmpNo());
        ui.setPhoneNo(req.getPhoneNo());
        ui.setJobTitle(req.getJobTitle());
        if(!StringUtils.isEmpty(req.getLanguage()))
            ui.setLanguage(req.getLanguage());//不为空才设置
        if(!StringUtils.isEmpty(req.getSkin()))
            ui.setSkin(req.getSkin());//不为空才设置
        if(!StringUtils.isEmpty(req.getLayout()))
            ui.setLayout(req.getLayout());//不为空才设置
        ui.setOfficeTel(req.getOfficeTel());
        flag = flag || ui.getStatus() == null || !ui.getStatus().equals(req.getStatus());
        ui.setStatus(req.getStatus());
        bomanager.updateBusinessObject(ui);
        
        if (YesNo.No.id.equals(req.getCenterFlag()) || StringUtils.isEmpty(req.getCenterFlag())) {//不为个人中心则更新role group
            addUserBelong(bomanager, req.getUserId(), req.getUserBelongs());
            flag = flag || role(header,bomanager,req.getUserId(),req.getRoles());
            flag = flag || group(header,bomanager,req.getUserId(),req.getGroups());
        }
        
        bomanager.updateDB();
        return flag;
    }
    
    /**
     * 清理logonId缓存
     * @param logonId
     */
    @Caching(evict = {
            @CacheEvict(value=KEY_USERLOGON, key="'"+KEY_USERLOGON+"' + #logonId")
        }
    )
    public void clearUserLogon(String logonId) {
        
    }
    

    /**
     * 添加用户所属机构信息
     * @param bomanager 本方法内部不做updateDB
     * @param userId 用户信息
     * @param list 接口输入的所属信息
     */
    private void addUserBelong(BusinessObjectManager bomanager,String userId,List<com.amarsoft.app.ems.system.cs.dto.userquery.UserBelong> list) {
        ArrayList<UserBelong> addList = new ArrayList<UserBelong>();
        for (com.amarsoft.app.ems.system.cs.dto.userquery.UserBelong userBelong : list) {
            UserBelong belong = null;
            List<UserBelong> ub = bomanager.loadBusinessObjects(UserBelong.class, "userId = :userId and orgId = :orgId","userId",userId,"orgId",userBelong.getOrgId());
            if(CollectionUtils.isEmpty(ub)) {
                belong = new UserBelong();
            }else {
                belong =ub.get(0);
            }
            belong.setUserId(userId);
            belong.setOrgId(userBelong.getOrgId());
            belong.setDefaultFlag(userBelong.getDefaultFlag());
            belong.setDataAuth(userBelong.getDataAuth());
            if(StringUtils.isEmpty(userBelong.getMigrationStatus())) {
                belong.setMigrationStatus(MigrationStatus.Normal.id);
            }
            if(log.isInfoEnabled()) {
                log.info("用户："+userId+"的所属机构从："+ub.toString()+" 变更为："+userBelong.toString());
            }
            addList.add(belong);
        }
        bomanager.updateBusinessObjects(addList);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="'"+KEY_FRE+"' + #req.getUserId()"),
            @CacheEvict(value=KEY_USERP5D, key="'"+KEY_USERP5D+"' + #req.getUserId()")}
    )
    public void deleteUser(DeleteUserReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        //在途业务查询
        if (bpmFlag || apmsFlag) {
            User user = UserHelper.getUser(req.getUserId());
            int businessCount = 0;
            if (bpmFlag) {
                for (com.amarsoft.app.ems.system.cs.dto.userquery.UserBelong belong : user.getUserBelongs()) {
                    /*
                    QueryRuntimeTasksReq taskReq = new QueryRuntimeTasksReq();
                    taskReq.setAssignee(req.getUserId());
                    taskReq.setOrgId(belong.getOrgId());
                    taskReq.setTenantId(systemId);
                    taskReq.setBegin(0);
                    taskReq.setPageSize(0);
                    Integer taskTotalCount = runtimeTaskClient.queryRuntimeTasks(new RequestMessage<QueryRuntimeTasksReq>(taskReq)).getBody().getMessage().getTotalCount();
                    businessCount += taskTotalCount;
                    */
                }
            }
            
            if (apmsFlag) {
                /*JudgeExistedBusinessReq existedBusinessReq = new JudgeExistedBusinessReq();
                existedBusinessReq.setUserId(req.getUserId());
                boolean flag = productCommonClient.judgeExistedBusiness(new RequestMessage<JudgeExistedBusinessReq>(existedBusinessReq)).getBody().getMessage().getResult();
                if (flag) {//有在途业务则加一条总数
                    businessCount ++;
                }*/
            }
            
            if (businessCount != 0) {
                if (log.isDebugEnabled()) {
                    log.debug("删除用户[" + req.getUserId() +"]时，有在图业务判断");
                }
                throw new ALSException("900928");
            }
        }
        
        
        if(log.isInfoEnabled()) {
            log.info("删除用户："+req.getUserId()+"及其角色和组信息。");
        }
        //删除该用户
        bomanager.deleteObjectBySql(UserInfo.class, "userId=:userId", "userId", req.getUserId());
        //用户对应角色
        bomanager.deleteObjectBySql(UserRole.class, "userId=:userId", "userId", req.getUserId());
        //用户对应角色组
        bomanager.deleteObjectBySql(UserGroup.class, "userId=:userId", "userId", req.getUserId());
        //用户对应所属机构
        bomanager.deleteObjectBySql(UserBelong.class, "userId=:userId", "userId", req.getUserId());
        //用户请假信息
        bomanager.deleteObjectBySql(UserLeave.class, "userId=:userId", "userId", req.getUserId());
        //用户转授权信息
        bomanager.deleteObjectBySql(SubstRole.class, "substId in(select substId from SubstInfo where userId=:userId)", "userId", req.getUserId());
        bomanager.deleteObjectBySql(SubstRole.class, "substId in(select substId from SubstInfo where substUserId=:userId)", "userId", req.getUserId());
        bomanager.deleteObjectBySql(SubstInfo.class, "userId=:userId", "userId", req.getUserId());
        bomanager.deleteObjectBySql(SubstInfo.class, "substUserId=:userId", "userId", req.getUserId());
        bomanager.deleteObjectBySql(UserLayout.class, "userId=:userId", "userId", req.getUserId());
        bomanager.updateDB();
    }

    @Override
    @Cacheable(value=KEY_FRE, key="'"+KEY_FRE+"' + #req.getUserId()", condition = "#req.getUserId() != null && #currentOrg == false")
    public UserQueryRsp getUsers(UserQueryReq req, boolean currentOrg) {
        if(StringUtils.isEmpty(req.getUserId())
                && StringUtils.isEmpty(req.getOrgId())
                && StringUtils.isEmpty(req.getLogonId()) 
                && (req.getBegin() == null 
                || req.getPageSize() == null 
                || req.getPageSize() == 0))
        {
            throw new ALSException("900908");
        }
        UserQueryRsp rsp = new UserQueryRsp();
        rsp.setUsers(new ArrayList<User>());
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        String[] searchAttributes = {"userId","userName","logonId","status"};//查询条件
        BusinessObjectAggregate<UserInfo> uis = null;
        if(StringUtils.isEmpty(req.getOrgId())) {
            req.setOrgId(GlobalShareContextHolder.getOrgId());
        }
        if (StringUtils.isEmpty(req.getSearchAttribute()) && StringUtils.isEmpty(req.getSearchContent())) {//查询条件为空
            if(!StringUtils.isEmpty(req.getUserId()) //查询用户详情
                    || !StringUtils.isEmpty(req.getLogonId()) ) {
                UserInfo ui;
                if(!StringUtils.isEmpty(req.getUserId()))
                    ui= bomanager.keyLoadBusinessObject(UserInfo.class, req.getUserId());
                else
                    ui= bomanager.loadBusinessObject(UserInfo.class, "logonId", req.getLogonId());
                if(ui == null) {
                    rsp.setTotalCount(0);
                    return rsp;
                }
                
                User u = new User();
                setUserData(u,ui,currentOrg,bomanager);
                rsp.setTotalCount(1);
                rsp.getUsers().add(u);
            }else {//根据传入机构查询用户
                uis = bomanager.loadBusinessObjects(UserInfo.class,req.getBegin(), req.getPageSize(),
                        " userId in ( select userId from UserBelong where orgId like :orgId)","orgId",req.getOrgId() + "%");
            }
        }else {//按条件查询
            if (Stream.of(searchAttributes).anyMatch(searchAttribute -> searchAttribute.equalsIgnoreCase(req.getSearchAttribute()))) {//验证查询条件
                if (StringUtils.isEmpty(req.getOrgId())) {
                    uis  = bomanager.loadBusinessObjects(UserInfo.class,req.getBegin(),req.getPageSize(), req.getSearchAttribute() + " like :searchContent",
                            "searchContent","%"+req.getSearchContent()+"%");
                } else {
                    uis = bomanager.loadBusinessObjects(UserInfo.class,req.getBegin(), req.getPageSize(),
                            " userId in ( select userId from UserBelong where orgId like :orgId) and " + req.getSearchAttribute()+ " like :searchContent", "orgId", req.getOrgId() + "%", "searchContent", "%"+req.getSearchContent()+"%");
                }
            }
        }
        
        //组装结果  uis在查询单条记录的时候会为空
        if (uis != null && !CollectionUtils.isEmpty(uis.getBusinessObjects())) {
            for(UserInfo ui : uis.getBusinessObjects()) {
                User u = new User();
                setUserData(u, ui, currentOrg, bomanager);
                rsp.getUsers().add(u);
            }
            rsp.setTotalCount(uis.getAggregate("count(userId) as cnt").getInt("cnt"));
        }
        return rsp;
    }
    /**
     * 填充用户返回对象
     * @param u
     * @param ui
     * @param currentOrg 是否获取当前机构项下的用户所属机构
     * @param bomanager 数据管理对象
     */
    private void  setUserData(User u, UserInfo ui, boolean currentOrg, BusinessObjectManager bomanager){
        u.setUserId(ui.getUserId());
        u.setLogonId(ui.getLogonId());
        u.setUserName(ui.getUserName());
        u.setCounter(ui.getCounter());
        u.setEmail(ui.getEmail());
        u.setEmpNo(ui.getEmpNo());
        u.setPhoneNo(ui.getPhoneNo());
        u.setJobTitle(ui.getJobTitle());
        u.setSkin(StringUtils.isEmpty(ui.getSkin())?Skin.Default.id:ui.getSkin());
        u.setLayout(StringUtils.isEmpty(ui.getLayout())?Layout.Side.id:ui.getLayout());
        u.setLanguage(ui.getLanguage());
        u.setOfficeTel(ui.getOfficeTel());
        u.setStatus(ui.getStatus());
        u.setUserBelongs(new ArrayList<com.amarsoft.app.ems.system.cs.dto.userquery.UserBelong>());
        u.setRoles(new ArrayList<Role>());
        u.setGroups(new ArrayList<Group>());
        u.setGroupRoles(new ArrayList<>());
        u.setTeamIds(new ArrayList<String>());
        
        //用户所属机构组装报文
        List<UserBelong> userBelongs = bomanager.loadBusinessObjects(UserBelong.class, "userId = :userId","userId",ui.getUserId());
        
        for (UserBelong userBelong : userBelongs) {
            if (currentOrg && !StringUtils.isEmpty(GlobalShareContextHolder.getOrgId())) {
                if(orgService.isParentOrg(GlobalShareContextHolder.getOrgId(),userBelong.getOrgId()) || userBelong.getOrgId().equals(GlobalShareContextHolder.getOrgId())) { //过滤非当前机构及项下机构
                    this.setUserBelongRsp(u.getUserBelongs(), bomanager, userBelong);
                }
            }
            
        }
        
        List<UserRole> urs = bomanager.loadBusinessObjects(UserRole.class, "userId=:userId", 
                                                                           "userId", ui.getUserId());
        for(UserRole ur : urs) {
            Role role = new Role();
            role.setRoleId(ur.getRoleId());
            RoleInfo r = bomanager.keyLoadBusinessObject(RoleInfo.class, ur.getRoleId());
            role.setRoleName(r.getRoleName());
            role.setOrgId(ur.getOrgId());
            u.getRoles().add(role);
        }
        
        List<UserGroup> ugs = bomanager.loadBusinessObjects(UserGroup.class, "userId=:userId", 
                "userId", ui.getUserId());
        for(UserGroup ug : ugs) {
        Group g = new Group();
        g.setGroupId(ug.getGroupId());
        g.setOrgId(ug.getOrgId());
        u.getGroups().add(g);
        }
        
        for(UserGroup ug : ugs) {
            BusinessObjectAggregate<RoleInfo> roles = bomanager.loadBusinessObjects(RoleInfo.class,0,Integer.MAX_VALUE, "roleId in (select roleId from GroupRole where groupId = :groupId)","groupId",ug.getGroupId());
            for (RoleInfo r : roles.getBusinessObjects()) {
                Role role = new Role();
                role.setRoleId(r.getRoleId());
                role.setRoleName(r.getRoleName());
                role.setOrgId(ug.getOrgId());
                u.getGroupRoles().add(role);
            }
        }
        
        List<UserTeam> uts = bomanager.loadBusinessObjects(UserTeam.class,"userId=:userId", 
                                                                              "userId", ui.getUserId());
        for (UserTeam userTeam : uts) {
            u.getTeamIds().add(userTeam.getTeamId());
        }
    }
    
    /**
     * 组装用户所属机构返回报文
     * @param userBelongs　返回数组信息
     * @param bomanager　业务对象管理器
     * @param userBelong　用户所属机构信息
     */
    private void setUserBelongRsp(List<com.amarsoft.app.ems.system.cs.dto.userquery.UserBelong> userBelongs,BusinessObjectManager bomanager,UserBelong userBelong) {
        OrgInfo org = bomanager.keyLoadBusinessObject(OrgInfo.class, userBelong.getOrgId());

        com.amarsoft.app.ems.system.cs.dto.userquery.UserBelong belong = new com.amarsoft.app.ems.system.cs.dto.userquery.UserBelong();
        belong.setDataAuth(userBelong.getDataAuth());
        belong.setDefaultFlag(userBelong.getDefaultFlag());
        belong.setOrgId(userBelong.getOrgId());
        
        belong.setBelongRootOrg(org.getRootOrgId());
        belong.setOrgName(org.getOrgName()); 
        belong.setBelongOrgLevel(org.getOrgLevel());
        if(!SystemStatus.Normal.id.equals(org.getStatus())) {//机构状态不正常，用户所属机构也返回停用
            belong.setMigrationStatus(MigrationStatus.BlockUp.id);
        }else {
            belong.setMigrationStatus(userBelong.getMigrationStatus());
        }
        userBelongs.add(belong);
    }
    
    @Override
    @Cacheable(value=KEY_USERLOGON, key="'"+KEY_USERLOGON+"'+ #logonId")
    public String getUserId(String logonId) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserInfo ui = bomanager.loadBusinessObject(UserInfo.class, "logonId", logonId);
        if(ui == null) {
            return null;
        }else {
            return ui.getUserId();
        }
    }
    
    @Override
    @Cacheable(value=KEY_USERP5D, key="'"+KEY_USERP5D+"'+ #userId")
    public UserPasswdQueryRsp getUserPasswd(String userId) {
        UserPasswdQueryRsp rsp = new UserPasswdQueryRsp();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserInfo ui = bomanager.loadBusinessObject(UserInfo.class, "userId", userId);
        if (ui == null)
            return rsp;
        rsp.setLogonId(ui.getLogonId());
        rsp.setUserId(ui.getUserId());
        rsp.setPassword(ui.getPassword());
        rsp.setStatus(ui.getStatus());
        rsp.setUserName(ui.getUserName());
        rsp.setPasswordTime(ui.getPasswordTime());
        rsp.setLogonTime(ui.getLogonTime());
        rsp.setLogoutTime(ui.getLogoutTime());
        rsp.setErrorTime(ui.getErrorTime());
        List<UserBelong> ubs = bomanager.loadBusinessObjects(UserBelong.class, "userId = :userId and migrationStatus = :migrationStatus order by defaultFlag desc", "userId", ui.getUserId(), "migrationStatus", MigrationStatus.Normal.id);
        if (!CollectionUtils.isEmpty(ubs)) {//登录时补user_belong数据
            rsp.setBelongOrgId(ubs.get(0).getOrgId());
            return rsp;
        } else {
            throw new ALSException("900941");
        }
    }
    
    @Override
    public void setLogonSuccess(String userId, String logonTime) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        bomanager.updateObjectBySql(UserInfo.class, "errorTime=0,logonTime=:logonTime", "userId=:userId", "userId", userId, "logonTime",logonTime);
        bomanager.updateDB();
    }

    @Override
    @CacheEvict(value=KEY_USERP5D, key="'"+KEY_USERP5D+"'+ #userId")
    public boolean setLogonFailure(String userId, String status) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserInfo ui= bomanager.loadBusinessObject(UserInfo.class, "userId", userId);
        boolean flag = false;//是否需要删除缓存
        if(ui != null && !status.equals(ui.getStatus())) {
            flag = true;
        }
        bomanager.updateObjectBySql(UserInfo.class, "errorTime=errorTime+1,status=:status", "userId=:userId", "userId", userId, "status", status);
        bomanager.updateDB();
        return flag;
    }

    @Override
    public void setLogoutSuccess(String userId, String logoutTime) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        bomanager.updateObjectBySql(UserInfo.class, "logoutTime=:logoutTime", "userId=:userId", "userId", userId, "logoutTime", logoutTime);
        bomanager.updateDB();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="'"+KEY_FRE+"'+ #req.getUserId()")}
    )
    public boolean userRole(HttpHeaders header,UserRoleReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        boolean flag = role(header,bomanager, req.getUserId(), req.getRoles());
        bomanager.updateDB();
        return flag;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="'"+KEY_FRE+"'+ #req.getUserId()")}
    )
    public boolean userGroup(HttpHeaders header , UserGroupReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        boolean flag = group(header,bomanager, req.getUserId(), req.getGroups());
        bomanager.updateDB();
        return flag;
    }

    @Override
    public UserMenuRsp getUserMenus(String userId, String orgId, UserService userService, RoleService roleService, GroupService groupService, MenuService menuService) {
        //获取所有角色
        RoleAllQueryRsp allRoles = roleService.roleAllQuery(new RoleAllQueryReq());
        //获取所有角色组
        GroupAllQueryRsp allGroups= groupService.groupAllQuery();
        //获取用户转授权信息
        UserSubstQueryReq substReq = new UserSubstQueryReq();
        
        ArrayList<String> status = new ArrayList<String>();
        status.add(UserLeaveStatus.Able.id);
        substReq.setStatus(status);
        substReq.setSubstUserId(userId);
        substReq.setBegin(0);
        substReq.setPageSize(Integer.MAX_VALUE);
        UserSubstQueryRsp allSubst= userService.getUserSubsts(substReq);
        
        //获取当前用户信息
        UserQueryReq userQueryReq = new UserQueryReq();
        userQueryReq.setUserId(userId);
        UserQueryRsp userQueryRsp = userService.getUsers(userQueryReq, false);
        
        //获取当前用户的所有权限
        List<RoleAuth> roleAuths = getUserRoleAuths(orgId, userQueryRsp.getUsers().get(0), allRoles, allGroups, allSubst);
        
        UserMenuRsp userMenuRsp = new UserMenuRsp();
        userMenuRsp.setMenus(new ArrayList<Menu>());
        
        if(CollectionUtils.isEmpty(roleAuths))
            return userMenuRsp;//用户没有授权信息就返回空的菜单列表
        
        //获取所有菜单
        MenuAllQueryRsp allMenus = menuService.getAllMenu(LocaleContextHolder.getLocale().toLanguageTag().toLowerCase());
        //获取用户权限菜单
        userMenuRsp.setMenus(getUserMenus(roleAuths, allMenus.getMenuInfos()));
        return userMenuRsp;
    }

    /**
     * 获取用户当前登录机构下的权限
     * @param user 用户基本信息
     * @param allMenus 取缓存中配置
     * @param allRoles 取缓存中配置
     * @param allGroups 取缓存中配置
     * @param allSubst 取缓存中配置
     * @return 
     */
    private List<RoleAuth> getUserRoleAuths(String orgId, User user, RoleAllQueryRsp allRoles, GroupAllQueryRsp allGroups,UserSubstQueryRsp allSubst) {
        List<RoleAuth> roleAuths = new ArrayList<>();
        if(!user.getStatus().equals(UserStatus.Valid.id)) return roleAuths;//如果用户状态不正常，就没有任何权限
    
        user.getRoles().forEach(role -> {
            if(role.getOrgId().equals(orgId)) { //获取当前机构项下的角色
                allRoles.getRoles().forEach(r -> {//遍历缓存角色
                    if(role.getRoleId().equals(r.getRoleId()) && r.getStatus().equals(SystemStatus.Normal.id)) {
                        roleAuths.addAll(r.getRoleAuths());
                    }
                });
            }
        });
        user.getGroups().forEach(group -> {
            if(group.getOrgId().equals(orgId)) {//获取当前机构项下的角色组
                allGroups.getGroups().forEach(g -> {//遍历缓存角色组
                    if(group.getGroupId().equals(g.getGroupId()) && g.getStatus().equals(SystemStatus.Normal.id)) {
                        g.getRoles().forEach(r -> {//遍历缓存角色组
                            roleAuths.addAll(r.getRoleAuths());
                        });
                    }
                });
            }
        });
        //转授权角色筛选
        allSubst.getSubsts().forEach(subst -> {
            subst.getRoles().forEach(role -> {
                if(role.getOrgId().equals(orgId)) {
                    allRoles.getRoles().forEach(r -> {//遍历缓存角色
                        if(r.getRoleId().equals(role.getRoleId()) && r.getStatus().equals(SystemStatus.Normal.id)) {
                            roleAuths.addAll(r.getRoleAuths());
                        }
                    });
                }
            });
        });
        return roleAuths;
    }
    
    
    /**
     * 根据所有菜单和权限，获取当前用户的菜单
     * @param roleAuths
     * @param allMenus
     * @return 菜单列表
     */
    private List<Menu> getUserMenus(List<RoleAuth> roleAuths, List<Menu> allMenus) {
        List<Menu> resultMenus = new ArrayList<>();
        for (Menu menu : allMenus) {
            List<Menu> childrenMenus = getUserMenus(roleAuths, menu.getChildren());
            //判断是否有该菜单权限
            if(checkUserMenuAuth(menu.getMenuId(), roleAuths) || !CollectionUtils.isEmpty(childrenMenus)) {
                Menu resultMenu = new Menu();
                resultMenu.setMenuId(menu.getMenuId());
                resultMenu.setName(menu.getName());
                resultMenu.setSortNo(menu.getSortNo());
                resultMenu.setIcon(menu.getIcon());
                resultMenu.setUrl(menu.getUrl());
                resultMenu.setUrlParam(menu.getUrlParam());
                resultMenu.setParentId(menu.getParentId());
                resultMenu.setMenuAuth(menu.getMenuAuth());
                resultMenu.setStatus(menu.getStatus());
                resultMenu.setChildren(childrenMenus);
                resultMenus.add(resultMenu);
            }
        }
        return resultMenus;
    }
    
    /**
     * 检查是否有指定菜单编号的菜单
     * @param menuId 菜单编号
     * @param roleAuths 用户的权限信息
     * @return 是否有权限
     */
    private boolean checkUserMenuAuth(String menuId, List<RoleAuth> roleAuths) {
        for(RoleAuth ra : roleAuths) {
            if(ra.getAuthType().equals(AuthType.MENU.id) && ra.getStatus().equals(SystemStatus.Normal.id) && menuId.equals(ra.getAuthNo())) {
                return true;//有找到就是有权限
            }
        }
        return false;//没有找到就是没有权限
    }
    
    
    @Override
    public boolean checkUserAuth(String url, String userId, String orgId, UserService userService, RoleService roleService, GroupService groupService, MenuService menuService) {
        if(StringUtils.isEmpty(url)) return false;//url为空没有权限
        
        if (Stream.of(allowedUrl.split("#")[0].split(",")).anyMatch(uri -> url.matches(uri))) {// 有些url总是能调用，如果匹配就返回true
            return true;
        }
        
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, orgId);
        if (orgInfo.getStatus().equals(SystemStatus.Locked.id)) { // 如果机构状态为停用，则无权限访问数据
            return false;
        }
        
        //获取所有角色
        RoleAllQueryRsp allRoles = roleService.roleAllQuery(new RoleAllQueryReq());
        //获取所有角色组
        GroupAllQueryRsp allGroups= groupService.groupAllQuery();
        //获取用户转授权信息
        UserSubstQueryReq substReq = new UserSubstQueryReq();
        ArrayList<String> status = new ArrayList<String>();
        status.add(UserLeaveStatus.Able.id);
        substReq.setStatus(status);
        substReq.setSubstUserId(userId);
        substReq.setBegin(0);
        substReq.setPageSize(Integer.MAX_VALUE);
        UserSubstQueryRsp allSubst= userService.getUserSubsts(substReq);
        
        //获取当前用户信息
        UserQueryReq userQueryReq = new UserQueryReq();
        userQueryReq.setUserId(userId);
        UserQueryRsp userQueryRsp = userService.getUsers(userQueryReq, false);
        
        //获取当前用户的所有权限
        List<RoleAuth> roleAuths = getUserRoleAuths(orgId, userQueryRsp.getUsers().get(0), allRoles, allGroups, allSubst);
        
        if(CollectionUtils.isEmpty(roleAuths))
            return false;//用户没有授权信息就返回没有权限
        //先查找授权信息中有没有url授权
        for(RoleAuth ra : roleAuths) {
            if(ra.getAuthType().equals(AuthType.URL.id) && ra.getStatus().equals(SystemStatus.Normal.id) && url.matches(ra.getAuthNo())) {
                return true;//有找到就是有权限
            }
        }
        
        //获取所有菜单
        MenuAllQueryRsp allMenus = menuService.getAllMenu(LocaleContextHolder.getLocale().toLanguageTag().toLowerCase());
        
        return this.checkUserAuth(url, roleAuths, allMenus.getMenuInfos());
    }
    
    /**
     * 判断url是否有权限
     * @param url
     * @param roleAuths
     * @param allMenus
     * @return 是否有权限
     */
    protected boolean checkUserAuth(String url, List<RoleAuth> roleAuths,List<Menu> allMenus) {
        for (Menu menu : allMenus) {
            //判断是否有该菜单权限
            if(!StringUtils.isEmpty(menu.getMenuAuth())) {
                for(String menuAuth : menu.getMenuAuth().split(",")) {
                    if(url.matches(menuAuth))
                        return true;
                }
            }
            boolean auth = checkUserAuth(url, roleAuths, menu.getChildren());
            if(auth)
                return auth;
        }
        return false;
    }

    @Override
    @CacheEvict(value=KEY_USERP5D, key="'"+KEY_USERP5D+"'+ #req.getUserId()")
    public void userResetPasswd(UserResetPasswdReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        bomanager.updateObjectBySql(UserInfo.class, "password=null,passwordTime=:passwordTime", "userId=:userId", "userId", req.getUserId(), "passwordTime", DateHelper.getBusinessDateLongTime());
        bomanager.updateDB();
    }
    
    @Override
    public void multiUserResetPasswd(UserService userService,MultiUserResetPasswdReq req) {
        req.getUserIds().forEach(r -> {
            UserResetPasswdReq request = new UserResetPasswdReq();
            request.setUserId(r);
            userService.userResetPasswd(request);
        });
    }

    @Override
    @CacheEvict(value=KEY_USERP5D, key="'"+KEY_USERP5D+"'+ #req.getUserId()")
    public void userSetPasswd(UserSetPasswdReq req) {
        if (StringUtils.isEmpty(req.getForgetFlag()) && StringUtils.isEmpty(req.getOldPassword())) {
            throw new ALSException("900962");
        }
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserInfo ui = bomanager.keyLoadBusinessObject(UserInfo.class, req.getUserId());
        if(ui == null) {
            throw new ALSException("980012");
        }
        if((StringUtils.isEmpty(ui.getPassword()) //忘记密码或者重置密码时可以修改密码
                || (!StringUtils.isEmpty(req.getOldPassword()) && req.getOldPassword().equals(ui.getPassword())) )
                || req.getForgetFlag()) {
            ui.setPassword(req.getNewPassword());
            ui.setPasswordTime(DateHelper.getBusinessDateLongTime());//最近一次修改密码时间
            bomanager.updateBusinessObject(ui);
        }else {
            throw new ALSException("980002");
        }
        bomanager.updateDB();
    }

    @Override
    public void userLeave(UserLeaveReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserInfo ui = bomanager.keyLoadBusinessObject(UserInfo.class, GlobalShareContextHolder.getUserId());
        if(ui == null) {//数据库不存在该用户
            throw new ALSException("900920");//没有用户
        }else {
            UserLeave ul = new UserLeave();
            if (StringUtils.isEmpty(req.getLeaveId())) {
                ul.generateKey();
            }else {
                ul.setLeaveId(req.getLeaveId());
            }
            ul.setUserId(GlobalShareContextHolder.getUserId());
            ul.setLeaveReason(req.getLeaveReason());
            ul.setLeaveType(req.getLeaveType());
            ul.setBeginTime(req.getBeginTime());
            ul.setEndTime(req.getEndTime());
            ul.setLeaveDays(Math.abs(DateHelper.getDays(req.getBeginTime(), req.getEndTime()) + 1));
            ul.setStatus(UserLeaveStatus.ToBeAble.id);
            bomanager.updateBusinessObject(ul);
        }
        bomanager.updateDB();
    }

    @Override
    public void userCancelLeave(UserCancelLeaveReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserLeave ul = bomanager.keyLoadBusinessObject(UserLeave.class, req.getLeaveId());
        if(ul == null)
            throw new ALSException("900921");//没有请假信息
        if (ul.getStatus().equals(UserLeaveStatus.ToBeAble.id)) {
            bomanager.deleteBusinessObject(ul);
        }else {
            throw new ALSException("900933");
        }
        bomanager.updateDB();
    }

    @Override
    public UserLeaveQueryRsp getUserLeaves(UserLeaveQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserLeaveQueryRsp rsp = new UserLeaveQueryRsp();
        rsp.setLeaves(new ArrayList<Leave>());
        
        BusinessObjectAggregate<UserLeave> uls = null;
        
        String[] leaveType = null;
        String[] status = null;
        
        if (!CollectionUtils.isEmpty(req.getLeaveType())) {
            leaveType = req.getLeaveType().stream().map(s -> s).toArray(String[]::new);
        }
        if (!CollectionUtils.isEmpty(req.getStatus())) {
            status = req.getStatus().stream().map(s -> s).toArray(String[]::new);
        }
        if (StringUtils.isEmpty(req.getBeginTime()) || StringUtils.isEmpty(req.getEndTime())) {
            if(CollectionUtils.isEmpty(req.getStatus())) {
                if (CollectionUtils.isEmpty(req.getLeaveType())) {
                    uls = bomanager.loadBusinessObjects(UserLeave.class, req.getBegin(), req.getPageSize(), "userId=:userId", "userId", req.getUserId());
                }else {
                    uls = bomanager.loadBusinessObjects(UserLeave.class, req.getBegin(), req.getPageSize(), "userId=:userId and leaveType in ( :leaveType )", "userId", req.getUserId(),"leaveType",leaveType);
                }
            } else {
                if (CollectionUtils.isEmpty(req.getLeaveType())) {
                    uls = bomanager.loadBusinessObjects(UserLeave.class, req.getBegin(), req.getPageSize(), "userId=:userId and status in ( :status )", "userId", req.getUserId(), "status",status);
                }else {
                    uls = bomanager.loadBusinessObjects(UserLeave.class, req.getBegin(), req.getPageSize(), "userId=:userId and status in ( :status ) and leaveType in ( :leaveType )", "userId", req.getUserId(), "status", status,"leaveType",leaveType);
                }
            }
        } else {
            if (CollectionUtils.isEmpty(req.getLeaveType())) {
                uls = bomanager.loadBusinessObjects(UserLeave.class, req.getBegin(), req.getPageSize(), "userId = :userId and beginTime between :reqBeginTime and :reqEndTime ",
                        "userId", req.getUserId(),"reqBeginTime",req.getBeginTime(),"reqEndTime",req.getEndTime());
            }else {
                uls = bomanager.loadBusinessObjects(UserLeave.class, req.getBegin(), req.getPageSize(), "userId = :userId and beginTime between :reqBeginTime and :reqEndTime and leaveType in ( :leaveType )",
                        "userId", req.getUserId(),"reqBeginTime",req.getBeginTime(),"reqEndTime",req.getEndTime(),"leaveType",leaveType);
            }
        }
        rsp.setTotalCount(uls.getAggregate("count(userId) as cnt").getInt("cnt"));
        
        for(UserLeave ul : uls.getBusinessObjects()) {
            Leave l = new Leave();
            l.setLeaveId(ul.getLeaveId());
            l.setLeaveReason(ul.getLeaveReason());
            l.setLeaveType(ul.getLeaveType());
            l.setBeginTime(ul.getBeginTime());
            l.setEndTime(ul.getEndTime());
            l.setStatus(ul.getStatus());
            rsp.getLeaves().add(l);
        }
        return rsp;
    }

    @Override
    public void userChangeLeave(ChangeUserLeaveReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<UserLeave> leaves = bomanager.loadBusinessObjects(UserLeave.class, "leaveId = :leaveId","leaveId",req.getLeaveId());
        leaves.get(0).setEndTime(req.getEndTime());
        leaves.get(0).setStatus(req.getStatus());
        bomanager.updateBusinessObject(leaves.get(0));
        bomanager.updateDB();
    }
    
    /**
     * 定时轮训用户请假信息表，将过期的请假信息设置成无效
     */
    @Scheduled(cron="0 0/1 * * * ?")
    @Transactional
    public void scheduleUserLeave() {
        if(log.isDebugEnabled())
            log.debug("定时轮训用户请假信息表，将过期的请假信息设置成无效，开始……");
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<UserLeave> uls = bomanager.loadBusinessObjects(UserLeave.class, 0, 10, "status = :status and endTime <= :businessTime", "status", UserLeaveStatus.Able.id, "businessTime", DateHelper.getBusinessDateShortTime()).getBusinessObjects();
        for(UserLeave ul: uls) {
            bomanager.updateObjectBySql(UserLeave.class, "status = :newStatus", "leaveId=:leaveId and status = :oldStatus", "newStatus", UserLeaveStatus.Disabled.id, "leaveId", ul.getLeaveId(), "oldStatus", ul.getStatus());
        }
        if(log.isDebugEnabled())
            log.debug("定时轮训用户请假信息表，将过期的请假信息设置成无效，完成");
    }
    
    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="'"+KEY_FRE_SUBST+"' + #req.getSubstUserId()")}
    )
    public void userSubst(UserSubstReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserInfo ui = bomanager.keyLoadBusinessObject(UserInfo.class, GlobalShareContextHolder.getUserId());
        if(ui == null) {//数据库不存在该用户
            throw new ALSException("900920");//没有用户
        }else {
            SubstInfo si = new SubstInfo();
            if (StringUtils.isEmpty(req.getSubstId())) {
                si.generateKey();
            }else {
                si.setSubstId(req.getSubstId());
            }
            si.setUserId(GlobalShareContextHolder.getUserId());
            si.setSubstUserId(req.getSubstUserId());
            si.setBeginTime(LocalDateTime.parse(req.getBeginTime(), DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format)));
            si.setEndTime(LocalDateTime.parse(req.getEndTime(), DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format)));
            si.setSubReason(req.getSubReason());
            si.setStatus(req.getStatus());
            bomanager.updateBusinessObject(si);
            substRole(bomanager,si.getSubstId(), si.getUserId(),req.getRoles());
        }
        bomanager.updateDB();
    }
    
    /**
     * 用户权限转移角色操作
     * @param bomanager 本方法内部不做updateDB
     * @param substId 转授权信息
     * @param userId 用户信息
     * @param roles 用户关联角色
     * @return 是否变更过权限
     */
    private boolean substRole(BusinessObjectManager bomanager, String substId, String userId, List<Role> roles) {
        if(StringUtils.isEmpty(substId) 
                || CollectionUtils.isEmpty(roles)) return false;//允许新增用户转授权、更新用户不设置角色
        List<SubstRole> srs = bomanager.loadBusinessObjects(SubstRole.class, "substId=:substId", 
                                                                             "substId", substId);
        boolean flag = false; //权限是否有过变更
        //先区分数据库存在，本次提交的记录中存在或不存在的
        for(SubstRole sr : srs) {
            boolean exist = false;
            for(Role role : roles) {
                if(role.getRoleId().equals(sr.getRoleId()) && role.getOrgId().equals(sr.getOrgId())) {
                    exist = true;
                    break;
                }
            }
            
            if(!exist) {
                flag = true;
                bomanager.deleteBusinessObject(sr);//本次没有传入的角色，全部删除
            }
        }
        
        //再区分本次提交记录存在的，但是数据库记录不存在的
        for(Role role : roles) {
            boolean exist = false;
            for(SubstRole sr : srs) {
                if(role.getRoleId().equals(sr.getRoleId()) && role.getOrgId().equals(sr.getOrgId())) {
                    exist = true;//前面已处理这种情况，这里就不再处理了
                }
            }
            
            if(!exist) {
                UserRole ur = bomanager.loadBusinessObject(UserRole.class, "userId", userId, "roleId", role.getRoleId(), "orgId", role.getOrgId());
                if(ur == null) //如果用户没有这个角色就跳过
                    continue;
                flag = true;
                SubstRole sr = new SubstRole();
                sr.setSubstId(substId);
                sr.setRoleId(role.getRoleId());
                sr.setOrgId(role.getOrgId());
                bomanager.updateBusinessObject(sr);
            }
        }
        if(log.isInfoEnabled() && flag) {
            log.info("用户："+substId+"的角色从："+srs.toString()+" 变更为："+roles.toString());
        }
        return flag;
    }


    public void userCancelSubst(UserCancelSubstReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        SubstInfo si = bomanager.keyLoadBusinessObject(SubstInfo.class, req.getSubstId());
        if(si == null)
            throw new ALSException("900922");//没有转授权信息
        si.setStatus(Status.Invalid.id);
        bomanager.updateBusinessObject(si);
        bomanager.updateDB();
        
        SpringHelper.getBean(UserServiceImpl.class).clearUserSubst(si.getSubstUserId());//清理缓存
    }
    
    /**
     * 清理内部缓存
     * @param substUserId
     */
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="'"+KEY_FRE_SUBST+"'+ #substUserId")}
    )
    public void clearUserSubst(String substUserId) {
        
    }

    @Override
    @Cacheable(value=KEY_FRE, key= "'"+KEY_FRE_SUBST+"' + #req.getSubstUserId()",
                condition="#req.getSubstUserId() != null && #req.getUserId() == null && #req.getBeginTime() == null && #req.getEndTime() == null && #req.getStatus() != null && #req.getStatus().size() == 1 && #req.getStatus().get(0) == '1'")
    public UserSubstQueryRsp getUserSubsts(UserSubstQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserSubstQueryRsp rsp = new UserSubstQueryRsp();
        BusinessObjectAggregate<SubstInfo> sis = null;
        if (StringUtils.isEmpty(req.getBeginTime()) || StringUtils.isEmpty(req.getEndTime())) {
            if (StringUtils.isEmpty(req.getUserId()) && StringUtils.isEmpty(req.getSubstUserId())) {
                sis = bomanager.loadBusinessObjects(SubstInfo.class, 0, Integer.MAX_VALUE, "1 = 1");
            }else if(CollectionUtils.isEmpty(req.getStatus())) {
                if (!StringUtils.isEmpty(req.getUserId())) {
                    sis = bomanager.loadBusinessObjects(SubstInfo.class, req.getBegin(), req.getPageSize(),
                                                            "userId=:userId", "userId", req.getUserId());
                } else {
                    sis = bomanager.loadBusinessObjects(SubstInfo.class, req.getBegin(), req.getPageSize(),
                                                            "substUserId=:substUserId", "substUserId", req.getSubstUserId());
                }
            } else {
                if (!StringUtils.isEmpty(req.getUserId())) {
                    sis = bomanager.loadBusinessObjects(SubstInfo.class, req.getBegin(), req.getPageSize(),
                                                            "userId=:userId and status in ( :status )", "userId", req.getUserId(),"status",req.getStatus());
                } else {
                    sis = bomanager.loadBusinessObjects(SubstInfo.class, req.getBegin(), req.getPageSize(), 
                                                            "substUserId=:substUserId and status in ( :status )", "substUserId", req.getSubstUserId(), "status", req.getStatus());
                }
            }
        }else {
            if (!CollectionUtils.isEmpty(req.getStatus())) {
                sis = bomanager.loadBusinessObjects(SubstInfo.class, req.getBegin(), req.getPageSize(),
                                                        "userId=:userId and beginTime between :reqBeginTime and :reqEndTime and status in ( :status )",
                                                        "userId", req.getUserId(),"reqBeginTime",getDateTime(req.getBeginTime()),"reqEndTime",getDateTime(req.getEndTime()),"status",req.getStatus());
            }else {
                sis = bomanager.loadBusinessObjects(SubstInfo.class, req.getBegin(), req.getPageSize(),
                                                        "userId=:userId and beginTime between :reqBeginTime and :reqEndTime",
                                                        "userId", req.getUserId(),"reqBeginTime",getDateTime(req.getBeginTime()),"reqEndTime",getDateTime(req.getEndTime()));
            }
        }
        rsp.setTotalCount(sis.getAggregate(" count(substId) as cnt ").getInt("cnt"));
        rsp.setSubsts(new ArrayList<Subst>());
        for(SubstInfo si : sis.getBusinessObjects()) {
            Subst s = new Subst();
            s.setSubstId(si.getSubstId());
            s.setUserId(si.getUserId());
            s.setSubstUserId(si.getSubstUserId());
            s.setBeginTime(DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format).format(si.getBeginTime()));
            s.setEndTime(DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format).format(si.getEndTime()));
            s.setStatus(si.getStatus());
            s.setRoles(new ArrayList<Role>());
            s.setSubReason(si.getSubReason());
            List<SubstRole> srs = bomanager.loadBusinessObjects(SubstRole.class, 0, Integer.MAX_VALUE, "substId=:substId", 
                                                                                 "substId", si.getSubstId()).getBusinessObjects();
            for(SubstRole sr : srs) {
                Role role = new Role();
                role.setRoleId(sr.getRoleId());
                role.setOrgId(sr.getOrgId());
                s.getRoles().add(role);
            }
            rsp.getSubsts().add(s);
        }
        return rsp;
    }
    
    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="'"+KEY_FRE_SUBST+"'+ #req.getSubstUserId()")}
    )
    public void changeUserSubst(UserSubstReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<SubstInfo> substs = bomanager.loadBusinessObjects(SubstInfo.class, "substId = :substId","substId",req.getSubstId());
        substs.get(0).setEndTime(LocalDateTime.parse(req.getEndTime(), DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format)));
        substs.get(0).setStatus(req.getStatus());
        bomanager.updateBusinessObject(substs.get(0));
        bomanager.updateDB();
    }
    
    /**
     * 定时轮训用户转授权信息表，将过期的授权设置成无效
     */
    @Scheduled(cron="0 0/1 * * * ?")
    @Transactional
    public void scheduleUserSubst() {
        if(log.isDebugEnabled())
            log.debug("定时轮训用户转授权信息表，将过期的授权设置成无效，开始……");
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<SubstInfo> sis = bomanager.loadBusinessObjects(SubstInfo.class, 0, 10, "status = :status and endTime <= :businessTime", "status", UserLeaveStatus.Able.id, "businessTime", DateHelper.getBusinessLocalDateTime()).getBusinessObjects();
        for(SubstInfo si: sis) {
            bomanager.updateObjectBySql(SubstInfo.class, "status = :newStatus", "substId=:substId and status = :oldStatus", "newStatus", UserLeaveStatus.Disabled.id, "substId", si.getSubstId(), "oldStatus", si.getStatus());
            SpringHelper.getBean(UserServiceImpl.class).clearUserSubst(si.getSubstUserId());//清理缓存
        }
        if(log.isDebugEnabled())
            log.debug("定时轮训用户转授权信息表，将过期的授权设置成无效，完成");
    }
    
    /**
     * 用户角色操作
     * @param header 请求头
     * @param bomanager 本方法内部不做updateDB
     * @param userId 用户信息
     * @param roles 用户关联角色
     * @return 是否变更过权限
     */
    private boolean role(HttpHeaders header, BusinessObjectManager bomanager, String userId, List<Role> roles) {
        if(StringUtils.isEmpty(userId)) return false;//允许新增用户、更新用户不设置角色
        List<UserRole> urs = bomanager.loadBusinessObjects(UserRole.class, "userId=:userId", 
                                                                             "userId", userId);
        
        boolean flag = false; //权限是否有过变更
        
        //先区分数据库存在，本次提交的记录中存在或不存在的
        for(UserRole ur : urs) {
            boolean exist = false;
            for(Role role : roles) {
                if(role.getRoleId().equals(ur.getRoleId()) && role.getOrgId().equals(ur.getOrgId())) {
                    exist = true;
                    break;
                }
            }
            
            if(!exist) {
                flag = true;
                bomanager.deleteBusinessObject(ur);//本次没有传入的角色，全部删除
            }
        }
        
        List<UserRole> userRoles = new ArrayList<UserRole>();
        
        //再区分本次提交记录存在的，但是数据库记录不存在的
        for(Role role : roles) {
            boolean exist = false;
            for(UserRole ur : urs) {
                if(role.getRoleId().equals(ur.getRoleId()) && role.getOrgId().equals(ur.getOrgId())) {
                    exist = true;//前面已处理这种情况，这里就不再处理了
                }
            }
            
            if(!exist) {
                RoleInfo ri = bomanager.keyLoadBusinessObject(RoleInfo.class, role.getRoleId());
                if(ri == null) //如果没有这个角色就跳过
                    continue;
                OrgInfo oi = bomanager.keyLoadBusinessObject(OrgInfo.class, role.getOrgId());
                if(oi == null) //如果没有这个机构就跳过
                    continue;
                flag = true;
                UserRole ur = new UserRole();
                ur.setUserId(userId);
                ur.setRoleId(role.getRoleId());
                ur.setOrgId(role.getOrgId());
                userRoles.add(ur);
                bomanager.updateBusinessObject(ur);
            }
        }
        
        String describe = "用户："+userId+"的角色从："+urs.toString()+" 变更为："+userRoles.toString();
        
        if(log.isInfoEnabled() && flag) {
            log.info(describe);
            UserEventHelper.setUserEvent(UserEventType.ChangeRole.id,describe,header);
        }
        
        return flag;
    }
    
    
    /**
     * 用户角色组操作
     * @param header 请求头
     * @param bomanager 本方法内部不做updateDB
     * @param userId 用户信息
     * @param groups 用户关联角色组
     * @return 是否变更过权限
     */
    private boolean group(HttpHeaders header, BusinessObjectManager bomanager, String userId, List<Group> groups) {
        if(StringUtils.isEmpty(userId)) return false;//允许新增用户、更新用户不设置角色
        List<UserGroup> ugs = bomanager.loadBusinessObjects(UserGroup.class, "userId=:userId", 
                                                                             "userId", userId);
        
        boolean flag = false; //权限是否有过变更
        
        //先区分数据库存在，本次提交的记录中存在或不存在的
        for(UserGroup ug : ugs) {
            boolean exist = false;
            for(Group group : groups) {
                if(group.getGroupId().equals(ug.getGroupId()) && group.getOrgId().equals(ug.getOrgId())) {
                    exist = true;
                    break;
                }
            }
            
            if(!exist) {
                flag = true;
                bomanager.deleteBusinessObject(ug);//本次没有传入的角色，全部删除
            }
        }
        
        List<UserGroup> userGroups = new ArrayList<>();
        
        //再区分本次提交记录存在的，但是数据库记录不存在的
        for(Group group : groups) {
            boolean exist = false;
            for(UserGroup ug : ugs) {
                if(group.getGroupId().equals(ug.getGroupId()) && group.getOrgId().equals(ug.getOrgId())) {
                    exist = true;//前面已处理这种情况，这里就不再处理了
                }
            }
            
            if(!exist) {
                GroupInfo gi = bomanager.keyLoadBusinessObject(GroupInfo.class, group.getGroupId());
                if(gi == null) //如果没有这个角色就跳过
                    continue;
                OrgInfo oi = bomanager.keyLoadBusinessObject(OrgInfo.class, group.getOrgId());
                if(oi == null) //如果没有这个机构就跳过
                    continue;
                
                flag = true;
                UserGroup ug = new UserGroup();
                ug.setUserId(userId);
                ug.setGroupId(group.getGroupId());
                ug.setOrgId(group.getOrgId());
                userGroups.add(ug);
                bomanager.updateBusinessObject(ug);
            }
        }
        
        String describe = "用户："+userId+"的组从："+ugs.toString()+" 变更为："+userGroups.toString();
        UserEventHelper.setUserEvent(UserEventType.ChangeGroup.id, describe, header);
        
        if(log.isInfoEnabled() && flag) {
            log.info(describe);
        }
        
        return flag;
    }
    

    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="'"+KEY_FRE+"'+ #req.getUserId()"),
            @CacheEvict(value=KEY_USERP5D, key="'"+KEY_USERP5D+"' + #req.getUserId()")}
    )
    public void toggleUserStatus(ToggleUserStatusReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserInfo userInfo = bomanager.keyLoadBusinessObject(UserInfo.class, req.getUserId());
        if (userInfo.getStatus().equals(UserStatus.Invalid.id) || userInfo.getStatus().equals(UserStatus.BlockUp.id)) {
            userInfo.setErrorTime(0);
            userInfo.setStatus(UserStatus.Valid.id);
        } else if(userInfo.getStatus().equals(UserStatus.Valid.id)){
            userInfo.setStatus(UserStatus.Invalid.id);
        }
        bomanager.updateBusinessObject(userInfo);
        bomanager.updateDB();
    }

    @Override
    public void multiToggleUserStatus(UserService userService,MultiToggleUserStatusReq req) {
        req.getUserIds().forEach(r -> {
            ToggleUserStatusReq request = new ToggleUserStatusReq();
            request.setUserId(r);
            userService.toggleUserStatus(request);//循环调接口 清spring cache
        });
    }
    
    @Override
    public boolean checkFileSize(long size,String fileSize) {
        double inputFileSize = 0d;
        double defaultSize = Double.valueOf(fileSize.replaceAll("[A-Za-z]", "")).doubleValue();
        if (fileSize.toLowerCase(Locale.ENGLISH).endsWith("kb")) {
            inputFileSize = size / 1024;
        }else if (fileSize.toLowerCase(Locale.ENGLISH).endsWith("mb")) {
            inputFileSize = size / 1048576;
        }else if (fileSize.toLowerCase(Locale.ENGLISH).endsWith("gb")) {
            inputFileSize = size / 1073741824;
        }else {
            throw new ALSException("900930",fileSize);
        }
        
        return defaultSize > inputFileSize;
    }
    
    /**
     * 时间格式化成时间对象
     * @param time
     * @return LocalDateTime
     */
    private LocalDateTime getDateTime(String time) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format));
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="'"+KEY_FRE+"'+ #req.getUserId()"),
            @CacheEvict(value=KEY_USERP5D, key="'"+KEY_USERP5D+"' + #req.getUserId()")}
    )
    public void setDefaultOrg(SetDefaultOrgReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<UserBelong> userBelongs = bomanager.loadBusinessObjects(UserBelong.class, 0, Integer.MAX_VALUE, "userId = :userId","userId",req.getUserId()).getBusinessObjects();
        boolean flag = false;
        for (UserBelong userBelong : userBelongs) {
            if (userBelong.getOrgId().equals(req.getOrgId())) {
                userBelong.setDefaultFlag(YesNo.Yes.id);
                flag = true;
            } else {
                userBelong.setDefaultFlag(YesNo.No.id);
            }
        }
        bomanager.updateBusinessObjects(userBelongs);
        if(!flag){
            throw new ALSException("900940");
        }
        bomanager.updateDB();
    }

    @Override
    public void getHeadPortrait(HttpServletRequest request, HttpServletResponse response) {
         BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        ResponseMessage<Object> hrb = new ResponseMessage<Object>();
        String userId = "";
        String uuid = "";
        if (!StringUtils.isEmpty(userId = request.getParameter("userId"))) {
            userId = request.getParameter("userId");
        } else  if (!StringUtils.isEmpty(userId = request.getParameter("uuid"))) {
            userId = "";
        }else {
            userId = GlobalShareContextHolder.getUserId();
        }
        if (!StringUtils.isEmpty(userId)) {
            UserInfo userInfo = bomanager.loadBusinessObject(UserInfo.class, userId);
            if (userInfo == null) {
                return;
            }
            if (!StringUtils.isEmpty(userInfo.getHeadPortrait())) {
                uuid = userInfo.getHeadPortrait();
            }
        }else {
            userId = "storage";
            uuid = request.getParameter("uuid");
        }
        String folderName = headPortraitSavePath+ File.separator + userId + File.separator + uuid + File.separator;
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder = new File(defaultHeadPortraitPath);
            if (!folder.exists() || folder.listFiles() == null || folder.listFiles().length == 0) {
                if (log.isWarnEnabled()) {
                    log.warn("The headportrait" + defaultHeadPortraitPath + " dose not init!");
                }
                throw new ALSException("900942");
            }
        }
        byte[] b = new byte[1024];
        try (FileInputStream fis = new FileInputStream(folder.listFiles()[0]);OutputStream os = response.getOutputStream();BufferedInputStream bis = new BufferedInputStream(fis);){
            if (folder != null) {
                if (folder.exists()) {
                    int i = 0;
                    while ((i= bis.read(b)) != -1) {
                        os.write(b, 0, i);
                    }
                }
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("头像下载出错：",e);
            }
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            try {
                response.getWriter().write(JSON.toJSONString(hrb));
            } catch (IOException e1) {
              //异常不作处理
            }
        }
        response.setContentType("image/png");
        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.OK.value());
        InputStream bufferIn = new ByteArrayInputStream(b);
        try {
            ImageIO.write(ImageIO.read(bufferIn),"png",response.getOutputStream());
        } catch (Exception e) {
            //不处理异常
        }
    }

    @Override
    public ResponseMessage<Object> updateHeadportrait(HttpServletRequest request,
            HttpServletResponse response) {
        ResponseMessage<Object> hrb = new ResponseMessage<Object>();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserInfo user = null;
        MultipartFile file = null;
        try {
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getServletContext());
            if (resolver.isMultipart(request) && request.getMethod().equals(HttpMethod.POST.toString())) { //Post  Multipart类型的请求
                MultipartHttpServletRequest fileRequest = (MultipartHttpServletRequest) request;
                file =fileRequest.getFiles("file").get(0);
            }
//            MultipartFile file = ((MultipartHttpServletRequest) request).getFiles("file").get(0);
            String uuid = LocalDateTime.now().getNano() + UUID.randomUUID().toString().replaceAll("-", "");
            String userId = request.getParameter("userId");
            if (StringUtils.isEmpty(userId) || userId.equals("undefined")) {//暂存在default目录
                userId = "storage";
            }
            if (file == null) {
                throw new ALSException("900936");
            }
            String prefix = headPortraitSavePath + File.separator + userId + File.separator + uuid + File.separator;
            String filePath = prefix + CleanPathUtil.pathCharWhite(file.getOriginalFilename());
            File image = new File(filePath);
            if (!image.exists()) {
                image.getParentFile().mkdirs();//创建父级文件夹
            }
            file.transferTo(image);
            if (!this.checkFileSize(file.getSize(),this.fileSize)) 
                throw new ALSException("900929",this.fileSize);
            if (userId.equals("storage")) {
//                hrb.setMessage("/asms/user/getheadportrait?uuid="+uuid);
                hrb.setMessage(uuid);
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.setStatus(HttpStatus.OK.value());
            }else {
                user = bomanager.keyLoadBusinessObject(UserInfo.class, userId);
                user.setHeadPortrait(uuid);
                hrb.setMessage("/asms/user/getheadportrait?userId="+userId);
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.setStatus(HttpStatus.OK.value());
                bomanager.updateBusinessObject(user);
                bomanager.updateDB();
            }
        } catch (IOException e) {
            throw new ALSException("900931", e);
        }
        return hrb;
    }

    @Override
    public UserBelongQueryRsp getUserBelong(UserBelongQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserBelongQueryRsp rsp = new UserBelongQueryRsp();
        rsp.setUserBelongs(new ArrayList<>());
        
        List<UserBelong> userBelongs = bomanager.loadBusinessObjects(UserBelong.class, "userId = :userId and migrationStatus = :status order by defaultFlag desc ", "userId", req.getUserId(), "status", MigrationStatus.Normal.id);;
        
        for (UserBelong userBelong : userBelongs) {
            this.setUserBelongRsp(rsp.getUserBelongs(), bomanager, userBelong);
        }
        return rsp;
    }

    @Override
    public UserQueryByOrgRsp userQueryByOrg(UserQueryByOrgReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        String[] searchAttributes = {"userId","userName"};//查询条件
        
        if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())
                && !Stream.of(searchAttributes).anyMatch(searchAttribute -> searchAttribute.equalsIgnoreCase(req.getSearchAttribute()))) {//验证查询条件
            throw new ALSException("900965");
        }

            
        BusinessObjectAggregate<UserInfo> uis;
        if(StringUtils.isEmpty(req.getRoleId())) {//根据传入机构查询用户
            if(StringUtils.isEmpty(req.getStatus())) {//状态为空查所有
                if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())) {
                    uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                            " userId in ( select userId from UserBelong where orgId = :orgId and migrationStatus = :migrationStatus) and "+req.getSearchAttribute()+" like :searchContent ",
                            "orgId", req.getOrgId(), 
                            "migrationStatus", MigrationStatus.Normal.id,
                            "searchContent", "%"+req.getSearchContent()+"%");
                }else {
                    uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                            " userId in ( select userId from UserBelong where orgId = :orgId and migrationStatus = :migrationStatus)",
                            "orgId", req.getOrgId(), 
                            "migrationStatus", MigrationStatus.Normal.id);
                }
            }else if(UserStatus.Valid.id.equals(req.getStatus())){//状态正常过滤掉请假信息
                if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())) {
                    uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                            " userId in ( select userId from UserBelong where orgId = :orgId and migrationStatus = :migrationStatus)"+ 
                            " and status = :status and not exists(select userId from UserLeave where userId = UserInfo.userId and status = :leaveStatus)"+
                            " and "+req.getSearchAttribute()+" like :searchContent ",
                            "orgId", req.getOrgId(), 
                            "migrationStatus", MigrationStatus.Normal.id,
                            "status", req.getStatus(),
                            "leaveStatus", UserLeaveStatus.Able.id,
                            "searchContent", "%"+req.getSearchContent()+"%");
                }else {
                    uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                            " userId in ( select userId from UserBelong where orgId = :orgId and migrationStatus = :migrationStatus)"+ 
                            " and status = :status and not exists(select userId from UserLeave where userId = UserInfo.userId and status = :leaveStatus)",
                            "orgId", req.getOrgId(), 
                            "migrationStatus", MigrationStatus.Normal.id,
                            "status", req.getStatus(),
                            "leaveStatus", UserLeaveStatus.Able.id);
                }
                
            }else {//其他状态直接取UserInfo状态
                if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())) {
                    uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                            " userId in ( select userId from UserBelong where orgId = :orgId and migrationStatus = :migrationStatus)"+ 
                            " and status = :status and "+req.getSearchAttribute()+" like :searchContent ",
                            "orgId", req.getOrgId(), 
                            "migrationStatus", MigrationStatus.Normal.id,
                            "status", req.getStatus(),
                            "searchContent", "%"+req.getSearchContent()+"%");
                }else {
                    uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                            " userId in ( select userId from UserBelong where orgId = :orgId and migrationStatus = :migrationStatus)"+ 
                            " and status = :status",
                            "orgId", req.getOrgId(), 
                            "migrationStatus", MigrationStatus.Normal.id,
                            "status", req.getStatus());
                }
                
            }
        }else {
            //根据传入机构和角色查询用户
            if(StringUtils.isEmpty(req.getStatus())) {//状态为空查所有
                if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())) {
                    uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                            " userId in ( select userId from UserBelong where orgId = :orgId and migrationStatus = :migrationStatus)" + 
                            " and (userId in (select userId from UserRole where roleId = :roleId and orgId = :orgId) or " +
                            " exists(select roleId from SubstRole where  roleId = :roleId and orgId = :orgId and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))"+
                            " and "+req.getSearchAttribute()+" like :searchContent ",
                            "orgId", req.getOrgId(), 
                            "roleId", req.getRoleId(), 
                            "migrationStatus", MigrationStatus.Normal.id,
                            "substStatus", UserLeaveStatus.Able.id,
                            "searchContent", "%"+req.getSearchContent()+"%");
                }else {
                    uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                            " userId in ( select userId from UserBelong where orgId = :orgId and migrationStatus = :migrationStatus)" + 
                            " and (userId in (select userId from UserRole where roleId = :roleId and orgId = :orgId) or " +
                            " exists(select roleId from SubstRole where  roleId = :roleId and orgId = :orgId and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))",
                            "orgId", req.getOrgId(), 
                            "roleId", req.getRoleId(), 
                            "migrationStatus", MigrationStatus.Normal.id,
                            "substStatus", UserLeaveStatus.Able.id);
                }
            }else if(UserStatus.Valid.id.equals(req.getStatus())){//状态正常过滤掉请假信息
                if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())) {
                    uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                            " userId in ( select userId from UserBelong where orgId = :orgId and migrationStatus = :migrationStatus)" + 
                            " and (userId in (select userId from UserRole where roleId = :roleId and orgId = :orgId) or " +
                            " exists(select roleId from SubstRole where  roleId = :roleId and orgId = :orgId and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))" +
                            " and status = :status and not exists(select userId from UserLeave where userId = UserInfo.userId and status = :leaveStatus)"+
                            " and "+req.getSearchAttribute()+" like :searchContent ",
                            "orgId", req.getOrgId(), 
                            "roleId", req.getRoleId(), 
                            "migrationStatus", MigrationStatus.Normal.id,
                            "substStatus", UserLeaveStatus.Able.id,
                            "status", req.getStatus(),
                            "leaveStatus", UserLeaveStatus.Able.id,
                            "searchContent", "%"+req.getSearchContent()+"%");
                }else {
                    uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId = :orgId and migrationStatus = :migrationStatus)" + 
                                " and (userId in (select userId from UserRole where roleId = :roleId and orgId = :orgId) or " +
                                " exists(select roleId from SubstRole where  roleId = :roleId and orgId = :orgId and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))" +
                                " and status = :status and not exists(select userId from UserLeave where userId = UserInfo.userId and status = :leaveStatus)",
                                "orgId", req.getOrgId(), 
                                "roleId", req.getRoleId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "substStatus", UserLeaveStatus.Able.id,
                                "status", req.getStatus(),
                                "leaveStatus", UserLeaveStatus.Able.id);
                }
            }else {//其他状态直接取UserInfo状态
                if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())) {
                    uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                            " userId in ( select userId from UserBelong where orgId = :orgId and migrationStatus = :migrationStatus)" + 
                            " and (userId in (select userId from UserRole where roleId = :roleId and orgId = :orgId) or " +
                            " exists(select roleId from SubstRole where  roleId = :roleId and orgId = :orgId and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))"+
                            " and status = :status and "+req.getSearchAttribute()+" like :searchContent ",
                            "orgId", req.getOrgId(), 
                            "roleId", req.getRoleId(), 
                            "migrationStatus", MigrationStatus.Normal.id,
                            "substStatus", UserLeaveStatus.Able.id,
                            "status", req.getStatus(),
                            "searchContent", "%"+req.getSearchContent()+"%");
                }else {
                    uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId = :orgId and migrationStatus = :migrationStatus)" + 
                                " and (userId in (select userId from UserRole where roleId = :roleId and orgId = :orgId) or " +
                                " exists(select roleId from SubstRole where  roleId = :roleId and orgId = :orgId and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))"+
                                " and status = :status ",
                                "orgId", req.getOrgId(), 
                                "roleId", req.getRoleId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "substStatus", UserLeaveStatus.Able.id,
                                "status", req.getStatus());
                }
            }
        }
        UserQueryByOrgRsp rsp = new UserQueryByOrgRsp();
        rsp.setTotalCount(uis.getAggregate("count(userId) as cnt").getInt("cnt"));
        rsp.setUsers(new ArrayList<SimpleUser>());
        for(UserInfo ui : uis.getBusinessObjects()) {
            SimpleUser simpleUser = new SimpleUser();
            simpleUser.setUserId(ui.getUserId());
            simpleUser.setUserName(ui.getUserName());
            simpleUser.setOrgId(req.getOrgId());
            simpleUser.setCounter(ui.getCounter());
            simpleUser.setEmail(ui.getEmail());
            simpleUser.setEmpNo(ui.getEmpNo());
            simpleUser.setJobTitle(ui.getJobTitle());
            simpleUser.setLanguage(ui.getLanguage());
            simpleUser.setOfficeTel(ui.getOfficeTel());
            simpleUser.setPhoneNo(ui.getPhoneNo());
            simpleUser.setStatus(ui.getStatus());
            rsp.getUsers().add(simpleUser);
        }
        return rsp;
    }

    @Override
    public UserQueryByParentOrgRsp userQueryByParentOrg(UserQueryByParentOrgReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        String[] searchAttributes = {"userId","userName"};//查询条件
        
        if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())
                && !Stream.of(searchAttributes).anyMatch(searchAttribute -> searchAttribute.equalsIgnoreCase(req.getSearchAttribute()))) {//验证查询条件
            throw new ALSException("900965");
        }

            
        BusinessObjectAggregate<UserInfo> uis;
        if(StringUtils.isEmpty(req.getRoleId())) {//根据传入机构查询用户
            if(StringUtils.isEmpty(req.getStatus())) {//状态为空查所有
                if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())) {
                    if(YesNo.Yes.id.equals(req.getInclude())) {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId and migrationStatus = :migrationStatus) and "+req.getSearchAttribute()+" like :searchContent ",
                                "orgId", "%"+req.getOrgId()+"%", 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "searchContent", "%"+req.getSearchContent()+"%");
                    }else {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus) and "+req.getSearchAttribute()+" like :searchContent ",
                                "orgId1", "%"+req.getOrgId()+"%", 
                                "orgId2", req.getOrgId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "searchContent", "%"+req.getSearchContent()+"%");
                    }
                }else {
                    if(YesNo.Yes.id.equals(req.getInclude())) {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId and migrationStatus = :migrationStatus)",
                                "orgId", "%"+req.getOrgId()+"%", 
                                "migrationStatus", MigrationStatus.Normal.id);
                    }else {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus)",
                                "orgId1", "%"+req.getOrgId()+"%", 
                                "orgId2", req.getOrgId(), 
                                "migrationStatus", MigrationStatus.Normal.id);
                    }
                }
            }else if(UserStatus.Valid.id.equals(req.getStatus())){//状态正常过滤掉请假信息
                if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())) {
                    if(YesNo.Yes.id.equals(req.getInclude())) {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId and migrationStatus = :migrationStatus)"+ 
                                " and status = :status and not exists(select userId from UserLeave where userId = UserInfo.userId and status = :leaveStatus)"+
                                " and "+req.getSearchAttribute()+" like :searchContent ",
                                "orgId", "%"+req.getOrgId()+"%", 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "status", req.getStatus(),
                                "leaveStatus", UserLeaveStatus.Able.id,
                                "searchContent", "%"+req.getSearchContent()+"%");
                    }else {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus)"+ 
                                " and status = :status and not exists(select userId from UserLeave where userId = UserInfo.userId and status = :leaveStatus)"+
                                " and "+req.getSearchAttribute()+" like :searchContent ",
                                "orgId1", "%"+req.getOrgId()+"%", 
                                "orgId2", req.getOrgId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "status", req.getStatus(),
                                "leaveStatus", UserLeaveStatus.Able.id,
                                "searchContent", "%"+req.getSearchContent()+"%");
                    }
                }else {
                    if(YesNo.Yes.id.equals(req.getInclude())) {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId and migrationStatus = :migrationStatus)"+ 
                                " and status = :status and not exists(select userId from UserLeave where userId = UserInfo.userId and status = :leaveStatus)",
                                "orgId", "%"+req.getOrgId()+"%", 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "status", req.getStatus(),
                                "leaveStatus", UserLeaveStatus.Able.id);
                    }else {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus)"+ 
                                " and status = :status and not exists(select userId from UserLeave where userId = UserInfo.userId and status = :leaveStatus)",
                                "orgId1", "%"+req.getOrgId()+"%", 
                                "orgId2", req.getOrgId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "status", req.getStatus(),
                                "leaveStatus", UserLeaveStatus.Able.id);
                    }
                }
                
            }else {//其他状态直接取UserInfo状态
                if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())) {
                    if(YesNo.Yes.id.equals(req.getInclude())) {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId and migrationStatus = :migrationStatus)"+ 
                                " and status = :status and "+req.getSearchAttribute()+" like :searchContent ",
                                "orgId", "%"+req.getOrgId()+"%", 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "status", req.getStatus(),
                                "searchContent", "%"+req.getSearchContent()+"%");
                    }else {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus)"+ 
                                " and status = :status and "+req.getSearchAttribute()+" like :searchContent ",
                                "orgId1", "%"+req.getOrgId()+"%", 
                                "orgId2", req.getOrgId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "status", req.getStatus(),
                                "searchContent", "%"+req.getSearchContent()+"%");
                    }
                }else {
                    if(YesNo.Yes.id.equals(req.getInclude())) {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId and migrationStatus = :migrationStatus)"+ 
                                " and status = :status",
                                "orgId", "%"+req.getOrgId()+"%", 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "status", req.getStatus());
                    }else {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus)"+ 
                                " and status = :status",
                                "orgId1", "%"+req.getOrgId()+"%", 
                                "orgId2", req.getOrgId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "status", req.getStatus());
                    }
                }
                
            }
        }else {
            //根据传入机构和角色查询用户
            if(StringUtils.isEmpty(req.getStatus())) {//状态为空查所有
                if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())) {
                    if(YesNo.Yes.id.equals(req.getInclude())) {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId and migrationStatus = :migrationStatus)" + 
                                " and (userId in (select userId from UserRole where roleId = :roleId and orgId like :orgId) or " +
                                " exists(select roleId from SubstRole where  roleId = :roleId and orgId like :orgId and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))"+
                                " and "+req.getSearchAttribute()+" like :searchContent ",
                                "orgId", "%"+req.getOrgId()+"%", 
                                "roleId", req.getRoleId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "substStatus", UserLeaveStatus.Able.id,
                                "searchContent", "%"+req.getSearchContent()+"%");
                    }else {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus)" + 
                                " and (userId in (select userId from UserRole where roleId = :roleId and orgId like :orgId1 and orgId <> :orgId2) or " +
                                " exists(select roleId from SubstRole where  roleId = :roleId and orgId like :orgId1 and orgId <> :orgId2 and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))"+
                                " and "+req.getSearchAttribute()+" like :searchContent ",
                                "orgId1", "%"+req.getOrgId()+"%", 
                                "orgId2", req.getOrgId(), 
                                "roleId", req.getRoleId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "substStatus", UserLeaveStatus.Able.id,
                                "searchContent", "%"+req.getSearchContent()+"%");
                    }
                    
                }else {
                    if(YesNo.Yes.id.equals(req.getInclude())) {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId and migrationStatus = :migrationStatus)" + 
                                " and (userId in (select userId from UserRole where roleId = :roleId and orgId like :orgId) or " +
                                " exists(select roleId from SubstRole where  roleId = :roleId and orgId like :orgId and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))",
                                "orgId", "%"+req.getOrgId()+"%", 
                                "roleId", req.getRoleId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "substStatus", UserLeaveStatus.Able.id);
                    }else {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus)" + 
                                " and (userId in (select userId from UserRole where roleId = :roleId and orgId like :orgId1 and orgId <> :orgId2) or " +
                                " exists(select roleId from SubstRole where  roleId = :roleId and orgId like :orgId1 and orgId <> :orgId2 and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))",
                                "orgId1", "%"+req.getOrgId()+"%", 
                                "orgId2", req.getOrgId(), 
                                "roleId", req.getRoleId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "substStatus", UserLeaveStatus.Able.id);
                    }
                }
            }else if(UserStatus.Valid.id.equals(req.getStatus())){//状态正常过滤掉请假信息
                if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())) {
                    if(YesNo.Yes.id.equals(req.getInclude())) {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId and migrationStatus = :migrationStatus)" + 
                                " and (userId in (select userId from UserRole where roleId = :roleId and orgId like :orgId) or " +
                                " exists(select roleId from SubstRole where  roleId = :roleId and orgId like :orgId and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))" +
                                " and status = :status and not exists(select userId from UserLeave where userId = UserInfo.userId and status = :leaveStatus)"+
                                " and "+req.getSearchAttribute()+" like :searchContent ",
                                "orgId", "%"+req.getOrgId()+"%", 
                                "roleId", req.getRoleId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "substStatus", UserLeaveStatus.Able.id,
                                "status", req.getStatus(),
                                "leaveStatus", UserLeaveStatus.Able.id,
                                "searchContent", "%"+req.getSearchContent()+"%");
                    }else {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus)" + 
                                " and (userId in (select userId from UserRole where roleId = :roleId and orgId like :orgId1 and orgId <> :orgId2) or " +
                                " exists(select roleId from SubstRole where  roleId = :roleId and orgId like :orgId1 and orgId <> :orgId2 and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))" +
                                " and status = :status and not exists(select userId from UserLeave where userId = UserInfo.userId and status = :leaveStatus)"+
                                " and "+req.getSearchAttribute()+" like :searchContent ",
                                "orgId1", "%"+req.getOrgId()+"%", 
                                "orgId2", req.getOrgId(), 
                                "roleId", req.getRoleId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "substStatus", UserLeaveStatus.Able.id,
                                "status", req.getStatus(),
                                "leaveStatus", UserLeaveStatus.Able.id,
                                "searchContent", "%"+req.getSearchContent()+"%");
                    }
                }else {
                    if(YesNo.Yes.id.equals(req.getInclude())) {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                    " userId in ( select userId from UserBelong where orgId like :orgId and migrationStatus = :migrationStatus)" + 
                                    " and (userId in (select userId from UserRole where roleId = :roleId and orgId like :orgId) or " +
                                    " exists(select roleId from SubstRole where  roleId = :roleId and orgId like :orgId and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))" +
                                    " and status = :status and not exists(select userId from UserLeave where userId = UserInfo.userId and status = :leaveStatus)",
                                    "orgId", "%"+req.getOrgId()+"%", 
                                    "roleId", req.getRoleId(), 
                                    "migrationStatus", MigrationStatus.Normal.id,
                                    "substStatus", UserLeaveStatus.Able.id,
                                    "status", req.getStatus(),
                                    "leaveStatus", UserLeaveStatus.Able.id);
                    }else {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus)" + 
                                " and (userId in (select userId from UserRole where roleId = :roleId and orgId like :orgId1 and orgId <> :orgId2) or " +
                                " exists(select roleId from SubstRole where  roleId = :roleId and orgId like :orgId1 and orgId <> :orgId2 and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))" +
                                " and status = :status and not exists(select userId from UserLeave where userId = UserInfo.userId and status = :leaveStatus)",
                                "orgId1", "%"+req.getOrgId()+"%", 
                                "orgId2", req.getOrgId(), 
                                "roleId", req.getRoleId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "substStatus", UserLeaveStatus.Able.id,
                                "status", req.getStatus(),
                                "leaveStatus", UserLeaveStatus.Able.id);
                    }
                }
            }else {//其他状态直接取UserInfo状态
                if (!StringUtils.isEmpty(req.getSearchAttribute()) && !StringUtils.isEmpty(req.getSearchContent())) {
                    if(YesNo.Yes.id.equals(req.getInclude())) {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId and migrationStatus = :migrationStatus)" + 
                                " and (userId in (select userId from UserRole where roleId = :roleId and orgId like :orgId) or " +
                                " exists(select roleId from SubstRole where  roleId = :roleId and orgId like :orgId and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))"+
                                " and status = :status and "+req.getSearchAttribute()+" like :searchContent ",
                                "orgId", "%"+req.getOrgId()+"%", 
                                "roleId", req.getRoleId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "substStatus", UserLeaveStatus.Able.id,
                                "status", req.getStatus(),
                                "searchContent", "%"+req.getSearchContent()+"%");
                    }else {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus)" + 
                                " and (userId in (select userId from UserRole where roleId = :roleId and orgId like :orgId1 and orgId <> :orgId2 ) or " +
                                " exists(select roleId from SubstRole where  roleId = :roleId and orgId like :orgId1 and orgId <> :orgId2 and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))"+
                                " and status = :status and "+req.getSearchAttribute()+" like :searchContent ",
                                "orgId1", "%"+req.getOrgId()+"%", 
                                "orgId2", req.getOrgId(), 
                                "roleId", req.getRoleId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "substStatus", UserLeaveStatus.Able.id,
                                "status", req.getStatus(),
                                "searchContent", "%"+req.getSearchContent()+"%");
                    }
                }else {
                    if(YesNo.Yes.id.equals(req.getInclude())) {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                    " userId in ( select userId from UserBelong where orgId like :orgId and migrationStatus = :migrationStatus)" + 
                                    " and (userId in (select userId from UserRole where roleId = :roleId and orgId like :orgId) or " +
                                    " exists(select roleId from SubstRole where  roleId = :roleId and orgId like :orgId and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))"+
                                    " and status = :status ",
                                    "orgId", "%"+req.getOrgId()+"%", 
                                    "roleId", req.getRoleId(), 
                                    "migrationStatus", MigrationStatus.Normal.id,
                                    "substStatus", UserLeaveStatus.Able.id,
                                    "status", req.getStatus());
                    }else {
                        uis = bomanager.loadBusinessObjects(UserInfo.class, req.getBegin(), req.getPageSize(),
                                " userId in ( select userId from UserBelong where orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus)" + 
                                " and (userId in (select userId from UserRole where roleId = :roleId and orgId like :orgId1 and orgId <> :orgId2) or " +
                                " exists(select roleId from SubstRole where  roleId = :roleId and orgId like :orgId1 and orgId <> :orgId2 and substId in(select substId from SubstInfo where substUserId=UserInfo.userId and status = :substStatus)))"+
                                " and status = :status ",
                                "orgId1", "%"+req.getOrgId()+"%", 
                                "orgId2", req.getOrgId(), 
                                "roleId", req.getRoleId(), 
                                "migrationStatus", MigrationStatus.Normal.id,
                                "substStatus", UserLeaveStatus.Able.id,
                                "status", req.getStatus());
                    }
                }
            }
        }
        UserQueryByParentOrgRsp rsp = new UserQueryByParentOrgRsp();
        rsp.setTotalCount(uis.getAggregate("count(userId) as cnt").getInt("cnt"));
        rsp.setUsers(new ArrayList<SimpleUser>());
        for(UserInfo ui : uis.getBusinessObjects()) {
            SimpleUser simpleUser = new SimpleUser();
            simpleUser.setUserId(ui.getUserId());
            simpleUser.setUserName(ui.getUserName());
            if(YesNo.Yes.id.equals(req.getInclude())) {
                List<UserBelong> userBelongs = bomanager.loadBusinessObjects(UserBelong.class, "orgId like :orgId and migrationStatus = :migrationStatus and userId=:userId order by defaultFlag desc",
                        "orgId", "%"+req.getOrgId()+"%",
                        "migrationStatus", MigrationStatus.Normal.id,
                        "userId", ui.getUserId());
                simpleUser.setOrgId(userBelongs.get(0).getOrgId());
            }else {
                List<UserBelong> userBelongs = bomanager.loadBusinessObjects(UserBelong.class, "orgId like :orgId1 and orgId <> :orgId2 and migrationStatus = :migrationStatus and userId=:userId order by defaultFlag desc",
                        "orgId1", "%"+req.getOrgId()+"%",
                        "orgId2", req.getOrgId(),
                        "migrationStatus", MigrationStatus.Normal.id,
                        "userId", ui.getUserId());
                simpleUser.setOrgId(userBelongs.get(0).getOrgId());
            }
            
            simpleUser.setCounter(ui.getCounter());
            simpleUser.setEmail(ui.getEmail());
            simpleUser.setEmpNo(ui.getEmpNo());
            simpleUser.setJobTitle(ui.getJobTitle());
            simpleUser.setLanguage(ui.getLanguage());
            simpleUser.setOfficeTel(ui.getOfficeTel());
            simpleUser.setPhoneNo(ui.getPhoneNo());
            simpleUser.setStatus(ui.getStatus());
            rsp.getUsers().add(simpleUser);
        }
        return rsp;
    }
    
}