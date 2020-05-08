package com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist;

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

/**
 * 追踪记录列表查询请求实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {}, orderBy = {"serialNo", })
public class EmployeeTraceRecordListQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("追踪编号")
    @ActualColumn("TR.serialNo")
    private QueryParameter<String> query_serialNo;

    @Description("员工编号")
    @ActualColumn("TR.employeeNo")
    private QueryParameter<String> query_employeeNo;

    @Description("员工姓名")
    @ActualColumn("TR.employeeName")
    private QueryParameter<String> query_employeeName;

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
}
