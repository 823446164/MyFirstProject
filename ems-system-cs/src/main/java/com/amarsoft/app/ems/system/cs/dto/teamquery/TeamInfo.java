/**
 * 查询团队信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.teamquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.userquery.User;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.aecd.system.constant.SystemStatus;
import javax.validation.Valid;

import java.util.List;

@Getter
@Setter
@ToString
public class TeamInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("团队编号")
    @Length(max=40)
    private String teamId;
    @Description("团队名称")
    @Length(max=40)
    private String teamName;
    @Description("所属法人")
    @Length(max=40)
    private String belongRootOrg;
    @Description("所属法人名称")
    @Length(max=80)
    private String belongRootOrgName;
    @Description("所属机构级别")
    @Length(max=1)
    @Enum(OrgLevel.class)
    private String belongOrgLevel;
    @Description("所属机构编号")
    @Length(max=40)
    private String belongOrgId;
    @Description("所属机构名称")
    @Length(max=40)
    private String belongOrgName;
    @Description("团队负责人")
    @Length(max=80)
    private String teamLeader;
    @Description("任务描述")
    @Length(max=40)
    private String description;
    @Description("状态")
    @NotEmpty
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status;
    @Description("团队成员")
    @Valid
    @NotEmpty
    private List<User> users;
}