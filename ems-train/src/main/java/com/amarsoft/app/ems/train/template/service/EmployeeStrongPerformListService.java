package com.amarsoft.app.ems.train.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformListDeleteReq;

/**
 * 员工强化培训表现Service接口
 * @author xphe
 */
public interface EmployeeStrongPerformListService {
    /**
     * 员工强化培训表现查询
     * @param request
     * @return
     */
    public EmployeeStrongPerformListQueryRsp employeeStrongPerformListQuery(@Valid EmployeeStrongPerformListQueryReq employeeStrongPerformListQueryReq);

    /**
     * 员工强化培训表现删除
     * @param request
     * @return
     */
    public void employeeStrongPerformListDelete(@Valid EmployeeStrongPerformListDeleteReq employeeStrongPerformListDeleteReq);
}
