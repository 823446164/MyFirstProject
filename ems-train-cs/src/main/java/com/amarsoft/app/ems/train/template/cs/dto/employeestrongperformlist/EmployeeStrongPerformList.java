package com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist;

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
 * 员工强化培训表现
 * @author xphe
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "EmployeeStrongPerformList", name = "员工强化培训表现", type = com.amarsoft.aecd.common.constant.TemplateType.List, readOnly = true, span = 1, export = true)
public class EmployeeStrongPerformList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("代码号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("CL.codeNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, isSorted = false, isFilter = true, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String codeNo;

    @Description("流水号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TP.serialNo")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, isSorted = false, isFilter = true, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String serialNo;

    @Description("模块编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TP.itemNo")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, isSorted = false, isFilter = true, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String itemNo;

    @Description("模块名称")
    @Length(max=80)
    @ActualColumn("CL.itemName")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, isSorted = false, isFilter = false, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String itemName;

    @Description("能力描述")
    @Length(max=400)
    @ActualColumn("TP.capabilityDes")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, isSorted = false, isFilter = false, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String capabilityDes;

    @Description("评分")
    @Length(max=20)
    @ActualColumn("TP.modelScore")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, isSorted = false, isFilter = false, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String modelScore;
}
