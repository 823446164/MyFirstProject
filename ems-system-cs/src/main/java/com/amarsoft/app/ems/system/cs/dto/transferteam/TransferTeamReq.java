/**
 * 转移团队负责人
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.transferteam;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.YesNo;

@Getter
@Setter
@ToString
public class TransferTeamReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("团队编号")
    @NotEmpty
    @Length(max=40)
    private String teamId;
    @Description("团队负责人")
    @NotEmpty
    @Length(max=80)
    private String teamLeader;
    @Description("是否系统管理操作")
    @NotEmpty
    @Length(max=1)
    @Enum(YesNo.class)
    private String systemChangeFlag;
}