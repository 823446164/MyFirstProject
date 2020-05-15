
package com.amarsoft.app.ems.train.entity;


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
@Description("附件关联表")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "DOC_RELATIVE")
@GeneratedKey(autoGenerateKey = true, allocationSize = 1000)
public class DocRelative extends BusinessObject {
    private static final long serialVersionUID = 1L;

    @Description("流水号")
    @Id
    @Column(name = "serialNo", nullable = false, length = 40)
    private String serialNo;

    @Description("附件编号")
    @Column(name = "docNo", length = 40)
    private String docNo;

    @Description("对象类型")
    @Column(name = "objectType", length = 20)
    private String objectType;

    @Description("对象编号")
    @Column(name = "objectNo", length = 40)
    private String objectNo;

    @Description("属性字段1")
    @Column(name = "attribute1", length = 80)
    private String attribute1;

    @Description("属性字段2")
    @Column(name = "attribute2", length = 80)
    private String attribute2;

    @Description("属性字段3")
    @Column(name = "attribute3", length = 80)
    private String attribute3;

    @Description("属性字段4")
    @Column(name = "attribute4", length = 80)
    private String attribute4;

    @Description("属性字段5")
    @Column(name = "attribute5", length = 80)
    private String attribute5;

    @Description("登记人") 
    @CreatedBy
    @Column(name = "inputUserId",length=32) 
    private String inputUserId;
      
    @Description("登记机构") 
    @Column(name = "inputOrgId",length=32) 
    private String inputOrgId;
      
    @Description("登记日期") 
    @CreatedDate
    @Column(name = "inputDate",length=20) 
    private LocalDateTime inputDate;
      
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
    
    public String getInputDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputDate.format(dtf);
    }

    public String getUpdateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateDate.format(dtf);
    }
}
