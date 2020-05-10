package com.amarsoft.app.ems.employee.template.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

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
import com.amarsoft.app.ems.employee.template.controller.EmployeeDevelopTargetInfoDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeDevelopTargetInfoDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetinfodto.EmployeeDevelopTargetInfoDtoSaveReq;

/**
 * 员工成长目标跟踪InfoController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeDevelopTargetInfoDtoControllerImpl implements EmployeeDevelopTargetInfoDtoController {
    @Autowired
    EmployeeDevelopTargetInfoDtoService employeeDevelopTargetInfoDtoServiceImpl;
    
    @Override
    @Transactional
    //员工成长目标跟踪Info查询
    public ResponseEntity<ResponseMessage<EmployeeDevelopTargetInfoDtoQueryRsp>> employeeDevelopTargetInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetInfoDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeDevelopTargetInfoDtoQueryRsp> rspMsg = null;
        try {
            EmployeeDevelopTargetInfoDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeDevelopTargetInfoDtoQueryRsp response = employeeDevelopTargetInfoDtoServiceImpl.employeeDevelopTargetInfoDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeDevelopTargetInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeDevelopTargetInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工成长目标跟踪Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeDevelopTargetInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工成长目标跟踪Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeDevelopTargetInfoDtoSaveReq request = reqMsg.getMessage();
            //接到service插入成功返回的信息
            Map<String,String> response = employeeDevelopTargetInfoDtoServiceImpl.employeeDevelopTargetInfoDtoSave(request);
            //添加到响应信息
            rspMsg = new ResponseMessage<Object>(response);

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
