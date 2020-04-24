package com.amarsoft.app.ems.system.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.EntityRelationShip;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import lombok.Getter;
import lombok.Setter;
/**
 * 用户布局信息
 */
@Entity
@Description("用户布局信息表")
@EntityListeners(AuditingEntityListener.class)
@EntityRelationShip(foreignEntity = UserInfo.class, columns = {"userId"}, foreignColumns = {"userId"})
@Table(name = "SYS_USER_LAYOUT",
indexes = {
        @Index(name="IDX_USER_LAYOUT_01",columnList="userid")
    })
@Setter
@Getter
@IdClass(UserLayoutPK.class)
public class UserLayout extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("机构编号")
    @Id
    @Column(name = "ORGID",length=40)
    private String orgId;
    
    @Description("用户编号")
    @Id
    @Column(name = "USERID",length=40)
    private String userId;
    
    @Description("布局索引")
    @Id
    @Column(name = "layoutIndex",length=2)
    private String layoutIndex;
    
    @Description("横坐标")
    @Column(name = "X")
    private Integer x;
    
    @Description("纵坐标")
    @Column(name = "Y")
    private Integer y;
    
    @Description("宽度")
    @Column(name = "W")
    private Integer w;

    @Description("高度")
    @Column(name = "H")
    private Integer h;
    
    @Description("引用组件")
    @Column(name = "COMPONENT",length=400)
    private String component;
    
    @Description("参数")
    @Column(name = "PARAMS",length=400)
    private String params;
    
    @Description("登记人")
    @Column(name = "INPUTUSERID",length=40)
    @CreatedBy
    private String inputUserId;
    
    @Description("登记时间 yyyy/MM/dd HH:mm:ss.SSS")
    @Column(name = "INPUTTIME")
    @CreatedDate
    private LocalDateTime inputTime;
    
    @Description("更新人")
    @Column(name = "UPDATEUSERID",length=40)
    @LastModifiedBy
    private String updateUserId;
    
    @Description("更新时间 yyyy/MM/dd HH:mm:ss.SSS")
    @Column(name = "UPDATETIME")
    @LastModifiedDate
    private LocalDateTime updateTime;
    
}

