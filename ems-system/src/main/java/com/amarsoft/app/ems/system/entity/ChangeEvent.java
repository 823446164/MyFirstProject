
package com.amarsoft.app.ems.system.entity;

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
@Description("变更记录表")
@Entity
@Table(
    name="CHANGE_EVENT")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class ChangeEvent extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("记录流水号") 
    @Id 
    @Column(name = "serialNo", nullable=false,length=40) 
    private String serialNo;
      
    @Description("对象编号") 
    @Column(name = "objectNo", nullable=false,length=40) 
    private String objectNo;
      
    @Description("对象类型") 
    @Column(name = "objectType", nullable=false,length=40) 
    private String objectType;
      
    @Description("变更日期") 
    @Column(name = "occurDate",length=20) 
    private LocalDateTime occurDate;
      
    @Description("变更前属性") 
    @Column(name = "oldEventValue",length=80) 
    private String oldEventValue;
      
    @Description("变更后属性") 
    @Column(name = "eventValue",length=80) 
    private String eventValue;
      
    @Description("变更内容") 
    @Column(name = "changeContext",length=800) 
    private String changeContext;
      
    @Description("登记人") 
    @Column(name = "inputUserId",length=40) 
    private String inputUserId;
      
    @Description("登记时间") 
    @Column(name = "inputDate",length=20) 
    private LocalDateTime inputDate;
      
    @Description("登记机构") 
    @Column(name = "inputOrgId",length=40) 
    private String inputOrgId;
      
    @Description("更新人") 
    @Column(name = "updateUserId",length=32) 
    private String updateUserId;
      
    @Description("更新机构") 
    @Column(name = "updateOrgId",length=32) 
    private String updateOrgId;
      
    @Description("更新时间") 
    @Column(name = "updateDate",length=20) 
    private LocalDateTime updateDate;
      
    @Description("备注") 
    @Column(name = "remark",length=2000) 
    private String remark;
    
    public String getOccurDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return occurDate.format(dtf);
    }
    
    public String getInputDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputDate.format(dtf);
    }

    public String getUpdateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateDate.format(dtf);
    }
}
