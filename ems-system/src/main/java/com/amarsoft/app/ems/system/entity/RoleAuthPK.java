package com.amarsoft.app.ems.system.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 角色访问权限主键
 */
@Embeddable
public class RoleAuthPK implements Serializable{
    private static final long serialVersionUID = 1L;

    private String roleId;
    
    private String authType;
    
    private String authNo;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthNo() {
        return authNo;
    }

    public void setAuthNo(String authNo) {
        this.authNo = authNo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authNo == null) ? 0 : authNo.hashCode());
        result = prime * result + ((authType == null) ? 0 : authType.hashCode());
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
        RoleAuthPK other = (RoleAuthPK) obj;
        if (authNo == null) {
            if (other.authNo != null)
                return false;
        } else if (!authNo.equals(other.authNo))
            return false;
        if (authType == null) {
            if (other.authType != null)
                return false;
        } else if (!authType.equals(other.authType))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.roleId))
            return false;
        return true;
    }

}