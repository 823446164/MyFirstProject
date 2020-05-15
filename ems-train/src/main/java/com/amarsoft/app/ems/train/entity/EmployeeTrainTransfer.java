
package com.amarsoft.app.ems.train.entity;

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
@Description("培训转移表")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(
    name="EMPLOYEE_TRAIN_TRANSFER")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeTrainTransfer extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("流水号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo", nullable=false,length=40) 
    private String employeeNo;
      
    @Description("员工姓名") 
    @Column(name = "employeeName", nullable=false,length=80) 
    private String employeeName;
      
    @Description("转移类型") 
    @Column(name = "transferType",length=10) 
    private String transferType;
      
    @Description("团队") 
    @Column(name = "teamNo",length=40) 
    private String teamNo;
      
    @Description("项目") 
    @Column(name = "projectNo",length=40) 
    private String projectNo;
      
    @Description("转移原因") 
    @Column(name = "transferReason",length=400) 
    private String transferReason;
      
    @Description("转移人") 
    @Column(name = "transfer",length=40) 
    private String transfer;
      
    @Description("转移时间") 
    @Column(name = "transferDate",length=10) 
    private LocalDate transferDate;
      
    @Description("登记人")
    @CreatedBy
    @Column(name = "inputUserId", length = 32)
    private String inputUserId;

    @Description("登记机构")
    @Column(name = "inputOrgId", length = 32)
    private String inputOrgId;

    @Description("登记日期")
    @CreatedDate
    @Column(name = "inputDate", length = 20)
    private LocalDateTime inputDate;

    @Description("更新人")
    @LastModifiedBy
    @Column(name = "updateUserId", length = 32)
    private String updateUserId;

    @Description("更新机构")
    @Column(name = "updateOrgId", length = 32)
    private String updateOrgId;

    @Description("更新时间")
    @LastModifiedDate
    @Column(name = "updateDate", length = 20)
    private LocalDateTime updateDate;
    
    public String getInputDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputDate.format(dtf);
    }

    public String getUpdateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateDate.format(dtf);
    }
    
    public String getTransferDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
           return transferDate.format(dtf);
    }
}
