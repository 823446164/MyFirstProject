package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfoSaveReq;

/**
 * 标签能力描述InfoController接口
 * @author yrong
 */
public interface LabelDescribeInfoController {
    @PostMapping(value = "/labeldescribeinfo/query", name="标签能力描述Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LabelDescribeInfoQueryRsp>> labelDescribeInfoQuery(@RequestBody @Valid RequestMessage<LabelDescribeInfoQueryReq> reqMsg);

    @PostMapping(value = "/labeldescribeinfo/save", name="标签能力描述Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> labelDescribeInfoSave(@RequestBody @Valid RequestMessage<LabelDescribeInfoSaveReq> reqMsg);
}
