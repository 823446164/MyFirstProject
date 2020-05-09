package com.amarsoft.app.ems.employee.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amarsoft.app.ems.employee.employee.controller.EmployeeLabelController;
import com.amarsoft.app.ems.employee.template.controller.EmployeeRankLableController;

/**
 * 员工历史最新职级对应标签查询
 * @author xszhou
 *
 */
@Component
@FeignClient(value = "employee-server")
@RequestMapping("/employee")
public interface EmployeeRankLableClient extends EmployeeLabelController{
	
}
