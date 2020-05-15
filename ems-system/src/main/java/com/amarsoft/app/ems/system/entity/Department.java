
package com.amarsoft.app.ems.system.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Description("部门基本信息附属表")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(
    name="SYS_DEPT_INFO")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class Department extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("部门编号") 
    @Id 
    @Column(name = "deptId", nullable=false,length=40) 
    private String deptId;
      
    @Description("部门名称") 
    @Column(name = "deptName",length=80) 
    private String deptName;
      
    @Description("部门地址") 
    @Column(name = "deptAddress",length=80) 
    private String deptAddress;
      
    @Description("部门经理") 
    @Column(name = "deptManager",length=40) 
    private String deptManager;
      
    @Description("部门设施说明") 
    @Column(name = "deptEquipment",length=2000) 
    private String deptEquipment;
      
    @Description("登记人") 
    @CreatedBy
    @Column(name = "inputUserId",length=40) 
    private String inputUserId;
      
    @Description("登记时间") 
    @CreatedDate 
    @Column(name = "inputDate",length=20) 
    private LocalDateTime inputDate;
      
    @Description("登记机构") 
    @Column(name = "inputOrgId",length=40) 
    private String inputOrgId;
      
    @Description("更新人") 
    @LastModifiedBy
    @Column(name = "updateUserId",length=32) 
    private String updateUserId;
      
    @Description("更新机构") 
    @Column(name = "updateOrgId",length=32) 
    private String updateOrgId;
      
    @Description("更新时间") 
    @LastModifiedDate
    @Column(name = "updateDate",length=20) 
    private LocalDateTime updateDate;
      
    @Description("备注") 
    @Column(name = "remark",length=2000) 
    private String remark;
    
    public String getInputDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputDate.format(dtf);
    }

    public String getUpdateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateDate.format(dtf);
    }
}