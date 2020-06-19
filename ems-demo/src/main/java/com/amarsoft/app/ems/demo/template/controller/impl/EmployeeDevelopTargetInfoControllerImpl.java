package com.amarsoft.app.ems.demo.template.controller.impl;

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
import com.amarsoft.app.ems.demo.template.controller.EmployeeDevelopTargetInfoController;
import com.amarsoft.app.ems.demo.template.service.EmployeeDevelopTargetInfoService;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetinfo.EmployeeDevelopTargetInfoSaveReq;

/**
 * 员工成长目标跟踪InfoController实现类
 * @author jfan5
 */
@Slf4j
@RestController
public class EmployeeDevelopTargetInfoControllerImpl implements EmployeeDevelopTargetInfoController {
    @Autowired
    EmployeeDevelopTargetInfoService employeeDevelopTargetInfoServiceImpl;
    
    @Override
    @Transactional
    //员工成长目标跟踪Info查询
    public ResponseEntity<ResponseMessage<EmployeeDevelopTargetInfoQueryRsp>> employeeDevelopTargetInfoQuery(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetInfoQueryReq> reqMsg){
        ResponseMessage<EmployeeDevelopTargetInfoQueryRsp> rspMsg = null;
        try {
            EmployeeDevelopTargetInfoQueryReq request = reqMsg.getMessage();
            
            EmployeeDevelopTargetInfoQueryRsp response = employeeDevelopTargetInfoServiceImpl.employeeDevelopTargetInfoQuery(request);
            rspMsg = new ResponseMessage<EmployeeDevelopTargetInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeDevelopTargetInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工成长目标跟踪Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeDevelopTargetInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工成长目标跟踪Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetInfoSave(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeDevelopTargetInfoSaveReq request = reqMsg.getMessage();
            
            employeeDevelopTargetInfoServiceImpl.employeeDevelopTargetInfoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工成长目标跟踪Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
