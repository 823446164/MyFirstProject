package com.amarsoft.app.ems.system.entity;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.InheritanceType;


import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.aecd.system.constant.UserEventType;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 用户信息
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserEventBase extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("事件编号")
    @Id
    @Column(name = "EVENTID",unique=true, nullable=false, length=40)
    private String eventId;
    
    @Description("用户编号")
    @Column(name = "USERID",length=40)
    private String userId;
    
    @Description("机构编号")
    @Column(name = "ORGID",length=40)
    private String orgId;
    
    @Description("事件类型")
    @Column(name = "USEREVENTTYPE",length=80)
    @Enum(UserEventType.class)
    private String userEventType;

    @Description("事件时间")
    @Column(name = "EVENTTIME")
    private LocalDateTime eventTime;
    
    @Description("日志")
    @Column(name = "LOG",length=4000)
    private String log;
    
    @Description("操作系统")
    @Column(name = "OS",length=40)
    private String os;
    
    @Description("操作浏览器")
    @Column(name = "BROWSER",length=80)
    private String browser;

    @Description("IP地址")
    @Column(name = "IPADDRESS",length=40)
    private String ipAddress;
    
    @Description("状态")
    @Column(name = "STATUS",length=1)
    @Enum(SystemStatus.class)
    private String status;
    
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserEventType() {
        return userEventType;
    }

    public void setUserEventType(String userEventType) {
        this.userEventType = userEventType;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    
    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress.replace("[", "").replace("]", "");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}

