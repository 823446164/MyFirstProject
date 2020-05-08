package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDtoSaveReq;

/**
 * 员工职级InfoService接口
 * @author lding
 */
public interface EmployeeRankInfoDtoService {
    /**
     * 员工职级Info查询
     * @param request
     * @return
     */
    public EmployeeRankInfoDtoQueryRsp employeeRankInfoDtoQuery(@Valid EmployeeRankInfoDtoQueryReq employeeRankInfoDtoQueryReq);

    /**
     * 员工职级Info保存
     * @param request
     * @return
     */
    public void employeeRankInfoDtoSave(@Valid EmployeeRankInfoDtoSaveReq employeeRankInfoDtoSaveReq);
}
