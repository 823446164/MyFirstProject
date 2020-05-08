package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoDeleteReq;

/**
 * 员工职级申请ListController接口
 * @author lding
 */
public interface EmployeeRankApplyListDtoController {
    @PostMapping(value = "/employeerankapplylistdto/query", name="员工职级申请List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankApplyListDtoQueryRsp>> employeeRankApplyListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankApplyListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeerankapplylistdto/save", name="员工职级申请List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankApplyListDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankApplyListDtoSaveReq> reqMsg);

    @PostMapping(value = "/employeerankapplylistdto/delete", name="员工职级申请List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankApplyListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeRankApplyListDtoDeleteReq> reqMsg);
}
