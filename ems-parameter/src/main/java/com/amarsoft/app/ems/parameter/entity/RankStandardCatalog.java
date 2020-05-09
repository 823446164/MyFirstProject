
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
@Description("职级标准表")
@Entity
@Table(
    name="RANK_STANDARD_CATALOG")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class RankStandardCatalog extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("职级编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("职等") 
    @Column(name = "rankStandard",length=40) 
    private String rankStandard;
      
    @Description("职级") 
    @Column(name = "rankName",length=40) 
    private String rankName;
      
    @Description("父职级") 
    @Column(name = "parentRankNo",length=40) 
    private String parentRankNo;
      
    @Description("能力要求") 
    @Column(name = "ability",length=2000) 
    private String ability;
      
    @Description("技术职级描述") 
    @Column(name = "rankDescribe",length=2000) 
    private String rankDescribe;
      
    @Description("岗位职责描述") 
    @Column(name = "responeDescribe",length=2000) 
    private String responeDescribe;
      
    @Description("能力要求描述") 
    @Column(name = "abilityDescribe",length=2000) 
    private String abilityDescribe;
      
    @Description("所属团队") 
    @Column(name = "belongTeam",length=40) 
    private String belongTeam;
      
    @Description("指标类型") 
    @Column(name = "rankType",length=10) 
    private String rankType;
      
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
    
    @Description("子职级  ") 
    @Column(name = "childRankNo",length=10) 
    private String childRankNo;
    
    public String getInputTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputTime.format(sdf);
    }
    
    public String getUpdateTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateTime.format(sdf);
    }
}
