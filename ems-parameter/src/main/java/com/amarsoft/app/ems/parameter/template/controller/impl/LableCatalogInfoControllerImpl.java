package com.amarsoft.app.ems.parameter.template.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import com.amarsoft.app.ems.parameter.template.controller.LableCatalogInfoController;
import com.amarsoft.app.ems.parameter.template.service.LableCatalogInfoService;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoSaveReq;

/**
 * 标签目录详情Controller实现类
 * @author ylgao
 */
@Slf4j 
@RestController
public class LableCatalogInfoControllerImpl implements LableCatalogInfoController {
    @Autowired
    LableCatalogInfoService lableCatalogInfoServiceImpl;
    
    @Override
    @Transactional
    //标签目录详情查询
    public ResponseEntity<ResponseMessage<LableCatalogInfoQueryRsp>> lableCatalogInfoQuery(@RequestBody @Valid RequestMessage<LableCatalogInfoQueryReq> reqMsg){
        ResponseMessage<LableCatalogInfoQueryRsp> rspMsg = null;
        try {
            LableCatalogInfoQueryReq request = reqMsg.getMessage();
            
            LableCatalogInfoQueryRsp response = lableCatalogInfoServiceImpl.lableCatalogInfoQuery(request);
            rspMsg = new ResponseMessage<LableCatalogInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<LableCatalogInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签目录详情查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<LableCatalogInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //选中目录查询标签
    public ResponseEntity<ResponseMessage<LableCatalogInfoQueryRsp>> labelBelongCatalogQuery(@RequestBody @Valid RequestMessage<LableCatalogInfoQueryReq> reqMsg){
        ResponseMessage<LableCatalogInfoQueryRsp> rspMsg = null;
        try {
            LableCatalogInfoQueryReq request = reqMsg.getMessage();
            
//            LableCatalogInfoQueryRsp response = lableCatalogInfoServiceImpl.lableCatalogInfoQuery(request);
            LableCatalogInfoQueryRsp response = lableCatalogInfoServiceImpl.labelBelongCatalogQuery(request);
            rspMsg = new ResponseMessage<LableCatalogInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<LableCatalogInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签目录详情查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<LableCatalogInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    @Override
    @Transactional
    //标签目录详情保存
    public ResponseEntity<ResponseMessage<Object>> lableCatalogInfoSave(@RequestBody @Valid RequestMessage<LableCatalogInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LableCatalogInfoSaveReq request = reqMsg.getMessage();
            
            lableCatalogInfoServiceImpl.lableCatalogInfoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签目录详情保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
