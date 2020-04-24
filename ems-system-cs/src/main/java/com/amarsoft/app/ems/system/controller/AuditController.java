/**
 * 安全信息汇总
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.allauthchangesquery.AllAuthChangesQueryReq;
import com.amarsoft.app.ems.system.cs.dto.allauthchangesquery.AllAuthChangesQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.alllogininfoquery.AllLoginInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.alllogininfoquery.AllLoginInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.allsecurityinfoquery.AllSecurityInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.onlinenumquery.OnlineNumQueryReq;
import com.amarsoft.app.ems.system.cs.dto.onlinenumquery.OnlineNumQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.onlineuserlist.OnlineUserListReq;
import com.amarsoft.app.ems.system.cs.dto.onlineuserlist.OnlineUserListRsp;

public interface AuditController {
    @PostMapping(value = "/audit/getallsecurityinfo", name="安全信息汇总", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<AllSecurityInfoQueryRsp>> allSecurityInfoQuery();
    @PostMapping(value = "/audit/getauthchanges", name="用户角色变更情况", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<AllAuthChangesQueryRsp>> allAuthChangesQuery(@RequestBody @Valid RequestMessage<AllAuthChangesQueryReq> reqMsg);
    @PostMapping(value = "/audit/getlogininfo", name="用户登录信息一览", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<AllLoginInfoQueryRsp>> allLoginInfoQuery(@RequestBody @Valid RequestMessage<AllLoginInfoQueryReq> reqMsg);
    @PostMapping(value = "/audit/getonlinenum", name="在线人数统计", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OnlineNumQueryRsp>> onlineNumQuery(@RequestBody @Valid RequestMessage<OnlineNumQueryReq> reqMsg);
    @PostMapping(value = "/audit/getonlinepeople", name="在线用户查询", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OnlineUserListRsp>> onlineUserList(@RequestBody @Valid RequestMessage<OnlineUserListReq> reqMsg);
}
