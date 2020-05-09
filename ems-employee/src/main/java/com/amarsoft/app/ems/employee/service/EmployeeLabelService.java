package com.amarsoft.app.ems.employee.service;
/**
 * 员工能力标签查询
 * @author xszhou
 *
 */

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.employee.cs.dto.employeeabilitylabels.EmployeeAbilityLabelsReq;
import com.amarsoft.app.ems.employee.employee.cs.dto.employeeabilitylabels.EmployeeAbilityLabelsRsp;

public interface EmployeeLabelService {
	/**
	 * 员工标签查询
	 * @param req
	 * @return
	 */
	public EmployeeAbilityLabelsRsp employeeLabelQuery(@Valid EmployeeAbilityLabelsReq req);
}
