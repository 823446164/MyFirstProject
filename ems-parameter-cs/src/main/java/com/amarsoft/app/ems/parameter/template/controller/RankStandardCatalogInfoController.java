package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;

import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoSaveRsq;

/**
 * 职级标准详情Controller接口
 * @author ylgao
 */
public interface RankStandardCatalogInfoController {
    @PostMapping(value = "/rankstandardcataloginfo/query", name="职级标准详情查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RankStandardCatalogInfoQueryRsp>> rankStandardCatalogInfoQuery(@RequestBody @Valid RequestMessage<RankStandardCatalogInfoQueryReq> reqMsg);

    @PostMapping(value = "/rankstandardcataloginfo/save", name="职级标准详情保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RankStandardCatalogInfoSaveRsq>> rankStandardCatalogInfoSave(@RequestBody @Valid RequestMessage<RankStandardCatalogInfoSaveReq> reqMsg);
    
    @PostMapping(value = "/rankstandardcataloginfo/delete", name="职级标准详情删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rankStandardCatalogInfoDelete(@RequestBody @Valid RequestMessage<RankStandardCatalogInfoQueryReq> reqMsg);
           
}
