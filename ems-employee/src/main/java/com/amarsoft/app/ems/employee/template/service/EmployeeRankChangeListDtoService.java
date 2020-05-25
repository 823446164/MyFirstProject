package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;

import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoSaveReq;

/**
 * 员工职级调整情况ListService接口
 * @author xucheng
 */
public interface EmployeeRankChangeListDtoService {
    /**
     * 员工职级调整情况List查询
     * @param request
     * @return
     */
    public EmployeeRankChangeListDtoQueryRsp employeeRankChangeListDtoQuery(@Valid EmployeeRankChangeListDtoQueryReq employeeRankChangeListDtoQueryReq);

    /**
     * 员工职级调整情况List保存
     * @param request
     * @return
     */
    public void employeeRankChangeListDtoSave(@Valid EmployeeRankChangeListDtoSaveReq employeeRankChangeListDtoSaveReq);

    /**
     * 员工职级调整情况List删除
     * @param request
     * @return
     */
    public void employeeRankChangeListDtoDelete(@Valid EmployeeRankChangeListDtoDeleteReq employeeRankChangeListDtoDeleteReq);
}
