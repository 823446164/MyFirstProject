package com.amarsoft.app.ems.demo.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListDeleteReq;

/**
 * 员工项目经历ListService接口
 * @author jfan5
 */
public interface EmployeeProjectExpListService {
    /**
     * 员工项目经历List查询
     * @param request
     * @return
     */
    public EmployeeProjectExpListQueryRsp employeeProjectExpListQuery(@Valid EmployeeProjectExpListQueryReq employeeProjectExpListQueryReq);

    /**
     * 员工项目经历List保存
     * @param request
     * @return
     */
    public void employeeProjectExpListSave(@Valid EmployeeProjectExpListSaveReq employeeProjectExpListSaveReq);

    /**
     * 员工项目经历List删除
     * @param request
     * @return
     */
    public void employeeProjectExpListDelete(@Valid EmployeeProjectExpListDeleteReq employeeProjectExpListDeleteReq);
}
