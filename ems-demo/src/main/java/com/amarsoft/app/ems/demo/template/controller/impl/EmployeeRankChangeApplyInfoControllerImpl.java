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
import com.amarsoft.app.ems.demo.template.controller.EmployeeRankChangeApplyInfoController;
import com.amarsoft.app.ems.demo.template.service.EmployeeRankChangeApplyInfoService;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfoQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfoQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplyinfo.EmployeeRankChangeApplyInfoSaveReq;

/**
 * 员工职级调整申请详情InfoController实现类
 * @author jfan5
 */
@Slf4j
@RestController
public class EmployeeRankChangeApplyInfoControllerImpl implements EmployeeRankChangeApplyInfoController {
    @Autowired
    EmployeeRankChangeApplyInfoService employeeRankChangeApplyInfoServiceImpl;
    
    @Override
    @Transactional
    //员工职级调整申请详情Info查询
    public ResponseEntity<ResponseMessage<EmployeeRankChangeApplyInfoQueryRsp>> employeeRankChangeApplyInfoQuery(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyInfoQueryReq> reqMsg){
        ResponseMessage<EmployeeRankChangeApplyInfoQueryRsp> rspMsg = null;
        try {
            EmployeeRankChangeApplyInfoQueryReq request = reqMsg.getMessage();
            
            EmployeeRankChangeApplyInfoQueryRsp response = employeeRankChangeApplyInfoServiceImpl.employeeRankChangeApplyInfoQuery(request);
            rspMsg = new ResponseMessage<EmployeeRankChangeApplyInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeRankChangeApplyInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整申请详情Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeRankChangeApplyInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工职级调整申请详情Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeApplyInfoSave(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankChangeApplyInfoSaveReq request = reqMsg.getMessage();
            
            employeeRankChangeApplyInfoServiceImpl.employeeRankChangeApplyInfoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整申请详情Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
