package com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.avta.annotation.TemplateBody;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 员工信息Info 单字段员工状态更新
 * @author jxzhou1
 */
@Getter
@Setter
@ToString
public class EmployeeInfoStatusUpdateReq extends EmployeeInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
    @Description("员工编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("EI.employeeNo")
    private String employeeNo;
    
    @Description("员工状态")
    @Length(max=10)
    @ActualColumn("EI.employeeStatus")
    private String employeeStatus;

}
