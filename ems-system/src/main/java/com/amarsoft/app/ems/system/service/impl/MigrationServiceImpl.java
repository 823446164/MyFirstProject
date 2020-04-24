package com.amarsoft.app.ems.system.service.impl;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.aecd.system.constant.DataAuth;
import com.amarsoft.aecd.system.constant.MigrationOptional;
import com.amarsoft.aecd.system.constant.MigrationStatus;
import com.amarsoft.aecd.system.constant.UserEventType;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.system.cs.dto.executemigration.ExecuteMigrationReq;
import com.amarsoft.app.ems.system.cs.dto.migrationcheckquery.MigrationCheckQueryReq;
import com.amarsoft.app.ems.system.cs.dto.migrationcheckquery.MigrationCheckQueryRsp;
import com.amarsoft.app.ems.system.entity.UserBelong;
import com.amarsoft.app.ems.system.entity.UserGroup;
import com.amarsoft.app.ems.system.entity.UserRole;
import com.amarsoft.app.ems.system.entity.UserTeam;
import com.amarsoft.app.ems.system.service.MigrationService;
import com.amarsoft.app.ems.system.util.UserEventHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户迁移服务类
 * 
 * @description 
 *      1.检查调用接口：取注册中心各业务微服务（系统服务通过配置跳过校验），各服务需实现检查删除用户关联机构（/migrationcheck/delete）、
 *                   检查迁移用户关联机构（/migrationcheck/change）；各微服务有各服务的返回值，只要有微服务返回不允许或者有微服务没有
 *                   实现该接口，就不能调执行接口。
 *      2.执行调用接口：逻辑同1，各服务需要实现接口API：删除用户关联机构（/executemigration/delete），变更用户关联机构（/executemigration/change）
 * @author hzhang23
 *
 */
@Slf4j
@Service
@RefreshScope
public class MigrationServiceImpl implements MigrationService {
    @Autowired
    DiscoveryClient discoveryClient;
    
    @Autowired
    LoadBalancerClient loadBalancerClient;
    
    @Value("${global.config.user.migration-check-url}")//迁移检查路径
    String migrationCheckUrl;
    
    @Value("${global.config.user.migration-execute-url}")//执行迁移路径
    String migrationExecuteUrl;
    
    @Value("${global.config.user.migration-pass-server}") //迁移不检查的服务
    String migrationPassServers;
    
    @Value("${global.config.user.migration-check-server}") //迁移检查的服务
    String migrationCheckServers;
    
    @Value("${global.config.user.migration-model}") // check or pass
    String migrationModel;
    
