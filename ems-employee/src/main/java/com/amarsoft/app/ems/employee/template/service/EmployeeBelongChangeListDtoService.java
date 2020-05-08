package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoDeleteReq;

/**
 * 团队调整申请ListService接口
 * @author lding
 */
public interface EmployeeBelongChangeListDtoService {
    /**
     * 团队调整申请List查询
     * @param request
     * @return
     */
    public EmployeeBelongChangeListDtoQueryRsp employeeBelongChangeListDtoQuery(@Valid EmployeeBelongChangeListDtoQueryReq employeeBelongChangeListDtoQueryReq);

    /**
     * 团队调整申请List保存
     * @param request
     * @return
     */
    public void employeeBelongChangeListDtoSave(@Valid EmployeeBelongChangeListDtoSaveReq employeeBelongChangeListDtoSaveReq);

    /**
     * 团队调整申请List删除
     * @param request
     * @return
     */
    public void employeeBelongChangeListDtoDelete(@Valid EmployeeBelongChangeListDtoDeleteReq employeeBelongChangeListDtoDeleteReq);
}
