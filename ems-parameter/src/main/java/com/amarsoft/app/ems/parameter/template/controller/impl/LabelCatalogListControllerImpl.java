/*
 * 文件名：LabelCatalogListControllerImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：LabelCatalogListController的实现类
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：修改注释
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
import com.amarsoft.app.ems.parameter.template.controller.LabelCatalogListController;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListDeleteReq;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogListService;
import lombok.extern.slf4j.Slf4j;

/**
 * 标签目录树图Controller实现类
 * @author yrong
 */
@Slf4j
@RestController
public class LabelCatalogListControllerImpl implements LabelCatalogListController {
    @Autowired
    LabelCatalogListService labelCatalogListServiceImpl;
    
    @Override
    @Transactional

    /**
     * 标签目录树图删除
     * @author yrong
     */
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