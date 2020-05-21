package com.amarsoft.app.ems.system.entity;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.AuthType;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.EntityRelationShip;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.app.ems.system.cs.dto.usermenu.Menu;

import lombok.Getter;
import lombok.Setter;
/**
 * 访问权限控制表
 */
@Getter
@Setter
@Entity
@Description("访问权限控制表")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_ROLE_AUTH")
@IdClass(RoleAuthPK.class)
//定义与其他实体关联关系
@EntityRelationShip(foreignEntity = RoleInfo.class, columns = {"roleId"}, foreignColumns = {"roleId"})
public class RoleAuth extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("角色编号")
    @Id
    @Column(name = "ROLEID", nullable=false, length=40)
    private String roleId;
    
    @Description("授权类型")
    @Id
    @Enum(AuthType.class)
    @Column(name = "AUTHTYPE", nullable=false, length=1)
    private String authType;
    
    @Description("后端服务API地址URL或菜单编号")
    @Id
    @Column(name = "AUTHNO", nullable=false, length=80)
    private String authNo;

    @Description("后端服务名称")
    @Column(name = "AUTHNAME", length=80)
    private String authName;

    @Description("权限状态")
    @Enum(SystemStatus.class)
    @Column(name = "STATUS",length=1)
    private String status;

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

