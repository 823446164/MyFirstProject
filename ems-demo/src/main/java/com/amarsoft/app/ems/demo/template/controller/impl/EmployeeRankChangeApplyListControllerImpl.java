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
import com.amarsoft.app.ems.demo.template.controller.EmployeeRankChangeApplyListController;
import com.amarsoft.app.ems.demo.template.service.EmployeeRankChangeApplyListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.demo.template.service.impl.EmployeeRankChangeApplyListServiceImpl;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListDeleteReq;

/**
 * 员工职级调整申请ListController实现类
 * @author jfan5
 */
@Slf4j
@RestController
public class EmployeeRankChangeApplyListControllerImpl implements EmployeeRankChangeApplyListController {
    @Autowired
    EmployeeRankChangeApplyListService employeeRankChangeApplyListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工职级调整申请List", query = EmployeeRankChangeApplyListServiceImpl.EmployeeRankChangeApplyListReqQuery.class, convert=EmployeeRankChangeApplyListServiceImpl.EmployeeRankChangeApplyListRspConvert.class)
    //员工职级调整申请List查询
    public ResponseEntity<ResponseMessage<EmployeeRankChangeApplyListQueryRsp>> employeeRankChangeApplyListQuery(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyListQueryReq> reqMsg){
        ResponseMessage<EmployeeRankChangeApplyListQueryRsp> rspMsg = null;
        try {
            EmployeeRankChangeApplyListQueryReq request = reqMsg.getMessage();
            
            EmployeeRankChangeApplyListQueryRsp response = employeeRankChangeApplyListServiceImpl.employeeRankChangeApplyListQuery(request);
            rspMsg = new ResponseMessage<EmployeeRankChangeApplyListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeRankChangeApplyListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整申请List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeRankChangeApplyListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工职级调整申请List保存
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeApplyListSave(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankChangeApplyListSaveReq request = reqMsg.getMessage();
            
            employeeRankChangeApplyListServiceImpl.employeeRankChangeApplyListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整申请List保存："+ reqMsg.toString(), e);
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
    //员工职级调整申请List删除
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeApplyListDelete(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankChangeApplyListDeleteReq request = reqMsg.getMessage();
            
            employeeRankChangeApplyListServiceImpl.employeeRankChangeApplyListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整申请List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
