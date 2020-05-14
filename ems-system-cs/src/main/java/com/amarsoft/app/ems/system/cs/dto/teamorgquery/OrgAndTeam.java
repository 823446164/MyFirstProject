package com.amarsoft.app.ems.system.cs.dto.teamorgquery;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrgAndTeam implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Description("团队编号")
    @Length(max=40)
    private String teamId;
    @Description("团队名称")
    @Length(max=80)
    private String teamName;
    @Description("团队负责人")
    @Length(max=40)
    private String roleA;
    @Description("部门编号")
    @Length(max=40)
    private String orgId;
    @Description("部门名称")
    @Length(max=80)
    private String orgName;
    @Description("部门负责人")
    @Length(max=40)
    private String deptManager;
   
}
