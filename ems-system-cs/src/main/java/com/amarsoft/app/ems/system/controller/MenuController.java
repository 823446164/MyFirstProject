/**
 * 菜单权限管理
 * @Author xxu1
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
    @PostMapping(value = "/menu/getmenuid", name="获取菜单编号", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<MenuIdQueryRsp>> menuIdQuery(@RequestBody @Valid RequestMessage<MenuIdQueryReq> reqMsg);
    @PostMapping(value = "/menu/getmenutree", name="查询菜单树图", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<MenuTreeQueryRsp>> menuTreeQuery(@RequestBody @Valid RequestMessage<MenuTreeQueryReq> req);
}
