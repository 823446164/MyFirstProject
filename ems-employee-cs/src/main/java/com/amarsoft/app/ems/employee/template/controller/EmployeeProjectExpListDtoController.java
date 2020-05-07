package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoDeleteReq;

/**
 * 员工项目经历ListController接口
 * @author lding
 */
public interface EmployeeProjectExpListDtoController {
    @PostMapping(value = "/employeeprojectexplistdto/query", name="员工项目经历List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeProjectExpListDtoQueryRsp>> employeeProjectExpListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeProjectExpListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeeprojectexplistdto/save", name="员工项目经历List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExpListDtoSave(@RequestBody @Valid RequestMessage<EmployeeProjectExpListDtoSaveReq> reqMsg);

    @PostMapping(value = "/employeeprojectexplistdto/delete", name="员工项目经历List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExpListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeProjectExpListDtoDeleteReq> reqMsg);
}
