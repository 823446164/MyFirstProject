package com.amarsoft.app.ems.train.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListDeleteReq;

/**
 * 追踪内容列表Service接口
 * @author xphe
 */
public interface EmployeeTraceDetailListService {
    /**
     * 追踪内容列表查询
     * @param request
     * @return
     */
    public EmployeeTraceDetailListQueryRsp employeeTraceDetailListQuery(@Valid EmployeeTraceDetailListQueryReq employeeTraceDetailListQueryReq);

    /**
     * 追踪内容列表保存
     * @param request
     * @return
     */
    public void employeeTraceDetailListSave(@Valid EmployeeTraceDetailListSaveReq employeeTraceDetailListSaveReq);

    /**
     * 追踪内容列表删除
     * @param request
     * @return
     */
    public void employeeTraceDetailListDelete(@Valid EmployeeTraceDetailListDeleteReq employeeTraceDetailListDeleteReq);
}
