package com.amarsoft.app.ems.system.controller;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.pooltaskconfig.PoolTaskConfigReq;
import com.amarsoft.app.ems.system.cs.dto.pooltaskconfig.PoolTaskConfigRsp;
import com.amarsoft.app.ems.system.cs.dto.pooltasknum.PoolTaskNumReq;
import com.amarsoft.app.ems.system.cs.dto.pooltasknum.PoolTaskNumRsp;
import com.amarsoft.app.ems.system.cs.dto.tasknumquery.TaskNumQueryReq;
import com.amarsoft.app.ems.system.cs.dto.tasknumquery.TaskNumQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.tasktemplate.TaskTemplateReq;
import com.amarsoft.app.ems.system.cs.dto.tasktemplate.TaskTemplateRsp;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Description
 * @Author dchen1
 * @Date 2019/9/29 下午4:03
 * @Version
 */
public interface TaskController {
    @PostMapping(value = "/gettemplate", name="工作台模板获取", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<TaskTemplateRsp>> taskTemplate(@RequestBody @Valid RequestMessage<TaskTemplateReq> reqMsg);
    @PostMapping(value = "/gettasksnum", name="获取代办总数 根据obejctType分类 数量降序", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<TaskNumQueryRsp>> taskNumQuery(@RequestBody @Valid RequestMessage<TaskNumQueryReq> reqMsg);
    @PostMapping(value = "/getpooltasksconfig", name="根据objectType 获取任务池模式以及按钮方式", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<PoolTaskConfigRsp>> poolTaskConfig(@RequestBody @Valid RequestMessage<PoolTaskConfigReq> reqMsg);
    @PostMapping(value = "/getpooltasksnum", name="根据objectType 获取任务池总数", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<PoolTaskNumRsp>> poolTaskNum(@RequestBody @Valid RequestMessage<PoolTaskNumReq> reqMsg);
}
