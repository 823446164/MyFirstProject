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
import com.amarsoft.app.ems.train.template.controller.EmployeeTraceRecordInfoController;
import com.amarsoft.app.ems.train.template.service.EmployeeTraceRecordInfoService;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracerecordinfo.EmployeeTraceRecordInfoSaveReq;

/**
 * 追踪记录详情Controller实现类
 * @author xphe
 */
@Slf4j
@RestController
public class EmployeeTraceRecordInfoControllerImpl implements EmployeeTraceRecordInfoController {
    @Autowired
    EmployeeTraceRecordInfoService employeeTraceRecordInfoServiceImpl;
    
    @Override
    @Transactional
    //追踪记录详情查询
    public ResponseEntity<ResponseMessage<EmployeeTraceRecordInfoQueryRsp>> employeeTraceRecordInfoQuery(@RequestBody @Valid RequestMessage<EmployeeTraceRecordInfoQueryReq> reqMsg){
        ResponseMessage<EmployeeTraceRecordInfoQueryRsp> rspMsg = null;
        try {
            EmployeeTraceRecordInfoQueryReq request = reqMsg.getMessage();
            
            EmployeeTraceRecordInfoQueryRsp response = employeeTraceRecordInfoServiceImpl.employeeTraceRecordInfoQuery(request);
            rspMsg = new ResponseMessage<EmployeeTraceRecordInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeTraceRecordInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("追踪记录详情查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeTraceRecordInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //追踪记录详情保存
    public ResponseEntity<ResponseMessage<Object>> employeeTraceRecordInfoSave(@RequestBody @Valid RequestMessage<EmployeeTraceRecordInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeTraceRecordInfoSaveReq request = reqMsg.getMessage();
            
            employeeTraceRecordInfoServiceImpl.employeeTraceRecordInfoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("追踪记录详情保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
