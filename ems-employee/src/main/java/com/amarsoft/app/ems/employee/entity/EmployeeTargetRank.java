
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
@Description("目标职级申请表")
@Entity
@Table(
    name="EMPLOYEE_TARGET_RANK")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeTargetRank extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("职级变更编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("申请编号") 
    @Column(name = "rankSerialNo", nullable=false,length=40) 
    private String rankSerialNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo",length=40) 
    private String employeeNo;
      
    @Description("员工姓名") 
    @Column(name = "employeeName",length=80) 
    private String employeeName;
      
    @Description("当前职级") 
    @Column(name = "rankName",length=80) 
    private String rankName;
      
    @Description("考核职级") 
    @Column(name = "evaluationRank",length=80) 
    private String evaluationRank;
      
    @Description("目标职级") 
    @Column(name = "targetRank",length=80) 
    private String targetRank;
      
    @Description("选择目标职级记录") 
    @Column(name = "targetRecord",length=80) 
    private String targetRecord;
      
    @Description("职级考核日期") 
    @Column(name = "evaluationRankTime",length=10) 
    private LocalDate evaluationRankTime;
     
    @Description("审批状态") 
    @Column(name = "approveStatus",length=20) 
    private String approveStatus;
    
    @Description("标签") 
    @Column(name = "label",length=40) 
    private String label;
      
    @Description("级别") 
    @Column(name = "grade",length=10) 
    private String grade;
      
    @Description("掌握程度") 
    @Column(name = "masteryDegree",length=10) 
    private String masteryDegree;
      
    @Description("变更理由") 
    @Column(name = "changeReason",length=2000) 
    private String changeReason;
      
    @Description("意见") 
    @Column(name = "opinion",length=400) 
    private String opinion;
      
    @Description("团队负责人") 
    @Column(name = "teamManager",length=40) 
    private String teamManager;
      
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
    
    public String getEvaluationRankTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateFormat.format);
        return evaluationRankTime.format(sdf);
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
