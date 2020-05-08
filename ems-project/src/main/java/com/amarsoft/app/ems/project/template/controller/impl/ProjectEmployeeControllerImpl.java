package com.amarsoft.app.ems.project.template.controller.impl;

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
import com.amarsoft.app.ems.project.template.controller.ProjectEmployeeController;
import com.amarsoft.app.ems.project.template.service.ProjectEmployeeService;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployeeQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployeeQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployeeSaveReq;

/**
 * 项目组人员信息Controller实现类
 * @author hpli
 */
@Slf4j
@RestController
public class ProjectEmployeeControllerImpl implements ProjectEmployeeController {
    @Autowired
    ProjectEmployeeService projectEmployeeServiceImpl;
    
    @Override
    @Transactional
    //项目组人员信息查询
    public ResponseEntity<ResponseMessage<ProjectEmployeeQueryRsp>> projectEmployeeQuery(@RequestBody @Valid RequestMessage<ProjectEmployeeQueryReq> reqMsg){
        ResponseMessage<ProjectEmployeeQueryRsp> rspMsg = null;
        try {
            ProjectEmployeeQueryReq request = reqMsg.getMessage();
            
            ProjectEmployeeQueryRsp response = projectEmployeeServiceImpl.projectEmployeeQuery(request);
            rspMsg = new ResponseMessage<ProjectEmployeeQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<ProjectEmployeeQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("项目组人员信息查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<ProjectEmployeeQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //项目组人员信息保存
    public ResponseEntity<ResponseMessage<Object>> projectEmployeeSave(@RequestBody @Valid RequestMessage<ProjectEmployeeSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            ProjectEmployeeSaveReq request = reqMsg.getMessage();
            
            projectEmployeeServiceImpl.projectEmployeeSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("项目组人员信息保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
