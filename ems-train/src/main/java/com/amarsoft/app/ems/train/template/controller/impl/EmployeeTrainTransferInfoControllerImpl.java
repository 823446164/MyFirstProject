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
import com.amarsoft.app.ems.train.template.controller.EmployeeTrainTransferInfoController;
import com.amarsoft.app.ems.train.template.service.EmployeeTrainTransferInfoService;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfoSaveReq;

/**
 * 培训转移记录Controller实现类
 * @author xphe
 */
@Slf4j
@RestController
public class EmployeeTrainTransferInfoControllerImpl implements EmployeeTrainTransferInfoController {
    @Autowired
    EmployeeTrainTransferInfoService employeeTrainTransferInfoServiceImpl;
    
    @Override
    @Transactional
    //培训转移记录查询
    public ResponseEntity<ResponseMessage<EmployeeTrainTransferInfoQueryRsp>> employeeTrainTransferInfoQuery(@RequestBody @Valid RequestMessage<EmployeeTrainTransferInfoQueryReq> reqMsg){
        ResponseMessage<EmployeeTrainTransferInfoQueryRsp> rspMsg = null;
        try {
            EmployeeTrainTransferInfoQueryReq request = reqMsg.getMessage();
            
            EmployeeTrainTransferInfoQueryRsp response = employeeTrainTransferInfoServiceImpl.employeeTrainTransferInfoQuery(request);
            rspMsg = new ResponseMessage<EmployeeTrainTransferInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeTrainTransferInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("培训转移记录查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeTrainTransferInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //培训转移记录保存
    public ResponseEntity<ResponseMessage<Object>> employeeTrainTransferInfoSave(@RequestBody @Valid RequestMessage<EmployeeTrainTransferInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeTrainTransferInfoSaveReq request = reqMsg.getMessage();
            
            employeeTrainTransferInfoServiceImpl.employeeTrainTransferInfoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("培训转移记录保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
