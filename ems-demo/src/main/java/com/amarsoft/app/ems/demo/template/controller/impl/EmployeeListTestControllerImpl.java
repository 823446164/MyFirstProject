package com.amarsoft.app.ems.demo.template.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.amarsoft.app.ems.demo.template.controller.EmployeeListTestController;
import com.amarsoft.app.ems.demo.template.service.EmployeeListTestService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.demo.template.service.impl.EmployeeListTestServiceImpl;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeRankApplySaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTestDeleteReq;

/**
 * 员工信息ListController实现类
 * @author jfan5
 */
@Slf4j
@RestController
public class EmployeeListTestControllerImpl implements EmployeeListTestController {
    @Autowired
    EmployeeListTestService employeeListTestServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工信息List", query = EmployeeListTestServiceImpl.EmployeeListTestReqQuery.class, convert=EmployeeListTestServiceImpl.EmployeeListTestRspConvert.class)
    //员工信息List查询
    public ResponseEntity<ResponseMessage<EmployeeListTestQueryRsp>> employeeListTestQuery(@RequestBody @Valid RequestMessage<EmployeeListTestQueryReq> reqMsg){
        ResponseMessage<EmployeeListTestQueryRsp> rspMsg = null;
        try {
            EmployeeListTestQueryReq request = reqMsg.getMessage();
            
            EmployeeListTestQueryRsp response = employeeListTestServiceImpl.employeeListTestQuery(request);
            rspMsg = new ResponseMessage<EmployeeListTestQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeListTestQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工信息List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeListTestQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工信息List保存
    public ResponseEntity<ResponseMessage<Object>> employeeListTestSave(@RequestBody @Valid RequestMessage<EmployeeListTestSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeListTestSaveReq request = reqMsg.getMessage();
            
            employeeListTestServiceImpl.employeeListTestSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工信息List保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工信息List删除
    public ResponseEntity<ResponseMessage<Object>> employeeListTestDelete(@RequestBody @Valid RequestMessage<EmployeeListTestDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeListTestDeleteReq request = reqMsg.getMessage();
            
            employeeListTestServiceImpl.employeeListTestDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工信息List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
