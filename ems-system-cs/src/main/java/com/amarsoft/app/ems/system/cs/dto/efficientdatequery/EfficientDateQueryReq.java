/**
 * 查询生效日
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.efficientdatequery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.Currency;
import com.amarsoft.aecd.system.constant.SystemStatus;

@Getter
@Setter
@ToString
public class EfficientDateQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("利率类型")
    @Length(max=3)
    private String rateType;
    @Description("币种")
    @Length(max=3)
    @Enum(Currency.class)
    private String currency;
    @Description("状态")
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status = "1";
}