package com.amarsoft.app.ems.train.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListDeleteReq;

/**
 * 培训项目参与人员列表Service接口
 * @author xphe
 */
public interface EmployeeStrongMemberListService {
    /**
     * 培训项目参与人员列表查询
     * @param request
     * @return
     */
    public EmployeeStrongMemberListQueryRsp employeeStrongMemberListQuery(@Valid EmployeeStrongMemberListQueryReq employeeStrongMemberListQueryReq);

    /**
     * 培训项目参与人员列表删除
     * @param request
     * @return
     */
    public void employeeStrongMemberListDelete(@Valid EmployeeStrongMemberListDeleteReq employeeStrongMemberListDeleteReq);
}
