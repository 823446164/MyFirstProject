/*
 * 文件名：RankStandardCatalogInfo.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo;

import java.io.Serializable;

import com.amarsoft.aecd.parameter.constant.ChildRankNo;
import com.amarsoft.aecd.parameter.constant.RankName;
import com.amarsoft.aecd.parameter.constant.RankStandard;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.avta.annotation.TemplateBody;
import com.amarsoft.amps.avta.annotation.TemplateHeader;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈职级标准详情〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "RankStandardCatalogInfo", name = "职级标准详情", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 2)
public class RankStandardCatalogInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("职级编号")
    @Length(max=40)
    @ActualColumn("RSC.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String serialNo;

    @Enum(RankStandard.class)
    @Description("职等")
    @NotEmpty
    @Length(max=40)
    @ActualColumn("RSC.rankStandard")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String rankStandard;

    @Enum(RankName.class)
    @Description("职级")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("RSC.rankName")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String rankName;

    @Enum(ChildRankNo.class)
    @Description("子职级")
    @Length(max=40)
    @ActualColumn("RSC.childRankNo")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = false, isReadOnly = true, isSorted = false, isFilter = true, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String childRankNo;

    @Description("能力要求")
    @Length(max=2000)
    @ActualColumn("RSC.ability")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String ability;

    @Description("技术职级描述")
    @Length(max=2000)
    @ActualColumn("RSC.rankDescribe")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.TextArea, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String rankDescribe;

    @Description("岗位职责描述")
    @Length(max=2000)
    @ActualColumn("RSC.responeDescribe")
    @TemplateBody(sortNo = 6, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.TextArea, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String responeDescribe;

    @Description("能力要求描述")
    @Length(max=2000)
    @ActualColumn("RSC.abilityDescribe")
    @TemplateBody(sortNo = 7, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.TextArea, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String abilityDescribe;

    @Description("所属团队")
    @Length(max=40)
    @ActualColumn("RSC.belongTeam")
    @TemplateBody(sortNo = 8, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String belongTeam;

    @Description("指标类型")
    @Length(max=40)
    @ActualColumn("RSC.rankType")
    @TemplateBody(sortNo = 9, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String rankType;

    @Description("登记人")
    @Length(max=40)
    @ActualColumn("RSC.inputUserId")
    @TemplateBody(sortNo = 10, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String inputUserId;

    @Description("登记时间")
    @Length(max=20)
    @ActualColumn("RSC.inputTime")
    @TemplateBody(sortNo = 11, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String inputTime;

    @Description("登记机构")
    @Length(max=40)
    @ActualColumn("RSC.inputOrgId")
    @TemplateBody(sortNo = 12, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String inputOrgId;

    @Description("更新人")
    @Length(max=40)
    @ActualColumn("RSC.updateUserId")
    @TemplateBody(sortNo = 13, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String updateUserId;

    @Description("更新时间")
    @Length(max=20)
    @ActualColumn("RSC.updateTime")
    @TemplateBody(sortNo = 14, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String updateTime;

    @Description("更新机构")
    @Length(max=40)
    @ActualColumn("RSC.updateOrgId")
    @TemplateBody(sortNo = 15, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String updateOrgId;
}
