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
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.CalendarType;

@Getter
@Setter
@ToString
public class CalendarMonthQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("年份")
    @NotEmpty
    @Length(max=4)
    private String year;
    @Description("日历类型")
    @Length(max=80)
    @Enum(CalendarType.class)
    private String calendarType = "Land";
}