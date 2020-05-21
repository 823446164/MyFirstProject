package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoSaveReq;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;

/**
 * 员工职级调整申请ListController接口
 * @author xucheng
 */
public interface EmployeeRankChangeApplyListDtoController {
    @PostMapping(value = "/employeerankchangeapplylistdto/query", name="员工职级调整申请List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankChangeApplyListDtoQueryRsp>> employeeRankChangeApplyListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeerankchangeapplylistdto/save", name="员工职级调整申请List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeApplyListDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyListDtoSaveReq> reqMsg);

    @PostMapping(value = "/employeerankchangeapplylistdto/delete", name="员工职级调整申请List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeApplyListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyListDtoDeleteReq> reqMsg);
}
