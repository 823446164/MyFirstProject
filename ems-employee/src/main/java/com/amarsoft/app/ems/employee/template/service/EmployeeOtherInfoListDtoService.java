package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoDeleteReq;

/**
 * 员工其他说明ListService接口
 * @author lding
 */
public interface EmployeeOtherInfoListDtoService {
    /**
     * 员工其他说明List查询
     * @param request
     * @return
     */
    public EmployeeOtherInfoListDtoQueryRsp employeeOtherInfoListDtoQuery(@Valid EmployeeOtherInfoListDtoQueryReq employeeOtherInfoListDtoQueryReq);

    /**
     * 员工其他说明List保存
     * @param request
     * @return
     */
    public void employeeOtherInfoListDtoSave(@Valid EmployeeOtherInfoListDtoSaveReq employeeOtherInfoListDtoSaveReq);

    /**
     * 员工其他说明List删除
     * @param request
     * @return
     */
    public void employeeOtherInfoListDtoDelete(@Valid EmployeeOtherInfoListDtoDeleteReq employeeOtherInfoListDtoDeleteReq);
}
