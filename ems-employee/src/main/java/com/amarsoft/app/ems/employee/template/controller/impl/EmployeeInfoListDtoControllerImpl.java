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
import com.amarsoft.app.ems.employee.template.controller.EmployeeInfoListDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeInfoListDtoService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeeInfoListDtoServiceImpl;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoDeleteReq;

/**
 * 员工信息ListController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeInfoListDtoControllerImpl implements EmployeeInfoListDtoController {
    @Autowired
    EmployeeInfoListDtoService employeeInfoListDtoServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工信息List", query = EmployeeInfoListDtoServiceImpl.EmployeeInfoListDtoReqQuery.class, convert=EmployeeInfoListDtoServiceImpl.EmployeeInfoListDtoRspConvert.class)
    //员工信息List查询
    public ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>> employeeInfoListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeInfoListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeInfoListDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeInfoListDtoQueryRsp response = employeeInfoListDtoServiceImpl.employeeInfoListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeInfoListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工信息List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工信息List保存
    public ResponseEntity<ResponseMessage<Object>> employeeInfoListDtoSave(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeInfoListDtoSaveReq request = reqMsg.getMessage();
            
            employeeInfoListDtoServiceImpl.employeeInfoListDtoSave(request);
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
    public ResponseEntity<ResponseMessage<Object>> employeeInfoListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeInfoListDtoDeleteReq request = reqMsg.getMessage();
            
            employeeInfoListDtoServiceImpl.employeeInfoListDtoDelete(request);
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
