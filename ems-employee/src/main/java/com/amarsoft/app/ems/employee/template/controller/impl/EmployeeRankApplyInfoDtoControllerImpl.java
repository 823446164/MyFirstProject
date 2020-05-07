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
import com.amarsoft.app.ems.employee.template.controller.EmployeeRankApplyInfoDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankApplyInfoDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoSaveReq;

/**
 * 员工职级申请InfoController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeRankApplyInfoDtoControllerImpl implements EmployeeRankApplyInfoDtoController {
    @Autowired
    EmployeeRankApplyInfoDtoService employeeRankApplyInfoDtoServiceImpl;
    
    @Override
    @Transactional
    //员工职级申请Info查询
    public ResponseEntity<ResponseMessage<EmployeeRankApplyInfoDtoQueryRsp>> employeeRankApplyInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankApplyInfoDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeRankApplyInfoDtoQueryRsp> rspMsg = null;
        try {
            EmployeeRankApplyInfoDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeRankApplyInfoDtoQueryRsp response = employeeRankApplyInfoDtoServiceImpl.employeeRankApplyInfoDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeRankApplyInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeRankApplyInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级申请Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeRankApplyInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工职级申请Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeRankApplyInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankApplyInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankApplyInfoDtoSaveReq request = reqMsg.getMessage();
            
            employeeRankApplyInfoDtoServiceImpl.employeeRankApplyInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级申请Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
