
package com.amarsoft.app.ems.employee.entity;

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
@Description("团队调整申请表")
@Entity
@Table(
    name="EMPLOYEE_BELONG_CHANGE")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeBelongChange extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("申请编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("团队编号") 
    @Column(name = "teamNo",length=40) 
    private String teamNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo",length=40) 
    private String employeeNo;
      
    @Description("员工姓名") 
    @Column(name = "employeeName",length=80) 
    private String employeeName;
      
    @Description("员工账号") 
    @Column(name = "employeeAcct",length=100) 
    private String employeeAcct;
      
    @Description("申请类型") 
    @Column(name = "objectType",length=40) 
    private String objectType;
      
    @Description("部门系统管理编号") 
    @Column(name = "deptId",length=40) 
    private String deptId;
      
    @Description("部门系统管理员") 
    @Column(name = "deptName",length=40) 
    private String deptName;
      
    @Description("调整前所在的团队") 
    @Column(name = "beforeTeam",length=40) 
    private String beforeTeam;
      
    @Description("调整后所在的团队") 
    @Column(name = "afterTeam", nullable=false,length=40) 
    private String afterTeam;
      
    @Description("调整后所在团队负责人") 
    @Column(name = "changeManager",length=40) 
    private String changeManager;
      
    @Description("调整原因") 
    @Column(name = "adjustReason",length=2000) 
    private String adjustReason;
      
    @Description("登记人")
    @CreatedBy
    @Column(name = "inputUserId", length = 40)
    private String inputUserId;

    @Description("登记时间")
    @CreatedDate 
    @Column(name = "inputTime", length = 20)
    private LocalDateTime inputTime;

    @Description("登记机构")
    @Column(name = "inputOrgId", length = 40)
    private String inputOrgId;

    @Description("更新人")
    @LastModifiedBy
    @Column(name = "updateUserId", length = 40)
    private String updateUserId;

    @Description("更新时间")
    @LastModifiedDate
    @Column(name = "updateTime", length = 20)
    private LocalDateTime updateTime;

    @Description("更新机构")
    @Column(name = "updateOrgId", length = 40)
    private String updateOrgId;
    
    public String getInputTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputTime.format(dtf);
    }

    public String getUpdateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateTime.format(dtf);
    }
}
