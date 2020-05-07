package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoDeleteReq;

/**
 * 员工项目经历ListService接口
 * @author lding
 */
public interface EmployeeProjectExpListDtoService {
    /**
     * 员工项目经历List查询
     * @param request
     * @return
     */
    public EmployeeProjectExpListDtoQueryRsp employeeProjectExpListDtoQuery(@Valid EmployeeProjectExpListDtoQueryReq employeeProjectExpListDtoQueryReq);

    /**
     * 员工项目经历List保存
     * @param request
     * @return
     */
    public void employeeProjectExpListDtoSave(@Valid EmployeeProjectExpListDtoSaveReq employeeProjectExpListDtoSaveReq);

    /**
     * 员工项目经历List删除
     * @param request
     * @return
     */
    public void employeeProjectExpListDtoDelete(@Valid EmployeeProjectExpListDtoDeleteReq employeeProjectExpListDtoDeleteReq);
}
