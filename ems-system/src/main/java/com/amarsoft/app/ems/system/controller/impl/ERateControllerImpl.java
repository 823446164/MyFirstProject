package com.amarsoft.app.ems.system.controller.impl;

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
import com.amarsoft.app.ems.system.controller.ERateController;
import com.amarsoft.app.ems.system.cs.dto.erateinfoadd.ErateInfoAddReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoallquery.ErateInfoAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoallquery.ErateInfoAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.erateinfodelete.ErateInfoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery.ErateInfoEfficientQueryReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery.ErateInfoEfficientQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.erateinfoquery.ErateInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoquery.ErateInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.erateinfoupdate.ErateInfoUpdateReq;
import com.amarsoft.app.ems.system.service.ErateService;

import lombok.extern.slf4j.Slf4j;

/**
 * 汇率服务的处理类
 * @author xxu1
 */
@Slf4j
@RestController
public class ERateControllerImpl implements ERateController {
    
    @Autowired
    ErateService erateService;
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<ErateInfoQueryRsp>> erateInfoQuery(@RequestBody @Valid RequestMessage<ErateInfoQueryReq> reqMsg){
        try {
            ErateInfoQueryReq request = reqMsg.getMessage();
            ErateInfoQueryRsp response = erateService.getErateInfo(request);
            return new ResponseEntity<ResponseMessage<ErateInfoQueryRsp>>(new ResponseMessage<ErateInfoQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<ErateInfoQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900601", e.getMessage());
            return new ResponseEntity<ResponseMessage<ErateInfoQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<ErateInfoAllQueryRsp>> erateInfoAllQuery(@RequestBody @Valid RequestMessage<ErateInfoAllQueryReq> reqMsg) {
        try {
            ErateInfoAllQueryReq request = reqMsg.getMessage();
            ErateInfoAllQueryRsp responseList = erateService.getErateAll(request);
            return new ResponseEntity<ResponseMessage<ErateInfoAllQueryRsp>>(new ResponseMessage<ErateInfoAllQueryRsp>(responseList), HttpStatus.OK);
        } catch(Exception e) {
            ResponseMessage<ErateInfoAllQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900601", e.getMessage());
            if(log.isErrorEnabled()) {
                log.error(hrb.getResponseMessage(), e);
            }
            return new ResponseEntity<ResponseMessage<ErateInfoAllQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<ErateInfoEfficientQueryRsp>> erateInfoEfficientQuery(@RequestBody @Valid RequestMessage<ErateInfoEfficientQueryReq> reqMsg){
        try {
            ErateInfoEfficientQueryReq request = reqMsg.getMessage();
            ErateInfoEfficientQueryRsp response = erateService.getErateEfficient(request);
            return new ResponseEntity<ResponseMessage<ErateInfoEfficientQueryRsp>>(new ResponseMessage<ErateInfoEfficientQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<ErateInfoEfficientQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900601", e.getMessage());
            return new ResponseEntity<ResponseMessage<ErateInfoEfficientQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> erateInfoUpdate(@RequestBody @Valid RequestMessage<ErateInfoUpdateReq> reqMsg){
        try {
            erateService.setErateInfo(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900602", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> erateInfoAdd(@RequestBody @Valid RequestMessage<ErateInfoAddReq> reqMsg){
        try {
            ErateInfoAddReq request = reqMsg.getMessage();
            erateService.addErateInfo(request);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900603", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> erateInfoDelete(@RequestBody @Valid RequestMessage<ErateInfoDeleteReq> reqMsg){
        try {
            ErateInfoDeleteReq request = reqMsg.getMessage();
            erateService.deleteErateInfo(request);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900605", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}