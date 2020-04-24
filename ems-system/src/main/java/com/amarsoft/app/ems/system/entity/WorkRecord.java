package com.amarsoft.app.ems.system.entity;
import java.time.LocalDateTime;

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

import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 工作日历
 */
@Entity
@Description("工作日历信息表")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_WORK_RECORD")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class WorkRecord extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("工作笔记主键")
    @Id
    @Column(name = "WORKID",unique=true, nullable=false, length=40)
    private String workId;
    
    @Description("登录人id")
    @Column(name = "USERID",length=40)
    private String userId;
    
    @Description("计划工作内容")
    @NotEmpty
    @Column(name = "WORKCONTENT",length=400)
    private String workContent;
    
    @Description("实际执行情况")
    @Column(name = "EXECUTESITUATION",length=400)
    private String executeSituation;
    
    @Description("未完成理由")
    @Column(name = "REASON",length=400)
    private String reason;
    
    @Description("备注")
    @Column(name = "REMARK",length=400)
    private String remark;
    
    @Description("工作笔记日期")
    @NotEmpty
    @Column(name = "WORKNODEDATE",length=10)
    private String workNodeDate;
    
    @Description("登记人")
    @Column(name = "INPUTUSERID",length=40)
    @CreatedBy
    private String inputUserId;
    
    @Description("登记时间 yyyy/MM/dd HH:mm:ss.SSS")
    @Column(name = "INPUTTIME")
    @CreatedDate
    private LocalDateTime inputTime;
    
    @Description("更新人")
    @Column(name = "UPDATEUSERID",length=40)
    @LastModifiedBy
    private String updateUserId;
    
    @Description("更新时间 yyyy/MM/dd HH:mm:ss.SSS")
    @Column(name = "UPDATETIME")
    @LastModifiedDate
    private LocalDateTime updateTime;

    public String getWorkId() {
        return workId;
    }

    public String getUserId() {
        return userId;
    }

    public String getWorkContent() {
        return workContent;
    }

    public String getExecuteSituation() {
        return executeSituation;
    }

    public String getReason() {
        return reason;
    }

    public String getRemark() {
        return remark;
    }

    public String getInputUserId() {
        return inputUserId;
    }

    public LocalDateTime getInputTime() {
        return inputTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public void setExecuteSituation(String executeSituation) {
        this.executeSituation = executeSituation;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setInputUserId(String inputUserId) {
        this.inputUserId = inputUserId;
    }

    public void setInputTime(LocalDateTime inputTime) {
        this.inputTime = inputTime;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getWorkNodeDate() {
        return workNodeDate;
    }

    public void setWorkNodeDate(String workNodeDate) {
        this.workNodeDate = workNodeDate;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    
}

