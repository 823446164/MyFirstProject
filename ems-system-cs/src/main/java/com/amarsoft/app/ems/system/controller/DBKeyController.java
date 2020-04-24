/**
 * 获取系统流水号（批量）
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
import com.amarsoft.app.ems.system.cs.dto.dbkeybatch.DbKeyBatchReq;
import com.amarsoft.app.ems.system.cs.dto.dbkeybatch.DbKeyBatchRsp;
import com.amarsoft.app.ems.system.cs.dto.dbkeysingle.DbKeySingleReq;
import com.amarsoft.app.ems.system.cs.dto.dbkeysingle.DbKeySingleRsp;

import org.springframework.web.bind.annotation.PostMapping;

public interface DBKeyController {
    @PostMapping(value = "/system/getserialnos", name="获取系统流水号（批量）", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<DbKeyBatchRsp>> dbKeyBatch(@RequestBody @Valid RequestMessage<DbKeyBatchReq> reqMsg);
    @PostMapping(value = "/system/getserialno", name="获取系统流水号（单个）", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<DbKeySingleRsp>> dbKeySingle(@RequestBody @Valid RequestMessage<DbKeySingleReq> reqMsg);
}
