package com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.aecd.employee.constant.RankIsFormal;
import com.amarsoft.aecd.system.constant.ArchitectureType;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.query.annotation.QueryBegin;
import com.amarsoft.amps.acsc.annotation.Range;
import com.amarsoft.amps.acsc.query.annotation.QueryPageSize;
import com.amarsoft.amps.acsc.query.annotation.QueryOrderBy;

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
    
    @Description("员工名称")
    @Length(max=80)
    private String userName;
    
    @Description("员工编号")
    @Length(max=80)
    private String userId;  
    
    @Description("查询条件编号")
    @Length(max=80)
    @Enum(RankIsFormal.class)
    private String conditionId;  
    
}
