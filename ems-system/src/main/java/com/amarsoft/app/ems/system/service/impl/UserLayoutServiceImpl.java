package com.amarsoft.app.ems.system.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.system.cs.dto.setuserconfig.SetUserConfigReq;
import com.amarsoft.app.ems.system.cs.dto.setuserlayout.SetUserLayoutReq;
import com.amarsoft.app.ems.system.cs.dto.setusershortcutmenu.SetUserShortcutMenuReq;
import com.amarsoft.app.ems.system.cs.dto.userlayoutquery.UserLayoutQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userlayoutquery.UserLayoutQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.userpanelquery.UserPanelQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenu;
import com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenuQueryReq;
import com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenuQueryRsp;
import com.amarsoft.app.ems.system.entity.MenuInfo;
import com.amarsoft.app.ems.system.entity.OrgInfo;
import com.amarsoft.app.ems.system.entity.UserInfo;
import com.amarsoft.app.ems.system.entity.UserLayout;
import com.amarsoft.app.ems.system.entity.UserPanel;
import com.amarsoft.app.ems.system.entity.UserShortCutMenu;
import com.amarsoft.app.ems.system.service.UserLayoutService;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户布局（前端）的接口实现类
 *  这个Service存放前端配置，如工作台布局，工作台快捷菜单配置，用户语言习惯和用户的布局等。
 * @author hzhang23
 *
 */
@Slf4j
@Service
public class UserLayoutServiceImpl implements UserLayoutService {

    private final static String KEY_FRE="userlayout:";
    private final static String KEY_FRE_USER="user:";
    private final static String KEY_FRE_MENU="usershortcutmenu:";
    
