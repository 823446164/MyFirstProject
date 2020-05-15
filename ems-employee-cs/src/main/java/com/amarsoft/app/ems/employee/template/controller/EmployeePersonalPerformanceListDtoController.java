/* 文件名：EmployeePersonalPerformanceListDtoController
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：dxiao
 * 修改时间：2020/05/14
 * 跟踪单号：
 * 修改单号：
 * 修改内容：删除自动生成的list删除方法
 */
package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoSaveReq;

/**
 * 员工项目经历个人表现ListController接口
 * @author lding
 */
public interface EmployeePersonalPerformanceListDtoController {
    @PostMapping(value = "/employeepersonalperformancelistdto/query", name="员工项目经历个人表现List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeePersonalPerformanceListDtoQueryRsp>> employeePersonalPerformanceListDtoQuery(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeepersonalperformancelistdto/save", name="员工项目经历个人表现List保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceListDtoSave(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListDtoSaveReq> reqMsg);

}
