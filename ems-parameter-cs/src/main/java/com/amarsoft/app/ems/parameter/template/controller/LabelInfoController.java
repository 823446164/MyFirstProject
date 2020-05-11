package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoSaveReq;

/**
 * 标签InfoController接口
 * @author ylgao
 */
public interface LabelInfoController {
    @PostMapping(value = "/labelinfo/query", name="标签Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LabelInfoQueryRsp>> labelInfoQuery(@RequestBody @Valid RequestMessage<LabelInfoQueryReq> reqMsg);

    @PostMapping(value = "/labelinfo/save", name="标签Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> labelInfoSave(@RequestBody @Valid RequestMessage<LabelInfoSaveReq> reqMsg);
}
