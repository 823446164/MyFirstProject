package com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser;

import java.io.Serializable;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EmployeeListByUserQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("员工信息List")
    private List<EmployeeInfoListDto> employeeList;
}
