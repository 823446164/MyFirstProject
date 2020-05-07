package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoSaveReq;

/**
 * 员工职级申请InfoService接口
 * @author lding
 */
public interface EmployeeRankApplyInfoDtoService {
    /**
     * 员工职级申请Info查询
     * @param request
     * @return
     */
    public EmployeeRankApplyInfoDtoQueryRsp employeeRankApplyInfoDtoQuery(@Valid EmployeeRankApplyInfoDtoQueryReq employeeRankApplyInfoDtoQueryReq);

    /**
     * 员工职级申请Info保存
     * @param request
     * @return
     */
    public void employeeRankApplyInfoDtoSave(@Valid EmployeeRankApplyInfoDtoSaveReq employeeRankApplyInfoDtoSaveReq);
}
