/*
 * 文件名：EmployeeRankRelabelListDtoController.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：标签查询接口
 * 修改人：dxiao
 * 修改时间：2020年5月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */

package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto.EmployeeRankRelabelListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto.EmployeeRankRelabelListDtoQueryRsp;

/**
 * @author dxiao
 * @version 2020年5月19日
 * @see EmployeeRankRelabelListDtoController
 * @since
 */

public interface EmployeeRankRelabelListDtoController {
    //标签查询
    @PostMapping(value = "/employeerankrelabellistdto/query", name="员工职级标签list查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankRelabelListDtoQueryRsp>> employeeRankListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankRelabelListDtoQueryReq> reqMsg);

}
