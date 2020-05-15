/*
 * 文件名：RankStandardCatalogInfoControllerImpl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.controller.impl;


import java.util.Map;

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
import com.amarsoft.app.ems.parameter.template.controller.RankStandardCatalogInfoController;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoQueryRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.service.RankStandardCatalogInfoService;

import lombok.extern.slf4j.Slf4j;


/**
 * 〈职级标准详情Controller实现类〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
@Slf4j
@RestController
public class RankStandardCatalogInfoControllerImpl implements RankStandardCatalogInfoController {
    /**
     * 引入service实现层
     */
    @Autowired
    RankStandardCatalogInfoService rankStandardCatalogInfoServiceImpl;


    /**
     * 
     * Description: 职级标准详情保存
     *包括职等与子职级的新增/更新保存
     * @param reqMsg
     * @return
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> rankStandardCatalogChildInfoSave(@RequestBody @Valid RequestMessage<RankStandardCatalogChildInfoSaveReq> reqMsg) {
        ResponseMessage<Object> rspMsg = null;
        try {
            RankStandardCatalogChildInfoSaveReq request = reqMsg.getMessage();

            Map<String,String> response= rankStandardCatalogInfoServiceImpl.rankStandardCatalogChildInfoSave(request);
            rspMsg = new ResponseMessage<Object>(response);

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.OK);
        }
        catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("职级标准详情保存：" + reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "EMS2005", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 
     * Description: 职级标准详情删除
     *
     * @param reqMsg
     * @return
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> rankStandardCatalogInfoDelete(@RequestBody @Valid RequestMessage<RankStandardCatalogChildInfoQueryReq> reqMsg) {
        ResponseMessage<Object> rspMsg = null;
        try {
            RankStandardCatalogChildInfoQueryReq request = reqMsg.getMessage();

            rankStandardCatalogInfoServiceImpl.rankStandardCatalogInfoDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.OK);
        }
        catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("职级标准详情删除：" + reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "EMS2006", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    /**
     * 
     * Description: 职等/子职级界面标准详情查询
     *
     * @param reqMsg
     * @return
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<RankStandardCatalogChildInfoQueryRsq>> rankStandardCatalogChildInfoQuery(@RequestBody @Valid RequestMessage<RankStandardCatalogChildInfoQueryReq> reqMsg) {
        ResponseMessage<RankStandardCatalogChildInfoQueryRsq> rspMsg = null;
        try {
            RankStandardCatalogChildInfoQueryReq request = reqMsg.getMessage();

            RankStandardCatalogChildInfoQueryRsq response = rankStandardCatalogInfoServiceImpl.rankStandardCatalogChildInfoQuery(request);
            rspMsg = new ResponseMessage<RankStandardCatalogChildInfoQueryRsq>(response);

            return new ResponseEntity<ResponseMessage<RankStandardCatalogChildInfoQueryRsq>>(rspMsg, HttpStatus.OK);
        }
        catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("职级界面标准详情查询：" + reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "EMS2011", e.getMessage());
            return new ResponseEntity<ResponseMessage<RankStandardCatalogChildInfoQueryRsq>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
