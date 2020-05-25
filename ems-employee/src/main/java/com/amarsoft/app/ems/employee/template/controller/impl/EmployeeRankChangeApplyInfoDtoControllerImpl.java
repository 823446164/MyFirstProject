/*
 * 文件名：EmployeeRankChangeApplyInfoDtoControllerImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：员工职级调整申请InfoController实现类
 * 修改人：xucheng
 * 修改时间：2020/5/20
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.employee.template.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import com.amarsoft.app.ems.employee.template.controller.EmployeeRankChangeApplyInfoDtoController;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankChangeApplyInfoDtoService;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplyinfodto.EmployeeRankChangeApplyInfoDtoSaveReq;

@Slf4j
@RestController
public class EmployeeRankChangeApplyInfoDtoControllerImpl implements EmployeeRankChangeApplyInfoDtoController {
    @Autowired
    EmployeeRankChangeApplyInfoDtoService employeeRankChangeApplyInfoDtoServiceImpl;
    
    /**
     * 
     * Description:员工职级调整申请详情Info查询
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<EmployeeRankChangeApplyInfoDtoQueryRsp>> employeeRankChangeApplyInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyInfoDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeRankChangeApplyInfoDtoQueryRsp> rspMsg = null;
        try {
            EmployeeRankChangeApplyInfoDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeRankChangeApplyInfoDtoQueryRsp response = employeeRankChangeApplyInfoDtoServiceImpl.employeeRankChangeApplyInfoDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeRankChangeApplyInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeRankChangeApplyInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整申请详情Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO xucheng Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeRankChangeApplyInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 
     * Description:员工职级调整申请详情Info保存
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeApplyInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankChangeApplyInfoDtoSaveReq request = reqMsg.getMessage();
            
            employeeRankChangeApplyInfoDtoServiceImpl.employeeRankChangeApplyInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整申请详情Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO xucheng Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
