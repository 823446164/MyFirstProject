package com.amarsoft.app.ems.system.service;

import com.amarsoft.app.ems.system.cs.dto.addrole.AddRoleReq;
import com.amarsoft.app.ems.system.cs.dto.deleterole.DeleteRoleReq;
import com.amarsoft.app.ems.system.cs.dto.levelrolequery.LevelRoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelrolequery.LevelRoleQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleauth.RoleAuthReq;
import com.amarsoft.app.ems.system.cs.dto.rolequery.RoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rolequery.RoleQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleuserquery.RoleUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.roleuserquery.RoleUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.updaterole.UpdateRoleReq;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserRoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserRoleQueryRsp;

/**
 * 角色服务的接口
 * @author xjzhao
 */
public interface RoleService {
    
    /**
     * 新增角色
     * @param req
     */
    public void addRole(AddRoleReq req);
    
    /**
     * 修改角色
     * @param req
     * @return 是否更改了权限
     */
    public boolean updateRole(UpdateRoleReq req);
    
    /**
     * 删除角色
     * @param req
     */
    public void deleteRole(DeleteRoleReq req);
    
    /**
     * 查询角色
     * @param req
     * @return
     */
    public RoleQueryRsp getRoles(RoleQueryReq req);
    
    /**
     * 角色权限管理
     * @param req
     * @return 是否更改了权限
     */
    public boolean roleAuth(RoleAuthReq req);
    
    /**
     * 清理角色缓存
     * @param roleId
     */
    public void cleanRoleCache(String roleId);
    /**
     * 角色权限管理
     * @param roleAllQueryReq 
     * @param null
     * @return 是否更改了权限
     */
    public RoleAllQueryRsp roleAllQuery(RoleAllQueryReq req);
    /**
     * 按法人机构查询不同机构级别的角色
     * @param levelRoleQueryReq 
     * @param orgService 
     * @param roleService 
     * @param null
     * @return 是否更改了权限
     */
    public LevelRoleQueryRsp levelRoleQuery(LevelRoleQueryReq req, RoleService roleService, OrgService orgService);
    /**
     * 查询角色关联用户
     * @param roleUserQueryReq
     * @return
     */
    public RoleUserQueryRsp roleUserQuery(RoleUserQueryReq roleUserQueryReq);
    
    /**
     * Description: 查询用户对应的角色组<br>
     * ${tags}
     * @see
     */
    public UserRoleQueryRsp userRoleQuery(UserRoleQueryReq req);
}
