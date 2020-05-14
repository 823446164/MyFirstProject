package com.amarsoft.app.ems.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;

import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDto;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDto;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoSaveReq;
import com.amarsoft.app.ems.system.entity.TeamInfo;
import com.amarsoft.app.ems.system.service.TeamListDtoService;


/**
 * 团队信息Service实现类
 * @author hpli
 */
@Slf4j
@Service
public class TeamListDtoServiceImpl implements TeamListDtoService{
    /**
                   * 查询结果集
     */
    
    
    public static class TeamListDtoReqQuery implements RequestQuery<TeamListDtoQueryReq> {
        @Override
        public Query apply(TeamListDtoQueryReq teamListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(teamListDtoQueryReq, TeamListDto.class);
            
            String sql = "select TINFO.teamId as teamId,TINFO.teamName as teamName,TINFO.roleA as roleA,TINFO.roleB as roleB,TINFO.roleC as roleC,TINFO.belongOrgId as belongOrgId,TINFO.status as status,TINFO.target as target,TINFO.description as description"
                +" from SYS_TEAM_INFO TINFO"
                +" where 1=1";
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
            //查询到的数据转换为响应实体
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
     * @param request
     * @return
     */
    @Override
    @Transactional
    public TeamListDtoQueryRsp teamListDtoQuery(@Valid TeamListDtoQueryReq teamListDtoQueryReq) {
        TeamListDtoQueryRsp teamListDtoQueryRsp = new TeamListDtoQueryRsp();
        //团队员工详情
        Query query = new TeamListDtoReqQuery().apply(teamListDtoQueryReq);
        String fullsql = query.getSql();
        TeamListDtoRspConvert convert = new TeamListDtoRspConvert();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> boa = bomanager.selectBusinessObjectsByNativeSql(teamListDtoQueryReq.getBegin(), teamListDtoQueryReq.getPageSize(), fullsql, query.getParam());
        
        List<BusinessObject> businessObjectList = boa.getBusinessObjects();
        if(null != businessObjectList && !businessObjectList.isEmpty()) {
            List<TeamListDto> teamListDtos = new ArrayList<TeamListDto>();
            for(BusinessObject bo : businessObjectList) {
                //查询到的数据转换为响应实体
                teamListDtos.add(convert.apply(bo));
            }
            teamListDtoQueryRsp.setTeamListDtos(teamListDtos);
        }
        //团队员工检查
        
        teamListDtoQueryRsp.setTotalCount(boa.getAggregate("count(1) as cnt").getInt("cnt"));
        
        return teamListDtoQueryRsp;
    }
    /**
     * 团队信息多记录保存
     * @param request
     * @return
     */
    @Override
    public void teamListDtoSave(@Valid TeamListDtoSaveReq teamListDtoSaveReq) {
        teamListDtoSaveAction(teamListDtoSaveReq.getTeamListDtos());
    }
    /**
     * 团队信息多记录保存
     * @param
     * @return
     */
    @Transactional
    public void teamListDtoSaveAction(List<TeamListDto> teamListDtos){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(teamListDtos!=null){
            for(TeamListDto teamListDtoTmp :teamListDtos){
            }
        }
        bomanager.updateDB();
    }
    /**
     * 团队信息删除
     * @param request
     * @return
     */
    @Override
    @Transactional
    public void teamListDtoDelete(@Valid TeamListDtoDeleteReq teamListDtoDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        TeamInfo teamInfo=bomanager.keyLoadBusinessObject(TeamInfo.class, teamListDtoDeleteReq.getTeamId());
        bomanager.deleteBusinessObject(teamInfo);
        // TODO 关联表数据如需删除的话，请自行补充代码
        bomanager.updateDB();
    }
    /**
     * 团队员工信息多记录查询
     * @param request
     * @return
     */
    @Transactional
    @Override
    public TeamListDtoQueryRsp teamUserQuery(@Valid TeamListDtoQueryReq teamListDtoQueryReq) {
        TeamListDtoQueryRsp   teamListDtoQueryRsp=new   TeamListDtoQueryRsp ();
        // TODO Auto-generated method stub
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        //查询员工信息
        BusinessObjectAggregate<BusinessObject> TeamUser = bomanager.selectBusinessObjectsBySql(" select EI.employeeNo as employeeNo, "
                + "EI.employeeName as employeeName,EI.sex as sex,EI.employeeAcct as employeeAcct,EI. nowRank as  nowRank,EI.rntryTime as rntryTime,"
                + "TI belongOrgId as  belongOrgId ,TI. teamName as  teamName"
                + " from   sys_team_info TI,sys_team_user  TU,employee_info EI where \n" + 
                "    EI.employeeNo=TU.USERID and  TI.TEAMID =TU.TEAMID   and TI.TEAMID=: teamId", "teamId" ,teamListDtoQueryReq.getQuery_teamId());
        List<BusinessObject> userTeam = TeamUser.getBusinessObjects();
        BusinessObject employeeList  = userTeam .get(0);
        teamListDtoQueryRsp.setBelongOrgId(employeeList.getString("BelongOrgId"));
        teamListDtoQueryRsp.setEmployeeAcct(employeeList.getString("EmployeeAcct"));
        teamListDtoQueryRsp.setEmployeeId(employeeList.getString("EmployeeId"));
        teamListDtoQueryRsp.setEmployeeName(employeeList.getString("EmployeeName"));
        teamListDtoQueryRsp.setNowRank(employeeList.getString("NowRank"));
         
        return teamListDtoQueryRsp;
    }
}
