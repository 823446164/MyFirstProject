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
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelteamquery.LevelTeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryRsp;


import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoSaveReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.EmployeeQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.EmployeeQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryRsp;

import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamorgquery.TeamOrgQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.transferteam.TransferTeamReq;
import com.amarsoft.app.ems.system.cs.dto.updateuserteam.UpdateUserTeamReq;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userteamquery.UserTeamQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto.DeleteInfoDtoQueryReq;

public interface TeamController {
    @PostMapping(value = "/team/addteam", name="新增团队信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> addTeam(@RequestBody @Valid RequestMessage<AddTeamReq> reqMsg);
    @PostMapping(value = "/team/getteaminfo", name="查询团队信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<TeamQueryRsp>> teamQuery(@RequestBody @Valid RequestMessage<TeamQueryReq> reqMsg);
    @PostMapping(value = "/team/getorgteam", name="查询部门团队", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<TeamOrgQueryRsp>> teamOrgQuery(@RequestBody @Valid RequestMessage<TeamOrgQueryReq> reqMsg);
       
    @PostMapping(value = "/team/transferteam", name="转移团队负责人", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> transferTeam(@RequestHeader() HttpHeaders header,@RequestBody @Valid RequestMessage<TransferTeamReq> reqMsg);
    
    @PostMapping(value = "/team/getteamsbylevel", name="按法人查询团队", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LevelTeamQueryRsp>> levelTeamQuery(@RequestBody @Valid RequestMessage<LevelTeamQueryReq> reqMsg);
    
    @PostMapping(value = "/teaminfodto/query", name="团队信息查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<TeamInfoDtoQueryRsp>> teamInfoDtoQuery(@RequestBody @Valid RequestMessage<TeamInfoDtoQueryReq> reqMsg);
    
    @PostMapping(value = "/teaminfodto/save", name="团队信息保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> teamInfoDtoSave(@RequestBody @Valid RequestMessage<TeamInfoDtoSaveReq> reqMsg);
   
    @PostMapping(value = "/teamlistdto/query", name="团队信息list查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<TeamListDtoQueryRsp>> teamListDtoQuery(@RequestBody @Valid RequestMessage<TeamListDtoQueryReq> reqMsg);
    
    @PostMapping(value = "/teamlistdto/delete", name="团队删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> teamListDtoDelete(@RequestBody @Valid RequestMessage<DeleteInfoDtoQueryReq> reqMsg);

  
    @PostMapping(value = "/teamlistdto/teamquerybyid", name="根据部门查询团队信息接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<TeamListDtoQueryRsp>> teamQueryById(@RequestBody @Valid RequestMessage<TeamListDtoQueryReq> reqMsg);
   
    @PostMapping(value = "/teaminfodto/updatestatus", name="团队状态更新接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<TeamInfoDtoQueryRsp>> updateStatus(@RequestBody @Valid RequestMessage<TeamInfoDtoQueryReq> reqMsg);
    
    
    @PostMapping(value = "/team/getteamsbyuser", name="按用户查询团队", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserTeamQueryRsp>> userTeamQuery(@RequestBody @Valid RequestMessage<UserTeamQueryReq> reqMsg);
    
    @PostMapping(value = "/team/employee", name="员工团队信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeQueryRsp>> employeeQuery(@RequestBody @Valid RequestMessage<EmployeeQueryReq> reqMsg);

    @PostMapping(value = "/team/updateteamuser", name="更新员工团队", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> updateUserTeam(@RequestBody @Valid RequestMessage<UpdateUserTeamReq> reqMsg);
}
