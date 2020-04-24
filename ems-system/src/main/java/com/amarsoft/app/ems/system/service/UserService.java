package com.amarsoft.app.ems.system.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.cs.dto.adduser.AddUserReq;
import com.amarsoft.app.ems.system.cs.dto.changeuserleave.ChangeUserLeaveReq;
import com.amarsoft.app.ems.system.cs.dto.deleteuser.DeleteUserReq;
import com.amarsoft.app.ems.system.cs.dto.multitoggleuserstatus.MultiToggleUserStatusReq;
import com.amarsoft.app.ems.system.cs.dto.multiuserresetpasswd.MultiUserResetPasswdReq;
import com.amarsoft.app.ems.system.cs.dto.setdefaultorg.SetDefaultOrgReq;
import com.amarsoft.app.ems.system.cs.dto.toggleuserstatus.ToggleUserStatusReq;
import com.amarsoft.app.ems.system.cs.dto.updateuser.UpdateUserReq;
import com.amarsoft.app.ems.system.cs.dto.userbelongquery.UserBelongQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userbelongquery.UserBelongQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.usercancelleave.UserCancelLeaveReq;
import com.amarsoft.app.ems.system.cs.dto.usercancelsubst.UserCancelSubstReq;
import com.amarsoft.app.ems.system.cs.dto.usergroup.UserGroupReq;
import com.amarsoft.app.ems.system.cs.dto.userleave.UserLeaveReq;
import com.amarsoft.app.ems.system.cs.dto.userleavequery.UserLeaveQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userleavequery.UserLeaveQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.usermenu.UserMenuRsp;
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

/**
 * 用户服务的处理接口
 * @author xjzhao
 */
public interface UserService {
    /**
     * 新增用户
     * @param header 
     * @param req
     */
    public void addUser(HttpHeaders header, AddUserReq req);
    
    /**
     * 修改用户
     * @param header 
     * @param req
     * @return 是否更改了权限
     */
    public boolean updateUser(HttpHeaders header, UpdateUserReq req);
    
    /**
     * 删除用户
     * @param req
     * @return 
     */
    public void deleteUser(DeleteUserReq req);
    
    /**
     * 查询用户
     * @param req
     * @param currentOrg 是否获取当前机构项下的用户所属机构
     * @return
     */
    public UserQueryRsp getUsers(UserQueryReq req, boolean currentOrg);
    
    /**
     * 通过logonId获取userId
     * @param loginId
     * @return
     */
    public String getUserId(String logonId);
    
    /**
     * 查询用户密码状态
     * @param req
     * @return
     */
    public UserPasswdQueryRsp getUserPasswd(String userId);
    
    /**
     * 记录用户登录成功
     * @param req
     */
    public void setLogonSuccess(String userId, String logonTime);
    /**
     * 记录用户登录失败
     * @param req
     * @return 是否更改了权限
     */
    public boolean setLogonFailure(String userId, String status);
    
    /**
     * 记录用户退出成功
     * @param req
     */
    public void setLogoutSuccess(String userId, String logoutTime);
    
    /**
     * 用户角色管理
     * @param header 
     * @param req
     * @return 是否更改了权限
     */
    public boolean userRole(HttpHeaders header, UserRoleReq req);
   
    /**
     * 用户所属组管理
     * @param header 
     * @param req
     * @return 是否更改了权限
     */
    public boolean userGroup(HttpHeaders header, UserGroupReq req);
    
    /**
     * 查询用户可操作菜单
     * @param userId
     * @param orgId
     * @param userService
     * @param roleService
     * @param groupService
     * @param menuService
     * @return
     */
    public UserMenuRsp getUserMenus(String userId, String orgId, UserService userService, RoleService roleService, GroupService groupService, MenuService menuService);
    
    /**
     * 检查用户权限
     * @param url
     * @param userId
     * @param orgId
     * @param userService
     * @param roleService
     * @param groupService
     * @param menuService
     * @return
     */
    public boolean checkUserAuth(String url, String userId, String orgId, UserService userService, RoleService roleService, GroupService groupService, MenuService menuService);

    /**
     * 用户密码重置
     * @param req
     */
    public void userResetPasswd(UserResetPasswdReq req);
    
    /**
     * 批量重置密码
     * @param userService
     * @param req
     */
    public void multiUserResetPasswd(UserService userService, MultiUserResetPasswdReq req);
    
    /**
     * 设置用户密码
     * @param req
     */
    public void userSetPasswd(UserSetPasswdReq req);
    
    /**
     * 用户请假
     * @param req
     */
    public void userLeave(UserLeaveReq req);
    
    /**
     * 用户放弃请假
     * @param req
     */
    public void userCancelLeave(UserCancelLeaveReq req);
    
    /**
     * 查询用户请假记录
     * @param req
     * @return
     */
    public UserLeaveQueryRsp getUserLeaves(UserLeaveQueryReq req);
    
    /**
     * 用户修改请假信息
     * @param changeUserLeaveReq
     */
    public void userChangeLeave(ChangeUserLeaveReq changeUserLeaveReq);
    
    /**
     * 用户转移授权
     * @param req
     */
    public void userSubst(UserSubstReq req);
    
    /**
     * 用户放弃转移授权
     * @param req
     */
    public void userCancelSubst(UserCancelSubstReq req);
    
    /**
     * 查询用户转移授权
     * @param req
     * @return
     */
    public UserSubstQueryRsp getUserSubsts(UserSubstQueryReq req);
    
    /**
     * 用户修改转授权信息
     * @param message
     */
    public void changeUserSubst(UserSubstReq message);
    
    /**
     * 切换用户状态
     * @param req
     * @return
     */
    public void toggleUserStatus(ToggleUserStatusReq message);
    
    /**
     * 批量更新用户状态
     * @param userService
     * @param req
     */
    public void multiToggleUserStatus(UserService userService, MultiToggleUserStatusReq req);
    /**
     * 检查文件大小
     * @param size
     * @return
     */
    public boolean checkFileSize(long size, String fileSize);

    /**
     * 设置默认机构
     * @param message
     */
    public void setDefaultOrg(SetDefaultOrgReq message);


    /**
     * 获取用户头像
     * @param request
     * @param response
     */
    public void getHeadPortrait(HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新用户头像
     * @param request
     * @param response
     * @return
     */
    public ResponseMessage<Object> updateHeadportrait(HttpServletRequest request,
            HttpServletResponse response);
    
    /**
     * 获取用户所属机构信息
     * @param req
     * @return
     */
    public UserBelongQueryRsp getUserBelong(UserBelongQueryReq req);
    
    /**
     * 按机构和角色查询用户
     * @param req
     * @return
     */
    public UserQueryByOrgRsp userQueryByOrg(UserQueryByOrgReq req);
    
    /**
     * 查询指定角色和机构下级机构用户
     * @param req
     * @return
     */
    public UserQueryByParentOrgRsp userQueryByParentOrg(UserQueryByParentOrgReq req);
}