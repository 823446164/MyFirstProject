
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
@Description("在职培训参与人员表")
@Entity
@Table(
    name="EMPLOYEE_INJOB_PARTOR")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeJobPartor extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("流水号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("培训编号") 
    @Column(name = "trainNo", nullable=false,length=40) 
    private String trainNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo", nullable=false,length=40) 
    private String employeeNo;
      
    @Description("附件编号") 
    @Column(name = "attachmentNo",length=40) 
    private String attachmentNo;
      
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
