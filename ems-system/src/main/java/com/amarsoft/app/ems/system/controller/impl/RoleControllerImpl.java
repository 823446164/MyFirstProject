package com.amarsoft.app.ems.system.controller.impl;

import java.util.Optional;

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
import com.amarsoft.app.ems.system.controller.RoleController;
import com.amarsoft.app.ems.system.cs.dto.addrole.AddRoleReq;
import com.amarsoft.app.ems.system.cs.dto.deleterole.DeleteRoleReq;
import com.amarsoft.app.ems.system.cs.dto.levelrolequery.LevelRoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelrolequery.LevelRoleQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.roleallquery.RoleAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleauth.RoleAuthReq;
import com.amarsoft.app.ems.system.cs.dto.rolequery.RoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.rolequery.RoleQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.roleuserquery.RoleUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.roleuserquery.RoleUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.updaterole.UpdateRoleReq;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserRoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserRoleQueryRsp;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.RoleService;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.roleinfodto.RoleInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.roleinfodto.RoleInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.roleinfodto.RoleInfoDtoSaveReq;
import com.amarsoft.app.ems.system.template.cs.dto.rolelistdto.RoleListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.rolelistdto.RoleListDtoQueryRsp;

import lombok.extern.slf4j.Slf4j;

/**
 * 角色服务处理类
 * @author xjzhao
 */
