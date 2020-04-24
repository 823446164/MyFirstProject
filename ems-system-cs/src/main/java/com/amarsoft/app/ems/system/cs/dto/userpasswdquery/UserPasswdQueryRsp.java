/**
 * 查询用户密码状态
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.userpasswdquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Digits;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.Status;

@Getter
@Setter
@ToString
public class UserPasswdQueryRsp implements Serializable{
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
    @Description("密码")
    @NotEmpty
    @Length(max=100)
    private String password;
    @Description("最近密码设置时间")
    @Length(max=23)
    private String passwordTime;
    @Description("最近一次登录时间")
    @Length(max=23)
    private String logonTime;
    @Description("最近一次退出时间")
    @Length(max=23)
    private String logoutTime;
    @Description("连续密码错误次数")
    @Digits(length=10,scale=0)
    private Integer errorTime;
    @Description("所属机构")
    @Length(max=40)
    private String belongOrgId;
    @Description("状态")
    @NotEmpty
    @Length(max=1)
    @Enum(Status.class)
    private String status;
}