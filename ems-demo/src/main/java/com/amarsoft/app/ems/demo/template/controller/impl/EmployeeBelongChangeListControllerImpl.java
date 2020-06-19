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
import com.amarsoft.app.ems.demo.template.controller.EmployeeBelongChangeListController;
import com.amarsoft.app.ems.demo.template.service.EmployeeBelongChangeListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.demo.template.service.impl.EmployeeBelongChangeListServiceImpl;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListDeleteReq;

/**
 * 团队调整申请ListController实现类
 * @author jfan5
 */
@Slf4j
@RestController
public class EmployeeBelongChangeListControllerImpl implements EmployeeBelongChangeListController {
    @Autowired
    EmployeeBelongChangeListService employeeBelongChangeListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="团队调整申请List", query = EmployeeBelongChangeListServiceImpl.EmployeeBelongChangeListReqQuery.class, convert=EmployeeBelongChangeListServiceImpl.EmployeeBelongChangeListRspConvert.class)
    //团队调整申请List查询
    public ResponseEntity<ResponseMessage<EmployeeBelongChangeListQueryRsp>> employeeBelongChangeListQuery(@RequestBody @Valid RequestMessage<EmployeeBelongChangeListQueryReq> reqMsg){
        ResponseMessage<EmployeeBelongChangeListQueryRsp> rspMsg = null;
        try {
            EmployeeBelongChangeListQueryReq request = reqMsg.getMessage();
            
            EmployeeBelongChangeListQueryRsp response = employeeBelongChangeListServiceImpl.employeeBelongChangeListQuery(request);
            rspMsg = new ResponseMessage<EmployeeBelongChangeListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeBelongChangeListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("团队调整申请List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeBelongChangeListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //团队调整申请List保存
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeListSave(@RequestBody @Valid RequestMessage<EmployeeBelongChangeListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeBelongChangeListSaveReq request = reqMsg.getMessage();
            
            employeeBelongChangeListServiceImpl.employeeBelongChangeListSave(request);
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
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeListDelete(@RequestBody @Valid RequestMessage<EmployeeBelongChangeListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeBelongChangeListDeleteReq request = reqMsg.getMessage();
            
            employeeBelongChangeListServiceImpl.employeeBelongChangeListDelete(request);
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
