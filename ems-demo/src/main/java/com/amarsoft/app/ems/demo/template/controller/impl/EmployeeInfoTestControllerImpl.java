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
import com.amarsoft.app.ems.demo.template.controller.EmployeeInfoTestController;
import com.amarsoft.app.ems.demo.template.service.EmployeeInfoTestService;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTestQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTestQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeInfoTestSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest.EmployeeRankApplySaveReq;

/**
 * 员工信息InfoController实现类
 * @author jfan5
 */
@Slf4j
@RestController
public class EmployeeInfoTestControllerImpl implements EmployeeInfoTestController {
    @Autowired
    EmployeeInfoTestService employeeInfoTestServiceImpl;
    
    @Override
    @Transactional
    //员工信息Info查询
    public ResponseEntity<ResponseMessage<EmployeeInfoTestQueryRsp>> employeeInfoTestQuery(@RequestBody @Valid RequestMessage<EmployeeInfoTestQueryReq> reqMsg){
        ResponseMessage<EmployeeInfoTestQueryRsp> rspMsg = null;
        try {
            EmployeeInfoTestQueryReq request = reqMsg.getMessage();
            
            EmployeeInfoTestQueryRsp response = employeeInfoTestServiceImpl.employeeInfoTestQuery(request);
            rspMsg = new ResponseMessage<EmployeeInfoTestQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeInfoTestQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工信息Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeInfoTestQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工信息Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeInfoTestSave(@RequestBody @Valid RequestMessage<EmployeeInfoTestSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeInfoTestSaveReq request = reqMsg.getMessage();
            
            employeeInfoTestServiceImpl.employeeInfoTestSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工信息Info保存："+ reqMsg.toString(), e);
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
	public ResponseEntity<ResponseMessage<Object>> employeeListTestRankApply(@RequestBody @Valid RequestMessage<EmployeeInfoTestSaveReq> reqMsg) {
		 ResponseMessage<Object> rspMsg = null;
	        try {
	        	EmployeeInfoTestSaveReq request = reqMsg.getMessage();
	            System.out.println("reqMsg:"+reqMsg.getMessage());
	            
	            employeeInfoTestServiceImpl.employeeRankApply(request);;
	            rspMsg = new ResponseMessage<Object>();

	            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
	        } catch (Exception e) {
	            if(log.isErrorEnabled()) {
	                log.error("员工职级申请保存："+ reqMsg.toString(), e);
	            }
	            //事务回滚
	            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	            // TODO Auto-generated  //默认异常码未设置，请补充。
	            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
	            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	}
}
