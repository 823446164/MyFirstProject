package com.amarsoft.app.ems.train.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListQueryReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListQueryRsp;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListSaveReq;
import com.amarsoft.app.ems.train.template.cs.dto.employeejobpartorlist.EmployeeJobPartorListDeleteReq;

/**
 * 在职培训参与人员列表Controller接口
 * @author xphe
 */
public interface EmployeeJobPartorListController {
    @PostMapping(value = "/employeejobpartorlist/query", name="在职培训参与人员列表查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeJobPartorListQueryRsp>> employeeJobPartorListQuery(@RequestBody @Valid RequestMessage<EmployeeJobPartorListQueryReq> reqMsg);

    @PostMapping(value = "/employeejobpartorlist/save", name="在职培训参与人员列表保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeJobPartorListSave(@RequestBody @Valid RequestMessage<EmployeeJobPartorListSaveReq> reqMsg);

    @PostMapping(value = "/employeejobpartorlist/delete", name="在职培训参与人员列表删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeJobPartorListDelete(@RequestBody @Valid RequestMessage<EmployeeJobPartorListDeleteReq> reqMsg);
}
