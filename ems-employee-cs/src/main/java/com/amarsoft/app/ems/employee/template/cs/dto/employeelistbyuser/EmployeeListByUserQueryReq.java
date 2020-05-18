package com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser;

import java.io.Serializable;

import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeListByUserQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Description("员工编号")
    private String employeeName;
    
    @Description("员工姓名")
    private String employeeNo;
}
