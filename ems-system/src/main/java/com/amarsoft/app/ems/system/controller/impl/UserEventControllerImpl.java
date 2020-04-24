/**
 * 查询用户事件
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

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.UserEventController;
import com.amarsoft.app.ems.system.cs.dto.adduserevent.AddUserEventReq;
import com.amarsoft.app.ems.system.cs.dto.usereventquery.UserEventQueryReq;
import com.amarsoft.app.ems.system.cs.dto.usereventquery.UserEventQueryRsp;
import com.amarsoft.app.ems.system.service.UserEventService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserEventControllerImpl implements UserEventController {

    @Autowired
    UserEventService userEventService;
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<UserEventQueryRsp>> userEventQuery(@RequestBody @Valid RequestMessage<UserEventQueryReq> reqMsg){
        try {
            UserEventQueryRsp rsp = userEventService.userEventQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<UserEventQueryRsp>>(new ResponseMessage<UserEventQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询用户事件请求报文："+ reqMsg.toString(), e);
            }
            ResponseMessage<UserEventQueryRsp> rspMsg = ResponseMessage.getResponseMessageFromException(e, "900050",e.getMessage());
            return new ResponseEntity<ResponseMessage<UserEventQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> addUserEvent(@RequestBody @Valid RequestMessage<AddUserEventReq> reqMsg){
        try {
            userEventService.addUserEvent(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("新增用户事件请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> rspMsg = ResponseMessage.getResponseMessageFromException(e, "900051",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}