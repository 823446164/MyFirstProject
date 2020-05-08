package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoDeleteReq;

/**
 * 目标职级申请ListService接口
 * @author lding
 */
public interface EmployeeTargetRankListDtoService {
    /**
     * 目标职级申请List查询
     * @param request
     * @return
     */
    public EmployeeTargetRankListDtoQueryRsp employeeTargetRankListDtoQuery(@Valid EmployeeTargetRankListDtoQueryReq employeeTargetRankListDtoQueryReq);

    /**
     * 目标职级申请List保存
     * @param request
     * @return
     */
    public void employeeTargetRankListDtoSave(@Valid EmployeeTargetRankListDtoSaveReq employeeTargetRankListDtoSaveReq);

    /**
     * 目标职级申请List删除
     * @param request
     * @return
     */
    public void employeeTargetRankListDtoDelete(@Valid EmployeeTargetRankListDtoDeleteReq employeeTargetRankListDtoDeleteReq);
}
