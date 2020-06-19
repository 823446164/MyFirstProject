package com.amarsoft.app.ems.demo.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfoSaveReq;

/**
 * 员工项目经历InfoService接口
 * @author jfan5
 */
public interface EmployeeProjectExpInfoService {
    /**
     * 员工项目经历Info查询
     * @param request
     * @return
     */
    public EmployeeProjectExpInfoQueryRsp employeeProjectExpInfoQuery(@Valid EmployeeProjectExpInfoQueryReq employeeProjectExpInfoQueryReq);

    /**
     * 员工项目经历Info保存
     * @param request
     * @return
     */
    public void employeeProjectExpInfoSave(@Valid EmployeeProjectExpInfoSaveReq employeeProjectExpInfoSaveReq);
}
