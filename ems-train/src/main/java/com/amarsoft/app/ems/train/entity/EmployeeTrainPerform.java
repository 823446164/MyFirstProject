
package com.amarsoft.app.ems.train.entity;

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
@Description("培训员工表现表")
@Entity
@Table(
    name="EMPLOYEE_TRAIN_PERFORM")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeTrainPerform extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("流水号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("模块编号") 
    @Column(name = "itemNo",length=40) 
    private String itemNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo", nullable=false,length=40) 
    private String employeeNo;
      
    @Description("项目编号") 
    @Column(name = "projectNo",length=40) 
    private String projectNo;
      
    @Description("是否完成") 
    @Column(name = "isComplete",length=10) 
    private String isComplete;
      
    @Description("完成度") 
    @Column(name = "completeness",length=20) 
    private String completeness;
      
    @Description("掌握情况") 
    @Column(name = "masterSitu",length=80) 
    private String masterSitu;
      
    @Description("能力描述") 
    @Column(name = "capabilityDes",length=400) 
    private String capabilityDes;
      
    @Description("评分") 
    @Column(name = "modelScore",length=20) 
    private String modelScore;
      
    @Description("登记人") 
    @Column(name = "inputUserId",length=32) 
    private String inputUserId;
      
    @Description("登记机构") 
    @Column(name = "inputOrgId",length=32) 
    private String inputOrgId;
      
    @Description("登记日期") 
    @Column(name = "inputDate",length=20) 
    private LocalDateTime inputDate;
      
    @Description("更新人") 
    @Column(name = "updateUserId",length=32) 
    private String updateUserId;
      
    @Description("更新机构") 
    @Column(name = "updateOrgId",length=32) 
    private String updateOrgId;
      
    @Description("更新时间") 
    @Column(name = "updateDate",length=20) 
    private LocalDateTime updateDate;
    
    public String getInputDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputDate.format(dtf);
    }

    public String getUpdateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateDate.format(dtf);
    }
}
