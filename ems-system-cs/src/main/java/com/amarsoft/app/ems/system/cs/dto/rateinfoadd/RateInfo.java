/**
 * 新增利率
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.rateinfoadd;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.Currency;
import com.amarsoft.aecd.acct.constant.BaseRateType;
import com.amarsoft.amps.acsc.annotation.DatePattern;
import com.amarsoft.aecd.acct.constant.RateUnit;
import com.amarsoft.amps.acsc.annotation.Digits;
import com.amarsoft.aecd.acct.constant.TermUnit;
import com.amarsoft.aecd.system.constant.SystemStatus;

@Getter
@Setter
@ToString
public class RateInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("币种")
    @NotEmpty
    @Length(max=3)
    @Enum(Currency.class)
    private String currency;
    @Description("利率类型")
    @NotEmpty
    @Length(max=3)
    @Enum(BaseRateType.class)
    private String rateType;
    @Description("生效日期")
    @NotEmpty
    @Length(max=10)
    @DatePattern("yyyy/MM/dd")
    private String efficientDate;
    @Description("利率单位")
    @NotEmpty
    @Length(max=2)
    @Enum(RateUnit.class)
    private String rateUnit;
    @Description("利率周期")
    @NotEmpty
    @Digits(length=2,scale=0)
    private Integer term;
    @Description("利率周期单位")
    @NotEmpty
    @Length(max=1)
    @Enum(TermUnit.class)
    private String termUnit;
    @Description("利率")
    @NotEmpty
    @Digits(length=12,scale=8)
    private Double rate;
    @Description("状态")
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status = "1";
}