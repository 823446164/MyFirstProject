/*
 * 文件名：TeamController
 * 版权：Copyright by www.amarsoft.com
 * 描述：团队信息controller实现类
 * 修改人：xszhou
 * 修改时间：2020/5/9
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.controller.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.TeamController;
import com.amarsoft.app.ems.system.cs.dto.addteam.AddTeamReq;
import com.amarsoft.app.ems.system.cs.dto.addteamuser.AddTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.deleteteam.DeleteTeamReq;
import com.amarsoft.app.ems.system.cs.dto.deleteteamuser.DeleteTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.getteamid.GetTeamIdRsp;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.transferteam.TransferTeamReq;
import com.amarsoft.app.ems.system.cs.dto.updateteam.UpdateTeamReq;
import com.amarsoft.app.ems.system.cs.dto.updateuserteam.UpdateUserTeamReq;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.TeamService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TeamControllerImpl implements TeamController {

    @Autowired
    TeamService teamService;
    
    @Autowired
    OrgService orgService;
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> addTeam(@RequestBody @Valid RequestMessage<AddTeamReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            teamService.addTeam(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("新增团队信息请求报文："+ reqMsg.toString(), e);
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901001",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> updateTeam(@RequestBody @Valid RequestMessage<UpdateTeamReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            teamService.updateTeam(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("更新团队信息请求报文："+ reqMsg.toString(), e);
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901002",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> addTeamUser(@RequestBody @Valid RequestMessage<AddTeamUserReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            teamService.addTeamUser(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("添加团队成员请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901003",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> deleteTeamUser(@RequestBody @Valid RequestMessage<DeleteTeamUserReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            teamService.deleteTeamUser(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("删除团队成员请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901004",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> transferTeam(@RequestHeader() HttpHeaders header,@RequestBody @Valid RequestMessage<TransferTeamReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            teamService.transferTeam(header,reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("转移团队负责人请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901005",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    @Transactional
    @CodeQuery
    public ResponseEntity<ResponseMessage<TeamQueryRsp>> teamQuery(@RequestBody @Valid RequestMessage<TeamQueryReq> reqMsg){
        ResponseMessage<TeamQueryRsp> rspMsg = null;
        try {
            TeamQueryRsp rsp = teamService.teamQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<TeamQueryRsp>>(new ResponseMessage<TeamQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询团队信息请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901010",e.getMessage());
            return new ResponseEntity<ResponseMessage<TeamQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<LevelTeamQueryRsp>> levelTeamQuery(@RequestBody @Valid RequestMessage<LevelTeamQueryReq> reqMsg){
        ResponseMessage<LevelTeamQueryRsp> rspMsg = null;
        try {
            LevelTeamQueryRsp rsp = teamService.levelTeamQuery(reqMsg.getMessage(),teamService,orgService);
            return new ResponseEntity<ResponseMessage<LevelTeamQueryRsp>>(new ResponseMessage<LevelTeamQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询团队信息失败：", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901010",e.getMessage());
            return new ResponseEntity<ResponseMessage<LevelTeamQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> deleteTeam(@RequestBody @Valid RequestMessage<DeleteTeamReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            teamService.deleteTeam(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("删除团队失败：", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901009",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(rspMsg), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<ResponseMessage<GetTeamIdRsp>> getTeamId(){
        ResponseMessage<GetTeamIdRsp> rspMsg = null;
        try {
            GetTeamIdRsp rsp = teamService.getTeamId();
            return new ResponseEntity<ResponseMessage<GetTeamIdRsp>>(new ResponseMessage<GetTeamIdRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("获取团队编号出错：", e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901011",e.getMessage());
            return new ResponseEntity<ResponseMessage<GetTeamIdRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Description: <br>
     * 1、更新员工团队信息<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     *
     * ${tags}
     * @see
     */
	@Override
	public ResponseEntity<ResponseMessage<Object>> updateUserTeam(@RequestBody @Valid RequestMessage<UpdateUserTeamReq> reqMsg) {
		ResponseMessage<Object> rspMsg = null;
        try {
            teamService.updateUserTeam(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("更新员工团队请求报文："+ reqMsg.toString(), e);
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901002",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

    /**
     * Description: <br>
     * 1、部门团队列表展示<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     *
     * ${tags}
     * @see
     */
	@Override
	public ResponseEntity<ResponseMessage<TeamOrgQueryRsp>> teamOrgQuery(@Valid RequestMessage<TeamOrgQueryReq> reqMsg) {
		ResponseMessage<TeamOrgQueryRsp> rspMsg = null;
        try {
        	TeamOrgQueryReq req = reqMsg.getMessage();
        	TeamOrgQueryRsp rsp = teamService.orgTeamListQuery(req);
        	rspMsg = new ResponseMessage<TeamOrgQueryRsp>(rsp);
            return new ResponseEntity<ResponseMessage<TeamOrgQueryRsp>>(rspMsg, HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询部门团队信息失败：", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "901010",e.getMessage());
            return new ResponseEntity<ResponseMessage<TeamOrgQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}