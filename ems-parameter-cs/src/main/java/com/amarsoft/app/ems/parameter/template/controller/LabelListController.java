package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListDeleteReq;

/**
 * 标签ListController接口
 * @author ylgao
 */
public interface LabelListController {
    @PostMapping(value = "/labellist/query", name="标签List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LabelListQueryRsp>> labelListQuery(@RequestBody @Valid RequestMessage<LabelListQueryReq> reqMsg);

    @PostMapping(value = "/labellist/save", name="标签List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> labelListSave(@RequestBody @Valid RequestMessage<LabelListSaveReq> reqMsg);

    @PostMapping(value = "/labellist/delete", name="标签List删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> labelListDelete(@RequestBody @Valid RequestMessage<LabelListDeleteReq> reqMsg);
}
