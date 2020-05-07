package com.amarsoft.app.ems.train.template.controller.impl;

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
import com.amarsoft.app.ems.train.template.controller.EmployeeTraceDetailListController;
import com.amarsoft.app.ems.train.template.service.EmployeeTraceDetailListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.train.template.service.impl.EmployeeTraceDetailListServiceImpl;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetaillist.EmployeeTraceDetailListDeleteReq;

/**
 * 追踪内容列表Controller实现类
 * @author xphe
 */
@Slf4j
@RestController
public class EmployeeTraceDetailListControllerImpl implements EmployeeTraceDetailListController {
    @Autowired
    EmployeeTraceDetailListService employeeTraceDetailListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="追踪内容列表", query = EmployeeTraceDetailListServiceImpl.EmployeeTraceDetailListReqQuery.class, convert=EmployeeTraceDetailListServiceImpl.EmployeeTraceDetailListRspConvert.class)
    //追踪内容列表查询
    public ResponseEntity<ResponseMessage<EmployeeTraceDetailListQueryRsp>> employeeTraceDetailListQuery(@RequestBody @Valid RequestMessage<EmployeeTraceDetailListQueryReq> reqMsg){
        ResponseMessage<EmployeeTraceDetailListQueryRsp> rspMsg = null;
        try {
            EmployeeTraceDetailListQueryReq request = reqMsg.getMessage();
            
            EmployeeTraceDetailListQueryRsp response = employeeTraceDetailListServiceImpl.employeeTraceDetailListQuery(request);
            rspMsg = new ResponseMessage<EmployeeTraceDetailListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeTraceDetailListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("追踪内容列表查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeTraceDetailListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //追踪内容列表保存
    public ResponseEntity<ResponseMessage<Object>> employeeTraceDetailListSave(@RequestBody @Valid RequestMessage<EmployeeTraceDetailListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeTraceDetailListSaveReq request = reqMsg.getMessage();
            
            employeeTraceDetailListServiceImpl.employeeTraceDetailListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("追踪内容列表保存："+ reqMsg.toString(), e);
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
    //追踪内容列表删除
    public ResponseEntity<ResponseMessage<Object>> employeeTraceDetailListDelete(@RequestBody @Valid RequestMessage<EmployeeTraceDetailListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeTraceDetailListDeleteReq request = reqMsg.getMessage();
            
            employeeTraceDetailListServiceImpl.employeeTraceDetailListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("追踪内容列表删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
