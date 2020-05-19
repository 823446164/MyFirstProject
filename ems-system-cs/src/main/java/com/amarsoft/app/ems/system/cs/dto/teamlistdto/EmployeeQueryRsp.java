/*
 * 文件名：EmployeeQueryRsp.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：hpli
 * 修改时间：2020年5月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.system.cs.dto.teamlistdto;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDto;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 
 * 员工响应
 * @author hpli
 * @version 2020年5月15日
 * @see EmployeeQueryRsp
 * @since
 */

@Getter
@Setter
@ToString
public class EmployeeQueryRsp extends EmployeeInfoListDto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Description("总笔数")
    private Integer totalCount;
    
    @Description("员工详情列表")
    private List<EmployeeInfoDto> employeeInfoList;
}
