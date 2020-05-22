/*
 * 文件名：TeamServiceImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：团队服务的处理接口
 * 修改人：hpli
 * 修改时间：2020/5/9
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.service;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.app.ems.system.cs.dto.addteam.AddTeamReq;
import com.amarsoft.app.ems.system.cs.dto.addteam.AddTeamRsp;
import com.amarsoft.app.ems.system.cs.dto.addteamuser.AddTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.deleteteam.DeleteTeamReq;
import com.amarsoft.app.ems.system.cs.dto.deleteteam.DeleteTeamRsp;
import com.amarsoft.app.ems.system.cs.dto.deleteteamuser.DeleteTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.transferteam.TransferTeamReq;
import com.amarsoft.app.ems.system.cs.dto.updateuserteam.UpdateUserTeamReq;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryRsp;


public interface TeamService {
    
    /**
     * 添加团队信息
     * @param req
     */
    AddTeamRsp addTeam(AddTeamReq req);
    
    
   
    /**
     * 增加团队用户
     * @param req
     */
    void addTeamUser(AddTeamUserReq req);
    
    /**
     * Description:更新员工团队<br>
     * ${tags}
     * @see
     */
    void updateUserTeam(UpdateUserTeamReq req);
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
     * Description:部门团队列表展示<br>
     * @param TeamOrgQueryReq
     * @return TeamOrgQueryRsp
     * @see
     */
    TeamOrgQueryRsp orgTeamListQuery(@Valid TeamOrgQueryReq req);
    
   
    /**
     * Description:根据用户查找对应的团队<br>
     * @param UserTeamQueryReq(userId)
     * @return UserTeamQueryRsp(teamId,teamName)
     * @see
     */
    public UserTeamQueryRsp userTeamQuery(@RequestBody @Valid UserTeamQueryReq req);



	
  
}