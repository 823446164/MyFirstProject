/* 文件名：EmployeePersonalPerformanceListDtoControllerImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：员工项目经历个人表现ListService实现类
 * 修改人：dxiao
 * 修改时间：2020/05/14
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.employee.template.controller.impl;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.employee.template.controller.EmployeePersonalPerformanceListDtoController;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto.EmployeePersonalPerformanceListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.service.EmployeePersonalPerformanceListDtoService;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeePersonalPerformanceListDtoServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 员工项目经历个人表现ListController实现类
 * @author lding
 */
@Slf4j
@RestController
public class EmployeePersonalPerformanceListDtoControllerImpl implements EmployeePersonalPerformanceListDtoController {
    @Autowired
    EmployeePersonalPerformanceListDtoService employeePersonalPerformanceListDtoServiceImpl;
    
    @Override
    @Transactional
    @TemplateExport(name="员工项目经历个人表现List", query = EmployeePersonalPerformanceListDtoServiceImpl.EmployeePersonalPerformanceListDtoReqQuery.class, convert=EmployeePersonalPerformanceListDtoServiceImpl.EmployeePersonalPerformanceListDtoRspConvert.class)
    //员工项目经历个人表现List查询
    public ResponseEntity<ResponseMessage<EmployeePersonalPerformanceListDtoQueryRsp>> employeePersonalPerformanceListDtoQuery(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListDtoQueryReq> reqMsg){
        ResponseMessage<EmployeePersonalPerformanceListDtoQueryRsp> rspMsg = null;
        try {
            EmployeePersonalPerformanceListDtoQueryReq request = reqMsg.getMessage();
            
            EmployeePersonalPerformanceListDtoQueryRsp response = employeePersonalPerformanceListDtoServiceImpl.employeePersonalPerformanceListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeePersonalPerformanceListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeePersonalPerformanceListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历个人表现List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeePersonalPerformanceListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //员工项目经历个人表现List保存
    public ResponseEntity<ResponseMessage<Object>> employeePersonalPerformanceListDtoSave(@RequestBody @Valid RequestMessage<EmployeePersonalPerformanceListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeePersonalPerformanceListDtoSaveReq request = reqMsg.getMessage();
            
            Map<String,String> response = employeePersonalPerformanceListDtoServiceImpl.employeePersonalPerformanceListDtoSave(request);
            rspMsg = new ResponseMessage<Object>(response);

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工项目经历个人表现List保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            //设置异常
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "EMS1007",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
