package com.amarsoft.app.ems.train.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfoSaveReq;

/**
 * 追踪记录详情Service接口
 * @author xphe
 */
public interface EmployeeTraceRecordInfoService {
    /**
     * 追踪记录详情查询
     * @param request
     * @return
     */
    public EmployeeTraceRecordInfoQueryRsp employeeTraceRecordInfoQuery(@Valid EmployeeTraceRecordInfoQueryReq employeeTraceRecordInfoQueryReq);

    /**
     * 追踪记录详情保存
     * @param request
     * @return
     */
    public void employeeTraceRecordInfoSave(@Valid EmployeeTraceRecordInfoSaveReq employeeTraceRecordInfoSaveReq);
}
