package com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist;


import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.query.annotation.QueryBegin;
import com.amarsoft.amps.acsc.annotation.Range;
import com.amarsoft.amps.acsc.query.annotation.QueryPageSize;
import com.amarsoft.amps.acsc.query.annotation.QueryOrderBy;


/**
 * 标签树图查询请求实体类
 * 
 * @author ylgao
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {}, orderBy = {"serialNo",})
public class LableDescribeListQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("起始条数")
    @QueryBegin
    private Integer begin;

    @Description("查询笔数")
    @Range(min=1,max=10)
    @QueryPageSize
    private Integer pageSize;

    @Description("流水号")
    @Length(max = 40)
    @NotEmpty
    @ActualColumn("LD.serialNo")
    private String serialNo;

    @Description("排序数组")
    @QueryOrderBy
    private String[] orderBy;
}
