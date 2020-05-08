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
import com.amarsoft.app.ems.employee.template.controller.EmployeeBelongChangeListDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeBelongChangeListDtoService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeeBelongChangeListDtoServiceImpl;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangelistdto.EmployeeBelongChangeListDtoDeleteReq;

/**
 * 团队调整申请ListController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeBelongChangeListDtoControllerImpl implements EmployeeBelongChangeListDtoController {
    @Autowired
    EmployeeBelongChangeListDtoService employeeBelongChangeListDtoServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="团队调整申请List", query = EmployeeBelongChangeListDtoServiceImpl.EmployeeBelongChangeListDtoReqQuery.class, convert=EmployeeBelongChangeListDtoServiceImpl.EmployeeBelongChangeListDtoRspConvert.class)
    //团队调整申请List查询
    public ResponseEntity<ResponseMessage<EmployeeBelongChangeListDtoQueryRsp>> employeeBelongChangeListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeBelongChangeListDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeBelongChangeListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeBelongChangeListDtoQueryReq request = reqMsg.getMessage();       
            EmployeeBelongChangeListDtoQueryRsp response = employeeBelongChangeListDtoServiceImpl.employeeBelongChangeListDtoQuery(request);
            
            rspMsg = new ResponseMessage<EmployeeBelongChangeListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeBelongChangeListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("团队调整申请List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeBelongChangeListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //团队调整申请List保存
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeListDtoSave(@RequestBody @Valid RequestMessage<EmployeeBelongChangeListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeBelongChangeListDtoSaveReq request = reqMsg.getMessage();
            
            employeeBelongChangeListDtoServiceImpl.employeeBelongChangeListDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("团队调整申请List保存："+ reqMsg.toString(), e);
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
    //团队调整申请List删除
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeBelongChangeListDtoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeBelongChangeListDtoDeleteReq request = reqMsg.getMessage();
            
            employeeBelongChangeListDtoServiceImpl.employeeBelongChangeListDtoDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("团队调整申请List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
