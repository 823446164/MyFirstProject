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
 * 角色组、角色关联表
 */
@Entity
@Description("角色组、角色关联表")
@Table(name = "SYS_GROUP_ROLE")
//定义与其他实体关联关系
@EntityRelationShip(foreignEntity = GroupRole.class, columns = {"groupId"}, foreignColumns = {"groupId"})
@EntityRelationShip(foreignEntity =RoleInfo.class, columns = {"roleId"}, foreignColumns = {"roleId"})
@IdClass(GroupRolePK.class)
public class GroupRole extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("角色组编号")
    @Id
    @Column(name = "GROUPID", nullable=false, length=40)
    private String groupId;
    
    @Description("角色编号")
    @Id
    @Column(name = "ROLEID", nullable=false, length=40)
    private String roleId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}

