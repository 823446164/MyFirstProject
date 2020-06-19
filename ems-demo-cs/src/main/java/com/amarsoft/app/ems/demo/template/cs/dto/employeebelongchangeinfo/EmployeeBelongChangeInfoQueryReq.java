package com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo;

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
 * 团队调整申请Info查询请求实体类
 * @author jfan5
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class EmployeeBelongChangeInfoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("申请编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("EBC.serialNo")
    private String serialNo;
}
