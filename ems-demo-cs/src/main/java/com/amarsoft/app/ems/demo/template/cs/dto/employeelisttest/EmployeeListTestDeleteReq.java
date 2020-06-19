package com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 员工信息List删除请求实体类
 * @author jfan5
 */
@Getter
@Setter
@ToString
public class EmployeeListTestDeleteReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("员工编号")
    @Length(max=40)
    @ActualColumn("TIJ.employeeNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, isSorted = true, isFilter = true, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String employeeNo;
}
