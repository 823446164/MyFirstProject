/**
 * 修改指定日历信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.calendarupdate;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.DatePattern;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.CalendarType;
import com.amarsoft.aecd.system.constant.WorkFlag;

@Getter
@Setter
@ToString
public class Calendar implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("当前日期")
    @NotEmpty
    @Length(max=10)
    @DatePattern("yyyy/MM/dd")
    private String curCalendar;
    @Description("日历类型")
    @NotEmpty
    @Length(max=80)
    @Enum(CalendarType.class)
    private String calendarType = "Land";
    @Description("是否工作日（大陆）")
    @NotEmpty
    @Length(max=10)
    @Enum(WorkFlag.class)
    private String workFlag;
}