package com.amarsoft.app.ems.system.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arem.util.SpringHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectCache;
import com.amarsoft.app.ems.system.cs.client.RoleClient;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.Role;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryReq;



/**
 * 角色信息缓存
 * 
 * @author hzhang23 
 */
public final class RoleHelper{
    private static RoleClient roleClient = SpringHelper.getBean(RoleClient.class);
    
    private static BusinessObjectCache roleCache = new BusinessObjectCache(1000);
    
    private static final String ALL_ROLES = "ALL_ROLES";
    
    /**
     * 清理缓存
     */
    public static void clear()
    {
        roleCache = new BusinessObjectCache(1000);
    }
    
    /**
     * 获取所有角色
     * @return 角色信息列表
     */
    public static List<Role> getRoles() {
        List<Role> roles = (List<Role>)roleCache.getCacheObject(ALL_ROLES);
        if (CollectionUtils.isEmpty(roles)) {
            RoleAllQueryReq request = new RoleAllQueryReq();
            roles = roleClient.roleAllQuery(new RequestMessage<RoleAllQueryReq>(request)).getBody().getMessage().getRoles();
            for(Role role : roles){
                roleCache.setCache(role.getRoleId(), role);
            }
            roleCache.setCache(ALL_ROLES, roles);
            return roles;
        }
        else 
            return roles;
    }
    
    /**
     * 获取法人机构对应的角色信息
     * @param belongRootOrg
     * @return 角色列表信息
     */
    public static List<Role> getRoles(String belongRootOrg) {
        if(StringUtils.isEmpty(belongRootOrg)) return new ArrayList<Role>();
        List<Role> roles = getRoles();
        List<Role> belongOrgRoles = new ArrayList<Role>();
        for(Role role : roles) {
            if(belongRootOrg.equals(role.getBelongRootOrg()))
                belongOrgRoles.add(role);
        }
        return belongOrgRoles;
    }
    
    /**
     * 获取法人机构指定机构级别对应的角色信息
     * @param belongRootOrg 法人机构
     * @param orgLevel 机构级别
     * @return 角色列表信息
     */
    public static List<Role> getRoles(String belongRootOrg, String orgLevel) {
        if(StringUtils.isEmpty(belongRootOrg) 
                || StringUtils.isEmpty(orgLevel)) return new ArrayList<Role>();
        List<Role> roles = getRoles();
        List<Role> belongOrgRoles = new ArrayList<Role>();
        for(Role role : roles) {
            if(belongRootOrg.equals(role.getBelongRootOrg()) && orgLevel.equals(role.getBelongOrgLevel()))
                belongOrgRoles.add(role);
        }
        return belongOrgRoles;
    }
    
    /**
     * 通过角色编号获取角色定义
     * @param roleId
     * @return 角色信息
     */
    public static Role getRole(String roleId) {
        Role role = (Role)roleCache.getCacheObject(roleId);
        if(role == null) {
            getRoles();
            role = (Role)roleCache.getCacheObject(roleId);
        }
        
        if(role == null) {
            throw new ALSException("902021", roleId);
        }
        
        return role;
    }
    
    /**
     * 获取角色名称
     * @param roleId
     * @return 角色名称
     */
    public static String getRoleName(String roleId) {
        return getRole(roleId).getRoleName();
    }
}

