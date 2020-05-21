package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoSaveReq;

/**
 * 员工信息InfoController接口
 * @author lding
 */
public interface EmployeeInfoDtoController {
    @PostMapping(value = "/employeeinfodto/query", name="员工信息Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeInfoDtoQueryRsp>> employeeInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeInfoDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeeinfodto/save", name="员工信息Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeInfoDtoSaveReq> reqMsg);
   
}
