/**
 * 记录用户登录失败
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.logonfailure;

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
public class LogonFailureReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("登录编号")
    @NotEmpty
    @Length(max=40)
    private String logonId;
    @Description("状态")
    @NotEmpty
    @Length(max=1)
    @Enum(UserStatus.class)
    private String status;
}