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
import com.amarsoft.app.ems.system.cs.dto.addrole.AddRoleReq;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryReq;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.deleterole.DeleteRoleReq;
import com.amarsoft.app.ems.system.cs.dto.levelrolequery.CooperateRole;
import com.amarsoft.app.ems.system.cs.dto.levelrolequery.LevelRoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelrolequery.LevelRoleQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleauth.RoleAuth;
import com.amarsoft.app.ems.system.cs.dto.roleauth.RoleAuthReq;
import com.amarsoft.app.ems.system.cs.dto.rolequery.Role;
import com.amarsoft.app.ems.system.cs.dto.rolequery.RoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rolequery.RoleQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleuserquery.RoleUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.roleuserquery.RoleUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleuserquery.User;
import com.amarsoft.app.ems.system.cs.dto.updaterole.UpdateRoleReq;
import com.amarsoft.app.ems.system.entity.BoardRole;
import com.amarsoft.app.ems.system.entity.GroupRole;
import com.amarsoft.app.ems.system.entity.OrgInfo;
import com.amarsoft.app.ems.system.entity.RoleInfo;
import com.amarsoft.app.ems.system.entity.UserBelong;
import com.amarsoft.app.ems.system.entity.UserInfo;
import com.amarsoft.app.ems.system.entity.UserRole;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.RoleService;

import lombok.extern.slf4j.Slf4j;

