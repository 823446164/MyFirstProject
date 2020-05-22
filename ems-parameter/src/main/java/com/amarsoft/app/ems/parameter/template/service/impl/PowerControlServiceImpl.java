/*
 * 文件名：PowerControl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：yrong
 * 修改时间：2020年5月22日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */


package com.amarsoft.app.ems.parameter.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amarsoft.aecd.system.constant.UserRoles;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.parameter.template.service.PowerControlService;
import com.amarsoft.app.ems.system.controller.RoleController;
import com.amarsoft.app.ems.system.cs.client.RoleClient;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserAndRole;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserRoleQueryReq;
import com.amarsoft.app.ems.system.cs.dto.userrolequery.UserRoleQueryRsp;


/**
 * 权限控制
 * 判断当前用户的权限是否可维护标签
 * @author yrong
 * @version 2020年5月22日
 * @see PowerControlServiceImpl
 * @since
 */

@Service
public class PowerControlServiceImpl implements PowerControlService {
    @Autowired 
    RoleController roleController;
   
    public boolean PowerToLabel() {
        boolean power = false; 
        //跨服务调用方法：按用户查找角色
        RequestMessage<UserRoleQueryReq> reqMsg =new RequestMessage<>();
        UserRoleQueryReq req = new UserRoleQueryReq();
        req.setUserId(GlobalShareContextHolder.getUserId());
        reqMsg.setMessage(req);
        ResponseEntity<ResponseMessage<UserRoleQueryRsp>> roles = roleController.userRoleQuery(reqMsg);
        List<UserAndRole> userRoles = roles.getBody().getMessage().getUserRoles();
        for (UserAndRole userAndRole : userRoles) {
            if(UserRoles.Admin.id.equals(userAndRole.getRoleId())) {
                power = true;
            }            
        }
        return power;       
    }
}
