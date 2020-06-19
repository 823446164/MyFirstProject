package com.amarsoft.app.ems.demo.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfoSaveReq;

/**
 * 员工项目经历InfoController接口
 * @author jfan5
 */
public interface EmployeeProjectExpInfoController {
    @PostMapping(value = "/employeeprojectexpinfo/query", name="员工项目经历Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeProjectExpInfoQueryRsp>> employeeProjectExpInfoQuery(@RequestBody @Valid RequestMessage<EmployeeProjectExpInfoQueryReq> reqMsg);

    @PostMapping(value = "/employeeprojectexpinfo/save", name="员工项目经历Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExpInfoSave(@RequestBody @Valid RequestMessage<EmployeeProjectExpInfoSaveReq> reqMsg);
}
