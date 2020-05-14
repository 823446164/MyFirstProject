package com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;

public class EmployeeListByUserQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //测试用，用完即删
    @Description("用户编号")
    @Length(max=40)
    private String userId;
}
