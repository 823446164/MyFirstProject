
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
@Description("代码目录表")
@Entity
@Table(
    name="CODE_CATALOG")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class CodeCatalog extends BusinessObject {
    private static final long serialVersionUID = 1L;
      
    @Description("代码号") 
    @Id 
    @Column(name = "codeNo", nullable=false,length=40) 
    private String codeNo;
      
    @Description("排序号") 
    @Column(name = "sortNo",length=40) 
    private String sortNo;
      
    @Description("代码名") 
    @Column(name = "codeName",length=80) 
    private String codeName;
      
    @Description("代码类型一") 
    @Column(name = "codeTypeOne",length=80) 
    private String codeTypeOne;
      
    @Description("代码类型二") 
    @Column(name = "codeTypeTwo",length=80) 
    private String codeTypeTwo;
      
    @Description("代码描述") 
    @Column(name = "codeDescribe",length=250) 
    private String codeDescribe;
      
    @Description("代码属性") 
    @Column(name = "codeAttribute",length=250) 
    private String codeAttribute;
      
    @Description("备注") 
    @Column(name = "remark",length=250) 
    private String remark;
      
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
