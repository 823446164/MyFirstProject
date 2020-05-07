package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDtoSaveReq;

/**
 * 目标职级申请InfoController接口
 * @author lding
 */
public interface EmployeeTargetRankInfoDtoController {
    @PostMapping(value = "/employeetargetrankinfodto/query", name="目标职级申请Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeTargetRankInfoDtoQueryRsp>> employeeTargetRankInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeTargetRankInfoDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeetargetrankinfodto/save", name="目标职级申请Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeTargetRankInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeTargetRankInfoDtoSaveReq> reqMsg);
}
