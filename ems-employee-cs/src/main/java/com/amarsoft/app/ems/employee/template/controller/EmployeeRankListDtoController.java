/* 文件名：EmployeeRankListDtoController
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：dxiao
 * 修改时间：2020/05/14
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto.EmployeeRankListDtoSaveReq;

/**
 * 员工职级ListController接口
 * @author lding
 */
public interface EmployeeRankListDtoController {
    @PostMapping(value = "/employeeranklistdto/query", name="员工职级List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankListDtoQueryRsp>> employeeRankListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeeranklistdto/save", name="员工职级List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankListDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankListDtoSaveReq> reqMsg);

}
