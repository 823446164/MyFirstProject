/**
 * 查询生效日
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.efficientdatequery.EfficientDateQueryReq;
import com.amarsoft.app.ems.system.cs.dto.efficientdatequery.EfficientDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.getbaserate.GetBaseRateReq;
import com.amarsoft.app.ems.system.cs.dto.getbaserate.GetBaseRateRsp;
import com.amarsoft.app.ems.system.cs.dto.rateinfoadd.RateInfoAddReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoallquery.RateInfoAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoallquery.RateInfoAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.rateinfodelete.RateInfoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoefficientquery.RateInfoEfficientQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoefficientquery.RateInfoEfficientQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.rateinfoquery.RateInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rateinfoquery.RateInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.rateinfoupdate.RateInfoUpdateReq;

import org.springframework.web.bind.annotation.PostMapping;

public interface RateController {
    @PostMapping(value = "/system/getallefficientdate", name="查询生效日", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EfficientDateQueryRsp>> efficientDateQuery(@RequestBody @Valid RequestMessage<EfficientDateQueryReq> reqMsg);
    @PostMapping(value = "/system/getrateinfo", name="查询指定利率的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RateInfoQueryRsp>> rateInfoQuery(@RequestBody @Valid RequestMessage<RateInfoQueryReq> reqMsg);
    @PostMapping(value = "/system/getrateefficientdate", name="查询利率的信息 (生效日)", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RateInfoEfficientQueryRsp>> rateInfoEfficientQuery(@RequestBody @Valid RequestMessage<RateInfoEfficientQueryReq> reqMsg);
    @PostMapping(value = "/system/getrateall", name="查询所有利率的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RateInfoAllQueryRsp>> rateInfoAllQuery(@RequestBody @Valid RequestMessage<RateInfoAllQueryReq> reqMsg);
    @PostMapping(value = "/system/setrateinfo", name="修改指定利率的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rateInfoUpdate(@RequestBody @Valid RequestMessage<RateInfoUpdateReq> reqMsg);
    @PostMapping(value = "/system/addrateinfo", name="新增利率的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rateInfoAdd(@RequestBody @Valid RequestMessage<RateInfoAddReq> reqMsg);
    @PostMapping(value = "/system/deleterateinfo", name="删除指定利率的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> rateInfoDelete(@RequestBody @Valid RequestMessage<RateInfoDeleteReq> reqMsg);
    @PostMapping(value = "/system/getBaseRate", name="获取基准利率", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<GetBaseRateRsp>> getBaseRate(@RequestBody @Valid RequestMessage<GetBaseRateReq> reqMsg);
}
