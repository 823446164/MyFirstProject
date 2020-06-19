package com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest;

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
 * 员工信息List查询请求实体类
 * @author jfan5
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {}, orderBy = {"employeeNo", })
public class EmployeeListTestQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("员工编号")
    @ActualColumn("TIJ.employeeNo")
    private QueryParameter<String> query_employeeNo;

    @Description("员工姓名")
    @ActualColumn("TIJ.employeeName")
    private QueryParameter<String> query_employeeName;

    @Description("员工账号")
    @ActualColumn("TIJ.employeeAcct")
    private QueryParameter<String> query_employeeAcct;

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
