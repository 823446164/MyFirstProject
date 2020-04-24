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
 * 用户、角色组、机构关联表
 */
@Entity
@Table(name = "SYS_USER_GROUP")
@Description("用户、角色组、机构关联表")
@IdClass(UserGroupPK.class)
//定义与其他实体关联关系
@EntityRelationShip(foreignEntity = GroupInfo.class, columns = {"groupId"}, foreignColumns = {"groupId"})
@EntityRelationShip(foreignEntity = OrgInfo.class, columns = {"orgId"}, foreignColumns = {"orgId"})
public class UserGroup extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("用户编号")
    @Id
    @Column(name = "USERID", nullable=false, length=40)
    private String userId;
    
    @Description("机构编号")
    @Id
    @Column(name = "ORGID", nullable=false, length=40)
    private String orgId;
    
    @Description("角色组编号")
    @Id
    @Column(name = "GROUPID", nullable=false, length=40)
    private String groupId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}

