/**
 * 用户布局
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.UserLayoutController;
import com.amarsoft.app.ems.system.cs.dto.setuserconfig.SetUserConfigReq;
import com.amarsoft.app.ems.system.cs.dto.setuserlayout.SetUserLayoutReq;
import com.amarsoft.app.ems.system.cs.dto.setusershortcutmenu.SetUserShortcutMenuReq;
import com.amarsoft.app.ems.system.cs.dto.userlayoutquery.UserLayoutQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userlayoutquery.UserLayoutQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.userpanelquery.UserPanelQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenuQueryReq;
import com.amarsoft.app.ems.system.cs.dto.usershortcutmenuquery.UserShortcutMenuQueryRsp;
import com.amarsoft.app.ems.system.service.UserLayoutService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserLayoutControllerImpl implements UserLayoutController {
    
    @Autowired
    UserLayoutService userLayoutService;
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> setUserLayout(@RequestBody @Valid RequestMessage<SetUserLayoutReq> reqMsg) {
        try {
            userLayoutService.setUserLayout(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("设置请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "901213", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<UserLayoutQueryRsp>> userLayoutQuery(@RequestBody @Valid RequestMessage<UserLayoutQueryReq> reqMsg) {
        try {
            UserLayoutQueryRsp rsp = userLayoutService.getUserLayout(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<UserLayoutQueryRsp>>(new ResponseMessage<UserLayoutQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("设置请求报文：" + reqMsg.toString(), e);
            }
            ResponseMessage<UserLayoutQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "901214", e.getMessage());
            return new ResponseEntity<ResponseMessage<UserLayoutQueryRsp>>(hrb, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<UserPanelQueryRsp>> userPanelQuery() {
        try {
            UserPanelQueryRsp rsp = userLayoutService.getUserPanel();
            return new ResponseEntity<ResponseMessage<UserPanelQueryRsp>>(new ResponseMessage<UserPanelQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("获取用户版块错误：" , e);
            }
            // 事务回滚
            ResponseMessage<UserPanelQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "901215", e.getMessage());
            return new ResponseEntity<ResponseMessage<UserPanelQueryRsp>>(hrb, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<UserShortcutMenuQueryRsp>> userShortcutMenuQuery(@RequestBody @Valid RequestMessage<UserShortcutMenuQueryReq> reqMsg) {
        try {
            UserShortcutMenuQueryRsp rsp = userLayoutService.getUserShortcutMenu(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<UserShortcutMenuQueryRsp>>(new ResponseMessage<UserShortcutMenuQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("获取用户版块请求报文：" + reqMsg.toString(), e);
            }
            ResponseMessage<UserShortcutMenuQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "901216", e.getMessage());
            return new ResponseEntity<ResponseMessage<UserShortcutMenuQueryRsp>>(hrb, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> setUserShortcutMenu(@RequestBody @Valid RequestMessage<SetUserShortcutMenuReq> reqMsg) {
        try {
            userLayoutService.setUserShortcutMenu(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("获取用户版块请求报文：" + reqMsg.toString(), e);
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "901217", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> setUserConfig(@RequestBody @Valid RequestMessage<SetUserConfigReq> reqMsg){
        try {
            userLayoutService.setUserConfig(GlobalShareContextHolder.getUserId(), reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("获取用户版块请求报文：" + reqMsg.toString(), e);
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "901218", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}