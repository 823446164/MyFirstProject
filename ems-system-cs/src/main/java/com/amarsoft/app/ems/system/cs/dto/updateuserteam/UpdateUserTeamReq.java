/**
 * 更新成员团队
 * @Author xshzou
 */
package com.amarsoft.app.ems.system.cs.dto.updateuserteam;

import java.io.Serializable;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateUserTeamReq implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Description("员工编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("EI.employeeNo")
    private String employeeNo;
    @Description("员工姓名")
    @Length(max=80)
    @NotEmpty
    @ActualColumn("EI.employeeName")
    private String employeeName;
    @Description("员工帐号")
    @Length(max=100)
    @NotEmpty
    @ActualColumn("EI.employeeAcct")
    private String employeeAcct;
    @Description("调整后所在团队")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TI.teamId")
    private String teamId;
    @Description("调整后团队负责人")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TI.teamLeader")
    private String teamLeader;
   
}
