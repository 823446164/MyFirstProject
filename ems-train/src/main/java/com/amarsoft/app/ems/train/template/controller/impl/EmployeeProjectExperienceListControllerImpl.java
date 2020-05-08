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
import com.amarsoft.app.ems.train.template.controller.EmployeeProjectExperienceListController;
import com.amarsoft.app.ems.train.template.service.EmployeeProjectExperienceListService;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.train.template.service.impl.EmployeeProjectExperienceListServiceImpl;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceListDeleteReq;

/**
 * 员工参与培训项目列表Controller实现类
 * @author xphe
 */
@Slf4j
@RestController
public class EmployeeProjectExperienceListControllerImpl implements EmployeeProjectExperienceListController {
    @Autowired
    EmployeeProjectExperienceListService employeeProjectExperienceListServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工参与培训项目列表", query = EmployeeProjectExperienceListServiceImpl.EmployeeProjectExperienceListReqQuery.class, convert=EmployeeProjectExperienceListServiceImpl.EmployeeProjectExperienceListRspConvert.class)
    //员工参与培训项目列表查询
    public ResponseEntity<ResponseMessage<EmployeeProjectExperienceListQueryRsp>> employeeProjectExperienceListQuery(@RequestBody @Valid RequestMessage<EmployeeProjectExperienceListQueryReq> reqMsg){
        ResponseMessage<EmployeeProjectExperienceListQueryRsp> rspMsg = null;
        try {
            EmployeeProjectExperienceListQueryReq request = reqMsg.getMessage();
            
            EmployeeProjectExperienceListQueryRsp response = employeeProjectExperienceListServiceImpl.employeeProjectExperienceListQuery(request);
            rspMsg = new ResponseMessage<EmployeeProjectExperienceListQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeProjectExperienceListQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工参与培训项目列表查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeProjectExperienceListQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工参与培训项目列表删除
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExperienceListDelete(@RequestBody @Valid RequestMessage<EmployeeProjectExperienceListDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeProjectExperienceListDeleteReq request = reqMsg.getMessage();
            
            employeeProjectExperienceListServiceImpl.employeeProjectExperienceListDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工参与培训项目列表删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
