/**
 * 查询用户事件
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
import com.amarsoft.app.ems.system.cs.dto.adduserevent.AddUserEventReq;
import com.amarsoft.app.ems.system.cs.dto.usereventquery.UserEventQueryReq;
import com.amarsoft.app.ems.system.cs.dto.usereventquery.UserEventQueryRsp;

import org.springframework.web.bind.annotation.PostMapping;

public interface UserEventController {
    @PostMapping(value = "/event/usereventquery", name="查询用户事件", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserEventQueryRsp>> userEventQuery(@RequestBody @Valid RequestMessage<UserEventQueryReq> reqMsg);
    @PostMapping(value = "/event/adduserevent", name="新增用户事件", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> addUserEvent(@RequestBody @Valid RequestMessage<AddUserEventReq> reqMsg);
}
