/*
 * 文件名：MenuServiceImpl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：菜单service层实现类
 * 修改人：jcli2
 * 修改时间：2020年5月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.Role;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.aecd.common.constant.Language;
import com.amarsoft.aecd.common.constant.Status;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.aecd.system.constant.AuthType;
import com.amarsoft.aecd.system.constant.MenuStatus;
import com.amarsoft.amps.arem.util.SpringHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.cs.dto.addmenu.AddMenuReq;
import com.amarsoft.app.ems.system.cs.dto.deletemenu.DeleteMenuReq;
import com.amarsoft.app.ems.system.cs.dto.menuallquery.MenuAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.menuauth.MenuAuthReq;
import com.amarsoft.app.ems.system.cs.dto.menuidquery.MenuIdQueryReq;
import com.amarsoft.app.ems.system.cs.dto.menuidquery.MenuIdQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.menuquery.MenuQueryReq;
import com.amarsoft.app.ems.system.cs.dto.menuquery.MenuQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.menutreequery.MenuTreeQueryReq;
import com.amarsoft.app.ems.system.cs.dto.menutreequery.MenuTreeQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.menutreequery.Tree;

import com.amarsoft.app.ems.system.cs.dto.updatemenu.UpdateMenuReq;

import com.amarsoft.app.ems.system.entity.MenuInfo;
import com.amarsoft.app.ems.system.entity.OrgInfo;
import com.amarsoft.app.ems.system.entity.RoleAuth;
import com.amarsoft.app.ems.system.entity.RoleInfo;
import com.amarsoft.app.ems.system.service.MenuService;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.RoleService;
import com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto.SysMenuInfoDto;
import com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto.SysMenuInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto.SysMenuInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto.SysMenuInfoDtoSaveReq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RefreshScope
public class MenuServiceImpl implements MenuService {
    
    @Value("${global.business.menu.default-length}")
    private int menuDefaultLength;
    
    
    protected final static String ROOT_MENU_PARENTID = "root"; 
    
    public final static String KEY_FRE_ALL="ASMS_MENU_CACHE_ALL";
    public final static String KEY_FRE_ALL_EN="ASMS_MENU_CACHE_ALL_EN";
    public final static String KEY_FRE_ALL_TW="ASMS_MENU_CACHE_ALL_TW";
    public final static String KEY_FRE_ROLE_ALL="ASMS_ROLE_CACHE_ALL";
    
    
    @Override
    @Caching(evict= {
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ROLE_ALL"),
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL"),
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL_EN"),
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL_TW"),
    })
    public boolean menuAuth(MenuAuthReq req) {
        boolean flag = false;
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        MenuInfo mi = bomanager.keyLoadBusinessObject(MenuInfo.class, req.getMenuId());
        if(mi == null) {//数据库不存在该菜单
            mi = new MenuInfo();
            if(log.isInfoEnabled()) {
                log.info("新增菜单："+req.getMenuId()+"的权限为："+req.getMenuAuth());
            }
        }else {
            if(log.isInfoEnabled() && !req.getMenuAuth().equals(mi.getMenuAuth())) {
                log.info("更新菜单："+req.getMenuId()+"的权限从："+mi.getMenuAuth()+" 变更为："+req.getMenuAuth());
            }
        }
        
        mi.setMenuId(req.getMenuId());
        mi.setMenuName(req.getMenuName());
        flag = flag || mi.getStatus() == null || !req.getStatus().equals(mi.getStatus());
        mi.setStatus(req.getStatus());
        flag = flag || mi.getMenuAuth() == null || !req.getMenuAuth().equals(mi.getMenuAuth());
        mi.setMenuAuth(req.getMenuAuth());
        
        bomanager.updateBusinessObject(mi);
        bomanager.updateDB();
        //更新角色权限表状态
        bomanager.updateObjectBySql(RoleAuth.class, "status=:status", "authType=:authType and authNo=:authNo", 
                                                    "status", req.getStatus(),
                                                    "authType", AuthType.MENU.id,
                                                    "authNo", req.getMenuId());
        return flag;
    }

    @Override
    public MenuQueryRsp getMenu(MenuQueryReq req) { 
    	String[] searchAttributes = {"roleId","roleName"};//查询条件
	    BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
	    MenuInfo mi = bomanager.keyLoadBusinessObject(MenuInfo.class, req.getMenuId());
	    MenuQueryRsp rsp = new MenuQueryRsp();
	    rsp.setRoles(new ArrayList<com.amarsoft.app.ems.system.cs.dto.userrole.Role>());
	    if(mi != null) {
	        rsp.setMenuId(mi.getMenuId());
	        rsp.setMenuName(mi.getMenuName());
	        rsp.setMenuEnName(mi.getMenuEnName());
	        rsp.setMenuTwName(mi.getMenuTwName());
	        rsp.setMenuAuth(mi.getMenuAuth());
	        rsp.setSortNo(mi.getSortNo());
	        rsp.setIcon(mi.getIcon());
	        rsp.setUrl(mi.getUrl());
	        rsp.setUrlParam(mi.getUrlParam());
	        rsp.setParentId(mi.getParentId());
	        MenuInfo pmi = bomanager.keyLoadBusinessObject(MenuInfo.class,mi.getParentId());
	        if (mi.getParentId().equals(ROOT_MENU_PARENTID)) {
	            rsp.setParentName("");
	        }else {
	            rsp.setParentName(pmi.getMenuName());
	        }
	        rsp.setStatus(mi.getStatus());
	        
	        List<RoleAuth> roleAuths = bomanager.loadBusinessObjects(RoleAuth.class, 0, Integer.MAX_VALUE, "authType =:authType and authNo = :authNo order by authNo",
	                "authType",AuthType.MENU.id,"authNo",mi.getMenuId()).getBusinessObjects();
	        if (StringUtils.isEmpty(req.getSearchAttribute()) && StringUtils.isEmpty(req.getSearchContent())) {//不走查询条件
	            roleAuths.forEach(roleAuth -> {
	            	com.amarsoft.app.ems.system.cs.dto.userrole.Role role = new com.amarsoft.app.ems.system.cs.dto.userrole.Role();
	                role.setRoleId(roleAuth.getRoleId());
	                RoleInfo r = bomanager.keyLoadBusinessObject(RoleInfo.class, roleAuth.getRoleId());
	                OrgInfo org = bomanager.keyLoadBusinessObject(OrgInfo.class, r.getBelongRootOrg());
	                role.setRoleName(r.getRoleName());
	                role.setBelongOrgLevel(r.getBelongOrgLevel());
	                role.setBelongOrgType(org.getOrgType());
	                role.setBelongRootOrg(r.getBelongRootOrg());
	                rsp.getRoles().add(role);
	            });
	        }else {
	            if (Stream.of(searchAttributes).anyMatch(searchAttribute -> searchAttribute.equalsIgnoreCase(req.getSearchAttribute()))) {//验证查询条件
	                roleAuths.forEach(roleAuth ->{
	                	com.amarsoft.app.ems.system.cs.dto.userrole.Role role = new com.amarsoft.app.ems.system.cs.dto.userrole.Role();
	                    role.setRoleId(roleAuth.getRoleId());
	                    RoleInfo r = bomanager.keyLoadBusinessObject(RoleInfo.class, roleAuth.getRoleId());
	                    OrgInfo org = bomanager.keyLoadBusinessObject(OrgInfo.class, r.getBelongRootOrg());
	                    role.setRoleName(r.getRoleName());
	                    role.setBelongOrgLevel(r.getBelongOrgLevel());
	                    role.setBelongOrgType(org.getOrgType());
	                    role.setBelongRootOrg(r.getBelongRootOrg());
	                    rsp.getRoles().add(role);
	                });
	            }
	        }
	    }
	    return rsp;
    }

    @Override
    @Caching(cacheable={
            @Cacheable(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL",condition="#language.equals('zh-cn')"),
            @Cacheable(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL_EN",condition="#language.equals('en-us')"),
            @Cacheable(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL_TW",condition="#language.equals('zh-tw')")
    })
    public MenuAllQueryRsp getAllMenu(String language) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        MenuAllQueryRsp rsp = new MenuAllQueryRsp();
        rsp.setMenuInfos(new ArrayList<com.amarsoft.app.ems.system.cs.dto.usermenu.Menu>());
        
        List<MenuInfo> allMenu = bomanager.loadBusinessObjects(MenuInfo.class, 0, Integer.MAX_VALUE, "status =:status order by sortNo","status",Status.Valid.id).getBusinessObjects();
        allMenu.stream()
        .filter(menu -> menu.getParentId().equals(ROOT_MENU_PARENTID))
        .forEach(menu ->{
            com.amarsoft.app.ems.system.cs.dto.usermenu.Menu rootMenu = new com.amarsoft.app.ems.system.cs.dto.usermenu.Menu();
            setMenuInfo(rootMenu, menu, language, bomanager);
            addChilren(rootMenu,menu,allMenu, language, bomanager);
            rsp.getMenuInfos().add(rootMenu);
        });
        return rsp;
    }

    /**
     * 设置菜单的子节点
     * @param rootMenu
     * @param menuInfo
     * @param allMenus
     * @param language
     * @param bomanager
     */
    private void addChilren(com.amarsoft.app.ems.system.cs.dto.usermenu.Menu rootMenu,MenuInfo menuInfo,List<MenuInfo> allMenus,String language, BusinessObjectManager bomanager) {
        for (MenuInfo allMenu : allMenus) {
            if(allMenu.getParentId().equals(menuInfo.getMenuId())) { //递归逻辑
                com.amarsoft.app.ems.system.cs.dto.usermenu.Menu childMenu = new com.amarsoft.app.ems.system.cs.dto.usermenu.Menu();
                setMenuInfo(childMenu,allMenu, language,bomanager);//塞值
                addChilren(childMenu, allMenu, allMenus, language, bomanager);//递归调用
                rootMenu.getChildren().add(childMenu);//根对象添加子节点
            }
        }
    }
    
    /**
     * 设置菜单的基本信息
     * @param childMenu
     * @param menuInfo
     * @param language
     * @param bomanager
     */
    private void setMenuInfo(com.amarsoft.app.ems.system.cs.dto.usermenu.Menu childMenu,MenuInfo menuInfo,String language, BusinessObjectManager bomanager) {
        childMenu.setMenuId(menuInfo.getMenuId());
        childMenu.setSortNo(menuInfo.getSortNo());
        if (language.equals(Language.zh_CN.id.toLowerCase(Locale.ENGLISH))) {
            childMenu.setName(menuInfo.getMenuName());
        }else if (language.equals(Language.zh_TW.id.toLowerCase(Locale.ENGLISH))) {
            childMenu.setName(menuInfo.getMenuTwName());
        }
//        else if (language.equals(Language.EN.id.toLowerCase(Locale.ENGLISH))) {
//            childMenu.setName(menuInfo.getMenuEnName());
//        }
        else {
            childMenu.setName(menuInfo.getMenuName());
        }
        childMenu.setIcon(menuInfo.getIcon());
        childMenu.setUrl(menuInfo.getUrl());
        childMenu.setUrlParam(menuInfo.getUrlParam());
        childMenu.setParentId(menuInfo.getParentId());
        childMenu.setMenuAuth(menuInfo.getMenuAuth());
        childMenu.setStatus(menuInfo.getStatus());
        childMenu.setChildren(new ArrayList<com.amarsoft.app.ems.system.cs.dto.usermenu.Menu>());
    }
    
    @Override
    @Caching(evict= {
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL"),
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL_EN"),
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL_TW"),
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ROLE_ALL"),
    })
    public void updateMenu(UpdateMenuReq req, OrgService orgService) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        MenuInfo menuInfo= bomanager.keyLoadBusinessObject(MenuInfo.class, req.getMenuId());
        menuInfo.setSortNo(StringUtils.isEmpty(req.getSortNo())?req.getMenuId():req.getSortNo());
        menuInfo.setMenuName(req.getMenuName());
        menuInfo.setMenuTwName(req.getMenuTwName());
        menuInfo.setMenuEnName(req.getMenuEnName());
        menuInfo.setIcon(req.getIcon());
        menuInfo.setUrl(req.getUrl());
        menuInfo.setUrlParam(req.getUrlParam());
        
        if(StringUtils.isEmpty(req.getParentId()) 
                || req.getMenuId().contentEquals(req.getParentId()))
        {
            menuInfo.setParentId(ROOT_MENU_PARENTID);
        }else {
            menuInfo.setParentId(req.getParentId());
        }
        menuInfo.setMenuAuth(StringUtils.isEmpty(req.getMenuAuth())?"":req.getMenuAuth());
        menuInfo.setStatus(req.getStatus());
        
        bomanager.updateBusinessObject(menuInfo);
        
        List<RoleAuth> roleAuths = bomanager.loadBusinessObjects(RoleAuth.class, 0, Integer.MAX_VALUE, "authType = :authType and authNo = :authNo","authType",AuthType.MENU.id,"authNo",menuInfo.getMenuId()).getBusinessObjects();//查询表里已有MenuRole关联关系
        if(!CollectionUtils.isEmpty(req.getRoleId())) {
            for (String roleId : req.getRoleId()) {
                
                SpringHelper.getBean(RoleService.class).cleanRoleCache(roleId);
                
                RoleInfo role = bomanager.keyLoadBusinessObject(RoleInfo.class, roleId);
                if(null == role) {//如果没有这个角色则跳过
                    continue;
                }
                
                if (orgService != null) {
                    String loginRootOrg = orgService.getRootOrgId(GlobalShareContextHolder.getOrgId());
                    if (!role.getBelongRootOrg().equals(loginRootOrg)) { //如果登陆机构的法人与权限配置的法人不同则跳过修改
                        continue;
                    }
                }
                
                boolean flag = false;
                for(RoleAuth roleAuth : roleAuths) {
                    if(roleAuth.getRoleId().equals(roleId)) {
                        flag = true;//已经存在
                        roleAuth.setStatus(Status.Valid.id);
                        bomanager.updateBusinessObject(roleAuth);
                    }
                }
                
                if(!flag) {//不存在就新增加一个
                    List<MenuInfo> children = bomanager.loadBusinessObjects(MenuInfo.class, "parentId = :menuId","menuId",req.getMenuId());
                        RoleAuth roleAuth = new RoleAuth();
                        roleAuth.setAuthType(AuthType.MENU.id);
                        roleAuth.setAuthNo(req.getMenuId());
                        roleAuth.setRoleId(roleId);
                        roleAuth.setStatus(Status.Valid.id);
                        bomanager.updateBusinessObject(roleAuth);
                    if (!CollectionUtils.isEmpty(children))  {
                        children.forEach(child -> {
                            this.updateMenuChildrenRoleAuth(bomanager, child.getMenuId(), roleId);
                        });
                    }
                }
            }
            
            //查找已经存在的，需要删除的记录
            for(RoleAuth roleAuth : roleAuths) {
                boolean flag = false;
                
                for (String roleId : req.getRoleId()) {
                    if(roleAuth.getRoleId().equals(roleId)) {
                        flag = true;//存在
                    }
                }
                
                //不存在的删除掉
                if(!flag) {
                    bomanager.deleteBusinessObject(roleAuth);
                }
            }
        }
        else {
            bomanager.deleteBusinessObjects(roleAuths);
        }
        
        if (log.isInfoEnabled()) {
            log.info("菜单："+menuInfo.getMenuId()+"-"+menuInfo.getMenuName()+" 的权限从： "+roleAuths+" 变更为："+req.getRoleId());
        }
        bomanager.updateDB();
    }
    
    private void updateMenuChildrenRoleAuth(BusinessObjectManager bomanager, String menuId, String roleId) {
        List<MenuInfo> children = bomanager.loadBusinessObjects(MenuInfo.class, "parentId = :menuId", "menuId", menuId);
        List<RoleAuth> dbRa = bomanager.loadBusinessObjects(RoleAuth.class, "roleId = :roleId and authNo = :authNo and authType =:authType", "roleId",roleId, "authNo", menuId ,"authType", AuthType.MENU.id);
        if (!CollectionUtils.isEmpty(dbRa)) {
            return;
        }
        RoleAuth roleAuth = new RoleAuth();
        roleAuth.setAuthType(AuthType.MENU.id);
        roleAuth.setAuthNo(menuId);
        roleAuth.setRoleId(roleId);
        roleAuth.setStatus(Status.Valid.id);
        bomanager.updateBusinessObject(roleAuth);
        if (!CollectionUtils.isEmpty(children)) {
            List<RoleAuth> ras = new ArrayList<>();
            children.forEach(child ->{
                this.updateMenuChildrenRoleAuth(bomanager, child.getMenuId(), roleId);
                RoleAuth ra = new RoleAuth();
                ra.setAuthType(AuthType.MENU.id);
                ra.setAuthNo(child.getMenuId());
                ra.setRoleId(roleId);
                ra.setStatus(Status.Valid.id);
                ras.add(ra);
            });
            bomanager.updateBusinessObjects(ras);
        }
    }

    @Override
    @Caching(evict= {
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL"),
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL_EN"),
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL_TW"),
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ROLE_ALL"),
    })
    public void addMenu(AddMenuReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        MenuInfo menuInfo= new MenuInfo();
        menuInfo.setMenuId(req.getMenuId());
        menuInfo.setSortNo(StringUtils.isEmpty(req.getSortNo())?req.getMenuId():req.getSortNo());
        menuInfo.setMenuName(req.getMenuName());
        menuInfo.setMenuTwName(req.getMenuTwName());
        menuInfo.setMenuEnName(req.getMenuEnName());
        menuInfo.setIcon(req.getIcon());
        menuInfo.setUrl(req.getUrl());
        menuInfo.setUrlParam(req.getUrlParam());
        if(StringUtils.isEmpty(req.getParentId()) 
                || req.getMenuId().contentEquals(req.getParentId()))
        {
            menuInfo.setParentId(ROOT_MENU_PARENTID);
        }else {
            menuInfo.setParentId(req.getParentId());
        }
        
        menuInfo.setMenuAuth(StringUtils.isEmpty(req.getMenuAuth())?"":req.getMenuAuth());
        menuInfo.setStatus(Status.Valid.id);
        bomanager.updateBusinessObject(menuInfo);
        
        if(!CollectionUtils.isEmpty(req.getRoleId())) {
            List<RoleAuth> roleAuths = new ArrayList<RoleAuth>();
            for (String roleId : req.getRoleId()) {
                
                SpringHelper.getBean(RoleService.class).cleanRoleCache(roleId);
                
                RoleInfo role = bomanager.keyLoadBusinessObject(RoleInfo.class, roleId);
                if(null == role) {
                    continue;//不存在的角色 跳过
                }
                RoleAuth roleAuth = new RoleAuth();
                roleAuth.setAuthType(AuthType.MENU.id);
                roleAuth.setAuthNo(menuInfo.getMenuId());
                roleAuth.setRoleId(roleId);
                roleAuth.setStatus(Status.Valid.id);
                roleAuths.add(roleAuth);
            }
            bomanager.updateBusinessObjects(roleAuths);
        }
        bomanager.updateDB();
    }
    
    @Override
    @Caching(evict= {
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL"),
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL_EN"),
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ALL_TW"),
            @CacheEvict(value=KEY_FRE_ALL, key="#root.target.KEY_FRE_ROLE_ALL"),
    })
    public void deleteMenu(DeleteMenuReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        //删除当前菜单和子菜单
        bomanager.deleteObjectBySql(MenuInfo.class, "menuId like :menuId", "menuId", req.getMenuId() + "%");
        bomanager.deleteObjectBySql(RoleAuth.class, "authNo like :menuId and authType = :authType", "menuId", req.getMenuId() + "%","authType",AuthType.MENU.id);
        bomanager.updateDB();
    }

    @Override
    public MenuTreeQueryRsp menuTreeQuery(MenuTreeQueryReq req,MenuService menuService,String language) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        MenuTreeQueryRsp rsp = new MenuTreeQueryRsp();
        rsp.setTrees(new ArrayList<Tree>());
        
        if (StringUtils.isEmpty(req.getIsShowAllModel())) {//默认展示全部
            req.setIsShowAllModel(YesNo.Yes.id);
        }
        
        List<MenuInfo> allMenu = bomanager.loadBusinessObjects(MenuInfo.class, 0, Integer.MAX_VALUE, "1 = 1").getBusinessObjects();
        for (MenuInfo menu : allMenu) {
            if(ROOT_MENU_PARENTID.equals(menu.getParentId())) {
                Tree rootNode = new Tree();
                rootNode.setChildren(new ArrayList<Tree>());
                if (language.equals(Language.zh_CN.id)) { // 根据语言确定字段
                    rootNode.setTitle(menu.getMenuName());
                }else if (language.equals(Language.zh_TW.id)) {
                    rootNode.setTitle(menu.getMenuTwName());
                }
//                else if (language.equals(Language.EN.id)) {
//                    rootNode.setTitle(menu.getMenuEnName());
//                }
                else {
                    rootNode.setTitle(menu.getMenuName());
                }
                rootNode.setSortNo(menu.getSortNo());
                rootNode.setKey(menu.getMenuId());
                rootNode.setExpanded(CollectionUtils.isEmpty(rsp.getTrees()));//展开第一个根节点
                rootNode.setLeaf(Boolean.FALSE);
                if(menu.getStatus().equals(Status.Valid.id)) {// 有效、停用展示，无效则不展示
                    rootNode.setDisable(Boolean.FALSE);
                }else if(menu.getStatus().equals(Status.BlockUp.id)) {
                    rootNode.setDisable(Boolean.TRUE);
                }
                this.addChildren(rootNode,menu,allMenu,req.getIsShowAllModel());
                if (req.getIsShowAllModel().equals(YesNo.No.id)  && !menu.getStatus().equals(Status.Valid.id)) { // 如果不展示全部，则只展示有效的菜单
                        continue;
                }
                rsp.getTrees().add(rootNode);
            }
        }
        return rsp;
    }
    
    private void addChildren(Tree rootNode, MenuInfo menuInfo, List<MenuInfo> allMenu,String isShowAllModel) {
        for (MenuInfo menu : allMenu) {
            if(menu.getParentId().equals(menuInfo.getMenuId()) && !menu.getParentId().equals(menu.getMenuId())) {
                Tree node = new Tree();
                node.setChildren(new ArrayList<Tree>());
                node.setTitle(menu.getMenuName());
                node.setSortNo(menu.getSortNo());
                node.setKey(menu.getMenuId());
                node.setExpanded(CollectionUtils.isEmpty(rootNode.getChildren()));//展开第一个根节点
                if(menu.getStatus().equals(Status.Valid.id)) {// 前端Disable为null则不展示该数据，前端为true、false展示
                    node.setDisable(Boolean.FALSE);
                }else if(menu.getStatus().equals(Status.BlockUp.id)) {
                    node.setDisable(Boolean.TRUE);
                }
                this.addChildren(node, menu, allMenu,isShowAllModel);
                if (isShowAllModel.equals(YesNo.No.id)  && !menu.getStatus().equals(Status.Valid.id)) { // 如果不展示全部，则只展示有效的菜单
                    node.setLeaf(Boolean.TRUE);
                    continue;
                }
                rootNode.getChildren().add(node);
                node.setLeaf(CollectionUtils.isEmpty(node.getChildren()));
            }
        }
    }


    @Override
    public MenuAllQueryRsp getMenuList() {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        MenuAllQueryRsp rsp = new MenuAllQueryRsp();
        rsp.setMenuInfos(new ArrayList<com.amarsoft.app.ems.system.cs.dto.usermenu.Menu>());
        List<MenuInfo> menus = bomanager.loadBusinessObjects(MenuInfo.class, 0, Integer.MAX_VALUE, "1 = 1").getBusinessObjects();
        if (!CollectionUtils.isEmpty(menus)) {
            menus.forEach(menu -> {
                com.amarsoft.app.ems.system.cs.dto.usermenu.Menu menuInfo = new com.amarsoft.app.ems.system.cs.dto.usermenu.Menu();
                menuInfo.setMenuId(menu.getMenuId());
                menuInfo.setSortNo(StringUtils.isEmpty(menu.getSortNo())?menu.getMenuId():menu.getSortNo());
                menuInfo.setName(menu.getMenuName());
                menuInfo.setIcon(menu.getIcon());
                menuInfo.setUrl(menu.getUrl());
                menuInfo.setUrlParam(menu.getUrlParam());
                menuInfo.setParentId(menu.getParentId());
                menuInfo.setMenuAuth(StringUtils.isEmpty(menu.getMenuAuth())?"":menu.getMenuAuth());
                menuInfo.setStatus(Status.Valid.id);
                rsp.getMenuInfos().add(menuInfo);
            });
        }
        return rsp;
    }


    /**
     * 新增，查看详情更新 共用方法
     * @author jcli2
     */
	@Override
	@Transactional
	public void sysMenuInfoDtoSave(SysMenuInfoDtoSaveReq sysMenuInfoDto) {
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
    	/**获取当前登录用户**/
    	String inputUserId=GlobalShareContextHolder.getUserId(); 
    	/**获取当前时间**/
    	LocalDateTime now = LocalDateTime.now(); 
        if(sysMenuInfoDto!=null){
        	/**判断新增or更新**/
        	MenuInfo menuInfo = bomanager.keyLoadBusinessObject(MenuInfo.class,sysMenuInfoDto.getMenuId());
            if(menuInfo==null){
            	/**新增**/
                menuInfo = new MenuInfo();
                menuInfo.setMenuId(sysMenuInfoDto.getMenuId());
                menuInfo.setSortNo(sysMenuInfoDto.getSortNo());
                menuInfo.setMenuName(sysMenuInfoDto.getMenuName());
                menuInfo.setMenuTwName(sysMenuInfoDto.getMenuTwName());
                menuInfo.setUrl(sysMenuInfoDto.getUrl());
                menuInfo.setUrlParam(sysMenuInfoDto.getUrlParam());
                menuInfo.setIcon(sysMenuInfoDto.getIcon());
                menuInfo.setStatus(MenuStatus.New.id);
                if(StringUtils.isEmpty(sysMenuInfoDto.getParentId()) 
                        || sysMenuInfoDto.getMenuId().contentEquals(sysMenuInfoDto.getParentId())){
                    menuInfo.setParentId(ROOT_MENU_PARENTID);
                }else {
                    menuInfo.setParentId(sysMenuInfoDto.getParentId());
                }
                menuInfo.setInputUserId(inputUserId);
                menuInfo.setInputTime(now);
                bomanager.updateBusinessObject(menuInfo);
                /**保存角色**/
                saveRoleAuthByMenuId(bomanager,sysMenuInfoDto,inputUserId,now);
                
            }else {
            	/**更新所填数据**/
            	menuInfo.setMenuName(sysMenuInfoDto.getMenuName());
                menuInfo.setMenuTwName(sysMenuInfoDto.getMenuTwName());
                menuInfo.setUrl(sysMenuInfoDto.getUrl());
                menuInfo.setUrlParam(sysMenuInfoDto.getUrlParam());
                menuInfo.setIcon(sysMenuInfoDto.getIcon());
                /**更新时间与更新用户，前端不填**/
                menuInfo.setUpdateUserId(inputUserId);
                menuInfo.setUpdateTime(now);
                bomanager.updateBusinessObject(menuInfo);
                /**保存角色**/
                saveRoleAuthByMenuId(bomanager,sysMenuInfoDto,inputUserId,now);
			}   
        }
        bomanager.updateDB();
		
	}

	/**
	 * 保存角色
	 * @author jcli2
	 */
	private void saveRoleAuthByMenuId(BusinessObjectManager bomanager,SysMenuInfoDtoSaveReq sysMenuInfoDto,
			String inputid,LocalDateTime time) {
		/**保存已选择角色之前 删除此菜单编号下所有的已配置角色 防止主键冲突**/
    	bomanager.deleteObjectBySql(RoleAuth.class, "authNo=:menuid","menuid",sysMenuInfoDto.getMenuId());
    	/**获取前端所选 角色list、遍历新增**/
    	List<Role> existlist = sysMenuInfoDto.getExistlist();
    	if(!CollectionUtils.isEmpty(existlist)) {
    		RoleAuth roleAuth =null;
    		for (int i = 0; i < existlist.size(); i++) {
    			Role role = existlist.get(i);
    			roleAuth= new RoleAuth();
    			roleAuth.setRoleId(role.getRoleId());
    			roleAuth.setAuthType(AuthType.MENU.id);
    			roleAuth.setAuthNo(sysMenuInfoDto.getMenuId());
    			roleAuth.setAuthName(sysMenuInfoDto.getMenuName());
    			roleAuth.setStatus("1");
    			roleAuth.setInputUserId(inputid);
    			roleAuth.setInputTime(time);
    			roleAuth.setUpdateUserId(inputid);
    			roleAuth.setUpdateTime(time);
    			bomanager.updateBusinessObject(roleAuth);
    		}
    	}
		
	}

	/**
	 * 根据菜单编号查看菜单info详情与 未配置用户、已配置用户
	 * @author jcli2
	 */
	@Override
	@Transactional
	public SysMenuInfoDtoQueryRsp queryRoleByMenuId(String id) {
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		SysMenuInfoDtoQueryRsp rsp=new SysMenuInfoDtoQueryRsp();
		/**塞值 菜单info详情**/
		sysMenuInfoDtoQuery(rsp,id,bomanager);
		
		rsp.setExistlist(new ArrayList<Role>());
		rsp.setNotexistlist(new ArrayList<Role>());
		/**根据菜单编号 查询此菜单下 已配置的用户**/
		String existsql="SELECT b.roleId,b.roleName FROM RoleAuth a inner join RoleInfo b on a.roleId=b.roleId where a.authNo=:id";
		/**根据菜单编号 查询此菜单下 未配置的用户**/
		String notexistsql="select roleId,roleName from RoleInfo where roleId not in " + 
				"(SELECT b.roleId FROM RoleAuth a inner join RoleInfo b on a.roleId=b.roleId where a.authNo=:id)";
		List<BusinessObject> exists = bomanager.selectBusinessObjectsBySql(existsql, "id",id).getBusinessObjects();
		List<BusinessObject> notexists = bomanager.selectBusinessObjectsBySql(notexistsql, "id",id).getBusinessObjects();
		/**遍历数组取出角色编号、姓名放入返回体**/
		 if (!CollectionUtils.isEmpty(exists)) {
			 Role existDto=null;
			 for (int i = 0; i < exists.size(); i++) {
				 existDto= new Role();
				 BusinessObject exist = exists.get(i);
				 existDto.setRoleId(exist.getString("b.ROLEID"));
				 existDto.setRoleName(exist.getString("b.roleName"));
				 rsp.getExistlist().add(existDto);
			}
		 }
		 if (!CollectionUtils.isEmpty(notexists)) {
			 Role notexistDto=null;
			 for (int i = 0; i < notexists.size(); i++) {
				 notexistDto= new Role();
				 BusinessObject notexist = notexists.get(i);
				 notexistDto.setRoleId(notexist.getString("roleId"));
				 notexistDto.setRoleName(notexist.getString("roleName"));
				 rsp.getNotexistlist().add(notexistDto);
			}
		 }

		return rsp;
	}

	/**
	 * 塞值 菜单info详情
	 * @author jcli2
	 */
	private void sysMenuInfoDtoQuery(SysMenuInfoDtoQueryRsp rsp,String id,BusinessObjectManager bomanager) {
        
        MenuInfo menuInfo = bomanager.loadBusinessObject(MenuInfo.class,"menuId",id);
        if(menuInfo!=null){
        	rsp.setMenuId(menuInfo.getMenuId());
        	rsp.setSortNo(menuInfo.getSortNo());
        	rsp.setMenuName(menuInfo.getMenuName());
        	rsp.setMenuTwName(menuInfo.getMenuTwName());
        	rsp.setUrl(menuInfo.getUrl());
        	rsp.setUrlParam(menuInfo.getUrlParam());
        	rsp.setIcon(menuInfo.getIcon());
        	rsp.setStatus(menuInfo.getStatus());
        	rsp.setInputUserId(menuInfo.getInputUserId());
        	rsp.setInputTime(menuInfo.getInputTime());
        }
	}

	/**
	 * 根据菜单编号、状态修改状态
	 * @author jcli2
	 */
	@Override
	@Transactional
	public void updateStatus(SysMenuInfoDtoQueryReq req) {
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		String menuId = req.getMenuId();
		String status = req.getStatus();
		/**如果是停用操作**/
		if (status.equals(MenuStatus.stop.id)) {
			/**停用此菜单下所有子菜单**/
			String updateStatus = "update sys_menu_info set status='"+status+"' where menuId like:id";
			bomanager.updateObjectByNativeSql(updateStatus, "id",menuId+"%");

		}
		/**如果是启用操作**/
		if (status.equals(MenuStatus.start.id)) {
			String updateStatus = "update sys_menu_info set status='"+status+"' where menuId =:id";
			bomanager.updateObjectByNativeSql(updateStatus, "id",menuId);
		}
		bomanager.updateDB();
	}

	/**
	 * 根据菜单编号删除菜单 和 此菜单、子菜单有关联的角色
	 * @author jcli2
	 */
	@Override
	@Transactional
	public void deleteMenuByid(String menuId) {
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		/**删除此菜单  以及下面的子菜单**/
		String deleteMenu = "delete from sys_menu_info where menuId like:id";
		/**  删除 此菜单、子菜单有关联的角色**/
		String deleteRoleAuth = "delete from SYS_ROLE_AUTH where authNo like:id and authType = :authType";
		
		bomanager.deleteObjectByNativeSql(deleteRoleAuth, "id",menuId+"%","authType",AuthType.MENU.id);
		
		bomanager.deleteObjectByNativeSql(deleteMenu, "id",menuId+"%");
		
		bomanager.updateDB();
	}
	/**
	 * 新增时 获取 菜单编号 和 未配置角色列表 和当前录入人
	 * @author jcli2
	 */
	@Override
    @Transactional
    public synchronized MenuIdQueryRsp getMenuId(MenuIdQueryReq req) {
        MenuIdQueryRsp rsp = new MenuIdQueryRsp();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        /**获取菜单编号**/
        BusinessObjectAggregate<BusinessObject> menuIdAggregate = bomanager.
        		selectBusinessObjectsBySql("select max(menuId) as menuId from MenuInfo where parentId = :parentId", "parentId",req.getParentId());
        
        if (!CollectionUtils.isEmpty(menuIdAggregate.getBusinessObjects()) && 
        		!StringUtils.isEmpty(menuIdAggregate.getBusinessObjects().get(0).getString("menuId"))) {
            String menuId = menuIdAggregate.getBusinessObjects().get(0).getString("menuId");
            int subMenuId =Integer.parseInt(menuId.substring(menuId.length()-menuDefaultLength))+1;//机构编号最后几位数值截取加一

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < menuDefaultLength; i++) {
                sb.append("0");
            }
            DecimalFormat decimalformat = new DecimalFormat(sb.toString());
            rsp.setMenuId(menuId.substring(0, menuId.length()-menuDefaultLength) + decimalformat.format(subMenuId));
        }else {
            //新增
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < menuDefaultLength - 1; i++) {
                sb.append("0");
            }
            String menuId = req.getParentId() + sb.append("1").toString();
            rsp.setMenuId(menuId);
        }
        
        /**获取 未配置角色列表**/
        rsp.setNotexistlist(new ArrayList<Role>());
        List<BusinessObject> notexists = bomanager.loadBusinessObjects(RoleInfo.class,"1=1");
        
        if (!CollectionUtils.isEmpty(notexists)) {
			 Role notexistDto=null;
			 for (int i = 0; i < notexists.size(); i++) {
				 notexistDto= new Role();
				 BusinessObject notexist = notexists.get(i);
				 notexistDto.setRoleId(notexist.getString("roleId"));
				 notexistDto.setRoleName(notexist.getString("roleName"));
				 rsp.getNotexistlist().add(notexistDto);
			}
		 }
        /**当前录入人 时间**/
        rsp.setUserId(GlobalShareContextHolder.getUserId());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        String localTime = df.format(time);
        rsp.setTime(localTime);
        return rsp;
    }
}
