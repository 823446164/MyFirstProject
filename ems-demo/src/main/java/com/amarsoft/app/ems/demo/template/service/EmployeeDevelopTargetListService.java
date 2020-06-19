package com.amarsoft.app.ems.demo.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListDeleteReq;

/**
 * 员工成长目标跟踪ListService接口
 * @author jfan5
 */
public interface EmployeeDevelopTargetListService {
    /**
     * 员工成长目标跟踪List查询
     * @param request
     * @return
     */
    public EmployeeDevelopTargetListQueryRsp employeeDevelopTargetListQuery(@Valid EmployeeDevelopTargetListQueryReq employeeDevelopTargetListQueryReq);

    /**
     * 员工成长目标跟踪List保存
     * @param request
     * @return
     */
    public void employeeDevelopTargetListSave(@Valid EmployeeDevelopTargetListSaveReq employeeDevelopTargetListSaveReq);

    /**
     * 员工成长目标跟踪List删除
     * @param request
     * @return
     */
    public void employeeDevelopTargetListDelete(@Valid EmployeeDevelopTargetListDeleteReq employeeDevelopTargetListDeleteReq);
}