    /**
     * 
     */
    @Override
    public ResponseMessage<MigrationCheckQueryRsp> migrationCheckQuery(String user, String contentType, MigrationCheckQueryReq req) {
//        if(req.getMigrationOption().equals(MigrationOptional.Change.id)) {
//            if (StringUtils.isEmpty(req.getTargetOrgId())) {
//                throw new ALSException("900928", "迁移目标机构不允许为空！");
//            }
//        }
        ResponseMessage<MigrationCheckQueryRsp> rspMsg = new ResponseMessage<MigrationCheckQueryRsp>();
        MigrationCheckQueryRsp rsp = new MigrationCheckQueryRsp();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserBelong userBelong = (UserBelong) bomanager.loadBusinessObjects(UserBelong.class, "userId =:userId and orgId =:orgId","userId",req.getUserId(),"orgId",req.getOrgId()).get(0);
        // 拼装访问各业务系统请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", contentType);
        requestHeaders.add("User-Id",user);
        requestHeaders.add("Accept-Language", LocaleContextHolder.getLocale().toLanguageTag());
        HttpEntity<MigrationCheckQueryReq> requestEntity = new HttpEntity<MigrationCheckQueryReq>(req, requestHeaders);
        
        RestTemplate restTemplete = new RestTemplate();
        StringBuilder warnMsg = new StringBuilder();
        boolean continueFlag = false;//业务系统标识
        boolean migrationModelFlag = migrationModel.equals(MigrationOptional.Pass.id);//check or pass  check为检查迁移服务,pass为不检查的迁移服务
        boolean migrationResult = true;
        for(String serverName: discoveryClient.getServices()) {
            if (userBelong.getMigrationStatus().equals(req.getMigrationOption())) {//如果做过相同处理，为节省时间，跳过处理
                break;
            }
            ServiceInstance si = loadBalancerClient.choose(serverName);
            if (migrationModelFlag) {
                if (!serverName.split("-")[0].equalsIgnoreCase(migrationCheckServers)) 
                    continue;
            } else {
                for (String passServers : migrationPassServers.split(",")) {// 只有业务微服务需要实现迁移接口
                    if (passServers.equalsIgnoreCase(serverName.split("-")[0])) {
                        continueFlag = true;
                        break;
                    }
                }
                if (continueFlag) {// 跳过该服务
                    continueFlag = false;
                    continue;
                }
            }
            String url = "";//拼接访问路径并记录机构状态
            if (req.getMigrationOption().equals(MigrationOptional.Delete.id)) {
                url = "/" + serverName.split("-")[0].toLowerCase() + migrationCheckUrl+"/delete";
            }else if (req.getMigrationOption().equals(MigrationOptional.Change.id)) {
                url = "/" + serverName.split("-")[0].toLowerCase() + migrationCheckUrl+"/change";
            }
            
            URI uri = UriComponentsBuilder.fromUri(si.getUri()).path(url).build(false).toUri();
            try {//调用其他服务的检查接口
                ParameterizedTypeReference<ResponseMessage<MigrationCheckQueryRsp>> ref = new ParameterizedTypeReference<ResponseMessage<MigrationCheckQueryRsp>>() {};
                ResponseEntity<ResponseMessage<MigrationCheckQueryRsp>> response = restTemplete.exchange(uri, HttpMethod.POST, requestEntity,ref);
                if (!response.getStatusCode().equals(HttpStatus.OK)) {
                    migrationResult = false; // 机构迁移不通过
                    throw new ALSException("900928", "服务：" + serverName.split("-")[0] + "不允许该用户" + req.getUserId() + "迁移机构");
                }
            } catch (Exception e) {
                if(log.isErrorEnabled()) {
                    log.error("调用用户迁移业务逻辑失败",e);
                }
                warnMsg.append(si.getServiceId()+",");//收集为实现接口服务
                rsp.setMigrationStatus(MigrationStatus.Normal.id);
            }
            continueFlag = false;//跳过挡板初始化
        }
        if (migrationResult) {
            if (req.getMigrationOption().equals(MigrationOptional.Delete.id)) {
                userBelong.setMigrationStatus(MigrationStatus.Deletable.id);//调用接口不报错 设置为待删除
            } else if (req.getMigrationOption().equals(MigrationOptional.Change.id)) {
                userBelong.setMigrationStatus(MigrationStatus.Modifiable.id);//调用接口不报错 设置为待变更
            } 
            bomanager.updateBusinessObject(userBelong);
            bomanager.updateDB();
        }
        String responseMessage = "有服务:"+ warnMsg.toString()+"未实现用户迁移接口,请联系管理员添加相关接口";
        //更新迁移结果
        rsp.setMigrationStatus(userBelong.getMigrationStatus());
        rspMsg.setMessage(rsp);
        if(log.isWarnEnabled() && warnMsg.length() > 0) {
            rspMsg.setResponseCode("900928");
            rspMsg.setResponseMessage(responseMessage);
            log.warn(responseMessage);
        }
        return rspMsg;
    }

