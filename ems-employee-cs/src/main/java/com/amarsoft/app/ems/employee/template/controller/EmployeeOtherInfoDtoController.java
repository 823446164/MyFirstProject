package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDtoSaveReq;

/**
 * 员工其他说明InfoController接口
 * @author lding
 */
public interface EmployeeOtherInfoDtoController {
    @PostMapping(value = "/employeeotherinfodto/query", name="员工其他说明Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeOtherInfoDtoQueryRsp>> employeeOtherInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeOtherInfoDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeeotherinfodto/save", name="员工其他说明Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeOtherInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeOtherInfoDtoSaveReq> reqMsg);
}
