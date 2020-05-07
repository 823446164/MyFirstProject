package com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 项目组人员变更信息删除请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
public class ProjectEmployeeChangeListDeleteReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("变更编号")
    @Length(max=40)
    @ActualColumn("PMC.changeNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, isSorted = false, isFilter = false, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String changeNo;
}
