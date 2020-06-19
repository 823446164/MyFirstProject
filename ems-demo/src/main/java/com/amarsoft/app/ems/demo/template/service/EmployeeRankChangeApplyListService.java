package com.amarsoft.app.ems.demo.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListDeleteReq;

/**
 * 员工职级调整申请ListService接口
 * @author jfan5
 */
public interface EmployeeRankChangeApplyListService {
    /**
     * 员工职级调整申请List查询
     * @param request
     * @return
     */
    public EmployeeRankChangeApplyListQueryRsp employeeRankChangeApplyListQuery(@Valid EmployeeRankChangeApplyListQueryReq employeeRankChangeApplyListQueryReq);

    /**
     * 员工职级调整申请List保存
     * @param request
     * @return
     */
    public void employeeRankChangeApplyListSave(@Valid EmployeeRankChangeApplyListSaveReq employeeRankChangeApplyListSaveReq);

    /**
     * 员工职级调整申请List删除
     * @param request
     * @return
     */
    public void employeeRankChangeApplyListDelete(@Valid EmployeeRankChangeApplyListDeleteReq employeeRankChangeApplyListDeleteReq);
}
