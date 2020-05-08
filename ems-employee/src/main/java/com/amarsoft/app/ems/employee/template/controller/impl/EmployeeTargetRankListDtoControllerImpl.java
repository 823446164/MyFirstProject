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
import com.amarsoft.app.ems.employee.template.controller.EmployeeTargetRankListDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeTargetRankListDtoService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeeTargetRankListDtoServiceImpl;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoDeleteReq;

/**
 * 目标职级申请ListController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeTargetRankListDtoControllerImpl implements EmployeeTargetRankListDtoController {
    @Autowired
    EmployeeTargetRankListDtoService employeeTargetRankListDtoServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="目标职级申请List", query = EmployeeTargetRankListDtoServiceImpl.EmployeeTargetRankListDtoReqQuery.class, convert=EmployeeTargetRankListDtoServiceImpl.EmployeeTargetRankListDtoRspConvert.class)
    //目标职级申请List查询
    public ResponseEntity<ResponseMessage<EmployeeTargetRankListDtoQueryRsp>> employeeTargetRankListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeTargetRankListDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeTargetRankListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeTargetRankListDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeTargetRankListDtoQueryRsp response = employeeTargetRankListDtoServiceImpl.employeeTargetRankListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeTargetRankListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeTargetRankListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("目标职级申请List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeTargetRankListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //目标职级申请List保存
    public ResponseEntity<ResponseMessage<Object>> employeeTargetRankListDtoSave(@RequestBody @Valid RequestMessage<EmployeeTargetRankListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeTargetRankListDtoSaveReq request = reqMsg.getMessage();
            
            employeeTargetRankListDtoServiceImpl.employeeTargetRankListDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("目标职级申请List保存："+ reqMsg.toString(), e);
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
    //目标职级申请List删除
    public ResponseEntity<ResponseMessage<Object>> employeeTargetRankListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeTargetRankListDtoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeTargetRankListDtoDeleteReq request = reqMsg.getMessage();
            
            employeeTargetRankListDtoServiceImpl.employeeTargetRankListDtoDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("目标职级申请List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
