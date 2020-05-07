package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoSaveReq;

/**
 * 团队调整申请InfoService接口
 * @author lding
 */
public interface EmployeeBelongChangeInfoDtoService {
    /**
     * 团队调整申请Info查询
     * @param request
     * @return
     */
    public EmployeeBelongChangeInfoDtoQueryRsp employeeBelongChangeInfoDtoQuery(@Valid EmployeeBelongChangeInfoDtoQueryReq employeeBelongChangeInfoDtoQueryReq);

    /**
     * 团队调整申请Info保存
     * @param request
     * @return
     */
    public void employeeBelongChangeInfoDtoSave(@Valid EmployeeBelongChangeInfoDtoSaveReq employeeBelongChangeInfoDtoSaveReq);
}
