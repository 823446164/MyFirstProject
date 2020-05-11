package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoSaveReq;

/**
 * 标签目录详情Controller接口
 * @author ylgao
 */
public interface LabelCatalogInfoController {
    @PostMapping(value = "/labelcataloginfo/query", name="标签目录详情查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LabelCatalogInfoQueryRsp>> labelCatalogInfoQuery(@RequestBody @Valid RequestMessage<LabelCatalogInfoQueryReq> reqMsg);

    @PostMapping(value = "/labelcataloginfo/save", name="标签目录详情保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> labelCatalogInfoSave(@RequestBody @Valid RequestMessage<LabelCatalogInfoSaveReq> reqMsg);
}
