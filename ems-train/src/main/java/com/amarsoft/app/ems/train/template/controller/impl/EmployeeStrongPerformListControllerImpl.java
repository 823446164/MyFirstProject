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
import com.amarsoft.app.ems.train.template.controller.EmployeeStrongPerformListController;
import com.amarsoft.app.ems.train.template.service.EmployeeStrongPerformListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.train.template.service.impl.EmployeeStrongPerformListServiceImpl;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformListDeleteReq;

/**
 * 员工强化培训表现Controller实现类
 * @author xphe
 */
@Slf4j
@RestController
public class EmployeeStrongPerformListControllerImpl implements EmployeeStrongPerformListController {
    @Autowired
    EmployeeStrongPerformListService employeeStrongPerformListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工强化培训表现", query = EmployeeStrongPerformListServiceImpl.EmployeeStrongPerformListReqQuery.class, convert=EmployeeStrongPerformListServiceImpl.EmployeeStrongPerformListRspConvert.class)
    //员工强化培训表现查询
    public ResponseEntity<ResponseMessage<EmployeeStrongPerformListQueryRsp>> employeeStrongPerformListQuery(@RequestBody @Valid RequestMessage<EmployeeStrongPerformListQueryReq> reqMsg){
        ResponseMessage<EmployeeStrongPerformListQueryRsp> rspMsg = null;
        try {
            EmployeeStrongPerformListQueryReq request = reqMsg.getMessage();
            
            EmployeeStrongPerformListQueryRsp response = employeeStrongPerformListServiceImpl.employeeStrongPerformListQuery(request);
            rspMsg = new ResponseMessage<EmployeeStrongPerformListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeStrongPerformListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工强化培训表现查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeStrongPerformListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工强化培训表现删除
    public ResponseEntity<ResponseMessage<Object>> employeeStrongPerformListDelete(@RequestBody @Valid RequestMessage<EmployeeStrongPerformListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeStrongPerformListDeleteReq request = reqMsg.getMessage();
            
            employeeStrongPerformListServiceImpl.employeeStrongPerformListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工强化培训表现删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
