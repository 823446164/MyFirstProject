package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoDeleteReq;

/**
 * 员工职级ListController接口
 * @author lding
 */
public interface EmployeeRankListDtoController {
    @PostMapping(value = "/employeeranklistdto/query", name="员工职级List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankListDtoQueryRsp>> employeeRankListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeeranklistdto/save", name="员工职级List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankListDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankListDtoSaveReq> reqMsg);

    @PostMapping(value = "/employeeranklistdto/delete", name="员工职级List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeRankListDtoDeleteReq> reqMsg);
}
