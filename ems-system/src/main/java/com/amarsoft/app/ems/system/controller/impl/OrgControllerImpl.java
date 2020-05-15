package com.amarsoft.app.ems.system.controller.impl;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.OrgController;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryReq;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.coperateorganizationquery.CoperateOrganizationQueryReq;
import com.amarsoft.app.ems.system.cs.dto.coperateorganizationquery.CoperateOrganizationQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgidquery.OrgIdQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgidquery.OrgIdQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfoadd.OrgInfoAddReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoallquery.OrgInfoAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoallquery.OrgInfoAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfodelete.OrgInfoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoquery.OrgInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orginfoupdate.OrgInfoUpdateReq;
import com.amarsoft.app.ems.system.cs.dto.orgtreequery.OrgTreeQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgtreequery.OrgTreeQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryRsp;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.UserService;
import com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto.DeleteInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoSearchReq;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDtoSaveReq;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptinfodto.SecondLevelDeptInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptinfodto.SecondLevelDeptInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptinfodto.SecondLevelDeptInfoDtoSaveReq;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDtoQueryRsp;

import lombok.extern.slf4j.Slf4j;

/**
 * 机构服务的处理类
 * @author xxu1
 * 
 * @ModefiedBy hzhang23
 */
@Slf4j
@RestController
public class OrgControllerImpl implements OrgController {
    
    @Autowired
    OrgService orgService;
    
