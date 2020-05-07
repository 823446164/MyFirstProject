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
import com.amarsoft.app.ems.employee.template.controller.EmployeeOtherInfoDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeOtherInfoDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfodto.EmployeeOtherInfoDtoSaveReq;

/**
 * 员工其他说明InfoController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeOtherInfoDtoControllerImpl implements EmployeeOtherInfoDtoController {
    @Autowired
    EmployeeOtherInfoDtoService employeeOtherInfoDtoServiceImpl;
    
    @Override
    @Transactional
    //员工其他说明Info查询
    public ResponseEntity<ResponseMessage<EmployeeOtherInfoDtoQueryRsp>> employeeOtherInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeOtherInfoDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeOtherInfoDtoQueryRsp> rspMsg = null;
        try {
            EmployeeOtherInfoDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeOtherInfoDtoQueryRsp response = employeeOtherInfoDtoServiceImpl.employeeOtherInfoDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeOtherInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeOtherInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工其他说明Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeOtherInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工其他说明Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeOtherInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeOtherInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeOtherInfoDtoSaveReq request = reqMsg.getMessage();
            
            employeeOtherInfoDtoServiceImpl.employeeOtherInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工其他说明Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
