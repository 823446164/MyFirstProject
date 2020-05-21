package com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.avta.annotation.TemplateHeader;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.aecd.parameter.constant.RankName;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 员工职级调整申请详情Info
 * @author xucheng
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "EmployeeRankChangeApplyInfoDto", name = "员工职级调整申请详情Info", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 1)
public class EmployeeRankChangeApplyInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("员工编号")
    @Length(max=40)
    @ActualColumn("EI.employeeNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String employeeNo;

    @Description("员工姓名")
    @Length(max=80)
    @ActualColumn("EI.employeeName")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String employeeName;

    @Description("员工账号")
    @Length(max=100)
    @ActualColumn("EI.employeeAcct")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String employeeAcct;

    @Description("申请编号")
    @Length(max=80)
    @ActualColumn("ERA.serialNo")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String serialNo;

    @Enum(RankName.class)
    @Description("当前职级")
    @Length(max=80)
    @ActualColumn("ERA.rankName")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String rankName;

    @Enum(RankName.class)
    @Description("考核职级")
    @Length(max=80)
    @ActualColumn("ERA.updateRankName")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateRankName;

    @Description("计划考核时间")
    @Length(max=20)
    @ActualColumn("ERA.rankTime")
    @TemplateBody(sortNo = 6, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Date, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String rankTime;

    @Description("登记人")
    @Length(max=40)
    @ActualColumn("ERA.inputUserId")
    @TemplateBody(sortNo = 7, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputUserId;

    @Description("登记时间")
    @Length(max=20)
    @ActualColumn("ERA.inputTime")
    @TemplateBody(sortNo = 8, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputTime;

    @Description("登记机构")
    @Length(max=40)
    @ActualColumn("ERA.inputOrgId")
    @TemplateBody(sortNo = 9, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputOrgId;

    @Description("更新人")
    @Length(max=40)
    @ActualColumn("ERA.updateUserId")
    @TemplateBody(sortNo = 10, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateUserId;

    @Description("更新时间")
    @Length(max=20)
    @ActualColumn("ERA.updateTime")
    @TemplateBody(sortNo = 11, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateTime;

    @Description("更新机构")
    @Length(max=40)
    @ActualColumn("ERA.updateOrgId")
    @TemplateBody(sortNo = 12, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateOrgId;
}
