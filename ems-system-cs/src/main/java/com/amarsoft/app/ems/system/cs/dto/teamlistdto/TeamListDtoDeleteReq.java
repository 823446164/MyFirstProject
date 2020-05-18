package com.amarsoft.app.ems.system.cs.dto.teamlistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 团队信息删除请求实体类
 * @author hpli
 */

@Getter
@Setter
@ToString
public class TeamListDtoDeleteReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("团队编号")
    @Length(max=40)
    @ActualColumn("TINFO.teamId")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, isSorted = false, isFilter = true, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String teamId;
    @Length(max=40)
    @ActualColumn("ChangeEvent.objectNo")
    @Description("对象编号")
    public String  objectNo;
    
}
