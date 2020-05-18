package com.amarsoft.app.ems.system.cs.dto.teamlistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryBegin;
import com.amarsoft.amps.acsc.query.annotation.QueryOrderBy;
import com.amarsoft.amps.acsc.query.annotation.QueryPageSize;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.acsc.query.QueryParameter;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Range;

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
    @Description("团队编号")
    @ActualColumn("TINFO.teamName")
    private String  teamName;
    @Description("所属部门")
    @ActualColumn("TINFO.belongOrgOId")
    private String  orgId;
    @Description("角色A")
    @ActualColumn("TINFO.roleA") 
    private String  roleA;
}
