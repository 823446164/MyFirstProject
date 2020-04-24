package com.amarsoft.app.ems.system.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.amarsoft.aecd.common.constant.Language;
import com.amarsoft.aecd.system.constant.Layout;
import com.amarsoft.aecd.system.constant.Skin;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 用户信息
 */
@Entity
@Description("用户信息表")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_USER_INFO", uniqueConstraints=@UniqueConstraint(name = "UQ_USER_INFO_01", columnNames={"logonId"}))
public class UserInfo extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("用户编号")
    @Id
    @Column(name = "USERID",unique=true, nullable=false, length=40)
    private String userId;
    
    @Description("登录ID 用于用户登录")
    @Column(name = "LOGONID",unique=true, nullable=false, length=40)
    private String logonId;
    
    @Description("用户名")
    @Column(name = "USERNAME",length=80)
    private String userName;

    @Description("密码")
    @Column(name = "PASSWORD",length=100)
    private String password;
    
    @Description("最近密码设置时间")
    @Column(name = "PASSWORDTIME",length=23)
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss.SSS")
    private String passwordTime;
    
    @Description("最近一次登录时间")
    @Column(name = "LOGONTIME",length=23)
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss.SSS")
    private String logonTime;

    @Description("最近一次退出时间")
    @Column(name = "LOGOUTTIME",length=23)
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss.SSS")
    private String logoutTime;
  
    @Description("连续密码错误次数")
    @Column(name = "ERRORTIME")
    private Integer errorTime;
    
    @Description("核心柜员号")
    @Column(name = "COUNTER",length=40)
    private String counter;
    
    @Description("邮箱地址")
    @Column(name = "EMAIL",length=40)
    private String email;
    
    @Description("内部工号")
    @Column(name = "EMPNO",length=40)
    private String empNo;
    
    @Description("手机号")
    @Column(name = "PHONENO",length=20)
    private String phoneNo;

    @Description("岗位名称")
    @Column(name = "JOBTITLE",length=80)
    private String jobTitle;

    @Description("头像")
    @Column(name = "HEADPORTRAIT",length=400)
    private String headPortrait;
    
    @Description("用户状态")
    @Column(name = "status",length=1)
    private String status;
    
    @Description("用户语言设置")
    @Column(name = "LANGUAGE",length=10)
    private String language = Language.zh_CN.id;
    
    @Description("用户皮肤设置")
    @Column(name = "SKIN",length=10)
    private String skin = Skin.Default.id;
    
    @Description("用户布局设置")
    @Column(name = "LAYOUT",length=10)
    private String layout = Layout.Side.id;
    
    @Description("办公电话")
    @Column(name = "OFFICETEL",length=20)
    private String officeTel;
    
    @Description("用户状态")
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


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLogonId() {
        return logonId;
    }

    public void setLogonId(String logonId) {
        this.logonId = logonId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordTime() {
        return passwordTime;
    }

    public void setPasswordTime(String passwordTime) {
        this.passwordTime = passwordTime;
    }

    public String getLogonTime() {
        return logonTime;
    }

    public void setLogonTime(String logonTime) {
        this.logonTime = logonTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Integer getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Integer errorTime) {
        this.errorTime = errorTime;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
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

