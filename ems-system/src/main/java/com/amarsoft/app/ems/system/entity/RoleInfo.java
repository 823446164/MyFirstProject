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
 * 角色信息
 */
@Entity
@Description("角色信息")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_ROLE_INFO")
//定义与其他实体关联关系
@EntityRelationShip(foreignEntity = RoleAuth.class, columns = {"roleId"}, foreignColumns = {"roleId"})
@EntityRelationShip(foreignEntity = GroupRole.class, columns = {"roleId"}, foreignColumns = {"roleId"})
@EntityRelationShip(foreignEntity = UserRole.class, columns = {"roleId"}, foreignColumns = {"roleId"})
@EntityRelationShip(foreignEntity = SubstRole.class, columns = {"roleId"}, foreignColumns = {"roleId"})
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class RoleInfo extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    /**
         * 角色编号
     */
    @Description("角色编号")
    @Id
    @Column(name = "ROLEID",unique=true, nullable=false, length=40)
    private String roleId;
    
    @Description("角色名称")
    @Column(name = "ROLENAME",length=80)
    private String roleName;
    
    @Description("角色所属机构级别")
    @Column(name = "BELONGORGLEVEL",length=1)
    private String belongOrgLevel;
    
    @Description("角色所属法人")
    @Column(name = "BELONGROOTORG",length=40)
    private String belongRootOrg;
    
    @Description("角色状态")
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
    

}

