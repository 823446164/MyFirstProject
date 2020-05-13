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
import com.amarsoft.app.ems.parameter.template.controller.LabelDescribeInfoController;
import com.amarsoft.app.ems.parameter.template.service.LabelDescribeInfoService;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfoSaveReq;

/**
 * 标签能力描述InfoController实现类
 * @author yrong
 */
@Slf4j
@RestController
public class LabelDescribeInfoControllerImpl implements LabelDescribeInfoController {
    @Autowired
    LabelDescribeInfoService labelDescribeInfoServiceImpl;
    
    @Override
    @Transactional
    /**
     * 标签目录树图查询
     * @author 标签能力描述Info查询
     */
    public ResponseEntity<ResponseMessage<LabelDescribeInfoQueryRsp>> labelDescribeInfoQuery(@RequestBody @Valid RequestMessage<LabelDescribeInfoQueryReq> reqMsg){
        ResponseMessage<LabelDescribeInfoQueryRsp> rspMsg = null;
        try {
            LabelDescribeInfoQueryReq request = reqMsg.getMessage();
            
            LabelDescribeInfoQueryRsp response = labelDescribeInfoServiceImpl.labelDescribeInfoQuery(request);
            rspMsg = new ResponseMessage<LabelDescribeInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<LabelDescribeInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签能力描述Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<LabelDescribeInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    /**
     * 标签能力描述Info保存
     * @author yrong
     */
    public ResponseEntity<ResponseMessage<Object>> labelDescribeInfoSave(@RequestBody @Valid RequestMessage<LabelDescribeInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LabelDescribeInfoSaveReq request = reqMsg.getMessage();
            
            labelDescribeInfoServiceImpl.labelDescribeInfoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签能力描述Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
