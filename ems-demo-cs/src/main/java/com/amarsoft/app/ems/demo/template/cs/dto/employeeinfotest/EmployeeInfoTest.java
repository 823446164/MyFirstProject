package com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.avta.annotation.TemplateHeader;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.avta.annotation.TemplateBody;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.amps.acsc.annotation.FunctionCode;
import com.amarsoft.aecd.parameter.constant.ChildRankNo;
import com.amarsoft.aecd.employee.constant.EmployeeStatus;
import com.amarsoft.aecd.function.impl.CodeCacheFunction;
import com.amarsoft.aecd.employee.constant.EmployeeDucation;

/**
 * 员工信息Info
 * @author jfan5
 */
@Getter
@Setter
@ToString
@TemplateHeader(id = "EmployeeInfoTest", name = "员工信息Info", type = com.amarsoft.aecd.common.constant.TemplateType.Info, readOnly = false, span = 2)
public class EmployeeInfoTest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("员工编号")
    @Length(max=40)
    @ActualColumn("TIJ.employeeNo")
    @TemplateBody(sortNo = 0, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String employeeNo;

    @Description("员工姓名")
    @Length(max=80)
    @ActualColumn("TIJ.employeeName")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String employeeName;

    @Description("员工账号")
    @Length(max=100)
    @ActualColumn("TIJ.employeeAcct")
    @TemplateBody(sortNo = 2, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String employeeAcct;

    @Description("手机号码")
    @Length(max=40)
    @ActualColumn("TIJ.phoneNum")
    @TemplateBody(sortNo = 3, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String phoneNum;

    @Description("当前职级")
    @Length(max=10)
    @Enum(ChildRankNo.class)
    @ActualColumn("TIJ.nowRank")
    @TemplateBody(sortNo = 4, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String nowRank;

    @Description("目标职级")
    @Length(max=10)
    @Enum(ChildRankNo.class)
    @ActualColumn("TIJ.goalRank")
    @TemplateBody(sortNo = 5, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String goalRank;

    @Description("入职日期")
    @Length(max=10)
    @ActualColumn("TIJ.rntryTime")
    @TemplateBody(sortNo = 6, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String rntryTime;

    @Description("转正日期")
    @Length(max=10)
    @ActualColumn("TIJ.changeTime")
    @TemplateBody(sortNo = 7, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String changeTime;

    @Description("员工状态")
    @Length(max=10)
    @Enum(EmployeeStatus.class)
    @ActualColumn("TIJ.employeeStatus")
    @TemplateBody(sortNo = 8, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Radio, htmlStyle = "", isVisible = true, isReadOnly = false, span = 2, groupIndex = -1)
    private String employeeStatus;

    @Description("离职原因")
    @Length(max=2000)
    @ActualColumn("TIJ.resignationReason")
    @TemplateBody(sortNo = 9, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String resignationReason;

    @Description("学历")
    @Length(max=10)
    @Enum(EmployeeDucation.class)
    @ActualColumn("TIJ.employeeeDucation")
    @TemplateBody(sortNo = 10, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String employeeeDucation;

    @Description("毕业时间")
    @Length(max=20)
    @ActualColumn("TIJ.graduationTime")
    @TemplateBody(sortNo = 11, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Date, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String graduationTime;

    @Description("毕业院校")
    @Length(max=80)
    @ActualColumn("TIJ.graduatedSchool")
    @TemplateBody(sortNo = 12, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String graduatedSchool;

    @Description("专业")
    @Length(max=40)
    @ActualColumn("TIJ.major")
    @TemplateBody(sortNo = 13, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = true, isReadOnly = true, span = 1, groupIndex = -1)
    private String major;

    @Description("籍贯")
    @Length(max=40)
    @ActualColumn("TIJ.homeTown")
    @FunctionCode(value = CodeCacheFunction.class,paramKeys = {"codeno"},paramValues = {"AreaCode"})
    @TemplateBody(sortNo = 14, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.SingleTree, htmlStyle = "", isVisible = true, isReadOnly = false, span = 1, groupIndex = -1)
    private String homeTown;

    @Description("登记人")
    @Length(max=40)
    @ActualColumn("TIJ.inputUserId")
    @TemplateBody(sortNo = 15, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputUserId;

    @Description("登记时间")
    @Length(max=20)
    @ActualColumn("TIJ.inputTime")
    @TemplateBody(sortNo = 16, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String inputTime;

    @Description("登记机构")
    @Length(max=40)
    @ActualColumn("TIJ.inputOrgId")
    @TemplateBody(sortNo = 17, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String inputOrgId;

    @Description("更新人")
    @Length(max=40)
    @ActualColumn("TIJ.updateUserId")
    @TemplateBody(sortNo = 18, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateUserId;

    @Description("更新时间")
    @Length(max=20)
    @ActualColumn("TIJ.updateTime")
    @TemplateBody(sortNo = 19, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String updateTime;

    @Description("更新机构")
    @Length(max=40)
    @ActualColumn("TIJ.updateOrgId")
    @TemplateBody(sortNo = 20, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = true, span = 1, groupIndex = -1)
    private String updateOrgId;

    @Description("员工工作状态")
    @Length(max=10)
    @Enum(EmployeeStatus.class)
    @ActualColumn("TIJ.employeeWorkStatus")
    @TemplateBody(sortNo = 21, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = false, isReadOnly = false, span = 1, groupIndex = -1)
    private String employeeWorkStatus;
    
    @Description("员工照片")
    @Length(max=10000000)
    @ActualColumn("TIJ.employeePhoto")
    @TemplateBody(sortNo = 22, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Text, htmlStyle = "", isVisible = false, isReadOnly = false, span = 2, groupIndex = -1)
    private String employeePhoto;
    
}
