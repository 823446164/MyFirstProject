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

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.RoleController;
import com.amarsoft.app.ems.system.cs.dto.addrole.AddRoleReq;
import com.amarsoft.app.ems.system.cs.dto.deleterole.DeleteRoleReq;
import com.amarsoft.app.ems.system.cs.dto.levelrolequery.LevelRoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelrolequery.LevelRoleQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleauth.RoleAuthReq;
import com.amarsoft.app.ems.system.cs.dto.rolequery.RoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rolequery.RoleQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleuserquery.RoleUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.roleuserquery.RoleUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.updaterole.UpdateRoleReq;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.RoleService;

import lombok.extern.slf4j.Slf4j;

/**
 * 角色服务处理类
 * @author xjzhao
 */
@Slf4j
@RestController
public class RoleControllerImpl implements RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    OrgService orgService;
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> addRole(@RequestBody @Valid RequestMessage<AddRoleReq> reqMsg){
        try {
            roleService.addRole(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900701", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> updateRole(@RequestBody @Valid RequestMessage<UpdateRoleReq> reqMsg){
        try {
            roleService.updateRole(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900703", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> deleteRole(@RequestBody @Valid RequestMessage<DeleteRoleReq> reqMsg){
        try {
            roleService.deleteRole(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900705", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<RoleQueryRsp>> roleQuery(@RequestBody @Valid RequestMessage<RoleQueryReq> reqMsg){
        try {
            RoleQueryRsp rsp = roleService.getRoles(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<RoleQueryRsp>>(new ResponseMessage<RoleQueryRsp>(rsp), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<RoleQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900704", e.getMessage());
            return new ResponseEntity<ResponseMessage<RoleQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> roleAuth(@RequestBody @Valid RequestMessage<RoleAuthReq> reqMsg){
        try {
            roleService.roleAuth(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900707", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<RoleAllQueryRsp>> roleAllQuery(@RequestBody @Valid RequestMessage<RoleAllQueryReq> reqMsg){
        ResponseMessage<RoleAllQueryRsp> rspMsg = null;
        try {
            RoleAllQueryRsp rsp= roleService.roleAllQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<RoleAllQueryRsp>>(new ResponseMessage<RoleAllQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            //事务回滚
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900709",e.getMessage());
            return new ResponseEntity<ResponseMessage<RoleAllQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<LevelRoleQueryRsp>> levelRoleQuery(@RequestBody @Valid RequestMessage<LevelRoleQueryReq> reqMsg){
        ResponseMessage<LevelRoleQueryRsp> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<LevelRoleQueryRsp>();
            LevelRoleQueryRsp rsp = roleService.levelRoleQuery(reqMsg.getMessage(),roleService,orgService);
            return new ResponseEntity<ResponseMessage<LevelRoleQueryRsp>>(new ResponseMessage<LevelRoleQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900704",e.getMessage());
            return new ResponseEntity<ResponseMessage<LevelRoleQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<RoleUserQueryRsp>> roleUserQuery(@RequestBody @Valid RequestMessage<RoleUserQueryReq> reqMsg){
        ResponseMessage<RoleUserQueryRsp> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<RoleUserQueryRsp>();
            RoleUserQueryRsp rsp = roleService.roleUserQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<RoleUserQueryRsp>>(new ResponseMessage<RoleUserQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询角色关联用户请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900709",e.getMessage());
            return new ResponseEntity<ResponseMessage<RoleUserQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}