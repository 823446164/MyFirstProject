/*
 * 文件名：EmployeeRankRelabelListDtoControllerImpl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：职级标签查询
 * 修改人：dxiao
 * 修改时间：2020年5月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
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
import com.amarsoft.app.ems.employee.template.controller.EmployeeRankRelabelListDtoController;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto.EmployeeRankRelabelListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto.EmployeeRankRelabelListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto.EmployeeRankRelabelListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto.EmployeeTargetRankListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeeRankRelabelListDtoServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dxiao
 * @version 2020年5月19日
 * @see EmployeeRankRelabelListDtoControllerImpl
 * @since 
 */
@Slf4j
@RestController
public class EmployeeRankRelabelListDtoControllerImpl implements EmployeeRankRelabelListDtoController {

    @Autowired
    private EmployeeRankRelabelListDtoServiceImpl employeeRankRelabelListDtoServiceImpl;
    /**
     * 
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<EmployeeRankRelabelListDtoQueryRsp>> employeeRankListDtoQuery(@Valid @RequestBody RequestMessage<EmployeeRankRelabelListDtoQueryReq> reqMsg) {
        ResponseMessage<EmployeeRankRelabelListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeRankRelabelListDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeRankRelabelListDtoQueryRsp response = employeeRankRelabelListDtoServiceImpl.employeeRankRelabelListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeRankRelabelListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeRankRelabelListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("职级标签List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "EMS1006",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeRankRelabelListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    //目标职级申请List保存
    public ResponseEntity<ResponseMessage<Object>> employeeRankListDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankRelabelListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankRelabelListDtoSaveReq request = reqMsg.getMessage();
            
            employeeRankRelabelListDtoServiceImpl.employeeRankRelabelListDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("目标职级申请List保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
