package com.amarsoft.app.ems.system.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 通告、机构、角色主键
 */
@Embeddable
public class BoardGroupPK implements Serializable{
    private static final long serialVersionUID = 1L;

    private String boardId;
    
    private String orgId;
    
    private String groupId;



    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
        result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
        result = prime * result + ((boardId == null) ? 0 : boardId.hashCode());
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
        BoardGroupPK other = (BoardGroupPK) obj;
        if (orgId == null) {
            if (other.orgId != null)
                return false;
        } else if (!orgId.equals(other.orgId))
            return false;
        if (groupId == null) {
            if (other.groupId != null)
                return false;
        } else if (!groupId.equals(other.groupId))
            return false;
        if (boardId == null) {
            if (other.boardId != null)
                return false;
        } else if (!boardId.equals(other.boardId))
            return false;
        return true;
    }

}