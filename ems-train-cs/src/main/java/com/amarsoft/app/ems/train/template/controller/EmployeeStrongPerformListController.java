package com.amarsoft.app.ems.train.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeestrongperformlist.EmployeeStrongPerformListDeleteReq;

/**
 * 员工强化培训表现Controller接口
 * @author xphe
 */
public interface EmployeeStrongPerformListController {
    @PostMapping(value = "/employeestrongperformlist/query", name="员工强化培训表现查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeStrongPerformListQueryRsp>> employeeStrongPerformListQuery(@RequestBody @Valid RequestMessage<EmployeeStrongPerformListQueryReq> reqMsg);

    @PostMapping(value = "/employeestrongperformlist/delete", name="员工强化培训表现删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeStrongPerformListDelete(@RequestBody @Valid RequestMessage<EmployeeStrongPerformListDeleteReq> reqMsg);
}
