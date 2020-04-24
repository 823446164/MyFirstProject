/**
 * 查询指定币种有效的汇率信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.Currency;

@Getter
@Setter
@ToString
public class ErateInfoEfficientQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("币种")
    @NotEmpty
    @Length(max=3)
    @Enum(Currency.class)
    private String currency = "CNY";
}