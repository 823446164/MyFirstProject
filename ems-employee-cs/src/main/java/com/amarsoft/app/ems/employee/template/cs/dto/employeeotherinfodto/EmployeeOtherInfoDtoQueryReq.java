package com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto;

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
 * 员工其他说明Info查询请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class EmployeeOtherInfoDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("其他说明编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("EOI.serialNo")
    private String serialNo;
}
