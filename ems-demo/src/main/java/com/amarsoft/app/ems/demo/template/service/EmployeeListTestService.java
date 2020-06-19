package com.amarsoft.app.ems.demo.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestDeleteReq;

/**
 * 员工信息ListService接口
 * @author jfan5
 */
public interface EmployeeListTestService {
    /**
     * 员工信息List查询
     * @param request
     * @return
     */
    public EmployeeListTestQueryRsp employeeListTestQuery(@Valid EmployeeListTestQueryReq employeeListTestQueryReq);

    /**
     * 员工信息List保存
     * @param request
     * @return
     */
    public void employeeListTestSave(@Valid EmployeeListTestSaveReq employeeListTestSaveReq);

    /**
     * 员工信息List删除
     * @param request
     * @return
     */
    public void employeeListTestDelete(@Valid EmployeeListTestDeleteReq employeeListTestDeleteReq);
    
}
