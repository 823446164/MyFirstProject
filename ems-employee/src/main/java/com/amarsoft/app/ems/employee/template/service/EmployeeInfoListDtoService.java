package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoDeleteReq;

/**
 * 员工信息ListService接口
 * @author lding
 */
public interface EmployeeInfoListDtoService {
    /**
     * 员工信息List查询
     * @param request
     * @return
     */
    public EmployeeInfoListDtoQueryRsp employeeInfoListDtoQuery(@Valid EmployeeInfoListDtoQueryReq employeeInfoListDtoQueryReq);

    /**
     * 员工信息List保存
     * @param request
     * @return
     */
    public void employeeInfoListDtoSave(@Valid EmployeeInfoListDtoSaveReq employeeInfoListDtoSaveReq);

    /**
     * 员工信息List删除
     * @param request
     * @return
     */
    public void employeeInfoListDtoDelete(@Valid EmployeeInfoListDtoDeleteReq employeeInfoListDtoDeleteReq);
}
