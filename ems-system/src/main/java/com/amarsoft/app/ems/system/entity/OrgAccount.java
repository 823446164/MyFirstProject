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
import com.amarsoft.aecd.common.constant.Currency;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;


/**
 * 机构内部账户表
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Description("机构内部账户表")
@Table(name="SYS_ORG_ACCOUNT",
        indexes = {
            @Index(name="IDX_ORG_ACCOUNT_01",columnList="accountOrgId,status")
        })
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class OrgAccount extends BusinessObject {
    private static final long serialVersionUID = 1L;

    @Description("流水号")
    @Id
    @Column(name="SERIALNO",unique=true, nullable=false, length=40)
    private String serialNo;

    @Description("存款账户类型")
    @Column(name = "ACCOUNTTYPE",length=10)
    private String accountType;

    @Description("存款账户账号")
    @Column(name = "ACCOUNTNO",length=40)
    private String accountNo;

    @Description("存款账户币种")
    @Enum(Currency.class)
    @Column(name = "ACCOUNTCURRENCY",length=3)
    private String accountCurrency;

    @Description("存款账户名称")
    @Column(name = "ACCOUNTNAME",length=80)
    private String accountName;

    @Description("存款账号机构号")
    @Column(name = "ACCOUNTORGID", nullable=false, length=40)
    private String accountOrgId;

    @Description("状态")
    @Enum(SystemStatus.class)
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
    
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountOrgId() {
        return accountOrgId;
    }

    public void setAccountOrgId(String accountOrgId) {
        this.accountOrgId = accountOrgId;
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