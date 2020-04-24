package com.amarsoft.app.ems.system.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.ExportStatus;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 用户导出文件和下载文件记录表
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserExportBase extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("导出编号")
    @Id
    @Column(name = "SERIALNO",unique=true, nullable=false, length=40)
    private String serialNo;
    
    @Description("用户编号")
    @Column(name = "USERID", length=40)
    private String userId;
    
    @Description("导出文件名")
    @Column(name = "FILENAME",length=100)
    private String fileName;
    
    @Description("导出开始时间")
    @Column(name = "BEGINTIME")
    private LocalDateTime beginTime;
    
    @Description("导出结束时间")
    @Column(name = "FINISHTIME")
    private LocalDateTime finishTime;

    @Description("导出状态")
    @Enum(ExportStatus.class)
    @Column(name = "STATUS",length=1)
    private String status;
    
    @Description("日志记录")
    @Column(name = "LOG",length=400)
    private String log;
    
    @Description("下载路径")
    @Column(name = "URI",length=100)
    private String uri;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}

