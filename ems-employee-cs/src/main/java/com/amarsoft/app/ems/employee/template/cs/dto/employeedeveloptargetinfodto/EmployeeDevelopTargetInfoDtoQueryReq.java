package com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto;

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
 * 员工成长目标跟踪Info查询请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class EmployeeDevelopTargetInfoDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("成长目标编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("EDT.serialNo")
    private String serialNo;
}
