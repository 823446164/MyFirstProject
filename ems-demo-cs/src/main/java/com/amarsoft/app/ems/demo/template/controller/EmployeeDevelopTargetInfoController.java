package com.amarsoft.app.ems.demo.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfoSaveReq;

/**
 * 员工成长目标跟踪InfoController接口
 * @author jfan5
 */
public interface EmployeeDevelopTargetInfoController {
    @PostMapping(value = "/employeedeveloptargetinfo/query", name="员工成长目标跟踪Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeDevelopTargetInfoQueryRsp>> employeeDevelopTargetInfoQuery(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetInfoQueryReq> reqMsg);

    @PostMapping(value = "/employeedeveloptargetinfo/save", name="员工成长目标跟踪Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetInfoSave(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetInfoSaveReq> reqMsg);
}
