package com.amarsoft.app.ems.system.cs.dto.teamlistdto;


import java.io.Serializable;

import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 团队信息查询响应实体类
 * 
 * @author hpli
 */
@Getter
@Setter
@ToString

public class TeamListDtoQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;

    @Description("总笔数")
    private Integer totalCount;

    @Description("团队信息")
    private List<TeamListDto> teamListDtos;



}
