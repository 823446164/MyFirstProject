package com.amarsoft.app.ems.train.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongmemberlist.EmployeeStrongMemberListDeleteReq;

/**
 * 培训项目参与人员列表Controller接口
 * @author xphe
 */
public interface EmployeeStrongMemberListController {
    @PostMapping(value = "/employeestrongmemberlist/query", name="培训项目参与人员列表查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeStrongMemberListQueryRsp>> employeeStrongMemberListQuery(@RequestBody @Valid RequestMessage<EmployeeStrongMemberListQueryReq> reqMsg);

    @PostMapping(value = "/employeestrongmemberlist/save", name="培训项目参与人员列表保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeStrongMemberListSave(@RequestBody @Valid RequestMessage<EmployeeStrongMemberListSaveReq> reqMsg);

    @PostMapping(value = "/employeestrongmemberlist/delete", name="培训项目参与人员列表删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeStrongMemberListDelete(@RequestBody @Valid RequestMessage<EmployeeStrongMemberListDeleteReq> reqMsg);
}
