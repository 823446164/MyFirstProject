package com.amarsoft.app.ems.employee.template.service;

import java.util.Map;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDtoSaveReq;

/**
 * 员工项目经历InfoService接口
 * @author lding
 */
public interface EmployeeProjectExpInfoDtoService {
    /**
     * 员工项目经历Info查询
     * @param request
     * @return
     */
    public EmployeeProjectExpInfoDtoQueryRsp employeeProjectExpInfoDtoQuery(@Valid EmployeeProjectExpInfoDtoQueryReq employeeProjectExpInfoDtoQueryReq);

    /**
     * 员工项目经历Info保存
     * @param request
     * @return
     */
    public Map<String,String> employeeProjectExpInfoDtoSave(@Valid EmployeeProjectExpInfoDtoSaveReq employeeProjectExpInfoDtoSaveReq);
}
