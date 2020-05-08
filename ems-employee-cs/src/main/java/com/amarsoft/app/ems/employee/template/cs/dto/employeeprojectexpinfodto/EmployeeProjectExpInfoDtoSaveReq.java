package com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto;

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
 * 员工项目经历Info保存请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
public class EmployeeProjectExpInfoDtoSaveReq extends EmployeeProjectExpInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("项目编号")
    @NotEmpty
    private String serialNo;
    
    @Description("工作描述")
    @NotEmpty
    private String workDescribe;
    
}
