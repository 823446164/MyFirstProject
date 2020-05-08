package com.amarsoft.app.ems.project.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListQueryReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListQueryRsp;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListSaveReq;
import com.amarsoft.app.ems.project.template.cs.dto.projectemployeechangelist.ProjectEmployeeChangeListDeleteReq;

/**
 * 项目组人员变更信息Controller接口
 * @author hpli
 */
public interface ProjectEmployeeChangeListController {
    @PostMapping(value = "/projectemployeechangelist/query", name="项目组人员变更信息查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ProjectEmployeeChangeListQueryRsp>> projectEmployeeChangeListQuery(@RequestBody @Valid RequestMessage<ProjectEmployeeChangeListQueryReq> reqMsg);

    @PostMapping(value = "/projectemployeechangelist/save", name="项目组人员变更信息保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> projectEmployeeChangeListSave(@RequestBody @Valid RequestMessage<ProjectEmployeeChangeListSaveReq> reqMsg);

    @PostMapping(value = "/projectemployeechangelist/delete", name="项目组人员变更信息删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> projectEmployeeChangeListDelete(@RequestBody @Valid RequestMessage<ProjectEmployeeChangeListDeleteReq> reqMsg);
}
