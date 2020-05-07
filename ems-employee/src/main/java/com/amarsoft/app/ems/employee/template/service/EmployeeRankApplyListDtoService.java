package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoDeleteReq;

/**
 * 员工职级申请ListService接口
 * @author lding
 */
public interface EmployeeRankApplyListDtoService {
    /**
     * 员工职级申请List查询
     * @param request
     * @return
     */
    public EmployeeRankApplyListDtoQueryRsp employeeRankApplyListDtoQuery(@Valid EmployeeRankApplyListDtoQueryReq employeeRankApplyListDtoQueryReq);

    /**
     * 员工职级申请List保存
     * @param request
     * @return
     */
    public void employeeRankApplyListDtoSave(@Valid EmployeeRankApplyListDtoSaveReq employeeRankApplyListDtoSaveReq);

    /**
     * 员工职级申请List删除
     * @param request
     * @return
     */
    public void employeeRankApplyListDtoDelete(@Valid EmployeeRankApplyListDtoDeleteReq employeeRankApplyListDtoDeleteReq);
}
