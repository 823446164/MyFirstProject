/*
 * 文件名：RankStandardCatalogListServiceImpl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：技能详情Controller接口
 * 修改人：xphe
 * 修改时间：2020年5月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */


package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfoSaveReq;

/**
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
public interface RankStandardItemsInfoController {
    @PostMapping(value = "/rankstandarditemsinfo/query", name="技能详情查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RankStandardItemsInfoQueryRsp>> rankStandardItemsInfoQuery(@RequestBody @Valid RequestMessage<RankStandardItemsInfoQueryReq> reqMsg);

    @PostMapping(value = "/rankstandarditemsinfo/save", name="技能详情保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rankStandardItemsInfoSave(@RequestBody @Valid RequestMessage<RankStandardItemsInfoSaveReq> reqMsg);
}
