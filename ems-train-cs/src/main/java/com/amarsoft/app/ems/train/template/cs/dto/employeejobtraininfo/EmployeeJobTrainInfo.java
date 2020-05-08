package com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo;

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
 * 在职培训详情
 * @author xphe
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "EmployeeJobTrainInfo", name = "在职培训详情", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 1)
public class EmployeeJobTrainInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("培训编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TC.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String serialNo;

    @Description("主题")
    @Length(max=200)
    @NotEmpty
    @ActualColumn("TC.theme")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String theme;

    @Description("讲师")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TC.lecturer")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String lecturer;

    @Description("日期")
    @Length(max=10)
    @ActualColumn("TC.trainDate")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String trainDate;

    @Description("开始时间")
    @Length(max=10)
    @ActualColumn("TC.startDate")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String startDate;

    @Description("结束时间")
    @Length(max=10)
    @ActualColumn("TC.endDate")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String endDate;

    @Description("状态")
    @Length(max=80)
    @ActualColumn("TC.trainStatus")
    @TemplateBody(sortNo = 6, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String trainStatus = "未开始";

    @Description("录入人")
    @Length(max=80)
    @ActualColumn("TC.recorder")
    @TemplateBody(sortNo = 7, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String recorder;

    @Description("录入时间")
    @Length(max=10)
    @ActualColumn("TC.recordDate")
    @TemplateBody(sortNo = 8, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String recordDate;
}
