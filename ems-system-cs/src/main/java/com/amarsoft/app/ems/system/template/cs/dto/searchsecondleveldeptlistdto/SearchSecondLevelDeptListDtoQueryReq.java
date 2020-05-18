package com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.query.annotation.QueryBegin;
import com.amarsoft.amps.acsc.query.annotation.QueryOrderBy;
import com.amarsoft.amps.acsc.query.annotation.QueryPageSize;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 搜索二级部门信息List查询请求实体类
 * @author zcluo
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class SearchSecondLevelDeptListDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("起始条数")
    @QueryBegin
    private Integer begin;

    @Description("查询笔数")
    @QueryPageSize
    private Integer pageSize;

    @Description("排序数组")
    @QueryOrderBy
    private String[] orderBy;
    
    @Description("部门名称")
    @NotEmpty
    @Length(max=80)
    private String orgName;
    
    @Description("部门等级")
    @NotEmpty
    @Length(max=80)
    private String orgLevel;
}
