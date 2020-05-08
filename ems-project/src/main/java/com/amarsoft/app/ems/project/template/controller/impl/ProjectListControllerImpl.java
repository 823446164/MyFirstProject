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
import com.amarsoft.app.ems.project.template.controller.ProjectListController;
import com.amarsoft.app.ems.project.template.service.ProjectListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.project.template.service.impl.ProjectListServiceImpl;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListSaveReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListDeleteReq;

/**
 * 项目列表Controller实现类
 * @author hpli
 */
@Slf4j
@RestController
public class ProjectListControllerImpl implements ProjectListController {
    @Autowired
    ProjectListService projectListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="项目列表", query = ProjectListServiceImpl.ProjectListReqQuery.class, convert=ProjectListServiceImpl.ProjectListRspConvert.class)
    //项目列表查询
    public ResponseEntity<ResponseMessage<ProjectListQueryRsp>> projectListQuery(@RequestBody @Valid RequestMessage<ProjectListQueryReq> reqMsg){
        ResponseMessage<ProjectListQueryRsp> rspMsg = null;
        try {
            ProjectListQueryReq request = reqMsg.getMessage();
            
            ProjectListQueryRsp response = projectListServiceImpl.projectListQuery(request);
            rspMsg = new ResponseMessage<ProjectListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<ProjectListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("项目列表查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<ProjectListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //项目列表保存
    public ResponseEntity<ResponseMessage<Object>> projectListSave(@RequestBody @Valid RequestMessage<ProjectListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            ProjectListSaveReq request = reqMsg.getMessage();
            
            projectListServiceImpl.projectListSave(request);
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

    @Override
    @Transactional
    //项目列表删除
    public ResponseEntity<ResponseMessage<Object>> projectListDelete(@RequestBody @Valid RequestMessage<ProjectListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            ProjectListDeleteReq request = reqMsg.getMessage();
            
            projectListServiceImpl.projectListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("项目列表删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
