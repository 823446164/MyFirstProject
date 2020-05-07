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
import com.amarsoft.app.ems.project.template.controller.ProjectInfoController;
import com.amarsoft.app.ems.project.template.service.ProjectInfoService;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfoQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfoQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfoSaveReq;

/**
 * 项目列表Controller实现类
 * @author hpli
 */
@Slf4j
@RestController
public class ProjectInfoControllerImpl implements ProjectInfoController {
    @Autowired
    ProjectInfoService projectInfoServiceImpl;
    
    @Override
    @Transactional
    //项目列表查询
    public ResponseEntity<ResponseMessage<ProjectInfoQueryRsp>> projectInfoQuery(@RequestBody @Valid RequestMessage<ProjectInfoQueryReq> reqMsg){
        ResponseMessage<ProjectInfoQueryRsp> rspMsg = null;
        try {
            ProjectInfoQueryReq request = reqMsg.getMessage();
            
            ProjectInfoQueryRsp response = projectInfoServiceImpl.projectInfoQuery(request);
            rspMsg = new ResponseMessage<ProjectInfoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<ProjectInfoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("项目列表查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<ProjectInfoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //项目列表保存
    public ResponseEntity<ResponseMessage<Object>> projectInfoSave(@RequestBody @Valid RequestMessage<ProjectInfoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            ProjectInfoSaveReq request = reqMsg.getMessage();
            
            projectInfoServiceImpl.projectInfoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("项目列表保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
