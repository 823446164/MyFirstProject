/*
 * 文件名：MenuService.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：菜单service接口类
 * 修改人：jcli2
 * 修改时间：2020年5月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.service;

import javax.validation.Valid;

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
import com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto.SysMenuInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto.SysMenuInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto.SysMenuInfoDtoSaveReq;

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
    /**
     * 菜单详情Info保存
     * @param request
     * @return
     */
    public void sysMenuInfoDtoSave(SysMenuInfoDtoSaveReq sysMenuInfoDtoSaveReq);
    /**
     * 根据菜单编号查询此菜单下已、未配置用户
     * @param request
     * @return
     */
    public SysMenuInfoDtoQueryRsp queryRoleByMenuId(String id);
    /**
     * 根据菜单编号更新菜单状态
     * @param request
     * @return
     */
	public void updateStatus(SysMenuInfoDtoQueryReq sysMenuInfoDtoSaveReq);
	/**
     * 根据菜单编号删除菜单
     * @param request
     * @return
     */
	public void deleteMenuByid(String menuId);

}