package com.amarsoft.app.ems.demo.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListDeleteReq;

/**
 * 员工成长目标跟踪ListController接口
 * @author jfan5
 */
public interface EmployeeDevelopTargetListController {
    @PostMapping(value = "/employeedeveloptargetlist/query", name="员工成长目标跟踪List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeDevelopTargetListQueryRsp>> employeeDevelopTargetListQuery(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListQueryReq> reqMsg);

    @PostMapping(value = "/employeedeveloptargetlist/save", name="员工成长目标跟踪List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetListSave(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListSaveReq> reqMsg);

    @PostMapping(value = "/employeedeveloptargetlist/delete", name="员工成长目标跟踪List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetListDelete(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListDeleteReq> reqMsg);
}
