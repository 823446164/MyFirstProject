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
import com.amarsoft.app.ems.parameter.template.controller.LabelCatalogInfoController;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogInfoService;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoSaveReq;

/**
 * 标签目录详情Controller实现类
 * @author ylgao
 */
@Slf4j
@RestController
public class LabelCatalogInfoControllerImpl implements LabelCatalogInfoController {
    @Autowired
    LabelCatalogInfoService labelCatalogInfoServiceImpl;
    
    @Override
    @Transactional
    //标签目录详情查询
    public ResponseEntity<ResponseMessage<LabelCatalogInfoQueryRsp>> labelCatalogInfoQuery(@RequestBody @Valid RequestMessage<LabelCatalogInfoQueryReq> reqMsg){
        ResponseMessage<LabelCatalogInfoQueryRsp> rspMsg = null;
        try {
            LabelCatalogInfoQueryReq request = reqMsg.getMessage();
            
            LabelCatalogInfoQueryRsp response = labelCatalogInfoServiceImpl.labelCatalogInfoQuery(request);
            rspMsg = new ResponseMessage<LabelCatalogInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<LabelCatalogInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签目录详情查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<LabelCatalogInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //标签目录详情保存
    public ResponseEntity<ResponseMessage<Object>> labelCatalogInfoSave(@RequestBody @Valid RequestMessage<LabelCatalogInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LabelCatalogInfoSaveReq request = reqMsg.getMessage();
            
            labelCatalogInfoServiceImpl.labelCatalogInfoSave(request);
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
