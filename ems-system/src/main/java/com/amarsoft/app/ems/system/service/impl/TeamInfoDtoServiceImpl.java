package com.amarsoft.app.ems.system.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amarsoft.aecd.system.constant.OrgStatus;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.employee.template.cs.client.EmployeeInfoListDtoClient;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoReq;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoRsp;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDto;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoRoleReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoRoleRsp;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoSaveReq;
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
        TeamInfoDtoQueryRsp rsp = new TeamInfoDtoQueryRsp();
        TeamInfo teamInfo = bomanager.loadBusinessObject(TeamInfo.class, "teamId", teamInfoDtoQueryReq.getTeamId());
        TeamInfoDtoQueryRsp teamInfoDto = new TeamInfoDtoQueryRsp();
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
        BusinessObjectAggregate<BusinessObject> selectBusinessObjectsBySql = bomanager.selectBusinessObjectsBySql(
            "select count(*)  as count from  UserTeam  where teamId=:teamId ", "teamId", teamInfoDtoQueryReq.getTeamId());
        List<BusinessObject> businessObjects = selectBusinessObjectsBySql.getBusinessObjects();
        if (businessObjects != null && businessObjects.size() > 0) {

            int count = businessObjects.get(0).getInt("count");
            teamInfoDto.setCount(count);
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
        if (teamInfo == null) {
            // 不存在该团队
            throw new ALSException("901017");
         }
        
          //原本状态
        String teamStatus = teamInfo.getStatus();
         
        TeamInfoDtoQueryRsp rsp = new TeamInfoDtoQueryRsp();
         // 判断完成 还是 停用 0:完成;1:停用;
        String status = teamInfoDtoQueryReq.getStatus();
        if (OrgStatus.Disabled.id.equals(status)) { // 操作停用
            BusinessObjectAggregate<BusinessObject> userCount=bomanager.selectBusinessObjectsBySql("select count(1) as cnt from UserTeam where teamId=:teamId","teamId",teamInfoDtoQueryReq.getTeamId());
            int count=userCount.getBusinessObjects().get(0).getInt("cnt");
            if(count>0 || OrgStatus.Disabled.id.equals(teamStatus)||OrgStatus.New.id.equals(teamStatus)) {
            throw new ALSException("EMS6009");
          }
        } /*
           * else {// 操作完成 if( OrgStatus.Completed.id.equals(teamStatus)) { throw new
           * ALSException("EMS6013"); } }
           */
      
        teamInfo.setStatus(teamInfoDtoQueryReq.getStatus());
        bomanager.updateBusinessObject(teamInfo);
        bomanager.updateDB();
        return rsp;

    }
    /**
     * 角色信息
     * 
     * @param request
     * @return rsp
     */

    @Override
    @Transactional
    public TeamInfoDtoRoleRsp  queryRole(@Valid TeamInfoDtoRoleReq request) {
   
       BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
       TeamInfoDtoRoleRsp rsp = new TeamInfoDtoRoleRsp(); 
       // 当前部门下的员工
       List<BusinessObject> bo =bomanager.selectBusinessObjectsBySql( "select  UT.userId  from  UserTeam UT" +
         "                       +  join TeamInfo TI on TI.teamId = UT.teamId and TI.belongOrgId =:belongOrgId and  not exists(select 1 from TeamInfo TI"
        +
         "                       +  where TI.roleA=UT.userId or TI.roleB =UT.userId or TI.roleC=UT.userId) "
         , request.getBelongOrgId()).getBusinessObjects();
         if (!CollectionUtils.isEmpty(bo)) {
         List<String> list = new ArrayList<String>(); for (BusinessObject bus : bo) { String userId =
         bus.getString("userId"); list.add(userId); } // 从当前对象中 拿去到员工信息
         EmployeeListByEmplNoReq employeeReq = new EmployeeListByEmplNoReq(); 
         employeeReq.setEmployeeNoList(list);
         EmployeeListByEmplNoReq reqEmployee = new EmployeeListByEmplNoReq();
         RequestMessage<EmployeeListByEmplNoReq> reqMsg = new RequestMessage<EmployeeListByEmplNoReq>();
         EmployeeListByEmplNoRsp message = employeeInfoListDtoClient.employeeListByEmployeeNoQuery(reqMsg).getBody().getMessage();
         rsp.setList(message.getEmployeeInfoList());
         } 
         return rsp;
    }

    
}
