
package com.amarsoft.app.ems.system.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Description("流程意见表")
@Entity
@Table(
    name="FLOW_OPINION")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class FlowOpinion extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("流程任务流水号") 
    @Id 
    @Column(name = "taskId", nullable=false,length=40) 
    private String taskId;
      
    @Description("意见序号") 
    @Column(name = "opinionNo",length=40) 
    private String opinionNo;
      
    @Description("对象类型") 
    @Column(name = "objectType",length=40) 
    private String objectType;
      
    @Description("对象编号") 
    @Column(name = "taskDefKey",length=40) 
    private String taskDefKey;
      
    @Description("员工编号") 
    @Column(name = "employeeNo",length=40) 
    private String employeeNo;
      
    @Description("员工姓名") 
    @Column(name = "employeeName",length=80) 
    private String employeeName;
      
    @Description("阶段意见") 
    @Column(name = "phaseChoice",length=800) 
    private String phaseChoice;
      
    @Description("意见类型") 
    @Column(name = "opinionType",length=10) 
    private String opinionType;
      
    @Description("意见详情") 
    @Column(name = "phaseOpinion",length=400) 
    private String phaseOpinion;
      
    @Description("意见详情1") 
    @Column(name = "phaseOpinion1",length=400) 
    private String phaseOpinion1;
      
    @Description("意见详情2") 
    @Column(name = "phaseOpinion2",length=400) 
    private String phaseOpinion2;
      
    @Description("意见详情3") 
    @Column(name = "phaseOpinion3",length=400) 
    private String phaseOpinion3;
      
    @Description("人工评分") 
    @Column(name = "manualScore",length=24) 
    private String manualScore;
      
    @Description("人工评分结果") 
    @Column(name = "manualResult",length=6) 
    private String manualResult;
      
    @Description("人工评分日期") 
    @Column(name = "manualEvaldate",length=10) 
    private LocalDate manualEvaldate;
      
    @Description("人工评分理由") 
    @Column(name = "manualReason",length=250) 
    private String manualReason;
      
    @Description("登记人") 
    @Column(name = "inputUserId",length=32) 
    private String inputUserId;
      
    @Description("登记机构") 
    @Column(name = "inputOrgId",length=32) 
    private String inputOrgId;
      
    @Description("登记日期") 
    @Column(name = "inputDate",length=20) 
    private LocalDateTime inputDate;
      
    @Description("更新人") 
    @Column(name = "updateUserId",length=32) 
    private String updateUserId;
      
    @Description("更新机构") 
    @Column(name = "updateOrgId",length=32) 
    private String updateOrgId;
      
    @Description("更新时间") 
    @Column(name = "updateDate",length=20) 
    private LocalDateTime updateDate;
      
    @Description("录入时间") 
    @Column(name = "inputTime",length=20) 
    private String inputTime;
      
    @Description("更新时间") 
    @Column(name = "updateTime",length=20) 
    private String updateTime;
    public String getManualEvaldate() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return manualEvaldate.format(sdf);
    }
    
    public String getInputDate() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputDate.format(sdf);
    }
    
    public String getUpdateDate() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateDate.format(sdf);
    }
}
