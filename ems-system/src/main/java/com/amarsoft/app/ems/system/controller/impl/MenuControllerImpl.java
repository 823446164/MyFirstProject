package com.amarsoft.app.ems.system.controller.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.MenuController;
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
import com.amarsoft.app.ems.system.service.MenuService;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto.SysMenuInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto.SysMenuInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto.SysMenuInfoDtoSaveReq;

import lombok.extern.slf4j.Slf4j;

/**
 * 菜单权限处理类
 * 
 * 后端存储菜单与可访问url的关系
 * 
 * @Modefiedby hzhang23
 */

@Slf4j
@RestController
public class MenuControllerImpl implements MenuController {

    @Autowired
    MenuService menuService;
    
    @Autowired
    OrgService orgService;
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> menuAuth(@RequestBody @Valid RequestMessage<MenuAuthReq> reqMsg){
        try {
            menuService.menuAuth(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900721", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<MenuQueryRsp>> menuQuery(@RequestBody @Valid RequestMessage<MenuQueryReq> reqMsg){
        try {
            MenuQueryRsp rsp = menuService.getMenu(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<MenuQueryRsp>>(new ResponseMessage<MenuQueryRsp>(rsp), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<MenuQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900722", e.getMessage());
            return new ResponseEntity<ResponseMessage<MenuQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<MenuAllQueryRsp>> menuAllQuery(){
        try {
            MenuAllQueryRsp rsp = menuService.getAllMenu(LocaleContextHolder.getLocale().toLanguageTag().toLowerCase());
            return new ResponseEntity<ResponseMessage<MenuAllQueryRsp>>(new ResponseMessage<MenuAllQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询所有菜单出错：", e);
            }
            ResponseMessage<MenuAllQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900722", e.getMessage());
            return new ResponseEntity<ResponseMessage<MenuAllQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<MenuTreeQueryRsp>> menuTreeQuery(@RequestBody @Valid RequestMessage<MenuTreeQueryReq> req){
        try {
            MenuTreeQueryRsp rsp = menuService.menuTreeQuery(req.getMessage(),menuService,LocaleContextHolder.getLocale().toLanguageTag().toLowerCase());
            return new ResponseEntity<ResponseMessage<MenuTreeQueryRsp>>(new ResponseMessage<MenuTreeQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询菜单树图出错：", e);
            }
            ResponseMessage<MenuTreeQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900722", e.getMessage());
            return new ResponseEntity<ResponseMessage<MenuTreeQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> updateMenu(@RequestBody @Valid RequestMessage<UpdateMenuReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            menuService.updateMenu(reqMsg.getMessage(), orgService);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("修改菜单请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900724",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> addMenu(@RequestBody @Valid RequestMessage<AddMenuReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            menuService.addMenu(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("修改菜单请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900723",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> deleteMenu(@RequestBody @Valid RequestMessage<DeleteMenuReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            menuService.deleteMenu(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("删除菜单请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900725",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    @Override
    public ResponseEntity<ResponseMessage<MenuAllQueryRsp>> menuListQuery() {
        try {
            MenuAllQueryRsp rsp = menuService.getMenuList();
            return new ResponseEntity<ResponseMessage<MenuAllQueryRsp>>(new ResponseMessage<MenuAllQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询所有菜单出错：", e);
            }
            ResponseMessage<MenuAllQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900722", e.getMessage());
            return new ResponseEntity<ResponseMessage<MenuAllQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


	@Override
	@CodeQuery
	@Transactional
	public ResponseEntity<ResponseMessage<Object>> sysMenuInfoDtoSave(@RequestBody
			@Valid RequestMessage<SysMenuInfoDtoSaveReq> reqMsg) {
        ResponseMessage<Object> rspMsg = null;
        try {
            SysMenuInfoDtoSaveReq request = reqMsg.getMessage();
            
            menuService.sysMenuInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("菜单详情Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<SysMenuInfoDtoQueryRsp>> queryRoleByMenuId(@RequestBody
			@Valid RequestMessage<SysMenuInfoDtoQueryReq> reqMsg) {

        ResponseMessage<SysMenuInfoDtoQueryRsp> rspMsg = null;
        try {
            String id = reqMsg.getMessage().getMenuId();
            
            SysMenuInfoDtoQueryRsp response = menuService.queryRoleByMenuId(id);
            rspMsg = new ResponseMessage<SysMenuInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<SysMenuInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("菜单详情Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<SysMenuInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
	}

	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<Object>> updateMenuStatus(@RequestBody
			@Valid RequestMessage<SysMenuInfoDtoQueryReq> reqMsg) {
        ResponseMessage<Object> rspMsg = null;
        try {
        	SysMenuInfoDtoQueryReq request = reqMsg.getMessage();
        	
            menuService.updateStatus(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("菜单详情Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<Object>> deleteMenuById(@RequestBody
			@Valid RequestMessage<SysMenuInfoDtoQueryReq> reqMsg) {

        ResponseMessage<Object> rspMsg = null;
        try {
        	String menuId = reqMsg.getMessage().getMenuId();
        	
            menuService.deleteMenuByid(menuId);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("菜单详情Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	@Override
	@Transactional
    public ResponseEntity<ResponseMessage<MenuIdQueryRsp>> menuIdQuery(@RequestBody @Valid RequestMessage<MenuIdQueryReq> reqMsg){
        ResponseMessage<MenuIdQueryRsp> rspMsg = null;
        try {
            MenuIdQueryRsp rsp = menuService.getMenuId(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<MenuIdQueryRsp>>(new ResponseMessage<MenuIdQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("获取菜单编号请求报文："+ reqMsg.toString(), e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900726",e.getMessage());
            return new ResponseEntity<ResponseMessage<MenuIdQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	
}