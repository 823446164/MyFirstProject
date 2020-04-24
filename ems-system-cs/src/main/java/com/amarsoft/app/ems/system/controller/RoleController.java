/**
 * 新增角色
 * @Author xxu1
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

public interface RoleController {
    @PostMapping(value = "/role/addrole", name="新增角色", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> addRole(@RequestBody @Valid RequestMessage<AddRoleReq> reqMsg);
    @PostMapping(value = "/role/updaterole", name="修改角色", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> updateRole(@RequestBody @Valid RequestMessage<UpdateRoleReq> reqMsg);
    @PostMapping(value = "/role/deleterole", name="删除角色", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> deleteRole(@RequestBody @Valid RequestMessage<DeleteRoleReq> reqMsg);
    @PostMapping(value = "/role/getallroles", name="查询所有角色", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RoleAllQueryRsp>> roleAllQuery(@RequestBody @Valid RequestMessage<RoleAllQueryReq> reqMsg);
    @PostMapping(value = "/role/getroles", name="查询角色", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RoleQueryRsp>> roleQuery(@RequestBody @Valid RequestMessage<RoleQueryReq> reqMsg);
    @PostMapping(value = "/role/roleauth", name="角色权限管理", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> roleAuth(@RequestBody @Valid RequestMessage<RoleAuthReq> reqMsg);
    @PostMapping(value = "/role/getrolesbylevel", name="按法人查找角色", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LevelRoleQueryRsp>> levelRoleQuery(@RequestBody @Valid RequestMessage<LevelRoleQueryReq> reqMsg);
    @PostMapping(value = "/role/getroleuser", name="查询角色关联用户", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RoleUserQueryRsp>> roleUserQuery(@RequestBody @Valid RequestMessage<RoleUserQueryReq> reqMsg);
}