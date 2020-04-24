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
 * 团队下用户表
 */
@Entity
@Description("团队下用户表")
@Table(name = "SYS_TEAM_USER")
//定义与其他实体关联关系
@EntityRelationShip(foreignEntity = UserInfo.class, columns = {"userId"}, foreignColumns = {"userId"})
@EntityRelationShip(foreignEntity =TeamInfo.class, columns = {"teamId"}, foreignColumns = {"teamId"})
@IdClass(UserTeamPK.class)
public class UserTeam extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("用户编号")
    @Id
    @Column(name = "USERID", nullable=false, length=40)
    private String userId;
    
    @Description("团队编号")
    @Id
    @Column(name = "TEAMID", nullable=false, length=40)
    private String teamId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
    
}

