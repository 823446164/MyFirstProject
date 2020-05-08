package com.amarsoft.app.ems.project.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListSaveReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectlist.ProjectListDeleteReq;

/**
 * 项目列表Controller接口
 * @author hpli
 */
public interface ProjectListController {
    @PostMapping(value = "/projectlist/query", name="项目列表查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ProjectListQueryRsp>> projectListQuery(@RequestBody @Valid RequestMessage<ProjectListQueryReq> reqMsg);

    @PostMapping(value = "/projectlist/save", name="项目列表保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> projectListSave(@RequestBody @Valid RequestMessage<ProjectListSaveReq> reqMsg);

    @PostMapping(value = "/projectlist/delete", name="项目列表删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> projectListDelete(@RequestBody @Valid RequestMessage<ProjectListDeleteReq> reqMsg);
}
