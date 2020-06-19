package com.amarsoft.app.ems.demo.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListDeleteReq;

/**
 * 员工项目经历个人表现ListController接口
 * @author jfan5
 */
public interface EmployeePersonalPerformanceListController {
    @PostMapping(value = "/employeepersonalperformancelist/query", name="员工项目经历个人表现List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeePersonalPerformanceListQueryRsp>> employeePersonalPerformanceListQuery(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListQueryReq> reqMsg);

    @PostMapping(value = "/employeepersonalperformancelist/save", name="员工项目经历个人表现List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceListSave(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListSaveReq> reqMsg);

    @PostMapping(value = "/employeepersonalperformancelist/delete", name="员工项目经历个人表现List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceListDelete(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListDeleteReq> reqMsg);
}
