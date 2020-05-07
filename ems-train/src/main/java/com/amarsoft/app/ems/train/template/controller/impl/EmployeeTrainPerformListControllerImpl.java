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
import com.amarsoft.app.ems.train.template.controller.EmployeeTrainPerformListController;
import com.amarsoft.app.ems.train.template.service.EmployeeTrainPerformListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.train.template.service.impl.EmployeeTrainPerformListServiceImpl;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformListDeleteReq;

/**
 * 员工基础培训表现Controller实现类
 * @author xphe
 */
@Slf4j
@RestController
public class EmployeeTrainPerformListControllerImpl implements EmployeeTrainPerformListController {
    @Autowired
    EmployeeTrainPerformListService employeeTrainPerformListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工基础培训表现", query = EmployeeTrainPerformListServiceImpl.EmployeeTrainPerformListReqQuery.class, convert=EmployeeTrainPerformListServiceImpl.EmployeeTrainPerformListRspConvert.class)
    //员工基础培训表现查询
    public ResponseEntity<ResponseMessage<EmployeeTrainPerformListQueryRsp>> employeeTrainPerformListQuery(@RequestBody @Valid RequestMessage<EmployeeTrainPerformListQueryReq> reqMsg){
        ResponseMessage<EmployeeTrainPerformListQueryRsp> rspMsg = null;
        try {
            EmployeeTrainPerformListQueryReq request = reqMsg.getMessage();
            
            EmployeeTrainPerformListQueryRsp response = employeeTrainPerformListServiceImpl.employeeTrainPerformListQuery(request);
            rspMsg = new ResponseMessage<EmployeeTrainPerformListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeTrainPerformListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工基础培训表现查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeTrainPerformListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工基础培训表现删除
    public ResponseEntity<ResponseMessage<Object>> employeeTrainPerformListDelete(@RequestBody @Valid RequestMessage<EmployeeTrainPerformListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeTrainPerformListDeleteReq request = reqMsg.getMessage();
            
            employeeTrainPerformListServiceImpl.employeeTrainPerformListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工基础培训表现删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
