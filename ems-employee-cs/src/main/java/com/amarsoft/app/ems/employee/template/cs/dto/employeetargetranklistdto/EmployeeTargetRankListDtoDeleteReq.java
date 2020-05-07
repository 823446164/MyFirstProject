package com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 目标职级申请List删除请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
public class EmployeeTargetRankListDtoDeleteReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("职级变更编号")
    @Length(max=40)
    @ActualColumn("ETR.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, isSorted = false, isFilter = false, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String serialNo;
}
