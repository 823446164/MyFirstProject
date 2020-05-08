package com.amarsoft.app.ems.train.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetrainperformlist.EmployeeTrainPerformListDeleteReq;

/**
 * 员工基础培训表现Controller接口
 * @author xphe
 */
public interface EmployeeTrainPerformListController {
    @PostMapping(value = "/employeetrainperformlist/query", name="员工基础培训表现查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeTrainPerformListQueryRsp>> employeeTrainPerformListQuery(@RequestBody @Valid RequestMessage<EmployeeTrainPerformListQueryReq> reqMsg);

    @PostMapping(value = "/employeetrainperformlist/delete", name="员工基础培训表现删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeTrainPerformListDelete(@RequestBody @Valid RequestMessage<EmployeeTrainPerformListDeleteReq> reqMsg);
}
