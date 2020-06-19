package com.amarsoft.app.ems.demo.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfoSaveReq;

/**
 * 员工职级调整申请详情InfoController接口
 * @author jfan5
 */
public interface EmployeeRankChangeApplyInfoController {
    @PostMapping(value = "/employeerankchangeapplyinfo/query", name="员工职级调整申请详情Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankChangeApplyInfoQueryRsp>> employeeRankChangeApplyInfoQuery(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyInfoQueryReq> reqMsg);

    @PostMapping(value = "/employeerankchangeapplyinfo/save", name="员工职级调整申请详情Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeApplyInfoSave(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyInfoSaveReq> reqMsg);
}
