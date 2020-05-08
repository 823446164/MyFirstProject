package com.amarsoft.app.ems.train.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeeprojectexperiencelist.EmployeeProjectExperienceListDeleteReq;

/**
 * 员工参与培训项目列表Controller接口
 * @author xphe
 */
public interface EmployeeProjectExperienceListController {
    @PostMapping(value = "/employeeprojectexperiencelist/query", name="员工参与培训项目列表查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeProjectExperienceListQueryRsp>> employeeProjectExperienceListQuery(@RequestBody @Valid RequestMessage<EmployeeProjectExperienceListQueryReq> reqMsg);

    @PostMapping(value = "/employeeprojectexperiencelist/delete", name="员工参与培训项目列表删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeProjectExperienceListDelete(@RequestBody @Valid RequestMessage<EmployeeProjectExperienceListDeleteReq> reqMsg);
}
