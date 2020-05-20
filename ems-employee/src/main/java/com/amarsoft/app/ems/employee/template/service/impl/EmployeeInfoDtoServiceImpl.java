package com.amarsoft.app.ems.employee.template.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.entity.EmployeeInfo;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoStatusUpdateReq;
import com.amarsoft.app.ems.employee.template.service.EmployeeInfoDtoService;
import com.amarsoft.app.ems.system.cs.client.TeamClient;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryRsp;

import lombok.extern.slf4j.Slf4j;

/**
 * 员工信息InfoService实现类
 * 
 * @author lding
 */
@Slf4j
@Service
public class EmployeeInfoDtoServiceImpl implements EmployeeInfoDtoService {
	@Autowired
	TeamClient teamClient;

	/**
	 * Description: 员工信息Info单记录查询<br>
	 * ${tags}
	 * 
	 * @see
	 */
	@Override
	@Transactional
	public EmployeeInfoDtoQueryRsp employeeInfoDtoQuery(
			@RequestBody @Valid EmployeeInfoDtoQueryReq employeeInfoDtoQueryReq) {
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		EmployeeInfo employeeInfo = bomanager.loadBusinessObject(EmployeeInfo.class, "employeeNo",
				employeeInfoDtoQueryReq.getEmployeeNo());
		if (employeeInfo != null) {
			// add by xszhou 员工详情中添加所属团队信息 begin
			RequestMessage<UserTeamQueryReq> reqMsg = new RequestMessage<UserTeamQueryReq>();
			UserTeamQueryReq req = new UserTeamQueryReq();
			req.setUserId(employeeInfoDtoQueryReq.getEmployeeNo());
			reqMsg.setMessage(req);
			ResponseEntity<ResponseMessage<UserTeamQueryRsp>> userTeamQuery = teamClient.userTeamQuery(reqMsg);
			String teamId = "";
			String teamName = "";
			if (!StringUtils.isEmpty(userTeamQuery)) {
				teamId = userTeamQuery.getBody().getMessage().getTeamId();
				teamName = userTeamQuery.getBody().getMessage().getTeamName();
			}
			// add by xszhou 员工详情中添加所属团队信息 end
			EmployeeInfoDtoQueryRsp employeeInfoDto = new EmployeeInfoDtoQueryRsp();
			employeeInfoDto.setEmployeeNo(employeeInfo.getEmployeeNo());
			employeeInfoDto.setEmployeeName(employeeInfo.getEmployeeName());
			employeeInfoDto.setEmployeeAcct(employeeInfo.getEmployeeAcct());
			employeeInfoDto.setPhoneNum(employeeInfo.getPhoneNum());
			employeeInfoDto.setNowRank(employeeInfo.getNowRank());
			employeeInfoDto.setGoalRank(employeeInfo.getGoalRank());
			employeeInfoDto.setRntryTime(employeeInfo.getRntryTime());
			employeeInfoDto.setChangeTime(employeeInfo.getChangeTime());
			employeeInfoDto.setEmployeeStatus(employeeInfo.getEmployeeStatus());
			employeeInfoDto.setResignationReason(employeeInfo.getResignationReason());
			employeeInfoDto.setEmployeeeDucation(employeeInfo.getEmployeeeDucation());
			employeeInfoDto.setGraduationTime(employeeInfo.getGraduationTime());
			employeeInfoDto.setGraduatedSchool(employeeInfo.getGraduatedSchool());
			employeeInfoDto.setMajor(employeeInfo.getMajor());
			employeeInfoDto.setHomeTown(employeeInfo.getHomeTown());
			employeeInfoDto.setInputUserId(employeeInfo.getInputUserId());
			employeeInfoDto.setInputTime(employeeInfo.getInputTime());
			employeeInfoDto.setInputOrgId(employeeInfo.getInputOrgId());
			employeeInfoDto.setUpdateUserId(employeeInfo.getUpdateUserId());
			employeeInfoDto.setUpdateTime(employeeInfo.getUpdateTime());
			employeeInfoDto.setUpdateOrgId(employeeInfo.getUpdateOrgId());
			employeeInfoDto.setEmployeeWorkStatus(employeeInfo.getEmployeeWorkStatus());
			employeeInfoDto.setTeamId(teamId);
			employeeInfoDto.setBeforeTeam(teamName);
			return employeeInfoDto;
		}
		return null;
	}

	/**
	 * 员工信息Info单记录保存
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public void employeeInfoDtoSave(@Valid EmployeeInfoDtoSaveReq employeeInfoDtoSaveReq) {
		employeeInfoDtoSaveAction(employeeInfoDtoSaveReq);
	}

	/**
	 * 员工信息Info单记录保存
	 * 
	 * @param
	 * @return
	 */
	@Transactional
	public void employeeInfoDtoSaveAction(EmployeeInfoDto employeeInfoDto) {
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		if (employeeInfoDto != null) {
		}
		bomanager.updateDB();
	}

	/**
	 * 员工Info员工状态更新
	 * 
	 * @param
	 * @return
	 */
	@Override
	public void employeeInfoDtoStatusSave(@Valid EmployeeInfoStatusUpdateReq employeeInfoStatusUpdateReq) {
		employeeInfoDtoStatusSaveAction(employeeInfoStatusUpdateReq);
	}

	@Transactional
	public void employeeInfoDtoStatusSaveAction(EmployeeInfoDto employeeInfoDto) {
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		if (employeeInfoDto != null) {
			//根据员工编号查询员工信息
			EmployeeInfo employeeInfo = bomanager.keyLoadBusinessObject(EmployeeInfo.class,
					employeeInfoDto.getEmployeeNo());
			//更新员工状态
			employeeInfo.setEmployeeStatus(employeeInfoDto.getEmployeeStatus());
			bomanager.updateBusinessObject(employeeInfo);
		}
		bomanager.updateDB();
	}
}
