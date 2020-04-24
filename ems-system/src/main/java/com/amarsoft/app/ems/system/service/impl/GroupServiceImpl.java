package com.amarsoft.app.ems.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.aecd.common.constant.Status;
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.aecd.system.constant.OrgType;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.system.cs.dto.addgroup.AddGroupReq;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryReq;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.deletegroup.DeleteGroupReq;
import com.amarsoft.app.ems.system.cs.dto.groupallquery.GroupAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.groupquery.Group;
import com.amarsoft.app.ems.system.cs.dto.groupquery.GroupQueryReq;
import com.amarsoft.app.ems.system.cs.dto.groupquery.GroupQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.grouprole.GroupRoleReq;
import com.amarsoft.app.ems.system.cs.dto.grouprole.Role;
import com.amarsoft.app.ems.system.cs.dto.groupuserquery.GroupUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.groupuserquery.GroupUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.levelgroupquery.CooperateGroup;
import com.amarsoft.app.ems.system.cs.dto.levelgroupquery.LevelGroupQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelgroupquery.LevelGroupQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleauth.RoleAuth;
import com.amarsoft.app.ems.system.cs.dto.updategroup.UpdateGroupReq;
import com.amarsoft.app.ems.system.entity.GroupInfo;
import com.amarsoft.app.ems.system.entity.GroupRole;
import com.amarsoft.app.ems.system.entity.OrgInfo;
import com.amarsoft.app.ems.system.entity.RoleInfo;
import com.amarsoft.app.ems.system.entity.UserBelong;
import com.amarsoft.app.ems.system.entity.UserGroup;
import com.amarsoft.app.ems.system.entity.UserInfo;
import com.amarsoft.app.ems.system.service.GroupService;
import com.amarsoft.app.ems.system.service.OrgService;

import lombok.extern.slf4j.Slf4j;

/**
 * 角色组服务逻辑处理类
 * @author xjzhao
 */
@Slf4j
@Service
public class GroupServiceImpl implements GroupService {
    
    private final static String KEY_FRE="group:";
    public final static String KEY_FRE_ALL="ASMS_GROUP_CACHE_ALL";
    
