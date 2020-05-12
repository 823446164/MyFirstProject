package com.amarsoft.app.ems.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDto;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoSaveReq;
import com.amarsoft.app.ems.system.entity.TeamInfo;
import com.amarsoft.app.ems.system.service.TeamInfoDtoService;


/**
 * 团队信息Service实现类
 * @author hpli
 */
@Slf4j
@Service
public class TeamInfoDtoServiceImpl implements TeamInfoDtoService{
    /**
     * 团队信息单记录查询
     * @param request
     * @return
     */
    
    @Override
    @Transactional
    public TeamInfoDtoQueryRsp teamInfoDtoQuery(@Valid TeamInfoDtoQueryReq teamInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        TeamInfo teamInfo = bomanager.loadBusinessObject(TeamInfo.class,"teamId",teamInfoDtoQueryReq.getTeamId());
        if(teamInfo!=null){
            TeamInfoDtoQueryRsp teamInfoDto = new TeamInfoDtoQueryRsp();
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

        return null;
    }

    /**
     * 团队信息单记录保存
     * @param request
     * @return
     */
    @Override
    public void teamInfoDtoSave(@Valid TeamInfoDtoSaveReq teamInfoDtoSaveReq) {
        teamInfoDtoSaveAction(teamInfoDtoSaveReq);
    }
    /**
     * 团队信息单记录保存
     * @param
     * @return
     */
    @Transactional
    public void teamInfoDtoSaveAction(TeamInfoDto teamInfoDto){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(teamInfoDto!=null){
        }
        bomanager.updateDB();
    }
}
