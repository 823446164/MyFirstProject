/*文件名：EmployeeDevelopTargetListDtoControllerImpl 
 * 版权：Copyright by www.amarsoft.com
 * 描述： 
 * 修改人：dxiao 
 * 修改时间：2020/05/09 
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：修改异常信息
 */
package com.amarsoft.app.ems.employee.template.controller.impl;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.employee.template.controller.EmployeeDevelopTargetListDtoController;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.service.EmployeeDevelopTargetListDtoService;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeeDevelopTargetListDtoServiceImpl;

import lombok.extern.slf4j.Slf4j;

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
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "EMS1007",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeDevelopTargetListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    @Transactional
    //员工成长目标跟踪List删除
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListDtoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeDevelopTargetListDtoDeleteReq request = reqMsg.getMessage();
            //接收返回信息
            Map<String, String> response = employeeDevelopTargetListDtoServiceImpl.employeeDevelopTargetListDtoDelete(request);
            rspMsg = new ResponseMessage<Object>(response);

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工成长目标跟踪List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            //异常码设置,补充为EMS1002。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "EMS1002",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
