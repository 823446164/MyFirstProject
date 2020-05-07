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
import com.amarsoft.app.ems.train.template.controller.EmployeeJobPartorListController;
import com.amarsoft.app.ems.train.template.service.EmployeeJobPartorListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.train.template.service.impl.EmployeeJobPartorListServiceImpl;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListDeleteReq;

/**
 * 在职培训参与人员列表Controller实现类
 * @author xphe
 */
@Slf4j
@RestController
public class EmployeeJobPartorListControllerImpl implements EmployeeJobPartorListController {
    @Autowired
    EmployeeJobPartorListService employeeJobPartorListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="在职培训参与人员列表", query = EmployeeJobPartorListServiceImpl.EmployeeJobPartorListReqQuery.class, convert=EmployeeJobPartorListServiceImpl.EmployeeJobPartorListRspConvert.class)
    //在职培训参与人员列表查询
    public ResponseEntity<ResponseMessage<EmployeeJobPartorListQueryRsp>> employeeJobPartorListQuery(@RequestBody @Valid RequestMessage<EmployeeJobPartorListQueryReq> reqMsg){
        ResponseMessage<EmployeeJobPartorListQueryRsp> rspMsg = null;
        try {
            EmployeeJobPartorListQueryReq request = reqMsg.getMessage();
            
            EmployeeJobPartorListQueryRsp response = employeeJobPartorListServiceImpl.employeeJobPartorListQuery(request);
            rspMsg = new ResponseMessage<EmployeeJobPartorListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeJobPartorListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("在职培训参与人员列表查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeJobPartorListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //在职培训参与人员列表保存
    public ResponseEntity<ResponseMessage<Object>> employeeJobPartorListSave(@RequestBody @Valid RequestMessage<EmployeeJobPartorListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeJobPartorListSaveReq request = reqMsg.getMessage();
            
            employeeJobPartorListServiceImpl.employeeJobPartorListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("在职培训参与人员列表保存："+ reqMsg.toString(), e);
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
    //在职培训参与人员列表删除
    public ResponseEntity<ResponseMessage<Object>> employeeJobPartorListDelete(@RequestBody @Valid RequestMessage<EmployeeJobPartorListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeJobPartorListDeleteReq request = reqMsg.getMessage();
            
            employeeJobPartorListServiceImpl.employeeJobPartorListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("在职培训参与人员列表删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
