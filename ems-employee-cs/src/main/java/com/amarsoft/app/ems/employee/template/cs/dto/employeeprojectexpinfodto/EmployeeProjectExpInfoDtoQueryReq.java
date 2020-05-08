package com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.ActualColumn;

/**
 * 员工项目经历Info查询请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class EmployeeProjectExpInfoDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("项目编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("EPE.serialNo")
    private String serialNo;
}
