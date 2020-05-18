/*
 * 文件名：LabelInfoControllerImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：LabelInfoController的实现类
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
import com.amarsoft.app.ems.parameter.template.controller.LabelInfoController;
import com.amarsoft.app.ems.parameter.template.service.LabelInfoService;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoCopyReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoSaveReq;

/**
 * 标签InfoController实现类
 * @author yrong
 */
@Slf4j
@RestController
public class LabelInfoControllerImpl implements LabelInfoController {
    @Autowired
    LabelInfoService labelInfoServiceImpl;
    
    @Override
    @Transactional
    /**
     * Description: 标签Info查询
     */
    public ResponseEntity<ResponseMessage<LabelInfoQueryRsp>> labelInfoQuery(@RequestBody @Valid RequestMessage<LabelInfoQueryReq> reqMsg){
        ResponseMessage<LabelInfoQueryRsp> rspMsg = null;
        try {
            LabelInfoQueryReq request = reqMsg.getMessage();
            
            LabelInfoQueryRsp response = labelInfoServiceImpl.labelInfoQuery(request);
            rspMsg = new ResponseMessage<LabelInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<LabelInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<LabelInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    /**
     * Description: 标签Info保存
     */
    public ResponseEntity<ResponseMessage<Object>> labelInfoSave(@RequestBody @Valid RequestMessage<LabelInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LabelInfoSaveReq request = reqMsg.getMessage();
            
            labelInfoServiceImpl.labelInfoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签Info保存："+ reqMsg.toString(), e);
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
     * Description: 标签生效
     */
    public ResponseEntity<ResponseMessage<Object>> lableStatusOk(@RequestBody @Valid RequestMessage<LabelInfoSaveReq> reqMsg) {
        ResponseMessage<Object> rspMsg = null;
        try {
            LabelInfoSaveReq request = reqMsg.getMessage();

            labelInfoServiceImpl.lableStatusOk(request);

            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.OK);
        }
        catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("标签生效：" + reqMsg.toString(), e);
            }
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}