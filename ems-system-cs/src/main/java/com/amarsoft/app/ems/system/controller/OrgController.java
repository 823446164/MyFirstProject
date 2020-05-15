/*
 * 文件名：OrgController.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：zcluo
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
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
import com.amarsoft.app.ems.system.cs.dto.orgtreequery.OrgTreeQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgtreequery.OrgTreeQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto.DeleteInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoSearchReq;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDtoSaveReq;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptinfodto.SecondLevelDeptInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptinfodto.SecondLevelDeptInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDtoQueryRsp;

public interface OrgController {
    @PostMapping(value = "/system/getorginfo", name="按机构编号查询机构", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OrgInfoQueryRsp>> orgInfoQuery(@RequestBody @Valid RequestMessage<OrgInfoQueryReq> reqMsg);
    @PostMapping(value = "/system/getconditionalorgs", name="按条件查询机构", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ConditionalOrgsQueryRsp>> conditionalOrgsQuery(@RequestBody @Valid RequestMessage<ConditionalOrgsQueryReq> reqMsg);
    @PostMapping(value = "/system/getorgall", name="查询所有机构的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OrgInfoAllQueryRsp>> orgInfoAllQuery(@RequestBody @Valid RequestMessage<OrgInfoAllQueryReq> reqMsg);
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

    @PostMapping(value = "/system/oneleveldeptdto/query", name="一级部门详情Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OneLevelDeptDtoQueryRsp>> oneLevelDeptDtoQuery(@RequestBody @Valid RequestMessage<OneLevelDeptDtoQueryReq> reqMsg);
    @PostMapping(value = "/system/oneleveldeptdto/save", name="一级部门详情Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> oneLevelDeptDtoSave(@RequestBody @Valid RequestMessage<OneLevelDeptDtoSaveReq> reqMsg);
    @PostMapping(value = "/system/searchsecondleveldeptlistdto/query", name="搜索二级部门信息List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<SearchSecondLevelDeptListDtoQueryRsp>> searchSecondLevelDeptListDtoQuery(@RequestBody @Valid RequestMessage<SearchSecondLevelDeptListDtoQueryReq> reqMsg);
    @PostMapping(value = "/system/deleteinfodto/delete", name="删除部门Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> deleteInfoDtoQuery(@RequestBody @Valid RequestMessage<DeleteInfoDtoQueryReq> reqMsg);
    @PostMapping(value = "/system/secondleveldeptinfodto/query", name="二级部门信息Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<SecondLevelDeptInfoDtoQueryRsp>> secondLevelDeptInfoDtoQuery(@RequestBody @Valid RequestMessage<SecondLevelDeptInfoDtoQueryReq> reqMsg);
    @PostMapping(value = "/system/secondleveldeptlistdto/query", name="二级部门信息List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<SecondLevelDeptListDtoQueryRsp>> secondLevelDeptListDtoQuery(@RequestBody @Valid RequestMessage<SecondLevelDeptListDtoQueryReq> reqMsg);
    @PostMapping(value = "/system/setorginfo", name="修改指定机构的状态信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> orgInfoUpdate(@RequestBody @Valid RequestMessage<OrgInfoUpdateReq> reqMsg);
    @PostMapping(value = "/system/getonesecondorgtree", name="机构树图展示", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OrgTreeQueryRsp>> oneSecondOrgTreeQuery(@RequestBody @Valid RequestMessage<OrgTreeQueryReq> reqMsg);
    @PostMapping(value = "/system/employeeinfolistdto/query", name="员工详情List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>> employeeInfoListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoQueryReq> reqMsg);
    @PostMapping(value = "/system/employeeinfolistdto/search", name="搜索员工详情List接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>> employeeInfoListDtoSearch(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoSearchReq> reqMsg);
    @PostMapping(value = "/system/getorguser", name="查询部门或者团队下员工id", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<OrgUserQueryRsp>> orgUserQuery(@RequestBody @Valid RequestMessage<OrgUserQueryReq> reqMsg);
}
