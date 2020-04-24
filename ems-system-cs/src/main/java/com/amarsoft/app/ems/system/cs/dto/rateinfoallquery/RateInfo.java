/**
 * 查询所有利率的信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.rateinfoallquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.acct.constant.BaseRateType;
import com.amarsoft.aecd.common.constant.Currency;
import com.amarsoft.amps.acsc.annotation.DatePattern;
import com.amarsoft.aecd.acct.constant.TermUnit;
import com.amarsoft.amps.acsc.annotation.Digits;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.aecd.acct.constant.RateUnit;

@Getter
@Setter
@ToString
public class RateInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("利率代号")
    @Length(max=40)
    private String rateId;
    @Description("利率类型")
    @Length(max=3)
    @Enum(BaseRateType.class)
    private String rateType;
    @Description("币种")
    @Length(max=3)
    @Enum(Currency.class)
    private String currency;
    @Description("生效日期")
    @Length(max=10)
    @DatePattern("yyyy/MM/dd")
    private String efficientDate;
    @Description("利率周期单位")
    @Length(max=1)
    @Enum(TermUnit.class)
    private String termUnit;
    @Description("利率周期")
    @Digits(length=10,scale=0)
    private Integer term;
    @Description("利率")
    @Digits(length=12,scale=8)
    private Double rate;
    @Description("状态")
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status;
    @Description("备注")
    @Length(max=250)
    private String remark;
    @Description("利率单位")
    @Length(max=3)
    @Enum(RateUnit.class)
    private String rateUnit;
}