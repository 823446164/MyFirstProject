package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListDeleteReq;

/**
 * 标签能力描述ListController接口
 * @author yrong
 */
public interface LabelDescribeListController {
    @PostMapping(value = "/labeldescribelist/query", name="标签能力描述List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LabelDescribeListQueryRsp>> labelDescribeListQuery(@RequestBody @Valid RequestMessage<LabelDescribeListQueryReq> reqMsg);

    @PostMapping(value = "/labeldescribelist/save", name="标签能力描述List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> labelDescribeListSave(@RequestBody @Valid RequestMessage<LabelDescribeListSaveReq> reqMsg);

    @PostMapping(value = "/labeldescribelist/delete", name="标签能力描述List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> labelDescribeListDelete(@RequestBody @Valid RequestMessage<LabelDescribeListDeleteReq> reqMsg);
}
