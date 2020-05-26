/*
 * 文件名：TeamServiceImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：团队关联信息service实现类
 * 修改人：hpli
 * 修改时间：2020/5/9
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.aecd.system.constant.DataAuth;
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.employee.template.cs.client.EmployeeBelongChangeInfoDtoClient;
import com.amarsoft.app.ems.employee.template.cs.client.EmployeeInfoDtoClient;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.Filter;
import com.amarsoft.app.ems.system.cs.dto.addteam.AddTeamReq;
import com.amarsoft.app.ems.system.cs.dto.addteam.AddTeamRsp;
import com.amarsoft.app.ems.system.cs.dto.addteamuser.AddTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryReq;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.deleteteamuser.DeleteTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.CooperateTeam;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.OrgAndTeam;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.updateuserteam.UpdateUserTeamReq;
import com.amarsoft.app.ems.system.cs.dto.userquery.User;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryRsp;
import com.amarsoft.app.ems.system.entity.Department;
import com.amarsoft.app.ems.system.entity.OrgTeam;
import com.amarsoft.app.ems.system.entity.TeamInfo;
import com.amarsoft.app.ems.system.entity.UserBelong;
import com.amarsoft.app.ems.system.entity.UserTeam;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.TeamService;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户事件服务逻辑的处理类
 * @author hzhang23
 */
