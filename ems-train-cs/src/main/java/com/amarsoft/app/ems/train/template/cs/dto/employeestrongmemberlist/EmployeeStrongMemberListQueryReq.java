package com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.acsc.query.QueryParameter;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.query.annotation.QueryBegin;
import com.amarsoft.amps.acsc.annotation.Range;
import com.amarsoft.amps.acsc.query.annotation.QueryPageSize;
import com.amarsoft.amps.acsc.query.annotation.QueryOrderBy;
import com.amarsoft.amps.acsc.annotation.Length;

/**
 * 培训项目参与人员列表查询请求实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class EmployeeStrongMemberListQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("流水号")
    @ActualColumn("PM.serialNo")
    private QueryParameter<String> query_serialNo;

    @Description("员工编号")
    @ActualColumn("EI.employeeNo")
    private QueryParameter<String> query_employeeNo;

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

    @Description("项目编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("PM.projectNo")
    private String projectNo;
}