    @Override
    public ResponseMessage<Object> executeMigration(String user, String contentType, ExecuteMigrationReq req,HttpHeaders header) {
        if(req.getMigrationOption().equals(MigrationOptional.Change.id)) {
          if (StringUtils.isEmpty(req.getTargetOrgId())) {
              throw new ALSException("900937");
          }
        }
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<UserBelong> userBelongs = null;
        boolean isChangeOption = req.getMigrationOption().equals(MigrationOptional.Change.id);
        boolean migrationModelFlag = migrationModel.equals(MigrationOptional.Pass.id);
        if(isChangeOption) {//前置条件判断：待删除/待变更的机构可以删除/变更
            if (StringUtils.isEmpty(req.getTargetOrgId()))
                throw new ALSException("900937");
            userBelongs= bomanager.loadBusinessObjects(UserBelong.class, "userId = :userId and orgId = :orgId and migrationStatus = :migrationStatus",
                    "userId",req.getUserId(),"orgId",req.getOrgId(),"migrationStatus",MigrationStatus.Modifiable.id);
            if (CollectionUtils.isEmpty(userBelongs))
                throw new ALSException("900938");
        }else if (req.getMigrationOption().equals(MigrationOptional.Delete.id)) {
            userBelongs= bomanager.loadBusinessObjects(UserBelong.class, "userId = :userId and orgId = :orgId and migrationStatus = :migrationStatus",
                    "userId",req.getUserId(),"orgId",req.getOrgId(),"migrationStatus",MigrationStatus.Deletable.id);
            if (CollectionUtils.isEmpty(userBelongs)) {
                throw new ALSException("900939");
            }
        }
        ResponseMessage<Object> rspMsg = new ResponseMessage<Object>();
        // 拼装访问各业务系统请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", contentType);
        requestHeaders.add("User-Id",user);
        requestHeaders.add("Accept-Language", LocaleContextHolder.getLocale().toLanguageTag());// 获取本地的语言
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(req, requestHeaders);
        
        RestTemplate restTemplete = new RestTemplate();
        StringBuilder warnMsg = new StringBuilder();
        boolean continueFlag = false;//业务系统标识
        boolean migrationResult = true; //执行结果
        for(String serverName: discoveryClient.getServices()) {
            ServiceInstance si = loadBalancerClient.choose(serverName);
            if (migrationModelFlag) {
                if (!serverName.split("-")[0].equalsIgnoreCase(migrationCheckServers))
                    continue;
            } else {
                for (String passServers : migrationPassServers.split(",")) {// 只有业务微服务需要实现迁移接口
                    if (passServers.equalsIgnoreCase(serverName.split("-")[0])) {
                        continueFlag = true;
                        break;
                    }
                }
                if (continueFlag) {// 跳过该服务
                    continueFlag = false;
                    continue;
                }
            }
            String url = "";//拼接访问路径并记录机构状态
            if (req.getMigrationOption().equals(MigrationOptional.Delete.id)) {
                url = "/" + serverName.split("-")[0].toLowerCase() + migrationExecuteUrl+"/delete";
                String log = "";
                UserEventHelper.setUserEvent(UserEventType.OrgMigration_Delete.id, log, header);
            }else if (req.getMigrationOption().equals(MigrationOptional.Change.id)) {
                url = "/" + serverName.split("-")[0].toLowerCase() + migrationExecuteUrl+"/change";
            }
            URI uri = UriComponentsBuilder.fromUri(si.getUri()).path(url).build(false).toUri();
            try {//调用其他服务的检查接口
                ParameterizedTypeReference<ResponseMessage<MigrationCheckQueryRsp>> ref = new ParameterizedTypeReference<ResponseMessage<MigrationCheckQueryRsp>>() {
                };
                ResponseEntity<ResponseMessage<MigrationCheckQueryRsp>> response = restTemplete.exchange(uri, HttpMethod.POST, requestEntity,
                        ref);
                if (!response.getStatusCode().equals(HttpStatus.OK)) {
                    migrationResult = false;
                    throw new ALSException("900928", "服务：" + serverName.split("-")[0] + "不允许该用户" + req.getUserId() + "迁移机构");
                }
            } catch (Exception e) {
                warnMsg.append(si.getServiceId()+",");//收集为实现接口服务
                if (log.isErrorEnabled()) {
                    log.error("机构迁移调用报错",e);
                }
            }
            continueFlag = false;
        }
        String responseMessage = "有服务:"+ warnMsg.toString()+"未实现用户迁移接口,请联系管理员添加相关接口";
        
        if (migrationResult) {//执行成功 修改数据并保存
            if (req.getMigrationOption().equals(MigrationOptional.Delete.id)) {
                bomanager.deleteBusinessObject(userBelongs.get(0));
            } else if (req.getMigrationOption().equals(MigrationOptional.Change.id)) {
                UserBelong ub = new UserBelong();
                ub.setOrgId(req.getTargetOrgId());
                ub.setUserId(req.getUserId());
                ub.setDataAuth(DataAuth.UserData.id);
                ub.setDefaultFlag(YesNo.No.id);
                ub.setOriginOrgId(req.getOrgId());
                ub.setMigrationStatus(MigrationStatus.Normal.id);
                bomanager.updateBusinessObject(ub);
            }
            //变更或机构删除都需要重新配置用户的角色/角色组
            bomanager.deleteObjectBySql(UserBelong.class, "userId = :userId and orgId = :orgId", "userId",req.getUserId(),"orgId",req.getOrgId());
            bomanager.deleteObjectBySql(UserRole.class, "userId = :userId and orgId = :orgId", "userId",req.getUserId(),"orgId",req.getOrgId());
            bomanager.deleteObjectBySql(UserGroup.class, "userId = :userId and orgId = :orgId", "userId",req.getUserId(),"orgId",req.getOrgId());
            bomanager.deleteObjectBySql(UserTeam.class, "userId = :userId ", "userId",req.getUserId());
            bomanager.updateDB();
        }
        
        if(log.isWarnEnabled() && warnMsg.length() > 0) {
            log.warn(responseMessage);
            rspMsg.setResponseCode("900928");
            rspMsg.setResponseMessage(responseMessage);
        }
        return rspMsg;
    }

}
