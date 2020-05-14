package com.amarsoft.app.ems.system.cs.dto.teamlistdto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;

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
public class TeamListDtoQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;
    @Description("团队信息")
    private List<TeamListDto> teamListDtos;
    @Description("员工工号")
    private String  employeeId;
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
