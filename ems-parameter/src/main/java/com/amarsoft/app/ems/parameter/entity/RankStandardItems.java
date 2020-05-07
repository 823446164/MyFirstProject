
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
      
    @Description("标签名称") 
    @Column(name = "labelName",length=40) 
    private String labelName;
      
    @Description("标签等级") 
    @Column(name = "labelLevel",length=40) 
    private String labelLevel;
      
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
