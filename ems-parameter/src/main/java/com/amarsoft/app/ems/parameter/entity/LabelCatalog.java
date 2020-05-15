
package com.amarsoft.app.ems.parameter.entity;

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
@Description("标签目录表")
@Entity
@Table(
    name="LABEL_CATALOG")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class LabelCatalog extends BusinessObject {
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
    @Column(name = "belongCataLog",length=80) 
    private String belongCataLog;
      
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
    @Column(name = "labelDescribe",length=2000) 
    private String labelDescribe;
      
    @Description("目录备注") 
    @Column(name = "catalogRemark",length=2000) 
    private String catalogRemark;
        
    @Description("版本") 
    @Column(name = "labelVersion",length=10) 
    private String labelVersion;
      
    @Description("登记人") 
    @CreatedBy
    @Column(name = "inputUserId", nullable=false,length=40) 
    private String inputUserId;
      
    @CreatedDate
    @Description("登记时间") 
    @Column(name = "inputTime",length=20) 
    private LocalDateTime inputTime;
      
    @Description("登记机构  ") 
    @Column(name = "inputOrgId",length=40) 
    private String inputOrgId;
      
    @Description("更新人") 
    @LastModifiedBy
    @Column(name = "updateUserId",length=40) 
    private String updateUserId;
      
    @Description("更新时间") 
    @LastModifiedDate
    @Column(name = "updateTime",length=20) 
    private LocalDateTime updateTime;
      
    @Description("更新机构  ") 
    @Column(name = "updateOrgId",length=40) 
    private String updateOrgId;
    
    @Description("类型  ") 
    @Column(name = "labelType",length=10) 
    private String labelType;
    
    
    public String getInputTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputTime.format(sdf);
    }
    
    public String getUpdateTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateTime.format(sdf);
    }
}
