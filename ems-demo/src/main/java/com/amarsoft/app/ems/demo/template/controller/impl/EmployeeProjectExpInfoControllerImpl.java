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
import com.amarsoft.app.ems.demo.template.controller.EmployeeProjectExpInfoController;
import com.amarsoft.app.ems.demo.template.service.EmployeeProjectExpInfoService;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexpinfo.EmployeeProjectExpInfoSaveReq;

/**
 * 员工项目经历InfoController实现类
 * @author jfan5
 */
@Slf4j
@RestController
public class EmployeeProjectExpInfoControllerImpl implements EmployeeProjectExpInfoController {
    @Autowired
    EmployeeProjectExpInfoService employeeProjectExpInfoServiceImpl;
    
    @Override
    @Transactional
    //员工项目经历Info查询
    public ResponseEntity<ResponseMessage<EmployeeProjectExpInfoQueryRsp>> employeeProjectExpInfoQuery(@RequestBody @Valid RequestMessage<EmployeeProjectExpInfoQueryReq> reqMsg){
        ResponseMessage<EmployeeProjectExpInfoQueryRsp> rspMsg = null;
        try {
            EmployeeProjectExpInfoQueryReq request = reqMsg.getMessage();
            
            EmployeeProjectExpInfoQueryRsp response = employeeProjectExpInfoServiceImpl.employeeProjectExpInfoQuery(request);
            rspMsg = new ResponseMessage<EmployeeProjectExpInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeProjectExpInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeProjectExpInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工项目经历Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExpInfoSave(@RequestBody @Valid RequestMessage<EmployeeProjectExpInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeProjectExpInfoSaveReq request = reqMsg.getMessage();
            
            employeeProjectExpInfoServiceImpl.employeeProjectExpInfoSave(request);
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
