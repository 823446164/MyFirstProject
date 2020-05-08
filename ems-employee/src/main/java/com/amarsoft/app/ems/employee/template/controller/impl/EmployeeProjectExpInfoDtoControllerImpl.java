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
import com.amarsoft.app.ems.employee.template.controller.EmployeeProjectExpInfoDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeProjectExpInfoDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto.EmployeeProjectExpInfoDtoSaveReq;

/**
 * 员工项目经历InfoController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeProjectExpInfoDtoControllerImpl implements EmployeeProjectExpInfoDtoController {
    @Autowired
    EmployeeProjectExpInfoDtoService employeeProjectExpInfoDtoServiceImpl;
    
    @Override
    @Transactional
    //员工项目经历Info查询
    public ResponseEntity<ResponseMessage<EmployeeProjectExpInfoDtoQueryRsp>> employeeProjectExpInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeProjectExpInfoDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeProjectExpInfoDtoQueryRsp> rspMsg = null;
        try {
            EmployeeProjectExpInfoDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeProjectExpInfoDtoQueryRsp response = employeeProjectExpInfoDtoServiceImpl.employeeProjectExpInfoDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeProjectExpInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeProjectExpInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeProjectExpInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工项目经历Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExpInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeProjectExpInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeProjectExpInfoDtoSaveReq request = reqMsg.getMessage();
            
            employeeProjectExpInfoDtoServiceImpl.employeeProjectExpInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
