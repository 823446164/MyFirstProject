/*
 * 文件名：LabelCatalogTreeControllerImpl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：yrong
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.controller.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.parameter.template.controller.LabelCatalogTreeController;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcatalogtreequery.LabelCatalogTreeQueryRsp;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogTreeService;

import lombok.extern.slf4j.Slf4j;

/**
 * 标签目录树图查询
 * @author yrong
 * @version 2020年5月13日
 * @see LabelCatalogTreeControllerImpl
 * @since
 */
@Slf4j
@RestController
public class LabelCatalogTreeControllerImpl implements LabelCatalogTreeController{
    @Autowired
    LabelCatalogTreeService labelCatalogTreeService;

    @Override
    public ResponseEntity<ResponseMessage<LabelCatalogTreeQueryRsp>> labelCatalogTreeQuery() {
        ResponseMessage<LabelCatalogTreeQueryRsp> rspMsg = null;
        try {
            LabelCatalogTreeQueryRsp rsp = labelCatalogTreeService.labelCatalogTreeQuery();
            return new ResponseEntity<ResponseMessage<LabelCatalogTreeQueryRsp>>(new ResponseMessage<LabelCatalogTreeQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签目录树图查询：", e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900211",e.getMessage());
            return new ResponseEntity<ResponseMessage<LabelCatalogTreeQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


}
