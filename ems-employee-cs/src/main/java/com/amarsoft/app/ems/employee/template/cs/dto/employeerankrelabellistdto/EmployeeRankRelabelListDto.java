/*文件名：EmployeeRankRelabelListDto
 * 版权：Copyright by www.amarsoft.com
 * 描述：点击员工职级,查询职级对应标签
 * 修改人：dxiao
 * 修改时间：2020/05/19
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.avta.annotation.TemplateHeader;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.aecd.employee.constant.MasteryOne;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.avta.annotation.TemplateBody;
import com.amarsoft.amps.acsc.annotation.Enum;
/**
 * 员工职级标签关联
 * @author dxiao
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "EmployeeRankRelabelListDto", name = "员工职级标签关联表List", type = com.amarsoft.aecd.common.constant.TemplateType.List, readOnly = false, span = 1)
public class EmployeeRankRelabelListDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("流水编号")
    @Length(max=40)
    @ActualColumn("ERR.serialNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String serialNo;

    @Description("职级编号")
    @Length(max=40)
    @ActualColumn("ERR.rankNo")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String rankNo;

    @Description("标签编号")
    @Length(max=40)
    @ActualColumn("ER.labelNo")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String labelNo;
    
    @Description("标签名称")
    @Length(max=40)
    @ActualColumn("LC.labelName")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String labelName;

    @Description("掌握程度")
    @Enum(MasteryOne.class)
    @Length(max=10)
    @ActualColumn("ER.level")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String level;


    @Description("所属目录")
    @Length(max=40)
    @ActualColumn("ER.belongCatalog")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String belongCatalog;

}
