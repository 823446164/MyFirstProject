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
import com.amarsoft.aecd.system.constant.UserLeaveType;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 用户假期信息
 */
@Entity
@Description("用户假期信息表")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_USER_LEAVE",
        indexes = {
            @Index(name="IDX_USER_LEAVE_01",columnList="userId,status")
        })
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class UserLeave extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("请假记录编号")
    @Id
    @Column(name = "LEAVEID",unique=true, nullable=false, length=40)
    private String leaveId;
    
    @Description("用户编号")
    @Column(name = "USERID",length=40)
    private String userId;
    
    @Description("请假原因")
    @Column(name = "LEAVEREASON",length=200)
    private String leaveReason;
    
    @Description("请假类型")
    @Column(name = "LEAVETYPE",length=1)
    @Enum(UserLeaveType.class)
    private String leaveType;
    
    @Description("请假开始时间 yyyy/MM/dd HH:mm:ss")
    @Column(name = "BEGINTIME",length=20)
    private String beginTime;

    @Description("请假结束时间 yyyy/MM/dd HH:mm:ss")
    @Column(name = "ENDTIME",length=20)
    private String endTime;
    
    @Description("请假天数")
    @Column(name = "LEAVEDAYS")
    private Integer leaveDays;

    @Description("用户状态")
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

    public String getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(String leaveId) {
        this.leaveId = leaveId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(Integer leaveDays) {
        this.leaveDays = leaveDays;
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

}

