package com.amarsoft.app.ems.system.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.aecd.common.constant.Currency;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.controller.RateController;
import com.amarsoft.app.ems.system.cs.dto.efficientdatequery.EfficientDateQueryReq;
import com.amarsoft.app.ems.system.cs.dto.efficientdatequery.EfficientDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.getbaserate.GetBaseRateReq;
import com.amarsoft.app.ems.system.cs.dto.getbaserate.GetBaseRateRsp;
import com.amarsoft.app.ems.system.cs.dto.rateinfoadd.RateInfoAddReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoallquery.RateInfoAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoallquery.RateInfoAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.rateinfodelete.RateInfoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoefficientquery.RateInfoEfficientQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoefficientquery.RateInfoEfficientQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.rateinfoquery.RateInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoquery.RateInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.rateinfoupdate.RateInfoUpdateReq;
import com.amarsoft.app.ems.system.service.RateService;
import com.amarsoft.app.ems.system.util.RateHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 利率服务的处理类
 * @author 核算产品团队
 */
@Slf4j
@RestController
public class RateControllerImpl implements RateController {
    
    @Autowired
    RateService rateService;
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<RateInfoQueryRsp>> rateInfoQuery(@RequestBody @Valid RequestMessage<RateInfoQueryReq> reqMsg){
        try {
            RateInfoQueryReq request = reqMsg.getMessage();
            RateInfoQueryRsp response = rateService.getRateInfo(request);
            return new ResponseEntity<ResponseMessage<RateInfoQueryRsp>>(new ResponseMessage<RateInfoQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<RateInfoQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900401", e.getMessage());
            return new ResponseEntity<ResponseMessage<RateInfoQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<RateInfoEfficientQueryRsp>> rateInfoEfficientQuery(@RequestBody @Valid RequestMessage<RateInfoEfficientQueryReq> reqMsg){
        try {
            RateInfoEfficientQueryRsp responseList = rateService.getRateInfoEfficientDate(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<RateInfoEfficientQueryRsp>>(new ResponseMessage<RateInfoEfficientQueryRsp>(responseList), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<RateInfoEfficientQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900401", e.getMessage());
            return new ResponseEntity<ResponseMessage<RateInfoEfficientQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<RateInfoAllQueryRsp>> rateInfoAllQuery(@RequestBody @Valid RequestMessage<RateInfoAllQueryReq> reqMsg){
        try {
            RateInfoAllQueryReq request = reqMsg.getMessage();
            RateInfoAllQueryRsp responseList = rateService.getRateAll(request);
            return new ResponseEntity<ResponseMessage<RateInfoAllQueryRsp>>(new ResponseMessage<RateInfoAllQueryRsp>(responseList), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<RateInfoAllQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900401", e.getMessage());
            return new ResponseEntity<ResponseMessage<RateInfoAllQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> rateInfoUpdate(@RequestBody @Valid RequestMessage<RateInfoUpdateReq> reqMsg){
        try {
            RateInfoUpdateReq request = reqMsg.getMessage();
            rateService.setRateInfo(request);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900402", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> rateInfoAdd(@RequestBody @Valid RequestMessage<RateInfoAddReq> reqMsg){
        try {
            rateService.addRateInfo(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900403", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> rateInfoDelete(@RequestBody @Valid RequestMessage<RateInfoDeleteReq> reqMsg){
        try {
            RateInfoDeleteReq request = reqMsg.getMessage();
            rateService.deleteRateInfo(request);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900405", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<EfficientDateQueryRsp>> efficientDateQuery(@RequestBody @Valid RequestMessage<EfficientDateQueryReq> reqMsg){
        ResponseMessage<EfficientDateQueryRsp> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<EfficientDateQueryRsp>();
            EfficientDateQueryRsp rsp = rateService.efficientDateQeury(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<EfficientDateQueryRsp>>(new ResponseMessage<EfficientDateQueryRsp>(rsp) , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询生效日请求报文："+ reqMsg.toString(), e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900408", e.getMessage());
            return new ResponseEntity<ResponseMessage<EfficientDateQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<GetBaseRateRsp>> getBaseRate(@RequestBody @Valid RequestMessage<GetBaseRateReq> reqMsg){
        ResponseMessage<GetBaseRateRsp> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<GetBaseRateRsp>();
            GetBaseRateReq getBaseRateReq = reqMsg.getMessage();
            String effectDate = getBaseRateReq.getEffectDate();
            if(StringUtils.isEmpty(effectDate)) effectDate = DateHelper.getBusinessDate();
            int yearDays = 0;
            if(getBaseRateReq.getYearDays()!=null) {
                yearDays = getBaseRateReq.getYearDays();
            }else {
                yearDays = Currency.getYearBaseDay(getBaseRateReq.getCurrency());
            }
            Double baseRate = RateHelper.getBaseRate(getBaseRateReq.getCurrency(), yearDays, getBaseRateReq.getBaseRateType(), getBaseRateReq.getRateUnit(), getBaseRateReq.getBeginDate(), getBaseRateReq.getEndDate(), effectDate);
            GetBaseRateRsp getBaseRateRsp = new GetBaseRateRsp();
            getBaseRateRsp.setBaseRate(baseRate);
            rspMsg.setMessage(getBaseRateRsp);
            return new ResponseEntity<ResponseMessage<GetBaseRateRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("获取基准利率请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "999998");
            return new ResponseEntity<ResponseMessage<GetBaseRateRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}