    @Override
    @CacheEvict(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #req.getOrgId() +'-'+#req.getUserId()")
    public void setUserLayout(SetUserLayoutReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (!CollectionUtils.isEmpty(req.getLayouts())) {
            List<UserLayout> userLayouts = bomanager.loadBusinessObjects(UserLayout.class, 0, Integer.MAX_VALUE, "userId = :userId and orgId =:orgId","userId",req.getUserId(),"orgId",req.getOrgId()).getBusinessObjects();
            Integer[] index = {0};
            if (!CollectionUtils.isEmpty(userLayouts)) { // 全量替换  全部删除后再新增
                bomanager.deleteBusinessObjects(userLayouts);
                bomanager.updateDB();
            }
            ArrayList<ArrayList<UserLayout>> updateList = new ArrayList<ArrayList<UserLayout>>();
            updateList.add(new ArrayList<UserLayout>());
            req.getLayouts().stream().forEach(layout -> {
                UserLayout l = new UserLayout();
                l.setOrgId(req.getOrgId());
                l.setUserId(req.getUserId());
                l.setLayoutIndex(index[0].toString());
                l.setX(layout.getX());
                l.setY(layout.getY());
                l.setW(layout.getW());
                l.setH(layout.getH());
                l.setComponent(layout.getComponent());
                l.setParams(layout.getParams());
                index[0]++;
                updateList.get(0).add(l);
            });
            bomanager.updateBusinessObjects(updateList.get(0));
            bomanager.updateDB();
        }
    }

    @Override
    @Cacheable(value=KEY_FRE, key = "'"+KEY_FRE+"'+ #req.getOrgId()+'-'+#req.getUserId()")
    public UserLayoutQueryRsp getUserLayout(UserLayoutQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserLayoutQueryRsp rsp = new UserLayoutQueryRsp();
        rsp.setLayouts(new ArrayList<com.amarsoft.app.ems.system.cs.dto.userlayoutquery.UserLayout>());
        
        List<UserLayout> userLayouts = bomanager.loadBusinessObjects(UserLayout.class, 0, Integer.MAX_VALUE, "userId = :userId and orgId = :orgId","userId",req.getUserId(),"orgId",req.getOrgId()).getBusinessObjects();
        if (!CollectionUtils.isEmpty(userLayouts)) {
            this.setUserLayoutRsp(userLayouts,rsp);
        }else {
            // 生成默认工作台
            ClassPathResource cpr = new ClassPathResource("userlayout" + File.separator +"defaultlayout.json"); // 工作台配置路径
            
            byte[] b;
            try(InputStream is = cpr.getInputStream()) {
                b = new byte[(int)is.available()];
                is.read(b);
                String defaultLayoutJson = new String(b,"UTF-8");
                JSONObject defaultLayoutList = JSONObject.parseObject(defaultLayoutJson);
                JSONArray defaultLayouts = defaultLayoutList.getJSONArray("layouts");
                for (int i = 0; i < defaultLayouts.size(); i++) {
                    JSONObject jsonLayout = defaultLayouts.getJSONObject(i);
                    UserLayout ul = new UserLayout();
                    ul.setUserId(req.getUserId());
                    ul.setOrgId(req.getOrgId());
                    ul.setLayoutIndex(i + "");
                    ul.setX(jsonLayout.getInteger("x"));
                    ul.setY(jsonLayout.getInteger("y"));
                    ul.setW(jsonLayout.getInteger("w"));
                    ul.setH(jsonLayout.getInteger("h"));
                    ul.setComponent(jsonLayout.getString("component"));
                    ul.setParams(jsonLayout.getString("params"));
                    userLayouts.add(ul);
                }
                this.setUserLayoutRsp(userLayouts,rsp);
            }catch (IOException e) {
                throw new ALSException("900935",e);
            }
        }
        return rsp;
    }
    /**
     * 组装用户工作台报文
     * @param userLayouts
     * @param rsp
     */
    private void setUserLayoutRsp(List<UserLayout> userLayouts,UserLayoutQueryRsp rsp) {
        userLayouts.forEach(layout -> {
            com.amarsoft.app.ems.system.cs.dto.userlayoutquery.UserLayout l = new  com.amarsoft.app.ems.system.cs.dto.userlayoutquery.UserLayout();
            l.setOrgId(layout.getOrgId());
            l.setUserId(layout.getUserId());
            l.setI(Integer.parseInt(layout.getLayoutIndex()));
            l.setX(layout.getX());
            l.setY(layout.getY());
            l.setW(layout.getW());
            l.setH(layout.getH());
            l.setComponent(layout.getComponent());
            l.setParams(layout.getParams());
            rsp.getLayouts().add(l);
        });
    }

    @Override
    public UserPanelQueryRsp getUserPanel() {
        UserPanelQueryRsp rsp = new UserPanelQueryRsp();
        rsp.setPanels(new ArrayList<com.amarsoft.app.ems.system.cs.dto.userpanelquery.UserPanel>());
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<UserPanel> userPanelResult = bomanager.loadBusinessObjects(UserPanel.class, 0, Integer.MAX_VALUE, "1 = 1");
        List<UserPanel> userPanels = userPanelResult.getBusinessObjects();
        rsp.setTotalCount(userPanelResult.getAggregate("count(panelId) as cnt").getInt("cnt"));
        if (!CollectionUtils.isEmpty(userPanels)) {
            userPanels.forEach(panel -> {
                com.amarsoft.app.ems.system.cs.dto.userpanelquery.UserPanel p = new com.amarsoft.app.ems.system.cs.dto.userpanelquery.UserPanel();
                p.setId(panel.getPanelId());
                p.setName(panel.getEnglishName());
                p.setChineseName(panel.getChineseName());
                p.setPanelDescription(panel.getPanelDescription());
                p.setStatus(panel.getStatus());
                rsp.getPanels().add(p);
            });
        }
        return rsp;
    }

    @Override
    @Cacheable(value=KEY_FRE_MENU, key = "'"+KEY_FRE_MENU+"'+ #req.getOrgId()+'-'+#req.getUserId()")
    public UserShortcutMenuQueryRsp getUserShortcutMenu(UserShortcutMenuQueryReq req) {
         UserShortcutMenuQueryRsp rsp = new UserShortcutMenuQueryRsp();
         rsp.setShortcutMenus(new ArrayList<com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenu>());
         BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
         List<UserShortCutMenu> shortcutMenus = bomanager.loadBusinessObjects(UserShortCutMenu.class, 0, Integer.MAX_VALUE, "orgId = :orgId and userId = :userId","orgId",req.getOrgId(),"userId",req.getUserId()).getBusinessObjects();
         List<MenuInfo> allMenu = bomanager.loadBusinessObjects(MenuInfo.class, 0, Integer.MAX_VALUE, "1 = 1").getBusinessObjects();
         if (!CollectionUtils.isEmpty(shortcutMenus)) {
             shortcutMenus.stream() //通过接口获取所有菜单，在所有菜单中筛选用户的快捷菜单信息
             .forEach(menu -> {
                 allMenu.stream()
                 .filter(redisMenu -> redisMenu.getMenuId().equals(menu.getMenuId()))
                 .forEach(redisMenu -> {
                     com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenu rspMenu = new com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenu();
                     rspMenu.setMenuId(redisMenu.getMenuId());
                     rspMenu.setName(redisMenu.getMenuName());
                     rspMenu.setUrl(redisMenu.getUrl());
                     rspMenu.setParams(redisMenu.getUrlParam());
                     rsp.getShortcutMenus().add(rspMenu);
                 });
             });
        }
        return rsp;
    }

    @Override
    @CacheEvict(value=KEY_FRE_MENU, key = "'"+KEY_FRE_MENU+"'+ #req.getShortcutMenus().get(0).getOrgId()+'-'+#req.getShortcutMenus().get(0).getUserId()")
    public void setUserShortcutMenu(SetUserShortcutMenuReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserShortcutMenu firstMenu = req.getShortcutMenus().get(0);
        this.shortcutMenu(bomanager, firstMenu.getUserId(), firstMenu.getOrgId(), req.getShortcutMenus());
        bomanager.updateDB();
    }
    
    /**
     * 快捷菜单保存
     * @param bomanager
     * @param userId
     * @param orgId
     * @param menus
     * @return
     */
    private boolean shortcutMenu(BusinessObjectManager bomanager, String userId, String orgId, List<com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenu> menus) {
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(orgId) ) return false;//用户编号为空不进行菜单设置
        List<UserShortCutMenu> ums = bomanager.loadBusinessObjects(UserShortCutMenu.class, 0, Integer.MAX_VALUE, "userId=:userId and orgId = :orgId", 
                                                                             "userId", userId,"orgId",orgId).getBusinessObjects();
        
        boolean flag = false; //权限是否有过变更
        
        //先区分数据库存在，本次提交的记录中存在或不存在的
        for(UserShortCutMenu ur : ums) {
            boolean exist = false;
            for(com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenu m : menus) {
                if(m.getMenuId().equals(ur.getMenuId()) && m.getOrgId().equals(ur.getOrgId())) {
                    exist = true;
                    break;
                }
            }
            
            if(!exist) {
                flag = true;
                bomanager.deleteBusinessObject(ur);//本次没有传入的角色，全部删除
            }
        }
        
        //再区分本次提交记录存在的，但是数据库记录不存在的
        for(com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenu menu : menus) {
            boolean exist = false;
            for(UserShortCutMenu ur : ums) {
                if(menu.getMenuId().equals(ur.getMenuId()) && menu.getOrgId().equals(ur.getOrgId())) {
                    exist = true;//前面已处理这种情况，这里就不再处理了
                }
            }
            
            if(!exist) {
                MenuInfo mi = bomanager.keyLoadBusinessObject(MenuInfo.class, menu.getMenuId());
                if(mi == null) //如果没有这个角色就跳过
                    continue;
                OrgInfo oi = bomanager.keyLoadBusinessObject(OrgInfo.class, menu.getOrgId());
                if(oi == null) //如果没有这个机构就跳过
                    continue;
                flag = true;
                UserShortCutMenu usm = new UserShortCutMenu();
                usm.setUserId(userId);
                usm.setMenuId(menu.getMenuId());
                usm.setOrgId(menu.getOrgId());
                bomanager.updateBusinessObject(usm);
            }
        }
        if(log.isInfoEnabled() && flag) {
            log.info("用户："+userId+"的快捷菜单从："+ums.toString()+" 变更为："+menus.toString());
        }
        return flag;
    }
    
    @Override
    @CacheEvict(value=KEY_FRE_USER, key="'"+KEY_FRE_USER+"' + #userId")
    public void setUserConfig(String userId , SetUserConfigReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<UserInfo> user = bomanager.loadBusinessObjects(UserInfo.class,"userId = :userId","userId",GlobalShareContextHolder.getUserId());
        if (!StringUtils.isEmpty(req.getLayout())) {
            user.get(0).setLayout(req.getLayout());
        }
        if (!StringUtils.isEmpty(req.getLanguage())) {
            user.get(0).setLanguage(req.getLanguage());
        }
        if (!StringUtils.isEmpty(req.getSkin())) {
            user.get(0).setSkin(req.getSkin());
        }
        bomanager.updateBusinessObjects(user);
        bomanager.updateDB();
    }
}
