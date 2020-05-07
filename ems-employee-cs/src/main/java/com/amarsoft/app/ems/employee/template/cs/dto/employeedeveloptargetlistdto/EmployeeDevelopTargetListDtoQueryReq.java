package com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.query.annotation.QueryBegin;
import com.amarsoft.amps.acsc.annotation.Range;
import com.amarsoft.amps.acsc.query.annotation.QueryPageSize;
import com.amarsoft.amps.acsc.query.annotation.QueryOrderBy;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.ActualColumn;

/**
 * 员工成长目标跟踪List查询请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class EmployeeDevelopTargetListDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("起始条数")
    @NotEmpty
    @QueryBegin
    private Integer begin;

    @Description("查询笔数")
    @Range(min=1,max=10)
    @NotEmpty
    @QueryPageSize
    private Integer pageSize;

    @Description("排序数组")
    @QueryOrderBy
    private String[] orderBy;

    @Description("员工编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("EDT.employeeNo")
    private String employeeNo;
}
