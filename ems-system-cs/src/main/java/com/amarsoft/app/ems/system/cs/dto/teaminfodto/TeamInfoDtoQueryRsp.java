package com.amarsoft.app.ems.system.cs.dto.teaminfodto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.UserInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 团队信息查询响应实体类
 * @author hpli
 */

@Getter
@Setter
@ToString
public class TeamInfoDtoQueryRsp extends TeamInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
 
    private Integer totalCount;
    @Description("团队信息组")
    @Valid
    @NotEmpty
    private List<TeamInfoDto> teamInfoDto;
    @Description("员工编号")
    private String employeeNo;
    @Description("员工姓名")
    private String employeeName;
    @Description("性别")
    private String sex;
    @Description("员工账号")
    private String employeeAcct;
    @Description("当前职级")
    private String nowRank;   
    @Description("入职日期")
    private LocalDate rntryTime;
    @Description("所属机构编号")
    private String belongOrgId;
    @Description("团队名称")
    private String teamName;
   
}
