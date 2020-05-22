package com.amarsoft.app.ems.system.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDtoSaveReq;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDtoDeleteReq;

/**
 * 二级部门信息ListController接口
 * @author zcluo
 */
public interface SecondLevelDeptListDtoController {

    @PostMapping(value = "/secondleveldeptlistdto/save", name="二级部门信息List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> secondLevelDeptListDtoSave(@RequestBody @Valid RequestMessage<SecondLevelDeptListDtoSaveReq> reqMsg);

    @PostMapping(value = "/secondleveldeptlistdto/delete", name="二级部门信息List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> secondLevelDeptListDtoDelete(@RequestBody @Valid RequestMessage<SecondLevelDeptListDtoDeleteReq> reqMsg);
}
