package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoDeleteReq;

/**
 * 团队调整申请ListController接口
 * @author lding
 */
public interface EmployeeBelongChangeListDtoController {
    @PostMapping(value = "/employeebelongchangelistdto/query", name="团队调整申请List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeBelongChangeListDtoQueryRsp>> employeeBelongChangeListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeBelongChangeListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeebelongchangelistdto/save", name="团队调整申请List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeListDtoSave(@RequestBody @Valid RequestMessage<EmployeeBelongChangeListDtoSaveReq> reqMsg);

    @PostMapping(value = "/employeebelongchangelistdto/delete", name="团队调整申请List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeBelongChangeListDtoDeleteReq> reqMsg);
}
