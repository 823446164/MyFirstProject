
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
@Description("追踪记录表")
@Entity
@Table(
    name="EMPLOYEE_TRACE_RECORD")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class EmployeeTraceRecord extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("追踪编号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("员工编号") 
    @Column(name = "employeeNo", nullable=false,length=40) 
    private String employeeNo;
      
    @Description("员工姓名") 
    @Column(name = "employeeName",length=80) 
    private String employeeName;
      
    @Description("严重级别") 
    @Column(name = "seriousLever",length=2) 
    private String seriousLever;
      
    @Description("追踪状态") 
    @Column(name = "traceStatus",length=2) 
    private String traceStatus;
      
    @Description("发起人") 
    @Column(name = "initiator",length=40) 
    private String initiator;
      
    @Description("团队负责人") 
    @Column(name = "teamLeader",length=40) 
    private String teamLeader;
      
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
