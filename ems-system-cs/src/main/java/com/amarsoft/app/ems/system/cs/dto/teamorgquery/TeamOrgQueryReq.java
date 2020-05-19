/**
 * 查询部门团队信息
 * @Author xszhou
 */
package com.amarsoft.app.ems.system.cs.dto.teamorgquery;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.Filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeamOrgQueryReq implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Description("查询条件数组")
	private List<Filter> filters;
}
