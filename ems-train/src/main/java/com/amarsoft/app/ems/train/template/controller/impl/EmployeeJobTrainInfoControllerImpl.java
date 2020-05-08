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
import com.amarsoft.app.ems.train.template.controller.EmployeeJobTrainInfoController;
import com.amarsoft.app.ems.train.template.service.EmployeeJobTrainInfoService;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfoSaveReq;

/**
 * 在职培训详情Controller实现类
 * @author xphe
 */
@Slf4j
@RestController
public class EmployeeJobTrainInfoControllerImpl implements EmployeeJobTrainInfoController {
    @Autowired
    EmployeeJobTrainInfoService employeeJobTrainInfoServiceImpl;
    
    @Override
    @Transactional
    //在职培训详情查询
    public ResponseEntity<ResponseMessage<EmployeeJobTrainInfoQueryRsp>> employeeJobTrainInfoQuery(@RequestBody @Valid RequestMessage<EmployeeJobTrainInfoQueryReq> reqMsg){
        ResponseMessage<EmployeeJobTrainInfoQueryRsp> rspMsg = null;
        try {
            EmployeeJobTrainInfoQueryReq request = reqMsg.getMessage();
            
            EmployeeJobTrainInfoQueryRsp response = employeeJobTrainInfoServiceImpl.employeeJobTrainInfoQuery(request);
            rspMsg = new ResponseMessage<EmployeeJobTrainInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeJobTrainInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("在职培训详情查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeJobTrainInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //在职培训详情保存
    public ResponseEntity<ResponseMessage<Object>> employeeJobTrainInfoSave(@RequestBody @Valid RequestMessage<EmployeeJobTrainInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeJobTrainInfoSaveReq request = reqMsg.getMessage();
            
            employeeJobTrainInfoServiceImpl.employeeJobTrainInfoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("在职培训详情保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
