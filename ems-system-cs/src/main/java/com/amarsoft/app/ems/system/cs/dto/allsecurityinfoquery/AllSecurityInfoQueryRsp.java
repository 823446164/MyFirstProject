/**
 * 安全信息汇总
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.allsecurityinfoquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class AllSecurityInfoQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("当日登录总次数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer loginTimesCount;
    @Description("当日登录成功总次数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer loginSuccessTimesCount;
    @Description("当日登录总人数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer loginPeopleCount;
    @Description("当日登出总次数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer logoutTimesCount;
    @Description("当日登录总人数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer logoutPeopleCount;
}