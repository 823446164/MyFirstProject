package com.amarsoft.app.ems.system.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arem.util.SpringHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectCache;
import com.amarsoft.app.ems.system.cs.client.GroupClient;
import com.amarsoft.app.ems.system.cs.dto.groupallquery.Group;



/**
 * 角色组信息缓存
 * 
 * @author hzhang23 
 */
public final class GroupHelper{
    private static GroupClient groupClient = SpringHelper.getBean(GroupClient.class);
    
    private static BusinessObjectCache groupCache = new BusinessObjectCache(1000);
    
    private static final String ALL_GROUPS = "ALL_GROUPS";
    
    /**
     * 清理缓存
     */
    public static void clear()
    {
        groupCache = new BusinessObjectCache(1000);
    }
    
    /**
     * 获取所有角色组
     * @return 角色组信息列表
     */
    public static List<Group> getGroups() {
        List<Group> groups = (List<Group>)groupCache.getCacheObject(ALL_GROUPS);
        if (CollectionUtils.isEmpty(groups)) {
            groups = groupClient.groupAllQuery(new RequestMessage<Object>(null)).getBody().getMessage().getGroups();
            for(Group group : groups){
                groupCache.setCache(group.getGroupId(), group);
            }
            groupCache.setCache(ALL_GROUPS, groups);
            return groups;
        }
        else 
            return groups;
    }
    
    /**
     * 获取法人机构对应的角色组信息
     * @param belongRootOrg
     * @return 角色组列表信息
     */
    public static List<Group> getGroups(String belongRootOrg) {
        if(StringUtils.isEmpty(belongRootOrg)) return new ArrayList<Group>();
        List<Group> groups = getGroups();
        List<Group> belongOrgGroups = new ArrayList<Group>();
        for(Group group : groups) {
            if(belongRootOrg.equals(group.getBelongRootOrg()))
                belongOrgGroups.add(group);
        }
        return belongOrgGroups;
    }
    
    /**
     * 获取法人机构指定机构级别对应的角色组信息
     * @param belongRootOrg 法人机构
     * @param orgLevel 机构级别
     * @return 角色组列表信息
     */
    public static List<Group> getGroups(String belongRootOrg, String orgLevel) {
        if(StringUtils.isEmpty(belongRootOrg) 
                || StringUtils.isEmpty(orgLevel)) return new ArrayList<Group>();
        List<Group> groups = getGroups();
        List<Group> belongOrgGroups = new ArrayList<Group>();
        for(Group group : groups) {
            if(belongRootOrg.equals(group.getBelongRootOrg()) && orgLevel.equals(group.getBelongOrgLevel()))
                belongOrgGroups.add(group);
        }
        return belongOrgGroups;
    }
    
    /**
     * 通过角色编号获取角色组定义
     * @param groupId
     * @return 角色组信息
     */
    public static Group getGroup(String groupId) {
        Group group = (Group)groupCache.getCacheObject(groupId);
        if(group == null) {
            getGroups();
            group = (Group)groupCache.getCacheObject(groupId);
        }
        
        if(group == null) {
            throw new ALSException("902019", groupId);
        }
        
        return group;
    }
    
    /**
     * 获取角色组名称
     * @param groupId
     * @return 角色组名称
     */
    public static String getGroupName(String groupId) {
        return getGroup(groupId).getGroupName();
    }
}

