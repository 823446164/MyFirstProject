package com.amarsoft.app.ems.system.controller.impl;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.DBKeyController;
import com.amarsoft.app.ems.system.cs.dto.dbkeybatch.DbKeyBatchReq;
import com.amarsoft.app.ems.system.cs.dto.dbkeybatch.DbKeyBatchRsp;
import com.amarsoft.app.ems.system.cs.dto.dbkeysingle.DbKeySingleReq;
import com.amarsoft.app.ems.system.cs.dto.dbkeysingle.DbKeySingleRsp;
import com.amarsoft.app.ems.system.service.DBKeyService;

import lombok.extern.slf4j.Slf4j;


/**
 * 获取系统流水号
 * @author xjzhao
 */
@Slf4j
@RestController
public class DBKeyControllerImpl implements DBKeyController {
    @Autowired
    DBKeyService dBKeyService;
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<DbKeyBatchRsp>> dbKeyBatch(@RequestBody @Valid RequestMessage<DbKeyBatchReq> reqMsg){
        try {
            DbKeyBatchReq request = reqMsg.getMessage();
            DbKeyBatchRsp response=dBKeyService.getBatchSerialNos(request);
            if(log.isDebugEnabled()) {
                log.debug("【" + request.getTable() + "】【" + request.getColumn() + "】【" + request.getDateFormat() + "】【" + request.getNoFormat() 
                        + "】获取批次流水号："
                        + response.getFromSerialNo() + "~"
                        + response.getToSerialNo());
            }
            return new ResponseEntity<ResponseMessage<DbKeyBatchRsp>>(new ResponseMessage<DbKeyBatchRsp >(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<DbKeyBatchRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900001", e.getMessage());
            return new ResponseEntity<ResponseMessage<DbKeyBatchRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<DbKeySingleRsp>> dbKeySingle(@RequestBody @Valid RequestMessage<DbKeySingleReq> reqMsg){
        try {
            DbKeySingleReq request =  reqMsg.getMessage();
            DbKeySingleRsp response=dBKeyService.getSingleSerialNo(request);
            if(log.isDebugEnabled()) {
                log.debug("【" + request.getTable() + "】【" + request.getColumn() + "】【"+request.getDateFormat() + "】【" + request.getNoFormat() + "】获取流水号："+response.getSerialNo());
            }
            return new ResponseEntity<ResponseMessage<DbKeySingleRsp>>(new ResponseMessage<DbKeySingleRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<DbKeySingleRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900001", e.getMessage());
            return new ResponseEntity<ResponseMessage<DbKeySingleRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}