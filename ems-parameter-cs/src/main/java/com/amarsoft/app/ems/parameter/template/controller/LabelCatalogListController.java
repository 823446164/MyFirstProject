/*
 * 文件名：LabelCatalogListController
 * 版权：Copyright by www.amarsoft.com
 * 描述：LableCatalogInfoControllerImpl的接口
 * 修改人：yrong
 * 修改时间：2020年5月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：重新生成
 */
package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListDeleteReq;

/**
 * 标签目录树图Controller接口
 * @author yrong
 */
public interface LabelCatalogListController {
    @PostMapping(value = "/labelcataloglist/query", name="标签目录树图查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LabelCatalogListQueryRsp>> labelCatalogListQuery(@RequestBody @Valid RequestMessage<LabelCatalogListQueryReq> reqMsg);

    @PostMapping(value = "/labelcataloglist/save", name="标签目录树图保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> labelCatalogListSave(@RequestBody @Valid RequestMessage<LabelCatalogListSaveReq> reqMsg);

    @PostMapping(value = "/labelcataloglist/delete", name="标签目录树图删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> labelCatalogListDelete(@RequestBody @Valid RequestMessage<LabelCatalogListDeleteReq> reqMsg);
}
