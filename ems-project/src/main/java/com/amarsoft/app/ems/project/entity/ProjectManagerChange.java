
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Description("项目组成员变更表")
@Entity
@Table(
    name="PROJECT_MEMBER_CHANGE")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class ProjectManagerChange extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("变更编号") 
    @Id 
    @Column(name = "changeNo", nullable=false,length=40) 
    private String changeNo;
      
    @Description("申请编号") 
    @Column(name = "serialNo",length=40) 
    private String serialNo;
      
    @Description("变更类型") 
    @Column(name = "changeType",length=40) 
    private String changeType;
      
    @Description("申请原因") 
    @Column(name = "applyReason",length=40) 
    private String applyReason;
      
    @Description("申请状态") 
    @Column(name = "applyStatus",length=40) 
    private String applyStatus;
      
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
    public String getInputTime() {
  		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
  		return inputTime.format(dtf);
  	}

  	public String getUpdateTime() {
  		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
  		return updateTime.format(dtf);
  	}
}