/**
 * 角色服务逻辑处理类
 * @author xjzhao
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final static String KEY_FRE="role:"; 
    public final static String KEY_FRE_ALL="ASMS_ROLE_CACHE_ALL";
    
    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="#root.target.KEY_FRE_ALL"),
            @CacheEvict(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #req.getRoleId()")
        }
    )
    public void addRole(AddRoleReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        RoleInfo ri = bomanager.keyLoadBusinessObject(RoleInfo.class, req.getRoleId());
        if(ri != null) //数据库不存在该角色
            throw new ALSException("900702");//不要重复保存
        ri = new RoleInfo();
        ri.setRoleId(req.getRoleId());
        ri.setRoleName(req.getRoleName());
        ri.setBelongRootOrg(req.getBelongRootOrg());
        ri.setBelongOrgLevel(req.getBelongOrgLevel());
        ri.setStatus(req.getStatus());
        bomanager.updateBusinessObject(ri);
        
        List<RoleAuth> roleAuths = req.getRoleAuths();
        roleAuth(bomanager,req.getRoleId(),roleAuths);
        user(bomanager, req.getRoleId(),req.getBelongOrgLevel(), req.getUsers());
        bomanager.updateBusinessObject(ri);
        bomanager.updateDB();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="#root.target.KEY_FRE_ALL"),
            @CacheEvict(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #req.getRoleId()")
        }
    )
    public boolean updateRole(UpdateRoleReq req) {
        boolean flag = false;
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        RoleInfo ri = bomanager.keyLoadBusinessObject(RoleInfo.class, req.getRoleId());
        if(ri == null) {//数据库不存在该角色
            ri = new RoleInfo();
        }
        ri.setRoleId(req.getRoleId());
        ri.setRoleName(req.getRoleName());
        ri.setBelongRootOrg(req.getBelongRootOrg());
        ri.setBelongOrgLevel(req.getBelongOrgLevel());
        flag = flag || ri.getStatus() == null || !ri.getStatus().equals(req.getStatus());
        ri.setStatus(req.getStatus());
        bomanager.updateBusinessObject(ri);
        List<RoleAuth> roleAuths = req.getRoleAuths();
        flag = flag || roleAuth(bomanager,req.getRoleId(),roleAuths);
        user(bomanager, req.getRoleId(), req.getBelongOrgLevel(), req.getUsers());
        bomanager.updateDB();
        return flag;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="#root.target.KEY_FRE_ALL"),
            @CacheEvict(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #req.getRoleId()")
        }
    )
    public void deleteRole(DeleteRoleReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<UserRole> urs = bomanager.loadBusinessObjects(UserRole.class, 0, 1, "roleId=:roleId", "roleId", req.getRoleId());
        if(!CollectionUtils.isEmpty(urs.getBusinessObjects()))
            throw new ALSException("900706");
        BusinessObjectAggregate<GroupRole> grs = bomanager.loadBusinessObjects(GroupRole.class, 0, 1, "roleId=:roleId", "roleId", req.getRoleId());
        if(!CollectionUtils.isEmpty(grs.getBusinessObjects()))
            throw new ALSException("900706");
        
        if(log.isInfoEnabled()) {
            log.info("删除角色："+req.getRoleId()+"及其角色权限。");
        }
        //删除该角色
        bomanager.deleteObjectBySql(RoleInfo.class, "roleId=:roleId", "roleId", req.getRoleId());
        //角色对应权限
        bomanager.deleteObjectBySql(com.amarsoft.app.ems.system.entity.RoleAuth.class, "roleId=:roleId", "roleId", req.getRoleId());
        bomanager.deleteObjectBySql(GroupRole.class, "roleId=:roleId", "roleId", req.getRoleId());
        bomanager.deleteObjectBySql(BoardRole.class, "roleId=:roleId", "roleId", req.getRoleId());
        bomanager.updateDB();
    }

    @Override
    @Cacheable(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #req.getRoleId()",condition="#req.getRoleId() != null")
    public RoleQueryRsp getRoles(RoleQueryReq req) {
        if(StringUtils.isEmpty(req.getRoleId()) 
                && (CollectionUtils.isEmpty(req.getBelongOrgLevel())
                && StringUtils.isEmpty(req.getBelongRootOrg()))
                && (req.getBegin() == null 
                || req.getPageSize() == null 
                || req.getPageSize() == 0))
        {
            throw new ALSException("900708");
        }
        RoleQueryRsp rsp = new RoleQueryRsp();
        BusinessObjectAggregate<RoleInfo> roleResult = null;
        rsp.setRoles(new ArrayList<com.amarsoft.app.ems.system.cs.dto.rolequery.Role>());
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(!StringUtils.isEmpty(req.getRoleId())) {//按角色查询
            RoleInfo ri = bomanager.keyLoadBusinessObject(RoleInfo.class, req.getRoleId());
            roleResponse(bomanager,ri,rsp);
            rsp.setTotalCount(1);
        }else if(!CollectionUtils.isEmpty(req.getBelongOrgLevel()) ||  !StringUtils.isEmpty(req.getBelongRootOrg())){//按机构查询
            
            if (CollectionUtils.isEmpty(req.getBelongOrgLevel())) {
                if (StringUtils.isEmpty(req.getBegin())  || StringUtils.isEmpty(req.getPageSize())  || req.getPageSize() == 0) {
                    roleResult = bomanager.loadBusinessObjects(RoleInfo.class, 0, Integer.MAX_VALUE, "belongRootOrg =:belongRootOrg and status = :status","belongRootOrg",req.getBelongRootOrg(),"status",Status.Valid.id);
                } else {
                    roleResult = bomanager.loadBusinessObjects(RoleInfo.class, req.getBegin(),req.getPageSize(), "belongRootOrg =:belongRootOrg  and status = :status","belongRootOrg",req.getBelongRootOrg(),"status",Status.Valid.id);
                }
            } else {
                if (StringUtils.isEmpty(req.getBegin())  || StringUtils.isEmpty(req.getPageSize())  || req.getPageSize() == 0) {
                    roleResult = bomanager.loadBusinessObjects(RoleInfo.class, 0, Integer.MAX_VALUE, "belongRootOrg =:belongRootOrg and belongOrgLevel in ( :belongOrgLevel ) and status = :status","belongRootOrg",req.getBelongRootOrg(),"belongOrgLevel",req.getBelongOrgLevel(),"status",Status.Valid.id);
                } else {
                    roleResult = bomanager.loadBusinessObjects(RoleInfo.class, req.getBegin(),req.getPageSize(), "belongRootOrg =:belongRootOrg and belongOrgLevel in ( :belongOrgLevel ) and status = :status","belongRootOrg",req.getBelongRootOrg(),"belongOrgLevel",req.getBelongOrgLevel(),"status",Status.Valid.id);
                }
            }
            List<RoleInfo> ris = roleResult.getBusinessObjects();

            rsp.setTotalCount(roleResult.getAggregate("count(roleId) as cnt").getInt("cnt"));
            for(RoleInfo ri : ris) {
                roleResponse(bomanager,ri,rsp);
            }
        }else {//查询所有
            roleResult = bomanager.loadBusinessObjects(RoleInfo.class, req.getBegin(), req.getPageSize(), " status =:status","status",Status.Valid.id);
            rsp.setTotalCount(roleResult.getAggregate("count(roleId) as cnt").getInt("cnt"));
            
            for(RoleInfo ri : roleResult.getBusinessObjects()) {
                roleResponse(bomanager,ri,rsp);
            }
        }
        
        return rsp;
    }
    
    /**
     * 组装角色返回报文
     * @param bomanager
     * @param ri
     * @param rsp
     */
    private void roleResponse(BusinessObjectManager bomanager, RoleInfo ri, RoleQueryRsp rsp){
        OrgInfo org = bomanager.keyLoadBusinessObject(OrgInfo.class, ri.getBelongRootOrg());
        
        com.amarsoft.app.ems.system.cs.dto.rolequery.Role r = new com.amarsoft.app.ems.system.cs.dto.rolequery.Role();
        r.setRoleId(ri.getRoleId());
        r.setRoleName(ri.getRoleName());
        r.setBelongRootOrg(ri.getBelongRootOrg());
        r.setBelongRootOrgName(org.getOrgName());
        r.setBelongOrgType(org.getOrgType());
        r.setBelongOrgLevel(ri.getBelongOrgLevel());
        r.setStatus(ri.getStatus());
        r.setRoleAuths(new ArrayList<RoleAuth>());
        List<com.amarsoft.app.ems.system.entity.RoleAuth> ras = bomanager.loadBusinessObjects(com.amarsoft.app.ems.system.entity.RoleAuth.class, 0, Integer.MAX_VALUE, "roleId=:roleId", 
                                                                                                                             "roleId", ri.getRoleId()).getBusinessObjects();
        for(com.amarsoft.app.ems.system.entity.RoleAuth ra : ras) {
            RoleAuth roleAuth = new RoleAuth();
            roleAuth.setAuthName(ra.getAuthName());
            roleAuth.setAuthNo(ra.getAuthNo());
            roleAuth.setAuthType(ra.getAuthType());
            roleAuth.setStatus(ra.getStatus());
            r.getRoleAuths().add(roleAuth);
        }
        rsp.getRoles().add(r);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="#root.target.KEY_FRE_ALL"),
            @CacheEvict(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #req.getRoleId()")
        }
    )
    public boolean roleAuth(RoleAuthReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        boolean flag = roleAuth(bomanager, req.getRoleId(), req.getRoleAuths());
        bomanager.updateDB();
        return flag;
    }
    
    @Override
    @Caching(evict = {
            @CacheEvict(value=KEY_FRE, key="#root.target.KEY_FRE_ALL"),
            @CacheEvict(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #roleId")
        }
    )
    public void cleanRoleCache(String roleId) {
        
    }

    /**
     * 角色权限操作
     * @param bomanager 本方法内部不做updateDB
     * @param roleId 角色信息
     * @param roleAuths 角色权限
     * @return 是否变更了权限
     */
    private boolean roleAuth(BusinessObjectManager bomanager, String roleId, List<RoleAuth> roleAuths) {
        if(StringUtils.isEmpty(roleId)) return false;//允许新增角色、更新角色不设置权限
        List<com.amarsoft.app.ems.system.entity.RoleAuth> ras = bomanager.loadBusinessObjects(com.amarsoft.app.ems.system.entity.RoleAuth.class, 0, Integer.MAX_VALUE, "roleId=:roleId", "roleId", roleId).getBusinessObjects();
        boolean flag = false;
        
        //先区分数据库存在，本次提交的记录中存在或不存在的
        for(com.amarsoft.app.ems.system.entity.RoleAuth ra : ras) {
            boolean exist = false;
            for(RoleAuth roleAuth : roleAuths) {
                if(roleAuth.getAuthNo().equals(ra.getAuthNo()) && roleAuth.getAuthType().equals(ra.getAuthType())) {
                    exist = true;
                    ra.setAuthName(roleAuth.getAuthName());
                    if(!ra.getStatus().equals(roleAuth.getStatus())) {
                        ra.setStatus(roleAuth.getStatus());
                        flag = true;
                    }
                    bomanager.updateBusinessObject(ra);
                    break;
                }
            }
            
            if(!exist) {
                flag = true;
                if(ra.getStatus().equals(Status.Valid.id)) {
                    bomanager.deleteBusinessObject(ra);//删除权限
                }
            }
        }
        
        //再区分本次提交记录存在的，但是数据库记录不存在的
        for(RoleAuth roleAuth : roleAuths) {
            boolean exist = false;
            for(com.amarsoft.app.ems.system.entity.RoleAuth ra : ras) {
                if(roleAuth.getAuthNo().equals(ra.getAuthNo()) && roleAuth.getAuthType().equals(ra.getAuthType())) {
                    exist = true;//前面已处理这种情况，这里就不再处理了
                }
            }
            
            if(!exist) {
                flag = true;
                com.amarsoft.app.ems.system.entity.RoleAuth ra = new com.amarsoft.app.ems.system.entity.RoleAuth();
                ra.setRoleId(roleId);
                ra.setAuthNo(roleAuth.getAuthNo());
                ra.setAuthType(roleAuth.getAuthType());
                ra.setAuthName(roleAuth.getAuthName());
                ra.setStatus(roleAuth.getStatus());
                bomanager.updateBusinessObject(ra);
            }
        }
        
        if(log.isInfoEnabled() && flag) {
            log.info("角色："+roleId+"的权限从："+ras.toString()+" 变更为："+roleAuths.toString());
        }
        return flag;
    }

    /**
     * 角色组用户操作
     * @param bomanager 本方法内部不做updateDB
     * @param roleId 角色组信息
     * @param roleOrgLevel 
     * @param orgId 机构信息
     * @param roles 角色组关联角色
     * @return 是否变更了权限
     */
    private boolean user(BusinessObjectManager bomanager, String roleId, String roleOrgLevel, List<com.amarsoft.app.ems.system.cs.dto.updaterole.UserInfo> users) {
        if(StringUtils.isEmpty(roleId)) return false;//允许新增角色组、更新角色组不设置角色
        List<UserRole> urs = bomanager.loadBusinessObjects(UserRole.class, 0, Integer.MAX_VALUE, "roleId=:roleId", 
                                                                             "roleId", roleId).getBusinessObjects();
        
        boolean flag = false;
        
        //先区分数据库存在，本次提交的记录中存在或不存在的
        for(UserRole ur : urs) {
            boolean exist = false;
            for(com.amarsoft.app.ems.system.cs.dto.updaterole.UserInfo ui : users) {
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
        for(com.amarsoft.app.ems.system.cs.dto.updaterole.UserInfo ui : users) {
            boolean exist = false;
            for(UserRole ur : urs) {
                if(ui.getUserId().equals(ur.getUserId()) ) {
                    exist = true;//前面已处理这种情况，这里就不再处理了
                }
            }
            
            if(!exist) {
                UserInfo u = bomanager.keyLoadBusinessObject(UserInfo.class, ui.getUserId());
                if(u == null) //如果没有这个角色就跳过
                    continue;
                
                //获取用户的机构信息，取所有机构哦
                List<UserBelong> ubs = bomanager.loadBusinessObjects(UserBelong.class, 0, Integer.MAX_VALUE, "userId = :userId order by defaultFlag desc", "userId", u.getUserId()).getBusinessObjects();
                if(CollectionUtils.isEmpty(ubs))
                    continue;
                
                flag = true;
                for (UserBelong ub : ubs) {
                    UserRole ur = new UserRole();
                    ur.setRoleId(roleId);
                    ur.setUserId(u.getUserId());
                    OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, ub.getOrgId());
                    if (orgInfo.getOrgLevel().equals(roleOrgLevel) && !orgInfo.getOrgType().equals(OrgType.Department.id)) { //机构和公司用户所属机构 按级别关联
                        ur.setOrgId(ub.getOrgId());
                        bomanager.updateBusinessObject(ur);
                    }
                    if (OrgLevel.isExist(OrgLevel.getNextLevel(orgInfo.getOrgLevel())) &&
                            OrgLevel.getNextLevel(roleOrgLevel).equals(orgInfo.getOrgLevel()) && 
                            orgInfo.getOrgType().equals(OrgType.Department.id)) {//部室-用户所属机构 按上级级别关联
                        ur.setOrgId(ub.getOrgId());
                        bomanager.updateBusinessObject(ur);
                    }
                    if (OrgLevel.LEVEL_4.id.equals(roleOrgLevel) && OrgLevel.LEVEL_5.id.equals(orgInfo.getOrgLevel())) {//网点所属机构 按支行关联
                        ur.setOrgId(ub.getOrgId());
                        bomanager.updateBusinessObject(ur);
                    }
                }
            }
        }
        
        if(log.isInfoEnabled() && flag) {
            log.info("角色："+roleId+"的用户从："+urs.toString()+" 变更为："+users.toString());
        }
        return flag;
    }
    
    @Override
    @Cacheable(value=KEY_FRE, key="#root.target.KEY_FRE_ALL",condition="#req.getBelongRootOrg() == null && #req.getBelongOrgLevel() == null && #req.getStatus() == null")
    public RoleAllQueryRsp roleAllQuery(RoleAllQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<RoleInfo> roles = null;
        
        if (!StringUtils.isEmpty(req.getBelongOrgLevel()) && !StringUtils.isEmpty(req.getBelongRootOrg())) {
            roles = bomanager.loadBusinessObjects(RoleInfo.class, 0, Integer.MAX_VALUE, "belongRootOrg =:belongRootOrg and belongOrgLevel =:belongOrgLevel","belongRootOrg",req.getBelongRootOrg(),"belongOrgLevel",req.getBelongOrgLevel()).getBusinessObjects();
        }else if (!StringUtils.isEmpty(req.getStatus())) {
            roles = bomanager.loadBusinessObjects(RoleInfo.class, 0, Integer.MAX_VALUE, "status = :status", "status", req.getStatus()).getBusinessObjects();
        }else {
            roles = bomanager.loadBusinessObjects(RoleInfo.class, 0, Integer.MAX_VALUE, "1=1").getBusinessObjects();
        }
        List<com.amarsoft.app.ems.system.cs.dto.roleallquery.Role> returnRoles = new ArrayList<com.amarsoft.app.ems.system.cs.dto.roleallquery.Role>();
        for (RoleInfo roleInfo : roles) {
            com.amarsoft.app.ems.system.cs.dto.roleallquery.Role role = new com.amarsoft.app.ems.system.cs.dto.roleallquery.Role();
            role.setRoleId(roleInfo.getRoleId());
            role.setRoleName(roleInfo.getRoleName());
            role.setBelongRootOrg(roleInfo.getBelongRootOrg());
            OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, roleInfo.getBelongRootOrg());
            if (orgInfo != null) {
                role.setBelongOrgType(orgInfo.getOrgType());
                role.setBelongOrgLevel(roleInfo.getBelongOrgLevel());
            }
            role.setStatus(roleInfo.getStatus());
            
            List<com.amarsoft.app.ems.system.entity.RoleAuth> loadRoleAuths = bomanager.loadBusinessObjects(com.amarsoft.app.ems.system.entity.RoleAuth.class, "roleId=:roleId", 
                    "roleId",roleInfo.getRoleId());
            List<RoleAuth> roleAuths = new ArrayList<RoleAuth>();
            for(com.amarsoft.app.ems.system.entity.RoleAuth ra : loadRoleAuths) {
                RoleAuth roleAuth = new RoleAuth();
                roleAuth.setAuthName(ra.getAuthName());
                roleAuth.setAuthNo(ra.getAuthNo());
                roleAuth.setAuthType(ra.getAuthType());
                roleAuth.setStatus(ra.getStatus());
                roleAuths.add(roleAuth);
            }
            role.setRoleAuths(roleAuths);
            returnRoles.add(role);
        }
        RoleAllQueryRsp rsp = new RoleAllQueryRsp();
        rsp.setRoles(returnRoles);
        return rsp;
    }

    @Override
    public LevelRoleQueryRsp levelRoleQuery(LevelRoleQueryReq req,RoleService roleService, OrgService orgService) {
        LevelRoleQueryRsp rsp = new LevelRoleQueryRsp();
        rsp.setRspList(new ArrayList<CooperateRole>());
        ConditionalOrgsQueryRsp orgRsp = orgService.orgInfoQueryByCondition(new ConditionalOrgsQueryReq());

        OrgInfoQueryReq orgReq = new OrgInfoQueryReq();//查询登陆机构详情
        orgReq.setOrgId(GlobalShareContextHolder.getOrgId());
        OrgInfoQueryRsp loginOrgInfo = orgService.getOrgInfo(orgReq);

        orgRsp.getOrgInfos().stream()
        .forEach(org -> {
            
            if (!StringUtils.isEmpty(GlobalShareContextHolder.getOrgId())) { //只查询当前登陆机构法人下的团队
                if(!org.getOrgId().equals(orgService.getRootOrgId(GlobalShareContextHolder.getOrgId()))) {
                    return ;
                }
            }
            
            CooperateRole roles = new CooperateRole();
            roles.setOrgId(org.getOrgId());
            roles.setOrgName(org.getOrgName());
            roles.setOrgType(org.getOrgType());
            
            RoleQueryReq roleReq = new RoleQueryReq();
            roleReq.setBelongRootOrg(org.getOrgId());
            roleReq.setBelongOrgLevel(new ArrayList<>());
            
            if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_1.id) <= 0
                    && CollectionUtils.isEmpty(req.getBelongOrgLevel()) ? true : this.containsAll(req.getBelongOrgLevel(), OrgLevel.LEVEL_1.id)) {//总行角色
                roles.setHeadList(new ArrayList<com.amarsoft.app.ems.system.cs.dto.rolequery.Role>());
                roleReq.setBegin(req.getBegin());
                roleReq.setPageSize(req.getPageSize());
                roleReq.getBelongOrgLevel().add(OrgLevel.LEVEL_1.id);
                RoleQueryRsp roleRsp = roleService.getRoles(roleReq);
                List<Role> rspList = roleRsp.getRoles();
                roles.getHeadList().addAll(rspList);
                roles.setHeadTotalCount(roleRsp.getTotalCount());
            }
            if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_2.id) <= 0 
                    && CollectionUtils.isEmpty(req.getBelongOrgLevel()) ? true : this.containsAll(req.getBelongOrgLevel(),OrgLevel.LEVEL_2.id,OrgLevel.LEVEL_3.id)) {//分行角色
                roles.setBranchList(new ArrayList<com.amarsoft.app.ems.system.cs.dto.rolequery.Role>());
                roleReq.setBegin(req.getBegin());
                roleReq.setPageSize(req.getPageSize());
                roleReq.getBelongOrgLevel().clear();//清空之前请求
                roleReq.getBelongOrgLevel().add(OrgLevel.LEVEL_2.id);
                roleReq.getBelongOrgLevel().add(OrgLevel.LEVEL_3.id);
                RoleQueryRsp roleRsp = roleService.getRoles(roleReq);
                List<Role> rspList = roleRsp.getRoles();
                roles.getBranchList().addAll(rspList);
                roles.setBranchTotalCount(roleRsp.getTotalCount());
            }else if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_3.id) <= 0
                    && CollectionUtils.isEmpty(req.getBelongOrgLevel()) ? true : this.containsAll(req.getBelongOrgLevel(),OrgLevel.LEVEL_3.id)) {//二级分行角色
                roles.setBranchList(new ArrayList<com.amarsoft.app.ems.system.cs.dto.rolequery.Role>());
                roleReq.setBegin(req.getBegin());
                roleReq.setPageSize(req.getPageSize());
                roleReq.getBelongOrgLevel().clear();//清空之前请求
                roleReq.getBelongOrgLevel().add(OrgLevel.LEVEL_3.id);
                RoleQueryRsp roleRsp = roleService.getRoles(roleReq);
                List<Role> rspList = roleRsp.getRoles();
                roles.getBranchList().addAll(rspList);
                roles.setBranchTotalCount(roleRsp.getTotalCount());
            }
            if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_4.id) <= 0 
                    && CollectionUtils.isEmpty(req.getBelongOrgLevel()) ? true : this.containsAll(req.getBelongOrgLevel(), OrgLevel.LEVEL_4.id)) {//支行角色
                roles.setSubbranchList(new ArrayList<com.amarsoft.app.ems.system.cs.dto.rolequery.Role>());
                roleReq.setBegin(req.getBegin());
                roleReq.setPageSize(req.getPageSize());
                roleReq.getBelongOrgLevel().clear();//清空之前请求
                roleReq.getBelongOrgLevel().add(OrgLevel.LEVEL_4.id);
                RoleQueryRsp roleRsp = roleService.getRoles(roleReq);
                List<Role> rspList = roleRsp.getRoles();
                roles.getSubbranchList().addAll(rspList);
                roles.setSubbranchTotalCount(roleRsp.getTotalCount());
            }
            rsp.getRspList().add(roles);
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
    
    @Override
    public RoleUserQueryRsp roleUserQuery(RoleUserQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        RoleUserQueryRsp rsp = new RoleUserQueryRsp();
        rsp.setUsers(new ArrayList<User>());
        String[] searchAttributes = {"userId","userName"};//查询条件
        List<UserRole> userRoles = null; 
                
        if (StringUtils.isEmpty(req.getSearchAttribute()) || StringUtils.isEmpty(req.getSearchContent())) {//不走查询条件
            if (StringUtils.isEmpty(req.getOrgId())) {
                userRoles = bomanager.loadBusinessObjects(UserRole.class, 0, Integer.MAX_VALUE, "roleId = :roleId","roleId",req.getRoleId()).getBusinessObjects();
            }else {
                userRoles = bomanager.loadBusinessObjects(UserRole.class, 0, Integer.MAX_VALUE, "roleId = :roleId and orgId =:orgId","roleId",req.getRoleId(),"orgId",req.getOrgId()).getBusinessObjects();
            }
        }else {
            if (Stream.of(searchAttributes).anyMatch(searchAttribute -> searchAttribute.equalsIgnoreCase(req.getSearchAttribute()))) {//验证查询条件
                BusinessObjectAggregate<UserRole> resultAggregate = bomanager.loadBusinessObjects(UserRole.class,req.getBegin(),req.getPageSize(), req.getSearchAttribute() + " like :searchContent",
                        "searchContent","%"+req.getSearchContent()+"%");
                if (resultAggregate != null) {
                    userRoles = resultAggregate.getBusinessObjects();
                }
            }else {
                throw new ALSException("900710");
            }
        }
        for (UserRole userRole : userRoles) {
            User user = new User();
            UserInfo userInfo = bomanager.keyLoadBusinessObject(UserInfo.class, userRole.getUserId());
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
}
