package com.amarsoft.app.ems.train.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListDeleteReq;

/**
 * 追踪记录列表Controller接口
 * @author xphe
 */
public interface EmployeeTraceRecordListController {
    @PostMapping(value = "/employeetracerecordlist/query", name="追踪记录列表查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeTraceRecordListQueryRsp>> employeeTraceRecordListQuery(@RequestBody @Valid RequestMessage<EmployeeTraceRecordListQueryReq> reqMsg);

    @PostMapping(value = "/employeetracerecordlist/save", name="追踪记录列表保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeTraceRecordListSave(@RequestBody @Valid RequestMessage<EmployeeTraceRecordListSaveReq> reqMsg);

    @PostMapping(value = "/employeetracerecordlist/delete", name="追踪记录列表删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeTraceRecordListDelete(@RequestBody @Valid RequestMessage<EmployeeTraceRecordListDeleteReq> reqMsg);
}
