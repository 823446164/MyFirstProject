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
import com.amarsoft.app.ems.demo.template.controller.EmployeePersonalPerformanceListController;
import com.amarsoft.app.ems.demo.template.service.EmployeePersonalPerformanceListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.demo.template.service.impl.EmployeePersonalPerformanceListServiceImpl;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeepersonalperformancelist.EmployeePersonalPerformanceListDeleteReq;

/**
 * 员工项目经历个人表现ListController实现类
 * @author jfan5
 */
@Slf4j
@RestController
public class EmployeePersonalPerformanceListControllerImpl implements EmployeePersonalPerformanceListController {
    @Autowired
    EmployeePersonalPerformanceListService employeePersonalPerformanceListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工项目经历个人表现List", query = EmployeePersonalPerformanceListServiceImpl.EmployeePersonalPerformanceListReqQuery.class, convert=EmployeePersonalPerformanceListServiceImpl.EmployeePersonalPerformanceListRspConvert.class)
    //员工项目经历个人表现List查询
    public ResponseEntity<ResponseMessage<EmployeePersonalPerformanceListQueryRsp>> employeePersonalPerformanceListQuery(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListQueryReq> reqMsg){
        ResponseMessage<EmployeePersonalPerformanceListQueryRsp> rspMsg = null;
        try {
            EmployeePersonalPerformanceListQueryReq request = reqMsg.getMessage();
            
            EmployeePersonalPerformanceListQueryRsp response = employeePersonalPerformanceListServiceImpl.employeePersonalPerformanceListQuery(request);
            rspMsg = new ResponseMessage<EmployeePersonalPerformanceListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeePersonalPerformanceListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历个人表现List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeePersonalPerformanceListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工项目经历个人表现List保存
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceListSave(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeePersonalPerformanceListSaveReq request = reqMsg.getMessage();
            
            employeePersonalPerformanceListServiceImpl.employeePersonalPerformanceListSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历个人表现List保存："+ reqMsg.toString(), e);
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
    //员工项目经历个人表现List删除
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceListDelete(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeePersonalPerformanceListDeleteReq request = reqMsg.getMessage();
            
            employeePersonalPerformanceListServiceImpl.employeePersonalPerformanceListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历个人表现List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
