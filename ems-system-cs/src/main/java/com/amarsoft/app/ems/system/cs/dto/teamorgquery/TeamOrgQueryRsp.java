package com.amarsoft.app.ems.system.cs.dto.teamorgquery;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import com.amarsoft.amps.acsc.annotation.Digits;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeamOrgQueryRsp implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Description("总数")
    @Digits(length=10,scale=0)
    private Integer totalCount;

    @Description("团队部门数组")
    @Valid
    @NotEmpty
    private List<OrgAndTeam> orgTeams;
}