@Slf4j
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    EmployeeInfoDtoClient employeeInfoDtoClient;
    @Autowired
    EmployeeBelongChangeInfoDtoClient employeeBelongChangeInfoDtoClient ;
    
    /**
     * Description:新增团队<br>
     * @param AddTeamReq
     * @return AddTeamRsp
     * 修改人:xszhou
     * @see
     */
    @Override
    public AddTeamRsp addTeam(AddTeamReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        AddTeamRsp  addTeamRsP=new AddTeamRsp();
        TeamInfo teamInfo = new TeamInfo();
		  //判断团队名称 
        BusinessObjectAggregate<BusinessObject> teamOrgRsq = bomanager.
		 selectBusinessObjectsBySql(" select  TI.teamName as teamName"
		 + " from OrgTeam OT,TeamInfo TI" +
		 " where OT.teamId =TI.teamId and TI.teamName=:teamName",
		 "teamName",req.getTeamName());
        	List<BusinessObject> teamOrg = teamOrgRsq.getBusinessObjects();
        	if(teamOrg!=null&&teamOrg.size()>0) {
        		if(req.getTeamName()!=null) {
        	   addTeamRsP.setMeassage("团队"+req.getTeamName()+ "已建立");
        			}
        		return addTeamRsP;
        		}else {
        	   addTeamRsP.setTeamName(req.getTeamName());
        		}   
        
        if(StringUtils.isEmpty(req.getTeamId())) {
            teamInfo.generateKey();
        }else {
            teamInfo.setTeamId(req.getTeamId());
        }
        teamInfo.setTeamName(req.getTeamName());
        teamInfo.setTeamLeader(req.getTeamLeader());
        teamInfo.setBelongOrgId(req.getBelongOrgId());
        teamInfo.setBelongRootOrg(req.getBelongRootOrg());
        teamInfo.setBelongOrgLevel(req.getBelongOrgLevel());
        teamInfo.setDescription(req.getDescription());
        teamInfo.setStatus(req.getStatus());
        teamInfo.setRoleA(req.getRoleA());
        List<UserBelong> userBelongs = bomanager.loadBusinessObjects(UserBelong.class, "userId = :userId and orgId = :orgId",
                "userId",req.getRoleA(),"orgId",req.getBelongOrgId());
        for (UserBelong userBelong : userBelongs) {
            userBelong.setDataAuth(DataAuth.TeamData.id);
        }
        
        bomanager.updateBusinessObject(teamInfo);
        bomanager.updateBusinessObjects(userBelongs);
        
        OrgTeam orgTeam = new OrgTeam();
        orgTeam.setTeamId(req.getTeamId());
        orgTeam.setOrgId(req.getBelongOrgId());
        bomanager.updateBusinessObject(orgTeam);
        bomanager.updateDB();
        return addTeamRsP;
     
    }
   
    /**
     * Description: 添加团队成员<br>
     *<br>
     * ${tags}
     * @see
     */
    @Override
    public void addTeamUser(AddTeamUserReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<UserTeam> userTeamList = new ArrayList<UserTeam>();
        List<com.amarsoft.app.ems.system.cs.dto.addteamuser.UserTeam> userTeams = req.getUserTeams();
       
        for (com.amarsoft.app.ems.system.cs.dto.addteamuser.UserTeam userTeamInfo : userTeams) {
            UserTeam userTeam = new UserTeam();
            userTeam.setUserId(userTeamInfo.getUserId());
            userTeam.setTeamId(userTeamInfo.getTeamId());
            userTeamList.add(userTeam);
        }
        
        bomanager.updateBusinessObjects(userTeamList);
        bomanager.updateDB();
        
    }
    /**
     * Description: 删除团队成员<br>
     *<br>
     * ${tags}
     * @see
     */
    @Override
    public void deleteTeamUser(DeleteTeamUserReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
       if (!CollectionUtils.isEmpty(req.getUserTeams())) {
           for (com.amarsoft.app.ems.system.cs.dto.deleteteamuser.UserTeam userTeamInfo : req.getUserTeams()) {
               bomanager.deleteObjectBySql(UserTeam.class, "userId=:userId and teamId = :teamId", "userId", userTeamInfo.getUserId(), "teamId", userTeamInfo.getTeamId());
           }
       }
       bomanager.updateDB();
    }

    /**
     * Description: 查询团队信息<br>
     * <br>
     * ${tags}
     * @see
     */
    @Override
    public TeamQueryRsp teamQuery(TeamQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<TeamInfo> teamAggregate = null;
        List<TeamInfo> teamInfos = new ArrayList<TeamInfo>();
        String[] searchAttributes = {"teamId","teamName"};//查询条件
        if (req.getBegin() == null || req.getPageSize() == null) { //如果传入分页信息为空则展示所有
            req.setBegin(0);
            req.setPageSize(Integer.MAX_VALUE);
        }
        if (StringUtils.isEmpty(req.getSearchAttribute()) && StringUtils.isEmpty(req.getSearchContent())) {//不走查询条件
            if (!StringUtils.isEmpty(req.getTeamId())) {//按主键查询
                TeamInfo team = bomanager.keyLoadBusinessObject(TeamInfo.class, req.getTeamId());
                teamInfos = new ArrayList<TeamInfo>();
                teamInfos.add(team);
            }else if (!StringUtils.isEmpty(req.getBelongOrgId())) {//按所属机构查询
                teamAggregate = bomanager.loadBusinessObjects(TeamInfo.class, req.getBegin(), req.getPageSize(), "belongOrgId = :belongOrgId","belongOrgId",req.getBelongOrgId());
            }else if (!StringUtils.isEmpty(req.getRoleA())) {//按团队长查询
                teamAggregate = bomanager.loadBusinessObjects(TeamInfo.class, req.getBegin(), req.getPageSize(), "roleA = :roleA","roleA",req.getRoleA());
            }else if (!CollectionUtils.isEmpty(req.getBelongOrgLevel()) && StringUtils.isEmpty(req.getBelongRootOrg())){
                teamAggregate = bomanager.loadBusinessObjects(TeamInfo.class, req.getBegin(), req.getPageSize(), "belongOrgLevel in ( :belongOrgLevel )","belongOrgLevel",req.getBelongOrgLevel());
            }else if (!StringUtils.isEmpty(req.getBelongRootOrg()) && !CollectionUtils.isEmpty(req.getBelongOrgLevel())){
                teamAggregate = bomanager.loadBusinessObjects(TeamInfo.class, req.getBegin(), req.getPageSize(), "belongRootOrg = :belongRootOrg and belongOrgLevel in ( :belongOrgLevel )","belongRootOrg",req.getBelongRootOrg(),"belongOrgLevel",req.getBelongOrgLevel());
            }else {//查询所有
                teamAggregate = bomanager.loadBusinessObjects(TeamInfo.class, req.getBegin(), req.getPageSize(), "1 = 1");
            }
        }else {
            if (Stream.of(searchAttributes).anyMatch(searchAttribute -> searchAttribute.equalsIgnoreCase(req.getSearchAttribute()))) {//验证查询条件
                if (StringUtils.isEmpty(req.getBelongOrgId())) {
                    teamAggregate = bomanager.loadBusinessObjects(TeamInfo.class,req.getBegin(),req.getPageSize(), req.getSearchAttribute() + " like :searchContent",
                            "searchContent","%"+req.getSearchContent()+"%");;
                }else {
                    teamAggregate = bomanager.loadBusinessObjects(TeamInfo.class,req.getBegin(),req.getPageSize(), req.getSearchAttribute() + " like :searchContent and belongOrgId = :belongOrgId",
                            "searchContent","%"+req.getSearchContent()+"%","belongOrgId",req.getBelongOrgId());
                }
            }else {
                throw new ALSException("901013");
            }
        }
        TeamQueryRsp rsp = new TeamQueryRsp();
        rsp.setTotalCount(teamAggregate == null ? 1 : teamAggregate.getAggregate("count(teamId) as cnt").getInt("cnt")); // 为空则为查询详情，不为空返回集合数量
        rsp.setTeamInfos(new ArrayList<com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo>());
        if(!CollectionUtils.isEmpty(teamInfos) || !CollectionUtils.isEmpty(teamAggregate.getBusinessObjects())) {
            if (CollectionUtils.isEmpty(teamInfos)) {
                teamInfos = teamAggregate.getBusinessObjects();
            }
            for (TeamInfo teamInfo : teamInfos) {
                com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo team = new com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo();
                team.setUsers(new ArrayList<User>());
                team.setTeamId(teamInfo.getTeamId());
                team.setTeamName(teamInfo.getTeamName());
                team.setRoleA(teamInfo.getRoleA());
                team.setBelongOrgId(teamInfo.getBelongOrgId());
                
                com.amarsoft.app.ems.system.entity.OrgInfo belongOrg = bomanager.keyLoadBusinessObject(com.amarsoft.app.ems.system.entity.OrgInfo.class, teamInfo.getBelongOrgId());
                if(belongOrg != null) {
                    team.setBelongOrgName(belongOrg.getOrgName());
                }
                team.setBelongRootOrg(teamInfo.getBelongRootOrg());
                team.setDescription(teamInfo.getDescription());
                team.setStatus(teamInfo.getStatus());
                
                com.amarsoft.app.ems.system.entity.OrgInfo rootOrg = bomanager.keyLoadBusinessObject(com.amarsoft.app.ems.system.entity.OrgInfo.class, teamInfo.getBelongRootOrg());
                if(rootOrg != null) {
                    team.setBelongRootOrgName(rootOrg.getOrgName());
                };
                team.setBelongOrgLevel(teamInfo.getBelongOrgLevel());
                
                List<UserTeam> userTeam = bomanager.loadBusinessObjects(UserTeam.class, "teamId = :teamId","teamId",teamInfo.getTeamId());
                
                if (!CollectionUtils.isEmpty(userTeam)) {
                    userTeam.forEach(ut ->{
                        com.amarsoft.app.ems.system.entity.UserInfo u = bomanager.keyLoadBusinessObject(com.amarsoft.app.ems.system.entity.UserInfo.class, ut.getUserId());
                        User user = new User();
                        if (u == null) {
                            return;
                        }
                        user.setUserId(u.getUserId());
                        user.setUserName(u.getUserName());
                        team.getUsers().add(user);
                    });
                }
                rsp.getTeamInfos().add(team);
            }
        }
        return rsp;
    }

    @Override
    public LevelTeamQueryRsp levelTeamQuery(LevelTeamQueryReq req, TeamService teamService, OrgService orgService) {
        LevelTeamQueryRsp rsp = new LevelTeamQueryRsp();
        rsp.setRspList(new ArrayList<CooperateTeam>());
        ConditionalOrgsQueryRsp orgRsp = orgService.orgInfoQueryByCondition(new ConditionalOrgsQueryReq());//查询法人机构
        
        orgRsp.getOrgInfos().stream()
        .forEach(org -> {
            if (!StringUtils.isEmpty(GlobalShareContextHolder.getOrgId())) {//只查询当前登陆机构法人下的团队
                if(!org.getOrgId().equals(orgService.getRootOrgId(GlobalShareContextHolder.getOrgId()))) {
                    return ;
                }
            }
            
            OrgInfoQueryReq orgReq = new OrgInfoQueryReq();//查询登陆机构详情
            orgReq.setOrgId(GlobalShareContextHolder.getOrgId());
            OrgInfoQueryRsp loginOrgInfo = orgService.getOrgInfo(orgReq);
            
            
            CooperateTeam teams = new CooperateTeam();
            teams.setOrgId(org.getOrgId());
            teams.setOrgName(org.getOrgName());
            teams.setOrgType(org.getOrgType());
            
            TeamQueryReq teamReq = new TeamQueryReq();
            teamReq.setBelongRootOrg(org.getOrgId());
            teamReq.setBelongOrgLevel(new ArrayList<>());
            
            if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_1.id) <= 0
                    && CollectionUtils.isEmpty(req.getBelongOrgLevel()) ? true : this.containsAll(req.getBelongOrgLevel(), OrgLevel.LEVEL_1.id)) {//总行角色
                teams.setHeadList(new ArrayList<>());
                teamReq.setBegin(req.getBegin());
                teamReq.setPageSize(req.getPageSize());
                teamReq.getBelongOrgLevel().clear();//清空之前请求
                teamReq.getBelongOrgLevel().add(OrgLevel.LEVEL_1.id);
                TeamQueryRsp teamRsp = teamService.teamQuery(teamReq);
                List<com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo> rspList = teamRsp.getTeamInfos();
                teams.getHeadList().addAll(rspList);
                teams.setHeadTotalCount(teamRsp.getTotalCount());
            }
            if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_2.id) <= 0 
                    && CollectionUtils.isEmpty(req.getBelongOrgLevel()) ? true : this.containsAll(req.getBelongOrgLevel(), OrgLevel.LEVEL_2.id, OrgLevel.LEVEL_3.id)) {//分行角色
                teams.setBranchList(new ArrayList<>());
                teamReq.setBegin(req.getBegin());
                teamReq.setPageSize(req.getPageSize());
                teamReq.getBelongOrgLevel().clear();//清空之前请求
                teamReq.getBelongOrgLevel().add(OrgLevel.LEVEL_2.id);
                teamReq.getBelongOrgLevel().add(OrgLevel.LEVEL_3.id);
                TeamQueryRsp teamRsp = teamService.teamQuery(teamReq);
                List<com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo> rspList = teamRsp.getTeamInfos();
                teams.getBranchList().addAll(rspList);
                teams.setBranchTotalCount(teamRsp.getTotalCount());
            }else if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_3.id) <= 0
                    && CollectionUtils.isEmpty(req.getBelongOrgLevel()) ? true : this.containsAll(req.getBelongOrgLevel(), OrgLevel.LEVEL_3.id)) {//分行角色
                teams.setBranchList(new ArrayList<>());
                teamReq.setBegin(req.getBegin());
                teamReq.setPageSize(req.getPageSize());
                teamReq.getBelongOrgLevel().clear();//清空之前请求
                teamReq.getBelongOrgLevel().add(OrgLevel.LEVEL_3.id);
                TeamQueryRsp teamRsp = teamService.teamQuery(teamReq);
                List<com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo> rspList = teamRsp.getTeamInfos();
                teams.getBranchList().addAll(rspList);
                teams.setBranchTotalCount(teamRsp.getTotalCount());
            }
            if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_4.id) <= 0 
                    && CollectionUtils.isEmpty(req.getBelongOrgLevel()) ? true : this.containsAll(req.getBelongOrgLevel(), OrgLevel.LEVEL_4.id)) {//支行角色
                teams.setSubbranchList(new ArrayList<>());
                teamReq.setBegin(req.getBegin());
                teamReq.setPageSize(req.getPageSize());
                teamReq.getBelongOrgLevel().clear();//清空之前请求
                teamReq.getBelongOrgLevel().add(OrgLevel.LEVEL_4.id);
                TeamQueryRsp teamRsp = teamService.teamQuery(teamReq);
                List<com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo> rspList = teamRsp.getTeamInfos();
                teams.getSubbranchList().addAll(rspList);
                teams.setSubbranchTotalCount(teamRsp.getTotalCount());
            }
            rsp.getRspList().add(teams);
        });
        return rsp;
    }
    
    /**
     * 查询一个数组中是否全量包涵另一个数组
     * @param source　源数组
     * @param target　目标数组
     * @return
     */
    private boolean containsAll(List<?> source, Object... target) {
        if (CollectionUtils.isEmpty(source) || target == null || target.length == 0) {
            return false;
        }
        for (Object t : target) {
            if (!source.contains(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Description: 更新员工团队信息 <br>
     * @param UpdateUserTeamReq(employeeNo、teamId...)
     * @see
     */
    @Override
    @Transactional
    public void updateUserTeam(@RequestBody @Valid UpdateUserTeamReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        String userId = GlobalShareContextHolder.getUserId();
        UserTeam userTeam = bomanager.loadBusinessObject(UserTeam.class, "userId",req.getEmployeeNo());
        if (!ObjectUtils.isEmpty(userTeam)) {// 1.如果员工有对应的团队，则执行更新团队的操作   
            if (userTeam.getTeamId().equals(req.getAfterTeamId())) {   //  2.如果调整前后团队一致，则不允许
                throw new ALSException("EMS6020");
            }
            TeamInfo teamInfo = bomanager.loadBusinessObject(TeamInfo.class, "teamId",req.getTeamId());
            UserBelong userBelong = bomanager.loadBusinessObject(UserBelong.class, "userId",req.getEmployeeNo());
            
            // 3.向employee_belong_change表中添加数据 (修改请求体)
            RequestMessage<EmployeeBelongChangeInfoDtoSaveReq> reqMsg = new RequestMessage<EmployeeBelongChangeInfoDtoSaveReq>();
            EmployeeBelongChangeInfoDtoSaveReq request = new EmployeeBelongChangeInfoDtoSaveReq();
            request.setEmployeeNo(req.getEmployeeNo());
            request.setEmployeeAcct(req.getEmployeeAcct());
            request.setEmployeeName(req.getEmployeeName());
            request.setAdjustReason(req.getAdjustReason());
            request.setTeamNo(req.getTeamId());
            request.setBeforeTeam(req.getTeamId());
            request.setAfterTeam(req.getAfterTeamId());
            request.setChangeManager(req.getRoleAId());
            reqMsg.setMessage(request);
            //4.去新增employee_belong_change
            employeeBelongChangeInfoDtoClient.employeeBelongChangeInfoDtoSave(reqMsg); 
            //5.先删除，再插入信息
            bomanager.deleteObjectBySql(UserTeam.class, "userId=:userId and teamId=:teamId", "userId",req.getEmployeeNo(),"teamId",req.getTeamId());
            UserTeam ut = new UserTeam();
            ut.setTeamId(req.getAfterTeamId());
            ut.setUserId(req.getEmployeeNo());
            
            
            if (!userBelong.getOrgId().equals(teamInfo.getBelongOrgId())) {//如果调整前后团队所属部门不一样
              //6.插入user_belong表中的信息
                UserBelong ub = new UserBelong();
                ub.setUserId(req.getEmployeeNo());
                ub.setOrgId(teamInfo.getBelongOrgId()); 
                ub.setDataAuth(userBelong.getDataAuth());
                ub.setDefaultFlag(userBelong.getDefaultFlag());
                ub.setOriginOrgId(userBelong.getOrgId());
                ub.setMigrationStatus(userBelong.getMigrationStatus());
                ub.setUpdateUserId(userId);
                ub.setUpdateTime(LocalDateTime.now());
                //7.删除表中原来数据后插入新的数据
                bomanager.deleteObjectBySql(UserBelong.class, "userId=:userId and orgId=:orgId", "userId",req.getEmployeeNo(),"orgId",userBelong.getOrgId()); 
                bomanager.updateBusinessObject(ub);  //  8.更新user_belong表中的所属部门
            }
            
            bomanager.updateBusinessObject(ut);    // 9.新增user_team中间表中的所属部门
            
            bomanager.updateDB();
        }      
    }

    /**
     * Description: 部门团队列表展示<br>
     *@param TeamOrgQueryReq
     *@return TeamOrgQueryRsp(OrgAndTeam)
     * ${tags}
     * @see
     */
    @Override
    public TeamOrgQueryRsp orgTeamListQuery(@Valid TeamOrgQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        TeamOrgQueryRsp rsp = new TeamOrgQueryRsp();
        List<OrgAndTeam> orgTeams = new ArrayList<OrgAndTeam>();
        OrgAndTeam orgTeam = null;
        //根据团队名称查询团队，获取参数
        String value = "";
        for (Filter filter : req.getFilters()) {
            if ("teamName".equals(filter.getName())) {
                value = filter.getValue()[0];
            }
        }
        String teamName = StringUtils.isEmpty(value)?"%":value+"%";
        //根据部门ＩＤ将获取的部门团队信息排序(加上模糊查询)
        List<BusinessObject> orgTeamLists = bomanager.selectBusinessObjectsBySql(
                "select UI.userId as UserId,OI.orgName as OrgName,OI.orgId as OrgId,TI.teamId as TeamId,TI.teamName as TeamName,TI.roleA as RoleA "
                + "from OrgTeam OT,TeamInfo TI,OrgInfo OI,UserTeam UT,UserInfo UI where UI.userName = TI.roleA and OT.teamId = TI.teamId "
                + "and OT.orgId = OI.orgId and UT.userId=UI.userId and TI.teamId=UT.teamId and TI.teamName like:teamName order by OT.orgId","teamName",teamName).getBusinessObjects();
        if (!CollectionUtils.isEmpty(orgTeamLists)) {//如果部门团队信息不为空，则遍历循环
            for (BusinessObject businessObject : orgTeamLists) {
                orgTeam = new OrgAndTeam();
                //获取当前部门的附属信息表中的部门管理员
                Department dept = bomanager.loadBusinessObject(Department.class,"deptId",businessObject.getString("OrgId"));
                
                orgTeam.setOrgId(businessObject.getString("OrgId"));
                orgTeam.setOrgName(businessObject.getString("OrgName"));
                orgTeam.setTeamId(businessObject.getString("TeamId"));
                orgTeam.setTeamName(businessObject.getString("TeamName"));
                orgTeam.setDeptManager(dept.getDeptManager());
                orgTeam.setRoleA(businessObject.getString("RoleA"));
                orgTeam.setRoleAId(businessObject.getString("UserId"));
                orgTeams.add(orgTeam);
               
            }
        }
        rsp.setTotalCount(orgTeamLists.size());
        rsp.setOrgTeams(orgTeams);
        return rsp;
    }
	
    /**
     * Description: 根据用户查找对应的团队<br>
     * @param  UserTeamQueryReq(userId)
     * @return UserTeamQueryRsp(teamId,teamName)
     * @see
     */
    @Override
    public UserTeamQueryRsp userTeamQuery(@RequestBody @Valid UserTeamQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserTeamQueryRsp rsp = new UserTeamQueryRsp();
        //查询用户对应的团队信息
        List<BusinessObject> businessObjects = bomanager
            .selectBusinessObjectsBySql("select TI.teamId as TeamId,TI.teamName as TeamName from UserTeam UT,TeamInfo TI where UT.teamId=TI.teamId and UT.userId=:userId", "userId",req.getUserId()).getBusinessObjects();
        String teamId = "";
        String teamName = "";
        if (!CollectionUtils.isEmpty(businessObjects)) {//如果获取的团队信息不为空则将信息返回
           teamId = businessObjects.get(0).getString("TeamId");
           teamName = businessObjects.get(0).getString("TeamName");
        }
        rsp.setTeamId(teamId);
        rsp.setTeamName(teamName);
        return rsp;
    }
    

}