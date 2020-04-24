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
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.EntityRelationShip;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 角色组信息
 */
@Entity
//定义与其他实体关联关系
@Description("角色组信息表")
@EntityRelationShip(foreignEntity = GroupRole.class, columns = {"groupId"}, foreignColumns = {"groupId"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_GROUP_INFO")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class GroupInfo extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("角色组编号")
    @Id
    @Column(name = "GROUPID",unique=true, nullable=false, length=40)
    private String groupId;
    
    @Description("角色组名称")
    @Column(name = "GROUPNAME",length=80)
    private String groupName;

    @Description("角色组所属机构级别")
    @Column(name = "BELONGORGLEVEL",length=1)
    private String belongOrgLevel;
    
    @Description("角色组所属法人")
    @Column(name = "BELONGROOTORG",length=40)
    private String belongRootOrg;
    
    @Description("角色组状态")
    @Enum(SystemStatus.class)
    @Column(name = "STATUS",length=1)
    private String status;
    
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getBelongOrgLevel() {
        return belongOrgLevel;
    }

    public void setBelongOrgLevel(String belongOrgLevel) {
        this.belongOrgLevel = belongOrgLevel;
    }

    public String getBelongRootOrg() {
        return belongRootOrg;
    }

    public void setBelongRootOrg(String belongRootOrg) {
        this.belongRootOrg = belongRootOrg;
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

