package com.amarsoft.app.ems.system.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.SystemController;
import com.amarsoft.app.ems.system.cs.dto.alldatequery.AllDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.batchdatequery.BatchDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.batchflagquery.BatchFlagQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.businessdatequery.BusinessDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.productdatequery.ProductDateQueryRsp;
import com.amarsoft.app.ems.system.service.SystemService;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统日期服务的处理类
 * @author hzhang23
 */
@Slf4j
@RestController
public class SystemControllerImpl implements SystemController {
    
    @Autowired
    SystemService systemService;
    
    @Override
    public ResponseEntity<ResponseMessage<BusinessDateQueryRsp>> businessDateQuery() {
        try {
            BusinessDateQueryRsp response = systemService.getBusinessDate();
            return new ResponseEntity<ResponseMessage<BusinessDateQueryRsp>>(new ResponseMessage<BusinessDateQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            ResponseMessage<BusinessDateQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900101", e.getMessage());
            if(log.isErrorEnabled()) {
                log.error(hrb.getResponseMessage(), e);
            }
            return new ResponseEntity<ResponseMessage<BusinessDateQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<BatchFlagQueryRsp>> batchFlagQuery() {
        try {
            BatchFlagQueryRsp response = systemService.getBatchFlag();
            return new ResponseEntity<ResponseMessage<BatchFlagQueryRsp>>(new ResponseMessage<BatchFlagQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            ResponseMessage<BatchFlagQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900102", e.getMessage());
            if(log.isErrorEnabled()) {
                log.error(hrb.getResponseMessage(), e);
            }
            return new ResponseEntity<ResponseMessage<BatchFlagQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<ResponseMessage<BatchDateQueryRsp>> batchDateQuery() {
        try {
            BatchDateQueryRsp response = systemService.getBatchDate();
            return new ResponseEntity<ResponseMessage<BatchDateQueryRsp>>(new ResponseMessage<BatchDateQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            ResponseMessage<BatchDateQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900103",e.getMessage());
            if(log.isErrorEnabled()) {
                log.error(hrb.getResponseMessage(), e);
            }
            return new ResponseEntity<ResponseMessage<BatchDateQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<ResponseMessage<ProductDateQueryRsp>> productDateQuery() {
        try {
            ProductDateQueryRsp response = systemService.getProductDate();
            return new ResponseEntity<ResponseMessage<ProductDateQueryRsp>>(new ResponseMessage<ProductDateQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            ResponseMessage<ProductDateQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900104", e.getMessage());
            if(log.isErrorEnabled()) {
                log.error(hrb.getResponseMessage(), e);
            }
            return new ResponseEntity<ResponseMessage<ProductDateQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<AllDateQueryRsp>> allDateQuery(){
        try {
            AllDateQueryRsp rsp = systemService.allDateQuery();
            return new ResponseEntity<ResponseMessage<AllDateQueryRsp>>(new ResponseMessage<AllDateQueryRsp>(rsp) , HttpStatus.OK);
        } catch (Exception e) {
            ResponseMessage<AllDateQueryRsp> rspMsg = ResponseMessage.getResponseMessageFromException(e, "900101",e.getMessage());
            return new ResponseEntity<ResponseMessage<AllDateQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}