package com.amarsoft.app.ems.project.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListSaveReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeelist.ProjectEmployeeListDeleteReq;

/**
 * 项目组人员信息Controller接口
 * @author hpli
 */
public interface ProjectEmployeeListController {
    @PostMapping(value = "/projectemployeelist/query", name="项目组人员信息查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ProjectEmployeeListQueryRsp>> projectEmployeeListQuery(@RequestBody @Valid RequestMessage<ProjectEmployeeListQueryReq> reqMsg);

    @PostMapping(value = "/projectemployeelist/save", name="项目组人员信息保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> projectEmployeeListSave(@RequestBody @Valid RequestMessage<ProjectEmployeeListSaveReq> reqMsg);

    @PostMapping(value = "/projectemployeelist/delete", name="项目组人员信息删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> projectEmployeeListDelete(@RequestBody @Valid RequestMessage<ProjectEmployeeListDeleteReq> reqMsg);
}
