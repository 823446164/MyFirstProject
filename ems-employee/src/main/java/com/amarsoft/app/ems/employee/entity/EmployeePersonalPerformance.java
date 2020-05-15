
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
@Description("员工个人表现表")
@Entity
@Table(
    name="EMPLOYEE_PERSONAL_PERFORMANCE")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeePersonalPerformance extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("个人表现编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo", nullable=false,length=40) 
    private String employeeNo;
      
    @Description("项目编号") 
    @Column(name = "projectNo", nullable=false,length=40) 
    private String projectNo;
      
    @Description("参考因素") 
    @Column(name = "factors", nullable=false,length=100) 
    private String factors;
      
    @Description("能力描述") 
    @Column(name = "abilityDescribe", nullable=false,length=2000) 
    private String abilityDescribe;
      
    @Description("评分") 
    @Column(name = "score", nullable=false,length=40) 
    private String score;
      
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
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputTime.format(sdf);
    }

    public String getUpdateTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateTime.format(sdf);
    }
}
