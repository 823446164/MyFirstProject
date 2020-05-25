/*
 * 文件名：TeamListDtoServiceImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：团队列表Service实现类
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.aecd.system.constant.ChangeEventType;
import com.amarsoft.aecd.system.constant.OrgStatus;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.acsc.util.DTOHelper;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.employee.template.cs.client.EmployeeInfoListDtoClient;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDto;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoReq;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.EmployeeQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.EmployeeQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.Filter;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDto;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryRsp;
import com.amarsoft.app.ems.system.entity.ChangeEvent;
import com.amarsoft.app.ems.system.entity.OrgTeam;
import com.amarsoft.app.ems.system.entity.TeamInfo;
import com.amarsoft.app.ems.system.entity.UserTeam;
import com.amarsoft.app.ems.system.service.TeamListDtoService;
import com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto.DeleteInfoDtoQueryReq;

import lombok.extern.slf4j.Slf4j;

/**
 * 团队信息Service实现类
 * 
 * @author hpli
 */
@Slf4j
@Service
public class TeamListDtoServiceImpl implements TeamListDtoService {

    @Autowired
    EmployeeInfoListDtoClient employeeListDtoClient;
    
    /**
     * 查询结果集
     */

    public static class TeamListDtoReqQuery implements RequestQuery<TeamListDtoQueryReq> {
        @Override
        public Query apply(TeamListDtoQueryReq teamListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(teamListDtoQueryReq, TeamListDto.class);

            String sql = "select TINFO.teamId as teamId,TINFO.teamName as teamName,TINFO.roleA as roleA,"
                         + "TINFO.roleB as roleB,TINFO.roleC as roleC,TINFO.belongOrgId as belongOrgId,"
                         + "TINFO.status as status,TINFO.target as target,TINFO.description as description" + " from SYS_TEAM_INFO TINFO"
                         + " where 1=1";
            return queryProperties.assembleSql(sql);
        }
    }

    /**
     * 查询到的数据转换为响应实体
     */
    public static class TeamListDtoRspConvert implements Convert<TeamListDto> {
        @Override
        public TeamListDto apply(BusinessObject bo) {
            TeamListDto temp = new TeamListDto();
            // 查询到的数据转换为响应实体
            temp.setTeamId(bo.getString("TeamId"));
            temp.setTeamName(bo.getString("TeamName"));
            temp.setRoleA(bo.getString("RoleA"));
            temp.setRoleB(bo.getString("RoleB"));
            temp.setRoleC(bo.getString("RoleC"));
            temp.setBelongOrgId(bo.getString("BelongOrgId"));
            temp.setStatus(bo.getString("Status"));
            temp.setTarget(bo.getString("Target"));
            temp.setDescription(bo.getString("Description"));

            return temp;
        }
    }

    /**
     * 团队信息多记录查询
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public TeamListDtoQueryRsp teamListDtoQuery(@Valid TeamListDtoQueryReq teamListDtoQueryReq) {
        TeamListDtoQueryRsp teamListDtoQueryRsp = new TeamListDtoQueryRsp();
        // 团队员工详情
        Query query = new TeamListDtoReqQuery().apply(teamListDtoQueryReq);
        String fullsql = query.getSql();
        TeamListDtoRspConvert convert = new TeamListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(teamListDtoQueryReq.getBegin(),
            teamListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        if (null != businessObjectList && !businessObjectList.isEmpty()) {
            List<TeamListDto> teamListDtos = new ArrayList<TeamListDto>();
            for (BusinessObject bo : businessObjectList) {
                // 查询到的数据转换为响应实体
                teamListDtos.add(convert.apply(bo));
            }
            teamListDtoQueryRsp.setTeamListDtos(teamListDtos);
        }

        teamListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));

        return teamListDtoQueryRsp;
    }

  
    /**
     * Description: 根据关键字查询团队信息<br>
     * @param TeamListDtoQueryReq
     * @return TeamListDtoQueryRsp
     * 修改人:xszhou
     * @see
     */

    @Override
    @Transactional
    public TeamListDtoQueryRsp teamQueryById(@Valid TeamListDtoQueryReq req) {

        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        TeamListDtoQueryRsp rsp = new TeamListDtoQueryRsp();
        List<TeamListDto> teams= new ArrayList<>();
        TeamListDto team = null;
        
        String name = "";
        String roleA = "";
        //获取查询条件值
        if (!CollectionUtils.isEmpty(req.getFilters())) {
            for (Filter filter : req.getFilters()) {
                if ("teamName".equals(filter.getName())) {
                    name = filter.getValue()[0];
                }
                if ("roleA".equals(filter.getName())) {
                    roleA = filter.getValue()[0];
                }
            }
        }
        name = StringUtils.isEmpty(name)?"%":"%"+name+"%";
        roleA = StringUtils.isEmpty(roleA)?"%":"%"+roleA+"%";
        String orgId = req.getOrgId();
        if (StringUtils.isEmpty(req.getOrgId())) {//如果没有传入部门
            throw new ALSException("请选择部门");
        }
        //根据搜索条件获取团队(显示出团队负责人姓名)  modify by xszhou  2020/5/25
        
        List<BusinessObject> bos = bomanager.selectBusinessObjectsBySql("select TI.teamId as TeamId,TI.teamName as TeamName,"
            + "TI.belongRootOrg as BelongRootOrg,TI.belongOrgId as BelongOrgId,OI.orgName as OrgName,"
            + "TI.description as Description,TI.roleA as RoleA,TI.roleB as RoleB,TI.roleC as RoleC,TI.status as Status,"
            + "TI.target as Target,UI.userName as UserName from TeamInfo TI,UserInfo UI,OrgInfo OI "
            + "where TI.roleA = UI.userId and TI.belongOrgId=OI.orgId and TI.belongOrgId=:orgId and TI.teamName like :name and UI.userName like :roleA", "orgId",
            orgId,"name",name,"roleA",roleA).getBusinessObjects();
        //返回对象的参数录入
        for (BusinessObject bo : bos) {
            team = new TeamListDto();
            team.setTeamId(bo.getString("TeamId"));
            team.setTeamName(bo.getString("TeamName"));
            team.setBelongOrgId(bo.getString("BelongOrgId"));
            team.setOrgId(bo.getString("BelongOrgId"));
            team.setOrgName(bo.getString("OrgName"));
            team.setDescription(bo.getString("Description"));
            team.setRoleA(bo.getString("RoleA"));
            team.setRoleAName(bo.getString("UserName"));
            team.setRoleB(bo.getString("RoleB"));
            team.setRoleC(bo.getString("RoleC"));
            team.setTarget(bo.getString("Target"));
            team.setStatus(bo.getString("Status"));
            List<UserTeam> users = bomanager.loadBusinessObjects(UserTeam.class, "teamId=:teamId","teamId",bo.getString("TeamId"));
            team.setCount(users.size());
            teams.add(team);
        }
        rsp.setTeamListDtos(teams);
        rsp.setTotalCount(teams.size());        
        return rsp;
    }

