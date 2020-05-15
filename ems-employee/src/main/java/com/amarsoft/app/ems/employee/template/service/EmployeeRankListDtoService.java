/* 文件名：EmployeePersonalPerformanceListDtoServiceImpl
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
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoDeleteReq;

/**
 * 员工职级ListService接口
 * @author lding
 */
public interface EmployeeRankListDtoService {
    /**
     * 员工职级List查询
     * @param request
     * @return
     */
    public EmployeeRankListDtoQueryRsp employeeRankListDtoQuery(@Valid EmployeeRankListDtoQueryReq employeeRankListDtoQueryReq);

    /**
     * 员工职级List保存
     * @param request
     * @return
     */
    public Map<String, String> employeeRankListDtoSave(@Valid EmployeeRankListDtoSaveReq employeeRankListDtoSaveReq);

}
