package com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist;

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
 * 员工强化培训表现查询请求实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class EmployeeStrongPerformListQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("代码号")
    @ActualColumn("CL.codeNo")
    private QueryParameter<String> query_codeNo;

    @Description("流水号")
    @ActualColumn("TP.serialNo")
    private QueryParameter<String> query_serialNo;

    @Description("模块编号")
    @ActualColumn("TP.itemNo")
    private QueryParameter<String> query_itemNo;

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
    @ActualColumn("TP.employeeNo")
    private String employeeNo;

    @Description("项目编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TP.projectNo")
    private String projectNo;
}
