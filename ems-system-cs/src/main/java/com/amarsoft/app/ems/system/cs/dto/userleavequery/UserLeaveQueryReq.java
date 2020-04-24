/**
 * 查询用户请假记录
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.userleavequery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.UserLeaveType;
import java.util.List;
import com.amarsoft.amps.acsc.annotation.Digits;
import com.amarsoft.aecd.system.constant.UserLeaveStatus;

@Getter
@Setter
@ToString
public class UserLeaveQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户编号")
    @NotEmpty
    @Length(max=40)
    private String userId;
    @Description("搜索项")
    @Length(max=40)
    private String searchAttribute;
    @Description("搜索内容")
    @Length(max=80)
    private String searchContent;
    @Description("开始时间")
    @Length(max=20)
    private String beginTime;
    @Description("结束时间")
    @Length(max=20)
    private String endTime;
    @Description("请假类型")
    @Enum(UserLeaveType.class)
    private List<String> leaveType;
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