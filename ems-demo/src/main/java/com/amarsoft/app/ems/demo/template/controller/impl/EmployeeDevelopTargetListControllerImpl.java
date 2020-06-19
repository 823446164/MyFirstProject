package com.amarsoft.app.ems.demo.template.controller.impl;

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
import com.amarsoft.app.ems.demo.template.controller.EmployeeDevelopTargetListController;
import com.amarsoft.app.ems.demo.template.service.EmployeeDevelopTargetListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.demo.template.service.impl.EmployeeDevelopTargetListServiceImpl;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeedeveloptargetlist.EmployeeDevelopTargetListDeleteReq;

/**
 * 员工成长目标跟踪ListController实现类
 * @author jfan5
 */
@Slf4j
@RestController
public class EmployeeDevelopTargetListControllerImpl implements EmployeeDevelopTargetListController {
    @Autowired
    EmployeeDevelopTargetListService employeeDevelopTargetListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工成长目标跟踪List", query = EmployeeDevelopTargetListServiceImpl.EmployeeDevelopTargetListReqQuery.class, convert=EmployeeDevelopTargetListServiceImpl.EmployeeDevelopTargetListRspConvert.class)
    //员工成长目标跟踪List查询
    public ResponseEntity<ResponseMessage<EmployeeDevelopTargetListQueryRsp>> employeeDevelopTargetListQuery(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListQueryReq> reqMsg){
        ResponseMessage<EmployeeDevelopTargetListQueryRsp> rspMsg = null;
        try {
            EmployeeDevelopTargetListQueryReq request = reqMsg.getMessage();
            
            EmployeeDevelopTargetListQueryRsp response = employeeDevelopTargetListServiceImpl.employeeDevelopTargetListQuery(request);
            rspMsg = new ResponseMessage<EmployeeDevelopTargetListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeDevelopTargetListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工成长目标跟踪List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeDevelopTargetListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工成长目标跟踪List保存
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetListSave(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeDevelopTargetListSaveReq request = reqMsg.getMessage();
            
            employeeDevelopTargetListServiceImpl.employeeDevelopTargetListSave(request);
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
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetListDelete(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeDevelopTargetListDeleteReq request = reqMsg.getMessage();
            
            employeeDevelopTargetListServiceImpl.employeeDevelopTargetListDelete(request);
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
