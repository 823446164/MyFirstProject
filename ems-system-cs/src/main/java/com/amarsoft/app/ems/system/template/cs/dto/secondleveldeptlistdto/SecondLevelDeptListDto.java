package com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto;

import java.io.Serializable;

import com.amarsoft.aecd.system.constant.OrgStatus;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.avta.annotation.TemplateBody;
import com.amarsoft.amps.avta.annotation.TemplateHeader;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 二级部门信息List
 * @author zcluo
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "SecondLevelDeptListDto", name = "二级部门信息List", type = com.amarsoft.aecd.common.constant.TemplateType.List, readOnly = false, span = 2, export = true)
public class SecondLevelDeptListDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("部门名称")
    @Length(max=80)
    @ActualColumn("DT.deptName")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = true, isSorted = false, isFilter = false, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String deptName;

    @Description("部门经理")
    @Length(max=40)
    @ActualColumn("DT.deptManager")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, isSorted = false, isFilter = false, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String deptManager;
    
    @Description("部门人数")
    @Length(max=40)
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, isSorted = false, isFilter = false, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String deptUserNumber;
    
    @Description("部门编号")
    @Length(max=80)
    @ActualColumn("OI.orgId")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, isSorted = false, isFilter = false, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String orgId;
    
    @Description("部门状态")
    @Length(max=1)
    @Enum(OrgStatus.class)
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, isSorted = false, isFilter = false, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String status;
}
