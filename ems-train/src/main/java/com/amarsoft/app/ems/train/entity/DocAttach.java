
package com.amarsoft.app.ems.train.entity;


import java.time.LocalDate;
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
@Description("员工管理附件表")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "DOC_ATTACHMENT")
@GeneratedKey(autoGenerateKey = true, allocationSize = 1000)
public class DocAttach extends BusinessObject {
    private static final long serialVersionUID = 1L;

    @Description("附件编号")
    @Id
    @Column(name = "serialNo", nullable = false, length = 40)
    private String serialNo;

    @Description("文件类型")
    @Column(name = "fileType", nullable = false, length = 40)
    private String fileType;

    @Description("文件名称")
    @Column(name = "fileName", length = 80)
    private String fileName;

    @Description("文件大小")
    @Column(name = "fileSize", length = 40)
    private String fileSize;

    @Description("文件路径")
    @Column(name = "filePath", length = 80)
    private String filePath;

    @Description("上传人")
    @Column(name = "uploader", length = 40)
    private String uploader;

    @Description("上传时间")
    @Column(name = "uploadDate", length = 10)
    private LocalDate uploadDate;

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
    
    public String getUploadDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return uploadDate.format(dtf);
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
