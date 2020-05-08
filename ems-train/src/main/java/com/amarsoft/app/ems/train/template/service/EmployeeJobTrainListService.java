package com.amarsoft.app.ems.train.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListDeleteReq;

/**
 * 在职培训列表Service接口
 * @author xphe
 */
public interface EmployeeJobTrainListService {
    /**
     * 在职培训列表查询
     * @param request
     * @return
     */
    public EmployeeJobTrainListQueryRsp employeeJobTrainListQuery(@Valid EmployeeJobTrainListQueryReq employeeJobTrainListQueryReq);

    /**
     * 在职培训列表保存
     * @param request
     * @return
     */
    public void employeeJobTrainListSave(@Valid EmployeeJobTrainListSaveReq employeeJobTrainListSaveReq);

    /**
     * 在职培训列表删除
     * @param request
     * @return
     */
    public void employeeJobTrainListDelete(@Valid EmployeeJobTrainListDeleteReq employeeJobTrainListDeleteReq);
}
