package com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.avta.annotation.TemplateHeader;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 追踪内容详情
 * @author xphe
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "EmployeeTraceDetailInfo", name = "追踪内容详情", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 2)
public class EmployeeTraceDetailInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("流水号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TD.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String serialNo;

    @Description("员工编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TD.employeeNo")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String employeeNo;

    @Description("追踪编号")
    @Length(max=80)
    @ActualColumn("TD.traceNo")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String traceNo;

    @Description("内容序号")
    @Length(max=80)
    @NotEmpty
    @ActualColumn("TD.dataNo")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String dataNo;

    @Description("填写时间")
    @Length(max=10)
    @ActualColumn("TD.traceDate")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String traceDate;

    @Description("关注级别")
    @Length(max=80)
    @ActualColumn("TD.attention")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Radio, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String attention;

    @Description("是否继续追踪")
    @Length(max=80)
    @ActualColumn("TD.isContinue")
    @TemplateBody(sortNo = 6, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Radio, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String isContinue;

    @Description("追踪人")
    @Length(max=80)
    @ActualColumn("TD.tracker")
    @TemplateBody(sortNo = 7, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String tracker;

    @Description("反馈人员")
    @Length(max=80)
    @ActualColumn("TD.feedbacker")
    @TemplateBody(sortNo = 8, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String feedbacker;

    @Description("备注")
    @Length(max=80)
    @ActualColumn("TD.remark")
    @TemplateBody(sortNo = 9, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String remark;

    @Description("代码质量")
    @Length(max=80)
    @ActualColumn("TD.codeQuality")
    @TemplateBody(sortNo = 10, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String codeQuality;

    @Description("任务进度")
    @Length(max=80)
    @ActualColumn("TD.taskProgress")
    @TemplateBody(sortNo = 11, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String taskProgress;

    @Description("主动性")
    @Length(max=80)
    @ActualColumn("TD.initiative")
    @TemplateBody(sortNo = 12, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String initiative;

    @Description("责任心")
    @Length(max=80)
    @ActualColumn("TD.responsibility")
    @TemplateBody(sortNo = 13, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String responsibility;

    @Description("出勤情况")
    @Length(max=80)
    @ActualColumn("TD.attendance")
    @TemplateBody(sortNo = 14, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String attendance;

    @Description("所学业务知识")
    @Length(max=80)
    @ActualColumn("TD.businessKnow")
    @TemplateBody(sortNo = 15, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String businessKnow;

    @Description("心理素质")
    @Length(max=80)
    @ActualColumn("TD.psychology")
    @TemplateBody(sortNo = 16, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String psychology;

    @Description("工作协调互助")
    @Length(max=80)
    @ActualColumn("TD.workHelp")
    @TemplateBody(sortNo = 17, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String workHelp;

    @Description("沟通情况")
    @Length(max=80)
    @ActualColumn("TD.communicate")
    @TemplateBody(sortNo = 18, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String communicate;

    @Description("知识(资源)共享")
    @Length(max=80)
    @ActualColumn("TD.knowShare")
    @TemplateBody(sortNo = 19, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String knowShare;

    @Description("参与团建情况")
    @Length(max=80)
    @ActualColumn("TD.participation")
    @TemplateBody(sortNo = 20, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String participation;
}
