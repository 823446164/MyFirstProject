package com.amarsoft.app.ems.project.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployeeQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployeeQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployee.ProjectEmployeeSaveReq;

/**
 * 项目组人员信息Controller接口
 * @author hpli
 */
public interface ProjectEmployeeController {
    @PostMapping(value = "/projectemployee/query", name="项目组人员信息查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ProjectEmployeeQueryRsp>> projectEmployeeQuery(@RequestBody @Valid RequestMessage<ProjectEmployeeQueryReq> reqMsg);

    @PostMapping(value = "/projectemployee/save", name="项目组人员信息保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> projectEmployeeSave(@RequestBody @Valid RequestMessage<ProjectEmployeeSaveReq> reqMsg);
}
