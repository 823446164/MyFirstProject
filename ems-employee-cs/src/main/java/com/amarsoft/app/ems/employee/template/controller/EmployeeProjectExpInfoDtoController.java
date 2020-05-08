package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDtoSaveReq;

/**
 * 员工项目经历InfoController接口
 * @author lding
 */
public interface EmployeeProjectExpInfoDtoController {
    @PostMapping(value = "/employeeprojectexpinfodto/query", name="员工项目经历Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeProjectExpInfoDtoQueryRsp>> employeeProjectExpInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeProjectExpInfoDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeeprojectexpinfodto/save", name="员工项目经历Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExpInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeProjectExpInfoDtoSaveReq> reqMsg);
}
