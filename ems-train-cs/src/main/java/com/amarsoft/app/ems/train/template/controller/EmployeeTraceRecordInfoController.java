package com.amarsoft.app.ems.train.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfoSaveReq;

/**
 * 追踪记录详情Controller接口
 * @author xphe
 */
public interface EmployeeTraceRecordInfoController {
    @PostMapping(value = "/employeetracerecordinfo/query", name="追踪记录详情查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeTraceRecordInfoQueryRsp>> employeeTraceRecordInfoQuery(@RequestBody @Valid RequestMessage<EmployeeTraceRecordInfoQueryReq> reqMsg);

    @PostMapping(value = "/employeetracerecordinfo/save", name="追踪记录详情保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeTraceRecordInfoSave(@RequestBody @Valid RequestMessage<EmployeeTraceRecordInfoSaveReq> reqMsg);
}
