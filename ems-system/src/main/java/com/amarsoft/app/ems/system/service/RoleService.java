/*
 * 文件名：RoleService.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：角色service接口类
 * 修改人：cmhuang
 * 修改时间：2020年5月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
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
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.roleinfodto.RoleInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.roleinfodto.RoleInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.roleinfodto.RoleInfoDtoSaveReq;
import com.amarsoft.app.ems.system.template.cs.dto.rolelistdto.RoleListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.rolelistdto.RoleListDtoQueryRsp;

/**
 * 角色服务的接口
 * @author cmhuang
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
    
    /**
     * 
     * Description: 角色信息List查询
     *
     * @param roleListDtoQueryReq
     * @return
     * @see
     */
    public RoleListDtoQueryRsp roleListDtoQuery(@Valid RoleListDtoQueryReq roleListDtoQueryReq);
    
    /**
     * 角色信息Info查询
     * @param request
     * @return
     */
    public RoleInfoDtoQueryRsp roleInfoDtoQuery(@Valid RoleInfoDtoQueryReq roleInfoDtoQueryReq);

    /**
     * 角色信息Info保存
     * @param request
     * @return
     */
    public void roleInfoDtoSave(@Valid RoleInfoDtoSaveReq roleInfoDtoSaveReq);
    
    /**
     * 用户待引入list查询
     * @param req
     * @return
     */
    public EmployeeInfoListDtoQueryRsp roleUserListDtoQuery(@Valid EmployeeInfoListDtoQueryReq req);
    
    /**
     * 用户已引入list查询
     * @param reqMsg
     * @return
     */
    public EmployeeInfoListDtoQueryRsp RoleUserIntroducedListDtoQuery(@Valid EmployeeInfoListDtoQueryReq req);
    
    /**
     * 用户引入list保存
     * @param reqMsg
     * @return
     */
    public void roleUserListDtoSave(@Valid EmployeeInfoListDtoQueryReq req);
    
    
    /**
     * 用户引入list删除
     * @param reqMsg
     * @return
     */
    public void roleUserListDtoDelete(@Valid EmployeeInfoListDtoQueryReq req);
    
    /**
     * 
     * Description: 角色状态更改
     *
     * @param req 角色info
     * @see
     */
    public void roleInfoStatusUpdate(@Valid RoleInfoDtoQueryReq req);
}
