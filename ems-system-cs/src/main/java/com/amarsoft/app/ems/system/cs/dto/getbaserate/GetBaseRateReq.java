/**
 * 获取基准利率
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.getbaserate;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.Currency;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.aecd.acct.constant.BaseRateType;
import com.amarsoft.aecd.acct.constant.RateUnit;
import com.amarsoft.amps.acsc.annotation.Digits;
import com.amarsoft.amps.acsc.annotation.DatePattern;

@Getter
@Setter
@ToString
public class GetBaseRateReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("币种")
    @Length(max=3)
    @Enum(Currency.class)
    private String currency = Currency.CNY.id;
    @Description("基准利率类型")
    @NotEmpty
    @Length(max=3)
    @Enum(BaseRateType.class)
    private String baseRateType;
    @Description("利率单位")
    @NotEmpty
    @Length(max=2)
    @Enum(RateUnit.class)
    private String rateUnit;
    @Description("年基准天数")
    @Digits(length=3,scale=0)
    private Integer yearDays;
    @Description("起始日")
    @NotEmpty
    @Length(max=10)
    @DatePattern("yyyy/MM/dd")
    private String beginDate;
    @Description("到期日")
    @NotEmpty
    @Length(max=10)
    @DatePattern("yyyy/MM/dd")
    private String endDate;
    @Description("生效日")
    @Length(max=10)
    @DatePattern("yyyy/MM/dd")
    private String effectDate;
}