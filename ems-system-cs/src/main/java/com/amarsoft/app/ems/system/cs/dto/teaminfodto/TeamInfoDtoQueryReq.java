package com.amarsoft.app.ems.system.cs.dto.teaminfodto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.avta.annotation.TemplateBody;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.ActualColumn;

/**
 * 团队信息查询请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class TeamInfoDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("团队编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("TINFO.teamId")
    private String teamId;
    @Description("所属部门")
    @Length(max=40)
    @ActualColumn("TINFO.belongOrgId")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String belongOrgId;
    @Description("执行状态")
    public String  status;
   
    
}

