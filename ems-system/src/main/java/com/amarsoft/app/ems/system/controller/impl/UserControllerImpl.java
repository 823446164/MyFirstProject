package com.amarsoft.app.ems.system.controller.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.aecd.system.constant.UserEventType;
import com.amarsoft.amps.arem.util.MessageHelper;
import com.amarsoft.app.ems.system.controller.UserController;
import com.amarsoft.app.ems.system.cs.dto.adduser.AddUserReq;
import com.amarsoft.app.ems.system.cs.dto.changeuserleave.ChangeUserLeaveReq;
import com.amarsoft.app.ems.system.cs.dto.deleteuser.DeleteUserReq;
import com.amarsoft.app.ems.system.cs.dto.executemigration.ExecuteMigrationReq;
import com.amarsoft.app.ems.system.cs.dto.logonfailure.LogonFailureReq;
import com.amarsoft.app.ems.system.cs.dto.logonsuccess.LogonSuccessReq;
import com.amarsoft.app.ems.system.cs.dto.logoutsuccess.LogoutSuccessReq;
import com.amarsoft.app.ems.system.cs.dto.migrationcheckquery.MigrationCheckQueryReq;
import com.amarsoft.app.ems.system.cs.dto.migrationcheckquery.MigrationCheckQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.multitoggleuserstatus.MultiToggleUserStatusReq;
import com.amarsoft.app.ems.system.cs.dto.multiuserresetpasswd.MultiUserResetPasswdReq;
import com.amarsoft.app.ems.system.cs.dto.setdefaultorg.SetDefaultOrgReq;
import com.amarsoft.app.ems.system.cs.dto.toggleuserstatus.ToggleUserStatusReq;
import com.amarsoft.app.ems.system.cs.dto.updateuser.UpdateUserReq;
import com.amarsoft.app.ems.system.cs.dto.userauth.UserAuthReq;
import com.amarsoft.app.ems.system.cs.dto.userauth.UserAuthRsp;
import com.amarsoft.app.ems.system.cs.dto.userbelongquery.UserBelongQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userbelongquery.UserBelongQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.usercancelleave.UserCancelLeaveReq;
import com.amarsoft.app.ems.system.cs.dto.usercancelsubst.UserCancelSubstReq;
import com.amarsoft.app.ems.system.cs.dto.usergroup.UserGroupReq;
import com.amarsoft.app.ems.system.cs.dto.userleave.UserLeaveReq;
import com.amarsoft.app.ems.system.cs.dto.userleavequery.UserLeaveQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userleavequery.UserLeaveQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.usermenu.UserMenuRsp;
import com.amarsoft.app.ems.system.cs.dto.userpasswdquery.UserPasswdQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userpasswdquery.UserPasswdQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.userquery.UserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userquery.UserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.userquerybyorg.UserQueryByOrgReq;
import com.amarsoft.app.ems.system.cs.dto.userquerybyorg.UserQueryByOrgRsp;
import com.amarsoft.app.ems.system.cs.dto.userquerybyparentorg.UserQueryByParentOrgReq;
import com.amarsoft.app.ems.system.cs.dto.userquerybyparentorg.UserQueryByParentOrgRsp;
import com.amarsoft.app.ems.system.cs.dto.userresetpasswd.UserResetPasswdReq;
import com.amarsoft.app.ems.system.cs.dto.userrole.UserRoleReq;
import com.amarsoft.app.ems.system.cs.dto.usersetpasswd.UserSetPasswdReq;
import com.amarsoft.app.ems.system.cs.dto.usersubst.UserSubstReq;
import com.amarsoft.app.ems.system.cs.dto.usersubstquery.UserSubstQueryReq;
import com.amarsoft.app.ems.system.cs.dto.usersubstquery.UserSubstQueryRsp;
import com.amarsoft.app.ems.system.service.GroupService;
import com.amarsoft.app.ems.system.service.MenuService;
import com.amarsoft.app.ems.system.service.MigrationService;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.RoleService;
import com.amarsoft.app.ems.system.service.UserService;
import com.amarsoft.app.ems.system.util.UserEventHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    GroupService groupService;
    
    @Autowired
    OrgService orgService;
    
    @Autowired
    MenuService menuService;
    
    @Autowired
    MigrationService migrationService;
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> addUser(@RequestHeader()HttpHeaders header,@RequestBody @Valid RequestMessage<AddUserReq> reqMsg) {
        try {
            userService.addUser(header,reqMsg.getMessage());
            UserEventHelper.setUserEvent(UserEventType.AddUser.id,"新增用户成功",header);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900901", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> updateUser(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UpdateUserReq> reqMsg) {
        try {
            userService.updateUser(header,reqMsg.getMessage());
            UserEventHelper.setUserEvent(UserEventType.UpdateUser.id,"更新用户成功",header);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900903", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> deleteUser(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<DeleteUserReq> reqMsg) {
        try {
            userService.deleteUser(reqMsg.getMessage());
            UserEventHelper.setUserEvent(UserEventType.DeleteUser.id,"删除用户成功",header);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900905", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<UserQueryRsp>> userQuery(@RequestBody @Valid RequestMessage<UserQueryReq> reqMsg){
        try {
            UserQueryRsp rsp = userService.getUsers(reqMsg.getMessage(), true);
            return new ResponseEntity<ResponseMessage<UserQueryRsp>>(new ResponseMessage<UserQueryRsp>(rsp), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<UserQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900904", e.getMessage());
            return new ResponseEntity<ResponseMessage<UserQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<ResponseMessage<UserPasswdQueryRsp>> userPasswdQuery(@RequestBody @Valid RequestMessage<UserPasswdQueryReq> reqMsg){
        try {
        	System.out.println("888888888888888888888888888888888");
            UserPasswdQueryRsp rsp = null;
            String userId = userService.getUserId(reqMsg.getMessage().getLogonId());
            if(!StringUtils.isEmpty(userId)) {
                rsp = userService.getUserPasswd(userId);
            }
            return new ResponseEntity<ResponseMessage<UserPasswdQueryRsp>>(new ResponseMessage<UserPasswdQueryRsp>(rsp), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<UserPasswdQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900904", e.getMessage());
            return new ResponseEntity<ResponseMessage<UserPasswdQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> logonSuccess(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<LogonSuccessReq> reqMsg){
        try {
            String userId = userService.getUserId(reqMsg.getMessage().getLogonId());
            if(!StringUtils.isEmpty(userId)) {
                userService.setLogonSuccess(userId, reqMsg.getMessage().getLogonTime());
                UserEventHelper.setUserEvent(UserEventType.LoginSuccess.id, MessageHelper.getMessage("900950"), header);//用户登录成功
            }
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900944", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> logonFailure(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<LogonFailureReq> reqMsg){
        try {
            String userId = userService.getUserId(reqMsg.getMessage().getLogonId());
            if(!StringUtils.isEmpty(userId)) {
                userService.setLogonFailure(userId, reqMsg.getMessage().getStatus());
                UserEventHelper.setUserEvent(UserEventType.LoginFailed.id, MessageHelper.getMessage("900951"), header);//用户登录失败
            }
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900945", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> logoutSuccess(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<LogoutSuccessReq> reqMsg){
        try {
            String userId = userService.getUserId(reqMsg.getMessage().getLogonId());
            if(!StringUtils.isEmpty(userId)) {
                userService.setLogoutSuccess(userId, reqMsg.getMessage().getLogoutTime());
                UserEventHelper.setUserEvent(UserEventType.LogoutSuccess.id, MessageHelper.getMessage("900952"), header);//用户退出成功
            }
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900946", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> userRole(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserRoleReq> reqMsg) {
        try {
            userService.userRole(header,reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900907", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> userGroup(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserGroupReq> reqMsg) {
        try {
            userService.userGroup(header,reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900909", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<UserMenuRsp>> userMenu(@RequestHeader HttpHeaders header){
        try {
            UserMenuRsp rsp = userService.getUserMenus(GlobalShareContextHolder.getUserId(), GlobalShareContextHolder.getOrgId(), userService, roleService, groupService, menuService);
            return new ResponseEntity<ResponseMessage<UserMenuRsp>>(new ResponseMessage<UserMenuRsp>(rsp), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("获取用户菜单出错", e);
            }
            ResponseMessage<UserMenuRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900910", e.getMessage());
            return new ResponseEntity<ResponseMessage<UserMenuRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<UserAuthRsp>> userAuth(@RequestBody @Valid RequestMessage<UserAuthReq> reqMsg){
        try {
            UserAuthRsp rsp = new UserAuthRsp();
            if(userService.checkUserAuth(reqMsg.getMessage().getUrl(), reqMsg.getMessage().getUserId(),  reqMsg.getMessage().getOrgId(), userService, roleService, groupService, menuService)) {
                rsp.setAuth(YesNo.Yes.id);
            }else {
                rsp.setAuth(YesNo.No.id);
            }
            return new ResponseEntity<ResponseMessage<UserAuthRsp>>(new ResponseMessage<UserAuthRsp>(rsp), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<UserAuthRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900911", e.getMessage());
            return new ResponseEntity<ResponseMessage<UserAuthRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> userResetPasswd(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserResetPasswdReq> reqMsg) {
        try {
            userService.userResetPasswd(reqMsg.getMessage());
            UserEventHelper.setUserEvent(UserEventType.ResetPassword.id,"",header);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900912", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> multiUserResetPasswd(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<MultiUserResetPasswdReq> reqMsg) {
        try {
            userService.multiUserResetPasswd(userService,reqMsg.getMessage());
            UserEventHelper.setUserEvent(UserEventType.ResetPassword.id,"",header);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900912", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> userSetPasswd(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserSetPasswdReq> reqMsg) {
        try {
            userService.userSetPasswd(reqMsg.getMessage());
            UserEventHelper.setUserEvent(UserEventType.SetPassword.id,"",header);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900913", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> userLeave(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserLeaveReq> reqMsg) {
        try {
            userService.userLeave(reqMsg.getMessage());
            UserEventHelper.setUserEvent(UserEventType.Leave.id,"",header);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900914", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> userCancelLeave(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserCancelLeaveReq> reqMsg) {
        try {
            userService.userCancelLeave(reqMsg.getMessage());
            UserEventHelper.setUserEvent(UserEventType.CancelLeave.id,"",header);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900915", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<UserLeaveQueryRsp>> userLeaveQuery(@RequestBody @Valid RequestMessage<UserLeaveQueryReq> reqMsg){
        try {
            UserLeaveQueryRsp rsp = userService.getUserLeaves(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<UserLeaveQueryRsp>>(new ResponseMessage<UserLeaveQueryRsp>(rsp), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<UserLeaveQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900916", e.getMessage());
            return new ResponseEntity<ResponseMessage<UserLeaveQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    @CodeQuery
    public ResponseEntity<ResponseMessage<Object>> userSubst(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserSubstReq> reqMsg) {
        try {
            userService.userSubst(reqMsg.getMessage());
            UserEventHelper.setUserEvent(UserEventType.Substitute.id,"",header);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900917", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> userCancelSubst(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserCancelSubstReq> reqMsg) {
        try {
            userService.userCancelSubst(reqMsg.getMessage());
            UserEventHelper.setUserEvent(UserEventType.CancelSubstitute.id,"",header);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900918", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<UserSubstQueryRsp>> userSubstQuery(@RequestBody @Valid RequestMessage<UserSubstQueryReq> reqMsg){
        try {
            UserSubstQueryRsp rsp = userService.getUserSubsts(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<UserSubstQueryRsp>>(new ResponseMessage<UserSubstQueryRsp>(rsp), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<UserSubstQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900919", e.getMessage());
            return new ResponseEntity<ResponseMessage<UserSubstQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> toggleUserStatus(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<ToggleUserStatusReq> reqMsg){
        try {
            userService.toggleUserStatus(reqMsg.getMessage());
            UserEventHelper.setUserEvent(UserEventType.ToggleStatus.id,"",header);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("锁定、解锁用户请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900927", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> multiToggleUserStatus(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<MultiToggleUserStatusReq> reqMsg){
        try {
            userService.multiToggleUserStatus(userService,reqMsg.getMessage());
            UserEventHelper.setUserEvent(UserEventType.ToggleStatus.id,"",header);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("锁定、解锁用户请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900927", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    @CodeQuery
    public ResponseEntity<ResponseMessage<MigrationCheckQueryRsp>> migrationCheckQuery(@RequestHeader("User-Id") String user,@RequestHeader("Content-Type") String contentType,@RequestBody @Valid RequestMessage<MigrationCheckQueryReq> reqMsg){
        ResponseMessage<MigrationCheckQueryRsp> rspMsg = null;
        try {
            rspMsg = migrationService.migrationCheckQuery(user,contentType,reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<MigrationCheckQueryRsp>>(rspMsg, HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询用户迁移状态请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900960",e.getMessage());
            return new ResponseEntity<ResponseMessage<MigrationCheckQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    @CodeQuery
    public ResponseEntity<ResponseMessage<Object>> executeMigration(@RequestHeader() HttpHeaders httpHeaders,@RequestHeader("User-Id") String user,@RequestHeader("Content-Type") String contentType,@RequestBody @Valid RequestMessage<ExecuteMigrationReq> reqMsg){
        ResponseMessage<Object> rspMsg = null;
        try {
            rspMsg = migrationService.executeMigration(user,contentType,reqMsg.getMessage(),httpHeaders);
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询用户迁移状态请求报文："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900961",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/user/updateheadportrait", name="更改头像", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> updateHeadPortrait(HttpServletRequest request,HttpServletResponse response) {
        ResponseMessage<Object> hrb = null;
        try {
            hrb = userService.updateHeadportrait(request,response);
            return new ResponseEntity<ResponseMessage<Object>>(hrb, HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("更换头像时出错：", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            hrb = ResponseMessage.getResponseMessageFromException(e, "900931",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(value = "/user/getheadportrait", name="获取头像", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getHeadPortrait(HttpServletRequest request,HttpServletResponse response)  {
        try {
            userService.getHeadPortrait(request,response);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("获取头像出错：", e);
            }
        }
    }
    
    @Override
    public ResponseEntity<ResponseMessage<UserBelongQueryRsp>> userBelongQuery(@RequestBody @Valid RequestMessage<UserBelongQueryReq> reqMsg){
        ResponseMessage<UserBelongQueryRsp> rspMsg = null;
        try {
            UserBelongQueryRsp rsp = userService.getUserBelong(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<UserBelongQueryRsp>>(new ResponseMessage<UserBelongQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询用户所属机构请求报文："+ reqMsg.toString(), e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900947", e.getMessage());
            return new ResponseEntity<ResponseMessage<UserBelongQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> setDefaultOrg(@RequestBody @Valid RequestMessage<SetDefaultOrgReq> reqMsg) {
        ResponseMessage<Object> rspMsg = null;
        try {
            userService.setDefaultOrg(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("设置默认机构时出错："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900948",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> userChangeLeave(@RequestBody @Valid RequestMessage<ChangeUserLeaveReq> reqMsg) {
        ResponseMessage<Object> rspMsg = null;
        try {
            userService.userChangeLeave(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("用户修改请假信息时出错："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900949",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> changeUserSubst(@RequestHeader()HttpHeaders header,@RequestBody @Valid RequestMessage<UserSubstReq> reqMsg) {
        ResponseMessage<Object> rspMsg = null;
        try {
            userService.changeUserSubst(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(Optional.empty()), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("修改用户转授权信息出错："+ reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900953",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<ResponseMessage<UserQueryByOrgRsp>> userQueryByOrg(@RequestBody @Valid RequestMessage<UserQueryByOrgReq> reqMsg){
        ResponseMessage<UserQueryByOrgRsp> rspMsg = null;
        try {
            UserQueryByOrgRsp rsp = userService.userQueryByOrg(reqMsg.getMessage());
            rspMsg = new ResponseMessage<UserQueryByOrgRsp>(rsp);
            return new ResponseEntity<ResponseMessage<UserQueryByOrgRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("按机构和角色查询用户请求报文："+ reqMsg.toString(), e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900904",e.getMessage());
            return new ResponseEntity<ResponseMessage<UserQueryByOrgRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<UserQueryByParentOrgRsp>> userQueryByParentOrg(@RequestBody @Valid RequestMessage<UserQueryByParentOrgReq> reqMsg){
        ResponseMessage<UserQueryByParentOrgRsp> rspMsg = null;
        try {
            
            UserQueryByParentOrgRsp rsp = userService.userQueryByParentOrg(reqMsg.getMessage());
            rspMsg = new ResponseMessage<UserQueryByParentOrgRsp>(rsp);
            return new ResponseEntity<ResponseMessage<UserQueryByParentOrgRsp>>(rspMsg , HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询指定角色和机构下级机构用户请求报文："+ reqMsg.toString(), e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900904",e.getMessage());
            return new ResponseEntity<ResponseMessage<UserQueryByParentOrgRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}