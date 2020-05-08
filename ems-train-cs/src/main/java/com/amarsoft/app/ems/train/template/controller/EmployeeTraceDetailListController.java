package com.amarsoft.app.ems.train.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListDeleteReq;

/**
 * 追踪内容列表Controller接口
 * @author xphe
 */
public interface EmployeeTraceDetailListController {
    @PostMapping(value = "/employeetracedetaillist/query", name="追踪内容列表查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeTraceDetailListQueryRsp>> employeeTraceDetailListQuery(@RequestBody @Valid RequestMessage<EmployeeTraceDetailListQueryReq> reqMsg);

    @PostMapping(value = "/employeetracedetaillist/save", name="追踪内容列表保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeTraceDetailListSave(@RequestBody @Valid RequestMessage<EmployeeTraceDetailListSaveReq> reqMsg);

    @PostMapping(value = "/employeetracedetaillist/delete", name="追踪内容列表删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeTraceDetailListDelete(@RequestBody @Valid RequestMessage<EmployeeTraceDetailListDeleteReq> reqMsg);
}
