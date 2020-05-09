package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryRsp;

/**
 * 员工职级及标签查询
 * @author xszhou
 *
 */
public interface EmployeeRankLableController {
    @PostMapping(value = "/employeeranklable/query", name="员工职级标签查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankListDtoQueryRsp>> employeeRankListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankListDtoQueryReq> reqMsg);
     
}
