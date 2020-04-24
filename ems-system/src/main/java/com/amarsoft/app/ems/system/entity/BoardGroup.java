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
 * 通告、角色组、机构关联表
 */
@Entity
@Table(name = "SYS_BOARD_GROUP")
@Description("通告、角色组、机构关联表")
@IdClass(BoardGroupPK.class)
//定义与其他实体关联关系
@EntityRelationShip(foreignEntity = BoardList.class, columns = {"boardId"}, foreignColumns = {"boardId"})
@EntityRelationShip(foreignEntity = GroupInfo.class, columns = {"groupId"}, foreignColumns = {"groupId"})
@EntityRelationShip(foreignEntity = OrgInfo.class, columns = {"orgId"}, foreignColumns = {"orgId"})
public class BoardGroup extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("通知编号")
    @Id
    @Column(name = "BOARDID", nullable=false, length=40)
    private String boardId;
    
    @Description("机构编号")
    @Id
    @Column(name = "ORGID", nullable=false, length=40)
    private String orgId;
    
    @Description("角色组编号")
    @Id
    @Column(name = "GROUPID", nullable=false, length=40)
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

}

