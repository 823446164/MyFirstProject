package com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 在职培训列表删除请求实体类
 * @author xphe
 */
@Getter
@Setter
@ToString
public class EmployeeJobTrainListDeleteReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("培训编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TC.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, isSorted = false, isFilter = true, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String serialNo;
}
