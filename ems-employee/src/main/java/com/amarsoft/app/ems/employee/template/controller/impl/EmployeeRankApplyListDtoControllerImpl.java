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
import com.amarsoft.app.ems.employee.template.controller.EmployeeRankApplyListDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankApplyListDtoService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeeRankApplyListDtoServiceImpl;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto.EmployeeRankApplyListDtoDeleteReq;

/**
 * 员工职级申请ListController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeeRankApplyListDtoControllerImpl implements EmployeeRankApplyListDtoController {
    @Autowired
    EmployeeRankApplyListDtoService employeeRankApplyListDtoServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工职级申请List", query = EmployeeRankApplyListDtoServiceImpl.EmployeeRankApplyListDtoReqQuery.class, convert=EmployeeRankApplyListDtoServiceImpl.EmployeeRankApplyListDtoRspConvert.class)
    //员工职级申请List查询
    public ResponseEntity<ResponseMessage<EmployeeRankApplyListDtoQueryRsp>> employeeRankApplyListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankApplyListDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeRankApplyListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeRankApplyListDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeRankApplyListDtoQueryRsp response = employeeRankApplyListDtoServiceImpl.employeeRankApplyListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeRankApplyListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeRankApplyListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级申请List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeRankApplyListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工职级申请List保存
    public ResponseEntity<ResponseMessage<Object>> employeeRankApplyListDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankApplyListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankApplyListDtoSaveReq request = reqMsg.getMessage();
            
            employeeRankApplyListDtoServiceImpl.employeeRankApplyListDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级申请List保存："+ reqMsg.toString(), e);
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
    //员工职级申请List删除
    public ResponseEntity<ResponseMessage<Object>> employeeRankApplyListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeRankApplyListDtoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankApplyListDtoDeleteReq request = reqMsg.getMessage();
            
            employeeRankApplyListDtoServiceImpl.employeeRankApplyListDtoDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级申请List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
