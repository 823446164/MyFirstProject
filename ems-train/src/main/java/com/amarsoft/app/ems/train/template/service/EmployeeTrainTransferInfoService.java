package com.amarsoft.app.ems.train.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfoSaveReq;

/**
 * 培训转移记录Service接口
 * @author xphe
 */
public interface EmployeeTrainTransferInfoService {
    /**
     * 培训转移记录查询
     * @param request
     * @return
     */
    public EmployeeTrainTransferInfoQueryRsp employeeTrainTransferInfoQuery(@Valid EmployeeTrainTransferInfoQueryReq employeeTrainTransferInfoQueryReq);

    /**
     * 培训转移记录保存
     * @param request
     * @return
     */
    public void employeeTrainTransferInfoSave(@Valid EmployeeTrainTransferInfoSaveReq employeeTrainTransferInfoSaveReq);
}
