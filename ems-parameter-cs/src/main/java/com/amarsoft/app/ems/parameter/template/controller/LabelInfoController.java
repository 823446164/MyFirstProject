/*
 * 文件名：LabelInfoController
 * 版权：Copyright by www.amarsoft.com
 * 描述：LableDescribeInfoControllerImpl的接口
 * 修改人：yrong
 * 修改时间：2020年5月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;

import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoCopyReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoSaveReq;

/**
 * 标签InfoController接口
 * @author yrong
 */
public interface LabelInfoController {
    @PostMapping(value = "/labelinfo/query", name="标签Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LabelInfoQueryRsp>> labelInfoQuery(@RequestBody @Valid RequestMessage<LabelInfoQueryReq> reqMsg);

    @PostMapping(value = "/labelinfo/save", name="标签Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> labelInfoSave(@RequestBody @Valid RequestMessage<LabelInfoSaveReq> reqMsg);
 
    @PostMapping(value = "/labelinfo/labelStatus", name="标签生效/失效接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> lableStatusOk(@RequestBody @Valid RequestMessage<LabelInfoSaveReq> reqMsg);
}
