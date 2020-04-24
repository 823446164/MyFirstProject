/**
 * 修改角色组
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.updategroup;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.grouprole.Role;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.aecd.system.constant.SystemStatus;
import javax.validation.Valid;

import java.util.List;

@Getter
@Setter
@ToString
public class UpdateGroupReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("角色组编号")
    @NotEmpty
    @Length(max=40)
    private String groupId;
    @Description("角色组名称")
    @NotEmpty
    @Length(max=80)
    private String groupName;
    @Description("所属法人")
    @NotEmpty
    @Length(max=40)
    private String belongRootOrg;
    @Description("所属机构级别")
    @NotEmpty
    @Length(max=1)
    @Enum(OrgLevel.class)
    private String belongOrgLevel;
    @Description("状态")
    @NotEmpty
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status;
    @Description("角色数组")
    @Valid
    private List<Role> roles;
    @Description("用户数组")
    @Valid
    private List<UserInfo> users;
}