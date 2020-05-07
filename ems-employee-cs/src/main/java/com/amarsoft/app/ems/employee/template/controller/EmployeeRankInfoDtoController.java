package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDtoSaveReq;

/**
 * 员工职级InfoController接口
 * @author lding
 */
public interface EmployeeRankInfoDtoController {
    @PostMapping(value = "/employeerankinfodto/query", name="员工职级Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankInfoDtoQueryRsp>> employeeRankInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankInfoDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeerankinfodto/save", name="员工职级Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankInfoDtoSaveReq> reqMsg);
}
