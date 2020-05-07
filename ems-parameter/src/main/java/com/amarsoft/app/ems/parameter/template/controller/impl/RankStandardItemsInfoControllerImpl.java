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
import com.amarsoft.app.ems.parameter.template.controller.RankStandardItemsInfoController;
import com.amarsoft.app.ems.parameter.template.service.RankStandardItemsInfoService;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfoSaveReq;

/**
 * 技能详情Controller实现类
 * @author ylgao
 */
@Slf4j
@RestController
public class RankStandardItemsInfoControllerImpl implements RankStandardItemsInfoController {
    @Autowired
    RankStandardItemsInfoService rankStandardItemsInfoServiceImpl;
    
    @Override
    @Transactional
    //技能详情查询
    public ResponseEntity<ResponseMessage<RankStandardItemsInfoQueryRsp>> rankStandardItemsInfoQuery(@RequestBody @Valid RequestMessage<RankStandardItemsInfoQueryReq> reqMsg){
        ResponseMessage<RankStandardItemsInfoQueryRsp> rspMsg = null;
        try {
            RankStandardItemsInfoQueryReq request = reqMsg.getMessage();
            
            RankStandardItemsInfoQueryRsp response = rankStandardItemsInfoServiceImpl.rankStandardItemsInfoQuery(request);
            rspMsg = new ResponseMessage<RankStandardItemsInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<RankStandardItemsInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("技能详情查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<RankStandardItemsInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //技能详情保存
    public ResponseEntity<ResponseMessage<Object>> rankStandardItemsInfoSave(@RequestBody @Valid RequestMessage<RankStandardItemsInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            RankStandardItemsInfoSaveReq request = reqMsg.getMessage();
            
            rankStandardItemsInfoServiceImpl.rankStandardItemsInfoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("技能详情保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
