/*
 * 文件名：LabelInfo.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：标签info模板
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo;

import java.io.Serializable;
import com.amarsoft.amps.acsc.annotation.Enum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.avta.annotation.TemplateHeader;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.aecd.system.constant.ApplyType;
import com.amarsoft.aecd.system.constant.LabelStatus;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 标签Info
 * @author yrong
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "LabelInfo", name = "标签Info", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 1)
public class LabelInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("标签编号")
    @Length(max=40)
    @ActualColumn("LC.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String serialNo;

    @Description("标签名称")
    @Length(max=80)
    @ActualColumn("LC.labelName")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String labelName;

    @Description("标签码值")
    @Length(max=80)
    @ActualColumn("LC.codeNo")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String codeNo;

    @Enum(LabelStatus.class)
    @Description("标签状态")
    @Length(max=10)
    @ActualColumn("LC.labelStatus")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Radio, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String labelStatus;

    @Description("所属目录")
    @Length(max=80)
    @ActualColumn("LC.belongCataLog")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String belongCataLog;

    @Description("所属大类")
    @Length(max=40)
    @ActualColumn("LC.rootNo")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String rootNo;
    
    @Enum(ApplyType.class)
    @Description("适用要求类别")
    @Length(max=10)
    @ActualColumn("LC.abilityType")
    @TemplateBody(sortNo = 6, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Radio, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String abilityType;

    @Description("标签说明")
    @Length(max=2000)
    @ActualColumn("LC.labelDescribe")
    @TemplateBody(sortNo = 7, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.TextArea, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String labelDescribe;

    @Description("版本")
    @Length(max=10)
    @ActualColumn("LC.labelVersion")
    @TemplateBody(sortNo = 8, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String labelVersion;

    @Description("标签编号")
    @Length(max=40)
    @ActualColumn("LD.labelNo")
    @TemplateBody(sortNo = 9, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String labelNo;

    @Description("标签等级")
    @Length(max=40)
    @ActualColumn("LD.labelLevel")
    @TemplateBody(sortNo = 10, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String labelLevel;

    @Description("登记人")
    @Length(max=40)
    @ActualColumn("LC.inputUserId")
    @TemplateBody(sortNo = 11, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String LC_InputUserId;

    @Description("登记时间")
    @Length(max=20)
    @ActualColumn("LC.inputTime")
    @TemplateBody(sortNo = 12, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String LC_InputTime;

    @Description("登记机构")
    @Length(max=40)
    @ActualColumn("LC.inputOrgId")
    @TemplateBody(sortNo = 13, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String LC_InputOrgId;

    @Description("更新人")
    @Length(max=40)
    @ActualColumn("LC.updateUserId")
    @TemplateBody(sortNo = 14, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String LC_UpdateUserId;

    @Description("更新时间")
    @Length(max=20)
    @ActualColumn("LC.updateTime")
    @TemplateBody(sortNo = 15, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String LC_UpdateTime;

    @Description("更新机构")
    @Length(max=40)
    @ActualColumn("LC.updateOrgId")
    @TemplateBody(sortNo = 16, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String LC_UpdateOrgId;

    @Description("标签类型")
    @Length(max=40)
    @ActualColumn("LC.labelType")
    @TemplateBody(sortNo = 16, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String LC_labelType;
    
    @Description("登记人")
    @Length(max=40)
    @ActualColumn("LD.inputUserId")
    @TemplateBody(sortNo = 17, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String LD_InputUserId;

    @Description("登记时间")
    @Length(max=20)
    @ActualColumn("LD.inputTime")
    @TemplateBody(sortNo = 18, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String LD_InputTime;

    @Description("登记机构")
    @Length(max=40)
    @ActualColumn("LD.inputOrgId")
    @TemplateBody(sortNo = 19, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String LD_InputOrgId;

    @Description("更新人")
    @Length(max=40)
    @ActualColumn("LD.updateUserId")
    @TemplateBody(sortNo = 20, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String LD_UpdateUserId;

    @Description("更新时间")
    @Length(max=20)
    @ActualColumn("LD.updateTime")
    @TemplateBody(sortNo = 21, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String LD_UpdateTime;

    @Description("更新机构")
    @Length(max=40)
    @ActualColumn("LD.updateOrgId")
    @TemplateBody(sortNo = 22, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String LD_UpdateOrgId;
  
    @Description("精通")
    @Length(max=2000)
    @TemplateBody(sortNo = 23, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String masterDescribe;

    @Description("熟练使用")
    @Length(max=2000)
    @TemplateBody(sortNo = 24, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String skilledDescribe;

    @Description("熟悉")
    @Length(max=2000)
    @TemplateBody(sortNo = 25, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String familiarDescribe;

    @Description("了解")
    @Length(max=2000)
    @TemplateBody(sortNo = 26, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String knowDescribe;

    @Description("优秀")
    @Length(max=2000)
    @TemplateBody(sortNo = 27, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String excellentDescribe;

    @Description("良好")
    @Length(max=2000)
    @TemplateBody(sortNo = 28, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String goodDescribe;

    @Description("一般")
    @Length(max=2000)
    @TemplateBody(sortNo = 29, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String commonlyDescribe;
    
}
