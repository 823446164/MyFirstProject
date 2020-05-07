package com.amarsoft.app.ems.train.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfoSaveReq;

/**
 * 追踪内容详情Service接口
 * @author xphe
 */
public interface EmployeeTraceDetailInfoService {
    /**
     * 追踪内容详情查询
     * @param request
     * @return
     */
    public EmployeeTraceDetailInfoQueryRsp employeeTraceDetailInfoQuery(@Valid EmployeeTraceDetailInfoQueryReq employeeTraceDetailInfoQueryReq);

    /**
     * 追踪内容详情保存
     * @param request
     * @return
     */
    public void employeeTraceDetailInfoSave(@Valid EmployeeTraceDetailInfoSaveReq employeeTraceDetailInfoSaveReq);
}
