/**
 * 查询用户
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.userquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.usergroup.Group;
import com.amarsoft.app.ems.system.cs.dto.userrole.Role;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.Language;
import com.amarsoft.aecd.system.constant.Skin;
import com.amarsoft.aecd.system.constant.Layout;
import com.amarsoft.aecd.system.constant.UserStatus;
import java.util.List;
import javax.validation.Valid;

@Getter
@Setter
@ToString
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户编号")
    @NotEmpty
    @Length(max=40)
    private String userId;
    @Description("登录ID")
    @NotEmpty
    @Length(max=40)
    private String logonId;
    @Description("用户名称")
    @NotEmpty
    @Length(max=80)
    private String userName;
    @Description("核心柜员号")
    @Length(max=40)
    private String counter;
    @Description("邮箱地址")
    @Length(max=40)
    private String email;
    @Description("内部员工号")
    @Length(max=40)
    private String empNo;
    @Description("手机号")
    @NotEmpty
    @Length(max=20)
    private String phoneNo;
    @Description("岗位名称")
    @Length(max=80)
    private String jobTitle;
    @Description("用户语言")
    @Length(max=10)
    @Enum(Language.class)
    private String language;
    @Description("皮肤")
    @Length(max=10)
    @Enum(Skin.class)
    private String skin = Skin.Default.id;
    @Description("布局")
    @Length(max=10)
    @Enum(Layout.class)
    private String layout = Layout.Side.id;
    @Description("办公电话")
    @NotEmpty
    @Length(max=20)
    private String officeTel;
    @Description("状态")
    @NotEmpty
    @Length(max=1)
    @Enum(UserStatus.class)
    private String status;
    @Description("参与团队数组")
    @NotEmpty
    private List<String> teamIds;
    @Description("所属信息数组")
    @Valid
    private List<UserBelong> userBelongs;
    @Description("角色数组")
    @Valid
    private List<Role> roles;
    @Description("角色组包含角色数组")
    @Valid
    private List<Role> groupRoles;
    @Description("角色组数组")
    @Valid
    private List<Group> groups;
}