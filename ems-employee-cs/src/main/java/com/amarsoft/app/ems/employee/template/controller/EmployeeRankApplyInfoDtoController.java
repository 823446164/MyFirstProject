package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoSaveReq;

/**
 * 员工职级申请InfoController接口
 * @author lding
 */
public interface EmployeeRankApplyInfoDtoController {
    @PostMapping(value = "/employeerankapplyinfodto/query", name="员工职级申请Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankApplyInfoDtoQueryRsp>> employeeRankApplyInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankApplyInfoDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeerankapplyinfodto/save", name="员工职级申请Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankApplyInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankApplyInfoDtoSaveReq> reqMsg);
}
