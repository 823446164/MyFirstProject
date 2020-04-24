package com.amarsoft.app.ems.system.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.aecd.system.constant.DataAuth;
import com.amarsoft.aecd.system.constant.MigrationStatus;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.aecd.system.constant.UserLeaveStatus;
import com.amarsoft.aecd.system.constant.UserStatus;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arem.util.SpringHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectCache;
import com.amarsoft.app.ems.system.cs.client.UserClient;
import com.amarsoft.app.ems.system.cs.dto.groupallquery.Group;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.Role;
import com.amarsoft.app.ems.system.cs.dto.userquery.User;
import com.amarsoft.app.ems.system.cs.dto.userquery.UserBelong;
import com.amarsoft.app.ems.system.cs.dto.userquery.UserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userquery.UserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.userquerybyorg.SimpleUser;
import com.amarsoft.app.ems.system.cs.dto.userquerybyorg.UserQueryByOrgReq;
import com.amarsoft.app.ems.system.cs.dto.usersubstquery.Subst;
import com.amarsoft.app.ems.system.cs.dto.usersubstquery.UserSubstQueryReq;
import com.amarsoft.app.ems.system.cs.dto.usersubstquery.UserSubstQueryRsp;

import lombok.extern.slf4j.Slf4j;


/**
 * 用户工具类
 * 
 * @author hzhang23
 */
@Slf4j
public class UserHelper {
    private static UserClient userClient = SpringHelper.getBean(UserClient.class);
    private static BusinessObjectCache dbcache = new BusinessObjectCache(5000);
    
    /**
     * 清理缓存
     */
    public static void clear()
    {
        dbcache = new BusinessObjectCache(5000);
    }
    
    /**
     * 获取指定用户编号的用户信息
     * @param userId
     * @return 用户信息
     */
    public static User getUser(String userId) {
        if(StringUtils.isEmpty(userId)) throw new ALSException("902020",userId);
        User user = (User) dbcache.getCacheObject(userId);
        if(user == null){
            RequestMessage<UserQueryReq> reqMsg = new RequestMessage<UserQueryReq>();
            UserQueryReq req = new UserQueryReq();
            req.setUserId(userId);
            reqMsg.setMessage(req);
            UserQueryRsp rsp = userClient.userQuery(reqMsg).getBody().getMessage();
            if (rsp.getTotalCount().equals(0)){
                throw new ALSException("902020",userId);
            } else{
                user = rsp.getUsers().get(0);
                dbcache.setCache(userId, user);
            }
        }
        return user;
    }
    
    /**
     * 获取当前操作用户的用户名称
     * @return 用户名称
     */
    public static String getUserName() {
        return getUserName(GlobalShareContextHolder.getUserId());
    }
    
    /**
     * 获取指定用户编号的用户名称
     * @param userId
     * @return 用户名称
     */
    public static String getUserName(String userId) {
        String userName = "";
        try {
            User user = getUser(userId);
            userName = user.getUserName();
        } catch (Exception e) {
            if(log.isWarnEnabled()) {
                log.warn("未找到"+userId+"的用户!");
            }
        }
        
        return userName;
    }
    
    /**
     * 根据当前用户编号获取默认机构编号
     * @return 机构编号
     */
    public static String getDefaultOrgId() {
        return getDefaultOrgId(GlobalShareContextHolder.getUserId());
    }
    
    /**
     * 根据指定的用户编号获取默认机构编号
     * @param userId
     * @return 机构编号
     */
    public static String getDefaultOrgId(String userId) {
        User user = null;
        try {
            user = getUser(userId);
        } catch (Exception e) {
            if(log.isWarnEnabled()) {               
                log.warn("未找到"+userId+"的用户!");
            }
        }
        String orgId = "";
        if(user!=null&&!CollectionUtils.isEmpty(user.getUserBelongs())) {
            for(UserBelong userBelong : user.getUserBelongs()) {
                if(YesNo.Yes.id.equals(userBelong.getDefaultFlag()) && userBelong.getMigrationStatus().equals(MigrationStatus.Normal.id)) {
                    return userBelong.getOrgId();
                }
                if(StringUtils.isEmpty(orgId))
                    orgId = userBelong.getOrgId();
            }
        }
        return orgId;//没有找到就取第一个机构
    }
    
    /**
     * 根据传入的用户和所属机构获取其数据权限
     * @param userId
     * @param orgId
     * @return 用户数据权限 @see com.amarsoft.aecd.system.constant.DataAuth
     */
    public static String getUserDataAuth(String userId, String orgId) {
        User user = getUser(userId);
        String dataAuth = DataAuth.UserData.id;//默认用户数据权限
        if(user!=null&&!CollectionUtils.isEmpty(user.getUserBelongs())) {
            for(UserBelong userBelong : user.getUserBelongs()) {
                if(userBelong.getOrgId().equals(orgId) && userBelong.getMigrationStatus().equals(MigrationStatus.Normal.id)) {
                    dataAuth = userBelong.getDataAuth();
                }
            }
        }
        return dataAuth;
    }
    
