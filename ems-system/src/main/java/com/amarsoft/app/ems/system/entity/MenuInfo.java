package com.amarsoft.app.ems.system.entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import lombok.Getter;
import lombok.Setter;
/**
 * 菜单信息
 */
@Entity
@Getter
@Setter
@Description("菜单信息表")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_MENU_INFO")
public class MenuInfo extends BusinessObject {
    private static final long serialVersionUID = 1L;
    
    @Description("菜单编号")
    @Id
    @Column(name = "MENUID",unique=true, nullable=false, length=40)
    private String menuId;
    
    @Description("排序编号")
    @Column(name = "SORTNO",length=40)
    private String sortNo;

    @Description("菜单名称")
    @Column(name = "MENUNAME",length=80)
    private String menuName;
    
    @Description("英文菜单名称")
    @Column(name = "MENUENNAME",length=80)
    private String menuEnName;
    
    @Description("中文繁体菜单名称")
    @Column(name = "MENUTWNAME",length=80)
    private String menuTwName;
    
    @Description("菜单图标")
    @Column(name = "ICON",length=50)
    private String icon;
    
    @Description("菜单路径")
    @Column(name = "URL",length=80)
    private String url;

    @Description("菜单路径参数")
    @Column(name = "URLPARAM",length=400)
    private String urlParam;
    
    @Description("上级菜单编号")
    @Column(name = "PARENTID",length=40)
    private String parentId;
    
    @Description("菜单权限（配置该菜单可访问服务端url正则表达式，可以多个以逗号分隔）")
    @Column(name = "MENUAUTH",length=400)
    private String menuAuth;
    
    @Description("菜单状态")
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

    public String getInputTime() {
    	if(inputTime!=null){
    		DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
    		return inputTime.format(sdf);    		
    	}else {
    		return "";
    	}
    }
    
}
