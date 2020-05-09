/*
 * 文件名：EmployeeLabelServiceImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：员工能力标签查询service层
 * 修改人：xszhou
 * 修改时间：2020/5/9
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
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
     * Description: <br>
     * 1、员工对应历史职级标签查询<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     *
     * ${tags}
     * @see
     */
	public EmployeeAbilityLabelsRsp employeeLabelQuery(@Valid EmployeeAbilityLabelsReq req);
}
