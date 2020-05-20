package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDtoSaveReq;

/**
 * 员工职级调整申请详情InfoService接口
 * @author xucheng
 */
public interface EmployeeRankChangeApplyInfoDtoService {
    /**
     * 员工职级调整申请详情Info查询
     * @param request
     * @return
     */
    public EmployeeRankChangeApplyInfoDtoQueryRsp employeeRankChangeApplyInfoDtoQuery(@Valid EmployeeRankChangeApplyInfoDtoQueryReq employeeRankChangeApplyInfoDtoQueryReq);

    /**
     * 员工职级调整申请详情Info保存
     * @param request
     * @return
     */
    public void employeeRankChangeApplyInfoDtoSave(@Valid EmployeeRankChangeApplyInfoDtoSaveReq employeeRankChangeApplyInfoDtoSaveReq);
}
