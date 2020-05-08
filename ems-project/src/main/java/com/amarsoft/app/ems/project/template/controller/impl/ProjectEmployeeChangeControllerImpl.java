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
import com.amarsoft.app.ems.project.template.controller.ProjectEmployeeChangeController;
import com.amarsoft.app.ems.project.template.service.ProjectEmployeeChangeService;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChangeQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChangeQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChangeSaveReq;

/**
 * 项目组人员变更信息Controller实现类
 * @author hpli
 */
@Slf4j
@RestController
public class ProjectEmployeeChangeControllerImpl implements ProjectEmployeeChangeController {
    @Autowired
    ProjectEmployeeChangeService projectEmployeeChangeServiceImpl;
    
    @Override
    @Transactional
    //项目组人员变更信息查询
    public ResponseEntity<ResponseMessage<ProjectEmployeeChangeQueryRsp>> projectEmployeeChangeQuery(@RequestBody @Valid RequestMessage<ProjectEmployeeChangeQueryReq> reqMsg){
        ResponseMessage<ProjectEmployeeChangeQueryRsp> rspMsg = null;
        try {
            ProjectEmployeeChangeQueryReq request = reqMsg.getMessage();
            
            ProjectEmployeeChangeQueryRsp response = projectEmployeeChangeServiceImpl.projectEmployeeChangeQuery(request);
            rspMsg = new ResponseMessage<ProjectEmployeeChangeQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<ProjectEmployeeChangeQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("项目组人员变更信息查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<ProjectEmployeeChangeQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //项目组人员变更信息保存
    public ResponseEntity<ResponseMessage<Object>> projectEmployeeChangeSave(@RequestBody @Valid RequestMessage<ProjectEmployeeChangeSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            ProjectEmployeeChangeSaveReq request = reqMsg.getMessage();
            
            projectEmployeeChangeServiceImpl.projectEmployeeChangeSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("项目组人员变更信息保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
