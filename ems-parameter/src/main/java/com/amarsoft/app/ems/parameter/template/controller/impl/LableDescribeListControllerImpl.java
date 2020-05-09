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
import com.amarsoft.app.ems.parameter.template.controller.LableDescribeListController;
import com.amarsoft.app.ems.parameter.template.service.LableDescribeListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.parameter.template.service.impl.LableDescribeListServiceImpl;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListDeleteReq;

/**
 * 标签树图Controller实现类
 * @author ylgao
 */
@Slf4j
@RestController
public class LableDescribeListControllerImpl implements LableDescribeListController {
    @Autowired
    LableDescribeListService lableDescribeListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="标签树图", query = LableDescribeListServiceImpl.LableDescribeListReqQuery.class, convert=LableDescribeListServiceImpl.LableDescribeListRspConvert.class)
    //标签树图查询
    public ResponseEntity<ResponseMessage<LableDescribeListQueryRsp>> lableDescribeListQuery(@RequestBody @Valid RequestMessage<LableDescribeListQueryReq> reqMsg){
        ResponseMessage<LableDescribeListQueryRsp> rspMsg = null;
        try {
            LableDescribeListQueryReq request = reqMsg.getMessage();
            
            LableDescribeListQueryRsp response = lableDescribeListServiceImpl.lableDescribeListQuery(request);
            rspMsg = new ResponseMessage<LableDescribeListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<LableDescribeListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签树图查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<LableDescribeListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //标签树图保存
    public ResponseEntity<ResponseMessage<Object>> lableDescribeListSave(@RequestBody @Valid RequestMessage<LableDescribeListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LableDescribeListSaveReq request = reqMsg.getMessage();
            
            lableDescribeListServiceImpl.lableDescribeListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签树图保存："+ reqMsg.toString(), e);
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
    //标签树图删除
    public ResponseEntity<ResponseMessage<Object>> lableDescribeListDelete(@RequestBody @Valid RequestMessage<LableDescribeListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            LableDescribeListDeleteReq request = reqMsg.getMessage();
            
            lableDescribeListServiceImpl.lableDescribeListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("标签树图删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