    @Autowired
    UserService userService;
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<OrgInfoAllQueryRsp>> orgInfoAllQuery(@RequestBody @Valid RequestMessage<OrgInfoAllQueryReq> reqMsg){
        try {
            OrgInfoAllQueryRsp responseList = orgService.getOrgAll(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<OrgInfoAllQueryRsp>>(new ResponseMessage<OrgInfoAllQueryRsp>(responseList), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<OrgInfoAllQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900201", e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgInfoAllQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<OrgInfoQueryRsp>> orgInfoQuery(@RequestBody @Valid RequestMessage<OrgInfoQueryReq> reqMsg){
        try {
            OrgInfoQueryRsp response = orgService.getOrgInfo(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<OrgInfoQueryRsp>>(new ResponseMessage<OrgInfoQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<OrgInfoQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900201", e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgInfoQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  

    /**
     * Description: 修改指定部门状态信息
     * @param reqMsg
     * @return  Map
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> orgInfoUpdate(@RequestBody @Valid RequestMessage<OrgInfoUpdateReq> reqMsg){
        try {
            Map<String, String> map =  orgService.setOrgInfo(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(map), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900202", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> orgInfoAdd(@RequestBody @Valid RequestMessage<OrgInfoAddReq> reqMsg){
        try {
            orgService.addOrgInfo(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900203", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> orgInfoDelete(@RequestBody @Valid RequestMessage<OrgInfoDeleteReq> reqMsg){
        try {
            orgService.deleteOrgInfo(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900205", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<ConditionalOrgsQueryRsp>> conditionalOrgsQuery(@RequestBody @Valid RequestMessage<ConditionalOrgsQueryReq> reqMsg){
        try {
            ConditionalOrgsQueryRsp rsp = orgService.orgInfoQueryByCondition(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<ConditionalOrgsQueryRsp>>(new ResponseMessage<ConditionalOrgsQueryRsp>(rsp), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<ConditionalOrgsQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900201", e.getMessage());
            return new ResponseEntity<ResponseMessage<ConditionalOrgsQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Description: 查询部门或团队下员工
     * @param reqMsg
     * @return  ResponseEntity
     * @see
     */
    @Override
    public ResponseEntity<ResponseMessage<OrgUserQueryRsp>> orgUserQuery(@RequestBody @Valid RequestMessage<OrgUserQueryReq> reqMsg){
        ResponseMessage<OrgUserQueryRsp> rspMsg = null;
        try {
            OrgUserQueryRsp rsp = orgService.orgUserQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<OrgUserQueryRsp>>(new ResponseMessage<OrgUserQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询机构用户请求报文："+ reqMsg.toString(), e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900209",e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgUserQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<OrgTreeQueryRsp>> orgTreeQuery(){
        ResponseMessage<OrgTreeQueryRsp> rspMsg = null;
        try {
            OrgTreeQueryRsp rsp = orgService.orgTreeQuery();
            return new ResponseEntity<ResponseMessage<OrgTreeQueryRsp>>(new ResponseMessage<OrgTreeQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询机构树图请求报文：",e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900211",e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgTreeQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<ResponseMessage<OrgIdQueryRsp>> orgIdQuery(@RequestBody @Valid RequestMessage<OrgIdQueryReq> reqMsg){
        ResponseMessage<OrgIdQueryRsp> rspMsg = null;
        try {
            OrgIdQueryRsp rsp = orgService.getOrgId(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<OrgIdQueryRsp>>(new ResponseMessage<OrgIdQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("获取机构编号请求报文：",e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900212",e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgIdQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<ResponseMessage<CoperateOrganizationQueryRsp>> coperateOrganizationQuery(@RequestBody @Valid RequestMessage<CoperateOrganizationQueryReq> reqMsg){
        try {
            CoperateOrganizationQueryRsp rsp= orgService.getCoperateOrganization(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<CoperateOrganizationQueryRsp>>(new ResponseMessage<CoperateOrganizationQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询法人机构请求报文：",e);
            }
            ResponseMessage<CoperateOrganizationQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900213", e.getMessage());
            return new ResponseEntity<ResponseMessage<CoperateOrganizationQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Description: 查询一级部门
     * @param reqMsg
     * @return  ResponseEntity
     * @see
     */
    @Override
    public ResponseEntity<ResponseMessage<OneLevelDeptDtoQueryRsp>> oneLevelDeptDtoQuery(@RequestBody @Valid RequestMessage<OneLevelDeptDtoQueryReq> reqMsg) {
        try {
            OneLevelDeptDtoQueryRsp response = orgService.oneLevelDeptDtoQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<OneLevelDeptDtoQueryRsp>>(new ResponseMessage<OneLevelDeptDtoQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<OneLevelDeptDtoQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900201", e.getMessage());
            return new ResponseEntity<ResponseMessage<OneLevelDeptDtoQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Description: 新增、保存一级部门
     * @param reqMsg
     * @return  Map
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> oneLevelDeptDtoSave(@RequestBody @Valid RequestMessage<OneLevelDeptDtoSaveReq> reqMsg) {
        try {
            Map<String, String> map =  orgService.oneLevelDeptDtoSave(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(map), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900203", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Description: 搜索二级部门list
     * @param reqMsg
     * @return  ResponseEntity
     * @see
     */
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<SearchSecondLevelDeptListDtoQueryRsp>> searchSecondLevelDeptListDtoQuery(@RequestBody @Valid RequestMessage<SearchSecondLevelDeptListDtoQueryReq> reqMsg) {
        try {
            SearchSecondLevelDeptListDtoQueryRsp response = orgService.searchSecondLevelDeptListDtoQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<SearchSecondLevelDeptListDtoQueryRsp>>(new ResponseMessage<SearchSecondLevelDeptListDtoQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<SearchSecondLevelDeptListDtoQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900201", e.getMessage());
            return new ResponseEntity<ResponseMessage<SearchSecondLevelDeptListDtoQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Description: 删除部门信息
     * @param reqMsg
     * @return  
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> deleteInfoDtoQuery(@RequestBody @Valid RequestMessage<DeleteInfoDtoQueryReq> reqMsg) {
        try {
            Map<String, String> map =  orgService.deleteInfoDtoQuery(reqMsg.getMessage());
            log.info(String.valueOf(map.size()));
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(map), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900205", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Description: 查询二级部门
     * @param reqMsg
     * @return  ResponseEntity
     * @see
     */
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<SecondLevelDeptInfoDtoQueryRsp>> secondLevelDeptInfoDtoQuery(@RequestBody @Valid RequestMessage<SecondLevelDeptInfoDtoQueryReq> reqMsg) {
        try {
            SecondLevelDeptInfoDtoQueryRsp response = orgService.secondLevelDeptInfoDtoQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<SecondLevelDeptInfoDtoQueryRsp>>(new ResponseMessage<SecondLevelDeptInfoDtoQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<SecondLevelDeptInfoDtoQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900201", e.getMessage());
            return new ResponseEntity<ResponseMessage<SecondLevelDeptInfoDtoQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Description: 查询二级部门list
     * @param reqMsg
     * @return  ResponseEntity
     * @see
     */
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<SecondLevelDeptListDtoQueryRsp>> secondLevelDeptListDtoQuery(@RequestBody @Valid RequestMessage<SecondLevelDeptListDtoQueryReq> reqMsg) {
        try {
            SecondLevelDeptListDtoQueryRsp response = orgService.secondLevelDeptListDtoQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<SecondLevelDeptListDtoQueryRsp>>(new ResponseMessage<SecondLevelDeptListDtoQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<SecondLevelDeptListDtoQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900201", e.getMessage());
            return new ResponseEntity<ResponseMessage<SecondLevelDeptListDtoQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Description: 部门树图展示
     * @param reqMsg
     * @return  rsp
     * @see
     */
    @Override
    public ResponseEntity<ResponseMessage<OrgTreeQueryRsp>> oneSecondOrgTreeQuery(@RequestBody @Valid RequestMessage<OrgTreeQueryReq> reqMsg) {
        ResponseMessage<OrgTreeQueryRsp> rspMsg = null;
        try {
            OrgTreeQueryRsp rsp = orgService.oneSecondOrgTreeQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<OrgTreeQueryRsp>>(new ResponseMessage<OrgTreeQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询机构树图请求报文：",e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900211",e.getMessage());
            return new ResponseEntity<ResponseMessage<OrgTreeQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Description: 二级部门员工List
     * @param reqMsg
     * @return  rsp
     * @see
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>> employeeInfoListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoQueryReq> reqMsg) {
        ResponseMessage<EmployeeInfoListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeInfoListDtoQueryReq request = reqMsg.getMessage();
            EmployeeInfoListDtoQueryRsp response = orgService.employeeInfoListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeInfoListDtoQueryRsp>(response);
            return new ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工详情List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900201",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Description: 搜索二级部门员工List
     * @param reqMsg
     * @return  rsp
     * @see
     */
    @Override
    @CodeQuery
    @Transactional
    public ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>> employeeInfoListDtoSearch(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoSearchReq> reqMsg) {
        ResponseMessage<EmployeeInfoListDtoQueryRsp> rspMsg = null;
        try {
            EmployeeInfoListDtoSearchReq request = reqMsg.getMessage();
            EmployeeInfoListDtoQueryRsp response = orgService.employeeInfoListDtoQuery(request);
            rspMsg = new ResponseMessage<EmployeeInfoListDtoQueryRsp>(response);
            return new ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("员工详情List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900201",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}