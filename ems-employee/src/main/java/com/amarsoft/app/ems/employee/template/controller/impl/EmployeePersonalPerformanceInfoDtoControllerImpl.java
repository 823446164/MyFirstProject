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
import com.amarsoft.app.ems.employee.template.controller.EmployeePersonalPerformanceInfoDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeePersonalPerformanceInfoDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformanceinfodto.EmployeePersonalPerformanceInfoDtoSaveReq;

/**
 * 员工项目经历个人表现InfoController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeePersonalPerformanceInfoDtoControllerImpl implements EmployeePersonalPerformanceInfoDtoController {
    @Autowired
    EmployeePersonalPerformanceInfoDtoService employeePersonalPerformanceInfoDtoServiceImpl;
    
    @Override
    @Transactional
    //员工项目经历个人表现Info查询
    public ResponseEntity<ResponseMessage<EmployeePersonalPerformanceInfoDtoQueryRsp>> employeePersonalPerformanceInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceInfoDtoQueryReq> reqMsg){
        ResponseMessage<EmployeePersonalPerformanceInfoDtoQueryRsp> rspMsg = null;
        try {
            EmployeePersonalPerformanceInfoDtoQueryReq request = reqMsg.getMessage();
            
            EmployeePersonalPerformanceInfoDtoQueryRsp response = employeePersonalPerformanceInfoDtoServiceImpl.employeePersonalPerformanceInfoDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeePersonalPerformanceInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeePersonalPerformanceInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历个人表现Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeePersonalPerformanceInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工项目经历个人表现Info保存
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeePersonalPerformanceInfoDtoSaveReq request = reqMsg.getMessage();
            
            employeePersonalPerformanceInfoDtoServiceImpl.employeePersonalPerformanceInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历个人表现Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
