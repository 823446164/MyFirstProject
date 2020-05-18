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
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.aecd.system.constant.OrgStatus;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.EntityRelationShip;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 团队信息
 */
@Entity
//定义与其他实体关联关系
@Description("团队信息表")
@EntityRelationShip(foreignEntity = OrgTeam.class, columns = {"teamId"}, foreignColumns = {"teamId"})
@EntityRelationShip(foreignEntity = UserTeam.class, columns = {"teamId"}, foreignColumns = {"teamId"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_TEAM_INFO")
@GeneratedKey(autoGenerateKey=true, allocationSize=1000)
public class TeamInfo extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("团队编号")
    @Id
    @Column(name = "TEAMID",unique=true, nullable=false, length=40)
    private String teamId;
    
    @Description("团队名称")
    @Column(name = "TEAMNAME",length=80)
    private String teamName;

	@Description("团队A角")
    @Column(name = "ROLEA",nullable=false,length=80)
    private String roleA;
    
    @Description("团队B角")
    @Column(name = "ROLEB",length=80)
    private String roleB;
    
    @Description("团队C角")
    @Column(name = "ROLEC",length=80)
    private String roleC;

   
    @Description("团队绩效目标")
    @Column(name = "TARGET",length=80)
    private String target;

    @Description("登记机构")
    @Column(name = "INPUTORGID",length=40)
    private String inputOrgId;

    @Description("更新机构")
    @Column(name = "UPDATEORGID",length=40)
    private String updateOrgId;

    
    @Description("所属法人机构")
    @Column(name = "BELONGROOTORG",length=40)
    private String belongRootOrg;
    
    @Description("所属机构级别")
    @Enum(OrgLevel.class)
    @Column(name = "BELONGORGLEVEL",length=1)
    private String belongOrgLevel;
    
    @Description("所属机构编号")
    @Column(name = "BELONGORGID",length=40)
    private String belongOrgId;

    @Description("任务描述")
    @Column(name = "DESCRIPTION",length=400)
    private String description;
    
    @Description("团队长")
    @Column(name = "TEAMLEADER",length=40)
    private String teamLeader;
    
    @Description("团队状态")
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

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    
    public String getRoleA() {
		return roleA;
	}

	public void setRoleA(String roleA) {
		this.roleA = roleA;
	}

	public String getRoleB() {
		return roleB;
	}

	public void setRoleB(String roleB) {
		this.roleB = roleB;
	}

	public String getRoleC() {
		return roleC;
	}

	public void setRoleC(String roleC) {
		this.roleC = roleC;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getInputOrgId() {
		return inputOrgId;
	}

	public void setInputOrgId(String inputOrgId) {
		this.inputOrgId = inputOrgId;
	}

	public String getUpdateOrgId() {
		return updateOrgId;
	}

	public void setUpdateOrgId(String updateOrgId) {
		this.updateOrgId = updateOrgId;
	}

    public String getBelongOrgId() {
        return belongOrgId;
    }

    public void setBelongOrgId(String belongOrgId) {
        this.belongOrgId = belongOrgId;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
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

    public String getBelongRootOrg() {
        return belongRootOrg;
    }

    public void setBelongRootOrg(String belongRootOrg) {
        this.belongRootOrg = belongRootOrg;
    }

    public String getBelongOrgLevel() {
        return belongOrgLevel;
    }

    public void setBelongOrgLevel(String belongOrgLevel) {
        this.belongOrgLevel = belongOrgLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