    /**
     * 获取当前操作用户和机构的用户数据权限
     * @return 用户数据权限 @see com.amarsoft.aecd.system.constant.DataAuth
     */
    public static String getUserDataAuth() {
        return getUserDataAuth(GlobalShareContextHolder.getUserId(), GlobalShareContextHolder.getOrgId());
    }
    
    
    /**
     * 获取指定用户和机构有效的角色列表（包括角色组项下的角色）
     * @param userId
     * @param orgId
     * @return 角色列表
     */
    public static List<String> getUserRoles(String userId, String orgId) {
        if(StringUtils.isEmpty(userId)
                || StringUtils.isEmpty(orgId)) return new ArrayList<String>();
        User user = getUser(userId);
        if(!UserStatus.Valid.id.equals(user.getStatus())) return new ArrayList<String>();
        
        List<String> roles = new ArrayList<String>();
        //先获取用户角色
        user.getRoles().forEach(role -> {
            if(role.getOrgId().equals(orgId)) {
                Role r = RoleHelper.getRole(role.getRoleId());
                if(SystemStatus.Normal.id.equals(r.getStatus()) && !roles.contains(r.getRoleId())) {//有效的角色
                    roles.add(r.getRoleId());
                }
            }
        });
        user.getGroups().forEach(group -> {
            if(group.getOrgId().equals(orgId)) {
                Group g = GroupHelper.getGroup(group.getGroupId());
                for(Role r : g.getRoles()) {
                    if(SystemStatus.Normal.id.equals(r.getStatus()) && !roles.contains(r.getRoleId())) {//有效的角色
                        roles.add(r.getRoleId());
                    }
                }
            }
        });
        
        //获取用户转授权信息
        UserSubstQueryReq substReq = new UserSubstQueryReq();
        ArrayList<String> status = new ArrayList<String>();
        status.add(UserLeaveStatus.Able.id);
        substReq.setStatus(status);
        substReq.setSubstUserId(userId);
        substReq.setBegin(0);
        substReq.setPageSize(Integer.MAX_VALUE);
        UserSubstQueryRsp allSubst= userClient.userSubstQuery(new RequestMessage<UserSubstQueryReq>(substReq)).getBody().getMessage();
        
        for(Subst subst : allSubst.getSubsts()){
            for(com.amarsoft.app.ems.system.cs.dto.userrole.Role r : subst.getRoles()) {
                if(!roles.contains(r.getRoleId())) {//有效的角色
                    roles.add(r.getRoleId());
                }
            }
        };
        
        return roles;
    }
    
    
    /**
     * 获取当前用户和机构有效的角色列表（包括角色组项下的角色）
     * @return 角色列表
     */
    public static List<String> getUserRoles() {
        return getUserRoles(GlobalShareContextHolder.getUserId(), GlobalShareContextHolder.getOrgId());
    }
    
    /**
     * 指定用户、机构是否有指定的角色（包括角色组项下的角色）
     * @param userId
     * @param orgId
     * @param roleId
     * @return 是否有指定的角色
     */
    public static boolean hasUserRole(String userId, String orgId, String roleId) {
        if(StringUtils.isEmpty(userId)
                || StringUtils.isEmpty(orgId)) return false;
        return getUserRoles(userId, orgId).contains(roleId);
    }
    
    /**
     * 当前用户、机构是否有指定的角色（包括角色组项下的角色）
     * @param roleId
     * @return 是否有指定的角色
     */
    public static boolean hasUserRole(String roleId) {
        return getUserRoles(GlobalShareContextHolder.getUserId(), GlobalShareContextHolder.getOrgId()).contains(roleId);
    }
    
    /**
     * 查询机构项下的所有用户信息（包括失效的）
     * @param orgId
     * @return 机构项下的所有用户信息
     */
    public static List<SimpleUser> getUsersByOrg(String orgId) {
        UserQueryByOrgReq req = new UserQueryByOrgReq();
        req.setOrgId(orgId);
        req.setBegin(0);
        req.setPageSize(Integer.MAX_VALUE);
        return userClient.userQueryByOrg(new RequestMessage<UserQueryByOrgReq>(req)).getBody().getMessage().getUsers();
    }
    
    /**
     * 查询机构项下的有效的用户信息（请假的用户不包含在内）
     * @param orgId
     * @return 机构项下的有效的用户信息
     */
    public static List<SimpleUser> getActiveUsersByOrg(String orgId) {
        UserQueryByOrgReq req = new UserQueryByOrgReq();
        req.setOrgId(orgId);
        req.setStatus(UserStatus.Valid.id);
        req.setBegin(0);
        req.setPageSize(Integer.MAX_VALUE);
        List<SimpleUser> users =  userClient.userQueryByOrg(new RequestMessage<UserQueryByOrgReq>(req)).getBody().getMessage().getUsers();
        
        return users;
    }
    
    /**
     * 查询机构和角色项下的所有用户信息，包括转授权的用户（包括失效的和请假的用户）
     * @param orgId
     * @param roleId
     * @return 机构和角色项下的所有用户信息
     */
    public static List<SimpleUser> getUsersByOrg(String orgId, String roleId) {
        UserQueryByOrgReq req = new UserQueryByOrgReq();
        req.setOrgId(orgId);
        req.setRoleId(roleId);
        req.setBegin(0);
        req.setPageSize(Integer.MAX_VALUE);
        return userClient.userQueryByOrg(new RequestMessage<UserQueryByOrgReq>(req)).getBody().getMessage().getUsers();
    }
    
    /**
     * 查询机构和角色项下的有效的用户信息，包括转授权的用户（请假的用户不包含在内）
     * @param orgId
     * @param roleId 
     * @return 机构和角色项下的有效的用户信息
     */
    public static List<SimpleUser> getActiveUsersByOrg(String orgId, String roleId) {
        UserQueryByOrgReq req = new UserQueryByOrgReq();
        req.setOrgId(orgId);
        req.setRoleId(roleId);
        req.setStatus(UserStatus.Valid.id);
        req.setBegin(0);
        req.setPageSize(Integer.MAX_VALUE);
        List<SimpleUser> users =  userClient.userQueryByOrg(new RequestMessage<UserQueryByOrgReq>(req)).getBody().getMessage().getUsers();
        return users;
    }
}
