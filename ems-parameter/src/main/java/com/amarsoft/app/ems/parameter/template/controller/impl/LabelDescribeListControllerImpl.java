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
import com.amarsoft.app.ems.parameter.template.controller.LabelDescribeListController;
import com.amarsoft.app.ems.parameter.template.service.LabelDescribeListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.parameter.template.service.impl.LabelDescribeListServiceImpl;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListDeleteReq;

/**
 * 标签能力描述ListController实现类
 * @author yrong
 */
@Slf4j
@RestController
public class LabelDescribeListControllerImpl implements LabelDescribeListController {
    @Autowired
    LabelDescribeListService labelDescribeListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="标签能力描述List", query = LabelDescribeListServiceImpl.LabelDescribeListReqQuery.class, convert=LabelDescribeListServiceImpl.LabelDescribeListRspConvert.class)
    //标签能力描述List查询
    public ResponseEntity<ResponseMessage<LabelDescribeListQueryRsp>> labelDescribeListQuery(@RequestBody @Valid RequestMessage<LabelDescribeListQueryReq> reqMsg){
        ResponseMessage<LabelDescribeListQueryRsp> rspMsg = null;
        try {
            LabelDescribeListQueryReq request = reqMsg.getMessage();
            
            LabelDescribeListQueryRsp response = labelDescribeListServiceImpl.labelDescribeListQuery(request);
            rspMsg = new ResponseMessage<LabelDescribeListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<LabelDescribeListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签能力描述List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<LabelDescribeListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //标签能力描述List保存
    public ResponseEntity<ResponseMessage<Object>> labelDescribeListSave(@RequestBody @Valid RequestMessage<LabelDescribeListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LabelDescribeListSaveReq request = reqMsg.getMessage();
            
            labelDescribeListServiceImpl.labelDescribeListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签能力描述List保存："+ reqMsg.toString(), e);
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
    //标签能力描述List删除
    public ResponseEntity<ResponseMessage<Object>> labelDescribeListDelete(@RequestBody @Valid RequestMessage<LabelDescribeListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LabelDescribeListDeleteReq request = reqMsg.getMessage();
            
            labelDescribeListServiceImpl.labelDescribeListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签能力描述List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
