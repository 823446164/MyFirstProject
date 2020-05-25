/*
 * 文件名：TeamInfoDtoServiceImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：团队信息Service实现类
 * 修改人：xszhou
 * 修改时间：2020/5/21
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.aecd.system.constant.OrgStatus;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.employee.template.cs.client.EmployeeInfoListDtoClient;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.UserInfo;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoSaveReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoUserQueryRsp;
import com.amarsoft.app.ems.system.entity.OrgInfo;
import com.amarsoft.app.ems.system.entity.OrgTeam;
import com.amarsoft.app.ems.system.entity.TeamInfo;
import com.amarsoft.app.ems.system.service.TeamInfoDtoService;
import com.amarsoft.app.ems.system.service.UserService;
import com.amarsoft.app.ems.system.util.UserHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 团队信息Service实现类
 * 
 * @author hpli
 */
@Slf4j
@Service
public class TeamInfoDtoServiceImpl implements TeamInfoDtoService {
	@Autowired
	EmployeeInfoListDtoClient employeeInfoListDtoClient;
	@Autowired
	UserService userservice;

	/**
	 * 查询团队详情
	 * 
	 * @param request
	 * @return 团队list
	 */

	@Override
	@Transactional
	public TeamInfoDtoQueryRsp teamInfoDtoQuery(@Valid TeamInfoDtoQueryReq teamInfoDtoQueryReq) {
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();

		List<BusinessObject> bos = bomanager.selectBusinessObjectsBySql(
				"select a.teamId,a.teamName,a.roleA,a.roleB,a.roleC,a.belongOrgId,a.status,a.target,a.description, b.userName from  TeamInfo  a "
						+ "inner  join  UserInfo b on a.roleA =b.userId  where  a.teamId =:teamId",
				"teamId", teamInfoDtoQueryReq.getTeamId()).getBusinessObjects();
		TeamInfoDtoQueryRsp teamInfoDto = new TeamInfoDtoQueryRsp();
		// 判空
		if (!CollectionUtils.isEmpty(bos)) {
			String userBName = "";
			String userCName = "";
			BusinessObject businessObject = bos.get(0);
			String userName = businessObject.getString("b.userName");
			String roleB = businessObject.getString("a.roleB");
			String roleC = businessObject.getString("a.roleC");
			teamInfoDto.setTeamId(businessObject.getString("a.teamId"));
			teamInfoDto.setTeamName(businessObject.getString("a.teamName"));
			teamInfoDto.setRoleA(businessObject.getString("a.roleA"));
			teamInfoDto.setRoleB(roleB);
			teamInfoDto.setRoleC(roleC);
			teamInfoDto.setBelongOrgId(businessObject.getString("a.belongOrgId"));
			teamInfoDto.setStatus(businessObject.getString("a.status"));
			teamInfoDto.setTarget(bos.get(0).getString("a.target"));
			teamInfoDto.setDescription(bos.get(0).getString("a.description"));
			teamInfoDto.setRoleAName(userName);
			
		
			if (roleB != "" && roleB != null) {
				String[] roleblist = roleB.split(",");
				for (int i = 0; i < roleblist.length; i++) {
					String userNameb = UserHelper.getUserName(roleblist[i]);
					userBName += userNameb + ",";
				}
                userBName = userBName.substring(0,userBName.length()-1);
			}
			if (roleC != "" && roleC != null) {
				String[] roleclist = roleC.split(",");
				for (int i = 0; i < roleclist.length; i++) {
					String userNamec = UserHelper.getUserName(roleclist[i]);
					userCName += userNamec + ",";
				}
				userCName = userCName.substring(0, userCName.length()-1);
			}

			teamInfoDto.setRoleBName(userBName);
			teamInfoDto.setRoleCName(userCName);

		}
		BusinessObjectAggregate<BusinessObject> selectBusinessObjectsBySql = bomanager.selectBusinessObjectsBySql(
				"select count(*)  as count from  UserTeam  where teamId=:teamId ", "teamId",
				teamInfoDtoQueryReq.getTeamId());
		List<BusinessObject> businessObjects = selectBusinessObjectsBySql.getBusinessObjects();
		if (!CollectionUtils.isEmpty(businessObjects)) {
			int count = businessObjects.get(0).getInt("count");
			teamInfoDto.setCount(count);
		}
		return teamInfoDto;

	}

	/**
	 * 团队信息单记录保存
	 * 
	 * @param request
	 * @return
	 */
	@Override
	@Transactional
	public void teamInfoDtoSave(@Valid TeamInfoDtoSaveReq teamInfoDtoSaveReq) {

		teamInfoDtoSaveAction(teamInfoDtoSaveReq);
	}


