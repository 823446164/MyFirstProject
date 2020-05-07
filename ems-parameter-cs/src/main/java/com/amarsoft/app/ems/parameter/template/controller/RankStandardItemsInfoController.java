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
 * 技能详情Controller接口
 * @author ylgao
 */
public interface RankStandardItemsInfoController {
    @PostMapping(value = "/rankstandarditemsinfo/query", name="技能详情查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RankStandardItemsInfoQueryRsp>> rankStandardItemsInfoQuery(@RequestBody @Valid RequestMessage<RankStandardItemsInfoQueryReq> reqMsg);

    @PostMapping(value = "/rankstandarditemsinfo/save", name="技能详情保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rankStandardItemsInfoSave(@RequestBody @Valid RequestMessage<RankStandardItemsInfoSaveReq> reqMsg);
}
