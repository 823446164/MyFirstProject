/**
 * 查询指定机构内部账户信息
 * @Author xxu1
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.orgaccountadd.OrgAccountAddReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountallquery.OrgAccountAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountallquery.OrgAccountAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccountByOrgQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccountByOrgQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgaccountdelete.OrgAccountDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountquery.OrgAccountQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountquery.OrgAccountQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgaccountupdate.OrgAccountUpdateReq;

import org.springframework.web.bind.annotation.PostMapping;

public interface AccountController {
    @PostMapping(value = "/system/getaccountinfo", name="查询指定机构内部账户信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OrgAccountQueryRsp>> orgAccountQuery(@RequestBody @Valid RequestMessage<OrgAccountQueryReq> reqMsg);
    @PostMapping(value = "/system/getaccountbyorg", name="查询机构内部账户信息（机构）", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OrgAccountByOrgQueryRsp>> orgAccountByOrgQuery(@RequestBody @Valid RequestMessage<OrgAccountByOrgQueryReq> reqMsg);
    @PostMapping(value = "/system/getaccountall", name="查询所有机构内部账户的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OrgAccountAllQueryRsp>> orgAccountAllQuery(@RequestBody @Valid RequestMessage<OrgAccountAllQueryReq> reqMsg);
    @PostMapping(value = "/system/setaccountinfo", name="修改机构内部账户的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> orgAccountUpdate(@RequestBody @Valid RequestMessage<OrgAccountUpdateReq> reqMsg);
    @PostMapping(value = "/system/addaccountinfo", name="新增机构内部账户信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> orgAccountAdd(@RequestBody @Valid RequestMessage<OrgAccountAddReq> reqMsg);
    @PostMapping(value = "/system/deleteaccountinfo", name="删除机构内部账户信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> orgAccountDelete(@RequestBody @Valid RequestMessage<OrgAccountDeleteReq> reqMsg);
}
