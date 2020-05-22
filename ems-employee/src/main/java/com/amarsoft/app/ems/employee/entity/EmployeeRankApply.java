
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
import org.springframework.util.StringUtils;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Description("员工职级申请表")
@Entity
@Table(
    name="EMPLOYEE_RANK_APPLY")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeRankApply extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("申请编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("职级编号") 
    @Column(name = "rankNo", nullable=false,length=40) 
    private String rankNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo", nullable=false,length=40) 
    private String employeeNo;
      
    @Description("员工姓名") 
    @Column(name = "employeeName",length=80) 
    private String employeeName;
      
    @Description("员工现职级") 
    @Column(name = "rankName",length=40) 
    private String rankName;
      
    @Description("员工变更职级") 
    @Column(name = "updateRankName",length=40) 
    private String updateRankName;
      
    @Description("团队负责人") 
    @Column(name = "teamManager",length=40) 
    private String teamManager;
      
    @Description("变更人") 
    @Column(name = "changePerson",length=40) 
    private String changePerson;
      
    @Description("发起时间") 
    @Column(name = "beginTime",length=20) 
    private LocalDateTime beginTime;
    
    @Description("计划考核时间") 
    @Column(name = "rankTime",length=20) 
    private LocalDateTime rankTime;
      
    @Description("审批状态") 
    @Column(name = "approveStatus",length=40) 
    private String approveStatus;
    
    @Description("变更理由") 
    @Column(name = "changeReason",length=2000) 
    private String changeReason;
      
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
    
    public String getRankTime() {
        if(!StringUtils.isEmpty(rankTime)) {
             DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return rankTime.format(sdf);
        }
        return null;
    }
}
