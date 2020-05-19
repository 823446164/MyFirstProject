package com.amarsoft.app.ems.system.service.impl;


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
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.employee.template.cs.client.EmployeeInfoListDtoClient;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.UserInfo;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDto;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoSaveReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoUserQueryRsp;
import com.amarsoft.app.ems.system.entity.TeamInfo;
import com.amarsoft.app.ems.system.service.TeamInfoDtoService;

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
        TeamInfo teamInfo = bomanager.loadBusinessObject(TeamInfo.class, "teamId", teamInfoDtoQueryReq.getTeamId());
        TeamInfoDtoQueryRsp teamInfoDto = new TeamInfoDtoQueryRsp();
        
        if (teamInfo != null) {
            teamInfoDto.setTeamId(teamInfo.getTeamId());
            teamInfoDto.setTeamName(teamInfo.getTeamName());
            teamInfoDto.setRoleA(teamInfo.getRoleA());
            teamInfoDto.setRoleB(teamInfo.getRoleB());
            teamInfoDto.setRoleC(teamInfo.getRoleC());
            teamInfoDto.setBelongOrgId(teamInfo.getBelongOrgId());
            teamInfoDto.setStatus(OrgStatus.getNameById(teamInfo.getStatus()));
            teamInfoDto.setTarget(teamInfo.getTarget());
            teamInfoDto.setDescription(teamInfo.getDescription());
            
        }
        BusinessObjectAggregate<BusinessObject> selectBusinessObjectsBySql = bomanager.selectBusinessObjectsBySql(
            "select count(*)  as count from  UserTeam  where teamId=:teamId ", "teamId", teamInfoDtoQueryReq.getTeamId());
        List<BusinessObject> businessObjects = selectBusinessObjectsBySql.getBusinessObjects();
        if (!CollectionUtils.isEmpty( businessObjects)) {
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
     * 团队信息单记录保存
     * 
     * @param teamInfoDto
     * @return
     */
    @Transactional
    public void teamInfoDtoSaveAction(TeamInfoDto teamInfoDto) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (ObjectUtils.isEmpty(teamInfoDto)) {
            throw new ALSException("EMS6022");
        }
        String teamId = teamInfoDto.getTeamId();
        TeamInfo teamInfo = null;
        if (StringUtils.isEmpty(teamId)) {
            // 新增
            BusinessObjectAggregate<BusinessObject> teamOrgRsq = bomanager.selectBusinessObjectsBySql(
                " select  TI.teamName as teamName" + " from OrgTeam OT,TeamInfo TI" + " where OT.teamId =TI.teamId and TI.teamName=:teamName",
                "teamName", teamInfoDto.getTeamName());
            List<BusinessObject> teamOrg = teamOrgRsq.getBusinessObjects();
            if (!CollectionUtils.isEmpty(teamOrg)) {
                throw new ALSException("901001");
            }
            else {        
                teamInfo = new TeamInfo();
                teamInfo.generateKey();
                teamInfo.setStatus(OrgStatus.New.id);         
            }   
        }
        else {
            // 更新
            teamInfo = bomanager.keyLoadBusinessObject(TeamInfo.class, teamInfoDto.getTeamId());          
        }
        // 拷贝属性
        BeanUtils.copyProperties(teamInfoDto, teamInfo);
        bomanager.updateBusinessObject(teamInfo);
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
                "select count(1) as cnt from UserTeam where teamId=:teamId", "teamId", teamInfoDtoQueryReq.getTeamId());
            int count = userCount.getBusinessObjects().get(0).getInt("cnt");
            if (count > 0) {
                throw new ALSException("EMS6009");// 该团队下存在负责人或者存在员工不予停用；
            }
          if (OrgStatus.New.id.equals(teamStatus)) {
                throw new ALSException("EMS6011");// 非完成状态的团队不予停用；
            }
        }else{
        	 //完成操作 完成状态不能在完成
            if(OrgStatus.Completed.id .equals(teamStatus)) { // 完成操作
            	throw new ALSException("EMS6028");	 //完成状态不在完成
            } 
        }
        teamInfo.setStatus(teamInfoDtoQueryReq.getStatus());
        bomanager.updateBusinessObject(teamInfo);

        bomanager.updateDB();

        return rsp;

    }
    /**
     *查询部门下不是团队负责人的人员
     * 
     * @param req
     * @return rsp
     */
	@Override
	@Transactional
	public TeamInfoUserQueryRsp teamUserId(TeamInfoUserQueryReq req ) {
	
		  BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		  TeamInfoUserQueryRsp  rsp =new TeamInfoUserQueryRsp ();
		  List<UserInfo> userInfos =new ArrayList<>();
		  UserInfo userInfo =new UserInfo();
		  //查询部门下的人员，取出掉成为团队负责人
		  List<BusinessObject> businessObjects = bomanager.selectBusinessObjectsBySql("select  UI.userId as userId , UI.userName as userName  "
		  		+ "from  UserBelong UB, UserInfo UI  where UI.userId =UB.userId and  UB.orgId =:orgId and UB.userId not in("
		  		+ " select roleA from  TeamInfo TI  where  TI.belongOrgId =:orgId) ","orgId", req .getOrgId()).getBusinessObjects();
		  //判空
		  if(!CollectionUtils.isEmpty(businessObjects)) {
			  //遍历集合
			  for(BusinessObject bos : businessObjects) {
				  userInfo.setUserId(bos.getString("userId"));
				  userInfo.setUserName(bos.getString("userName"));
				  userInfos.add(userInfo);
			  }
			 
			  rsp.setUserInfos(userInfos);
		  }
		  
		  
		  return rsp;
	}

}

