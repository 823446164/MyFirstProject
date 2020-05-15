/*
 * 文件名：LableCatalogInfoControllerImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：LableCatalogInfoController的实现类
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：增加选中目录查询标签方法
 */
package com.amarsoft.app.ems.parameter.template.controller.impl;

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
import com.amarsoft.app.ems.parameter.template.controller.LabelCatalogInfoController;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelByLabelCatalogQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelByLabelCatalogQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogInfoService;
import lombok.extern.slf4j.Slf4j;

/**
 * 标签目录详情Controller实现类
 * @author yrong
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
    
    
    
    
    @Override
    @Transactional
    /**
     * Description: 选中目录查询标签
     */
    public ResponseEntity<ResponseMessage<LabelByLabelCatalogQueryRsp>> labelBelongCatalogQuery(@RequestBody @Valid RequestMessage<LabelByLabelCatalogQueryReq> reqMsg) {
        ResponseMessage<LabelByLabelCatalogQueryRsp> rspMsg = null;
        try {
            LabelByLabelCatalogQueryReq request = reqMsg.getMessage();
            LabelByLabelCatalogQueryRsp response = labelCatalogInfoServiceImpl.labelBelongCatalogQuery(request);
            rspMsg = new ResponseMessage<LabelByLabelCatalogQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<LabelByLabelCatalogQueryRsp>>(rspMsg, HttpStatus.OK);
        }
        catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("标签目录详情查询：" + reqMsg.toString(), e);
            }
            /**
             * Description: 事务回滚
             */
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "", e.getMessage());
            return new ResponseEntity<ResponseMessage<LabelByLabelCatalogQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}