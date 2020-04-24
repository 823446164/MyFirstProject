package com.amarsoft.app.ems.system.service;

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
import com.amarsoft.app.ems.system.cs.dto.updatemenu.UpdateMenuReq;

/**
 * 菜单权限服务的接口
 * @author xjzhao
 */
public interface MenuService {
    /**
     * 菜单权限管理
     * @param req
     * @return 是否变更权限
     */
    public boolean menuAuth(MenuAuthReq req);
    
    /**
     * 查询菜单信息
     * @param req
     * @return
     */
    public MenuQueryRsp getMenu(MenuQueryReq req);
    
    /**
     * 查询所有菜单信息
     * @param 
     * @return 所有菜单
     */
    public MenuAllQueryRsp getAllMenu(String language);
    
    /**
     * 更新菜单信息
     * @param 
     * @return null
     */
    public void updateMenu(UpdateMenuReq message, OrgService orgService);
    
    /**
     * 新增菜单信息
     * @param 
     * @return null
     */
    public void addMenu(AddMenuReq message);
    
    /**
     * 删除菜单信息
     * @param 
     * @return null
     */
    public void deleteMenu(DeleteMenuReq message);

    /**
     * 查询菜单树图
     * @param menuTreeQueryReq 
     * @param menuService 
     * @param 
     * @return null
     */
    public MenuTreeQueryRsp menuTreeQuery(MenuTreeQueryReq req, MenuService menuService,String language);
    /**
     * 查询菜单编号
     * @param MenuIdQueryReq 
     * @param 
     * @return null
     */
    public MenuIdQueryRsp getMenuId(MenuIdQueryReq req);
    /**
     * 查询菜单列表
     * @return
     */
    public MenuAllQueryRsp getMenuList();

}