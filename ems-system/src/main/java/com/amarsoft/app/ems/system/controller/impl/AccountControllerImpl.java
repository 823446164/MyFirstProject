package com.amarsoft.app.ems.system.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.AccountController;
import com.amarsoft.app.ems.system.cs.dto.orgaccountadd.OrgAccountAddReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountallquery.OrgAccountAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountallquery.OrgAccountAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccountByOrgQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccountByOrgQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgaccountdelete.OrgAccountDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountquery.OrgAccountQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountquery.OrgAccountQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgaccountupdate.OrgAccountUpdateReq;
import com.amarsoft.app.ems.system.service.AccountService;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统账户服务的处理类
 * @author 核算产品团队
 */
@Slf4j
@RestController
public class AccountControllerImpl implements AccountController{
    
    @Autowired
    @Qualifier("accountServiceImpl")
    AccountService accountService;
    
    @Override
    @Transactional
    @CodeQuery
    public ResponseEntity<ResponseMessage<OrgAccountQueryRsp>> orgAccountQuery(@RequestBody @Valid RequestMessage<OrgAccountQueryReq> reqMsg){
        try {
            OrgAccountQueryReq request = reqMsg.getMessage();
            OrgAccountQueryRsp response = accountService.getAccountInfo(request);
            return new ResponseEntity<ResponseMessage<OrgAccountQueryRsp>>(new ResponseMessage<OrgAccountQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<OrgAccountQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900301", e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgAccountQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    @CodeQuery
    public ResponseEntity<ResponseMessage<OrgAccountByOrgQueryRsp>> orgAccountByOrgQuery(@RequestBody @Valid RequestMessage<OrgAccountByOrgQueryReq> reqMsg){
        try {
            OrgAccountByOrgQueryReq request = reqMsg.getMessage();
            OrgAccountByOrgQueryRsp response = accountService.getAccountByOrg(request);
            return new ResponseEntity<ResponseMessage<OrgAccountByOrgQueryRsp>>(new ResponseMessage<OrgAccountByOrgQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<OrgAccountByOrgQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900301", e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgAccountByOrgQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    @CodeQuery
    public ResponseEntity<ResponseMessage<OrgAccountAllQueryRsp>> orgAccountAllQuery(@RequestBody @Valid RequestMessage<OrgAccountAllQueryReq> reqMsg){
        try {
            OrgAccountAllQueryReq request = reqMsg.getMessage();
            OrgAccountAllQueryRsp response = accountService.getAccountAll(request);
            return new ResponseEntity<ResponseMessage<OrgAccountAllQueryRsp>>(new ResponseMessage<OrgAccountAllQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<OrgAccountAllQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900301", e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgAccountAllQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> orgAccountUpdate(@RequestBody @Valid RequestMessage<OrgAccountUpdateReq> reqMsg){
        try {
            OrgAccountUpdateReq request = reqMsg.getMessage();
            accountService.setAccountInfo(request);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900302", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> orgAccountAdd(@RequestBody @Valid RequestMessage<OrgAccountAddReq> reqMsg){
        try {
            OrgAccountAddReq request = reqMsg.getMessage();
            accountService.addAccountInfo(request);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900303", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> orgAccountDelete(@RequestBody @Valid RequestMessage<OrgAccountDeleteReq> reqMsg){
        try {
            OrgAccountDeleteReq request = reqMsg.getMessage();
            accountService.deleteAccountInfo(request);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900305", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}