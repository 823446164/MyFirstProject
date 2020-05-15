/* 文件名：EmployeeDevelopTargetInfoDtoService
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：dxiao
 * 修改时间：2020/05/14
 * 跟踪单号：
 * 修改单号：
 * 修改内容:
 */
package com.amarsoft.app.ems.employee.template.service;

import java.util.Map;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoSaveReq;

/**
 * 员工成长目标跟踪InfoService接口
 * @author lding
 */
public interface EmployeeDevelopTargetInfoDtoService {
    /**
     * 员工成长目标跟踪Info查询
     * @param request
     * @return
     */
    public EmployeeDevelopTargetInfoDtoQueryRsp employeeDevelopTargetInfoDtoQuery(@Valid EmployeeDevelopTargetInfoDtoQueryReq employeeDevelopTargetInfoDtoQueryReq);

    /**
     * 员工成长目标跟踪Info保存
     * @param request
     * @return
     */
    public Map<String, String> employeeDevelopTargetInfoDtoSave(@Valid EmployeeDevelopTargetInfoDtoSaveReq employeeDevelopTargetInfoDtoSaveReq);
}
