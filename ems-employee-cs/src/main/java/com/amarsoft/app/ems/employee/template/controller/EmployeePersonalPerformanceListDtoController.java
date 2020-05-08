package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoDeleteReq;

/**
 * 员工项目经历个人表现ListController接口
 * @author lding
 */
public interface EmployeePersonalPerformanceListDtoController {
    @PostMapping(value = "/employeepersonalperformancelistdto/query", name="员工项目经历个人表现List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeePersonalPerformanceListDtoQueryRsp>> employeePersonalPerformanceListDtoQuery(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeepersonalperformancelistdto/save", name="员工项目经历个人表现List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceListDtoSave(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListDtoSaveReq> reqMsg);

    @PostMapping(value = "/employeepersonalperformancelistdto/delete", name="员工项目经历个人表现List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceListDtoDelete(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListDtoDeleteReq> reqMsg);
}
