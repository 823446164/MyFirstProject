/*
 * 文件名：RankStandardCatalogInfoController.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */


package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoQueryRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoSaveReq;

/**
 * 〈职级标准详情Controller接口〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
public interface RankStandardCatalogInfoController {

    @PostMapping(value = "/rankstandardcataloginfo/query", name="职级标准详情查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RankStandardCatalogChildInfoQueryRsq>> rankStandardCatalogChildInfoQuery(@RequestBody @Valid RequestMessage<RankStandardCatalogChildInfoQueryReq> reqMsg);
  

    @PostMapping(value = "/rankstandardcataloginfo/save", name="职级标准详情保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rankStandardCatalogChildInfoSave(@RequestBody @Valid RequestMessage<RankStandardCatalogChildInfoSaveReq> reqMsg);

    
    @PostMapping(value = "/rankstandardcataloginfo/delete", name="职级标准详情删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rankStandardCatalogInfoDelete(@RequestBody @Valid RequestMessage<RankStandardCatalogChildInfoQueryReq> reqMsg);
     

}
