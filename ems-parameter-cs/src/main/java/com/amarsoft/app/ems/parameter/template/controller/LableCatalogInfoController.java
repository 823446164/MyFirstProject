package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoSaveReq;

/**
 * 标签目录详情Controller接口
 * @author ylgao
 */
public interface LableCatalogInfoController {
    @PostMapping(value = "/lablecataloginfo/query", name="标签目录详情查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LableCatalogInfoQueryRsp>> lableCatalogInfoQuery(@RequestBody @Valid RequestMessage<LableCatalogInfoQueryReq> reqMsg);

    @PostMapping(value = "/lablecataloginfo/save", name="标签目录详情保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> lableCatalogInfoSave(@RequestBody @Valid RequestMessage<LableCatalogInfoSaveReq> reqMsg);
}
