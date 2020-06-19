package com.amarsoft.app.ems.demo.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTestQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTestQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTestSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeRankApplySaveReq;

/**
 * 员工信息InfoService接口
 * @author jfan5
 */
public interface EmployeeInfoTestService {
    /**
     * 员工信息Info查询
     * @param request
     * @return
     */
    public EmployeeInfoTestQueryRsp employeeInfoTestQuery(@Valid EmployeeInfoTestQueryReq employeeInfoTestQueryReq);

    /**
     * 员工信息Info保存
     * @param request
     * @return
     */
    public void employeeInfoTestSave(@Valid EmployeeInfoTestSaveReq employeeInfoTestSaveReq);
    
    /**
     * 员工信息职级调整申请
     * @param request
     * @return
     */
    public void employeeRankApply(@Valid EmployeeInfoTestSaveReq employeeInfoTestSaveReq);
}
