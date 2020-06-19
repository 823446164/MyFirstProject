package com.amarsoft.app.ems.demo.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfoSaveReq;

/**
 * 团队调整申请InfoService接口
 * @author jfan5
 */
public interface EmployeeBelongChangeInfoService {
    /**
     * 团队调整申请Info查询
     * @param request
     * @return
     */
    public EmployeeBelongChangeInfoQueryRsp employeeBelongChangeInfoQuery(@Valid EmployeeBelongChangeInfoQueryReq employeeBelongChangeInfoQueryReq);

    /**
     * 团队调整申请Info保存
     * @param request
     * @return
     */
    public void employeeBelongChangeInfoSave(@Valid EmployeeBelongChangeInfoSaveReq employeeBelongChangeInfoSaveReq);
}
