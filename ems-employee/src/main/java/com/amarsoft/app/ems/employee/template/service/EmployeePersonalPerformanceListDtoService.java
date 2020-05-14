package com.amarsoft.app.ems.employee.template.service;

import java.util.Map;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoDeleteReq;

/**
 * 员工项目经历个人表现ListService接口
 * @author lding
 */
public interface EmployeePersonalPerformanceListDtoService {
    /**
     * 员工项目经历个人表现List查询
     * @param request
     * @return
     */
    public EmployeePersonalPerformanceListDtoQueryRsp employeePersonalPerformanceListDtoQuery(@Valid EmployeePersonalPerformanceListDtoQueryReq employeePersonalPerformanceListDtoQueryReq);

    /**
     * 员工项目经历个人表现List保存
     * @param request
     * @return
     */
    public Map<String, String> employeePersonalPerformanceListDtoSave(@Valid EmployeePersonalPerformanceListDtoSaveReq employeePersonalPerformanceListDtoSaveReq);

    /**
     * 员工项目经历个人表现List删除
     * @param request
     * @return
     */
    public void employeePersonalPerformanceListDtoDelete(@Valid EmployeePersonalPerformanceListDtoDeleteReq employeePersonalPerformanceListDtoDeleteReq);
}
