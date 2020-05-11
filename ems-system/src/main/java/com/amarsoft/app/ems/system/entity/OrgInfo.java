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

import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.amps.acsc.annotation.FunctionCode;
import com.amarsoft.aecd.common.constant.CountryCode;
import com.amarsoft.aecd.function.impl.CodeCacheFunction;
import com.amarsoft.aecd.system.constant.CompanyType;
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.aecd.system.constant.OrgStatus;
import com.amarsoft.aecd.system.constant.OrgType;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 机构信息
 */
@Entity
@Description("机构信息")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_ORG_INFO")
public class OrgInfo extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("机构编号")
    @Id
    @Column(name="ORGID",unique=true, nullable=false, length=40)
    private String orgId;
    
    @Description("排序号")
    @Column(name = "SORTNO",length=40)
    private String sortNo;
    
    @Description("机构名称")
    @Column(name = "ORGNAME",length=80)
    private String orgName;
    
    @Description("机构级别")
    @Enum(OrgLevel.class)
    @Column(name = "ORGLEVEL",length=1)
    private String orgLevel;
    
    @Description("机构类型")
    @Enum({OrgType.class,CompanyType.class})
    @Column(name = "ORGTYPE",length=1)
    private String orgType;
    
    @Description("父机构代码")
    @Column(name = "PARENTORGID",length=40)
    private String parentOrgId;
    
    @Description("所属分行")
    @Column(name = "BRANCHORGID",length=40)
    private String branchOrgId;
    
    @Description("所属法人")
    @Column(name = "ROOTORGID",length=40)
    private String rootOrgId;

    @Description("国家代码（默认中国）")
    @Enum(CountryCode.class)
    @Column(name = "COUNTRY",length=3)
    private String country = CountryCode.CHN.id;

    @Description("人行金融机构代码")
    @Column(name = "BANKID",length=40)
    private String bankId;
    
    @Description("机构辖区（AreaCode）")
    @FunctionCode(value=CodeCacheFunction.class, paramKeys="codeno", paramValues="AreaCode")
    @Column(name = "BELONGAREA",length=6)
    private String belongArea;
    
    @Description("核心机构号")
    @Column(name = "COREORGID",length=40)
    private String coreOrgId;
    
    @Description("机构地址")
    @Column(name = "ORGADDRESS",length=80)
    private String orgAddress;
    
    @Description("状态")
    @Enum(OrgStatus.class)
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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getBranchOrgId() {
        return branchOrgId;
    }

    public void setBranchOrgId(String branchOrgId) {
        this.branchOrgId = branchOrgId;
    }
    
    public String getRootOrgId() {
        return rootOrgId;
    }

    public void setRootOrgId(String rootOrgId) {
        this.rootOrgId = rootOrgId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBelongArea() {
        return belongArea;
    }

    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
    }

    public String getCoreOrgId() {
        return coreOrgId;
    }

    public void setCoreOrgId(String coreOrgId) {
        this.coreOrgId = coreOrgId;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
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
