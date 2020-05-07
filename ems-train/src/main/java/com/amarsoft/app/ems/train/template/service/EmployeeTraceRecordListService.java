package com.amarsoft.app.ems.train.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListDeleteReq;

/**
 * 追踪记录列表Service接口
 * @author xphe
 */
public interface EmployeeTraceRecordListService {
    /**
     * 追踪记录列表查询
     * @param request
     * @return
     */
    public EmployeeTraceRecordListQueryRsp employeeTraceRecordListQuery(@Valid EmployeeTraceRecordListQueryReq employeeTraceRecordListQueryReq);

    /**
     * 追踪记录列表保存
     * @param request
     * @return
     */
    public void employeeTraceRecordListSave(@Valid EmployeeTraceRecordListSaveReq employeeTraceRecordListSaveReq);

    /**
     * 追踪记录列表删除
     * @param request
     * @return
     */
    public void employeeTraceRecordListDelete(@Valid EmployeeTraceRecordListDeleteReq employeeTraceRecordListDeleteReq);
}
