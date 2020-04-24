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
import java.util.List;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class UserEventQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户编号")
    @NotEmpty
    @Length(max=40)
    private String userId;
    @Description("用户事件类型")
    @Enum(UserEventType.class)
    private List<String> userEventType;
    @Description("开始时间")
    @Length(max=23)
    private String beginTime;
    @Description("结束时间")
    @Length(max=23)
    private String endTime;
    @Description("开始值")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer begin;
    @Description("笔数")
    @NotEmpty
    @Digits(length=10,scale=0)
    private Integer pageSize;
}