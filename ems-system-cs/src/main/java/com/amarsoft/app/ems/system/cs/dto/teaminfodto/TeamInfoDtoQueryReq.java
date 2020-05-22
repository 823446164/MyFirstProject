package com.amarsoft.app.ems.system.cs.dto.teaminfodto;

import java.io.Serializable;

import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 团队信息查询请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
public class TeamInfoDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("团队编号")
    private String teamId;
    
    @Description("所属部门")
    private String belongOrgId;
    
    @Description("团队状态")
    public String status; 
    
}

