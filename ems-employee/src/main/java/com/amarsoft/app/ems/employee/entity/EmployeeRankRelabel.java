
package com.amarsoft.app.ems.employee.entity;

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
@Description("员工职级标签关联表")
@Entity
@Table(
    name="EMPLOYEE_RANK_RELABEL")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeRankRelabel extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("流水编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("职级编号") 
    @Column(name = "rankNo", nullable=false,length=40) 
    private String rankNo;
      
    @Description("标签") 
    @Column(name = "labelNo", nullable=false,length=40) 
    private String labelNo;
      
    @Description("掌握程度") 
    @Column(name = "level", nullable=false,length=40) 
    private String level;
      
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
