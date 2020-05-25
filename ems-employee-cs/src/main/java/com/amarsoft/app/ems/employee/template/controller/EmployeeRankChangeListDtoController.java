package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoSaveReq;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;

/**
 * 员工职级调整情况ListController接口
 * @author xucheng
 */
public interface EmployeeRankChangeListDtoController {
    @PostMapping(value = "/employeerankchangelistdto/query", name="员工职级调整情况List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankChangeListDtoQueryRsp>> employeeRankChangeListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankChangeListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeerankchangelistdto/save", name="员工职级调整情况List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeListDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankChangeListDtoSaveReq> reqMsg);

    @PostMapping(value = "/employeerankchangelistdto/delete", name="员工职级调整情况List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeRankChangeListDtoDeleteReq> reqMsg);
}
