
package com.amarsoft.app.ems.demo.entity;

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
@Description("员工个人表现表")
@Entity
@Table(
    name="EMPLOYEE_PERSONAL_PERFORMANCE_JFAN5")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeePersonalPerformance extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("个人表现编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo", nullable=false,length=40) 
    private String employeeNo;
      
    @Description("项目编号") 
    @Column(name = "projectNo", nullable=false,length=40) 
    private String projectNo;
      
    @Description("参考因素") 
    @Column(name = "factors", nullable=false,length=100) 
    private String factors;
      
    @Description("能力描述") 
    @Column(name = "abilityDescribe", nullable=false,length=2000) 
    private String abilityDescribe;
      
    @Description("评分") 
    @Column(name = "score", nullable=false,length=40) 
    private String score;
      
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
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputTime.format(sdf);
    }

    public String getUpdateTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateTime.format(sdf);
    }
}
