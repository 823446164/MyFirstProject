package com.amarsoft.app.ems.train.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListDeleteReq;

/**
 * 在职培训参与人员列表Service接口
 * @author xphe
 */
public interface EmployeeJobPartorListService {
    /**
     * 在职培训参与人员列表查询
     * @param request
     * @return
     */
    public EmployeeJobPartorListQueryRsp employeeJobPartorListQuery(@Valid EmployeeJobPartorListQueryReq employeeJobPartorListQueryReq);

    /**
     * 在职培训参与人员列表保存
     * @param request
     * @return
     */
    public void employeeJobPartorListSave(@Valid EmployeeJobPartorListSaveReq employeeJobPartorListSaveReq);

    /**
     * 在职培训参与人员列表删除
     * @param request
     * @return
     */
    public void employeeJobPartorListDelete(@Valid EmployeeJobPartorListDeleteReq employeeJobPartorListDeleteReq);
}
