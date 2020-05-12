/*
 * 文件名：AddTeamRsp.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：amarsoft
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.system.cs.dto.addteam;

import java.util.List;

import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.updateuser.Team;
import com.amarsoft.app.ems.system.cs.dto.usergroup.Group;
import com.amarsoft.app.ems.system.cs.dto.userquery.UserBelong;
import com.amarsoft.app.ems.system.cs.dto.userrole.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddTeamRsp {
    private static final long serialVersionUID = 1L;
    @Description("团队名称")
    
    private String teamName;
    @Description("状态")
    private String status;
    @Description("信息")
    private String  Meassage;
    
}
