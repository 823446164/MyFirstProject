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
import javax.validation.Valid;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
public class CalendarMonthQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("日历信息组")
    @Valid
    @NotEmpty
    private List<MonthInfo> monthInfos;
}