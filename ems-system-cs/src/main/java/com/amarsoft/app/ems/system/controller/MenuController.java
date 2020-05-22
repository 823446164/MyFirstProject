/*
 * 文件名：MenuController.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：菜单contoller接口类
 * 修改人：jcli2
 * 修改时间：2020年5月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
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

public interface MenuController {
    @PostMapping(value = "/menu/menuauth", name="菜单权限管理", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> menuAuth(@RequestBody @Valid RequestMessage<MenuAuthReq> reqMsg);
    @PostMapping(value = "/menu/getmenu", name="查询菜单", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<MenuQueryRsp>> menuQuery(@RequestBody @Valid RequestMessage<MenuQueryReq> reqMsg);
    @PostMapping(value = "/menu/addmenu", name="新增菜单", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> addMenu(@RequestBody @Valid RequestMessage<AddMenuReq> reqMsg);
    @PostMapping(value = "/menu/updatemenu", name="修改菜单", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> updateMenu(@RequestBody @Valid RequestMessage<UpdateMenuReq> reqMsg);
    @PostMapping(value = "/menu/deletemenu", name="删除菜单", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> deleteMenu(@RequestBody @Valid RequestMessage<DeleteMenuReq> reqMsg);
    @PostMapping(value = "/menu/getallmenu", name="查询所有菜单", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<MenuAllQueryRsp>> menuAllQuery();
    @PostMapping(value = "/menu/getmenulist", name="查询菜单列表", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<MenuAllQueryRsp>> menuListQuery();
    @PostMapping(value = "/menu/getmenutree", name="查询菜单树图", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<MenuTreeQueryRsp>> menuTreeQuery(@RequestBody @Valid RequestMessage<MenuTreeQueryReq> req);
    /**jcli2**/
    @PostMapping(value = "/menu/infosave", name="菜单详情保存、已选择角色保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> sysMenuInfoDtoSave(@RequestBody @Valid RequestMessage<SysMenuInfoDtoSaveReq> reqMsg);
    @PostMapping(value = "/menu/queryexistrole", name="根据菜单编号查询此菜单详情 与 已配置用户", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<SysMenuInfoDtoQueryRsp>> queryRoleByMenuId(@RequestBody @Valid RequestMessage<SysMenuInfoDtoQueryReq> reqMsg);
    @PostMapping(value = "/menu/updatestatus", name="更新菜单状态", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> updateMenuStatus(@RequestBody @Valid RequestMessage<SysMenuInfoDtoQueryReq> reqMsg);
    @PostMapping(value = "/menu/deletemenubyid", name="根据菜单编号删除菜单", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> deleteMenuById(@RequestBody @Valid RequestMessage<SysMenuInfoDtoQueryReq> reqMsg);
    @PostMapping(value = "/menu/getmenuid", name="获取菜单编号、未配置角色、录入人", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<MenuIdQueryRsp>> menuIdQuery(@RequestBody @Valid RequestMessage<MenuIdQueryReq> reqMsg);
}
