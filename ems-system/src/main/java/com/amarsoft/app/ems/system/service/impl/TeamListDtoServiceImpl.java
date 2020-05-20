package com.amarsoft.app.ems.system.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.amarsoft.aecd.system.constant.ChangeEventType;
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
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoReq;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.EmployeeQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.EmployeeQueryRsp;
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
     * 根据部门/关键字查询团队信息
     * 
     * @param request
     * @return
     */

    @Override
    @Transactional
    public TeamListDtoQueryRsp teamQueryById(TeamListDtoQueryReq request) {

        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        TeamListDtoQueryRsp rsp = new TeamListDtoQueryRsp();
        List<TeamListDto> teamListDtos = new ArrayList<>();
        BusinessObjectAggregate<TeamInfo> loadBusinessObjects = bomanager.loadBusinessObjects(TeamInfo.class,request.getBegin(), request.getPageSize(), "belongOrgId=:orgId", "orgId",
                request.getOrgId());
             List<TeamInfo> businessObjects = loadBusinessObjects.getBusinessObjects();
        if (!CollectionUtils.isEmpty( businessObjects)) {
            for (BusinessObject bs : businessObjects) {
                // 查询员工信息
                String sql = "select count(*) as count , TI.teamName as teamName,TI.teamLeader as teamLeader ,"
                             + "TI.status as status ,TI.teamId as teamId from UserTeam UT,"
                             + "TeamInfo TI where TI.teamId = UT.teamId and  UT.teamId=:teamId";
                String search = "";
                BusinessObjectAggregate<BusinessObject> selectBusinessObjectsBySql = bomanager.selectBusinessObjectsBySql(sql, "teamId",
                    bs.getString("teamId"));
                if (!StringUtils.isEmpty(request.getTeamName())) {
                    search = request.getTeamName();

                    selectBusinessObjectsBySql = bomanager.selectBusinessObjectsBySql(
                        "select count(*) as count ,TI.belongOrgId as belongOrgId," + " TI.teamName as teamName,TI.status as status ,TI.teamLeader as teamLeader ,"
                                                                                      + "TI.teamId as teamId from UserTeam UT,"
                                                                                      + "TeamInfo TI where TI.teamId = UT.teamId and  UT.teamId=:teamId and TI.teamName like: teamName ",
                        "teamId", bs.getString("teamId"), "search", "%" + search + "%");
                }
                else if (!StringUtils.isEmpty(request.getRoleA())) {
                   
                	search = request.getRoleA();
                    selectBusinessObjectsBySql = bomanager.selectBusinessObjectsBySql(
                        "select count(*) as count ,TI.belongOrgId as belongOrgId, TI.teamName as teamName,TI.status as status ,"
                                                                                      + "TI.teamLeader as teamLeader ,TI.teamId as teamId from UserTeam UT,"
                                                                                      + "TeamInfo TI where TI.teamId = UT.teamId and  UT.teamId=:teamId and TI.roleA like: roleA ",
                        "teamId", bs.getString("teamId"), "search", "%" + search + "%");

                }

                List<BusinessObject> businessObject = selectBusinessObjectsBySql.getBusinessObjects();
                if (!CollectionUtils.isEmpty(businessObject)) {
                    for (BusinessObject bos : businessObject) {
                        TeamListDto tl = new TeamListDto();
                        tl.setTeamName(bos.getString("teamName"));
                        tl.setTeamId(bos.getString("teamId"));
                        tl.setRoleA(bos.getString("roleA"));
                        tl.setCount(bos.getInt("count"));
                        tl.setStatus(bos.getString("status"));
                        teamListDtos.add(tl);
                    }
                    
                    rsp.setTeamListDtos(teamListDtos);
                }
            }

        }
        rsp.setTotalCount(loadBusinessObjects == null ? 0 :loadBusinessObjects.getAggregate("count(belongOrgId) as count").getInt("count"));
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
        
        if (!"2".equals(delTeam.getStatus())) {//5.若团队不为停用状态，不许操作
            throw new ALSException("EMS6030");
        }else if(!CollectionUtils.isEmpty(userTeams)) {//6.若团队下有员工，不许删除
            throw new ALSException("EMS6032");
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
     * 
     * 员工团队信息
     * @param request
     * @return response 
     */
    @Transactional
    @Override
    public EmployeeQueryRsp employeeQuery(@Valid EmployeeQueryReq employeeQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<String> employeeLists = new ArrayList<String>();
        EmployeeQueryRsp response = new EmployeeQueryRsp();
        List<BusinessObject> businessObjects = bomanager.selectBusinessObjectsBySql(
            "select userId as userId from UserTeam   where teamId=:teamId", "teamId", employeeQueryReq.getTeamId()).getBusinessObjects();
        if (!CollectionUtils.isEmpty(businessObjects)) {
            for (BusinessObject busis : businessObjects) {
                employeeLists.add(busis.getString("userId"));

            }
        }
        EmployeeListByEmplNoReq req = new EmployeeListByEmplNoReq();
        req.setEmployeeNoList(employeeLists);
        RequestMessage<EmployeeListByEmplNoReq> reqMsg = new RequestMessage<EmployeeListByEmplNoReq>();
        reqMsg.setMessage(req);
        if (!CollectionUtils.isEmpty(employeeLists)) {
            ResponseEntity<ResponseMessage<EmployeeListByEmplNoRsp>> employeeListByEmployeeNoQuery = employeeListDtoClient.employeeListByEmployeeNoQuery(reqMsg);
            EmployeeListByEmplNoRsp message = employeeListByEmployeeNoQuery.getBody().getMessage();
            response.setEmployeeInfoList(message.getEmployeeInfoList());
        }

        return response;
    }
    /**
     * 
     * 按条件搜所团队信息
     * @param request
     * @return response 
     */
    @Transactional
    @Override
	public TeamListDtoQueryRsp teamSearch(TeamListDtoQueryReq req) {
	
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
    	
    	  TeamListDtoQueryRsp rsp =new  TeamListDtoQueryRsp();
    	  List<TeamListDto> teamListDtos =new ArrayList<TeamListDto>();
    	  TeamListDto dto =new TeamListDto();
    	  List<BusinessObject>  bos=null;
    	  //按照查询团队名称进行查询
    	  if(!StringUtils.isEmpty(req.getTeamName())) {
    		 bos = bomanager.selectBusinessObjectsBySql("select count(1) as cnt, UT.teamId as teamId ,TI.teamName as teamName ,TI.roleA as roleA from UserTeam  UT ,"
    		 		+ "TeamInfo TI where  TI.teamId =UT.teamId and TI.teamName like :teamName","teamName",req.getTeamName()+"%").getBusinessObjects();
    		//遍历bos
    		 for( BusinessObject bo :bos) {
    			int count= bos.get(0).getInt("cnt");
    			dto.setRoleA(bo.getString("roleA"));	
    			dto.setTeamName(bo.getString("teamName"));
    			dto.setCount(count);
    			teamListDtos.add(dto);
    		}
    		 rsp.setTeamListDtos(teamListDtos);
    		
    	  } else if(!StringUtils.isEmpty(req.getRoleA())) {
    		  bos = bomanager.selectBusinessObjectsBySql("select count(1) as cnt,UT.teamId as teamId , TI.teamName as teamName ,"
    		  		+ "TI.roleA as roleA from UserTeam  UT ,TeamInfo TI where   TI.teamId =UT.teamId and TI.roleA like:roleA","roleA",req.getRoleA()+"%").getBusinessObjects();
        		for( BusinessObject bo :bos) {
        			int count= bos.get(0).getInt("cnt");
        			dto.setRoleA(bo.getString("roleA"));	
        			dto.setTeamName(bo.getString("teamName"));
        			dto.setCount(count);
        			teamListDtos.add(dto);
    		}
        		 rsp.setTeamListDtos(teamListDtos);
    	  } else {
    		  throw new ALSException("901013");
    	  }
    	  return rsp;
	}
}
