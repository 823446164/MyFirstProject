package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDtoSaveReq;

/**
 * 员工职级调整申请详情InfoController接口
 * @author xucheng
 */
public interface EmployeeRankChangeApplyInfoDtoController {
    @PostMapping(value = "/employeerankchangeapplyinfodto/query", name="员工职级调整申请详情Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankChangeApplyInfoDtoQueryRsp>> employeeRankChangeApplyInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyInfoDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeerankchangeapplyinfodto/save", name="员工职级调整申请详情Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeApplyInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyInfoDtoSaveReq> reqMsg);
}
