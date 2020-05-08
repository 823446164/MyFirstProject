package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogSonQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogSonQueryRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListDeleteReq;

/**
 * 职级标准列表Controller接口
 * @author ylgao
 */
public interface RankStandardCatalogListController {
    @PostMapping(value = "/rankstandardcataloglist/query", name="职级标准列表查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RankStandardCatalogListQueryRsp>> rankStandardCatalogListQuery(@RequestBody @Valid RequestMessage<RankStandardCatalogListQueryReq> reqMsg);

    @PostMapping(value = "/rankstandardcataloglist/save", name="职级标准列表保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rankStandardCatalogListSave(@RequestBody @Valid RequestMessage<RankStandardCatalogListSaveReq> reqMsg);

    @PostMapping(value = "/rankstandardcataloglist/delete", name="职级标准列表删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rankStandardCatalogListDelete(@RequestBody @Valid RequestMessage<RankStandardCatalogListDeleteReq> reqMsg);
            
    @PostMapping(value = "/rankstandardcataloglist/sonquery", name="职级标准列表子职级查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RankStandardCatalogSonQueryRsq>> rankStandardCatalogSonQuery(@RequestBody @Valid RequestMessage<RankStandardCatalogSonQueryReq> reqMsg);

}
