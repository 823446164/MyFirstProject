/**
 * 查询每月概况
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.calendarmonthquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class MonthInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("月份")
    @Digits(length=2,scale=0)
    private Integer month;
    @Description("工作日天数")
    @Digits(length=2,scale=0)
    private Integer workdays;
    @Description("休息日天数")
    @Digits(length=2,scale=0)
    private Integer offdays;
    @Description("法定假期天数")
    @Digits(length=2,scale=0)
    private Integer statutorydays;
}