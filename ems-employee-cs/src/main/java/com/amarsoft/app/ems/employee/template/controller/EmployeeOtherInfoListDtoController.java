package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoDeleteReq;

/**
 * 员工其他说明ListController接口
 * @author lding
 */
public interface EmployeeOtherInfoListDtoController {
    @PostMapping(value = "/employeeotherinfolistdto/query", name="员工其他说明List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeOtherInfoListDtoQueryRsp>> employeeOtherInfoListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeOtherInfoListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeeotherinfolistdto/save", name="员工其他说明List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeOtherInfoListDtoSave(@RequestBody @Valid RequestMessage<EmployeeOtherInfoListDtoSaveReq> reqMsg);

    @PostMapping(value = "/employeeotherinfolistdto/delete", name="员工其他说明List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeOtherInfoListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeOtherInfoListDtoDeleteReq> reqMsg);
}
