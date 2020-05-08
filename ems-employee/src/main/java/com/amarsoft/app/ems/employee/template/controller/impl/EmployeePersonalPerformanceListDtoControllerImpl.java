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
import com.amarsoft.app.ems.employee.template.controller.EmployeePersonalPerformanceListDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeePersonalPerformanceListDtoService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeePersonalPerformanceListDtoServiceImpl;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoDeleteReq;

/**
 * 员工项目经历个人表现ListController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeePersonalPerformanceListDtoControllerImpl implements EmployeePersonalPerformanceListDtoController {
    @Autowired
    EmployeePersonalPerformanceListDtoService employeePersonalPerformanceListDtoServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工项目经历个人表现List", query = EmployeePersonalPerformanceListDtoServiceImpl.EmployeePersonalPerformanceListDtoReqQuery.class, convert=EmployeePersonalPerformanceListDtoServiceImpl.EmployeePersonalPerformanceListDtoRspConvert.class)
    //员工项目经历个人表现List查询
    public ResponseEntity<ResponseMessage<EmployeePersonalPerformanceListDtoQueryRsp>> employeePersonalPerformanceListDtoQuery(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListDtoQueryReq> reqMsg){
        ResponseMessage<EmployeePersonalPerformanceListDtoQueryRsp> rspMsg = null;
        try {
            EmployeePersonalPerformanceListDtoQueryReq request = reqMsg.getMessage();
            
            EmployeePersonalPerformanceListDtoQueryRsp response = employeePersonalPerformanceListDtoServiceImpl.employeePersonalPerformanceListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeePersonalPerformanceListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeePersonalPerformanceListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历个人表现List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeePersonalPerformanceListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工项目经历个人表现List保存
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceListDtoSave(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeePersonalPerformanceListDtoSaveReq request = reqMsg.getMessage();
            
            employeePersonalPerformanceListDtoServiceImpl.employeePersonalPerformanceListDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历个人表现List保存："+ reqMsg.toString(), e);
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
    //员工项目经历个人表现List删除
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceListDtoDelete(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListDtoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeePersonalPerformanceListDtoDeleteReq request = reqMsg.getMessage();
            
            employeePersonalPerformanceListDtoServiceImpl.employeePersonalPerformanceListDtoDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历个人表现List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
