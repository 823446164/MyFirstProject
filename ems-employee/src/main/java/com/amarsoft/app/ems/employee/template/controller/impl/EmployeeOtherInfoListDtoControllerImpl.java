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
import com.amarsoft.app.ems.employee.template.controller.EmployeeOtherInfoListDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeOtherInfoListDtoService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeeOtherInfoListDtoServiceImpl;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeotherinfolistdto.EmployeeOtherInfoListDtoDeleteReq;

/**
 * 员工其他说明ListController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeOtherInfoListDtoControllerImpl implements EmployeeOtherInfoListDtoController {
    @Autowired
    EmployeeOtherInfoListDtoService employeeOtherInfoListDtoServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工其他说明List", query = EmployeeOtherInfoListDtoServiceImpl.EmployeeOtherInfoListDtoReqQuery.class, convert=EmployeeOtherInfoListDtoServiceImpl.EmployeeOtherInfoListDtoRspConvert.class)
    //员工其他说明List查询
    public ResponseEntity<ResponseMessage<EmployeeOtherInfoListDtoQueryRsp>> employeeOtherInfoListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeOtherInfoListDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeOtherInfoListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeOtherInfoListDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeOtherInfoListDtoQueryRsp response = employeeOtherInfoListDtoServiceImpl.employeeOtherInfoListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeOtherInfoListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeOtherInfoListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工其他说明List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeOtherInfoListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工其他说明List保存
    public ResponseEntity<ResponseMessage<Object>> employeeOtherInfoListDtoSave(@RequestBody @Valid RequestMessage<EmployeeOtherInfoListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeOtherInfoListDtoSaveReq request = reqMsg.getMessage();
            
            employeeOtherInfoListDtoServiceImpl.employeeOtherInfoListDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工其他说明List保存："+ reqMsg.toString(), e);
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
    //员工其他说明List删除
    public ResponseEntity<ResponseMessage<Object>> employeeOtherInfoListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeOtherInfoListDtoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeOtherInfoListDtoDeleteReq request = reqMsg.getMessage();
            
            employeeOtherInfoListDtoServiceImpl.employeeOtherInfoListDtoDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工其他说明List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