    @Override
    @Caching(evict = {
                @CacheEvict(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #req.getGroupId()",condition="#req.getGroupId() != null"),
                @CacheEvict(value=KEY_FRE, key="#root.target.KEY_FRE_ALL")
        }
    )
    public void addGroup(AddGroupReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        GroupInfo gi = bomanager.keyLoadBusinessObject(GroupInfo.class, req.getGroupId());
        if(gi != null)
            throw new ALSException("900802");//不要重复保存
        gi = new GroupInfo();
        gi.setGroupId(req.getGroupId());
        gi.setGroupName(req.getGroupName());
        gi.setBelongRootOrg(req.getBelongRootOrg());
        gi.setBelongOrgLevel(req.getBelongOrgLevel());
        gi.setStatus(req.getStatus());
        bomanager.updateBusinessObject(gi);
        
        role(bomanager, req.getGroupId(), req.getRoles());
        user(bomanager, req.getGroupId(), req.getBelongOrgLevel(), req.getUsers());
        bomanager.updateDB();
    }

    @Override
    @Caching(evict = {
                @CacheEvict(value=KEY_FRE, key="#root.target.KEY_FRE_ALL"),
                @CacheEvict(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #req.getGroupId()",condition="#req.getGroupId() != null")
        }
    )
    public boolean updateGroup(UpdateGroupReq req) {
        boolean flag = false;
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        GroupInfo gi = bomanager.keyLoadBusinessObject(GroupInfo.class, req.getGroupId());
        if(gi == null) //数据库不存在该角色组
            gi = new GroupInfo();
        gi.setGroupId(req.getGroupId());
        gi.setGroupName(req.getGroupName());
        gi.setBelongRootOrg(req.getBelongRootOrg());
        gi.setBelongOrgLevel(req.getBelongOrgLevel());
        flag = flag || gi.getStatus() == null || !gi.getStatus().equals(req.getStatus());
        gi.setStatus(req.getStatus());
        bomanager.updateBusinessObject(gi);
        List<Role> roles = req.getRoles();
        flag = flag || role(bomanager,req.getGroupId(),roles);
        user(bomanager, req.getGroupId(), req.getBelongOrgLevel(), req.getUsers());
        bomanager.updateDB();
        return flag;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="#root.target.KEY_FRE_ALL"),
            @CacheEvict(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #req.getGroupId()",condition="#req.getGroupId() != null")
        }
    )
    public void deleteGroup(DeleteGroupReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<UserGroup> grs = bomanager.loadBusinessObjects(UserGroup.class, 0, 1, "groupId=:groupId", "groupId", req.getGroupId());
        if(!CollectionUtils.isEmpty(grs.getBusinessObjects()))
            throw new ALSException("900806");
        
        if(log.isInfoEnabled()) {
            log.info("删除角色："+req.getGroupId()+"及其角色权限。");
        }
        
        //删除该角色组
        bomanager.deleteObjectBySql(GroupInfo.class, "groupId=:groupId", "groupId", req.getGroupId());
        //角色组对应角色
        bomanager.deleteObjectBySql(GroupRole.class, "groupId=:groupId", "groupId", req.getGroupId());
        bomanager.updateDB();
    }

    @Override
    @Caching(cacheable = {
            @Cacheable(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #req.getGroupId()",condition="#req.getGroupId() != null"),
        }
    )
    public GroupQueryRsp getGroups(GroupQueryReq req) {
        if(StringUtils.isEmpty(req.getGroupId()) 
                && (CollectionUtils.isEmpty(req.getBelongOrgLevel())
                        && StringUtils.isEmpty(req.getBelongRootOrg()))
                    && (req.getBegin() == null 
                    || req.getPageSize() == null 
                    || req.getPageSize() == 0))
            throw new ALSException("900808");
        GroupQueryRsp rsp = new GroupQueryRsp();
        rsp.setGroups(new ArrayList<Group>());
        
        if (StringUtils.isEmpty(req.getBegin()) || StringUtils.isEmpty(req.getPageSize())) {
            req.setBegin(0);
            req.setPageSize(Integer.MAX_VALUE);
        }
        
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(!StringUtils.isEmpty(req.getGroupId())) {
            GroupInfo gi = bomanager.keyLoadBusinessObject(GroupInfo.class, req.getGroupId());
            if(gi != null) {
                groupResponse(bomanager, gi, rsp);
            }
            rsp.setTotalCount(1);
        }else if(!CollectionUtils.isEmpty(req.getBelongOrgLevel()) || !StringUtils.isEmpty(req.getBelongRootOrg())){
            BusinessObjectAggregate<GroupInfo> gis = null;
                if (CollectionUtils.isEmpty(req.getBelongOrgLevel())) {
                     gis = bomanager.loadBusinessObjects(GroupInfo.class, req.getBegin(), req.getPageSize(), "belongRootOrg =:belongRootOrg","belongRootOrg",req.getBelongRootOrg());
                    for(GroupInfo gi : gis.getBusinessObjects()) {
                        groupResponse(bomanager,gi,rsp);
                    }
                } else {
                     gis = bomanager.loadBusinessObjects(GroupInfo.class, req.getBegin(), req.getPageSize(), 
                                                                    "belongRootOrg =:belongRootOrg and belongOrgLevel in ( :belongOrgLevel )",
                                                                    "belongRootOrg",req.getBelongRootOrg(),"belongOrgLevel",req.getBelongOrgLevel())
                                                                    ;
                    for(GroupInfo gi : gis.getBusinessObjects()) {
                        groupResponse(bomanager,gi,rsp);
                    }
                }
                rsp.setTotalCount(gis.getAggregate("count(groupId) as cnt").getInt("cnt"));
        }else{
            BusinessObjectAggregate<GroupInfo> gis = bomanager.loadBusinessObjects(GroupInfo.class, req.getBegin(), req.getPageSize(), " 1=1 ");
            for(GroupInfo gi : gis.getBusinessObjects()) {
                groupResponse(bomanager,gi,rsp);
            }
        }
        return rsp;
    }

    /**
     * 组装角色组返回对象
     * @param bomanager
     * @param gi
     * @param rsp
     */
    private void groupResponse(BusinessObjectManager bomanager, GroupInfo gi, GroupQueryRsp rsp){
        OrgInfo org = bomanager.keyLoadBusinessObject(OrgInfo.class, gi.getBelongRootOrg());
        Group g = new Group();
        g.setGroupId(gi.getGroupId());
        g.setGroupName(gi.getGroupName());
        g.setBelongRootOrg(gi.getBelongRootOrg());
        g.setBelongRootOrgName(org.getOrgName());
        g.setBelongOrgType(org.getOrgType());
        g.setBelongOrgLevel(gi.getBelongOrgLevel());
        g.setStatus(gi.getStatus());
        g.setRoles(new ArrayList<com.amarsoft.app.ems.system.cs.dto.userrole.Role>());
        List<GroupRole> ras = bomanager.loadBusinessObjects(GroupRole.class, 0, Integer.MAX_VALUE, "groupId=:groupId", 
                                                                        "groupId", gi.getGroupId()).getBusinessObjects();
        for(GroupRole ra : ras) {
            com.amarsoft.app.ems.system.cs.dto.userrole.Role role = new com.amarsoft.app.ems.system.cs.dto.userrole.Role();
            role.setRoleId(ra.getRoleId());
            g.getRoles().add(role);
        }
        rsp.getGroups().add(g);
    }

    @Override
    @Caching(evict= {
              @CacheEvict(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #req.getGroupId()",condition="#req.getGroupId() != null"),
              @CacheEvict(value=KEY_FRE, key="#root.target.KEY_FRE_ALL")
    })
    public boolean groupRole(GroupRoleReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        boolean flag = role(bomanager, req.getGroupId(), req.getRoles());
        bomanager.updateDB();
        return flag;
    }

    /**
     * 角色组角色操作
     * @param bomanager 本方法内部不做updateDB
     * @param groupId 角色组信息
     * @param groups 角色组关联角色
     * @return 是否变更了权限
     */
    private boolean role(BusinessObjectManager bomanager, String groupId, List<Role> groups) {
        if(StringUtils.isEmpty(groupId)) return false;//允许新增角色组、更新角色组不设置角色
        List<GroupRole> grs = bomanager.loadBusinessObjects(GroupRole.class, 0, Integer.MAX_VALUE, "groupId=:groupId", "groupId", groupId).getBusinessObjects();
        
        boolean flag = false;
        
        //先区分数据库存在，本次提交的记录中存在或不存在的
        for(GroupRole gr : grs) {
            boolean exist = false;
            for(Role role : groups) {
                if(role.getRoleId().equals(gr.getRoleId()) ) {
                    exist = true;
                    break;
                }
            }
            
            if(!exist) {
                flag = true;
                bomanager.deleteBusinessObject(gr);//本次没有传入的角色，全部删除
            }
        }
        
        //再区分本次提交记录存在的，但是数据库记录不存在的
        for(Role role : groups) {
            boolean exist = false;
            for(GroupRole gr : grs) {
                if(role.getRoleId().equals(gr.getRoleId()) ) {
                    exist = true;//前面已处理这种情况，这里就不再处理了
                }
            }
            
            if(!exist) {
                RoleInfo ri = bomanager.keyLoadBusinessObject(RoleInfo.class, role.getRoleId());
                if(ri == null) //如果没有这个角色就跳过
                    continue;
                flag = true;
                GroupRole gr = new GroupRole();
                gr.setGroupId(groupId);
                gr.setRoleId(role.getRoleId());
                bomanager.updateBusinessObject(gr);
            }
        }
        
        if(log.isInfoEnabled() && flag) {
            log.info("角色组："+groupId+"的角色从："+grs.toString()+" 变更为："+groups.toString());
        }
        return flag;
    }
    
    /**
     * 角色组用户操作
     * @param bomanager 本方法内部不做updateDB
     * @param groupId 角色组信息
     * @param groupOrgLevel 
     * @param groups 角色组关联角色
     * @return 是否变更了权限
     */
    private boolean user(BusinessObjectManager bomanager, String groupId, String groupOrgLevel, List<com.amarsoft.app.ems.system.cs.dto.updategroup.UserInfo> users) {
        if(StringUtils.isEmpty(groupId)) return false;//允许新增角色组、更新角色组不设置角色
        List<UserGroup> urs = bomanager.loadBusinessObjects(UserGroup.class, 0, Integer.MAX_VALUE, "groupId=:groupId", "groupId", groupId).getBusinessObjects();
        
        boolean flag = false;
        
        //先区分数据库存在，本次提交的记录中存在或不存在的
        for(UserGroup ur : urs) {
            boolean exist = false;
            for(com.amarsoft.app.ems.system.cs.dto.updategroup.UserInfo ui : users) {
                if(ui.getUserId().equals(ur.getUserId()) ) {
                    exist = true;
                    break;
                }
            }
            
            if(!exist) {
                flag = true;
                bomanager.deleteBusinessObject(ur);//本次没有传入的角色，全部删除
            }
        }
        
        //再区分本次提交记录存在的，但是数据库记录不存在的
        for(com.amarsoft.app.ems.system.cs.dto.updategroup.UserInfo ui : users) {
            boolean exist = false;
            for(UserGroup ug : urs) {
                if(ui.getUserId().equals(ug.getUserId()) ) {
                    exist = true;//前面已处理这种情况，这里就不再处理了
                }
            }
            
            if(!exist) {
                UserInfo u = bomanager.keyLoadBusinessObject(UserInfo.class, ui.getUserId());
                if(u == null) //如果没有这个角色就跳过
                    continue;
                
                //获取用户的机构信息，优先取默认机构
                List<UserBelong> ubs = bomanager.loadBusinessObjects(UserBelong.class, 0, Integer.MAX_VALUE, "userId = :userId order by defaultFlag desc", "userId", u.getUserId()).getBusinessObjects();
                if(CollectionUtils.isEmpty(ubs))
                    continue;
                
                flag = true;
                for (UserBelong ub : ubs) {
                    UserGroup ug = new UserGroup();
                    ug.setGroupId(groupId);
                    ug.setUserId(u.getUserId());
                    OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, ub.getOrgId());
                    if (orgInfo.getOrgLevel().equals(groupOrgLevel) && !orgInfo.getOrgType().equals(OrgType.Department.id)) { //机构和公司用户所属机构 按级别关联
                        ug.setOrgId(ub.getOrgId());
                        bomanager.updateBusinessObject(ug);
                    }else if (OrgLevel.isExist(OrgLevel.getNextLevel(orgInfo.getOrgLevel())) &&
                            OrgLevel.getNextLevel(groupOrgLevel).equals(orgInfo.getOrgLevel()) && 
                            orgInfo.getOrgType().equals(OrgType.Department.id)) {//部室-用户所属机构 按上级级别关联
                        ug.setOrgId(ub.getOrgId());
                        bomanager.updateBusinessObject(ug);
                    }else if (OrgLevel.LEVEL_4.id.equals(groupOrgLevel) && OrgLevel.LEVEL_5.id.equals(orgInfo.getOrgLevel())) {//网点所属机构 按支行关联
                        ug.setOrgId(ub.getOrgId());
                        bomanager.updateBusinessObject(ug);
                    }
                }
            }
        }
        
        if(log.isInfoEnabled() && flag) {
            log.info("角色组："+groupId+"的用户从："+urs.toString()+" 变更为："+users.toString());
        }
        return flag;
    }
    @Override
    @Cacheable(value=KEY_FRE, key="#root.target.KEY_FRE_ALL")
    public GroupAllQueryRsp groupAllQuery() {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<GroupInfo> groups = bomanager.loadBusinessObjects(GroupInfo.class, 0, Integer.MAX_VALUE, "status =:status","status",Status.Valid.id).getBusinessObjects();
        List<com.amarsoft.app.ems.system.cs.dto.groupallquery.Group> returnGroups = new ArrayList<com.amarsoft.app.ems.system.cs.dto.groupallquery.Group>();
        for (GroupInfo groupInfo : groups) {
            com.amarsoft.app.ems.system.cs.dto.groupallquery.Group group = new com.amarsoft.app.ems.system.cs.dto.groupallquery.Group();
            group.setGroupId(groupInfo.getGroupId());
            group.setGroupName(groupInfo.getGroupName());
            group.setBelongRootOrg(groupInfo.getBelongRootOrg());
            OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, groupInfo.getBelongRootOrg());
            if (orgInfo != null) {
                group.setBelongOrgType(orgInfo.getOrgType());
                group.setBelongOrgLevel(groupInfo.getBelongOrgLevel());
            }
            group.setStatus(groupInfo.getStatus());
            group.setRoles(new ArrayList<com.amarsoft.app.ems.system.cs.dto.roleallquery.Role>());
            
            List<GroupRole> groupgroups = bomanager.loadBusinessObjects(GroupRole.class, 0, Integer.MAX_VALUE, "groupId=:groupId", "groupId", groupInfo.getGroupId()).getBusinessObjects();
            for(GroupRole gr : groupgroups) {
                com.amarsoft.app.ems.system.cs.dto.roleallquery.Role role = new com.amarsoft.app.ems.system.cs.dto.roleallquery.Role();
                List<RoleInfo> rs = bomanager.loadBusinessObjects(RoleInfo.class, "roleId =:roleId and status = :status", "roleId", gr.getRoleId(), "status", Status.Valid.id);
                if (CollectionUtils.isEmpty(rs)) {
                    continue;
                }
                RoleInfo r = rs.get(0);
                role.setRoleId(gr.getRoleId());
                role.setRoleName(r.getRoleName());
                role.setBelongOrgLevel(r.getBelongOrgLevel());
                role.setBelongRootOrg(r.getBelongRootOrg());
                role.setStatus(r.getStatus());
                role.setRoleAuths(new ArrayList<com.amarsoft.app.ems.system.cs.dto.roleauth.RoleAuth>());
                List<com.amarsoft.app.ems.system.entity.RoleAuth> ras = bomanager.loadBusinessObjects(com.amarsoft.app.ems.system.entity.RoleAuth.class, 0, Integer.MAX_VALUE, "roleId=:roleId", "roleId", role.getRoleId()).getBusinessObjects();
                for(com.amarsoft.app.ems.system.entity.RoleAuth ra : ras) {
                    RoleAuth roleAuth = new RoleAuth();
                    roleAuth.setAuthName(ra.getAuthName());
                    roleAuth.setAuthNo(ra.getAuthNo());
                    roleAuth.setAuthType(ra.getAuthType());
                    roleAuth.setStatus(ra.getStatus());
                    role.getRoleAuths().add(roleAuth);
                }
                group.getRoles().add(role);
            }
            returnGroups.add(group);
        }
        GroupAllQueryRsp rsp = new GroupAllQueryRsp();
        rsp.setGroups(returnGroups);
        return rsp;
    }

    @Override
    public GroupUserQueryRsp groupUserQuery(GroupUserQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<UserGroup> userGroups = null;
        String[] searchAttributes = {"userId","userName"};//查询条件
        if (StringUtils.isEmpty(req.getSearchAttribute()) && StringUtils.isEmpty(req.getSearchContent())) {//不走查询条件
            userGroups = bomanager.loadBusinessObjects(UserGroup.class, 0, Integer.MAX_VALUE, "groupId = :groupId","groupId",req.getGroupId()).getBusinessObjects();
        }else {
            if (Stream.of(searchAttributes).anyMatch(searchAttribute -> searchAttribute.equalsIgnoreCase(req.getSearchAttribute()))) {//验证查询条件
                BusinessObjectAggregate<UserGroup> resultAggregate = bomanager.loadBusinessObjects(UserGroup.class,req.getBegin(),req.getPageSize(), req.getSearchAttribute() + " like :searchContent",
                        "searchContent","%"+req.getSearchContent()+"%");;
                if (resultAggregate != null) {
                    userGroups = resultAggregate.getBusinessObjects();
                }
            }else {
                throw new ALSException("900811");
            }
        }
        GroupUserQueryRsp rsp = new GroupUserQueryRsp();
        rsp.setUsers(new ArrayList<com.amarsoft.app.ems.system.cs.dto.groupuserquery.User>());
        for (UserGroup userGroup : userGroups) {
            com.amarsoft.app.ems.system.cs.dto.groupuserquery.User user = new com.amarsoft.app.ems.system.cs.dto.groupuserquery.User();
            UserInfo userInfo = bomanager.keyLoadBusinessObject(UserInfo.class, userGroup.getUserId());
            if(userInfo == null) 
                continue;
            user.setUserId(userInfo.getUserId());
            user.setUserName(userInfo.getUserName());
            user.setCounter(userInfo.getCounter());
            user.setEmail(userInfo.getEmail());
            user.setEmpNo(userInfo.getEmpNo());
            user.setPhoneNo(userInfo.getPhoneNo());
            user.setJobTitle(userInfo.getJobTitle());
            user.setOfficeTel(userInfo.getOfficeTel());
            user.setStatus(userInfo.getStatus());
            rsp.getUsers().add(user);
        }
        return rsp;
    }

    @Override
    public LevelGroupQueryRsp levelGroupQuery(LevelGroupQueryReq req, GroupService groupService, OrgService orgService) {
        LevelGroupQueryRsp rsp = new LevelGroupQueryRsp();
        rsp.setRspList(new ArrayList<CooperateGroup>());
        ConditionalOrgsQueryRsp orgRsp = orgService.orgInfoQueryByCondition(new ConditionalOrgsQueryReq());//查询法人机构
        
        OrgInfoQueryReq orgReq = new OrgInfoQueryReq();//查询登陆机构详情
        orgReq.setOrgId(GlobalShareContextHolder.getOrgId());
        OrgInfoQueryRsp loginOrgInfo = orgService.getOrgInfo(orgReq);

        orgRsp.getOrgInfos().stream()
        .forEach(org -> {
            
            if (!StringUtils.isEmpty(GlobalShareContextHolder.getOrgId())) {//只查询当前登陆机构法人下的团队
                if(!org.getOrgId().equals(orgService.getRootOrgId(GlobalShareContextHolder.getOrgId()))) {
                    return ;
                }
            }
            
            CooperateGroup groups = new CooperateGroup();
            groups.setOrgId(org.getOrgId());
            groups.setOrgName(org.getOrgName());
            groups.setOrgType(org.getOrgType());
            
            GroupQueryReq groupReq = new GroupQueryReq();
            groupReq.setBelongRootOrg(org.getOrgId());
            groupReq.setBelongOrgLevel(new ArrayList<>());
            
            if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_1.id) <= 0
                    && CollectionUtils.isEmpty(req.getBelongOrgLevel()) ? true : this.containsAll(req.getBelongOrgLevel(), OrgLevel.LEVEL_1.id)) {//总行角色
                groups.setHeadList(new ArrayList<>());
                groupReq.setBegin(req.getBegin());
                groupReq.setPageSize(req.getPageSize());
                groupReq.getBelongOrgLevel().add(OrgLevel.LEVEL_1.id);
                GroupQueryRsp groupRsp = groupService.getGroups(groupReq);
                List<Group> rspList = groupRsp.getGroups();
                groups.getHeadList().addAll(rspList);
                groups.setHeadTotalCount(groupRsp.getTotalCount());
            }
            if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_2.id) <= 0 
                    && CollectionUtils.isEmpty(req.getBelongOrgLevel()) ? true : this.containsAll(req.getBelongOrgLevel(), OrgLevel.LEVEL_2.id,OrgLevel.LEVEL_3.id)) {//分行角色
                groups.setBranchList(new ArrayList<>());
                groupReq.setBegin(req.getBegin());
                groupReq.setPageSize(req.getPageSize());
                groupReq.getBelongOrgLevel().clear();//清空之前请求
                groupReq.getBelongOrgLevel().add(OrgLevel.LEVEL_2.id);
                groupReq.getBelongOrgLevel().add(OrgLevel.LEVEL_3.id);
                GroupQueryRsp groupRsp = groupService.getGroups(groupReq);
                List<Group> rspList = groupRsp.getGroups();
                groups.getBranchList().addAll(rspList);
                groups.setBranchTotalCount(groupRsp.getTotalCount());
            }else if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_3.id) <= 0
                    && CollectionUtils.isEmpty(req.getBelongOrgLevel()) ? true : this.containsAll(req.getBelongOrgLevel(),OrgLevel.LEVEL_3.id)) {//分行角色
                groups.setBranchList(new ArrayList<>());
                groupReq.setBegin(req.getBegin());
                groupReq.setPageSize(req.getPageSize());
                groupReq.getBelongOrgLevel().clear();//清空之前请求
                groupReq.getBelongOrgLevel().add(OrgLevel.LEVEL_2.id);
                groupReq.getBelongOrgLevel().add(OrgLevel.LEVEL_3.id);
                GroupQueryRsp groupRsp = groupService.getGroups(groupReq);
                List<Group> rspList = groupRsp.getGroups();
                groups.getBranchList().addAll(rspList);
                groups.setBranchTotalCount(groupRsp.getTotalCount());
            }
            if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_4.id) <= 0 
                    && CollectionUtils.isEmpty(req.getBelongOrgLevel()) ? true : this.containsAll(req.getBelongOrgLevel(), OrgLevel.LEVEL_4.id)) {//支行角色
                groups.setSubbranchList(new ArrayList<>());
                groupReq.setBegin(req.getBegin());
                groupReq.setPageSize(req.getPageSize());
                groupReq.getBelongOrgLevel().clear();//清空之前请求
                groupReq.getBelongOrgLevel().add(OrgLevel.LEVEL_4.id);
                GroupQueryRsp groupRsp = groupService.getGroups(groupReq);
                List<Group> rspList = groupRsp.getGroups();
                groups.getSubbranchList().addAll(rspList);
                groups.setSubbranchTotalCount(groupRsp.getTotalCount());
            }
            rsp.getRspList().add(groups);
        });
        return rsp;
    }
    
    /**
     * 查询一个数组中是否全量包涵另一个数组
     * @param source　源数组
     * @param target　目标数组
     * @return
     */
    private boolean containsAll(List<?> source, Object... target) {
        if (CollectionUtils.isEmpty(source) || target == null || target.length == 0) {
            return false;
        }
        for (Object t : target) {
            if (!source.contains(t)) {
                return false;
            }
        }
        return true;
    }
}
