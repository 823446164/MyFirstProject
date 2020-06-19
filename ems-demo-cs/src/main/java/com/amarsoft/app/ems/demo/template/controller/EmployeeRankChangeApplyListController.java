package com.amarsoft.app.ems.demo.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListQueryReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListQueryRsp;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListSaveReq;
import com.amarsoft.app.ems.demo.template.cs.dto.employeerankchangeapplylist.EmployeeRankChangeApplyListDeleteReq;

/**
 * 员工职级调整申请ListController接口
 * @author jfan5
 */
public interface EmployeeRankChangeApplyListController {
    @PostMapping(value = "/employeerankchangeapplylist/query", name="员工职级调整申请List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankChangeApplyListQueryRsp>> employeeRankChangeApplyListQuery(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyListQueryReq> reqMsg);

    @PostMapping(value = "/employeerankchangeapplylist/save", name="员工职级调整申请List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeApplyListSave(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyListSaveReq> reqMsg);

    @PostMapping(value = "/employeerankchangeapplylist/delete", name="员工职级调整申请List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeApplyListDelete(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyListDeleteReq> reqMsg);
}
