/*
 * 文件名：EmployeeBelongChangeInfoDtoControllerImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：团队调整申请InfoController实现类
 * 修改人：xszhou
 * 修改时间：2020/5/15
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.employee.template.controller.impl;

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
import com.amarsoft.app.ems.employee.template.controller.EmployeeBelongChangeInfoDtoController;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeebelongchangeinfodto.EmployeeBelongChangeInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.service.EmployeeBelongChangeInfoDtoService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class EmployeeBelongChangeInfoDtoControllerImpl implements EmployeeBelongChangeInfoDtoController {
    @Autowired
    EmployeeBelongChangeInfoDtoService employeeBelongChangeInfoDtoServiceImpl;
    
    @Override
    @Transactional
    //团队调整申请Info查询
    public ResponseEntity<ResponseMessage<EmployeeBelongChangeInfoDtoQueryRsp>> employeeBelongChangeInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeBelongChangeInfoDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeBelongChangeInfoDtoQueryRsp> rspMsg = null;
        try {
            EmployeeBelongChangeInfoDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeBelongChangeInfoDtoQueryRsp response = employeeBelongChangeInfoDtoServiceImpl.employeeBelongChangeInfoDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeBelongChangeInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeBelongChangeInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("团队调整申请Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeBelongChangeInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Description: 更新员工团队<br>
     * @param EmployeeBelongChangeInfoDtoSaveReq
     * @see
     */
    @Override
    @Transactional
    //团队调整申请Info保存
    public ResponseEntity<ResponseMessage<Object>> employeeBelongChangeInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeBelongChangeInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeBelongChangeInfoDtoSaveReq request = reqMsg.getMessage();
            
            employeeBelongChangeInfoDtoServiceImpl.employeeBelongChangeInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("团队调整申请Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "EMS1020",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
