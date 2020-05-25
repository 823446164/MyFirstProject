/*
 * 文件名：powertolabel.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：yrong
 * 修改时间：2020年5月22日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.powertolabel;


import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.avta.annotation.TemplateBody;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 标签管理权限模板
 * 
 * @author yrong
 * @version 2020年5月22日
 * @see PowerToLabel
 * @since
 */
@Getter
@Setter
@ToString
public class PowerToLabel {
    @Description("用户编号")
    @Length(max=40)
    @ActualColumn("LC.userId")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String userId;

    
}
