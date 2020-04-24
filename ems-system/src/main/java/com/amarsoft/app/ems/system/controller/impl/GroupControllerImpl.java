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
import com.amarsoft.app.ems.system.controller.GroupController;
import com.amarsoft.app.ems.system.cs.dto.addgroup.AddGroupReq;
import com.amarsoft.app.ems.system.cs.dto.deletegroup.DeleteGroupReq;
import com.amarsoft.app.ems.system.cs.dto.groupallquery.GroupAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.groupquery.GroupQueryReq;
import com.amarsoft.app.ems.system.cs.dto.groupquery.GroupQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.grouprole.GroupRoleReq;
import com.amarsoft.app.ems.system.cs.dto.groupuserquery.GroupUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.groupuserquery.GroupUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.levelgroupquery.LevelGroupQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelgroupquery.LevelGroupQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.updategroup.UpdateGroupReq;
import com.amarsoft.app.ems.system.service.GroupService;
import com.amarsoft.app.ems.system.service.OrgService;

import lombok.extern.slf4j.Slf4j;

/**
 * 角色组服务处理类
 * @author hzhang23
 */
@Slf4j
@RestController
public class GroupControllerImpl implements GroupController {

    @Autowired
    GroupService groupService;
    
    @Autowired
    OrgService orgService;
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> addGroup(@RequestBody @Valid RequestMessage<AddGroupReq> reqMsg){
        try {
            groupService.addGroup(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900801", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> updateGroup(@RequestBody @Valid RequestMessage<UpdateGroupReq> reqMsg){
        try {
            groupService.updateGroup(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900803", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> deleteGroup(@RequestBody @Valid RequestMessage<DeleteGroupReq> reqMsg){
        try {
            groupService.deleteGroup(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900805", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<GroupQueryRsp>> groupQuery(@RequestBody @Valid RequestMessage<GroupQueryReq> reqMsg){
        try {
            GroupQueryRsp rsp = groupService.getGroups(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<GroupQueryRsp>>(new ResponseMessage<GroupQueryRsp>(rsp), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<GroupQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900804", e.getMessage());
            return new ResponseEntity<ResponseMessage<GroupQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> groupRole(@RequestBody @Valid RequestMessage<GroupRoleReq> reqMsg){
        try {
            groupService.groupRole(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900807", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<GroupAllQueryRsp>> groupAllQuery(@RequestBody @Valid RequestMessage<Object> reqMsg){
        ResponseMessage<GroupAllQueryRsp> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<GroupAllQueryRsp>();
            GroupAllQueryRsp rsp = groupService.groupAllQuery();
            return new ResponseEntity<ResponseMessage<GroupAllQueryRsp>>(new ResponseMessage<GroupAllQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900804",e.getMessage());
            return new ResponseEntity<ResponseMessage<GroupAllQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<GroupUserQueryRsp>> groupUserQuery(@RequestBody @Valid RequestMessage<GroupUserQueryReq> reqMsg){
        ResponseMessage<GroupUserQueryRsp> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<GroupUserQueryRsp>();
            GroupUserQueryRsp rsp = groupService.groupUserQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<GroupUserQueryRsp>>(new ResponseMessage<GroupUserQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询角色组关联用户请求报文："+ reqMsg.toString(), e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900809",e.getMessage());
            return new ResponseEntity<ResponseMessage<GroupUserQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<LevelGroupQueryRsp>> levelGroupQuery(@RequestBody @Valid RequestMessage<LevelGroupQueryReq> reqMsg){
        ResponseMessage<LevelGroupQueryRsp> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<LevelGroupQueryRsp>();
            LevelGroupQueryRsp rsp = groupService.levelGroupQuery(reqMsg.getMessage(),groupService,orgService);
            return new ResponseEntity<ResponseMessage<LevelGroupQueryRsp>>(new ResponseMessage<LevelGroupQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("按法人查询角色组请求失败：", e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900810",e.getMessage());
            return new ResponseEntity<ResponseMessage<LevelGroupQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}