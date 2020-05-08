package com.amarsoft.app.ems.train.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceListDeleteReq;

/**
 * 员工参与培训项目列表Service接口
 * @author xphe
 */
public interface EmployeeProjectExperienceListService {
    /**
     * 员工参与培训项目列表查询
     * @param request
     * @return
     */
    public EmployeeProjectExperienceListQueryRsp employeeProjectExperienceListQuery(@Valid EmployeeProjectExperienceListQueryReq employeeProjectExperienceListQueryReq);

    /**
     * 员工参与培训项目列表删除
     * @param request
     * @return
     */
    public void employeeProjectExperienceListDelete(@Valid EmployeeProjectExperienceListDeleteReq employeeProjectExperienceListDeleteReq);
}
