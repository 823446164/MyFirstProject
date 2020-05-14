package com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.avta.annotation.TemplateHeader;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 标签能力描述Info
 * @author yrong
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "LabelDescribeInfo", name = "标签能力描述Info", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 1)
public class LabelDescribeInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("流水号")
    @Length(max=40)
    @ActualColumn("LD.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String serialNo;

    @Description("标签编号")
    @Length(max=40)
    @ActualColumn("LD.labelNo")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String labelNo;

    @Description("标签等级")
    @Length(max=40)
    @ActualColumn("LD.labelLevel")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String labelLevel;

    @Description("等级描述")
    @Length(max=2000)
    @ActualColumn("LD.levelDescribe")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String levelDescribe;

    @Description("登记人")
    @Length(max=40)
    @ActualColumn("LD.inputUserId")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String inputUserId;

    @Description("登记时间")
    @Length(max=40)
    @ActualColumn("LD.inputTime")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String inputTime;

    @Description("登记机构")
    @Length(max=40)
    @ActualColumn("LD.inputOrgId")
    @TemplateBody(sortNo = 6, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String inputOrgId;

    @Description("更新人")
    @Length(max=40)
    @ActualColumn("LD.updateUserId")
    @TemplateBody(sortNo = 7, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String updateUserId;

    @Description("更新时间")
    @Length(max=40)
    @ActualColumn("LD.updateTime")
    @TemplateBody(sortNo = 8, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String updateTime;

    @Description("更新机构")
    @Length(max=40)
    @ActualColumn("LD.updateOrgId")
    @TemplateBody(sortNo = 9, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String updateOrgId;
}
