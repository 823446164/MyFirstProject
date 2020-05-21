/*
 * 文件名：LabelCatalogInfo.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：标签目录的info模板
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.avta.annotation.TemplateHeader;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.aecd.parameter.constant.LabelType;
import com.amarsoft.aecd.system.constant.LabelStatus;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 标签目录详情
 * @author yrong
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "LabelCatalogInfo", name = "标签目录详情", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 2)
public class LabelCatalogInfo  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("标签编号")
    @Length(max=40)
    @ActualColumn("LC.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String serialNo;

    @Description("标签名称")
    @Length(max=80)
    @NotEmpty
    @ActualColumn("LC.labelName")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String labelName;
    
    @Description("所属大类")
    @Length(max=40)
    @ActualColumn("LC.rootNo")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String rootNo;
    
    @Description("所属父类")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("LC.parentNo")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String parentNo;
    
    @Enum(LabelStatus.class)
    @Description("标签状态")
    @Length(max=40)
    @ActualColumn("LC.labelStatus")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Radio, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String labelStatus;

    @Description("目录备注")
    @Length(max=2000)
    @ActualColumn("LC.catalogRemark")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String catalogRemark;

    @Description("版本")
    @Length(max=10)
    @ActualColumn("LC.labelVersion")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String labelVersion;
    
    @Enum(LabelType.class)
    @Description("标签类型")
    @Length(max=10)
    @NotEmpty
    @ActualColumn("LC.labelType")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Radio, htmlStyle = "", isVisible = true, isReadOnly = true, span = 2, groupIndex = -1)
    private String labelType;

    @Description("登记人")
    @Length(max=40)
    @ActualColumn("LC.inputUserId")
    @TemplateBody(sortNo = 6, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputUserId;

    @Description("登记时间")
    @Length(max=20)
    @ActualColumn("LC.inputTime")
    @TemplateBody(sortNo = 7, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputTime;

    @Description("登记机构")
    @Length(max=40)
    @ActualColumn("LC.inputOrgId")
    @TemplateBody(sortNo = 8, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputOrgId;

    @Description("更新人")
    @Length(max=40)
    @ActualColumn("LC.updateUserId")
    @TemplateBody(sortNo = 9, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateUserId;

    @Description("更新时间")
    @Length(max=20)
    @ActualColumn("LC.updateTime")
    @TemplateBody(sortNo = 10, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateTime;

    @Description("更新机构")
    @Length(max=40)
    @ActualColumn("LC.updateOrgId")
    @TemplateBody(sortNo = 11, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateOrgId;
  
}
