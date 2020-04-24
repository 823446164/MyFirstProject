/**
 * 新增角色组
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
import com.amarsoft.app.ems.system.cs.dto.addgroup.AddGroupReq;
import com.amarsoft.app.ems.system.cs.dto.deletegroup.DeleteGroupReq;
import com.amarsoft.app.ems.system.cs.dto.groupallquery.GroupAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.groupquery.GroupQueryReq;
import com.amarsoft.app.ems.system.cs.dto.groupquery.GroupQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.grouprole.GroupRoleReq;
import com.amarsoft.app.ems.system.cs.dto.groupuserquery.GroupUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.groupuserquery.GroupUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.levelgroupquery.LevelGroupQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelgroupquery.LevelGroupQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.updategroup.UpdateGroupReq;

public interface GroupController {
    @PostMapping(value = "/group/addgroup", name="新增角色组", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> addGroup(@RequestBody @Valid RequestMessage<AddGroupReq> reqMsg);
    @PostMapping(value = "/group/updategroup", name="修改角色组", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> updateGroup(@RequestBody @Valid RequestMessage<UpdateGroupReq> reqMsg);
    @PostMapping(value = "/group/deletegroup", name="删除角色组", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> deleteGroup(@RequestBody @Valid RequestMessage<DeleteGroupReq> reqMsg);
    @PostMapping(value = "/group/getallgroups", name="查询所有角色组", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<GroupAllQueryRsp>> groupAllQuery(@RequestBody @Valid RequestMessage<Object> reqMsg);
    @PostMapping(value = "/group/getgroups", name="查询角色组", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<GroupQueryRsp>> groupQuery(@RequestBody @Valid RequestMessage<GroupQueryReq> reqMsg);
    @PostMapping(value = "/group/getgroupuser", name="查询角色组关联用户", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<GroupUserQueryRsp>> groupUserQuery(@RequestBody @Valid RequestMessage<GroupUserQueryReq> reqMsg);
    @PostMapping(value = "/group/grouprole", name="角色组角色管理", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> groupRole(@RequestBody @Valid RequestMessage<GroupRoleReq> reqMsg);
    @PostMapping(value = "/group/getgroupsbylevel", name="按法人查找角色组", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LevelGroupQueryRsp>> levelGroupQuery(@RequestBody @Valid RequestMessage<LevelGroupQueryReq> reqMsg);
}
