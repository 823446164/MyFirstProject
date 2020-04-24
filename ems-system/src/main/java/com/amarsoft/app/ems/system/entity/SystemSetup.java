package com.amarsoft.app.ems.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.acct.constant.BatchFlag;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;


/**
 * 交易日期表
 */
@Entity
@Description("交易日期表")
@Table(name="SYSTEM_SETUP")
public class SystemSetup extends BusinessObject {
    private static final long serialVersionUID = 1L;

    @Description("系统ID")
    @Id
    @Column(name = "SYSTEMID",length=10,unique=true, nullable=false)
    private String systemId;

    @Description("营业日期")
    @Column(name = "BUSINESSDATE",length=10, nullable=false)
    private String businessDate;

    @Description("是否能做交易 1 可以做交易; 0 不可以做交易")
    @Enum(YesNo.class)
    @Column(name = "TRANSFLAG",length=1, nullable=false)
    private String transFlag;

    @Description("日终状态")
    @Enum(BatchFlag.class)
    @Column(name = "BATCHFLAG",length=1, nullable=false)
    private String batchFlag;

    @Description("系统投产日期")
    @Column(name = "PRODUCTDATE",length=10)
    private String productDate;

    @Description("批量日期")
    @Column(name = "BATCHDATE",length=10)
    private String batchDate;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getTransFlag() {
        return transFlag;
    }

    public void setTransFlag(String transFlag) {
        this.transFlag = transFlag;
    }

    public String getBatchFlag() {
        return batchFlag;
    }

    public void setBatchFlag(String batchFlag) {
        this.batchFlag = batchFlag;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

}