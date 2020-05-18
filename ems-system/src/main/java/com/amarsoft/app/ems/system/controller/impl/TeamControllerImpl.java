/*
 * 文件名：团队管理控制层
 * 版权：Copyright by www.amarsoft.com
 * 描述：团队信息controller实现类
 * 修改人：hpli
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
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.system.controller.TeamController;
import com.amarsoft.app.ems.system.cs.dto.addteam.AddTeamReq;
import com.amarsoft.app.ems.system.cs.dto.addteamuser.AddTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.deleteteam.DeleteTeamReq;
import com.amarsoft.app.ems.system.cs.dto.deleteteamuser.DeleteTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryRsp;


import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoSaveReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.EmployeeQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.EmployeeQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryRsp;

import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.transferteam.TransferTeamReq;

import com.amarsoft.app.ems.system.cs.dto.updateuserteam.UpdateUserTeamReq;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryRsp;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.TeamInfoDtoService;
import com.amarsoft.app.ems.system.service.TeamListDtoService;
import com.amarsoft.app.ems.system.service.TeamService;
import com.amarsoft.app.ems.system.service.impl.TeamListDtoServiceImpl;
import com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto.DeleteInfoDtoQueryReq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TeamControllerImpl implements TeamController {

	@Autowired
	TeamService teamServiceImpl;
	@Autowired
	TeamInfoDtoService teamInfoDtoServiceImpl;
	@Autowired
	TeamListDtoService teamListDtoServiceImpl;
	@Autowired
	OrgService orgService;

	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<Object>> addTeam(@RequestBody @Valid RequestMessage<AddTeamReq> reqMsg) {
		ResponseMessage<Object> rspMsg = null;
		try {
			teamServiceImpl.addTeam(reqMsg.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()),
					HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("新增团队信息请求报文：" + reqMsg.toString(), e);
			}
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rspMsg = ResponseMessage.getResponseMessageFromException(e, "901001", e.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<Object>> addTeamUser(
			@RequestBody @Valid RequestMessage<AddTeamUserReq> reqMsg) {
		ResponseMessage<Object> rspMsg = null;
		try {
			teamServiceImpl.addTeamUser(reqMsg.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()),
					HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("添加团队成员请求报文：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rspMsg = ResponseMessage.getResponseMessageFromException(e, "901003", e.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<Object>> deleteTeamUser(
			@RequestBody @Valid RequestMessage<DeleteTeamUserReq> reqMsg) {
		ResponseMessage<Object> rspMsg = null;
		try {
			teamServiceImpl.deleteTeamUser(reqMsg.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()),
					HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("删除团队成员请求报文：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rspMsg = ResponseMessage.getResponseMessageFromException(e, "901004", e.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<Object>> transferTeam(@RequestHeader() HttpHeaders header,
			@RequestBody @Valid RequestMessage<TransferTeamReq> reqMsg) {
		ResponseMessage<Object> rspMsg = null;
		try {
			teamServiceImpl.transferTeam(header, reqMsg.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()),
					HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("转移团队负责人请求报文：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rspMsg = ResponseMessage.getResponseMessageFromException(e, "901005", e.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<TeamQueryRsp>> teamQuery(
			@RequestBody @Valid RequestMessage<TeamQueryReq> reqMsg) {
		ResponseMessage<TeamQueryRsp> rspMsg = null;
		try {
			TeamQueryRsp rsp = teamServiceImpl.teamQuery(reqMsg.getMessage());
			return new ResponseEntity<ResponseMessage<TeamQueryRsp>>(new ResponseMessage<TeamQueryRsp>(rsp),
					HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("查询团队信息请求报文：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rspMsg = ResponseMessage.getResponseMessageFromException(e, "9001001", e.getMessage());
			return new ResponseEntity<ResponseMessage<TeamQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<LevelTeamQueryRsp>> levelTeamQuery(
			@RequestBody @Valid RequestMessage<LevelTeamQueryReq> reqMsg) {
		ResponseMessage<LevelTeamQueryRsp> rspMsg = null;
		try {
			LevelTeamQueryRsp rsp = teamServiceImpl.levelTeamQuery(reqMsg.getMessage(), teamServiceImpl, orgService);
			return new ResponseEntity<ResponseMessage<LevelTeamQueryRsp>>(new ResponseMessage<LevelTeamQueryRsp>(rsp),
					HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("查询团队信息失败：", e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rspMsg = ResponseMessage.getResponseMessageFromException(e, "901010", e.getMessage());
			return new ResponseEntity<ResponseMessage<LevelTeamQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Description: 删除团队信息 <br>
	 * ${tags}
	 * 
	 * @see
	 */

	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<Object>> deleteTeam(
			@RequestBody @Valid RequestMessage<DeleteTeamReq> reqMsg) {
		ResponseMessage<Object> rspMsg = null;
		try {
			teamServiceImpl.deleteTeam(reqMsg.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()),
					HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("删除团队失败：", e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rspMsg = ResponseMessage.getResponseMessageFromException(e, "901009", e.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(rspMsg),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Description: 查询团队信息 <br>
	 * ${tags}
	 * 
	 * @see
	 */

	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<TeamInfoDtoQueryRsp>> teamInfoDtoQuery(
			@RequestBody @Valid RequestMessage<TeamInfoDtoQueryReq> reqMsg) {
		ResponseMessage<TeamInfoDtoQueryRsp> rspMsg = null;
		try {
			TeamInfoDtoQueryReq request = reqMsg.getMessage();

			TeamInfoDtoQueryRsp response = teamInfoDtoServiceImpl.teamInfoDtoQuery(request);
			rspMsg = new ResponseMessage<TeamInfoDtoQueryRsp>(response);

			return new ResponseEntity<ResponseMessage<TeamInfoDtoQueryRsp>>(rspMsg, HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("团队信息查询：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			rspMsg = ResponseMessage.getResponseMessageFromException(e, "901010", e.getMessage());
			return new ResponseEntity<ResponseMessage<TeamInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Description: 保存团队信息 <br>
	 * ${tags}
	 * 
	 * @see
	 */
	@Override
	@Transactional

	public ResponseEntity<ResponseMessage<Object>> teamInfoDtoSave(
			@RequestBody @Valid RequestMessage<TeamInfoDtoSaveReq> reqMsg) {
		ResponseMessage<Object> rspMsg = null;
		try {
			TeamInfoDtoSaveReq request = reqMsg.getMessage();

			teamInfoDtoServiceImpl.teamInfoDtoSave(request);
			rspMsg = new ResponseMessage<Object>();

			return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("团队信息保存：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			rspMsg = ResponseMessage.getResponseMessageFromException(e, "EMS6014", e.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Description: 查询团队信息 <br>
	 * ${tags}
	 * 
	 * @see
	 */
	@Override
	@Transactional
	@TemplateExport(name = "团队信息", query = TeamListDtoServiceImpl.TeamListDtoReqQuery.class, convert = TeamListDtoServiceImpl.TeamListDtoRspConvert.class)
	public ResponseEntity<ResponseMessage<TeamListDtoQueryRsp>> teamListDtoQuery(
			@RequestBody @Valid RequestMessage<TeamListDtoQueryReq> reqMsg) {
		ResponseMessage<TeamListDtoQueryRsp> rspMsg = null;
		try {
			TeamListDtoQueryReq request = reqMsg.getMessage();

			TeamListDtoQueryRsp response = teamListDtoServiceImpl.teamListDtoQuery(request);
			rspMsg = new ResponseMessage<TeamListDtoQueryRsp>(response);

			return new ResponseEntity<ResponseMessage<TeamListDtoQueryRsp>>(rspMsg, HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("团队信息查询：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			rspMsg = ResponseMessage.getResponseMessageFromException(e, "901010", e.getMessage());
			return new ResponseEntity<ResponseMessage<TeamListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Description: 删除团队<br>
	 * ${tags}
	 * 
	 * @see
	 */
	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<Object>> teamListDtoDelete(
			@RequestBody @Valid RequestMessage<DeleteInfoDtoQueryReq> reqMsg) {
		ResponseMessage<Object> rspMsg = null;
		try {
			DeleteInfoDtoQueryReq request = reqMsg.getMessage();

			teamListDtoServiceImpl.teamListDtoDelete(request);
			rspMsg = new ResponseMessage<Object>();

			return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("团队信息删除：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			rspMsg = ResponseMessage.getResponseMessageFromException(e, "901007", e.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Description: 部门团队列表展示<br>
	 * ${tags}
	 * 
	 * @see
	 */
	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<TeamOrgQueryRsp>> teamOrgQuery(
			@RequestBody @Valid RequestMessage<TeamOrgQueryReq> reqMsg) {
		ResponseMessage<TeamOrgQueryRsp> rspMsg = null;
		try {
			TeamOrgQueryReq req = reqMsg.getMessage();
			TeamOrgQueryRsp rsp = teamServiceImpl.orgTeamListQuery(req);
			rspMsg = new ResponseMessage<TeamOrgQueryRsp>(rsp);
			return new ResponseEntity<ResponseMessage<TeamOrgQueryRsp>>(rspMsg, HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("查询部门信息：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rspMsg = ResponseMessage.getResponseMessageFromException(e, "901010", e.getMessage());
			return new ResponseEntity<ResponseMessage<TeamOrgQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Description: 根据部门编号查询团队<br>
	 * ${tags}
	 * 
	 * @see
	 */

	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<TeamListDtoQueryRsp>> teamQueryById(
			@RequestBody @Valid RequestMessage<TeamListDtoQueryReq> reqMsg) {
		ResponseMessage<TeamListDtoQueryRsp> rspMsg = null;
		try {
			TeamListDtoQueryReq request = reqMsg.getMessage();

			TeamListDtoQueryRsp response = teamListDtoServiceImpl.teamQueryById(request);
			rspMsg = new ResponseMessage<TeamListDtoQueryRsp>(response);

			return new ResponseEntity<ResponseMessage<TeamListDtoQueryRsp>>(rspMsg, HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("查询团队信息请求报文：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			rspMsg = ResponseMessage.getResponseMessageFromException(e, "901010", e.getMessage());
			return new ResponseEntity<ResponseMessage<TeamListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Description: 修改团队状态<br>
	 * ${tags}
	 * 
	 * @see
	 */
	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<TeamInfoDtoQueryRsp>> updateStatus(
			@RequestBody @Valid RequestMessage<TeamInfoDtoQueryReq> reqMsg) {

		ResponseMessage<TeamInfoDtoQueryRsp> rspMsg = null;
		try {
			TeamInfoDtoQueryReq request = reqMsg.getMessage();

			TeamInfoDtoQueryRsp response = teamInfoDtoServiceImpl.updateStatus(request);
			rspMsg = new ResponseMessage<TeamInfoDtoQueryRsp>(response);

			return new ResponseEntity<ResponseMessage<TeamInfoDtoQueryRsp>>(rspMsg, HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("团队信息状态：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			rspMsg = ResponseMessage.getResponseMessageFromException(e, "901010", e.getMessage());
			return new ResponseEntity<ResponseMessage<TeamInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Description: 更新用户团队<br>
	 * 
	 * @param UpdateUserTeamReq
	 * @see
	 */
	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<Object>> updateUserTeam(
			@RequestBody @Valid RequestMessage<UpdateUserTeamReq> reqMsg) {
		ResponseMessage<Object> rspMsg = null;
		try {
			UpdateUserTeamReq request = reqMsg.getMessage();

			teamServiceImpl.updateUserTeam(request);
			rspMsg = new ResponseMessage<Object>();

			return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("更新用户团队请求：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			rspMsg = ResponseMessage.getResponseMessageFromException(e, "", e.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Description: 按用户查询团队<br>
	 * 
	 * @param UserTeamQueryReq
	 * @return UserTeamQueryRsp
	 * @see
	 */
	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<UserTeamQueryRsp>> userTeamQuery(
			@RequestBody @Valid RequestMessage<UserTeamQueryReq> reqMsg) {
		ResponseMessage<UserTeamQueryRsp> rspMsg = null;
		try {
			UserTeamQueryReq req = reqMsg.getMessage();
			UserTeamQueryRsp rsp = teamServiceImpl.userTeamQuery(req);
			return new ResponseEntity<ResponseMessage<UserTeamQueryRsp>>(new ResponseMessage<UserTeamQueryRsp>(rsp),
					HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("按照员工查询团队请求报文：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rspMsg = ResponseMessage.getResponseMessageFromException(e, "", e.getMessage());
			return new ResponseEntity<ResponseMessage<UserTeamQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Description: 员工列表<br>
	 * 
	 * @param UserTeamQueryReq
	 * @return UserTeamQueryRsp
	 * @see
	 */
	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<EmployeeQueryRsp>> employeeQuery(
			@RequestBody @Valid RequestMessage<EmployeeQueryReq> reqMsg) {
		ResponseMessage<EmployeeQueryRsp> rspMsg = null;
		try {
			EmployeeQueryReq request = reqMsg.getMessage();
			EmployeeQueryRsp response = teamListDtoServiceImpl.employeeQuery(request);
			rspMsg = new ResponseMessage<EmployeeQueryRsp>(response);
			return new ResponseEntity<ResponseMessage<EmployeeQueryRsp>>(rspMsg, HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("展示员工列表：" + reqMsg.toString(), e);
			}
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rspMsg = ResponseMessage.getResponseMessageFromException(e, "EMS6021", e.getMessage());
			return new ResponseEntity<ResponseMessage<EmployeeQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

}
