package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoSaveReq;

/**
 * 员工成长目标跟踪InfoService接口
 * @author lding
 */
public interface EmployeeDevelopTargetInfoDtoService {
    /**
     * 员工成长目标跟踪Info查询
     * @param request
     * @return
     */
    public EmployeeDevelopTargetInfoDtoQueryRsp employeeDevelopTargetInfoDtoQuery(@Valid EmployeeDevelopTargetInfoDtoQueryReq employeeDevelopTargetInfoDtoQueryReq);

    /**
     * 员工成长目标跟踪Info保存
     * @param request
     * @return
     */
    public void employeeDevelopTargetInfoDtoSave(@Valid EmployeeDevelopTargetInfoDtoSaveReq employeeDevelopTargetInfoDtoSaveReq);
}
