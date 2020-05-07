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
import com.amarsoft.app.ems.employee.template.controller.EmployeeBelongChangeInfoDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeBelongChangeInfoDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoSaveReq;

/**
 * 团队调整申请InfoController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeBelongChangeInfoDtoControllerImpl implements EmployeeBelongChangeInfoDtoController {
    @Autowired
    EmployeeBelongChangeInfoDtoService employeeBelongChangeInfoDtoServiceImpl;
    
    @Override
    @Transactional
    //团队调整申请Info查询
    public ResponseEntity<ResponseMessage<EmployeeBelongChangeInfoDtoQueryRsp>> employeeBelongChangeInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeBelongChangeInfoDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeBelongChangeInfoDtoQueryRsp> rspMsg = null;
        try {
            EmployeeBelongChangeInfoDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeBelongChangeInfoDtoQueryRsp response = employeeBelongChangeInfoDtoServiceImpl.employeeBelongChangeInfoDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeBelongChangeInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeBelongChangeInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("团队调整申请Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeBelongChangeInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //团队调整申请Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeBelongChangeInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeBelongChangeInfoDtoSaveReq request = reqMsg.getMessage();
            
            employeeBelongChangeInfoDtoServiceImpl.employeeBelongChangeInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("团队调整申请Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
