/**
 * 员工能力标签查询
 * @Author lding
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.employee.employee.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.employee.employee.cs.dto.employeeabilitylabels.EmployeeAbilityLabelsReq;
import com.amarsoft.app.ems.employee.employee.cs.dto.employeeabilitylabels.EmployeeAbilityLabelsRsp;
import org.springframework.web.bind.annotation.PostMapping;

public interface EmployeeLabelController {
    @PostMapping(value = "/ability/employeelabels", name="员工能力标签查询", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeAbilityLabelsRsp>> employeeAbilityLabels(@RequestBody @Valid RequestMessage<EmployeeAbilityLabelsReq> reqMsg);

}