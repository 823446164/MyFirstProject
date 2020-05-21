package com.amarsoft.app.ems.system.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.system.template.cs.dto.roleinfodto.RoleInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.roleinfodto.RoleInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.roleinfodto.RoleInfoDtoSaveReq;

/**
 * 角色信息InfoController接口
 * @author cmhuang
 */
public interface RoleInfoDtoController {
    @PostMapping(value = "/roleinfodto/query", name="角色信息Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RoleInfoDtoQueryRsp>> roleInfoDtoQuery(@RequestBody @Valid RequestMessage<RoleInfoDtoQueryReq> reqMsg);

    @PostMapping(value = "/roleinfodto/save", name="角色信息Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> roleInfoDtoSave(@RequestBody @Valid RequestMessage<RoleInfoDtoSaveReq> reqMsg);
}
