
package com.amarsoft.app.ems.employee.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.acsc.annotation.Digits;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Description("员工职级情况表")
@Entity
@Table(
    name="EMPLOYEE_RANK")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeRank extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("职级编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo", nullable=false,length=40) 
    private String employeeNo;
      
    @Description("分类") 
    @Column(name = "classify", nullable=false,length=40) 
    private String classify;
      
    @Description("目标日期") 
    @Column(name = "goalDate",length=10) 
    private LocalDate goalDate;
      
    @Description("职级") 
    @Column(name = "rank", nullable=false,length=40) 
    private String rank;
    
    @Description("方向") 
    @Column(name = "direction", nullable=false,length=40) 
    private String direction;
      
    @Description("版本") 
    @Column(name = "rankVersion",length=40) 
    private String rankVersion;
      
    @Description("登记人") 
    @Column(name = "inputUserId",length=40) 
    private String inputUserId;
      
    @Description("登记时间") 
    @Column(name = "inputTime",length=20) 
    private LocalDateTime inputTime;
      
    @Description("登记机构") 
    @Column(name = "inputOrgId",length=40) 
    private String inputOrgId;
      
    @Description("更新人") 
    @Column(name = "updateUserId",length=40) 
    private String updateUserId;
      
    @Description("更新时间") 
    @Column(name = "updateTime",length=20) 
    private LocalDateTime updateTime;
      
    @Description("更新机构") 
    @Column(name = "updateOrgId",length=40) 
    private String updateOrgId;
      
    @Description("是否正式") 
    @Column(name = "rankIsFormal",length=10) 
    private String rankIsFormal;
      
    @Description("实际调整日期") 
    @Column(name = "changeDate",length=10) 
    private LocalDate changeDate;
    
    @Description("评定职级层") 
    @Column(name = "evaluateRankLevel",length=10) 
    private String evaluateRankLevel;
    
    @Description("本次调整额")
    @Column(name = "currentAdjustPay")
    @Digits(length=24,scale=2)
    private double currentAdjustPay;
    
    public String getGoalDate() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateFormat.format);
        return goalDate.format(sdf);
    }
    
    public String getInputTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputTime.format(sdf);
    }
    
    public String getUpdateTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateTime.format(sdf);
    }
    public String getChangeDate() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateFormat.format);
        return changeDate.format(sdf);
    }

}
