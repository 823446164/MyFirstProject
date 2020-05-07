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
import com.amarsoft.app.ems.project.template.controller.ProjectEmployeeListController;
import com.amarsoft.app.ems.project.template.service.ProjectEmployeeListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.project.template.service.impl.ProjectEmployeeListServiceImpl;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListSaveReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListDeleteReq;

/**
 * 项目组人员信息Controller实现类
 * @author hpli
 */
@Slf4j
@RestController
public class ProjectEmployeeListControllerImpl implements ProjectEmployeeListController {
    @Autowired
    ProjectEmployeeListService projectEmployeeListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="项目组人员信息", query = ProjectEmployeeListServiceImpl.ProjectEmployeeListReqQuery.class, convert=ProjectEmployeeListServiceImpl.ProjectEmployeeListRspConvert.class)
    //项目组人员信息查询
    public ResponseEntity<ResponseMessage<ProjectEmployeeListQueryRsp>> projectEmployeeListQuery(@RequestBody @Valid RequestMessage<ProjectEmployeeListQueryReq> reqMsg){
        ResponseMessage<ProjectEmployeeListQueryRsp> rspMsg = null;
        try {
            ProjectEmployeeListQueryReq request = reqMsg.getMessage();
            
            ProjectEmployeeListQueryRsp response = projectEmployeeListServiceImpl.projectEmployeeListQuery(request);
            rspMsg = new ResponseMessage<ProjectEmployeeListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<ProjectEmployeeListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("项目组人员信息查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<ProjectEmployeeListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //项目组人员信息保存
    public ResponseEntity<ResponseMessage<Object>> projectEmployeeListSave(@RequestBody @Valid RequestMessage<ProjectEmployeeListSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            ProjectEmployeeListSaveReq request = reqMsg.getMessage();
            
            projectEmployeeListServiceImpl.projectEmployeeListSave(request);
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

    @Override
    @Transactional
    //项目组人员信息删除
    public ResponseEntity<ResponseMessage<Object>> projectEmployeeListDelete(@RequestBody @Valid RequestMessage<ProjectEmployeeListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            ProjectEmployeeListDeleteReq request = reqMsg.getMessage();
            
            projectEmployeeListServiceImpl.projectEmployeeListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("项目组人员信息删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
