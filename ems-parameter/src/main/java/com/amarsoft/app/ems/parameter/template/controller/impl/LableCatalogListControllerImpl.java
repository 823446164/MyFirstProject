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
import com.amarsoft.app.ems.parameter.template.controller.LableCatalogListController;
import com.amarsoft.app.ems.parameter.template.service.LableCatalogListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.parameter.template.service.impl.LableCatalogListServiceImpl;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListDeleteReq;

/**
 * 标签目录树图Controller实现类
 * @author ylgao
 */
@Slf4j
@RestController
public class LableCatalogListControllerImpl implements LableCatalogListController {
    @Autowired
    LableCatalogListService lableCatalogListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="标签目录树图", query = LableCatalogListServiceImpl.LableCatalogListReqQuery.class, convert=LableCatalogListServiceImpl.LableCatalogListRspConvert.class)
    //标签目录树图查询
    public ResponseEntity<ResponseMessage<LableCatalogListQueryRsp>> lableCatalogListQuery(@RequestBody @Valid RequestMessage<LableCatalogListQueryReq> reqMsg){
        ResponseMessage<LableCatalogListQueryRsp> rspMsg = null;
        try {
            LableCatalogListQueryReq request = reqMsg.getMessage();
            
            LableCatalogListQueryRsp response = lableCatalogListServiceImpl.lableCatalogListQuery(request);
            rspMsg = new ResponseMessage<LableCatalogListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<LableCatalogListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签目录树图查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<LableCatalogListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //标签目录树图保存
    public ResponseEntity<ResponseMessage<Object>> lableCatalogListSave(@RequestBody @Valid RequestMessage<LableCatalogListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LableCatalogListSaveReq request = reqMsg.getMessage();
            
            lableCatalogListServiceImpl.lableCatalogListSave(request);
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
    public ResponseEntity<ResponseMessage<Object>> lableCatalogListDelete(@RequestBody @Valid RequestMessage<LableCatalogListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LableCatalogListDeleteReq request = reqMsg.getMessage();
            
            lableCatalogListServiceImpl.lableCatalogListDelete(request);
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
