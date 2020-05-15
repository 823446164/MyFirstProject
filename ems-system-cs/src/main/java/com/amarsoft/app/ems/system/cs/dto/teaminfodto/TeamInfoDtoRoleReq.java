/*
 * 文件名：团队负责人请求体
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：hpli
 * 修改时间：2020年5月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.system.cs.dto.teaminfodto;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.avta.annotation.TemplateBody;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 团队负责人请求体
 * @author hpli
 * @version 2020年5月15日
 * @see TeamInfoDtoRoleReq
 * @since 
 */
@Getter
@Setter
@ToString
public class TeamInfoDtoRoleReq   implements Serializable {
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

}
