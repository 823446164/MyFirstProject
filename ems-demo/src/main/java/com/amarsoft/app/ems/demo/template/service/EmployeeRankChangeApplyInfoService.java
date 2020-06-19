package com.amarsoft.app.ems.demo.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfoSaveReq;

/**
 * 员工职级调整申请详情InfoService接口
 * @author jfan5
 */
public interface EmployeeRankChangeApplyInfoService {
    /**
     * 员工职级调整申请详情Info查询
     * @param request
     * @return
     */
    public EmployeeRankChangeApplyInfoQueryRsp employeeRankChangeApplyInfoQuery(@Valid EmployeeRankChangeApplyInfoQueryReq employeeRankChangeApplyInfoQueryReq);

    /**
     * 员工职级调整申请详情Info保存
     * @param request
     * @return
     */
    public void employeeRankChangeApplyInfoSave(@Valid EmployeeRankChangeApplyInfoSaveReq employeeRankChangeApplyInfoSaveReq);
}
