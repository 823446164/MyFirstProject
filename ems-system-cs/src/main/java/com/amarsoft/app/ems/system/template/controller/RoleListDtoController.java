package com.amarsoft.app.ems.system.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.system.template.cs.dto.rolelistdto.RoleListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.rolelistdto.RoleListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.rolelistdto.RoleListDtoSaveReq;
import com.amarsoft.app.ems.system.template.cs.dto.rolelistdto.RoleListDtoDeleteReq;

/**
 * 角色信息ListController接口
 * @author cmhuang
 */
public interface RoleListDtoController {
    @PostMapping(value = "/rolelistdto/query", name="角色信息List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RoleListDtoQueryRsp>> roleListDtoQuery(@RequestBody @Valid RequestMessage<RoleListDtoQueryReq> reqMsg);

    @PostMapping(value = "/rolelistdto/save", name="角色信息List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> roleListDtoSave(@RequestBody @Valid RequestMessage<RoleListDtoSaveReq> reqMsg);

    @PostMapping(value = "/rolelistdto/delete", name="角色信息List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> roleListDtoDelete(@RequestBody @Valid RequestMessage<RoleListDtoDeleteReq> reqMsg);
}
