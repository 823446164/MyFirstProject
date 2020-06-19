package com.amarsoft.app.ems.demo.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeRankApplySaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestDeleteReq;

/**
 * 员工信息ListController接口
 * @author jfan5
 */
public interface EmployeeListTestController {
    @PostMapping(value = "/employeelisttest/query", name="员工信息List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeListTestQueryRsp>> employeeListTestQuery(@RequestBody @Valid RequestMessage<EmployeeListTestQueryReq> reqMsg);

    @PostMapping(value = "/employeelisttest/save", name="员工信息List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeListTestSave(@RequestBody @Valid RequestMessage<EmployeeListTestSaveReq> reqMsg);

    @PostMapping(value = "/employeelisttest/delete", name="员工信息List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeListTestDelete(@RequestBody @Valid RequestMessage<EmployeeListTestDeleteReq> reqMsg);

}
