package com.amarsoft.app.ems.demo.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListDeleteReq;

/**
 * 团队调整申请ListService接口
 * @author jfan5
 */
public interface EmployeeBelongChangeListService {
    /**
     * 团队调整申请List查询
     * @param request
     * @return
     */
    public EmployeeBelongChangeListQueryRsp employeeBelongChangeListQuery(@Valid EmployeeBelongChangeListQueryReq employeeBelongChangeListQueryReq);

    /**
     * 团队调整申请List保存
     * @param request
     * @return
     */
    public void employeeBelongChangeListSave(@Valid EmployeeBelongChangeListSaveReq employeeBelongChangeListSaveReq);

    /**
     * 团队调整申请List删除
     * @param request
     * @return
     */
    public void employeeBelongChangeListDelete(@Valid EmployeeBelongChangeListDeleteReq employeeBelongChangeListDeleteReq);
}
