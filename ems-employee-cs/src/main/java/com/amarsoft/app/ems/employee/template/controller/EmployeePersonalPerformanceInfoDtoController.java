package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDtoSaveReq;

/**
 * 员工项目经历个人表现InfoController接口
 * @author lding
 */
public interface EmployeePersonalPerformanceInfoDtoController {
    @PostMapping(value = "/employeepersonalperformanceinfodto/query", name="员工项目经历个人表现Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeePersonalPerformanceInfoDtoQueryRsp>> employeePersonalPerformanceInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceInfoDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeepersonalperformanceinfodto/save", name="员工项目经历个人表现Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceInfoDtoSaveReq> reqMsg);
}
