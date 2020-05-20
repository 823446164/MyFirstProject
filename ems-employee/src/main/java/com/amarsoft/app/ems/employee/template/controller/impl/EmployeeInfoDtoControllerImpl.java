package com.amarsoft.app.ems.employee.template.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.employee.template.controller.EmployeeInfoDtoController;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.service.EmployeeInfoDtoService;

import lombok.extern.slf4j.Slf4j;

/**
 * 员工信息InfoController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeInfoDtoControllerImpl implements EmployeeInfoDtoController {
    @Autowired
    EmployeeInfoDtoService employeeInfoDtoServiceImpl;
    
    @Override
    @Transactional
    //员工信息Info查询
    public ResponseEntity<ResponseMessage<EmployeeInfoDtoQueryRsp>> employeeInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeInfoDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeInfoDtoQueryRsp> rspMsg = null;
        try {
            EmployeeInfoDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeInfoDtoQueryRsp response = employeeInfoDtoServiceImpl.employeeInfoDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工信息Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工信息Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeInfoDtoSaveReq request = reqMsg.getMessage();
            
            employeeInfoDtoServiceImpl.employeeInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工信息Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
