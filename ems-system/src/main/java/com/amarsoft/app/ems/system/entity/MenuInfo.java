package com.amarsoft.app.ems.system.entity;
import java.time.LocalDateTime;

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
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
/**
 * 菜单信息
 */
@Entity
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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuEnName() {
        return menuEnName;
    }

    public void setMenuEnName(String menuEnName) {
        this.menuEnName = menuEnName;
    }

    public String getMenuTwName() {
        return menuTwName;
    }

    public void setMenuTwName(String menuTwName) {
        this.menuTwName = menuTwName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlParam() {
        return urlParam;
    }

    public void setUrlParam(String urlParam) {
        this.urlParam = urlParam;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public String getMenuAuth() {
        return menuAuth;
    }

    public void setMenuAuth(String menuAuth) {
        this.menuAuth = menuAuth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInputUserId() {
        return inputUserId;
    }

    public void setInputUserId(String inputUserId) {
        this.inputUserId = inputUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public LocalDateTime getInputTime() {
        return inputTime;
    }

    public void setInputTime(LocalDateTime inputTime) {
        this.inputTime = inputTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    
}
