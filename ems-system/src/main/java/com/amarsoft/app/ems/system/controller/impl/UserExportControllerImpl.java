/**
 * 新增用户导出记录
 * 根据接口定义的excel文档自动生成实体，由AutoCreateInterfBeans.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.UserExportController;
import com.amarsoft.app.ems.system.cs.dto.addexport.AddExportReq;
import com.amarsoft.app.ems.system.cs.dto.exportquery.ExportQueryReq;
import com.amarsoft.app.ems.system.cs.dto.exportquery.ExportQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.finishexport.FinishExportReq;
import com.amarsoft.app.ems.system.cs.dto.getexportserialno.GetExportSerialNoRsp;
import com.amarsoft.app.ems.system.service.UserExportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserExportControllerImpl implements UserExportController {
    
    @Autowired
    UserExportService userExportService;
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> addExport(@RequestBody @Valid RequestMessage<AddExportReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<Object>();
            userExportService.addExport(reqMsg);
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("新增用户导出记录请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900924",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<GetExportSerialNoRsp>> getExportSerialNo(){
        ResponseMessage<GetExportSerialNoRsp> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<GetExportSerialNoRsp>();
            rspMsg = userExportService.getExportSerialNo();
            return new ResponseEntity<ResponseMessage<GetExportSerialNoRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("获取用户导出流水号请求报文："+ null, e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900925",e.getMessage());
            return new ResponseEntity<ResponseMessage<GetExportSerialNoRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> finishExport(@RequestBody @Valid RequestMessage<FinishExportReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<Object>();
            rspMsg = userExportService.finishExport(reqMsg);
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("更新用户导出记录请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900926",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<ExportQueryRsp>> exportQuery(@RequestBody @Valid RequestMessage<ExportQueryReq> reqMsg){
        ResponseMessage<ExportQueryRsp> rspMsg = null;
        try {
            ExportQueryRsp rsp = userExportService.exportQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<ExportQueryRsp>>(new ResponseMessage<ExportQueryRsp>(rsp) , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("更新用户导出记录请求报文："+ reqMsg.toString(), e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900926",e.getMessage());
            return new ResponseEntity<ResponseMessage<ExportQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping(value = "/userexport/exportbyuri", name="用户下载路径下载", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> exportByUri(HttpServletRequest request, HttpServletResponse response) {
        ResponseMessage<Object> rspMsg = null;
        try {
            userExportService.downloadByUri(request, response);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("获取用户流水号请求报文：" + request, e );
            }
            rspMsg =ResponseMessage.getResponseMessageFromException(e,"900966",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

}