package com.amarsoft.app.ems.system.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.EntityRelationShip;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 机构下团队表
 */
@Entity
@Description("机构下团队表")
@Table(name = "SYS_ORG_TEAM")
//定义与其他实体关联关系
@EntityRelationShip(foreignEntity = TeamInfo.class, columns = {"teamId"}, foreignColumns = {"teamId"})
@EntityRelationShip(foreignEntity =OrgInfo.class, columns = {"orgId"}, foreignColumns = {"orgId"})
@IdClass(OrgTeamPK.class)
public class OrgTeam extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("团队编号")
    @Id
    @Column(name = "TEAMID", nullable=false, length=40)
    private String teamId;
    
    @Description("机构编号")
    @Id
    @Column(name = "ORGID", nullable=false, length=40)
    private String orgId;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}

