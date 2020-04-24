/**
 * 查询工作笔记
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.worknotequery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;

@Getter
@Setter
@ToString
public class WorkNoteQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("笔记编号")
    @Length(max=40)
    private String workId;
    @Description("用户编号")
    @Length(max=40)
    private String userId;
    @Description("日历日期")
    @Length(max=10)
    private String calendarDate;
    @Description("计划内容")
    @Length(max=400)
    private String workContent;
    @Description("实际执行情况")
    @Length(max=400)
    private String executeSituation;
    @Description("未完成理由")
    @Length(max=400)
    private String reason;
    @Description("备注")
    @Length(max=400)
    private String remark;
}