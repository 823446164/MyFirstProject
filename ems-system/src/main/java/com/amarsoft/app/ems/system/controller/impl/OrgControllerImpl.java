package com.amarsoft.app.ems.system.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.OrgController;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryReq;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.coperateorganizationquery.CoperateOrganizationQueryReq;
import com.amarsoft.app.ems.system.cs.dto.coperateorganizationquery.CoperateOrganizationQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgidquery.OrgIdQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgidquery.OrgIdQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfoadd.OrgInfoAddReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoallquery.OrgInfoAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoallquery.OrgInfoAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfodelete.OrgInfoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfoupdate.OrgInfoUpdateReq;
import com.amarsoft.app.ems.system.cs.dto.orgtreequery.OrgTreeQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryRsp;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 机构服务的处理类
 * @author xxu1
 * 
 * @ModefiedBy hzhang23
 */
@Slf4j
@RestController
public class OrgControllerImpl implements OrgController {
    
    @Autowired
    OrgService orgService;
    
    @Autowired
    UserService userService;
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<OrgInfoAllQueryRsp>> orgInfoAllQuery(@RequestBody @Valid RequestMessage<OrgInfoAllQueryReq> reqMsg){
        try {
            OrgInfoAllQueryRsp responseList = orgService.getOrgAll(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<OrgInfoAllQueryRsp>>(new ResponseMessage<OrgInfoAllQueryRsp>(responseList), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<OrgInfoAllQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900201", e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgInfoAllQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<OrgInfoQueryRsp>> orgInfoQuery(@RequestBody @Valid RequestMessage<OrgInfoQueryReq> reqMsg){
        try {
            OrgInfoQueryRsp response = orgService.getOrgInfo(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<OrgInfoQueryRsp>>(new ResponseMessage<OrgInfoQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<OrgInfoQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900201", e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgInfoQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  

    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> orgInfoUpdate(@RequestBody @Valid RequestMessage<OrgInfoUpdateReq> reqMsg){
        try {
            orgService.setOrgInfo(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900202", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> orgInfoAdd(@RequestBody @Valid RequestMessage<OrgInfoAddReq> reqMsg){
        try {
            orgService.addOrgInfo(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900203", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> orgInfoDelete(@RequestBody @Valid RequestMessage<OrgInfoDeleteReq> reqMsg){
        try {
            orgService.deleteOrgInfo(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900205", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<ConditionalOrgsQueryRsp>> conditionalOrgsQuery(@RequestBody @Valid RequestMessage<ConditionalOrgsQueryReq> reqMsg){
        try {
            ConditionalOrgsQueryRsp rsp = orgService.orgInfoQueryByCondition(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<ConditionalOrgsQueryRsp>>(new ResponseMessage<ConditionalOrgsQueryRsp>(rsp), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<ConditionalOrgsQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900201", e.getMessage());
            return new ResponseEntity<ResponseMessage<ConditionalOrgsQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    public ResponseEntity<ResponseMessage<OrgUserQueryRsp>> orgUserQuery(@RequestBody @Valid RequestMessage<OrgUserQueryReq> reqMsg){
        ResponseMessage<OrgUserQueryRsp> rspMsg = null;
        try {
            OrgUserQueryRsp rsp = orgService.orgUserQuery(reqMsg.getMessage(),userService);
            return new ResponseEntity<ResponseMessage<OrgUserQueryRsp>>(new ResponseMessage<OrgUserQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询机构用户请求报文："+ reqMsg.toString(), e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900209",e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgUserQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<OrgTreeQueryRsp>> orgTreeQuery(){
        ResponseMessage<OrgTreeQueryRsp> rspMsg = null;
        try {
            OrgTreeQueryRsp rsp = orgService.orgTreeQuery();
            return new ResponseEntity<ResponseMessage<OrgTreeQueryRsp>>(new ResponseMessage<OrgTreeQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询机构树图请求报文：",e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900211",e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgTreeQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<ResponseMessage<OrgIdQueryRsp>> orgIdQuery(@RequestBody @Valid RequestMessage<OrgIdQueryReq> reqMsg){
        ResponseMessage<OrgIdQueryRsp> rspMsg = null;
        try {
            OrgIdQueryRsp rsp = orgService.getOrgId(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<OrgIdQueryRsp>>(new ResponseMessage<OrgIdQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("获取机构编号请求报文：",e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900212",e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgIdQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<ResponseMessage<CoperateOrganizationQueryRsp>> coperateOrganizationQuery(@RequestBody @Valid RequestMessage<CoperateOrganizationQueryReq> reqMsg){
        try {
            CoperateOrganizationQueryRsp rsp= orgService.getCoperateOrganization(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<CoperateOrganizationQueryRsp>>(new ResponseMessage<CoperateOrganizationQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询法人机构请求报文：",e);
            }
            ResponseMessage<CoperateOrganizationQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900213", e.getMessage());
            return new ResponseEntity<ResponseMessage<CoperateOrganizationQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}