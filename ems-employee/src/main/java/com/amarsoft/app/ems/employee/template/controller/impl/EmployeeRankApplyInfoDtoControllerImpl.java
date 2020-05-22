/*文件名：EmployeeRankApplyInfoDtoControllerImpl 
 * 版权：Copyright by www.amarsoft.com
 * 描述： 
 * 修改人：xphe 
 * 修改时间：2020/05/21
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：新增职级删除/指标选择校验
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
import com.amarsoft.app.ems.employee.template.controller.EmployeeRankApplyInfoDtoController;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.RankDeleteValidateReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.RankDeleteValidateRsp;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankApplyInfoDtoService;
import lombok.extern.slf4j.Slf4j;

/**
 * 〈员工职级申请InfoController实现类〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
@Slf4j
@RestController
public class EmployeeRankApplyInfoDtoControllerImpl implements EmployeeRankApplyInfoDtoController {
    @Autowired
    EmployeeRankApplyInfoDtoService employeeRankApplyInfoDtoServiceImpl;
    
    /**
     * 
     * Description: 员工职级申请Info查询
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<EmployeeRankApplyInfoDtoQueryRsp>> employeeRankApplyInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankApplyInfoDtoQueryReq> reqMsg){
        ResponseMessage<EmployeeRankApplyInfoDtoQueryRsp> rspMsg = null;
        try {
            EmployeeRankApplyInfoDtoQueryReq request = reqMsg.getMessage();
            
            EmployeeRankApplyInfoDtoQueryRsp response = employeeRankApplyInfoDtoServiceImpl.employeeRankApplyInfoDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeRankApplyInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<EmployeeRankApplyInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级申请Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeRankApplyInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 
     * Description: 员工职级申请Info保存
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> employeeRankApplyInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankApplyInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            EmployeeRankApplyInfoDtoSaveReq request = reqMsg.getMessage();
            
            employeeRankApplyInfoDtoServiceImpl.employeeRankApplyInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工职级申请Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 
     * Description: 职级删除/指标选择校验
     *
     * @param reqMsg
     * @return ResponseEntity
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<RankDeleteValidateRsp>> employeeRankApplyInfoExist(@RequestBody @Valid RequestMessage<RankDeleteValidateReq> reqMsg){
        ResponseMessage<RankDeleteValidateRsp> rspMsg = null;
        try {
            RankDeleteValidateReq request = reqMsg.getMessage();
            
            RankDeleteValidateRsp response = employeeRankApplyInfoDtoServiceImpl.employeeRankApplyInfoExist(request);
            rspMsg = new ResponseMessage<RankDeleteValidateRsp>(response);

            return new ResponseEntity<ResponseMessage<RankDeleteValidateRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("子职级删除校验："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO xphe  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<RankDeleteValidateRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
