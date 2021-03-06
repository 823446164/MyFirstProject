/**
 * 新增团队信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.addteam;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.SystemStatus;

@Getter
@Setter
@ToString
public class AddTeamReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("团队编号")
    @Length(max=40)
    private String teamId;
    @Description("团队名称")
    @NotEmpty
    @Length(max=80)
    private String teamName;
    @Description("所属机构编号")
    @NotEmpty
    @Length(max=40)
    private String belongOrgId;
    @Description("所属机构级别")
    @NotEmpty
    @Length(max=1)
    private String belongOrgLevel;
    @Description("所属法人编号")
    @NotEmpty
    @Length(max=40)
    private String belongRootOrg;
    
    @Description("任务描述")
    @Length(max=400)
    private String description;
    @Description("团队负责人")
    @Length(max=40)
    private String teamLeader;
    
    @Description("团队负责人")
    @Length(max=80)
    private String roleA;
    @Description("状态")
    @NotEmpty
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status;
}