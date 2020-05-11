package com.amarsoft.app.ems.system.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDtoSaveReq;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDtoDeleteReq;

/**
 * 搜索二级部门信息ListController接口
 * @author zcluo
 */
public interface SearchSecondLevelDeptListDtoController {
    @PostMapping(value = "/searchsecondleveldeptlistdto/save", name="搜索二级部门信息List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> searchSecondLevelDeptListDtoSave(@RequestBody @Valid RequestMessage<SearchSecondLevelDeptListDtoSaveReq> reqMsg);

 }
