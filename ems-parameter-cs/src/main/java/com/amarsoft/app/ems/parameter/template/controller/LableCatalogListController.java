package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListDeleteReq;

/**
 * 标签目录树图Controller接口
 * @author ylgao
 */
public interface LableCatalogListController {
    @PostMapping(value = "/lablecataloglist/query", name="标签目录树图查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LableCatalogListQueryRsp>> lableCatalogListQuery(@RequestBody @Valid RequestMessage<LableCatalogListQueryReq> reqMsg);

    @PostMapping(value = "/lablecataloglist/save", name="标签目录树图保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> lableCatalogListSave(@RequestBody @Valid RequestMessage<LableCatalogListSaveReq> reqMsg);

    @PostMapping(value = "/lablecataloglist/delete", name="标签目录树图删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> lableCatalogListDelete(@RequestBody @Valid RequestMessage<LableCatalogListDeleteReq> reqMsg);
}
