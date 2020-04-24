/**
 * 查询用户转移授权
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.usersubstquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.userrole.Role;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.DatePattern;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.UserLeaveStatus;
import javax.validation.Valid;

import java.util.List;

@Getter
@Setter
@ToString
public class Subst implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("代理记录编号")
    @Length(max=40)
    private String substId;
    @Description("用户编号")
    @NotEmpty
    @Length(max=40)
    private String userId;
    @Description("代理用户编号")
    @NotEmpty
    @Length(max=40)
    private String substUserId;
    @Description("开始时间")
    @NotEmpty
    @Length(max=20)
    @DatePattern("yyyy/MM/dd HH:mm:ss")
    private String beginTime;
    @Description("结束时间")
    @NotEmpty
    @Length(max=20)
    @DatePattern("yyyy/MM/dd HH:mm:ss")
    private String endTime;
    @Description("授权原因")
    @NotEmpty
    @Length(max=400)
    private String subReason;
    @Description("状态")
    @NotEmpty
    @Length(max=1)
    @Enum(UserLeaveStatus.class)
    private String status;
    @Description("角色数组")
    @Valid
    private List<Role> roles;
}