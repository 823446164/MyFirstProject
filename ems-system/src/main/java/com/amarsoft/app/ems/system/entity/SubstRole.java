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
 * 用户代理、角色关联表
 */
@Entity
@Description("用户代理、角色关联表")
@Table(name = "SYS_SUBST_ROLE")
@IdClass(SubstRolePK.class)
//定义与其他实体关联关系
@EntityRelationShip(foreignEntity = SubstInfo.class, columns = {"substId"}, foreignColumns = {"substId"})
@EntityRelationShip(foreignEntity = OrgInfo.class, columns = {"orgId"}, foreignColumns = {"orgId"})
@EntityRelationShip(foreignEntity = RoleInfo.class, columns = {"roleId"}, foreignColumns = {"roleId"})
public class SubstRole extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("用户代理编号")
    @Id
    @Column(name = "SUBSTID", nullable=false, length=40)
    private String substId;
    
    @Description("机构编号")
    @Id
    @Column(name = "ORGID", nullable=false, length=40)
    private String orgId;
    
    @Description("角色编号")
    @Id
    @Column(name = "ROLEID", nullable=false, length=40)
    private String roleId;

    public String getSubstId() {
        return substId;
    }

    public void setSubstId(String substId) {
        this.substId = substId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}

