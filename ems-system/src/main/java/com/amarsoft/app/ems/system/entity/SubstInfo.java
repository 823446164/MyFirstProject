package com.amarsoft.app.ems.system.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.UserLeaveStatus;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 用户权限代理信息
 */
@Entity
@Description("用户权限代理信息")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_SUBST_INFO",
        indexes = {
            @Index(name="IDX_SUBST_INFO_01",columnList="userId,status"),
            @Index(name="IDX_SUBST_INFO_02",columnList="substUserId,status")
        })
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class SubstInfo extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("代理记录编号")
    @Id
    @Column(name = "SUBSTID",unique=true, nullable=false, length=40)
    private String substId;
    
    @Description("被代理人用户编号（提供权限的用户）")
    @Column(name = "USERID",length=40)
    private String userId;
    
    @Description("代理人用户编号（使用权限的用户）")
    @Column(name = "SUBSTUSERID",length=40)
    private String substUserId;
    
    @Description("开始时间 yyyy/MM/dd HH:mm:ss")
    @Column(name = "BEGINTIME")
    private LocalDateTime beginTime;

    @Description("结束时间 yyyy/MM/dd HH:mm:ss")
    @Column(name = "ENDTIME")
    private LocalDateTime endTime;
    
    @Description("授权原因")
    @Column(name = "SUBREASON",length=400)
    private String subReason;
    
    @Description("代理状态")
    @Enum(UserLeaveStatus.class)
    @Column(name = "STATUS",length=1)
    private String status;
    
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

    public String getSubstId() {
        return substId;
    }

    public void setSubstId(String substId) {
        this.substId = substId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubstUserId() {
        return substUserId;
    }

    public void setSubstUserId(String substUserId) {
        this.substUserId = substUserId;
    }


    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getInputTime() {
        return inputTime;
    }

    public void setInputTime(LocalDateTime inputTime) {
        this.inputTime = inputTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInputUserId() {
        return inputUserId;
    }

    public void setInputUserId(String inputUserId) {
        this.inputUserId = inputUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getSubReason() {
        return subReason;
    }

    public void setSubReason(String subReason) {
        this.subReason = subReason;
    }

}

