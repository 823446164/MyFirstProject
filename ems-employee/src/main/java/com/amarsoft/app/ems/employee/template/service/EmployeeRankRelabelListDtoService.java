/*
 * 文件名：EmployeeRankRelabelListDtoService.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：职级标签实体类接口
 * 修改人：dxiao
 * 修改时间：2020年5月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */

package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;

import com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto.EmployeeRankRelabelListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto.EmployeeRankRelabelListDtoQueryRsp;

/**
 * @author dxiao
 * @version 2020年5月19日
 * @see EmployeeRankRelabelListDtoService
 * @since
 */

public interface EmployeeRankRelabelListDtoService {

    /**
     * 职级标签List查询
     * @param request
     * @return
     */
    public EmployeeRankRelabelListDtoQueryRsp employeeRankRelabelListDtoQuery(@Valid EmployeeRankRelabelListDtoQueryReq employeeRankRelabelListDtoQueryReq);

    
}
