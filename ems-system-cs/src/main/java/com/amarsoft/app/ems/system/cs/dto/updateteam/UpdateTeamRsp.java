package com.amarsoft.app.ems.system.cs.dto.updateteam;

import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateTeamRsp {
    
    private static final long serialVersionUID = 1L;
    @Description("信息")
    private String  Meassage;
    
}
