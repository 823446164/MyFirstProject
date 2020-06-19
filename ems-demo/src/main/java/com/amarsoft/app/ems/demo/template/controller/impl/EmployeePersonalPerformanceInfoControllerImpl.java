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
import com.amarsoft.app.ems.demo.template.controller.EmployeePersonalPerformanceInfoController;
import com.amarsoft.app.ems.demo.template.service.EmployeePersonalPerformanceInfoService;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformanceinfo.EmployeePersonalPerformanceInfoSaveReq;

/**
 * 员工项目经历个人表现InfoController实现类
 * @author jfan5
 */
@Slf4j
@RestController
public class EmployeePersonalPerformanceInfoControllerImpl implements EmployeePersonalPerformanceInfoController {
    @Autowired
    EmployeePersonalPerformanceInfoService employeePersonalPerformanceInfoServiceImpl;
    
    @Override
    @Transactional
    //员工项目经历个人表现Info查询
    public ResponseEntity<ResponseMessage<EmployeePersonalPerformanceInfoQueryRsp>> employeePersonalPerformanceInfoQuery(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceInfoQueryReq> reqMsg){
        ResponseMessage<EmployeePersonalPerformanceInfoQueryRsp> rspMsg = null;
        try {
            EmployeePersonalPerformanceInfoQueryReq request = reqMsg.getMessage();
            
            EmployeePersonalPerformanceInfoQueryRsp response = employeePersonalPerformanceInfoServiceImpl.employeePersonalPerformanceInfoQuery(request);
            rspMsg = new ResponseMessage<EmployeePersonalPerformanceInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeePersonalPerformanceInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历个人表现Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeePersonalPerformanceInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工项目经历个人表现Info保存
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceInfoSave(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeePersonalPerformanceInfoSaveReq request = reqMsg.getMessage();
            
            employeePersonalPerformanceInfoServiceImpl.employeePersonalPerformanceInfoSave(request);
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
