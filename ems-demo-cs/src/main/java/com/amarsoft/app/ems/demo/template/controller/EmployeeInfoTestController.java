package com.amarsoft.app.ems.demo.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTestQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTestQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTestSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeRankApplySaveReq;

/**
 * 员工信息InfoController接口
 * @author jfan5
 */
public interface EmployeeInfoTestController {
    @PostMapping(value = "/employeeinfotest/query", name="员工信息Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeInfoTestQueryRsp>> employeeInfoTestQuery(@RequestBody @Valid RequestMessage<EmployeeInfoTestQueryReq> reqMsg);

    @PostMapping(value = "/employeeinfotest/save", name="员工信息Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeInfoTestSave(@RequestBody @Valid RequestMessage<EmployeeInfoTestSaveReq> reqMsg);
    
    @PostMapping(value = "/employeelisttest/rankApply", name="员工信息职级调整申请", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeListTestRankApply(@RequestBody @Valid RequestMessage<EmployeeInfoTestSaveReq> reqMsg);

}