    /**
     * Description: 删除团队<br>
     * 1、删除团队和关联表信息<br>
     * 2、增加变更记录表信息<br>
     * @param DeleteInfoDtoQueryReq
     * 修改人:xszhou
     * @see
     */
    @Transactional
    @Override
    public void teamListDtoDelete(@Valid DeleteInfoDtoQueryReq req) {
        //获取全局变量
    	String userId = GlobalShareContextHolder.getUserId();
    	String orgId = GlobalShareContextHolder.getOrgId();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        //1.查询需要删除的团队信息
        TeamInfo delTeam = bomanager.keyLoadBusinessObject(TeamInfo.class, req.getObjectNo());
        ChangeEvent ch = new ChangeEvent();
        //2.填写变更理由表信息
        ch.generateKey();
        ch.setObjectNo(req.getObjectNo());
        ch.setObjectType(ChangeEventType.Delete.id);
        ch.setChangeContext("删除团队:" + delTeam.getTeamName());
        ch.setInputUserId(userId);
        ch.setInputOrgId(orgId);    
        ch.setOccurDate(LocalDateTime.now());
        ch.setRemark(req.getRemark());

        //3.根据传入参数确认团队下员工信息
        List<UserTeam> userTeams = bomanager.loadBusinessObjects(UserTeam.class, "teamId=:teamId","teamId",req.getObjectNo());
        //4.查询团队对应的部门Id
        OrgTeam orgTeam = bomanager.loadBusinessObject(OrgTeam.class, "teamId",req.getObjectNo());
        
        if (!OrgStatus.Disabled.id.equals(delTeam.getStatus())) {//5.若团队不为停用状态，不许操作
            throw new ALSException("EMS6029");
        }else if(!CollectionUtils.isEmpty(userTeams)) {//6.若团队下有员工，不许删除
            throw new ALSException("EMS6008");
        }else if(!StringUtils.isEmpty(delTeam.getRoleA())) {//7.若团队有负责人，不许删除
            throw new ALSException("EMS6031");
        }else {//8.可删除校验成功,执行删除操作
            bomanager.deleteBusinessObject(delTeam);
            if (!ObjectUtils.isEmpty(orgTeam)) {
                bomanager.deleteObjectBySql(OrgTeam.class, "teamId=:teamId and orgId=:orgId", "teamId",delTeam.getTeamId(),"orgId",orgTeam.getOrgId());
            }
        }
       
        bomanager.updateBusinessObject(ch);
        bomanager.updateDB();
    }


    /**
     * Description: 团队下员工信息<br>
     * @param EmployeeQueryReq(teamID)
     * @return EmployeeQueryRsp(List<EmployeeInfoDto>)
     * 修改人:xszhou
     * @see
     */
    @Transactional
    @Override
    public EmployeeQueryRsp employeeQuery(@Valid EmployeeQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<String> eNoList = new ArrayList<String>();
        EmployeeQueryRsp rsp = new EmployeeQueryRsp();
        List<EmployeeInfoDto> eiList = new ArrayList<EmployeeInfoDto>();
        //查询团队下员工ID
        List<UserTeam> userTeams = bomanager.loadBusinessObjects(UserTeam.class, "teamId=:teamId","teamId",req.getTeamId());
        if (!CollectionUtils.isEmpty(userTeams)) {//如果团队下有员工,将员工ID取出后
            for (UserTeam userTeam : userTeams) {
                eNoList.add(userTeam.getUserId());
            }
            //调用员工管理接口服务
            EmployeeListByEmplNoReq request = new EmployeeListByEmplNoReq();
            request.setEmployeeNoList(eNoList);
            request.setFilters(req.getFilters());
            RequestMessage<EmployeeListByEmplNoReq> reqMsg = new RequestMessage<EmployeeListByEmplNoReq>();
            reqMsg.setMessage(request);
            ResponseEntity<ResponseMessage<EmployeeListByEmplNoRsp>> eQuery = employeeListDtoClient.employeeListByEmployeeNoQuery(reqMsg);
            //获取员工的信息
            eiList = eQuery.getBody().getMessage().getEmployeeInfoList();
        }
        rsp.setEmployeeInfoList(eiList);
        return rsp;
    }
    
    
}
