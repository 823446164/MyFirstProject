package com.amarsoft.app.ems.system.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import lombok.Getter;
import lombok.Setter;
/**
 * 用户版块
 */
@Entity
@Description("用户版块表")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_USER_PANEL")
@Setter
@Getter
public class UserPanel extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("版块编号")
    @Id
    @Column(name = "PANELID",length=40)
    private String panelId;
    
    @Description("中文名")
    @Column(name = "CHINESENAME",length=100)
    private String chineseName;
    
    @Description("英文名")
    @Column(name = "ENGLISHNAME",length=100)
    private String englishName;
    
    @Description("描述")
    @Column(name = "PANELDESCRIPTION",length=400)
    private String panelDescription;
    
    @Description("是否启用")
    @Column(name = "STATUS",length=1)
    private String status;
    
}

