package com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist;

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
 * 员工参与培训项目列表
 * @author xphe
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "EmployeeProjectExperienceList", name = "员工参与培训项目列表", type = com.amarsoft.aecd.common.constant.TemplateType.List, readOnly = true, span = 1, export = true)
public class EmployeeProjectExperienceList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("项目编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("PE.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, isSorted = false, isFilter = true, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String serialNo;

    @Description("项目名称")
    @Length(max=80)
    @ActualColumn("PE.projectName")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, isSorted = false, isFilter = false, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String projectName;
}
