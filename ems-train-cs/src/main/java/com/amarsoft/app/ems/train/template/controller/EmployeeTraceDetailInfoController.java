package com.amarsoft.app.ems.train.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfoSaveReq;

/**
 * 追踪内容详情Controller接口
 * @author xphe
 */
public interface EmployeeTraceDetailInfoController {
    @PostMapping(value = "/employeetracedetailinfo/query", name="追踪内容详情查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeTraceDetailInfoQueryRsp>> employeeTraceDetailInfoQuery(@RequestBody @Valid RequestMessage<EmployeeTraceDetailInfoQueryReq> reqMsg);

    @PostMapping(value = "/employeetracedetailinfo/save", name="追踪内容详情保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeTraceDetailInfoSave(@RequestBody @Valid RequestMessage<EmployeeTraceDetailInfoSaveReq> reqMsg);
}
