/**
 * 新增用户
 * @Author xxu1
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
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

public interface UserController {
    @PostMapping(value = "/user/adduser", name="新增用户", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> addUser(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<AddUserReq> reqMsg);
    @PostMapping(value = "/user/updateuser", name="修改用户", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> updateUser(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UpdateUserReq> reqMsg);
    @PostMapping(value = "/user/deleteuser", name="删除用户", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> deleteUser(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<DeleteUserReq> reqMsg);
    @PostMapping(value = "/user/getusers", name="查询用户", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserQueryRsp>> userQuery(@RequestBody @Valid RequestMessage<UserQueryReq> reqMsg);
    @PostMapping(value = "/user/migrationcheck", name="查询用户迁移状态", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<MigrationCheckQueryRsp>> migrationCheckQuery(@RequestHeader("User-Id") String user,@RequestHeader("Content-Type") String contentType,
            @Valid RequestMessage<MigrationCheckQueryReq> reqMsg);
    @PostMapping(value = "/user/executemigration", name="用户机构迁移", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> executeMigration(@RequestHeader() HttpHeaders httpHeaders,@RequestHeader("User-Id") String user,@RequestHeader("Content-Type") String contentType,@RequestBody @Valid RequestMessage<ExecuteMigrationReq> reqMsg);
    @PostMapping(value = "/user/getuserpasswd", name="查询用户密码状态", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserPasswdQueryRsp>> userPasswdQuery(@RequestBody @Valid RequestMessage<UserPasswdQueryReq> reqMsg);
    @PostMapping(value = "/user/setlogonsuccess", name="记录用户登录成功", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> logonSuccess(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<LogonSuccessReq> reqMsg);
    @PostMapping(value = "/user/setlogonfailure", name="记录用户登录失败", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> logonFailure(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<LogonFailureReq> reqMsg);
    @PostMapping(value = "/user/setlogoutsuccess", name="记录用户退出成功", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> logoutSuccess(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<LogoutSuccessReq> reqMsg);
    @PostMapping(value = "/user/userrole", name="用户角色管理", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> userRole(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserRoleReq> reqMsg);
    @PostMapping(value = "/user/usergroup", name="用户所属组管理", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> userGroup(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserGroupReq> reqMsg);
    @PostMapping(value = "/user/getusermenus", name="查询用户可操作菜单", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserMenuRsp>> userMenu(@RequestHeader()HttpHeaders header);
    @PostMapping(value = "/user/checkuserauth", name="检查用户对URL的权限", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserAuthRsp>> userAuth(@RequestBody @Valid RequestMessage<UserAuthReq> reqMsg);
    @PostMapping(value = "/user/userresetpasswd", name="用户密码重置", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> userResetPasswd(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserResetPasswdReq> reqMsg);
    @PostMapping(value = "/user/toggleuserstatus", name="锁定、解锁用户", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> toggleUserStatus(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<ToggleUserStatusReq> reqMsg);
    @PostMapping(value = "/user/batchuserresetpasswd", name="批量用户密码重置", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> multiUserResetPasswd(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<MultiUserResetPasswdReq> reqMsg);
    @PostMapping(value = "/user/batchtoggleuserstatus", name="批量锁定、解锁用户", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> multiToggleUserStatus(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<MultiToggleUserStatusReq> reqMsg);
    @PostMapping(value = "/user/usersetpasswd", name="设置用户密码", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> userSetPasswd(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserSetPasswdReq> reqMsg);
    @PostMapping(value = "/user/userleave", name="用户请假", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> userLeave(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserLeaveReq> reqMsg);
    @PostMapping(value = "/user/userchangeleaves", name="用户修改请假", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> userChangeLeave(@RequestBody @Valid RequestMessage<ChangeUserLeaveReq> reqMsg);
    @PostMapping(value = "/user/usercancelleave", name="用户放弃请假", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> userCancelLeave(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserCancelLeaveReq> reqMsg);
    @PostMapping(value = "/user/getuserleaves", name="查询用户请假记录", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserLeaveQueryRsp>> userLeaveQuery(@RequestBody @Valid RequestMessage<UserLeaveQueryReq> reqMsg);
    @PostMapping(value = "/user/usersubst", name="用户转移授权", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> userSubst(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserSubstReq> reqMsg);
    @PostMapping(value = "/user/userchangesubst", name="用户修改转移授权", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> changeUserSubst(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserSubstReq> reqMsg);
   @PostMapping(value = "/user/usercancelsubst", name="用户放弃转移授权", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> userCancelSubst(@RequestHeader()HttpHeaders header, @RequestBody @Valid RequestMessage<UserCancelSubstReq> reqMsg);
    @PostMapping(value = "/user/getusersubsts", name="查询用户转移授权", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserSubstQueryRsp>> userSubstQuery(@RequestBody @Valid RequestMessage<UserSubstQueryReq> reqMsg);
    @PostMapping(value = "/user/setdefaultorg", name="设置用户默认机构", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> setDefaultOrg(@RequestBody @Valid RequestMessage<SetDefaultOrgReq> reqMsg);
//    @PostMapping(value = "/user/updateheadportrait", name="更改头像", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<ResponseMessage<Object>> updateHeadPortrait(HttpServletRequest request,HttpServletResponse response);
//    @GetMapping(value = "/user/getheadportrait", name="获取头像", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public void getHeadPortrait(HttpServletRequest request,HttpServletResponse response);
    @PostMapping(value = "/user/getuserbelong", name="查询用户所属机构", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserBelongQueryRsp>> userBelongQuery(@RequestBody @Valid RequestMessage<UserBelongQueryReq> reqMsg);
    @PostMapping(value = "/user/getusersbyorg", name="按机构和角色查询用户", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserQueryByOrgRsp>> userQueryByOrg(@RequestBody @Valid RequestMessage<UserQueryByOrgReq> reqMsg);
    @PostMapping(value = "/user/getusersbyparentorg", name="查询指定角色和机构下级机构用户", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<UserQueryByParentOrgRsp>> userQueryByParentOrg(@RequestBody @Valid RequestMessage<UserQueryByParentOrgReq> reqMsg);
}
