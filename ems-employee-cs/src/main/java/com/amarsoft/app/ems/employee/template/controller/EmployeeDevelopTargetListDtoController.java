package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoDeleteReq;

/**
 * 员工成长目标跟踪ListController接口
 * @author lding
 */
public interface EmployeeDevelopTargetListDtoController {
    @PostMapping(value = "/employeedeveloptargetlistdto/query", name="员工成长目标跟踪List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeDevelopTargetListDtoQueryRsp>> employeeDevelopTargetListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeedeveloptargetlistdto/save", name="员工成长目标跟踪List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetListDtoSave(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListDtoSaveReq> reqMsg);

    @PostMapping(value = "/employeedeveloptargetlistdto/delete", name="员工成长目标跟踪List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListDtoDeleteReq> reqMsg);
}
