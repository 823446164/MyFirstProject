package com.amarsoft.app.ems.system.service;

import com.amarsoft.app.ems.system.cs.dto.addgroup.AddGroupReq;
import com.amarsoft.app.ems.system.cs.dto.deletegroup.DeleteGroupReq;
import com.amarsoft.app.ems.system.cs.dto.groupallquery.GroupAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.groupquery.GroupQueryReq;
import com.amarsoft.app.ems.system.cs.dto.groupquery.GroupQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.grouprole.GroupRoleReq;
import com.amarsoft.app.ems.system.cs.dto.groupuserquery.GroupUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.groupuserquery.GroupUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.levelgroupquery.LevelGroupQueryReq;
import com.amarsoft.app.ems.system.cs.dto.levelgroupquery.LevelGroupQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.updategroup.UpdateGroupReq;

/**
 * 角色组服务处理接口
 * @author xjzhao
 */
public interface GroupService {
    /**
     * 新增角色组
     * @param req
     */
    public void addGroup(AddGroupReq req);
    
    /**
     * 修改角色组
     * @param req
     * @return 是否更改了权限
     */
    public boolean updateGroup(UpdateGroupReq req);
    
    /**
     * 删除角色组
     * @param req
     */
    public void deleteGroup(DeleteGroupReq req);
    
    /**
     * 查询角色组
     * @param req
     * @return
     */
    public GroupQueryRsp getGroups(GroupQueryReq req);
    
    /**
     * 角色组角色管理
     * @param req
     * @return 是否更改了权限
     */
    public boolean groupRole(GroupRoleReq req);
    /**
     * 查询所有角色组
     * @param null
     * @return List(角色组)
     */
    public GroupAllQueryRsp groupAllQuery();
    /**
     * 查询所有角色组关联用户
     * @param null
     * @return List(角色组)
     */
    public GroupUserQueryRsp groupUserQuery(GroupUserQueryReq message);

    /**
     * 按法人机构查询不同机构级别的角色组 
     * @param levelGroupQueryReq 
     * @param orgService 
     * @param groupService 
     * @param null
     * @return List(角色组)
     */
    public LevelGroupQueryRsp levelGroupQuery(LevelGroupQueryReq levelGroupQueryReq, GroupService groupService, OrgService orgService);
}