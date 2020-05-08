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
import com.amarsoft.app.ems.parameter.template.controller.RankStandardCatalogInfoController;
import com.amarsoft.app.ems.parameter.template.service.RankStandardCatalogInfoService;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoSaveRsq;

/**
 * 职级标准详情Controller实现类
 * @author ylgao
 */
@Slf4j
@RestController
public class RankStandardCatalogInfoControllerImpl implements RankStandardCatalogInfoController {
    @Autowired
    RankStandardCatalogInfoService rankStandardCatalogInfoServiceImpl;
    
    @Override
    @Transactional
    //职级标准详情查询
    public ResponseEntity<ResponseMessage<RankStandardCatalogInfoQueryRsp>> rankStandardCatalogInfoQuery(@RequestBody @Valid RequestMessage<RankStandardCatalogInfoQueryReq> reqMsg){
        ResponseMessage<RankStandardCatalogInfoQueryRsp> rspMsg = null;
        try {
            RankStandardCatalogInfoQueryReq request = reqMsg.getMessage();
            
            RankStandardCatalogInfoQueryRsp response = rankStandardCatalogInfoServiceImpl.rankStandardCatalogInfoQuery(request);
            System.out.println(response);
            rspMsg = new ResponseMessage<RankStandardCatalogInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<RankStandardCatalogInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("职级标准详情查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<RankStandardCatalogInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //职级标准详情保存
    public ResponseEntity<ResponseMessage<RankStandardCatalogInfoSaveRsq>> rankStandardCatalogInfoSave(@RequestBody @Valid RequestMessage<RankStandardCatalogInfoSaveReq> reqMsg){
        ResponseMessage<RankStandardCatalogInfoSaveRsq> rspMsg = null;
        try {
            RankStandardCatalogInfoSaveReq request = reqMsg.getMessage();
            
            RankStandardCatalogInfoSaveRsq response=  rankStandardCatalogInfoServiceImpl.rankStandardCatalogInfoSave(request);
            rspMsg = new ResponseMessage<RankStandardCatalogInfoSaveRsq>(response);

            return new ResponseEntity<ResponseMessage<RankStandardCatalogInfoSaveRsq>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("职级标准详情保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<RankStandardCatalogInfoSaveRsq>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> rankStandardCatalogInfoDelete(@RequestBody @Valid RequestMessage<RankStandardCatalogInfoQueryReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            RankStandardCatalogInfoQueryReq request = reqMsg.getMessage();
            
            rankStandardCatalogInfoServiceImpl.rankStandardCatalogInfoDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工公司所属详情删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
