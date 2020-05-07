package com.amarsoft.app.ems.train.template.controller.impl;

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
import com.amarsoft.app.ems.train.template.controller.EmployeeStrongMemberListController;
import com.amarsoft.app.ems.train.template.service.EmployeeStrongMemberListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.train.template.service.impl.EmployeeStrongMemberListServiceImpl;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListDeleteReq;

/**
 * 培训项目参与人员列表Controller实现类
 * @author xphe
 */
@Slf4j
@RestController
public class EmployeeStrongMemberListControllerImpl implements EmployeeStrongMemberListController {
    @Autowired
    EmployeeStrongMemberListService employeeStrongMemberListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="培训项目参与人员列表", query = EmployeeStrongMemberListServiceImpl.EmployeeStrongMemberListReqQuery.class, convert=EmployeeStrongMemberListServiceImpl.EmployeeStrongMemberListRspConvert.class)
    //培训项目参与人员列表查询
    public ResponseEntity<ResponseMessage<EmployeeStrongMemberListQueryRsp>> employeeStrongMemberListQuery(@RequestBody @Valid RequestMessage<EmployeeStrongMemberListQueryReq> reqMsg){
        ResponseMessage<EmployeeStrongMemberListQueryRsp> rspMsg = null;
        try {
            EmployeeStrongMemberListQueryReq request = reqMsg.getMessage();
            
            EmployeeStrongMemberListQueryRsp response = employeeStrongMemberListServiceImpl.employeeStrongMemberListQuery(request);
            rspMsg = new ResponseMessage<EmployeeStrongMemberListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeStrongMemberListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("培训项目参与人员列表查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeStrongMemberListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //培训项目参与人员列表保存
    public ResponseEntity<ResponseMessage<Object>> employeeStrongMemberListSave(@RequestBody @Valid RequestMessage<EmployeeStrongMemberListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeStrongMemberListSaveReq request = reqMsg.getMessage();
            
            employeeStrongMemberListServiceImpl.employeeStrongMemberListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("培训项目参与人员列表保存："+ reqMsg.toString(), e);
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
    //培训项目参与人员列表删除
    public ResponseEntity<ResponseMessage<Object>> employeeStrongMemberListDelete(@RequestBody @Valid RequestMessage<EmployeeStrongMemberListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeStrongMemberListDeleteReq request = reqMsg.getMessage();
            
            employeeStrongMemberListServiceImpl.employeeStrongMemberListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("培训项目参与人员列表删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
