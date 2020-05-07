package com.amarsoft.app.ems.parameter.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListDeleteReq;

/**
 * 标签树图Controller接口
 * @author ylgao
 */
public interface LableDescribeListController {
    @PostMapping(value = "/labledescribelist/query", name="标签树图查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LableDescribeListQueryRsp>> lableDescribeListQuery(@RequestBody @Valid RequestMessage<LableDescribeListQueryReq> reqMsg);

    @PostMapping(value = "/labledescribelist/save", name="标签树图保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> lableDescribeListSave(@RequestBody @Valid RequestMessage<LableDescribeListSaveReq> reqMsg);

    @PostMapping(value = "/labledescribelist/delete", name="标签树图删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> lableDescribeListDelete(@RequestBody @Valid RequestMessage<LableDescribeListDeleteReq> reqMsg);
}
