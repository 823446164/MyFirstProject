package com.amarsoft.app.ems.demo.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListDeleteReq;

/**
 * 员工项目经历个人表现ListService接口
 * @author jfan5
 */
public interface EmployeePersonalPerformanceListService {
    /**
     * 员工项目经历个人表现List查询
     * @param request
     * @return
     */
    public EmployeePersonalPerformanceListQueryRsp employeePersonalPerformanceListQuery(@Valid EmployeePersonalPerformanceListQueryReq employeePersonalPerformanceListQueryReq);

    /**
     * 员工项目经历个人表现List保存
     * @param request
     * @return
     */
    public void employeePersonalPerformanceListSave(@Valid EmployeePersonalPerformanceListSaveReq employeePersonalPerformanceListSaveReq);

    /**
     * 员工项目经历个人表现List删除
     * @param request
     * @return
     */
    public void employeePersonalPerformanceListDelete(@Valid EmployeePersonalPerformanceListDeleteReq employeePersonalPerformanceListDeleteReq);
}
