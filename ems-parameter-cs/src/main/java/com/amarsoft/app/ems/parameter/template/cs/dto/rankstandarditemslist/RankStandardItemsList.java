/*
 * 文件名：RankStandardItemsList.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：职级指标list模板RankStandardCatalogSonInfo
 * 修改人：xphe
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.avta.annotation.TemplateHeader;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.aecd.employee.constant.MasteryTwo;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * @author xphe
 * @version 2020年5月13日
 * @see RankStandardItemsList
 * @since
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "RankStandardItemsList", name = "技能列表", type = com.amarsoft.aecd.common.constant.TemplateType.List, readOnly = false, span = 1)
public class RankStandardItemsList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("指标编号")
    @Length(max=40)
    @ActualColumn("RSI.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String serialNo;

    @Description("职级编号")
    @Length(max=40)
    @ActualColumn("RSI.rankNo")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String rankNo;

    @Description("标签名称")
    @Length(max=40)
    @ActualColumn("RSI.labelName")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String labelName;
    
    @Description("标签名称")
    @Length(max=40)
    @ActualColumn("RSI.labelNo")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String labelNo;
    
    @Description("所属目录")
    @Length(max=40)
    @ActualColumn("RSI.parentNo")
    @TemplateBody(sortNo = 10, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String parentNo;
    
    @Enum(MasteryTwo.class)
    @Description("标签等级")
    @Length(max=40)
    @ActualColumn("RSI.labelLevel")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String labelLevel;



}
