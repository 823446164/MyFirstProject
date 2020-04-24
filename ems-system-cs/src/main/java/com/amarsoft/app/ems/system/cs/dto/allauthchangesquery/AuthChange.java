/**
 * 用户角色变更情况
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.allauthchangesquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.UserEventType;

@Getter
@Setter
@ToString
public class AuthChange implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户编号")
    @Length(max=40)
    private String userId;
    @Description("用户名称")
    @Length(max=80)
    private String userName;
    @Description("机构编号")
    @Length(max=40)
    private String orgId;
    @Description("机构名称")
    @Length(max=80)
    private String orgName;
    @Description("操作系统")
    @Length(max=40)
    private String os;
    @Description("事件类型")
    @Length(max=40)
    @Enum(UserEventType.class)
    private String userEventType;
    @Description("变更信息")
    @Length(max=4000)
    private String log;
    @Description("浏览器")
    @Length(max=40)
    private String browser;
    @Description("网络地址")
    @Length(max=40)
    private String ipAddress;
    @Description("发生时间")
    @Length(max=23)
    private String eventTime;
}