package com.amarsoft.app.ems.train.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformListDeleteReq;

/**
 * 员工基础培训表现Service接口
 * @author xphe
 */
public interface EmployeeTrainPerformListService {
    /**
     * 员工基础培训表现查询
     * @param request
     * @return
     */
    public EmployeeTrainPerformListQueryRsp employeeTrainPerformListQuery(@Valid EmployeeTrainPerformListQueryReq employeeTrainPerformListQueryReq);

    /**
     * 员工基础培训表现删除
     * @param request
     * @return
     */
    public void employeeTrainPerformListDelete(@Valid EmployeeTrainPerformListDeleteReq employeeTrainPerformListDeleteReq);
}
