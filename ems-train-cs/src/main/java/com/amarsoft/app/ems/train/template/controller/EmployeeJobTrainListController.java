package com.amarsoft.app.ems.train.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtrainlist.EmployeeJobTrainListDeleteReq;

/**
 * 在职培训列表Controller接口
 * @author xphe
 */
public interface EmployeeJobTrainListController {
    @PostMapping(value = "/employeejobtrainlist/query", name="在职培训列表查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeJobTrainListQueryRsp>> employeeJobTrainListQuery(@RequestBody @Valid RequestMessage<EmployeeJobTrainListQueryReq> reqMsg);

    @PostMapping(value = "/employeejobtrainlist/save", name="在职培训列表保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeJobTrainListSave(@RequestBody @Valid RequestMessage<EmployeeJobTrainListSaveReq> reqMsg);

    @PostMapping(value = "/employeejobtrainlist/delete", name="在职培训列表删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeJobTrainListDelete(@RequestBody @Valid RequestMessage<EmployeeJobTrainListDeleteReq> reqMsg);
}
