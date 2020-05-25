/*
 * 文件名：EmployeeRankChangeApplyListDtoControllerImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：员工职级调整申请ListController实现类
 * 修改人：xucheng
 * 修改时间：2020/5/20
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
import com.amarsoft.amps.avts.annotation.TemplateExport;
import com.amarsoft.app.ems.employee.template.controller.EmployeeRankChangeApplyListDtoController;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto.EmployeeRankChangeApplyListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankChangeApplyListDtoService;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeeRankChangeApplyListDtoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmployeeRankChangeApplyListDtoControllerImpl implements EmployeeRankChangeApplyListDtoController {
    @Autowired
    EmployeeRankChangeApplyListDtoService employeeRankChangeApplyListDtoServiceImpl;
    
    /**
     * 
     * Description:员工职级调整申请List查询
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    @TemplateExport(name="员工职级调整申请List", query = EmployeeRankChangeApplyListDtoServiceImpl.EmployeeRankChangeApplyListDtoReqQuery.class, convert=EmployeeRankChangeApplyListDtoServiceImpl.EmployeeRankChangeApplyListDtoRspConvert.class)
    public ResponseEntity<ResponseMessage<EmployeeRankChangeApplyListDtoQueryRsp>> employeeRankChangeApplyListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyListDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeRankChangeApplyListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeRankChangeApplyListDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeRankChangeApplyListDtoQueryRsp response = employeeRankChangeApplyListDtoServiceImpl.employeeRankChangeApplyListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeRankChangeApplyListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeRankChangeApplyListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整申请List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO xucheng Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeRankChangeApplyListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 
     * Description: 员工职级调整申请List保存
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeApplyListDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankChangeApplyListDtoSaveReq request = reqMsg.getMessage();
            
            employeeRankChangeApplyListDtoServiceImpl.employeeRankChangeApplyListDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整申请List保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO xucheng Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 
     * Description: 员工职级调整申请List删除
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeApplyListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeRankChangeApplyListDtoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankChangeApplyListDtoDeleteReq request = reqMsg.getMessage();
            
            employeeRankChangeApplyListDtoServiceImpl.employeeRankChangeApplyListDtoDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整申请List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO xucheng  Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
