package com.amarsoft.app.ems.demo.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfoSaveReq;

/**
 * 员工项目经历个人表现InfoController接口
 * @author jfan5
 */
public interface EmployeePersonalPerformanceInfoController {
    @PostMapping(value = "/employeepersonalperformanceinfo/query", name="员工项目经历个人表现Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeePersonalPerformanceInfoQueryRsp>> employeePersonalPerformanceInfoQuery(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceInfoQueryReq> reqMsg);

    @PostMapping(value = "/employeepersonalperformanceinfo/save", name="员工项目经历个人表现Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceInfoSave(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceInfoSaveReq> reqMsg);
}
