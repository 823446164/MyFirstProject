package com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 员工信息Info查询响应实体类
 * @author lding
 */
@Getter
@Setter
@ToString
public class EmployeeInfoDtoQueryRsp extends EmployeeInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private EmployeeInfoDto employeeInfoDto;
    
    @Description("所属团队编号")
    @Length(max=40)
    private String teamId;
    
    @Description("所属团队名称")
    @Length(max=80)    
    private String beforeTeam;
    
}
