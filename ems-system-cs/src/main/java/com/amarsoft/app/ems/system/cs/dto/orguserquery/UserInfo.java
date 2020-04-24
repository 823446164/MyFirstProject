/**
 * 查询机构用户
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.orguserquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.UserStatus;

@Getter
@Setter
@ToString
public class UserInfo implements Serializable{
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
    private String language;
    @Description("办公电话")
    @NotEmpty
    @Length(max=20)
    private String officeTel;
    @Description("用户状态")
    @NotEmpty
    @Length(max=1)
    @Enum(UserStatus.class)
    private String status;
}