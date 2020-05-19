package com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto;

import java.io.Serializable;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 一级部门详情Info保存请求实体类
 * @author zcluo
 */
@Getter
@Setter
@ToString
public class OneLevelDeptDtoSaveReq extends OneLevelDeptDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("机构编号")
    @Length(max=40)
    private String orgId;
    @Description("机构名称")
    @Length(max=80)
    private String orgName;
    @Description("上级机构编号")
    @Length(max=40)
    private String parentOrgId;
    @Description("上级机构名称")
    @Length(max=80)
    private String parentOrgName;
    @Description("部门设施说明") 
    @Length(max=2000) 
    private String DeptEquipment;
    @Description("部门地址") 
    @Length(max=80) 
    private String deptAddress;
    @Description("备注") 
    @Length(max=2000)  
    private String remark;
    @Description("部门等级") 
    @Length(max=10)  
    private String orgLevel;
    @Description("部门编号长度参数") 
    @Length(max=10)  
    private String ruleLength;

}
