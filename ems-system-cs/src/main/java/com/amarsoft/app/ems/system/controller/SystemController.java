/**
 * 查询交易日期
 * @Author xxu1
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.alldatequery.AllDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.batchdatequery.BatchDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.batchflagquery.BatchFlagQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.businessdatequery.BusinessDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.productdatequery.ProductDateQueryRsp;

public interface SystemController {
    @GetMapping(value = "/system/getbusinessdate", name="查询交易日期", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<BusinessDateQueryRsp>> businessDateQuery();
    @GetMapping(value = "/system/getbatchflag", name="查询批量标识", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<BatchFlagQueryRsp>> batchFlagQuery();
    @GetMapping(value = "/system/getbatchdate", name="查询批量日期", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<BatchDateQueryRsp>> batchDateQuery();
    @GetMapping(value = "/system/getproductdate", name="查询投产日期", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ProductDateQueryRsp>> productDateQuery();
    @GetMapping(value = "/system/getalldate", name="查询系统日期", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<AllDateQueryRsp>> allDateQuery();
}
