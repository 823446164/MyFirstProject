
package com.amarsoft.app.ems.parameter.entity;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.EntityRelationShip;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;

import javax.persistence.*;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Description("标签目录表")
@Entity
@Table(
    name="LABEL_CATALOG")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class LableCatalog extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("标签编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("标签名称") 
    @Column(name = "labelName",length=80) 
    private String labelName;
      
    @Description("标签码值") 
    @Column(name = "codeNo",length=80) 
    private String codeNo;
      
    @Description("标签状态") 
    @Column(name = "labelStatus",length=10) 
    private String labelStatus;
      
    @Description("所属目录") 
    @Column(name = "cataLog",length=80) 
    private String cataLog;
      
    @Description("所属大类") 
    @Column(name = "rootNo",length=40) 
    private String rootNo;
      
    @Description("所属父类") 
    @Column(name = "parentNo",length=40) 
    private String parentNo;
      
    @Description("适用要求类别") 
    @Column(name = "abilityType",length=10) 
    private String abilityType;
      
    @Description("标签说明") 
    @Column(name = "labeldescribe", nullable=false,length=2000) 
    private String labeldescribe;
      
    @Description("目录备注") 
    @Column(name = "catalogRemark",length=2000) 
    private String catalogRemark;
      
    @Description("录入人") 
    @Column(name = "author",length=40) 
    private String author;
      
    @Description("版本") 
    @Column(name = "version",length=10) 
    private String version;
      
    @Description("登记人") 
    @Column(name = "inputUserId", nullable=false,length=40) 
    private String inputUserId;
      
    @Description("登记时间") 
    @Column(name = "inputTime",length=20) 
    private LocalDateTime inputTime;
      
    @Description("登记机构  ") 
    @Column(name = "inputOrgId",length=40) 
    private String inputOrgId;
      
    @Description("更新人") 
    @Column(name = "updateUserId",length=40) 
    private String updateUserId;
      
    @Description("更新时间") 
    @Column(name = "updateTime",length=20) 
    private LocalDateTime updateTime;
      
    @Description("更新机构  ") 
    @Column(name = "updateOrgId",length=40) 
    private String updateOrgId;
}
