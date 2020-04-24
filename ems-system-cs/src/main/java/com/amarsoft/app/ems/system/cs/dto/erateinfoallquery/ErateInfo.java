/**
 * 查询所有汇率的信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.erateinfoallquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.Currency;
import com.amarsoft.amps.acsc.annotation.DatePattern;
import com.amarsoft.amps.acsc.annotation.Digits;
import com.amarsoft.aecd.acct.constant.RateUnit;

@Getter
@Setter
@ToString
public class ErateInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("币种")
    @Length(max=3)
    @Enum(Currency.class)
    private String currency = "CNY";
    @Description("生效日期")
    @Length(max=10)
    @DatePattern("yyyy/MM/dd")
    private String efficientDate;
    @Description("相对值")
    @Digits(length=12,scale=8)
    private Double exchangeValue;
    @Description("备注")
    @Length(max=250)
    private String remark;
    @Description("单位")
    @Digits(length=3,scale=0)
    @Enum(RateUnit.class)
    private Integer unit;
    @Description("中间价")
    @Digits(length=24,scale=8)
    private Double price;
}