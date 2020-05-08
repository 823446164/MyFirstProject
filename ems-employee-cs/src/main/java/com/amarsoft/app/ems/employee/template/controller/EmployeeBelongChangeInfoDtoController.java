package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoSaveReq;

/**
 * 团队调整申请InfoController接口
 * @author lding
 */
public interface EmployeeBelongChangeInfoDtoController {
    @PostMapping(value = "/employeebelongchangeinfodto/query", name="团队调整申请Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeBelongChangeInfoDtoQueryRsp>> employeeBelongChangeInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeBelongChangeInfoDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeebelongchangeinfodto/save", name="团队调整申请Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeBelongChangeInfoDtoSaveReq> reqMsg);
}
