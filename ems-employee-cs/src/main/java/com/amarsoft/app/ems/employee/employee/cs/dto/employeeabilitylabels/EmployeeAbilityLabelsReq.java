/**
 * 员工能力标签查询
 * @Author lding
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.employee.employee.cs.dto.employeeabilitylabels;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;

@Getter
@Setter
@ToString
public class EmployeeAbilityLabelsReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("员工编号")
    @NotEmpty
    @Length(max=80)
    private String employeeNo;
    
}