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
import com.amarsoft.app.ems.train.template.controller.EmployeeJobTrainListController;
import com.amarsoft.app.ems.train.template.service.EmployeeJobTrainListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.train.template.service.impl.EmployeeJobTrainListServiceImpl;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListDeleteReq;

/**
 * 在职培训列表Controller实现类
 * @author xphe
 */
@Slf4j
@RestController
public class EmployeeJobTrainListControllerImpl implements EmployeeJobTrainListController {
    @Autowired
    EmployeeJobTrainListService employeeJobTrainListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="在职培训列表", query = EmployeeJobTrainListServiceImpl.EmployeeJobTrainListReqQuery.class, convert=EmployeeJobTrainListServiceImpl.EmployeeJobTrainListRspConvert.class)
    //在职培训列表查询
    public ResponseEntity<ResponseMessage<EmployeeJobTrainListQueryRsp>> employeeJobTrainListQuery(@RequestBody @Valid RequestMessage<EmployeeJobTrainListQueryReq> reqMsg){
        ResponseMessage<EmployeeJobTrainListQueryRsp> rspMsg = null;
        try {
            EmployeeJobTrainListQueryReq request = reqMsg.getMessage();
            
            EmployeeJobTrainListQueryRsp response = employeeJobTrainListServiceImpl.employeeJobTrainListQuery(request);
            rspMsg = new ResponseMessage<EmployeeJobTrainListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeJobTrainListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("在职培训列表查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeJobTrainListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //在职培训列表保存
    public ResponseEntity<ResponseMessage<Object>> employeeJobTrainListSave(@RequestBody @Valid RequestMessage<EmployeeJobTrainListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeJobTrainListSaveReq request = reqMsg.getMessage();
            
            employeeJobTrainListServiceImpl.employeeJobTrainListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("在职培训列表保存："+ reqMsg.toString(), e);
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
    //在职培训列表删除
    public ResponseEntity<ResponseMessage<Object>> employeeJobTrainListDelete(@RequestBody @Valid RequestMessage<EmployeeJobTrainListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeJobTrainListDeleteReq request = reqMsg.getMessage();
            
            employeeJobTrainListServiceImpl.employeeJobTrainListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("在职培训列表删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
