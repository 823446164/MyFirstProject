package com.amarsoft.app.ems.system.service;

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
import com.amarsoft.app.ems.system.cs.dto.orgtreequery.OrgTreeQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryRsp;

/**
 * 机构服务的接口
 * @author xxu1
 */
public interface OrgService {
    
    /**
     * 获取所有机构的信息
     * @param request
     * @return 
     */
    OrgInfoAllQueryRsp getOrgAll(OrgInfoAllQueryReq request);
    
    /**
     * 修改机构的信息
     * @param request
     * @return
     */
    void setOrgInfo(OrgInfoUpdateReq request);

    /**
     * 新增机构的信息
     * @param request
     * @return
     */
    void addOrgInfo(OrgInfoAddReq request);
    

    /**
     * 删除机构的信息
     * @param request
     * @return
     */
    void deleteOrgInfo(OrgInfoDeleteReq request);
    /**
     * 按条件查询机构信息
     * 父机构编号：
     * 机构级别：
     * 是否只查询当前机构：
     * @param request
     * @return
     */
    ConditionalOrgsQueryRsp orgInfoQueryByCondition(ConditionalOrgsQueryReq request);
    /**
     * 查询机构信息
     * @param request
     * @return
     */
    OrgInfoQueryRsp getOrgInfo(OrgInfoQueryReq request);
    /**
     * 查询机构用户信息
     * @param request
     * @param userService
     * @return
     */
    OrgUserQueryRsp orgUserQuery(OrgUserQueryReq request,UserService userService);
    /**
     * 查询机构树图
     * @param bomanger
     * @return
     */
    OrgTreeQueryRsp orgTreeQuery();

    /**
     * 获取机构编号
     * @param bomanger
     * @return
     */
    OrgIdQueryRsp getOrgId(OrgIdQueryReq req);

    /**
     * 获取法人机构
     * @param req 
     * @return
     */
    CoperateOrganizationQueryRsp getCoperateOrganization(CoperateOrganizationQueryReq req);

    /**
     * 根据传入的机构获取其法人机构（rootOrgId）
     * @param orgId 普通机构编号
     * @return rootOrgId 法人机构编号
     */
    String getRootOrgId(String orgId);

    /**
     * 判断是否为父级机构
     * @param parentOrgId　目标父级机构
     * @param orgId　子机构
     * @return
     */
    boolean isParentOrg(String parentOrgId, String orgId);
}