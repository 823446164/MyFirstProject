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
import com.amarsoft.app.ems.parameter.template.controller.LableDescribeInfoController;
import com.amarsoft.app.ems.parameter.template.service.LableDescribeInfoService;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoSaveReq;

/**
 * 标签树图Controller实现类
 * @author ylgao
 */
@Slf4j
@RestController
public class LableDescribeInfoControllerImpl implements LableDescribeInfoController {
    @Autowired
    LableDescribeInfoService lableDescribeInfoServiceImpl;
    
    @Override
    @Transactional
    //标签树图查询
    public ResponseEntity<ResponseMessage<LableDescribeInfoQueryRsp>> lableDescribeInfoQuery(@RequestBody @Valid RequestMessage<LableDescribeInfoQueryReq> reqMsg){
        ResponseMessage<LableDescribeInfoQueryRsp> rspMsg = null;
        try {
            LableDescribeInfoQueryReq request = reqMsg.getMessage();
            
            LableDescribeInfoQueryRsp response = lableDescribeInfoServiceImpl.lableDescribeInfoQuery(request);
            rspMsg = new ResponseMessage<LableDescribeInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<LableDescribeInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签树图查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<LableDescribeInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //标签树图保存
    public ResponseEntity<ResponseMessage<Object>> lableDescribeInfoSave(@RequestBody @Valid RequestMessage<LableDescribeInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LableDescribeInfoSaveReq request = reqMsg.getMessage();
            
            lableDescribeInfoServiceImpl.lableDescribeInfoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签树图保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @Override
    @Transactional
    //标签生效
    public ResponseEntity<ResponseMessage<Object>> lableStatusOk(@RequestBody @Valid RequestMessage<LableDescribeInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LableDescribeInfoSaveReq request = reqMsg.getMessage(); 
            
            lableDescribeInfoServiceImpl.lableStatusOk(request);
           
            
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签生效："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    @Override
    @Transactional
    //标签失效
    public ResponseEntity<ResponseMessage<Object>> lableStatusNo(@RequestBody @Valid RequestMessage<LableDescribeInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LableDescribeInfoSaveReq request = reqMsg.getMessage();
            
            lableDescribeInfoServiceImpl.lableStatusNo(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签失效："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
