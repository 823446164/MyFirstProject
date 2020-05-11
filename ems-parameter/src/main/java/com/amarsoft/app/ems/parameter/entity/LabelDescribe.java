
package com.amarsoft.app.ems.parameter.entity;

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
@Description("标签能力描述表")
@Entity
@Table(
    name="LABEL_DESCRIBE")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class LabelDescribe extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("流水号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("标签编号") 
    @Column(name = "labelNo",length=40) 
    private String labelNo;
      
    @Description("标签等级") 
    @Column(name = "labelLevel",length=40) 
    private String labelLevel;
      
    @Description("等级描述") 
    @Column(name = "levelDescribe",length=2000) 
    private String levelDescribe;
      
    @Description("登记人") 
    @Column(name = "inputUserId", nullable=false,length=40) 
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
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputTime.format(sdf);
    }    
    public String getUpdateTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateTime.format(sdf);
    }
}  
