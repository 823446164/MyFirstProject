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
import com.amarsoft.app.ems.employee.template.controller.EmployeeRankInfoDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankInfoDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankinfodto.EmployeeRankInfoDtoSaveReq;

/**
 * 员工职级InfoController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeRankInfoDtoControllerImpl implements EmployeeRankInfoDtoController {
    @Autowired
    EmployeeRankInfoDtoService employeeRankInfoDtoServiceImpl;
    
    @Override
    @Transactional
    //员工职级Info查询
    public ResponseEntity<ResponseMessage<EmployeeRankInfoDtoQueryRsp>> employeeRankInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankInfoDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeRankInfoDtoQueryRsp> rspMsg = null;
        try {
            EmployeeRankInfoDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeRankInfoDtoQueryRsp response = employeeRankInfoDtoServiceImpl.employeeRankInfoDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeRankInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeRankInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeRankInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工职级Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeRankInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankInfoDtoSaveReq request = reqMsg.getMessage();
            
            employeeRankInfoDtoServiceImpl.employeeRankInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
