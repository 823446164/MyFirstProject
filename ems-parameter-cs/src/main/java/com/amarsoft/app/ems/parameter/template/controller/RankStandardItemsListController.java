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

import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel.TreeLabelSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsInfoDeleteReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist.RankStandardItemsListSaveReq;

/**
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
public interface RankStandardItemsListController {
    @PostMapping(value = "/rankstandarditemsinfo/query", name="子职级指标列表查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RankStandardItemsListQueryRsp>> rankStandardItemsInfoQuery(@RequestBody @Valid RequestMessage<RankStandardItemsListQueryReq> reqMsg);

    @PostMapping(value = "/rankstandarditemsinfo/save", name="技能详情保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rankStandardItemsListSave(@RequestBody @Valid RequestMessage<RankStandardItemsListSaveReq> reqMsg);

    @PostMapping(value = "/rankstandarditemsinfo/listquery", name="标签选择展示标签列表接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<TreeLabelQueryRsp>> rankStandardItemsInfoListQuery(@RequestBody @Valid RequestMessage<TreeLabelQueryReq> reqMsg);
    
    @PostMapping(value = "/rankstandarditemsinfo/labelsave", name="标签选择保存至职级指标接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rankStandardItemsInfoLabelSave(@RequestBody @Valid RequestMessage<TreeLabelSaveReq> reqMsg);
    
    @PostMapping(value = "/rankstandarditemsinfo/delete", name="职级指标删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rankStandardItemsInfoDelete(@RequestBody @Valid RequestMessage<RankStandardItemsInfoDeleteReq> reqMsg);
    
  
}
