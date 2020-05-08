package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoDeleteReq;

/**
 * 员工成长目标跟踪ListService接口
 * @author lding
 */
public interface EmployeeDevelopTargetListDtoService {
    /**
     * 员工成长目标跟踪List查询
     * @param request
     * @return
     */
    public EmployeeDevelopTargetListDtoQueryRsp employeeDevelopTargetListDtoQuery(@Valid EmployeeDevelopTargetListDtoQueryReq employeeDevelopTargetListDtoQueryReq);

    /**
     * 员工成长目标跟踪List保存
     * @param request
     * @return
     */
    public void employeeDevelopTargetListDtoSave(@Valid EmployeeDevelopTargetListDtoSaveReq employeeDevelopTargetListDtoSaveReq);

    /**
     * 员工成长目标跟踪List删除
     * @param request
     * @return
     */
    public void employeeDevelopTargetListDtoDelete(@Valid EmployeeDevelopTargetListDtoDeleteReq employeeDevelopTargetListDtoDeleteReq);
}
