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
import com.amarsoft.app.ems.employee.template.controller.EmployeeProjectExpListDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeProjectExpListDtoService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeeProjectExpListDtoServiceImpl;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto.EmployeeProjectExpListDtoDeleteReq;

/**
 * 员工项目经历ListController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeProjectExpListDtoControllerImpl implements EmployeeProjectExpListDtoController {
    @Autowired
    EmployeeProjectExpListDtoService employeeProjectExpListDtoServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工项目经历List", query = EmployeeProjectExpListDtoServiceImpl.EmployeeProjectExpListDtoReqQuery.class, convert=EmployeeProjectExpListDtoServiceImpl.EmployeeProjectExpListDtoRspConvert.class)
    //员工项目经历List查询
    public ResponseEntity<ResponseMessage<EmployeeProjectExpListDtoQueryRsp>> employeeProjectExpListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeProjectExpListDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeProjectExpListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeProjectExpListDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeProjectExpListDtoQueryRsp response = employeeProjectExpListDtoServiceImpl.employeeProjectExpListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeProjectExpListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeProjectExpListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeProjectExpListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工项目经历List保存
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExpListDtoSave(@RequestBody @Valid RequestMessage<EmployeeProjectExpListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeProjectExpListDtoSaveReq request = reqMsg.getMessage();
            
            employeeProjectExpListDtoServiceImpl.employeeProjectExpListDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历List保存："+ reqMsg.toString(), e);
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
    //员工项目经历List删除
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExpListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeProjectExpListDtoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeProjectExpListDtoDeleteReq request = reqMsg.getMessage();
            
            employeeProjectExpListDtoServiceImpl.employeeProjectExpListDtoDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
