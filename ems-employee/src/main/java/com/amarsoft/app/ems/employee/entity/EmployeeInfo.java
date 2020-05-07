
package com.amarsoft.app.ems.employee.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Description("员工基本信息表")
@Entity
@Table(name = "EMPLOYEE_INFO")
@GeneratedKey(autoGenerateKey = true, allocationSize = 1000)
public class EmployeeInfo extends BusinessObject {
    private static final long serialVersionUID = 1L;

    @Description("员工编号")
    @Id
    @Column(name = "employeeNo", nullable = false, length = 40)
    private String employeeNo;

    @Description("员工姓名")
    @Column(name = "employeeName", nullable = false, length = 80)
    private String employeeName;
    
    @Description("性别")
    @Column(name = "sex", nullable = false, length = 10)
    private String sex;

    @Description("员工账号")
    @Column(name = "employeeAcct", nullable = false, length = 100)
    private String employeeAcct;

    @Description("手机号码")
    @Column(name = "phoneNum", nullable = false, length = 40)
    private String phoneNum;

    @Description("当前职级")
    @Column(name = "nowRank", nullable = false, length = 10)
    private String nowRank;

    @Description("目标职级")
    @Column(name = "goalRank", nullable = false, length = 10)
    private String goalRank;

    @Description("入职日期")
    @Column(name = "rntryTime", length = 10)
    private LocalDate rntryTime;

    @Description("转正日期")
    @Column(name = "changeTime", length = 10)
    private LocalDate changeTime;

    @Description("员工状态")
    @Column(name = "employeeStatus", nullable = false, length = 10)
    private String employeeStatus;

    @Description("离职原因")
    @Column(name = "resignationReason", length = 2000)
    private String resignationReason;

    @Description("学历")
    @Column(name = "employeeeDucation", nullable = false, length = 10)
    private String employeeeDucation;

    @Description("毕业时间")
    @Column(name = "graduationTime", length = 20)
    private LocalDateTime graduationTime;

    @Description("毕业院校")
    @Column(name = "graduatedSchool", nullable = false, length = 80)
    private String graduatedSchool;

    @Description("专业")
    @Column(name = "major", nullable = false, length = 40)
    private String major;

    @Description("籍贯")
    @Column(name = "homeTown", length = 40)
    private String homeTown;

    @Description("登记人")
    @Column(name = "inputUserId", length = 40)
    private String inputUserId;

    @Description("登记时间")
    @Column(name = "inputTime", length = 20)
    private LocalDateTime inputTime;

    @Description("登记机构")
    @Column(name = "inputOrgId", length = 40)
    private String inputOrgId;

    @Description("更新人")
    @Column(name = "updateUserId", length = 40)
    private String updateUserId;

    @Description("更新时间")
    @Column(name = "updateTime", length = 20)
    private LocalDateTime updateTime;

    @Description("更新机构")
    @Column(name = "updateOrgId", length = 40)
    private String updateOrgId;

    @Description("员工工作状态")
    @Column(name = "employeeWorkStatus", length = 10)
    private String employeeWorkStatus;

    public String getRntryTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateFormat.format);
        return rntryTime.format(sdf);
    }

    public String getChangeTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateFormat.format);
        return changeTime.format(sdf);
    }

    public String getGraduationTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return graduationTime.format(sdf);
    }

    public String getInputTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputTime.format(sdf);
    }

    public String getUpdateTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateTime.format(sdf);
    }

}
