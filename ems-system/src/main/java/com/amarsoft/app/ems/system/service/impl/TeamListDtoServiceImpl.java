package com.amarsoft.app.ems.system.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.aecd.system.constant.OrgStatus;
import com.amarsoft.amps.acsc.query.QueryProperties;
import com.amarsoft.amps.acsc.util.DTOHelper;
import com.amarsoft.amps.arem.exception.ALSException;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.acsc.query.QueryProperties.Query;
import com.amarsoft.amps.avts.convert.Convert;
import com.amarsoft.amps.avts.query.RequestQuery;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDto;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoDeleteRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryReq;

import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoSaveReq;
import com.amarsoft.app.ems.system.entity.ChangeEvent;
import com.amarsoft.app.ems.system.entity.TeamInfo;
import com.amarsoft.app.ems.system.service.TeamListDtoService;
import com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto.DeleteInfoDtoQueryReq;


/**
 * 团队信息Service实现类
 * 
 * @author hpli
 */
@Slf4j
@Service
public class TeamListDtoServiceImpl implements TeamListDtoService {

    /**
     * 查询结果集
     */

    public static class TeamListDtoReqQuery implements RequestQuery<TeamListDtoQueryReq> {
        @Override
        public Query apply(TeamListDtoQueryReq teamListDtoQueryReq) {
            QueryProperties queryProperties = DTOHelper.getQueryProperties(teamListDtoQueryReq, TeamListDto.class);

            String sql = "select TINFO.teamId as teamId,TINFO.teamName as teamName,TINFO.roleA as roleA,TINFO.roleB as roleB,TINFO.roleC as roleC,TINFO.belongOrgId as belongOrgId,TINFO.status as status,TINFO.target as target,TINFO.description as description"
                         + " from SYS_TEAM_INFO TINFO" + " where 1=1";
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
     * 团队信息多记录保存
     * 
     * @param request
     * @return
     */
    @Override
    public void teamListDtoSave(@Valid TeamListDtoSaveReq teamListDtoSaveReq) {
        teamListDtoSaveAction(teamListDtoSaveReq.getTeamListDtos());
    }

    /**
     * 团队信息多记录保存
     * 
     * @param
     * @return
     */
    @Transactional
    public void teamListDtoSaveAction(List<TeamListDto> teamListDtos) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (teamListDtos != null) {
            for (TeamListDto teamListDtoTmp : teamListDtos) {}
        }
        bomanager.updateDB();
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
        List<BusinessObject> loadBusinessObjects = bomanager.loadBusinessObjects(TeamInfo.class, "belongOrgId=:orgId", "orgId",
            request.getOrgId());
        if (loadBusinessObjects != null && loadBusinessObjects.size() > 0) {
            for (BusinessObject bs : loadBusinessObjects) {
                // 根据bs查询员工信息
                String sql = "select count(*) as count , TI.teamName as teamName,TI.teamLeader as teamLeader ,TI.status as status ,TI.teamId as teamId from UserTeam UT,"
                             + "TeamInfo TI where TI.teamId = UT.teamId and  UT.teamId=:teamId";
                String search = "";
                BusinessObjectAggregate<BusinessObject> selectBusinessObjectsBySql = bomanager.selectBusinessObjectsBySql(sql, "teamId",
                    bs.getString("teamId"));
                if (!StringUtils.isEmpty(request.getTeamName())) {
                    search = request.getTeamName();

                    selectBusinessObjectsBySql = bomanager.selectBusinessObjectsBySql(
                        "select count(*) as count ,TI.belongOrgId as belongOrgId, TI.teamName as teamName,TI.status as status ,TI.teamLeader as teamLeader ,TI.teamId as teamId from UserTeam UT,"
                                                                                      + "TeamInfo TI where TI.teamId = UT.teamId and  UT.teamId=:teamId and TI.teamName like: teamName ",
                        "teamId", bs.getString("teamId"), "search", "%" + search + "%");
                }
                else if (!StringUtils.isEmpty(request.getGetRoleA())) {
                    search = request.getGetRoleA();

                    selectBusinessObjectsBySql = bomanager.selectBusinessObjectsBySql(
                        "select count(*) as count ,TI.belongOrgId as belongOrgId, TI.teamName as teamName,TI.status as status ,TI.teamLeader as teamLeader ,TI.teamId as teamId from UserTeam UT,"
                                                                                      + "TeamInfo TI where TI.teamId = UT.teamId and  UT.teamId=:teamId and TI.roleA like: roleA ",
                        "teamId", bs.getString("teamId"), "search", "%" + search + "%");

                }

                
                List<BusinessObject> businessObjects = selectBusinessObjectsBySql.getBusinessObjects();
                if (businessObjects != null && businessObjects.size() > 0) {
                    for (BusinessObject bos : businessObjects) {
                        TeamListDto tl = new TeamListDto();
                        tl.setTeamName(bos.getString("teamName"));
                        tl.setTeamId(bos.getString("teamId"));
                        tl.setRoleA(bos.getString("teamLeader"));
                        tl.setCount(bos.getInt("count"));
                        tl.setStatus(bos.getString("status"));
                        teamListDtos.add(tl);
                    }
                    rsp.setTeamListDtos(teamListDtos);
                }
            }

        }
        return rsp;
    }

    /**
     * 删除团队信息
     * 
     * @param request
     * @return
     */
    @Transactional
    @Override
    public void teamListDtoDelete(@Valid DeleteInfoDtoQueryReq req) {

        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        TeamInfo teamInfo = bomanager.keyLoadBusinessObject(TeamInfo.class, "teamId =:req.getObjectNo()", req.getSerialNo());
        TeamListDtoDeleteRsp rsp = new TeamListDtoDeleteRsp();
        if (teamInfo == null) {
            throw new ALSException("901007");
        }
        ChangeEvent ch = new ChangeEvent();
        // DeleteInfoDto
        // 填写变更理由
        ch.generateKey();
        ch.setObjectNo(req.getObjectNo());
        ch.setObjectType("DELETE");
        ch.setChangeContext("删除团队信息:" + req.getObjectNo());
        ch.setInputDate(LocalDateTime.now());
        ch.setOccurDate(LocalDateTime.now());
        BusinessObjectAggregate<BusinessObject> userTeamBo = bomanager.selectBusinessObjectsBySql(
            "select UT.userId as userId,TI.status as status from TeamInfo TI,UserTeam UT where" + " TI.teamId=UT.teamId and UT.teamId =:teamId",
            "teamId", teamInfo.getTeamId());

        List<BusinessObject> businessObjects = userTeamBo.getBusinessObjects();
        if (businessObjects != null && businessObjects.size() > 0) {
            throw new ALSException("901007");
        }
        if (teamInfo.getRoleA() != null && teamInfo.getStatus().equals(OrgStatus.Disabled.id)) {
            throw new ALSException("EMS6008");
        }
        bomanager.updateBusinessObject(ch);
        bomanager.deleteBusinessObject(teamInfo);
        bomanager.updateDB();
    }

}
