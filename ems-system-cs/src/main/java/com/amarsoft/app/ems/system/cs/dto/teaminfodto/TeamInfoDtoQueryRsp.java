package com.amarsoft.app.ems.system.cs.dto.teaminfodto;

import java.io.Serializable;
import java.util.List;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDto;

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
   private  List<EmployeeInfoListDto> list;
  
   
}

