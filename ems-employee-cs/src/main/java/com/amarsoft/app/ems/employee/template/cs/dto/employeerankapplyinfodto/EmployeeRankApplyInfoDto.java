package com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.avta.annotation.TemplateHeader;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 员工职级申请Info
 * @author lding
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "EmployeeRankApplyInfoDto", name = "员工职级申请Info", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 1)
public class EmployeeRankApplyInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("申请编号")
    @Length(max=40)
    @ActualColumn("ERA.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String serialNo;

    @Description("职级编号")
    @Length(max=40)
    @ActualColumn("ERA.rankNo")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String rankNo;

    @Description("员工编号")
    @Length(max=40)
    @ActualColumn("ERA.employeeNo")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String employeeNo;

    @Description("员工姓名")
    @Length(max=80)
    @ActualColumn("ERA.employeeName")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String employeeName;

    @Description("员工现职级")
    @Length(max=40)
    @ActualColumn("ERA.rankName")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String rankName;

    @Description("员工变更职级")
    @Length(max=40)
    @ActualColumn("ERA.updateRankName")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateRankName;

    @Description("团队负责人")
    @Length(max=40)
    @ActualColumn("ERA.teamManager")
    @TemplateBody(sortNo = 6, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String teamManager;

    @Description("变更人")
    @Length(max=40)
    @ActualColumn("ERA.changePerson")
    @TemplateBody(sortNo = 7, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String changePerson;

    @Description("发起时间")
    @Length(max=20)
    @ActualColumn("ERA.beginTime")
    @TemplateBody(sortNo = 8, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String beginTime;

    @Description("变更理由")
    @Length(max=2000)
    @ActualColumn("ERA.changeReason")
    @TemplateBody(sortNo = 9, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String changeReason;

    @Description("登记人")
    @Length(max=40)
    @ActualColumn("ERA.inputUserId")
    @TemplateBody(sortNo = 10, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputUserId;

    @Description("登记时间")
    @Length(max=20)
    @ActualColumn("ERA.inputTime")
    @TemplateBody(sortNo = 11, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputTime;

    @Description("登记机构")
    @Length(max=40)
    @ActualColumn("ERA.inputOrgId")
    @TemplateBody(sortNo = 12, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputOrgId;

    @Description("更新人")
    @Length(max=40)
    @ActualColumn("ERA.updateUserId")
    @TemplateBody(sortNo = 13, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateUserId;

    @Description("更新时间")
    @Length(max=20)
    @ActualColumn("ERA.updateTime")
    @TemplateBody(sortNo = 14, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateTime;

    @Description("更新机构")
    @Length(max=40)
    @ActualColumn("ERA.updateOrgId")
    @TemplateBody(sortNo = 15, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateOrgId;
}
