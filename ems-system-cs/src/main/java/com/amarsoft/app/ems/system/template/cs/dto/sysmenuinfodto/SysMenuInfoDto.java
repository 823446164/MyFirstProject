/*
 * 文件名：SysMenuInfoDto.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：菜单dto类
 * 修改人：jcli2
 * 修改时间：2020年5月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.avta.annotation.TemplateHeader;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.aecd.system.constant.MenuStatus;
import com.amarsoft.aecd.system.constant.OrgStatus;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.amps.avta.annotation.TemplateBody;

/**
 * 菜单详情Info
 * @author jcli2
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "SysMenuInfoDto", name = "菜单详情Info", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 2)
public class SysMenuInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("菜单编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("MI.menuId")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String menuId;

    @Description("排序号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("MI.sortNo")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String sortNo;

    @Description("菜单名称")
    @Length(max=80)
    @NotEmpty
    @ActualColumn("MI.menuName")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String menuName;

    @Description("显示名称")
    @Length(max=80)
    @NotEmpty
    @ActualColumn("MI.menuTwName")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String menuTwName;

    @Description("url")
    @Length(max=80)
    @ActualColumn("MI.url")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String url;

    @Description("参数")
    @Length(max=400)
    @ActualColumn("MI.urlParam")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String urlParam;

    @Description("菜单图标")
    @Length(max=10)
    @ActualColumn("MI.icon")
    @TemplateBody(sortNo = 6, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String icon;

    @Description("菜单状态")
    @Length(max=1)
    @NotEmpty
    @Enum(MenuStatus.class)
    @ActualColumn("MI.status")
    @TemplateBody(sortNo = 7, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Radio, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String status;

    @Description("登记人")
    @Length(max=10)
    @NotEmpty
    @ActualColumn("MI.inputUserId")
    @TemplateBody(sortNo = 8, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputUserId;

    @Description("登记时间")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("MI.inputTime")
    @TemplateBody(sortNo = 9, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputTime;
    
    @Description("上级菜单编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("MI.parentId")
    @TemplateBody(sortNo = 10, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String parentId;
    
}
