/**
 * 查询指定汇率的信息
 * @Author xxu1
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.erateinfoadd.ErateInfoAddReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoallquery.ErateInfoAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoallquery.ErateInfoAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.erateinfodelete.ErateInfoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery.ErateInfoEfficientQueryReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoefficientquery.ErateInfoEfficientQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.erateinfoquery.ErateInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.erateinfoquery.ErateInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.erateinfoupdate.ErateInfoUpdateReq;

import org.springframework.web.bind.annotation.PostMapping;

public interface ERateController {
    @PostMapping(value = "/system/geterateinfo", name="查询指定汇率的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ErateInfoQueryRsp>> erateInfoQuery(@RequestBody @Valid RequestMessage<ErateInfoQueryReq> reqMsg);
    @PostMapping(value = "/system/geterateefficient", name="查询指定币种有效的汇率信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ErateInfoEfficientQueryRsp>> erateInfoEfficientQuery(@RequestBody @Valid RequestMessage<ErateInfoEfficientQueryReq> reqMsg);
    @PostMapping(value = "/system/geterateall", name="查询所有汇率的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ErateInfoAllQueryRsp>> erateInfoAllQuery(@RequestBody @Valid RequestMessage<ErateInfoAllQueryReq> reqMsg);
    @PostMapping(value = "/system/seterateinfo", name="修改指定汇率的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> erateInfoUpdate(@RequestBody @Valid RequestMessage<ErateInfoUpdateReq> reqMsg);
    @PostMapping(value = "/system/adderateinfo", name="新增利率的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> erateInfoAdd(@RequestBody @Valid RequestMessage<ErateInfoAddReq> reqMsg);
    @PostMapping(value = "/system/deleteerateinfo", name="删除指定利率的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> erateInfoDelete(@RequestBody @Valid RequestMessage<ErateInfoDeleteReq> reqMsg);
}
