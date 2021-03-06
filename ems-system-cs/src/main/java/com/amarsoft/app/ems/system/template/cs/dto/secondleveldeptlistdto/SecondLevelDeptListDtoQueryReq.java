package com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.query.annotation.QueryBegin;
import com.amarsoft.amps.acsc.query.annotation.QueryPageSize;
import com.amarsoft.amps.acsc.query.annotation.QueryOrderBy;

/**
 * 二级部门信息List查询请求实体类
 * @author zcluo
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class SecondLevelDeptListDtoQueryReq implements Serializable {
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
    
    @Description("部门编号")
    @NotEmpty
    @Length(max=80)
    private String orgId;
}