	/**
	 * Description: 团队信息单记录保存<br>
	 * @param TeamInfoDtoSaveReq
	 * 修改人:xszhou
	 * ${tags}
	 * @see
	 */
	@Transactional
	public void teamInfoDtoSaveAction(TeamInfoDtoSaveReq req) {
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		TeamInfo team = null;
		//如果请求体中团队编号为空，则是新增团队，检索团队名称是否存在
		if (StringUtils.isEmpty(req.getTeamId())) {
		    TeamInfo search = bomanager.loadBusinessObject(TeamInfo.class,"teamName",req.getTeamName());
	        if (!ObjectUtils.isEmpty(search)) {//若同名团队存在，则提示团队已存在
	            throw new ALSException("EMS6030");
	        }
	        //如果没有,则新建一个团队
	        OrgInfo org = bomanager.loadBusinessObject(OrgInfo.class, "orgId",req.getBelongOrgId());
	        team = new TeamInfo();
	        BeanUtils.copyProperties(req, team);
	        team.generateKey();
	        team.setStatus(OrgStatus.New.id);
	        team.setBelongRootOrg(org.getRootOrgId());
	        team.setBelongOrgLevel(org.getOrgLevel());
	        team.setInputOrgId(GlobalShareContextHolder.getOrgId());
	        //添加部门团队关系表
	        OrgTeam orgTeam = new OrgTeam();
	        orgTeam.setOrgId(req.getBelongOrgId());
	        orgTeam.setTeamId(team.getTeamId());
	        bomanager.updateBusinessObject(orgTeam);
	        bomanager.updateBusinessObject(team);
        }else {//否则执行更新操作
            TeamInfo teamInfo = bomanager.loadBusinessObject(TeamInfo.class, "teamId",req.getTeamId());
            teamInfo.setRoleA(req.getRoleA());
            teamInfo.setRoleB(req.getRoleB());
            teamInfo.setRoleC(req.getRoleC());
            teamInfo.setTeamName(req.getTeamName());
            teamInfo.setDescription(req.getDescription());
            teamInfo.setBelongOrgId(req.getBelongOrgId());
            teamInfo.setStatus(req.getStatus());
            teamInfo.setUpdateUserId(GlobalShareContextHolder.getUserId());
            teamInfo.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
            teamInfo.setUpdateTime(LocalDateTime.now());
            bomanager.updateBusinessObject(teamInfo);
        }
		
		bomanager.updateDB();
	}

	
	/**
	 * 团队状态 1.表示完成 2.表示停用 3.表示新增
	 * 
	 * @param req
	 * @return rsp
	 */
	@Override
	@Transactional
	public TeamInfoDtoQueryRsp updateStatus(@Valid TeamInfoDtoQueryReq teamInfoDtoQueryReq) {

		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		// 根据部门编号查询团队状态
		TeamInfo teamInfo = bomanager.keyLoadBusinessObject(TeamInfo.class, teamInfoDtoQueryReq.getTeamId());
		if (ObjectUtils.isEmpty(teamInfo)) {
			// 不存在该团队
			throw new ALSException("EMS6022");
		}
		// 原本状态
		String teamStatus = teamInfo.getStatus();

		TeamInfoDtoQueryRsp rsp = new TeamInfoDtoQueryRsp();
		// 判断完成 还是 停用 1:完成;2:停用 ;3.表示新增
		String status = teamInfoDtoQueryReq.getStatus();
		if (OrgStatus.Disabled.id.equals(status)) { // 操作停用
			BusinessObjectAggregate<BusinessObject> userCount = bomanager.selectBusinessObjectsBySql(
					"select count(1) as cnt from UserTeam where teamId=:teamId", "teamId",
					teamInfoDtoQueryReq.getTeamId());
			int count = userCount.getBusinessObjects().get(0).getInt("cnt");
			if (count > 0) {
				throw new ALSException("EMS6009");// 该团队下存在负责人或者存在员工不予停用；
			}
			if (OrgStatus.New.id.equals(teamStatus)) {
				throw new ALSException("EMS6011");// 非完成状态的团队不予停用；
			}
		} else {
			// 完成操作 完成状态不能在完成
			if (OrgStatus.Completed.id.equals(teamStatus)) { // 完成操作
				throw new ALSException("EMS6028"); // 完成状态不在完成
			}
		}
		teamInfo.setStatus(teamInfoDtoQueryReq.getStatus());
		bomanager.updateBusinessObject(teamInfo);

		bomanager.updateDB();

		return rsp;

	}

	/**
	 * 查询部门下不是团队负责人的人员
	 * 
	 * @param req
	 * @return rsp
	 */
	@Override
	@Transactional
	public TeamInfoUserQueryRsp teamUserId(TeamInfoUserQueryReq req) {

		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		TeamInfoUserQueryRsp rsp = new TeamInfoUserQueryRsp();
		List<UserInfo> userInfos = new ArrayList<>();
		UserInfo userInfo = new UserInfo();
		// 查询部门下的人员，取出掉成为团队负责人
		List<BusinessObject> businessObjects = bomanager
				.selectBusinessObjectsBySql("select UI.userId as userId,UI.userName as userName"
						+ "from  UserBelong UB, UserInfo UI  where UI.userId =UB.userId and UB.orgId =:orgId and UB.userId not in("
						+ " select roleA from TeamInfo TI where TI.belongOrgId =:orgId)", "orgId", req.getOrgId())
				.getBusinessObjects();
		// 判空
		if (!CollectionUtils.isEmpty(businessObjects)) {
			// 遍历集合
			for (BusinessObject bos : businessObjects) {
				userInfo.setUserId(bos.getString("userId"));
				userInfo.setUserName(bos.getString("userName"));
				userInfos.add(userInfo);
			}

			rsp.setUserInfos(userInfos);
		}

		return rsp;
	}

}
