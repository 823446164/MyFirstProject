package com.amarsoft.app.ems.system.cs.dto.teaminfodto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.avta.annotation.TemplateHeader;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 团队信息
 * @author hpli
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "TeamInfoDto", name = "团队信息", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 2)
public class TeamInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("团队编号")
    @Length(max=40)
    @ActualColumn("TINFO.teamId")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String teamId;
    @Description("团队名称")
    @Length(max=80)
    @ActualColumn("TINFO.teamName")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String teamName;

    @Description("团队A角")
    @Length(max=80)
    @ActualColumn("TINFO.roleA")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String roleA;
    @Description("团队B角")
    @Length(max=240)
    @ActualColumn("TINFO.roleB")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String roleB;

    @Description("团队C角")
    @Length(max=240)
    @ActualColumn("TINFO.roleC")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String roleC;

    
    @Description("所属部门")
    @Length(max=40)
    @ActualColumn("TINFO.belongOrgId")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String belongOrgId;

    @Description("团队状态")
    @Length(max=1)
    @NotEmpty
    @ActualColumn("TINFO.status")
    @TemplateBody(sortNo = 6, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String status;
    @Description("团队人数")
    @TemplateBody(sortNo = 7, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 2, groupIndex = -1)
    private int count;
    
    @Description("团队绩效目标")
    @Length(max=100)
    @ActualColumn("TINFO.target")
    @TemplateBody(sortNo = 8, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.TextArea, htmlStyle = "", isVisible = true, isReadOnly =false, span = 2, groupIndex = -1)
    private String target;

    @Description("团队说明")
    @Length(max=400)
    @ActualColumn("TINFO.description")
    @TemplateBody(sortNo = 9, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.TextArea, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String description;
    
  
    
}
