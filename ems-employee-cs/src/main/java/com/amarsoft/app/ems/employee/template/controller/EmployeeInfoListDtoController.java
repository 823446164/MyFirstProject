package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoStatusUpdateReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.EmployeeListByUserQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.EmployeeListByUserQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoReq;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoRsp;

/**
 * 员工信息ListController接口
 * @author lding
 */
public interface EmployeeInfoListDtoController {
    @PostMapping(value = "/employeeinfolistdto/query", name="员工信息List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>> employeeInfoListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeeinfolistdto/save", name="员工信息List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeInfoListDtoSave(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoSaveReq> reqMsg);

    @PostMapping(value = "/employeeinfolistdto/delete", name="员工信息List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeInfoListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoDeleteReq> reqMsg);
    
    @PostMapping(value = "/employeelistbyuser/query", name="根据用户权限查询员工信息List接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeListByUserQueryRsp>> employeeListByUserQuery(@RequestBody @Valid RequestMessage<EmployeeListByUserQueryReq> reqMsg);
    
    @PostMapping(value = "/employeelistbyemployeeno/query", name="按条件查询员工信息List接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeListByEmplNoRsp>> employeeListByEmployeeNoQuery(@RequestBody @Valid RequestMessage<EmployeeListByEmplNoReq> reqMsg);

    @PostMapping(value = "/employeeinfodto/saveStatus", name="员工状态置为离职", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeInfoDtoStatusSave(@RequestBody @Valid RequestMessage<EmployeeInfoStatusUpdateReq> reqMsg);

}
