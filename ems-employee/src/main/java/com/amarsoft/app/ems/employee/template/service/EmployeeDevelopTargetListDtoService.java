/* 文件名：EmployeeDevelopTargetListDtoService
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
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoDeleteReq;

/**
 * 员工成长目标跟踪ListService接口
 * @author lding
 */
public interface EmployeeDevelopTargetListDtoService {
    /**
     * 员工成长目标跟踪List查询
     * @param request
     * @return
     */
    public EmployeeDevelopTargetListDtoQueryRsp employeeDevelopTargetListDtoQuery(@Valid EmployeeDevelopTargetListDtoQueryReq employeeDevelopTargetListDtoQueryReq);

    /**
     * 员工成长目标跟踪List删除
     * @param EDT.serialNo
     * @return Y
     */
    public Map<String, String> employeeDevelopTargetListDtoDelete(@Valid EmployeeDevelopTargetListDtoDeleteReq employeeDevelopTargetListDtoDeleteReq);
}