@Slf4j
@RestController
public class RoleControllerImpl implements RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    OrgService orgService;
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> addRole(@RequestBody @Valid RequestMessage<AddRoleReq> reqMsg){
        try {
            roleService.addRole(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900701", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> updateRole(@RequestBody @Valid RequestMessage<UpdateRoleReq> reqMsg){
        try {
            roleService.updateRole(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900703", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> deleteRole(@RequestBody @Valid RequestMessage<DeleteRoleReq> reqMsg){
        try {
            roleService.deleteRole(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900705", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<RoleQueryRsp>> roleQuery(@RequestBody @Valid RequestMessage<RoleQueryReq> reqMsg){
        try {
            RoleQueryRsp rsp = roleService.getRoles(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<RoleQueryRsp>>(new ResponseMessage<RoleQueryRsp>(rsp), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<RoleQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900704", e.getMessage());
            return new ResponseEntity<ResponseMessage<RoleQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> roleAuth(@RequestBody @Valid RequestMessage<RoleAuthReq> reqMsg){
        try {
            roleService.roleAuth(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900707", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<RoleAllQueryRsp>> roleAllQuery(@RequestBody @Valid RequestMessage<RoleAllQueryReq> reqMsg){
        ResponseMessage<RoleAllQueryRsp> rspMsg = null;
        try {
            RoleAllQueryRsp rsp= roleService.roleAllQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<RoleAllQueryRsp>>(new ResponseMessage<RoleAllQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            //事务回滚
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900709",e.getMessage());
            return new ResponseEntity<ResponseMessage<RoleAllQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<LevelRoleQueryRsp>> levelRoleQuery(@RequestBody @Valid RequestMessage<LevelRoleQueryReq> reqMsg){
        ResponseMessage<LevelRoleQueryRsp> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<LevelRoleQueryRsp>();
            LevelRoleQueryRsp rsp = roleService.levelRoleQuery(reqMsg.getMessage(),roleService,orgService);
            return new ResponseEntity<ResponseMessage<LevelRoleQueryRsp>>(new ResponseMessage<LevelRoleQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900704",e.getMessage());
            return new ResponseEntity<ResponseMessage<LevelRoleQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<RoleUserQueryRsp>> roleUserQuery(@RequestBody @Valid RequestMessage<RoleUserQueryReq> reqMsg){
        ResponseMessage<RoleUserQueryRsp> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<RoleUserQueryRsp>();
            RoleUserQueryRsp rsp = roleService.roleUserQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<RoleUserQueryRsp>>(new ResponseMessage<RoleUserQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询角色关联用户请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900709",e.getMessage());
            return new ResponseEntity<ResponseMessage<RoleUserQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Description: 根据用户查询角色信息<br>
     * ${tags}
     * @see
     */
    @Override
    public ResponseEntity<ResponseMessage<UserRoleQueryRsp>> userRoleQuery(@RequestBody @Valid RequestMessage<UserRoleQueryReq> reqMsg) {
        ResponseMessage<UserRoleQueryRsp> rspMsg = null;
        try {
            rspMsg = new ResponseMessage<UserRoleQueryRsp>();
            UserRoleQueryRsp rsp = roleService.userRoleQuery(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<UserRoleQueryRsp>>(new ResponseMessage<UserRoleQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询用户角色请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            //TODO
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<UserRoleQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 角色信息List查询
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<RoleListDtoQueryRsp>> roleListDtoQuery(@RequestBody @Valid RequestMessage<RoleListDtoQueryReq> reqMsg){
        ResponseMessage<RoleListDtoQueryRsp> rspMsg = null;
        try {
            RoleListDtoQueryReq request = reqMsg.getMessage();
            
            RoleListDtoQueryRsp response = roleService.roleListDtoQuery(request);
            rspMsg = new ResponseMessage<RoleListDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<RoleListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("角色信息List查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<RoleListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 角色信息Info查询
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<RoleInfoDtoQueryRsp>> roleInfoDtoQuery(@RequestBody @Valid RequestMessage<RoleInfoDtoQueryReq> reqMsg){
        ResponseMessage<RoleInfoDtoQueryRsp> rspMsg = null;
        try {
            RoleInfoDtoQueryReq request = reqMsg.getMessage();
            
            RoleInfoDtoQueryRsp response = roleService.roleInfoDtoQuery(request);
            rspMsg = new ResponseMessage<RoleInfoDtoQueryRsp>(response);

            return new ResponseEntity<ResponseMessage<RoleInfoDtoQueryRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("角色信息Info查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<RoleInfoDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 角色信息Info保存
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> roleInfoDtoSave(@RequestBody @Valid RequestMessage<RoleInfoDtoSaveReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            RoleInfoDtoSaveReq request = reqMsg.getMessage();
            
            roleService.roleInfoDtoSave(request);
            rspMsg = new ResponseMessage<Object>();

            return new ResponseEntity<ResponseMessage<Object>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("角色信息Info保存："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 用户待引入的list查询接口
     */
	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>> roleUserListDtoQuery(@RequestBody @Valid RequestMessage<EmployeeInfoListDtoQueryReq> reqMsg) {
		ResponseMessage<EmployeeInfoListDtoQueryRsp> rspMsg = null;
		try {
			EmployeeInfoListDtoQueryRsp rsp = roleService.roleUserListDtoQuery(reqMsg.getMessage());
			rspMsg = new ResponseMessage<EmployeeInfoListDtoQueryRsp>(rsp);
			return new ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
		}catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("用户待引入list查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	/**
	 * 用户已引入的list查询接口
	 */
	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>> RoleUserIntroducedListDtoQuery(@RequestBody
			@Valid RequestMessage<EmployeeInfoListDtoQueryReq> reqMsg) {
		ResponseMessage<EmployeeInfoListDtoQueryRsp> rspMsg = null;
		try {
			EmployeeInfoListDtoQueryRsp rsp = roleService.RoleUserIntroducedListDtoQuery(reqMsg.getMessage());
			rspMsg = new ResponseMessage<EmployeeInfoListDtoQueryRsp>(rsp);
			return new ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>>(rspMsg , HttpStatus.OK);
		}catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("用户已引入list查询："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO Auto-generated  //默认异常码未设置，请补充。
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "",e.getMessage());
            return new ResponseEntity<ResponseMessage<EmployeeInfoListDtoQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	/**
	 * 用户引入的多记录保存接口
	 */
	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<Object>> roleUserListDtoSave(@RequestBody
			@Valid RequestMessage<EmployeeInfoListDtoQueryReq> reqMsg) {
		ResponseMessage<Object> rspMsg = null;
		try {
			roleService.roleUserListDtoSave(reqMsg.getMessage());
			rspMsg = new ResponseMessage<Object>();
			return new ResponseEntity<ResponseMessage<Object>>(rspMsg,HttpStatus.OK);
		}catch(Exception e) {
			if(log.isErrorEnabled()) {
				log.error("用户引入多记录保存："+reqMsg.toString(), e);
			}
			//事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rspMsg = ResponseMessage.getResponseMessageFromException(e, "", e.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 用户引入的多记录删除接口
	 */
	@Override
	@Transactional
	public ResponseEntity<ResponseMessage<Object>> roleUserListDtoDelete(@RequestBody
			@Valid RequestMessage<EmployeeInfoListDtoQueryReq> reqMsg) {
		ResponseMessage<Object> rspMsg = null;
		try {
			roleService.roleUserListDtoDelete(reqMsg.getMessage());
			rspMsg = new ResponseMessage<Object>();
			return new ResponseEntity<ResponseMessage<Object>>(rspMsg,HttpStatus.OK);
		}catch(Exception e) {
			if(log.isErrorEnabled()) {
				log.error("用户引入多记录删除："+reqMsg.toString(), e);
			}
			//事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			rspMsg = ResponseMessage.getResponseMessageFromException(e, "", e.getMessage());
			return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}