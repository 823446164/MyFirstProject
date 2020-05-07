package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoDeleteReq;

/**
 * 目标职级申请ListController接口
 * @author lding
 */
public interface EmployeeTargetRankListDtoController {
    @PostMapping(value = "/employeetargetranklistdto/query", name="目标职级申请List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeTargetRankListDtoQueryRsp>> employeeTargetRankListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeTargetRankListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeetargetranklistdto/save", name="目标职级申请List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeTargetRankListDtoSave(@RequestBody @Valid RequestMessage<EmployeeTargetRankListDtoSaveReq> reqMsg);

    @PostMapping(value = "/employeetargetranklistdto/delete", name="目标职级申请List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeTargetRankListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeTargetRankListDtoDeleteReq> reqMsg);
}
