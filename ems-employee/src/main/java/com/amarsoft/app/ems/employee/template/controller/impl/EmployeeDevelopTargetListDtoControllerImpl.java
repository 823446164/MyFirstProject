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
import com.amarsoft.app.ems.employee.template.controller.EmployeeDevelopTargetListDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeDevelopTargetListDtoService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeeDevelopTargetListDtoServiceImpl;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoDeleteReq;

/**
 * 员工成长目标跟踪ListController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeDevelopTargetListDtoControllerImpl implements EmployeeDevelopTargetListDtoController {
    @Autowired
    EmployeeDevelopTargetListDtoService employeeDevelopTargetListDtoServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工成长目标跟踪List", query = EmployeeDevelopTargetListDtoServiceImpl.EmployeeDevelopTargetListDtoReqQuery.class, convert=EmployeeDevelopTargetListDtoServiceImpl.EmployeeDevelopTargetListDtoRspConvert.class)
    //员工成长目标跟踪List查询
    public ResponseEntity<ResponseMessage<EmployeeDevelopTargetListDtoQueryRsp>> employeeDevelopTargetListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeDevelopTargetListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeDevelopTargetListDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeDevelopTargetListDtoQueryRsp response = employeeDevelopTargetListDtoServiceImpl.employeeDevelopTargetListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeDevelopTargetListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeDevelopTargetListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工成长目标跟踪List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeDevelopTargetListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工成长目标跟踪List保存
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetListDtoSave(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeDevelopTargetListDtoSaveReq request = reqMsg.getMessage();
            
            employeeDevelopTargetListDtoServiceImpl.employeeDevelopTargetListDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工成长目标跟踪List保存："+ reqMsg.toString(), e);
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
    //员工成长目标跟踪List删除
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListDtoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeDevelopTargetListDtoDeleteReq request = reqMsg.getMessage();
            
            employeeDevelopTargetListDtoServiceImpl.employeeDevelopTargetListDtoDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工成长目标跟踪List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}