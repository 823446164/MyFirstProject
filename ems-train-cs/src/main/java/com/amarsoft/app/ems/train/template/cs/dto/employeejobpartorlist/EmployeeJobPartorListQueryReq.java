package com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist;

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
 * 在职培训参与人员列表查询请求实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {}, orderBy = {"serialNo", })
public class EmployeeJobPartorListQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("流水号")
    @ActualColumn("TR.serialNo")
    private QueryParameter<String> query_serialNo;

    @Description("培训编号")
    @ActualColumn("TR.trainNo")
    private QueryParameter<String> query_trainNo;

    @Description("员工编号")
    @ActualColumn("TR.employeeNo")
    private QueryParameter<String> query_employeeNo;

    @Description("附件编号")
    @ActualColumn("TR.attachmentNo")
    private QueryParameter<String> query_attachmentNo;

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

    @Description("培训编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TR.trainNo")
    private String trainNo;
}
