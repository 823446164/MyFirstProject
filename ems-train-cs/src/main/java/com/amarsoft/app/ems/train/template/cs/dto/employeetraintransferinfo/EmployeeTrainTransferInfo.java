package com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo;

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
 * 培训转移记录
 * @author xphe
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "EmployeeTrainTransferInfo", name = "培训转移记录", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 1)
public class EmployeeTrainTransferInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("流水号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TT.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String serialNo;

    @Description("员工姓名")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TT.employeeName")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String employeeName;

    @Description("转移类型")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TT.transferType")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String transferType;

    @Description("项目")
    @Length(max=40)
    @ActualColumn("TT.projectNo")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String projectNo;

    @Description("团队")
    @Length(max=40)
    @ActualColumn("TT.teamNo")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String teamNo;

    @Description("转移原因")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TT.transferReason")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String transferReason;

    @Description("转移人")
    @Length(max=80)
    @ActualColumn("TT.transfer")
    @TemplateBody(sortNo = 6, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String transfer;

    @Description("转移时间")
    @Length(max=20)
    @ActualColumn("TT.transferDate")
    @TemplateBody(sortNo = 7, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String transferDate;
}
