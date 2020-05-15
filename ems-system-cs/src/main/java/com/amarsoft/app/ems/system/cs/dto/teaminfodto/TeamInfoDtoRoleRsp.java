/*
 * 文件名： 团队角色负责人
 * 版权：Copyright by www.amarsoft.com
 * 描述： 团队角色负责人
 * 修改人：hpli
 * 修改时间：2020年5月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.system.cs.dto.teaminfodto;
import java.io.Serializable;
import java.util.List;

import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDto;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoRsp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author hpli
 * @version 2020年5月14日
 * @see TeamInfoDtoRoleRsp
 * @since
 */
@Getter
@Setter
@ToString
public class TeamInfoDtoRoleRsp  extends TeamInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
   
    private  List<EmployeeInfoDto> list;
    
    
     
}
