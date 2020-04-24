/**
 * 查询指定利率的信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.rateinfoquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.SystemStatus;

@Getter
@Setter
@ToString
public class RateInfoQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("利率代号")
    @NotEmpty
    @Length(max=40)
    private String rateId;
    @Description("状态")
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status = "1";
}