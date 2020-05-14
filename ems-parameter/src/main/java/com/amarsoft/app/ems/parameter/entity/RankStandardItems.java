
package com.amarsoft.app.ems.parameter.entity;

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
@Description("职级指标表")
@Entity
@Table(
    name="RANK_STANDARD_ITEMS")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class RankStandardItems extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("指标编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("职级编号") 
    @Column(name = "rankNo",length=40) 
    private String rankNo;
    
    @Description("标签编号") 
    @Column(name = "labelNo",length=40) 
    private String labelNo;
      
    @Description("标签名称") 
    @Column(name = "labelName",length=40) 
    private String labelName;
      
    @Description("标签等级") 
    @Column(name = "labelLevel",length=40) 
    private String labelLevel;
    
    @Description("指标标准分类") 
    @Column(name = "labelStandardType",length=10) 
    private String labelStandardType;
    
    @Description("所属目录") 
    @Column(name = "belongCatalog",length=80) 
    private String belongCatalog;
    
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
    
    public String getInputTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputTime.format(sdf);
    }
    
    public String getUpdateTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateTime.format(sdf);
    }
}
