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
import com.amarsoft.app.ems.employee.template.controller.EmployeeRankListDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankListDtoService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeeRankListDtoServiceImpl;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoDeleteReq;

/**
 * 员工职级ListController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeRankListDtoControllerImpl implements EmployeeRankListDtoController {
    @Autowired
    EmployeeRankListDtoService employeeRankListDtoServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工职级List", query = EmployeeRankListDtoServiceImpl.EmployeeRankListDtoReqQuery.class, convert=EmployeeRankListDtoServiceImpl.EmployeeRankListDtoRspConvert.class)
    //员工职级List查询
    public ResponseEntity<ResponseMessage<EmployeeRankListDtoQueryRsp>> employeeRankListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankListDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeRankListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeRankListDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeRankListDtoQueryRsp response = employeeRankListDtoServiceImpl.employeeRankListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeRankListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeRankListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeRankListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工职级List保存
    public ResponseEntity<ResponseMessage<Object>> employeeRankListDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankListDtoSaveReq request = reqMsg.getMessage();
            
            employeeRankListDtoServiceImpl.employeeRankListDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级List保存："+ reqMsg.toString(), e);
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
    //员工职级List删除
    public ResponseEntity<ResponseMessage<Object>> employeeRankListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeRankListDtoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankListDtoDeleteReq request = reqMsg.getMessage();
            
            employeeRankListDtoServiceImpl.employeeRankListDtoDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
