/*
 * 文件名：EmployeeRankChangeListDtoControllerImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：员工职级调整情况ListController实现类
 * 修改人：xucheng
 * 修改时间：2020/5/22
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
import com.amarsoft.app.ems.employee.template.controller.EmployeeRankChangeListDtoController;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoDeleteReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto.EmployeeRankChangeListDtoSaveReq;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankChangeListDtoService;
import com.amarsoft.app.ems.employee.template.service.impl.EmployeeRankChangeListDtoServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmployeeRankChangeListDtoControllerImpl implements EmployeeRankChangeListDtoController {
    @Autowired
    EmployeeRankChangeListDtoService employeeRankChangeListDtoServiceImpl;
    
    /**
     * Description: 员工职级调整List查询
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<EmployeeRankChangeListDtoQueryRsp>> employeeRankChangeListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankChangeListDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeRankChangeListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeRankChangeListDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeRankChangeListDtoQueryRsp response = employeeRankChangeListDtoServiceImpl.employeeRankChangeListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeRankChangeListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeRankChangeListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整情况List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO xucheng  Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeRankChangeListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    /**
     * Description: 员工职级调整情况List保
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeListDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankChangeListDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankChangeListDtoSaveReq request = reqMsg.getMessage();
            
            employeeRankChangeListDtoServiceImpl.employeeRankChangeListDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整情况List保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO xucheng Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Description: 员工职级调整情况List删除
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> employeeRankChangeListDtoDelete(@RequestBody @Valid RequestMessage<EmployeeRankChangeListDtoDeleteReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankChangeListDtoDeleteReq request = reqMsg.getMessage();
            
            employeeRankChangeListDtoServiceImpl.employeeRankChangeListDtoDelete(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级调整情况List删除："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO xucheng  Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
