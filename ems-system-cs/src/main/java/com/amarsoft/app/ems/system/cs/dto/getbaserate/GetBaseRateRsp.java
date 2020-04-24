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
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class GetBaseRateRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("基准利率")
    @NotEmpty
    @Digits(length=12,scale=8)
    private Double baseRate;
}