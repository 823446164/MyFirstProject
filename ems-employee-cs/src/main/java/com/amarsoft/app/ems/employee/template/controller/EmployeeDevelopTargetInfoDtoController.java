package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoSaveReq;

/**
 * 员工成长目标跟踪InfoController接口
 * @author lding
 */
public interface EmployeeDevelopTargetInfoDtoController {
    @PostMapping(value = "/employeedeveloptargetinfodto/query", name="员工成长目标跟踪Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeDevelopTargetInfoDtoQueryRsp>> employeeDevelopTargetInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetInfoDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeedeveloptargetinfodto/save", name="员工成长目标跟踪Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetInfoDtoSaveReq> reqMsg);
}
