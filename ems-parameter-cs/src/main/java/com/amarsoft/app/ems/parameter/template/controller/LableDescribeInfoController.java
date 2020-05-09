package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoSaveReq;

/**
 * 标签树图Controller接口
 * @author ylgao
 */
public interface LableDescribeInfoController {
    @PostMapping(value = "/labledescribeinfo/query", name="标签树图查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LableDescribeInfoQueryRsp>> lableDescribeInfoQuery(@RequestBody @Valid RequestMessage<LableDescribeInfoQueryReq> reqMsg);

    @PostMapping(value = "/labledescribeinfo/save", name="标签树图保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> lableDescribeInfoSave(@RequestBody @Valid RequestMessage<LableDescribeInfoSaveReq> reqMsg);

    @PostMapping(value = "/labledescribeinfo/ok", name="标签生效接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> lableStatusOk(@RequestBody @Valid RequestMessage<LableDescribeInfoSaveReq> reqMsg);
    
    @PostMapping(value = "/labledescribeinfo/no", name="标签失效接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> lableStatusNo(@Valid RequestMessage<LableDescribeInfoSaveReq> reqMsg);


}
