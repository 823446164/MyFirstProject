package com.amarsoft.app.ems.demo.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfoSaveReq;

/**
 * 员工项目经历个人表现InfoService接口
 * @author jfan5
 */
public interface EmployeePersonalPerformanceInfoService {
    /**
     * 员工项目经历个人表现Info查询
     * @param request
     * @return
     */
    public EmployeePersonalPerformanceInfoQueryRsp employeePersonalPerformanceInfoQuery(@Valid EmployeePersonalPerformanceInfoQueryReq employeePersonalPerformanceInfoQueryReq);

    /**
     * 员工项目经历个人表现Info保存
     * @param request
     * @return
     */
    public void employeePersonalPerformanceInfoSave(@Valid EmployeePersonalPerformanceInfoSaveReq employeePersonalPerformanceInfoSaveReq);
}
