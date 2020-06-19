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
import com.amarsoft.app.ems.demo.template.controller.EmployeeProjectExpListController;
import com.amarsoft.app.ems.demo.template.service.EmployeeProjectExpListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.demo.template.service.impl.EmployeeProjectExpListServiceImpl;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist.EmployeeProjectExpListDeleteReq;

/**
 * 员工项目经历ListController实现类
 * @author jfan5
 */
@Slf4j
@RestController
public class EmployeeProjectExpListControllerImpl implements EmployeeProjectExpListController {
    @Autowired
    EmployeeProjectExpListService employeeProjectExpListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工项目经历List", query = EmployeeProjectExpListServiceImpl.EmployeeProjectExpListReqQuery.class, convert=EmployeeProjectExpListServiceImpl.EmployeeProjectExpListRspConvert.class)
    //员工项目经历List查询
    public ResponseEntity<ResponseMessage<EmployeeProjectExpListQueryRsp>> employeeProjectExpListQuery(@RequestBody @Valid RequestMessage<EmployeeProjectExpListQueryReq> reqMsg){
        ResponseMessage<EmployeeProjectExpListQueryRsp> rspMsg = null;
        try {
            EmployeeProjectExpListQueryReq request = reqMsg.getMessage();
            
            EmployeeProjectExpListQueryRsp response = employeeProjectExpListServiceImpl.employeeProjectExpListQuery(request);
            rspMsg = new ResponseMessage<EmployeeProjectExpListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeProjectExpListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeProjectExpListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工项目经历List保存
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExpListSave(@RequestBody @Valid RequestMessage<EmployeeProjectExpListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeProjectExpListSaveReq request = reqMsg.getMessage();
            
            employeeProjectExpListServiceImpl.employeeProjectExpListSave(request);
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
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExpListDelete(@RequestBody @Valid RequestMessage<EmployeeProjectExpListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeProjectExpListDeleteReq request = reqMsg.getMessage();
            
            employeeProjectExpListServiceImpl.employeeProjectExpListDelete(request);
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
