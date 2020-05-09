/**
 * 新增团队信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.addteam.AddTeamReq;
import com.amarsoft.app.ems.system.cs.dto.addteamuser.AddTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.deleteteam.DeleteTeamReq;
import com.amarsoft.app.ems.system.cs.dto.deleteteamuser.DeleteTeamUserReq;
import com.amarsoft.app.ems.system.cs.dto.getteamid.GetTeamIdRsp;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.transferteam.TransferTeamReq;
import com.amarsoft.app.ems.system.cs.dto.updateteam.UpdateTeamReq;
import com.amarsoft.app.ems.system.cs.dto.updateuserteam.UpdateUserTeamReq;

public interface TeamController {
    @PostMapping(value = "/team/addteam", name="新增团队信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> addTeam(@RequestBody @Valid RequestMessage<AddTeamReq> reqMsg);
    @PostMapping(value = "/team/getteaminfo", name="查询团队信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<TeamQueryRsp>> teamQuery(@RequestBody @Valid RequestMessage<TeamQueryReq> reqMsg);
    @PostMapping(value = "/team/getorgteam", name="查询部门团队", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<TeamOrgQueryRsp>> teamOrgQuery(@RequestBody @Valid RequestMessage<TeamOrgQueryReq> reqMsg);

    @PostMapping(value = "/team/updateteam", name="更新团队信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> updateTeam(@RequestBody @Valid RequestMessage<UpdateTeamReq> reqMsg);
    @PostMapping(value = "/team/addteamuser", name="添加团队成员", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> addTeamUser(@RequestBody @Valid RequestMessage<AddTeamUserReq> reqMsg);
    @PostMapping(value = "/team/updateteamuser", name="更新员工团队", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> updateUserTeam(@RequestBody @Valid RequestMessage<UpdateUserTeamReq> reqMsg);
    @PostMapping(value = "/team/deleteteamuser", name="删除团队成员", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> deleteTeamUser(@RequestBody @Valid RequestMessage<DeleteTeamUserReq> reqMsg);
    @PostMapping(value = "/team/deleteteam", name="删除团队", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> deleteTeam(@RequestBody @Valid RequestMessage<DeleteTeamReq> reqMsg);
    @PostMapping(value = "/team/transferteam", name="转移团队负责人", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> transferTeam(@RequestHeader() HttpHeaders header,@RequestBody @Valid RequestMessage<TransferTeamReq> reqMsg);
    @PostMapping(value = "/team/getteamsbylevel", name="按法人查询团队", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LevelTeamQueryRsp>> levelTeamQuery(@RequestBody @Valid RequestMessage<LevelTeamQueryReq> reqMsg);
    @PostMapping(value = "/team/getteamid", name="按法人查询团队", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<GetTeamIdRsp>> getTeamId();
    
}
