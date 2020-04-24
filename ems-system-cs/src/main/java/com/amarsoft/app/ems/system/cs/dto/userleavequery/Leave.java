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
import com.amarsoft.amps.acsc.annotation.DatePattern;
import com.amarsoft.aecd.system.constant.UserLeaveStatus;

@Getter
@Setter
@ToString
public class Leave implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("请假记录编号")
    @NotEmpty
    @Length(max=40)
    private String leaveId;
    @Description("请假原因")
    @NotEmpty
    @Length(max=200)
    private String leaveReason;
    @Description("请假类型")
    @NotEmpty
    @Length(max=1)
    @Enum(UserLeaveType.class)
    private String leaveType;
    @Description("请假开始时间")
    @NotEmpty
    @Length(max=20)
    @DatePattern("yyyy/MM/dd HH:mm:ss")
    private String beginTime;
    @Description("请假结束时间")
    @NotEmpty
    @Length(max=20)
    @DatePattern("yyyy/MM/dd HH:mm:ss")
    private String endTime;
    @Description("状态")
    @NotEmpty
    @Length(max=1)
    @Enum(UserLeaveStatus.class)
    private String status;
}