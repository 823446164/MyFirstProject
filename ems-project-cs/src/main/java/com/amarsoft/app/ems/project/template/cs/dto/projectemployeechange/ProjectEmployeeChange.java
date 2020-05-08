package com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange;

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
 * 项目组人员变更信息
 * @author hpli
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "ProjectEmployeeChange", name = "项目组人员变更信息", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 1)
public class ProjectEmployeeChange implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("申请编号")
    @Length(max=40)
    @ActualColumn("PMC.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String serialNo;

    @Description("变更编号")
    @Length(max=40)
    @ActualColumn("PMC.changeNo")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String changeNo;

    @Description("变更类型")
    @Length(max=100)
    @ActualColumn("PMC.changeType")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String changeType;

    @Description("申请原因")
    @Length(max=40)
    @ActualColumn("PMC.applyReason")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String applyReason;
}
