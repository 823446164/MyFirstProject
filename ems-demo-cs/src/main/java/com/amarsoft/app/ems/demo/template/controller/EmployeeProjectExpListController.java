package com.amarsoft.app.ems.demo.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListDeleteReq;

/**
 * 员工项目经历ListController接口
 * @author jfan5
 */
public interface EmployeeProjectExpListController {
    @PostMapping(value = "/employeeprojectexplist/query", name="员工项目经历List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeProjectExpListQueryRsp>> employeeProjectExpListQuery(@RequestBody @Valid RequestMessage<EmployeeProjectExpListQueryReq> reqMsg);

    @PostMapping(value = "/employeeprojectexplist/save", name="员工项目经历List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExpListSave(@RequestBody @Valid RequestMessage<EmployeeProjectExpListSaveReq> reqMsg);

    @PostMapping(value = "/employeeprojectexplist/delete", name="员工项目经历List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExpListDelete(@RequestBody @Valid RequestMessage<EmployeeProjectExpListDeleteReq> reqMsg);
}
