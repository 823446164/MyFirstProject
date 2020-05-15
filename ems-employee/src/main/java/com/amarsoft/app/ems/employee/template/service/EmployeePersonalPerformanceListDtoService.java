/*文件名：EmployeePersonalPerformanceListDtoService
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：dxiao
 * 修改时间：2020/05/14
 * 跟踪单号：
 * 修改单号：
 * 修改内容：修改注释
 */
package com.amarsoft.app.ems.employee.template.service;

import java.util.Map;

import javax.validation.Valid;

import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoSaveReq;

/**
 * 员工项目经历个人表现查询接口
 * @author lding
 */
public interface EmployeePersonalPerformanceListDtoService {
    /**
     * 员工项目经历个人表现List查询
     * @param request
     * @return
     */
    public EmployeePersonalPerformanceListDtoQueryRsp employeePersonalPerformanceListDtoQuery(@Valid EmployeePersonalPerformanceListDtoQueryReq employeePersonalPerformanceListDtoQueryReq);

    /**
     * 员工项目经历个人表现List保存
     * @param request
     * @return
     */
    public Map<String, String> employeePersonalPerformanceListDtoSave(@Valid EmployeePersonalPerformanceListDtoSaveReq employeePersonalPerformanceListDtoSaveReq);
}
