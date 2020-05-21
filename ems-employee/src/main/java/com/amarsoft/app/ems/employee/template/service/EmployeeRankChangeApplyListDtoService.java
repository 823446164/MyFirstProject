package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;

import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoSaveReq;

/**
 * 员工职级调整申请ListService接口
 * @author xucheng
 */
public interface EmployeeRankChangeApplyListDtoService {
    /**
     * 员工职级调整申请List查询
     * @param request
     * @return
     */
    public EmployeeRankChangeApplyListDtoQueryRsp employeeRankChangeApplyListDtoQuery(@Valid EmployeeRankChangeApplyListDtoQueryReq employeeRankChangeApplyListDtoQueryReq);

    /**
     * 员工职级调整申请List保存
     * @param request
     * @return
     */
    public void employeeRankChangeApplyListDtoSave(@Valid EmployeeRankChangeApplyListDtoSaveReq employeeRankChangeApplyListDtoSaveReq);

    /**
     * 员工职级调整申请List删除
     * @param request
     * @return
     */
    public void employeeRankChangeApplyListDtoDelete(@Valid EmployeeRankChangeApplyListDtoDeleteReq employeeRankChangeApplyListDtoDeleteReq);
}
