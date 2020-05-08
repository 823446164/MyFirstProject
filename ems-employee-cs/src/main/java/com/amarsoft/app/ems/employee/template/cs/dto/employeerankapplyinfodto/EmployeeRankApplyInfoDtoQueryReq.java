package com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto;

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
 * 员工职级申请Info查询请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class EmployeeRankApplyInfoDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("申请编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("ERA.serialNo")
    private String serialNo;
}
