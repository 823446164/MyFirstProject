
package com.amarsoft.app.ems.employee.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Description("员工项目经历表")
@Entity
@Table(
    name="EMPLOYEE_PROJECT_EXPERIENCE")
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
    @CreatedBy
    @Column(name = "inputUserid",length=40) 
    private String inputUserid;
    
    @CreatedDate  
    @Description("登记时间")     
    @Column(name = "inputTime",length=20) 
    private LocalDateTime inputTime;
      
    @Description("登记机构") 
    @Column(name = "inputOrgId",length=40) 
    private String inputOrgId;
      
    @Description("更新人")
    @LastModifiedBy
    @Column(name = "updateuserid",length=40) 
    private String updateuserid;
      
    @Description("更新时间") 
    @LastModifiedDate
    @Column(name = "updatetime",length=20) 
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
    
    public String getInputTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputTime.format(sdf);
    }
    
    public String getUpdateTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updatetime.format(sdf);
    }

}
