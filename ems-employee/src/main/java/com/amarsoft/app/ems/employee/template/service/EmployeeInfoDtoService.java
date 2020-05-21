package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;

import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoSaveReq;

/**
 * 员工信息InfoService接口
 * @author lding
 */
public interface EmployeeInfoDtoService {
    /**
     * 员工信息Info查询
     * @param request
     * @return
     */
    public EmployeeInfoDtoQueryRsp employeeInfoDtoQuery(@Valid EmployeeInfoDtoQueryReq employeeInfoDtoQueryReq);

    /**
     * 员工信息Info保存
     * @param request
     * @return
     */
    public void employeeInfoDtoSave(@Valid EmployeeInfoDtoSaveReq employeeInfoDtoSaveReq);
    
}
