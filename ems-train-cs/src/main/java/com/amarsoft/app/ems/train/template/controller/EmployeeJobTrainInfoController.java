package com.amarsoft.app.ems.train.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobtraininfo.EmployeeJobTrainInfoSaveReq;

/**
 * 在职培训详情Controller接口
 * @author xphe
 */
public interface EmployeeJobTrainInfoController {
    @PostMapping(value = "/employeejobtraininfo/query", name="在职培训详情查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeJobTrainInfoQueryRsp>> employeeJobTrainInfoQuery(@RequestBody @Valid RequestMessage<EmployeeJobTrainInfoQueryReq> reqMsg);

    @PostMapping(value = "/employeejobtraininfo/save", name="在职培训详情保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeJobTrainInfoSave(@RequestBody @Valid RequestMessage<EmployeeJobTrainInfoSaveReq> reqMsg);
}
