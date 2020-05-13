package com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto;

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
 * 一级部门详情Info
 * @author zcluo
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "OneLevelDeptDto", name = "一级部门详情Info", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 2)
public class OneLevelDeptDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("部门编号")
    @Length(max=40)
    @ActualColumn("OI.orgId")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String orgId;

    @Description("部门名称")
    @Length(max=80)
    @ActualColumn("OI.orgName")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String orgName;

    @Description("上级机构编号")
    @Length(max=40)
    @ActualColumn("OI.parentOrgId")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String parentOrgId;
    
    @Description("部门经理")
    @Length(max=40)
//    @ActualColumn("OI.deptManager")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String deptManager;
    
    @Description("所属上级")
    @Length(max=80)
//    @ActualColumn("OI.parentOrgName")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String parentOrgName;
    
    @Description("下级部门数")
    @Length(max=40)
//    @ActualColumn("OI.parentOrgName")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 2, groupIndex = -1)
    private String secondOrgNumber;

}
