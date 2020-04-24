/**
 * 查询用户事件
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.usereventquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.UserEventType;

@Getter
@Setter
@ToString
public class UserEvent implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户事件类型")
    @NotEmpty
    @Length(max=80)
    @Enum(UserEventType.class)
    private String userEventType;
    @Description("事件时间")
    @Length(max=23)
    private String eventTime;
    @Description("日志")
    @NotEmpty
    @Length(max=4000)
    private String log;
    @Description("操作浏览器")
    @NotEmpty
    @Length(max=80)
    private String browser;
    @Description("操作系统")
    @NotEmpty
    @Length(max=40)
    private String os;
    @Description("IP地址")
    @NotEmpty
    @Length(max=15)
    private String ipAddress;
}