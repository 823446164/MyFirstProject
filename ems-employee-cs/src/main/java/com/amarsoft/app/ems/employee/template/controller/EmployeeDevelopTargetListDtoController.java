/*文件名：EmployeeDevelopTargetListDtoController 
 * 版权：Copyright by www.amarsoft.com
 * 描述： 
 * 修改人：dxiao 
 * 修改时间：2020/05/09 
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：删除自动生成的list模板保存方法
 */
package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeedeveloptargetlistdto.EmployeeDevelopTargetListDtoQueryRsp;

/**
 * 员工成长目标跟踪ListController接口
 * @author lding
 */
public interface EmployeeDevelopTargetListDtoController {
    @PostMapping(value = "/employeedeveloptargetlistdto/query", name="员工成长目标跟踪List查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeDevelopTargetListDtoQueryRsp>> employeeDevelopTargetListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeedeveloptargetlistdto/delete", name="员工成长目标跟踪List记录删除接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeDevelopTargetListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeDevelopTargetListDtoDeleteReq> reqMsg);
}
