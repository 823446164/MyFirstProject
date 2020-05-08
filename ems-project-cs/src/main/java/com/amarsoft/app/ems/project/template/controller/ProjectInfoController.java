package com.amarsoft.app.ems.project.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfoQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfoQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectinfo.ProjectInfoSaveReq;

/**
 * 项目列表Controller接口
 * @author hpli
 */
public interface ProjectInfoController {
    @PostMapping(value = "/projectinfo/query", name="项目列表查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ProjectInfoQueryRsp>> projectInfoQuery(@RequestBody @Valid RequestMessage<ProjectInfoQueryReq> reqMsg);

    @PostMapping(value = "/projectinfo/save", name="项目列表保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> projectInfoSave(@RequestBody @Valid RequestMessage<ProjectInfoSaveReq> reqMsg);
}
