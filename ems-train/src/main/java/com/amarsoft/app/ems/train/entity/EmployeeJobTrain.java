
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
@Description("在职培训目录表")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(
    name="EMPLOYEE_INJOB_TRAIN")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeJobTrain extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("培训编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("主题") 
    @Column(name = "theme", nullable=false,length=200) 
    private String theme;
      
    @Description("讲师") 
    @Column(name = "lecturer", nullable=false,length=40) 
    private String lecturer;
      
    @Description("日期") 
    @Column(name = "trainDate",length=10) 
    private LocalDate trainDate;
      
    @Description("开始时间") 
    @Column(name = "startDate",length=10) 
    private LocalDate startDate;
      
    @Description("结束时间") 
    @Column(name = "endDate",length=10) 
    private LocalDate endDate;
      
    @Description("状态") 
    @Column(name = "trainStatus",length=120) 
    private String trainStatus;
      
    @Description("录入人") 
    @Column(name = "recorder",length=40) 
    private String recorder;
      
    @Description("录入时间") 
    @Column(name = "recordDate",length=10) 
    private LocalDate recordDate;
      
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
            
    
    public String getStartDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return startDate.format(dtf);
           }
    public String getEndDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return endDate.format(dtf);
           }
    public String getTrainDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return trainDate.format(dtf);
           }
    public String getRecordDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return recordDate.format(dtf);
           }
    public String getInputDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputDate.format(dtf);
    }

    public String getUpdateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateDate.format(dtf);
    }
}
