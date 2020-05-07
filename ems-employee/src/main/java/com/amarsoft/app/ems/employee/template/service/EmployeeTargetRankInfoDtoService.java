package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDtoSaveReq;

/**
 * 目标职级申请InfoService接口
 * @author lding
 */
public interface EmployeeTargetRankInfoDtoService {
    /**
     * 目标职级申请Info查询
     * @param request
     * @return
     */
    public EmployeeTargetRankInfoDtoQueryRsp employeeTargetRankInfoDtoQuery(@Valid EmployeeTargetRankInfoDtoQueryReq employeeTargetRankInfoDtoQueryReq);

    /**
     * 目标职级申请Info保存
     * @param request
     * @return
     */
    public void employeeTargetRankInfoDtoSave(@Valid EmployeeTargetRankInfoDtoSaveReq employeeTargetRankInfoDtoSaveReq);
}
