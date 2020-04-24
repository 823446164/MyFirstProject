package com.amarsoft.app.ems.system.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.EntityRelationShip;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import lombok.Getter;
import lombok.Setter;
/**
 * 用户快捷菜单
 *      本表只存储跟菜单的关联关系
 */
@Entity
@Description("用户快捷菜单表")
@EntityListeners(AuditingEntityListener.class)
@EntityRelationShip(foreignEntity = UserInfo.class, columns = {"userId"}, foreignColumns = {"userId"})
@Table(name = "SYS_USER_SHORTCUT_MENU")
@Setter
@Getter
@IdClass(UserShortcutMenuPK.class)
public class UserShortCutMenu extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("机构编号")
    @Id
    @Column(name = "ORGID",length=40)
    private String orgId;
    
    @Description("用户编号")
    @Id
    @Column(name = "USERID",length=40)
    private String userId;
    
    @Description("菜单编号")
    @Id
    @Column(name = "MENUID",length=40)
    private String menuId;
}

