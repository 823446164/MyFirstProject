package com.amarsoft.app.ems.system.template.cs.dto.roleinfodto;

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
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.SystemStatus;

/**
 * 角色信息Info
 * @author cmhuang
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "RoleInfoDto", name = "角色信息Info", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 2)
public class RoleInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("角色编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("RI.roleId")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String roleId;

    @Description("角色名称")
    @Length(max=80)
    @NotEmpty
    @ActualColumn("RI.roleName")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String roleName;

    @Description("角色状态")
    @Length(max=1)
    @Enum(SystemStatus.class)
    @NotEmpty
    @ActualColumn("RI.status")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Radio, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String status;
}
