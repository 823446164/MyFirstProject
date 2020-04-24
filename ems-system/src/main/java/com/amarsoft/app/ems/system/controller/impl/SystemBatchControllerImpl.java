/**
 * 核算批量标识修改
 * 根据接口定义的excel文档自动生成实体，由AutoCreateInterfBeans.class的test方法批量生成。
 */
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
import com.amarsoft.app.ems.system.controller.SystemBatchController;
import com.amarsoft.app.ems.system.cs.dto.batchdateupdate.BatchDateUpdateReq;
import com.amarsoft.app.ems.system.cs.dto.batchflagupdate.BatchFlagUpdateReq;
import com.amarsoft.app.ems.system.cs.dto.businessdateupdate.BusinessDateUpdateReq;
import com.amarsoft.app.ems.system.service.SystemBatchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SystemBatchControllerImpl implements SystemBatchController {
    @Autowired
    SystemBatchService systemBatchService;

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> batchFlagUpdate(@RequestBody @Valid RequestMessage<BatchFlagUpdateReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<Object>();
            BatchFlagUpdateReq batchFlagUpdateReq = reqMsg.getMessage();
            systemBatchService.batchFlagUpdate(batchFlagUpdateReq.getBatchFlag(), batchFlagUpdateReq.getBatchDate());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("修改批量标识请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900105",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> batchDateUpdate(@RequestBody @Valid RequestMessage<BatchDateUpdateReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<Object>();
            BatchDateUpdateReq batchFlagUpdateReq = reqMsg.getMessage();
            systemBatchService.batchDateUpdate(batchFlagUpdateReq.getBatchDate());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("修改批量日期请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900106",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> businessDateUpdate(@RequestBody @Valid RequestMessage<BusinessDateUpdateReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<Object>();
            BusinessDateUpdateReq businessDateUpdateReq = reqMsg.getMessage();
            systemBatchService.businessDateUpdate(businessDateUpdateReq.getBusinessDate(),businessDateUpdateReq.getBatchDate());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("修改系统日期请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900107",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}