package com.amarsoft.app.ems.system.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;



/**
 * UserLayout表的主键类
 */
@Embeddable
public class UserLayoutPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orgId;
    
    private String userId;
    
    private String layoutIndex;
    
    
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public String getLayoutIndex() {
        return layoutIndex;
    }

    public void setLayoutIndex(String layoutIndex) {
        this.layoutIndex = layoutIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((layoutIndex == null) ? 0 : layoutIndex.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserLayoutPK other = (UserLayoutPK) obj;
        if (orgId == null) {
            if (other.orgId != null)
                return false;
        } else if (!orgId.equals(other.orgId))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (layoutIndex == null) {
            if (other.layoutIndex != null)
                return false;
        } else if (!layoutIndex.equals(other.layoutIndex))
            return false;
        return true;
    }
    
    
}