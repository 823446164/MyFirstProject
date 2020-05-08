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
import com.amarsoft.app.ems.train.template.controller.EmployeeTraceDetailInfoController;
import com.amarsoft.app.ems.train.template.service.EmployeeTraceDetailInfoService;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetracedetailinfo.EmployeeTraceDetailInfoSaveReq;

/**
 * 追踪内容详情Controller实现类
 * @author xphe
 */
@Slf4j
@RestController
public class EmployeeTraceDetailInfoControllerImpl implements EmployeeTraceDetailInfoController {
    @Autowired
    EmployeeTraceDetailInfoService employeeTraceDetailInfoServiceImpl;
    
    @Override
    @Transactional
    //追踪内容详情查询
    public ResponseEntity<ResponseMessage<EmployeeTraceDetailInfoQueryRsp>> employeeTraceDetailInfoQuery(@RequestBody @Valid RequestMessage<EmployeeTraceDetailInfoQueryReq> reqMsg){
        ResponseMessage<EmployeeTraceDetailInfoQueryRsp> rspMsg = null;
        try {
            EmployeeTraceDetailInfoQueryReq request = reqMsg.getMessage();
            
            EmployeeTraceDetailInfoQueryRsp response = employeeTraceDetailInfoServiceImpl.employeeTraceDetailInfoQuery(request);
            rspMsg = new ResponseMessage<EmployeeTraceDetailInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeTraceDetailInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("追踪内容详情查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeTraceDetailInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //追踪内容详情保存
    public ResponseEntity<ResponseMessage<Object>> employeeTraceDetailInfoSave(@RequestBody @Valid RequestMessage<EmployeeTraceDetailInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeTraceDetailInfoSaveReq request = reqMsg.getMessage();
            
            employeeTraceDetailInfoServiceImpl.employeeTraceDetailInfoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("追踪内容详情保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
