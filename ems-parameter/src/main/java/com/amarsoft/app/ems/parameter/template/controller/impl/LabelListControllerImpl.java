/*
 * 文件名：LabelListControllerImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：LabelListController的实现类
 * 修改人：yrong
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
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
import com.amarsoft.app.ems.parameter.template.controller.LabelListController;
import com.amarsoft.app.ems.parameter.template.service.LabelListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.parameter.template.service.impl.LabelListServiceImpl;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListDeleteReq;

/**
 * 标签ListController实现类
 * @author yrong
 */
@Slf4j
@RestController
public class LabelListControllerImpl implements LabelListController {
    @Autowired
    LabelListService labelListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="标签List", query = LabelListServiceImpl.LabelListReqQuery.class, convert=LabelListServiceImpl.LabelListRspConvert.class)
    //标签List查询
    public ResponseEntity<ResponseMessage<LabelListQueryRsp>> labelListQuery(@RequestBody @Valid RequestMessage<LabelListQueryReq> reqMsg){
        ResponseMessage<LabelListQueryRsp> rspMsg = null;
        try {
            LabelListQueryReq request = reqMsg.getMessage();
            
            LabelListQueryRsp response = labelListServiceImpl.labelListQuery(request);
            rspMsg = new ResponseMessage<LabelListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<LabelListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<LabelListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //标签List保存
    public ResponseEntity<ResponseMessage<Object>> labelListSave(@RequestBody @Valid RequestMessage<LabelListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LabelListSaveReq request = reqMsg.getMessage();
            
            labelListServiceImpl.labelListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签List保存："+ reqMsg.toString(), e);
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
    //标签List删除
    public ResponseEntity<ResponseMessage<Object>> labelListDelete(@RequestBody @Valid RequestMessage<LabelListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LabelListDeleteReq request = reqMsg.getMessage();
            
            labelListServiceImpl.labelListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
