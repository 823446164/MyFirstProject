/*
 * 文件名：TreeLabel.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel;

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
 * 〈标签列表〉
 * 〈点击标签选择之后出现的大标签list〉
 * @author xphe
 * @version 2020年5月13日
 * @see TreeLabel
 * @since
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "TreeLabelList", name = "树图下大标签List", type = com.amarsoft.aecd.common.constant.TemplateType.List, readOnly = false, span = 1, export = true)
public class TreeLabel implements Serializable{
    @Description("职级编号")
    @Length(max=40)
    @ActualColumn("RSC.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, groupIndex = -1)
    private String rankSerialNo;
    
    @Description("标签编号")
    @Length(max=40)
    @ActualColumn("LC.serialNo")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, groupIndex = -1)
    private String serialNo;
    
    @Description("标签名称")
    @Length(max=40)
    @ActualColumn("LC.labelName")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, groupIndex = -1)
    private String labelName;
    
    @Description("所属目录")
    @Length(max=40)
    @ActualColumn("LC.belongCatalog")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, groupIndex = -1)
    private String belongCatalog;
}
