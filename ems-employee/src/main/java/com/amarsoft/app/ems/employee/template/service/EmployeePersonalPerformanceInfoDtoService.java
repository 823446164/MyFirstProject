package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDtoSaveReq;

/**
 * 员工项目经历个人表现InfoService接口
 * @author lding
 */
public interface EmployeePersonalPerformanceInfoDtoService {
    /**
     * 员工项目经历个人表现Info查询
     * @param request
     * @return
     */
    public EmployeePersonalPerformanceInfoDtoQueryRsp employeePersonalPerformanceInfoDtoQuery(@Valid EmployeePersonalPerformanceInfoDtoQueryReq employeePersonalPerformanceInfoDtoQueryReq);

    /**
     * 员工项目经历个人表现Info保存
     * @param request
     * @return
     */
    public void employeePersonalPerformanceInfoDtoSave(@Valid EmployeePersonalPerformanceInfoDtoSaveReq employeePersonalPerformanceInfoDtoSaveReq);
}
