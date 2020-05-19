package com.amarsoft.app.ems.system.cs.dto.teaminfodto;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.avta.annotation.TemplateBody;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 团队员工信息查询请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
public class TeamInfoUserQueryReq implements Serializable {
	  private static final long serialVersionUID = 1L;
	@Description("所属部门")
    @Length(max=40)
    @ActualColumn("UserBelong.orgId")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String orgId;
}
