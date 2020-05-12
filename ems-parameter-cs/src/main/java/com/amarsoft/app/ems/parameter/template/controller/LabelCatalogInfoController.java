/*
 * 文件名：LableCatalogInfoController
 * 版权：Copyright by www.amarsoft.com
 * 描述：LableCatalogInfoControllerImpl的接口
 * 修改人：yrong
 * 修改时间：2020年5月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：增加选中目录查询标签接口
 */
package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;

import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelByLabelCatalogQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelByLabelCatalogQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoSaveReq;

/**
 * 标签目录详情Controller接口
 * @author yrong
 */
public interface LabelCatalogInfoController {
    @PostMapping(value = "/labelcataloginfo/query", name="标签目录详情查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LabelCatalogInfoQueryRsp>> labelCatalogInfoQuery(@RequestBody @Valid RequestMessage<LabelCatalogInfoQueryReq> reqMsg);

    @PostMapping(value = "/labelcataloginfo/save", name="标签目录详情保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> labelCatalogInfoSave(@RequestBody @Valid RequestMessage<LabelCatalogInfoSaveReq> reqMsg);

    @PostMapping(value = "/labelBelongCatalog/query", name="选中目录查询标签接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LabelByLabelCatalogQueryRsp>> labelBelongCatalogQuery(@RequestBody @Valid RequestMessage<LabelByLabelCatalogQueryReq> reqMsg);
}
