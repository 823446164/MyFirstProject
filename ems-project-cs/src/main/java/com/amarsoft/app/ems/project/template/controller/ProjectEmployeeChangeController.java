package com.amarsoft.app.ems.project.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChangeQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChangeQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechange.ProjectEmployeeChangeSaveReq;

/**
 * 项目组人员变更信息Controller接口
 * @author hpli
 */
public interface ProjectEmployeeChangeController {
    @PostMapping(value = "/projectemployeechange/query", name="项目组人员变更信息查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ProjectEmployeeChangeQueryRsp>> projectEmployeeChangeQuery(@RequestBody @Valid RequestMessage<ProjectEmployeeChangeQueryReq> reqMsg);

    @PostMapping(value = "/projectemployeechange/save", name="项目组人员变更信息保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> projectEmployeeChangeSave(@RequestBody @Valid RequestMessage<ProjectEmployeeChangeSaveReq> reqMsg);
}
