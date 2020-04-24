package com.amarsoft.app.ems.system.entity;

import java.math.BigDecimal;
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

import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.acct.constant.BaseRateType;
import com.amarsoft.aecd.acct.constant.RateUnit;
import com.amarsoft.aecd.acct.constant.TermUnit;
import com.amarsoft.aecd.common.constant.Currency;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

/**
 * 利率表
 */
@Entity
@Description("利率表")
@EntityListeners(AuditingEntityListener.class)
@Table(name="SYS_RATE_INFO")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class RateInfo extends BusinessObject {
    private static final long serialVersionUID = 1L;

    @Description("利率代号")
    @Id
    @Column(name="RATEID",unique=true, nullable=false, length=40)
    private String rateId;

    @Description("利率名称")
    @Enum(BaseRateType.class)
    @Column(name = "RATETYPE",length=3, nullable=false)
    private String rateType;

    @Description("币种")
    @Enum(Currency.class)
    @Column(name = "CURRENCY",length=3, nullable=false)
    private String currency;

    @Description("生效日期")
    @Column(name = "EFFICIENTDATE",length=10, nullable=false)
    private String efficientDate;

    @Description("利率周期单位")
    @Enum(TermUnit.class)
    @Column(name = "TERMUNIT",length=1, nullable=false)
    private String termUnit;

    @Description("利率周期")
    @Column(name = "TERM",nullable=false)
    private Integer term;

    @Description("利率单位")
    @Enum(RateUnit.class)
    @Column(name = "RATEUNIT",length=3)
    private String rateUnit;

    @Description("利率")
    @Column(name = "RATE",precision=12,scale=8)
    private BigDecimal rate;

    @Description("状态")
    @Enum(SystemStatus.class)
    @Column(name = "STATUS",length=1)
    private String status;

    @Description("备注")
    @Column(name = "REMARK",length=200)
    private String remark;

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

    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEfficientDate() {
        return efficientDate;
    }

    public void setEfficientDate(String efficientDate) {
        this.efficientDate = efficientDate;
    }

    public String getTermUnit() {
        return termUnit;
    }

    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getRateUnit() {
        return rateUnit;
    }

    public void setRateUnit(String rateUnit) {
        this.rateUnit = rateUnit;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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