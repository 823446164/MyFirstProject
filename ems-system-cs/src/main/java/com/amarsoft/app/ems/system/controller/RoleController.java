/*
 * 文件名：RoleController.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：角色contoller接口类
 * 修改人：cmhuang
 * 修改时间：2020年5月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
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
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserRoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserRoleQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.roleinfodto.RoleInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.roleinfodto.RoleInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.roleinfodto.RoleInfoDtoSaveReq;
import com.amarsoft.app.ems.system.template.cs.dto.rolelistdto.RoleListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.rolelistdto.RoleListDtoQueryRsp;

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
    @PostMapping(value = "/role/getrolesbyuser", name="按用户查找角色", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserRoleQueryRsp>> userRoleQuery(@RequestBody @Valid RequestMessage<UserRoleQueryReq> reqMsg);
    
    //dto模板 
    @PostMapping(value = "/rolelistdto/query", name="角色信息List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RoleListDtoQueryRsp>> roleListDtoQuery(@RequestBody @Valid RequestMessage<RoleListDtoQueryReq> reqMsg);
    @PostMapping(value = "/roleuserlist/query", name="用户待引入的list查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>> roleUserListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoQueryReq> reqMsg);
    @PostMapping(value = "/roleuserintroducedlist/query", name="用户已引入的list查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>> RoleUserIntroducedListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoQueryReq> reqMsg);
    @PostMapping(value = "/roleinfodto/query", name="角色信息Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RoleInfoDtoQueryRsp>> roleInfoDtoQuery(@RequestBody @Valid RequestMessage<RoleInfoDtoQueryReq> reqMsg);
    @PostMapping(value = "/roleinfodto/save", name="角色信息Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> roleInfoDtoSave(@RequestBody @Valid RequestMessage<RoleInfoDtoSaveReq> reqMsg);
    @PostMapping(value = "/roleuserlist/save", name="用户引入list保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> roleUserListDtoSave(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoQueryReq> reqMsg);
    @PostMapping(value = "/roleuserlist/delete", name="用户引入list删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> roleUserListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoQueryReq> reqMsg);
    

}
