/**
 * 用户修改转授权信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.changeusersubst;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.DatePattern;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.UserLeaveStatus;

@Getter
@Setter
@ToString
public class ChangeUserSubstReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("代理记录编号")
    @NotEmpty
    @Length(max=40)
    private String substId;
    @Description("结束时间")
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