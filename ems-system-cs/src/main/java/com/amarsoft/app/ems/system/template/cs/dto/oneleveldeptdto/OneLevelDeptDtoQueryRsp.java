package com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 一级部门详情Info查询响应实体类
 * @author zcluo
 */
@Getter
@Setter
@ToString
public class OneLevelDeptDtoQueryRsp extends OneLevelDeptDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("机构编号")
    @Length(max=40)
    private String orgId;
    @Description("部门经理") 
    @Length(max=40) 
    private String deptManager;
    @Description("机构名称")
    @Length(max=80)
    private String orgName;
    @Description("上级机构编号")
    @Length(max=40)
    private String parentOrgId;
    @Description("上级机构名称")
    @Length(max=80)
    private String parentOrgName;
    @Description("下级部门数")
    @Length(max=40)
    private String secondOrgNumber;
}
