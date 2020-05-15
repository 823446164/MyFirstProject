
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
@Description("追踪内容表")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(
    name="EMPLOYEE_TRACE_DETAIL")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeTraceDetail extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("流水号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("追踪编号") 
    @Column(name = "traceNo",length=40) 
    private String traceNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo", nullable=false,length=40) 
    private String employeeNo;
      
    @Description("内容序号") 
    @Column(name = "dataNo", nullable=false,length=40) 
    private String dataNo;
      
    @Description("填写时间") 
    @Column(name = "traceDate",length=20) 
    private LocalDate traceDate;
      
    @Description("关注级别") 
    @Column(name = "attention",length=20) 
    private String attention;
      
    @Description("是否继续追踪") 
    @Column(name = "isContinue",length=20) 
    private String isContinue;
      
    @Description("追踪人") 
    @Column(name = "tracker",length=80) 
    private String tracker;
      
    @Description("备注") 
    @Column(name = "remark",length=400) 
    private String remark;
      
    @Description("代码质量") 
    @Column(name = "codeQuality",length=80) 
    private String codeQuality;
      
    @Description("任务进度") 
    @Column(name = "taskProgress",length=80) 
    private String taskProgress;
      
    @Description("主动性") 
    @Column(name = "initiative",length=80) 
    private String initiative;
      
    @Description("责任心") 
    @Column(name = "responsibility",length=80) 
    private String responsibility;
      
    @Description("出勤情况") 
    @Column(name = "attendance",length=80) 
    private String attendance;
      
    @Description("所学业务知识") 
    @Column(name = "businessKnow",length=80) 
    private String businessKnow;
      
    @Description("心理素质") 
    @Column(name = "psychology",length=80) 
    private String psychology;
      
    @Description("工作协调互助") 
    @Column(name = "workHelp",length=80) 
    private String workHelp;
      
    @Description("沟通情况") 
    @Column(name = "communicate",length=80) 
    private String communicate;
      
    @Description("知识(资源)共享") 
    @Column(name = "knowShare",length=80) 
    private String knowShare;
      
    @Description("参与团建情况") 
    @Column(name = "participation",length=80) 
    private String participation;
      
    @Description("反馈人员") 
    @Column(name = "feedbacker",length=40) 
    private String feedbacker;
      
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
    
    public String getTraceDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return traceDate.format(dtf);
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
