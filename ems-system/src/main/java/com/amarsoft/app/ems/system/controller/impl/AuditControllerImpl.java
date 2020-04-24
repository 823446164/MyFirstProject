/**
 * 安全信息汇总
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.AuditController;
import com.amarsoft.app.ems.system.cs.dto.allauthchangesquery.AllAuthChangesQueryReq;
import com.amarsoft.app.ems.system.cs.dto.allauthchangesquery.AllAuthChangesQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.alllogininfoquery.AllLoginInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.alllogininfoquery.AllLoginInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.allsecurityinfoquery.AllSecurityInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.onlinenumquery.OnlineNumQueryReq;
import com.amarsoft.app.ems.system.cs.dto.onlinenumquery.OnlineNumQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.onlineuserlist.OnlineUserListReq;
import com.amarsoft.app.ems.system.cs.dto.onlineuserlist.OnlineUserListRsp;
import com.amarsoft.app.ems.system.service.AuditService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuditControllerImpl implements AuditController {
    
    @Autowired
    AuditService auditService;

    
    @Override
    public ResponseEntity<ResponseMessage<AllSecurityInfoQueryRsp>> allSecurityInfoQuery(){
        ResponseMessage<AllSecurityInfoQueryRsp> rspMsg = null;
        try {
            AllSecurityInfoQueryRsp rsp = auditService.allSecurityInfoQuery();
            return new ResponseEntity<ResponseMessage<AllSecurityInfoQueryRsp>>(new ResponseMessage<AllSecurityInfoQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询安全信息汇总出错：", e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "902030",e.getMessage());
            return new ResponseEntity<ResponseMessage<AllSecurityInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<AllAuthChangesQueryRsp>> allAuthChangesQuery(@RequestBody @Valid RequestMessage<AllAuthChangesQueryReq> reqMsg){
        ResponseMessage<AllAuthChangesQueryRsp> rspMsg = null;
        try {
            AllAuthChangesQueryRsp rsp = auditService.allAuthChangesQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<AllAuthChangesQueryRsp>>(new ResponseMessage<AllAuthChangesQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询用户角色变更情况出错：[请求报文：" + reqMsg.toString() + "]", e);
            }
            
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "902031",e.getMessage());
            return new ResponseEntity<ResponseMessage<AllAuthChangesQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<AllLoginInfoQueryRsp>> allLoginInfoQuery(@RequestBody @Valid RequestMessage<AllLoginInfoQueryReq> reqMsg){
        ResponseMessage<AllLoginInfoQueryRsp> rspMsg = null;
        try {
            AllLoginInfoQueryRsp rsp = auditService.allLoginInfoQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<AllLoginInfoQueryRsp>>(new ResponseMessage<AllLoginInfoQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询用户登录信息一览出错：[请求报文：" + reqMsg.toString() + "]", e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "902032",e.getMessage());
            return new ResponseEntity<ResponseMessage<AllLoginInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<OnlineNumQueryRsp>> onlineNumQuery(@Valid RequestMessage<OnlineNumQueryReq> reqMsg) {
        ResponseMessage<OnlineNumQueryRsp> rspMsg = null;
        try {
            OnlineNumQueryRsp rsp = auditService.onlineNumQuery();
            return new ResponseEntity<ResponseMessage<OnlineNumQueryRsp>>(new ResponseMessage<OnlineNumQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询用户在线人数出错：[请求报文：" + reqMsg.toString() + "]", e);
            }
            
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "902033",e.getMessage());
            return new ResponseEntity<ResponseMessage<OnlineNumQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<OnlineUserListRsp>> onlineUserList(@RequestBody @Valid RequestMessage<OnlineUserListReq> reqMsg){
        ResponseMessage<OnlineUserListRsp> rspMsg = null;
        try {
            OnlineUserListRsp rsp = auditService.getOnlineUserList(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<OnlineUserListRsp>>(new ResponseMessage<OnlineUserListRsp>(rsp) , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("在线用户查询请求报文："+ reqMsg.toString(), e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<OnlineUserListRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}