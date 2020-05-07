package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDtoSaveReq;

/**
 * 员工其他说明InfoService接口
 * @author lding
 */
public interface EmployeeOtherInfoDtoService {
    /**
     * 员工其他说明Info查询
     * @param request
     * @return
     */
    public EmployeeOtherInfoDtoQueryRsp employeeOtherInfoDtoQuery(@Valid EmployeeOtherInfoDtoQueryReq employeeOtherInfoDtoQueryReq);

    /**
     * 员工其他说明Info保存
     * @param request
     * @return
     */
    public void employeeOtherInfoDtoSave(@Valid EmployeeOtherInfoDtoSaveReq employeeOtherInfoDtoSaveReq);
}
