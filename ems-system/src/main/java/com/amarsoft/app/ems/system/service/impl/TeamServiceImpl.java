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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.app.ems.system.cs.dto.teamorgquery.OrgAndTeam;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.aecd.system.constant.DataAuth;
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.aecd.system.constant.OrgStatus;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.employee.cs.dto.employeebelongupdate.EmployeeBelongUpdateReq;
import com.amarsoft.app.ems.employee.template.cs.client.EmployeeInfoDtoClient;
import com.amarsoft.app.ems.system.cs.dto.addteam.AddTeamReq;
import com.amarsoft.app.ems.system.cs.dto.addteam.AddTeamRsp;
import com.amarsoft.app.ems.system.cs.dto.addteamuser.AddTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryReq;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.deleteteam.DeleteTeamReq;
import com.amarsoft.app.ems.system.cs.dto.deleteteam.DeleteTeamRsp;
import com.amarsoft.app.ems.system.cs.dto.deleteteamuser.DeleteTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.getteamid.GetTeamIdRsp;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.CooperateTeam;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.transferteam.TransferTeamReq;
import com.amarsoft.app.ems.system.cs.dto.updateteam.UpdateTeamReq;
import com.amarsoft.app.ems.system.cs.dto.updateteam.UpdateTeamRsp;
import com.amarsoft.app.ems.system.cs.dto.updateuserteam.UpdateUserTeamReq;
import com.amarsoft.app.ems.system.cs.dto.userquery.User;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryRsp;
import com.amarsoft.app.ems.system.entity.Department;
import com.amarsoft.app.ems.system.entity.OrgInfo;
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
    /**
     * Description: <br>
     * 新增团队<br>
     * ${tags}
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
        teamInfo.setRoleA(req.getTeamLeader());
        List<UserBelong> userBelongs = bomanager.loadBusinessObjects(UserBelong.class, "userId = :userId and orgId = :orgId",
                "userId",req.getTeamLeader(),"orgId",req.getBelongOrgId());
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
     * Description: <br>
     * 1、团队状态完成<br>
     * ${tags}
     * @see
     */
    @Override
    public UpdateTeamRsp  updateTeam(UpdateTeamReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        TeamInfo teamInfo = bomanager.keyLoadBusinessObject(TeamInfo.class, req.getTeamId());
        UpdateTeamRsp  updateTeamRsp =new   UpdateTeamRsp();
            if(req.getStatus().equals(OrgStatus.Completed.id)) {
                updateTeamRsp.setMeassage("团队状态已完成"); 
                return  updateTeamRsp;
            }
           
        teamInfo.setTeamName(req.getTeamName());
        teamInfo.setStatus(req.getStatus());
        teamInfo.setDescription(req.getDescription());
       
        bomanager.updateBusinessObject(teamInfo);
        bomanager.updateDB();
        return  updateTeamRsp;
    }
    /**
     * Description:团队状态停用 <br>
     * <br>
     * ${tags}
     * @see
     */
    @Override
    public UpdateTeamRsp  updateTeamStatus(UpdateTeamReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        TeamInfo teamInfo = bomanager.keyLoadBusinessObject(TeamInfo.class, req.getTeamId());
        UpdateTeamRsp  updateTeamRsp =new   UpdateTeamRsp();
            if(req.getStatus().equals(OrgStatus.Disabled.id)) {
                updateTeamRsp.setMeassage("团队已停用");  
                return  updateTeamRsp;
            }
           //查询团队下员工
            BusinessObjectAggregate<BusinessObject> userTeamListBoa = bomanager.selectBusinessObjectsBySql("select UT.userId as userId,TI.roleA as roleA from TeamInfo TI,UserTeam UT where"
                + " TI.teamId=UT.teamId and UT.teamId =:teamId","teamId",req.getTeamId());
            List<BusinessObject> userTeamList = userTeamListBoa.getBusinessObjects();
            if(userTeamList!=null &&userTeamList.size()>0) {
                updateTeamRsp.setMeassage("团队已停用");  
                return  updateTeamRsp;
            }
        teamInfo.setTeamName(req.getTeamName());
        teamInfo.setStatus(req.getStatus());
        teamInfo.setDescription(req.getDescription());
       
        bomanager.updateBusinessObject(teamInfo);
        bomanager.updateDB();
        return  updateTeamRsp;
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

    @Override
    public void transferTeam(HttpHeaders header,TransferTeamReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        TeamInfo teamInfo = bomanager.keyLoadBusinessObject(TeamInfo.class, req.getTeamId());
        String userId = GlobalShareContextHolder.getUserId();
        if(req.getSystemChangeFlag().equals(YesNo.No.id) || StringUtils.isEmpty(req.getSystemChangeFlag())) {
            if(!teamInfo.getTeamLeader().equals(userId))
                throw new ALSException("901012");
        }
        teamInfo.setTeamLeader(req.getTeamLeader());
        bomanager.updateBusinessObject(teamInfo);
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
            }else if (!StringUtils.isEmpty(req.getTeamLeader())) {//按团队长查询
                teamAggregate = bomanager.loadBusinessObjects(TeamInfo.class, req.getBegin(), req.getPageSize(), "teamLeader = :teamLeader","teamLeader",req.getTeamLeader());
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
                team.setTeamLeader(teamInfo.getTeamLeader());
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
     * Description: 删除团队信息<br>
     *<br>
     * ${tags}
     * @see
     */
    @Override
    public DeleteTeamRsp deleteTeam(DeleteTeamReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        TeamInfo deleteTeam = bomanager.keyLoadBusinessObject(TeamInfo.class, req.getTeamId());
        BusinessObjectAggregate<BusinessObject>  userTeamBo= bomanager.selectBusinessObjectsBySql("select UT.userId as userId,TI.status as status from TeamInfo TI,UserTeam UT where"
            + " TI.teamId=UT.teamId and UT.teamId =:teamId", "teamId",req.getTeamId());
        List<BusinessObject> businessObjects = userTeamBo.getBusinessObjects();
        DeleteTeamRsp deleteTeamRsp =new   DeleteTeamRsp ();
        if(businessObjects!=null&&businessObjects.size()>0) {
            deleteTeamRsp.setIsExit(true);
            deleteTeamRsp.setMeassage("团队下存在员工，请勿删除");
        }
        if(deleteTeam.getRoleA()!=null||deleteTeam.getStatus()=="1" ) {
            deleteTeamRsp.setIsExit(true);
            deleteTeamRsp.setMeassage("该团队存在负责人，请核实后再操作！"); 
        }
       
        if (deleteTeam == null) {
            throw new ALSException("901007");
        }
        bomanager.deleteBusinessObject(deleteTeam);
        bomanager.updateDB();
        return deleteTeamRsp;
    }

    @Override
    public GetTeamIdRsp getTeamId() {
        GetTeamIdRsp rsp = new GetTeamIdRsp();
        TeamInfo team = new TeamInfo();
        team.generateKey();
        rsp.setTeamId(team.getKeyString());
        return rsp;
    }
    
    /**
     * Description:根据部门编号查询团队信息 <br>
     * <br>
     * ${tags}
     * @see
     */
    @Override
    public TeamQueryRsp teamQueryById(TeamQueryReq req) {
        // TODO Auto-generated method stub
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        //根据部门编号查询团队信息
        bomanager.clear();
       // String orgId = req.getOrgId();
        BusinessObjectAggregate<BusinessObject> TeamQueryReqBoa = bomanager.selectBusinessObjectsBySql(
            "select  count(*), UT.userId as userId ,TI.teamId as teamId,TI.teamName as teamName,TI.teamLeader as  teamLeader "
            + "from UserTeam UT, TeamInfo TI "
            + "where TI.teamId  =UT.teamId and  TI.belongOrgId= :belongOrgId  ","belongOrgId", req.getBelongOrgId());
        /**
   "select  count(*), UT.userId as userId from UserTeam UT "
            + "inner join (select TI.teamId as teamId  ,TI.teamname as teamname ,TI.teamLeader as  teamLeader from TeamInfo TI"
            + " where TI.belongOrgId= :belongOrgId ) UT on TI.TEAMID =UT.TEAMID"

         */
        List<BusinessObject> TeamQueryList = TeamQueryReqBoa.getBusinessObjects();
        TeamQueryRsp  teamQueryRsp =new TeamQueryRsp ();
        if(TeamQueryList !=null && TeamQueryList.size()>0) {
            List list = new ArrayList<TeamInfo>();
            //遍历TeamQueryList
         for(BusinessObject team:TeamQueryList) {
             com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo t = new com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo();
             t.setTeamId(team.getString("teamId"));
              t.setTeamName(team.getString("teamName"));
              t.setTeamLeader(team.getString("teamLeader"));
              //设置团队的总人数
              t.setCount(team.getInt("count"));
              list.add(t); 
             }
         teamQueryRsp.setTeamInfos(list);
        }
        return teamQueryRsp;  
    }

    /**
     * Description: 更新员工团队信息 <br>
     *<br>
     * ${tags}
     * @see
     */
	@Override
	@Transactional
	public void updateUserTeam(@RequestBody @Valid UpdateUserTeamReq req) {
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		
		UserTeam userTeam = bomanager.loadBusinessObject(UserTeam.class, "userId",req.getEmployeeNo());
		if (!StringUtils.isEmpty(userTeam)) {// 如果员工有对应的团队，则执行更新团队的操作   
		    if (userTeam.getTeamId().equals(req.getTeamId())) {   //  如果调整前后团队一致，则不允许
                throw new ALSException("901020");
            }
		    TeamInfo teamInfo = bomanager.loadBusinessObject(TeamInfo.class, "teamId",req.getTeamId());
		    UserBelong userBelong = bomanager.loadBusinessObject(UserBelong.class, "userId",req.getEmployeeNo());
			userTeam.setTeamId(req.getTeamId());
			userBelong.setOrgId(teamInfo.getBelongOrgId()); 
			
			bomanager.updateBusinessObject(userTeam);    //  更新user_team中间表中的所属部门
			bomanager.updateBusinessObject(userBelong);  //  更新user_belong表中的所属部门

			bomanager.updateDB();
		}
		
	}

    /**
     * Description: 部门团队列表展示<br>
     *部门团队列表展示<br>
     * ${tags}
     * @see
     */
	@Override
	public TeamOrgQueryRsp orgTeamListQuery(@Valid TeamOrgQueryReq req) {
		BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
		TeamOrgQueryRsp rsp = new TeamOrgQueryRsp();
		List<OrgAndTeam> orgTeams = new ArrayList<OrgAndTeam>();
		OrgAndTeam orgTeam = null;
		List<BusinessObject> orgTeamLists = bomanager.selectBusinessObjectsBySql(
				"select OI.orgName as OrgName,OI.orgId as OrgId,TI.teamId as TeamId,TI.teamName as TeamName,TI.teamLeader as TeamLeader "
				+ "from OrgTeam OT,TeamInfo TI,OrgInfo OI where OT.teamId = TI.teamId and OT.orgId = OI.orgId order by OT.orgId").getBusinessObjects();
		if (!StringUtils.isEmpty(orgTeamLists)) {
			for (BusinessObject businessObject : orgTeamLists) {
				orgTeam = new OrgAndTeam();
				//获取当前部门的附属信息表中的部门管理员
				Department dept = bomanager.loadBusinessObject(Department.class,"deptId",businessObject.getString("OrgId"));
                if (!StringUtils.isEmpty(dept)) {
                    orgTeam.setOrgId(businessObject.getString("OrgId"));
                    orgTeam.setOrgName(businessObject.getString("OrgName"));
                    orgTeam.setTeamId(businessObject.getString("TeamId"));
                    orgTeam.setTeamName(businessObject.getString("TeamName"));
                    orgTeam.setDeptManager(dept.getDeptManager());
                    orgTeam.setTeamLeader(businessObject.getString("TeamLeader"));
                    orgTeams.add(orgTeam);
                }
			}
		}
		rsp.setTotalCount(orgTeamLists.size());
		rsp.setOrgTeams(orgTeams);
		return rsp;
	}
	 /**
     * Description: 根据条件查询团队信息<br>
     * ${tags}
     * @see
     */
	
    @Override
    public TeamQueryRsp teamSearch(TeamQueryReq req) {
        // TODO Auto-generated method stub
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager(); 
        BusinessObjectAggregate<TeamInfo> teamAggregate = null;
        List<TeamInfo> teamInfos = new ArrayList<TeamInfo>();
        //根据团队负责人查询团队信息
        String[] searchAttributes = {"teamLeader","teamName"};//查询条件
        if (req.getBegin() == null || req.getPageSize() == null) { //如果传入分页信息为空则展示所有
            req.setBegin(0);
            req.setPageSize(Integer.MAX_VALUE);
            }
       if (!StringUtils.isEmpty(req.getTeamName())) {//按团队名称查询
            teamAggregate = bomanager.loadBusinessObjects(TeamInfo.class, req.getBegin(), req.getPageSize(), "teamName = :teamName ","teamName ",req.getTeamName());
        }else if (!StringUtils.isEmpty(req.getTeamLeader())) {//按团队负责人查询
            teamAggregate = bomanager.loadBusinessObjects(TeamInfo.class, req.getBegin(), req.getPageSize(), "teamLeader = :teamLeader","teamLeader",req.getTeamLeader());
        }else {//查询所有
            teamAggregate = bomanager.loadBusinessObjects(TeamInfo.class, req.getBegin(), req.getPageSize(), "1 = 1");
            }
       TeamQueryRsp rsp = new TeamQueryRsp();
       rsp.setTotalCount(teamAggregate == null ? 1 : teamAggregate.getAggregate("count(teamId) as cnt").getInt("cnt")); // 为空则为查询详情，不为空返回集合数量
       rsp.setTeamInfos(new ArrayList<com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo>());
       return rsp;
      
       
    }

	 /**
     * Description: 根据用户查找对应的团队<br>
     * ${tags}
     * @see
     */
    @Override
    public UserTeamQueryRsp userTeamQuery(@RequestBody @Valid UserTeamQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserTeamQueryRsp rsp = new UserTeamQueryRsp();
        List<BusinessObject> businessObjects = bomanager.selectBusinessObjectsBySql("select TI.teamId as TeamId,TI.teamName as TeamName from UserTeam UT,TeamInfo TI where UT.teamId=TI.teamId and UT.userId=:userId", "userId",req.getUserId()).getBusinessObjects();
        String teamId = "";
        String teamName = "";
        if (!StringUtils.isEmpty(businessObjects)) {
           teamId = businessObjects.get(0).getString("TeamId");
           teamName = businessObjects.get(0).getString("TeamName");
        }
        rsp.setTeamId(teamId);
        rsp.setTeamName(teamName);
        return rsp;
    }
}