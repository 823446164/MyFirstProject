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
import com.amarsoft.app.ems.train.template.controller.EmployeeTraceRecordListController;
import com.amarsoft.app.ems.train.template.service.EmployeeTraceRecordListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.train.template.service.impl.EmployeeTraceRecordListServiceImpl;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordlist.EmployeeTraceRecordListDeleteReq;

/**
 * 追踪记录列表Controller实现类
 * @author xphe
 */
@Slf4j
@RestController
public class EmployeeTraceRecordListControllerImpl implements EmployeeTraceRecordListController {
    @Autowired
    EmployeeTraceRecordListService employeeTraceRecordListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="追踪记录列表", query = EmployeeTraceRecordListServiceImpl.EmployeeTraceRecordListReqQuery.class, convert=EmployeeTraceRecordListServiceImpl.EmployeeTraceRecordListRspConvert.class)
    //追踪记录列表查询
    public ResponseEntity<ResponseMessage<EmployeeTraceRecordListQueryRsp>> employeeTraceRecordListQuery(@RequestBody @Valid RequestMessage<EmployeeTraceRecordListQueryReq> reqMsg){
        ResponseMessage<EmployeeTraceRecordListQueryRsp> rspMsg = null;
        try {
            EmployeeTraceRecordListQueryReq request = reqMsg.getMessage();
            
            EmployeeTraceRecordListQueryRsp response = employeeTraceRecordListServiceImpl.employeeTraceRecordListQuery(request);
            rspMsg = new ResponseMessage<EmployeeTraceRecordListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeTraceRecordListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("追踪记录列表查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeTraceRecordListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //追踪记录列表保存
    public ResponseEntity<ResponseMessage<Object>> employeeTraceRecordListSave(@RequestBody @Valid RequestMessage<EmployeeTraceRecordListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeTraceRecordListSaveReq request = reqMsg.getMessage();
            
            employeeTraceRecordListServiceImpl.employeeTraceRecordListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("追踪记录列表保存："+ reqMsg.toString(), e);
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
    //追踪记录列表删除
    public ResponseEntity<ResponseMessage<Object>> employeeTraceRecordListDelete(@RequestBody @Valid RequestMessage<EmployeeTraceRecordListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeTraceRecordListDeleteReq request = reqMsg.getMessage();
            
            employeeTraceRecordListServiceImpl.employeeTraceRecordListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("追踪记录列表删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
