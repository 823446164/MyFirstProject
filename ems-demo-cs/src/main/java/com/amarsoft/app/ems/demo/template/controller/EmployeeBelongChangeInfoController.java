package com.amarsoft.app.ems.demo.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfoSaveReq;

/**
 * 团队调整申请InfoController接口
 * @author jfan5
 */
public interface EmployeeBelongChangeInfoController {
    @PostMapping(value = "/employeebelongchangeinfo/query", name="团队调整申请Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeBelongChangeInfoQueryRsp>> employeeBelongChangeInfoQuery(@RequestBody @Valid RequestMessage<EmployeeBelongChangeInfoQueryReq> reqMsg);

    @PostMapping(value = "/employeebelongchangeinfo/save", name="团队调整申请Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeInfoSave(@RequestBody @Valid RequestMessage<EmployeeBelongChangeInfoSaveReq> reqMsg);
}
