package com.amarsoft.app.ems.demo.template.controller.impl;

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
import com.amarsoft.app.ems.demo.template.controller.EmployeeBelongChangeInfoController;
import com.amarsoft.app.ems.demo.template.service.EmployeeBelongChangeInfoService;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangeinfo.EmployeeBelongChangeInfoSaveReq;

/**
 * 团队调整申请InfoController实现类
 * @author jfan5
 */
@Slf4j
@RestController
public class EmployeeBelongChangeInfoControllerImpl implements EmployeeBelongChangeInfoController {
    @Autowired
    EmployeeBelongChangeInfoService employeeBelongChangeInfoServiceImpl;
    
    @Override
    @Transactional
    //团队调整申请Info查询
    public ResponseEntity<ResponseMessage<EmployeeBelongChangeInfoQueryRsp>> employeeBelongChangeInfoQuery(@RequestBody @Valid RequestMessage<EmployeeBelongChangeInfoQueryReq> reqMsg){
        ResponseMessage<EmployeeBelongChangeInfoQueryRsp> rspMsg = null;
        try {
            EmployeeBelongChangeInfoQueryReq request = reqMsg.getMessage();
            
            EmployeeBelongChangeInfoQueryRsp response = employeeBelongChangeInfoServiceImpl.employeeBelongChangeInfoQuery(request);
            rspMsg = new ResponseMessage<EmployeeBelongChangeInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeBelongChangeInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("团队调整申请Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeBelongChangeInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //团队调整申请Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeInfoSave(@RequestBody @Valid RequestMessage<EmployeeBelongChangeInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeBelongChangeInfoSaveReq request = reqMsg.getMessage();
            
            employeeBelongChangeInfoServiceImpl.employeeBelongChangeInfoSave(request);
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
