/**
 * 按机构编号查询机构
 * @Author xxu1
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
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryReq;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.coperateorganizationquery.CoperateOrganizationQueryReq;
import com.amarsoft.app.ems.system.cs.dto.coperateorganizationquery.CoperateOrganizationQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgidquery.OrgIdQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgidquery.OrgIdQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfoadd.OrgInfoAddReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoallquery.OrgInfoAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoallquery.OrgInfoAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfodelete.OrgInfoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfoupdate.OrgInfoUpdateReq;
import com.amarsoft.app.ems.system.cs.dto.orgtreequery.OrgTreeQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryRsp;

public interface OrgController {
    @PostMapping(value = "/system/getorginfo", name="按机构编号查询机构", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OrgInfoQueryRsp>> orgInfoQuery(@RequestBody @Valid RequestMessage<OrgInfoQueryReq> reqMsg);
    @PostMapping(value = "/system/getorguser", name="查询部门下员工", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OrgUserQueryRsp>> orgUserQuery(@RequestBody @Valid RequestMessage<OrgUserQueryReq> reqMsg);
    @PostMapping(value = "/system/getconditionalorgs", name="按条件查询机构", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ConditionalOrgsQueryRsp>> conditionalOrgsQuery(@RequestBody @Valid RequestMessage<ConditionalOrgsQueryReq> reqMsg);
    @PostMapping(value = "/system/getorgall", name="查询所有机构的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OrgInfoAllQueryRsp>> orgInfoAllQuery(@RequestBody @Valid RequestMessage<OrgInfoAllQueryReq> reqMsg);
    @PostMapping(value = "/system/setorginfo", name="修改指定机构的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> orgInfoUpdate(@RequestBody @Valid RequestMessage<OrgInfoUpdateReq> reqMsg);
    @PostMapping(value = "/system/addorginfo", name="新增机构的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> orgInfoAdd(@RequestBody @Valid RequestMessage<OrgInfoAddReq> reqMsg);
    @PostMapping(value = "/system/deleteorginfo", name="删除指定机构信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> orgInfoDelete(@RequestBody @Valid RequestMessage<OrgInfoDeleteReq> reqMsg);
    @PostMapping(value = "/system/getorgtree", name="机构树图展示", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OrgTreeQueryRsp>> orgTreeQuery();
    @PostMapping(value = "/system/getcoperate", name="按法人查询机构", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<CoperateOrganizationQueryRsp>> coperateOrganizationQuery(@RequestBody @Valid RequestMessage<CoperateOrganizationQueryReq> reqMsg);
    @PostMapping(value = "/system/getorgid", name="获取机构编号", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OrgIdQueryRsp>> orgIdQuery(@RequestBody @Valid RequestMessage<OrgIdQueryReq> reqMsg);
}
