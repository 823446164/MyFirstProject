/**
 * 获取用户布局
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.setuserconfig.SetUserConfigReq;
import com.amarsoft.app.ems.system.cs.dto.setuserlayout.SetUserLayoutReq;
import com.amarsoft.app.ems.system.cs.dto.setusershortcutmenu.SetUserShortcutMenuReq;
import com.amarsoft.app.ems.system.cs.dto.userlayoutquery.UserLayoutQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userlayoutquery.UserLayoutQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.userpanelquery.UserPanelQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenuQueryReq;
import com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenuQueryRsp;

public interface UserLayoutController {
    @PostMapping(value = "/layout/getuserlayout", name="获取用户布局", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserLayoutQueryRsp>> userLayoutQuery(@RequestBody @Valid RequestMessage<UserLayoutQueryReq> reqMsg);
    @PostMapping(value = "/layout/setuserlayout", name="设置用户布局", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> setUserLayout(@RequestBody @Valid RequestMessage<SetUserLayoutReq> reqMsg);
    @PostMapping(value = "/layout/getusershortcutmenu", name="获取快捷菜单", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserShortcutMenuQueryRsp>> userShortcutMenuQuery(@RequestBody @Valid RequestMessage<UserShortcutMenuQueryReq> reqMsg);
    @PostMapping(value = "/layout/setusershortcutmenu", name="设置快捷菜单", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> setUserShortcutMenu(@RequestBody @Valid RequestMessage<SetUserShortcutMenuReq> reqMsg);
    @PostMapping(value = "/layout/getuserpanels", name="获取用户版块组件", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserPanelQueryRsp>> userPanelQuery();
    @PostMapping(value = "/layout/setuserconfig", name="获取用户布局", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> setUserConfig(@RequestBody @Valid RequestMessage<SetUserConfigReq> reqMsg);
}
