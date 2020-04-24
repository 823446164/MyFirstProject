package com.amarsoft.app.ems.system.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 用户代理、角色主键
 */
@Embeddable
public class SubstRolePK implements Serializable{
    private static final long serialVersionUID = 1L;

    private String substId;
    
    private String orgId;
    
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
        result = prime * result + ((substId == null) ? 0 : substId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubstRolePK other = (SubstRolePK) obj;
        if (orgId == null) {
            if (other.orgId != null)
                return false;
        } else if (!orgId.equals(other.orgId))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.roleId))
            return false;
        if (substId == null) {
            if (other.substId != null)
                return false;
        } else if (!substId.equals(other.substId))
            return false;
        return true;
    }

}