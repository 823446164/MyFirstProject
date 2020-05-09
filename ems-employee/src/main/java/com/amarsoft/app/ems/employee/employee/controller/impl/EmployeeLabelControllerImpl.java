
/*
 * 文件名：EmployeeLabelControllerImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：员工能力标签查询controller实现类
 * 修改人：xszhou
 * 修改时间：2020/5/9
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.employee.employee.controller.impl;

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
import com.amarsoft.app.ems.employee.employee.controller.EmployeeLabelController;
import lombok.extern.slf4j.Slf4j;
import com.amarsoft.app.ems.employee.employee.cs.dto.employeeabilitylabels.EmployeeAbilityLabelsReq;
import com.amarsoft.app.ems.employee.employee.cs.dto.employeeabilitylabels.EmployeeAbilityLabelsRsp;
import com.amarsoft.app.ems.employee.service.EmployeeLabelService;

@Slf4j
@RestController
public class EmployeeLabelControllerImpl implements EmployeeLabelController {
	@Autowired
	EmployeeLabelService employeeLabelServiceImpl;
	
    @Override
    @Transactional
    /**
     * Description: <br>
     * 1、员工标签查询<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     *
     * ${tags}
     * @see
     */
    public ResponseEntity<ResponseMessage<EmployeeAbilityLabelsRsp>> employeeAbilityLabels(@RequestBody @Valid RequestMessage<EmployeeAbilityLabelsReq> reqMsg){
        ResponseMessage<EmployeeAbilityLabelsRsp> rspMsg = null;
        try {
        	EmployeeAbilityLabelsReq request = reqMsg.getMessage();
        	EmployeeAbilityLabelsRsp response = employeeLabelServiceImpl.employeeLabelQuery(request);
            rspMsg = new ResponseMessage<EmployeeAbilityLabelsRsp>(response);
            
            return new ResponseEntity<ResponseMessage<EmployeeAbilityLabelsRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工能力标签查询请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeAbilityLabelsRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}