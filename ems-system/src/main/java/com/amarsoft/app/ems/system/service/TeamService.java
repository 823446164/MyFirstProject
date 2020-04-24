package com.amarsoft.app.ems.system.service;

import org.springframework.http.HttpHeaders;

import com.amarsoft.app.ems.system.cs.dto.addteam.AddTeamReq;
import com.amarsoft.app.ems.system.cs.dto.addteamuser.AddTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.deleteteam.DeleteTeamReq;
import com.amarsoft.app.ems.system.cs.dto.deleteteamuser.DeleteTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.getteamid.GetTeamIdRsp;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.transferteam.TransferTeamReq;
import com.amarsoft.app.ems.system.cs.dto.updateteam.UpdateTeamReq;

/**
 * 团队服务的处理接口
 * @author hzhang23
 */
public interface TeamService {
    /**
     * 添加团队信息
     * @param req
     */
    void addTeam(AddTeamReq req);
    
    /**
     * 更新团队信息
     * @param req
     */
    void updateTeam(UpdateTeamReq req);

    /**
     * 增加团队用户
     * @param req
     */
    void addTeamUser(AddTeamUserReq req);
    /**
     * 删除团队用户
     * @param req
     */
    void deleteTeamUser(DeleteTeamUserReq req);
    /**
     * 转移团队
     * @param header 
     * @param req
     */
    void transferTeam(HttpHeaders header, TransferTeamReq req);
    /**
     * 团队查询
     * @param orgService 
     * @param req
     */
    TeamQueryRsp teamQuery(TeamQueryReq message);
    /**
     * 按法人查询团队
     */
    LevelTeamQueryRsp levelTeamQuery(LevelTeamQueryReq req,TeamService teamService, OrgService orgService);

    /**
     * 删除团队
     * @param message
     */
    void deleteTeam(DeleteTeamReq req);

    /**
     * 获取团队编号
     */
    GetTeamIdRsp getTeamId();
}