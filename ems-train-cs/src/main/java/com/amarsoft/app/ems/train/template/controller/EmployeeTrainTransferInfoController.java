package com.amarsoft.app.ems.train.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfoQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfoQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeetraintransferinfo.EmployeeTrainTransferInfoSaveReq;

/**
 * 培训转移记录Controller接口
 * @author xphe
 */
public interface EmployeeTrainTransferInfoController {
    @PostMapping(value = "/employeetraintransferinfo/query", name="培训转移记录查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeTrainTransferInfoQueryRsp>> employeeTrainTransferInfoQuery(@RequestBody @Valid RequestMessage<EmployeeTrainTransferInfoQueryReq> reqMsg);

    @PostMapping(value = "/employeetraintransferinfo/save", name="培训转移记录保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeTrainTransferInfoSave(@RequestBody @Valid RequestMessage<EmployeeTrainTransferInfoSaveReq> reqMsg);
}
