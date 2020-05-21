
package com.amarsoft.app.ems.employee.template.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoStatusUpdateReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.EmployeeListByUserQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.EmployeeListByUserQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoReq;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoRsp;

/**
 * 员工信息ListService接口
 * @author lding
 */
public interface EmployeeInfoListDtoService {
    /**
     * 员工信息List查询
     * @param request
     * @return
     */
    public EmployeeInfoListDtoQueryRsp employeeInfoListDtoQuery(@Valid EmployeeInfoListDtoQueryReq employeeInfoListDtoQueryReq);

    /**
     * 员工信息List保存
     * @param request
     * @return
     */
    public void employeeInfoListDtoSave(@Valid EmployeeInfoListDtoSaveReq employeeInfoListDtoSaveReq);

    /**
     * 员工信息List删除
     * @param request
     * @return
     */
    public void employeeInfoListDtoDelete(@Valid EmployeeInfoListDtoDeleteReq employeeInfoListDtoDeleteReq);
   
    /**
     * Description: 根据用户权限获取员工列表<br>
     * ${tags}
     * @see
     */
    public EmployeeListByUserQueryRsp employeeListByUserQuery(@RequestBody @Valid EmployeeListByUserQueryReq req);
    
    /**
     * Description: 根据条件获取员工列表<br>
     * ${tags}
     * @see
     */
    public EmployeeListByEmplNoRsp employeeListByEmployeeNo(@RequestBody @Valid EmployeeListByEmplNoReq req);

    /**
     * Description: 员工状态置为离职<br>
     * ${tags}
     * @see
     */
    public Map<String,String> employeeInfoDtoStatusSave(@Valid EmployeeInfoStatusUpdateReq EmployeeInfoStatusUpdateReq);

    /**
     * Description: 将离职员工更改为实习或者是使用<br>
     * ${tags}
     * @see
     */
    public Map<String,String> employeeInfoDtoStatusUpdate(@Valid EmployeeInfoStatusUpdateReq EmployeeInfoStatusUpdateReq);

}
