package com.amarsoft.app.ems.train.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfoSaveReq;

/**
 * 在职培训详情Service接口
 * @author xphe
 */
public interface EmployeeJobTrainInfoService {
    /**
     * 在职培训详情查询
     * @param request
     * @return
     */
    public EmployeeJobTrainInfoQueryRsp employeeJobTrainInfoQuery(@Valid EmployeeJobTrainInfoQueryReq employeeJobTrainInfoQueryReq);

    /**
     * 在职培训详情保存
     * @param request
     * @return
     */
    public void employeeJobTrainInfoSave(@Valid EmployeeJobTrainInfoSaveReq employeeJobTrainInfoSaveReq);
}
