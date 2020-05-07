
package com.amarsoft.app.ems.project.entity;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.EntityRelationShip;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;

import javax.persistence.*;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Description("项目基本信息表")
@Entity
@Table(
    name="PROJECT_INFO")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class Project extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("项目编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("项目名称") 
    @Column(name = "projectName",length=100) 
    private String projectName;
      
    @Description("项目总监") 
    @Column(name = "projectDirector",length=40) 
    private String projectDirector;
      
    @Description("项目经理") 
    @Column(name = "projectManager",length=40) 
    private String projectManager;
      
    @Description("客户经理") 
    @Column(name = "customerManager",length=40) 
    private String customerManager;
      
    @Description("立项日期") 
    @Column(name = "createTime",length=20) 
    private LocalDate createTime;
      
    @Description("验收通过日期") 
    @Column(name = "acceptPassTime",length=20) 
    private LocalDate acceptPassTime;
      
    @Description("客户") 
    @Column(name = "customer",length=80) 
    private String customer;
      
    @Description("咨询顾问") 
    @Column(name = "consultant",length=40) 
    private String consultant;
      
    @Description("QA经理") 
    @Column(name = "qaManager",length=40) 
    private String qaManager;
      
    @Description("项目分类") 
    @Column(name = "projetClassification",length=40) 
    private String projetClassification;
      
    @Description("发生类型") 
    @Column(name = "projectType",length=10) 
    private String projectType;
      
    @Description("签订合同") 
    @Column(name = "signContract",length=40) 
    private String signContract;
      
    @Description("当前状态") 
    @Column(name = "status",length=10) 
    private String status;
      
    @Description("当前阶段") 
    @Column(name = "nowPhase",length=10) 
    private String nowPhase;
      
    @Description("所在城市") 
    @Column(name = "stayCity",length=40) 
    private String stayCity;
      
    @Description("项目组出发日期") 
    @Column(name = "startTime",length=40) 
    private LocalDate startTime;
      
    @Description("实际结束日期") 
    @Column(name = "realendTime",length=40) 
    private LocalDate realendTime;
      
    @Description("保存状态") 
    @Column(name = "saveStatus",length=10) 
    private String saveStatus;
   
    @Description("关联代码") 
    @Column(name = "codeNo",length=40) 
    private String codeNo;
    @Description("项目类型") 
    @Column(name = "mainType",length=10) 
    private String mainType;  
    @Description("登记人") 
    @Column(name = "inputUserId",length=40) 
    private String inputUserId;
      
    @Description("登记时间") 
    @Column(name = "inputTime",length=20) 
    private LocalDateTime inputTime;
      
    @Description("登记机构") 
    @Column(name = "inputOrgId",length=40) 
    private String inputOrgId;
      
    @Description("更新人") 
    @Column(name = "updateUserId",length=40) 
    private String updateUserId;
      
    @Description("更新时间") 
    @Column(name = "updateTime",length=20) 
    private LocalDateTime updateTime;
      
    @Description("更新机构") 
    @Column(name = "updateOrgId",length=40) 
    private String updateOrgId;
    public String getCreateTime() {
  		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
  		return inputTime.format(dtf);
  	}
    public String getRealendTime() {
  		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
  		return inputTime.format(dtf);
  	}
    public String getAcceptPassTime() {
  		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
  		return inputTime.format(dtf);
  	}
    public String getStartTime() {
  		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
  		return inputTime.format(dtf);
  	}

    public String getInputTime() {
  		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
  		return inputTime.format(dtf);
  	}

  	public String getUpdateTime() {
  		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
  		return updateTime.format(dtf);
  	}
}
