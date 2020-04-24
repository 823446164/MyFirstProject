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
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Digits;
import java.util.List;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.UserLeaveStatus;

@Getter
@Setter
@ToString
public class UserSubstQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("代理记录编号")
    @Length(max=40)
    private String substId;
    @Description("用户编号")
    @Length(max=40)
    private String userId;
    @Description("代理人用户编号")
    @Length(max=40)
    private String substUserId;
    @Description("开始时间")
    @Length(max=20)
    private String beginTime;
    @Description("结束时间")
    @Length(max=20)
    private String endTime;
    @Description("开始值")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer begin;
    @Description("笔数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer pageSize;
    @Description("状态")
    @Enum(UserLeaveStatus.class)
    private List<String> status;
}