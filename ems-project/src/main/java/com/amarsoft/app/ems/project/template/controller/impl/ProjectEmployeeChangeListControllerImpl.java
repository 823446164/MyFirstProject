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
import com.amarsoft.app.ems.project.template.controller.ProjectEmployeeChangeListController;
import com.amarsoft.app.ems.project.template.service.ProjectEmployeeChangeListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.project.template.service.impl.ProjectEmployeeChangeListServiceImpl;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListSaveReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListDeleteReq;

/**
 * 项目组人员变更信息Controller实现类
 * @author hpli
 */
@Slf4j
@RestController
public class ProjectEmployeeChangeListControllerImpl implements ProjectEmployeeChangeListController {
    @Autowired
    ProjectEmployeeChangeListService projectEmployeeChangeListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="项目组人员变更信息", query = ProjectEmployeeChangeListServiceImpl.ProjectEmployeeChangeListReqQuery.class, convert=ProjectEmployeeChangeListServiceImpl.ProjectEmployeeChangeListRspConvert.class)
    //项目组人员变更信息查询
    public ResponseEntity<ResponseMessage<ProjectEmployeeChangeListQueryRsp>> projectEmployeeChangeListQuery(@RequestBody @Valid RequestMessage<ProjectEmployeeChangeListQueryReq> reqMsg){
        ResponseMessage<ProjectEmployeeChangeListQueryRsp> rspMsg = null;
        try {
            ProjectEmployeeChangeListQueryReq request = reqMsg.getMessage();
            
            ProjectEmployeeChangeListQueryRsp response = projectEmployeeChangeListServiceImpl.projectEmployeeChangeListQuery(request);
            rspMsg = new ResponseMessage<ProjectEmployeeChangeListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<ProjectEmployeeChangeListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("项目组人员变更信息查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<ProjectEmployeeChangeListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //项目组人员变更信息保存
    public ResponseEntity<ResponseMessage<Object>> projectEmployeeChangeListSave(@RequestBody @Valid RequestMessage<ProjectEmployeeChangeListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            ProjectEmployeeChangeListSaveReq request = reqMsg.getMessage();
            
            projectEmployeeChangeListServiceImpl.projectEmployeeChangeListSave(request);
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

    @Override
    @Transactional
    //项目组人员变更信息删除
    public ResponseEntity<ResponseMessage<Object>> projectEmployeeChangeListDelete(@RequestBody @Valid RequestMessage<ProjectEmployeeChangeListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            ProjectEmployeeChangeListDeleteReq request = reqMsg.getMessage();
            
            projectEmployeeChangeListServiceImpl.projectEmployeeChangeListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("项目组人员变更信息删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
