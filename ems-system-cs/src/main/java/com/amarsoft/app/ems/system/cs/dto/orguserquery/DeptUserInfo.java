/*
 * 文件名：DeptUserInfo.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：zcluo
 * 修改时间：2020年5月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
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
public class DeptUserInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("员工编号")
    @NotEmpty
    @Length(max=40)
    private String employeeNo;
    @Description("登录ID")
    @NotEmpty
    @Length(max=40)
    private String logonId;
    @Description("员工名称")
    @NotEmpty
    @Length(max=80)
    private String employeeName;
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