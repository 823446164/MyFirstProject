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
import com.amarsoft.app.ems.parameter.template.controller.LabelCatalogListController;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.parameter.template.service.impl.LabelCatalogListServiceImpl;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListDeleteReq;

/**
 * 标签目录树图Controller实现类
 * @author ylgao
 */
@Slf4j
@RestController
public class LabelCatalogListControllerImpl implements LabelCatalogListController {
    @Autowired
    LabelCatalogListService labelCatalogListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="标签目录树图", query = LabelCatalogListServiceImpl.LabelCatalogListReqQuery.class, convert=LabelCatalogListServiceImpl.LabelCatalogListRspConvert.class)
    //标签目录树图查询
    public ResponseEntity<ResponseMessage<LabelCatalogListQueryRsp>> labelCatalogListQuery(@RequestBody @Valid RequestMessage<LabelCatalogListQueryReq> reqMsg){
        ResponseMessage<LabelCatalogListQueryRsp> rspMsg = null;
        try {
            LabelCatalogListQueryReq request = reqMsg.getMessage();
            
            LabelCatalogListQueryRsp response = labelCatalogListServiceImpl.labelCatalogListQuery(request);
            rspMsg = new ResponseMessage<LabelCatalogListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<LabelCatalogListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签目录树图查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<LabelCatalogListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //标签目录树图保存
    public ResponseEntity<ResponseMessage<Object>> labelCatalogListSave(@RequestBody @Valid RequestMessage<LabelCatalogListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LabelCatalogListSaveReq request = reqMsg.getMessage();
            
            labelCatalogListServiceImpl.labelCatalogListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签目录树图保存："+ reqMsg.toString(), e);
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
    //标签目录树图删除
    public ResponseEntity<ResponseMessage<Object>> labelCatalogListDelete(@RequestBody @Valid RequestMessage<LabelCatalogListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LabelCatalogListDeleteReq request = reqMsg.getMessage();
            
            labelCatalogListServiceImpl.labelCatalogListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签目录树图删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
