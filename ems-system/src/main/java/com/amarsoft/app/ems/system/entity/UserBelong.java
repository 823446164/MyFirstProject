package com.amarsoft.app.ems.system.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.aecd.system.constant.DataAuth;
import com.amarsoft.aecd.system.constant.MigrationStatus;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 用户机构关系表
 */
@Entity
@Description("用户机构关系表")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_USER_BELONG")
@IdClass(UserBelongPK.class)
//定义与其他实体关联关系
public class UserBelong extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("用户编号")
    @Id
    @Column(name = "USERID", nullable=false, length=40)
    private String userId;
    
    @Description("机构编号")
    @Id
    @Column(name = "ORGID", nullable=false, length=40)
    private String orgId;
    
    @Description("数据权限")
    @Enum(DataAuth.class)
    @Column(name = "DATAAUTH", nullable=false, length=1)
    private String dataAuth;

    @Description("是否默认")
    @Enum(YesNo.class)
    @Column(name = "DEFAULTFLAG", length=1)
    private String defaultFlag;

    @Description("原机构编号")
    @Column(name = "ORIGINORGID", length=40)
    private String originOrgId;
    
    @Description("迁移状态")
    @Enum(MigrationStatus.class)
    @Column(name = "MIGRATIONSTATUS", length=1)
    private String migrationStatus;

    @Description("登记人")
    @Column(name = "INPUTUSERID",length=40)
    @CreatedBy
    private String inputUserId;
    
    @Description("登记时间")
    @Column(name = "INPUTTIME")
    @CreatedDate
    private LocalDateTime inputTime;
    
    @Description("更新人")
    @Column(name = "UPDATEUSERID",length=40)
    @LastModifiedBy
    private String updateUserId;
    
    @Description("更新时间")
    @Column(name = "UPDATETIME")
    @LastModifiedDate
    private LocalDateTime updateTime;
    
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

    public String getDataAuth() {
        return dataAuth;
    }

    public void setDataAuth(String dataAuth) {
        this.dataAuth = dataAuth;
    }

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getOriginOrgId() {
        return originOrgId;
    }

    public void setOriginOrgId(String originOrgId) {
        this.originOrgId = originOrgId;
    }

    public String getMigrationStatus() {
        return migrationStatus;
    }

    public void setMigrationStatus(String migrationStatus) {
        this.migrationStatus = migrationStatus;
    }

    public String getInputUserId() {
        return inputUserId;
    }

    public void setInputUserId(String inputUserId) {
        this.inputUserId = inputUserId;
    }

    public LocalDateTime getInputTime() {
        return inputTime;
    }

    public void setInputTime(LocalDateTime inputTime) {
        this.inputTime = inputTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

}

