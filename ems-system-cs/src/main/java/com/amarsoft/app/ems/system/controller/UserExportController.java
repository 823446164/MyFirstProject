/**
 * 新增用户导出记录
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.addexport.AddExportReq;
import com.amarsoft.app.ems.system.cs.dto.exportquery.ExportQueryReq;
import com.amarsoft.app.ems.system.cs.dto.exportquery.ExportQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.finishexport.FinishExportReq;
import com.amarsoft.app.ems.system.cs.dto.getexportserialno.GetExportSerialNoRsp;


public interface UserExportController {
    @PostMapping(value = "/userexport/addexport", name="新增用户导出记录", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> addExport(@RequestBody @Valid RequestMessage<AddExportReq> reqMsg);
    @GetMapping(value = "/userexport/getexportserialno", name="获取用户导出流水号", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<GetExportSerialNoRsp>> getExportSerialNo();
    @PostMapping(value = "/userexport/finishexport", name="更新用户导出记录", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> finishExport(@RequestBody @Valid RequestMessage<FinishExportReq> reqMsg);
    @PostMapping(value = "/userexport/exportquery", name="用户导出记录查询", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<ExportQueryRsp>> exportQuery(@Valid RequestMessage<ExportQueryReq> reqMsg);
}
