/**
 * 修改批量标识
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
import com.amarsoft.app.ems.system.cs.dto.batchdateupdate.BatchDateUpdateReq;
import com.amarsoft.app.ems.system.cs.dto.batchflagupdate.BatchFlagUpdateReq;
import com.amarsoft.app.ems.system.cs.dto.businessdateupdate.BusinessDateUpdateReq;

import org.springframework.web.bind.annotation.PostMapping;

public interface SystemBatchController {
    @PostMapping(value = "/systembatch/updatebatchflag", name="修改批量标识", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> batchFlagUpdate(@RequestBody @Valid RequestMessage<BatchFlagUpdateReq> reqMsg);
    @PostMapping(value = "/systembatch/updatebatchdate", name="修改批量日期", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> batchDateUpdate(@RequestBody @Valid RequestMessage<BatchDateUpdateReq> reqMsg);
    @PostMapping(value = "/systembatch/updatebusinessdate", name="修改系统日期", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> businessDateUpdate(@RequestBody @Valid RequestMessage<BusinessDateUpdateReq> reqMsg);
}
