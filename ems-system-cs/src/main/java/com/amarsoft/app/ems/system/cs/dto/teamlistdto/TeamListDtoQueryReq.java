package com.amarsoft.app.ems.system.cs.dto.teamlistdto;

import java.awt.List;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.acsc.query.QueryParameter;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.query.annotation.QueryBegin;
import com.amarsoft.amps.acsc.annotation.Range;
import com.amarsoft.amps.acsc.query.annotation.QueryPageSize;
import com.amarsoft.amps.acsc.query.annotation.QueryOrderBy;

/**
 * 团队信息查询请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class TeamListDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("团队编号")
    @ActualColumn("TINFO.teamId")
    private QueryParameter<String> query_teamId;
    @Description("员工编号")
    @Length(max=40)
    private String UserId;
    @Description("部门编号")
    @Length(max=40)
    private String orgId;
    
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

  
    @Description("团队名称")
    public String TeamName;

  
    @Description("团队负责人")
    public String getRoleA;
    
    
    
}
