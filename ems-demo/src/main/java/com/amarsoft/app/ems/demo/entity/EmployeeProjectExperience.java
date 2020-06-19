
package com.amarsoft.app.ems.demo.entity;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;

import javax.persistence.*;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Description("员工项目经历表")
@Entity
@Table(
    name="EMPLOYEE_PROJECT_EXPERIENCE_JFAN5")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeProjectExperience extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("项目编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo", nullable=false,length=40) 
    private String employeeNo;
      
    @Description("项目名称") 
    @Column(name = "projectName", nullable=false,length=100) 
    private String projectName;
      
    @Description("项目经理") 
    @Column(name = "projectMangerId", nullable=false,length=40) 
    private String projectMangerId;
      
    @Description("工作岗位") 
    @Column(name = "employeeJob", nullable=false,length=10) 
    private String employeeJob;
      
    @Description("入场日期") 
    @Column(name = "begainTime",length=10) 
    private LocalDate begainTime;
      
    @Description("离场日期") 
    @Column(name = "endTime",length=10) 
    private LocalDate endTime;
      
    @Description("工作描述") 
    @Column(name = "workDescribe", nullable=false,length=2000) 
    private String workDescribe;
      
    @Description("登记人") 
    @Column(name = "inputUserid",length=40) 
    private String inputUserid;
      
    @Description("登记时间") 
    @Column(name = "inputTime",length=30) 
    private LocalDateTime inputTime;
      
    @Description("登记机构") 
    @Column(name = "inputOrgId",length=40) 
    private String inputOrgId;
      
    @Description("更新人") 
    @Column(name = "updateuserid",length=40) 
    private String updateuserid;
      
    @Description("更新时间") 
    @Column(name = "updatetime",length=30) 
    private LocalDateTime updatetime;
      
    @Description("更新机构") 
    @Column(name = "updateorgid",length=40) 
    private String updateorgid;
    
    public String getBegainTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateFormat.format);
        return begainTime.format(sdf);
    }

    public String getEndTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateFormat.format);
        return endTime.format(sdf);
    }

    public String getUpdatetime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updatetime.format(sdf);
    }
    
    public String getInputTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputTime.format(sdf);
    }

}
