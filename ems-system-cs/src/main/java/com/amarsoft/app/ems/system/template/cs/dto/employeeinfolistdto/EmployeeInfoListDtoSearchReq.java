package com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.query.annotation.QueryOrderBy;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.Filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 员工详情List查询请求实体类
 * @author zcluo
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {}, orderBy = {"employeeName", })
public class EmployeeInfoListDtoSearchReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("起始条数")
    //@QueryBegin
    private Integer begin;

    @Description("查询笔数")
    //@Range(min=1,max=10)
    //@QueryPageSize
    private Integer pageSize;

    @Description("排序数组")
    @QueryOrderBy
    private String[] orderBy;
    
    @Description("部门编号")
    @Length(max=80)
    private String orgId;  
    
    @Description("filterLIst")
    private List<Filter> filters;
}
