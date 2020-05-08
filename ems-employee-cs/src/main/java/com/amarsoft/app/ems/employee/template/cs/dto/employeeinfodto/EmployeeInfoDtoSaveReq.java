package com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 员工信息Info保存请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
public class EmployeeInfoDtoSaveReq extends EmployeeInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
}
