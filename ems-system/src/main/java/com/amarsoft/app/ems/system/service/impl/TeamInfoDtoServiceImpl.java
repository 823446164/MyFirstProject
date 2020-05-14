package com.amarsoft.app.ems.system.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amarsoft.aecd.system.constant.OrgStatus;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.employee.template.cs.client.EmployeeInfoDtoClient;
import com.amarsoft.app.ems.employee.template.cs.client.EmployeeInfoListDtoClient;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.addteam.AddTeamRsp;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDto;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoSaveReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;
import com.amarsoft.app.ems.system.entity.TeamInfo;
import com.amarsoft.app.ems.system.entity.UserTeam;
import com.amarsoft.app.ems.system.service.TeamInfoDtoService;


/**
 * 团队信息Service实现类
 * 
 * @author hpli
 */
@Slf4j
@Service
public class TeamInfoDtoServiceImpl implements TeamInfoDtoService {
    @Autowired
    EmployeeInfoListDtoClient employeeInfoDtoClient;

    /**
     * @param request
     * @return
     */

    @Override
    @Transactional
    public TeamInfoDtoQueryRsp teamInfoDtoQuery(@Valid TeamInfoDtoQueryReq teamInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        TeamInfoDtoQueryRsp rsp = new TeamInfoDtoQueryRsp();
        TeamInfo teamInfo = bomanager.loadBusinessObject(TeamInfo.class, "teamId", teamInfoDtoQueryReq.getTeamId());
        TeamInfoDtoQueryRsp teamInfoDto = new TeamInfoDtoQueryRsp();
        BusinessObjectAggregate<BusinessObject> selectBusinessObjectsBySql = bomanager.selectBusinessObjectsBySql(
            "select count(*)  as count from  UserTeam  where teamId=:teamId ", "teamId", teamInfoDtoQueryReq.getTeamId());
        List<BusinessObject> businessObjects = selectBusinessObjectsBySql.getBusinessObjects();
        if (businessObjects != null && businessObjects.size() > 0) {

            int count = businessObjects.get(0).getInt("count");
            teamInfoDto.setCount(count);
        }
        if (teamInfo != null) {
            teamInfoDto.setTeamId(teamInfo.getTeamId());
            teamInfoDto.setTeamName(teamInfo.getTeamName());
            teamInfoDto.setRoleA(teamInfo.getRoleA());
            teamInfoDto.setRoleB(teamInfo.getRoleB());
            teamInfoDto.setRoleC(teamInfo.getRoleC());
            teamInfoDto.setBelongOrgId(teamInfo.getBelongOrgId());
            teamInfoDto.setStatus(teamInfo.getStatus());
            teamInfoDto.setTarget(teamInfo.getTarget());
            teamInfoDto.setDescription(teamInfo.getDescription());
            return teamInfoDto;
        }
        return rsp;

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
     * @param
     * @return
     */
    @Transactional
    public void teamInfoDtoSaveAction(TeamInfoDto teamInfoDto) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
      
        if (teamInfoDto == null) {
            throw new ALSException("901009");
        }
        String teamId = teamInfoDto.getTeamId();
        TeamInfo teamInfo = null;
        if (StringUtils.isEmpty(teamId)) {
            // 新增
            BusinessObjectAggregate<BusinessObject> teamOrgRsq = bomanager.selectBusinessObjectsBySql(
                " select  TI.teamName as teamName" + " from OrgTeam OT,TeamInfo TI" + " where OT.teamId =TI.teamId and TI.teamName=:teamName",
                "teamName", teamInfoDto.getTeamName());
            List<BusinessObject> teamOrg = teamOrgRsq.getBusinessObjects();
            if (teamOrg != null && teamOrg.size() > 0) {
                throw new ALSException("901009");
            }
            else {
                teamInfo = new TeamInfo();
                teamInfo.generateKey();
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
     * 团队状态 1.表示完成 2.表示停用
     * 
     * @param
     * @return
     */
    @Override
    @Transactional
    public TeamInfoDtoQueryRsp updateStatus(@Valid TeamInfoDtoQueryReq teamInfoDtoQueryReq) {
        
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 根据部门编号查询团队状态
        TeamInfo teamInfo = bomanager.keyLoadBusinessObject(TeamInfo.class, teamInfoDtoQueryReq.getTeamId());
        TeamInfoDtoQueryRsp rsp = new TeamInfoDtoQueryRsp();
        if (teamInfo == null) {
            // 不存在该团队
            throw new ALSException("901017");
        }
        // 判断状态 1:完成;2:停用;
        if (teamInfoDtoQueryReq.getStringflag) { // 点击执行完成
            teamInfo.setStatus(OrgStatus.Completed.id);
            bomanager.updateBusinessObject(teamInfo);
            bomanager.updateDB();

        }
        else { // 执行停用删除

            if ((OrgStatus.Disabled.id).equals(teamInfo.getStatus())) {
                throw new ALSException("EMS6012");
            }
            BusinessObjectAggregate<BusinessObject> selectBusinessObjectsBySql = bomanager.selectBusinessObjectsBySql(
                "select  UT.userId  from  UserTeam UT ,TeamInfo TI where TI.teamId = UT.teamId", teamInfo.getTeamId());
            List<BusinessObject> businessObjects = selectBusinessObjectsBySql.getBusinessObjects();
            if (businessObjects != null) {
                throw new ALSException("EMS6011");
            }
            bomanager.updateBusinessObject(teamInfo);
            bomanager.updateDB();

        }

        return rsp;
    }

}
