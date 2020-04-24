package com.amarsoft.app.ems.system.service;

import com.amarsoft.app.ems.system.cs.dto.setuserconfig.SetUserConfigReq;
import com.amarsoft.app.ems.system.cs.dto.setuserlayout.SetUserLayoutReq;
import com.amarsoft.app.ems.system.cs.dto.setusershortcutmenu.SetUserShortcutMenuReq;
import com.amarsoft.app.ems.system.cs.dto.userlayoutquery.UserLayoutQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userlayoutquery.UserLayoutQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.userpanelquery.UserPanelQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenuQueryReq;
import com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenuQueryRsp;

/**
 * 用户布局（前端）的处理接口
 * @author hzhang23
 */
public interface UserLayoutService {

    /**
     * 设置用户布局
     * @param message
     */
    void setUserLayout(SetUserLayoutReq message);

    /**
     * 获取用户布局
     * @param message
     * @return
     */
    UserLayoutQueryRsp getUserLayout(UserLayoutQueryReq message);

    /**
     * 获取用户版块
     * @param message
     * @return
     */
    UserPanelQueryRsp getUserPanel();

    /**
     * 获取用户快捷菜单
     * @param message
     * @return
     */
    UserShortcutMenuQueryRsp getUserShortcutMenu(UserShortcutMenuQueryReq message);

    /**
     * 设置用户快捷菜单
     * @param message
     */
    void setUserShortcutMenu(SetUserShortcutMenuReq message);

    /**
     * 设置用户皮肤/语言/布局
     * @param message
     */
    void setUserConfig(String userId ,SetUserConfigReq message);

}