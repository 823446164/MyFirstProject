package com.amarsoft.app.ems.demo.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeebelongchangelist.EmployeeBelongChangeListDeleteReq;

/**
 * 团队调整申请ListController接口
 * @author jfan5
 */
public interface EmployeeBelongChangeListController {
    @PostMapping(value = "/employeebelongchangelist/query", name="团队调整申请List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeBelongChangeListQueryRsp>> employeeBelongChangeListQuery(@RequestBody @Valid RequestMessage<EmployeeBelongChangeListQueryReq> reqMsg);

    @PostMapping(value = "/employeebelongchangelist/save", name="团队调整申请List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeListSave(@RequestBody @Valid RequestMessage<EmployeeBelongChangeListSaveReq> reqMsg);

    @PostMapping(value = "/employeebelongchangelist/delete", name="团队调整申请List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeListDelete(@RequestBody @Valid RequestMessage<EmployeeBelongChangeListDeleteReq> reqMsg);
}
