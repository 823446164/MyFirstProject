package com.amarsoft.app.ems.employee.template.controller.impl;

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
import com.amarsoft.app.ems.employee.template.controller.EmployeeTargetRankInfoDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeTargetRankInfoDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetrankinfodto.EmployeeTargetRankInfoDtoSaveReq;

/**
 * 目标职级申请InfoController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeTargetRankInfoDtoControllerImpl implements EmployeeTargetRankInfoDtoController {
    @Autowired
    EmployeeTargetRankInfoDtoService employeeTargetRankInfoDtoServiceImpl;
    
    @Override
    @Transactional
    //目标职级申请Info查询
    public ResponseEntity<ResponseMessage<EmployeeTargetRankInfoDtoQueryRsp>> employeeTargetRankInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeTargetRankInfoDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeTargetRankInfoDtoQueryRsp> rspMsg = null;
        try {
            EmployeeTargetRankInfoDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeTargetRankInfoDtoQueryRsp response = employeeTargetRankInfoDtoServiceImpl.employeeTargetRankInfoDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeTargetRankInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeTargetRankInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("目标职级申请Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeTargetRankInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //目标职级申请Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeTargetRankInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeTargetRankInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeTargetRankInfoDtoSaveReq request = reqMsg.getMessage();
            
            employeeTargetRankInfoDtoServiceImpl.employeeTargetRankInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("目标职级申请Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
