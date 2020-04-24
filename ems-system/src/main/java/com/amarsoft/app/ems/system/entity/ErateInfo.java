package com.amarsoft.app.ems.system.entity;

import java.math.BigDecimal;
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
import com.amarsoft.aecd.common.constant.Currency;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;


/**
 * 汇率表
 */
@Entity
@Description("汇率信息表")
@EntityListeners(AuditingEntityListener.class)
@Table(name="SYS_ERATE_INFO")
@IdClass(ErateInfoPK.class)
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class ErateInfo extends BusinessObject {
    private static final long serialVersionUID = 1L;

    @Description("币种")
    @Id
    @Column(name = "CURRENCY",length=3)
    @Enum(Currency.class)
    private String currency;
    
    @Description("生效日期")
    @Id
    @Column(name = "EFFICIENTDATE",length=10)
    private String efficientDate;
    
    @Description("相对值")
    @Column(name="EXCHANGEVALUE",precision=12,scale=8)
    private BigDecimal exchangeValue;

    @Description("备注")
    @Column(name = "REMARK",length=250)
    private String remark;

    @Description("单位 默认一百，exchangeValue = price/unit")
    @Column(name = "UNIT")
    private Integer unit = 100;

    @Description("中间价")
    @Column(name = "PRICE",precision=24,scale=8)
    private BigDecimal price;
    
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

    public BigDecimal getExchangeValue() {
        return exchangeValue;
    }

    public void setExchangeValue(BigDecimal exchangeValue) {
        this.exchangeValue = exchangeValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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