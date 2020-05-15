
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
@Description("员工成长目标表")
@Entity
@Table(
    name="EMPLOYEE_DEVELOP_TARGET")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeDevelopTarget extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("成长目标编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo", nullable=false,length=40) 
    private String employeeNo;
      
    @Description("职级编号") 
    @Column(name = "rankNo", nullable=false,length=40) 
    private String rankNo;
      
    @Description("制定日期") 
    @Column(name = "designTime",length=10) 
    private LocalDate designTime;
      
    @Description("目标简述") 
    @Column(name = "targetDescribe",length=2000) 
    private String targetDescribe;
      
    @Description("预计达成日期") 
    @Column(name = "describeTime",length=10) 
    private LocalDate describeTime;
      
    @Description("目标制定人") 
    @Column(name = "designerId",length=40) 
    private String designerId;
      
    @Description("跟踪记录") 
    @Column(name = "record",length=2000) 
    private String record;
    
    @Description("跟踪记录人") 
    @Column(name = "traceUserId",length=40) 
    private String traceUserId;
      
    @Description("实现情况") 
    @Column(name = "implStatus",length=2000) 
    private String implStatus;
      
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
            
    public String getDesignTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateFormat.format);
        return designTime.format(sdf);
    }
    public String getDescribeTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateFormat.format);
        return describeTime.format(sdf);
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
