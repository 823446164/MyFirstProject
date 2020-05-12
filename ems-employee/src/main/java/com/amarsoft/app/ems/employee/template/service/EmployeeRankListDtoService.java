package com.amarsoft.app.ems.employee.template.service;

import java.util.Map;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoDeleteReq;

/**
 * 员工职级ListService接口
 * @author lding
 */
public interface EmployeeRankListDtoService {
    /**
     * 员工职级List查询
     * @param request
     * @return
     */
    public EmployeeRankListDtoQueryRsp employeeRankListDtoQuery(@Valid EmployeeRankListDtoQueryReq employeeRankListDtoQueryReq);

    /**
     * 员工职级List保存
     * @param request
     * @return
     */
    public Map<String, String> employeeRankListDtoSave(@Valid EmployeeRankListDtoSaveReq employeeRankListDtoSaveReq);

    /**
     * 员工职级List删除
     * @param request
     * @return
     */
    public void employeeRankListDtoDelete(@Valid EmployeeRankListDtoDeleteReq employeeRankListDtoDeleteReq);
}
