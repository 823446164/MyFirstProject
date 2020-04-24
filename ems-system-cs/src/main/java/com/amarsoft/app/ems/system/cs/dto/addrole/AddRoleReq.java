/**
 * 新增角色
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.addrole;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.roleauth.RoleAuth;
import com.amarsoft.app.ems.system.cs.dto.updaterole.UserInfo;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.SystemStatus;
import javax.validation.Valid;

import java.util.List;

@Getter
@Setter
@ToString
public class AddRoleReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("角色编号")
    @NotEmpty
    @Length(max=40)
    private String roleId;
    @Description("角色名称")
    @NotEmpty
    @Length(max=80)
    private String roleName;
    @Description("所属法人")
    @NotEmpty
    @Length(max=40)
    private String belongRootOrg;
    @Description("所属机构级别")
    @NotEmpty
    @Length(max=1)
    private String belongOrgLevel;
    @Description("状态")
    @NotEmpty
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status;
    @Description("授权数组")
    @Valid
    private List<RoleAuth> roleAuths;
    @Description("用户数组")
    @Valid
    private List<UserInfo> users;
}