package com.amarsoft.app.ems.demo.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfoSaveReq;

/**
 * 员工成长目标跟踪InfoService接口
 * @author jfan5
 */
public interface EmployeeDevelopTargetInfoService {
    /**
     * 员工成长目标跟踪Info查询
     * @param request
     * @return
     */
    public EmployeeDevelopTargetInfoQueryRsp employeeDevelopTargetInfoQuery(@Valid EmployeeDevelopTargetInfoQueryReq employeeDevelopTargetInfoQueryReq);

    /**
     * 员工成长目标跟踪Info保存
     * @param request
     * @return
     */
    public void employeeDevelopTargetInfoSave(@Valid EmployeeDevelopTargetInfoSaveReq employeeDevelopTargetInfoSaveReq);
}
