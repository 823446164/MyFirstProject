/*
 * 文件名：RankStandardCatalogSonInfo.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogsoninfo;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.avta.annotation.TemplateBody;
import com.amarsoft.amps.avta.annotation.TemplateHeader;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xphe
 * @version 2020年5月9日
 * @see RankStandardCatalogSonInfo
 * @since
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "RankStandardCatalogSonInfo", name = "子职级标准详情", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 1)
public class RankStandardCatalogSonInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Description("职级编号")
    @Length(max=40)
    @ActualColumn("RSC.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String serialNo;
    
    @Description("职等")
    @Length(max=40)
    @ActualColumn("RSC.rankStandard")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String rankStandard;

    @Description("职级")
    @Length(max=40)
    @ActualColumn("RSC.rankName")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String rankName;

    @Description("子职级")
    @Length(max=40)
    @ActualColumn("RSC.childRankNo")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, isSorted = false, isFilter = true, isSum = false, filterType = {com.amarsoft.aecd.common.constant.QueryFilterType.EQUALS, com.amarsoft.aecd.common.constant.QueryFilterType.STARTSWITH, }, groupIndex = -1)
    private String childRankNo;

    @Description("能力要求")
    @Length(max=2000)
    @ActualColumn("RSC.ability")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
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
}
