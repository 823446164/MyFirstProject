package com.amarsoft.app.ems.system.service;

import java.util.Map;

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
import com.amarsoft.app.ems.system.cs.dto.orguserquery.DeptManagerUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.DeptManagerUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgManagerQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgManagerQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto.DeleteInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDtoSaveReq;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptinfodto.SecondLevelDeptInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptinfodto.SecondLevelDeptInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDtoQueryRsp;

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
     * 修改机构的状态信息
     * @param request
     * @return
     */
    Map<String, String> setOrgInfo(OrgInfoUpdateReq request);

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
     * 查询部门或者团队的用户信息
     * @param request
     * @param userService
     * @return
     */
    OrgUserQueryRsp orgUserQuery(OrgUserQueryReq request);
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
    /**
     * Description: 查询一级机构信息
     * @param request
     * @return
     * @see
     */
    OneLevelDeptDtoQueryRsp oneLevelDeptDtoQuery(OneLevelDeptDtoQueryReq message);
    
    /**
     * 
     * Description: 新增一级机构的信息
     *
     * @param request
     * @return Map
     * @see
     */
    Map<String, String> oneLevelDeptDtoSave(OneLevelDeptDtoSaveReq request);
    
    /**
     * Description: 搜索部门
     * @param request
     * @return SearchSecondLevelDeptListDtoQueryRsp
     * @see
     */
    SearchSecondLevelDeptListDtoQueryRsp searchSecondLevelDeptListDtoQuery(SearchSecondLevelDeptListDtoQueryReq request);

    /**
     * Description: 删除机构的信息
     * @param request
     * @return
     * @see
     */
    Map<String, String> deleteInfoDtoQuery(DeleteInfoDtoQueryReq request);

    /**
     * Description: 查询二级部门信息
     * @param req
     * @return
     * @see
     */
    SecondLevelDeptInfoDtoQueryRsp secondLevelDeptInfoDtoQuery(SecondLevelDeptInfoDtoQueryReq req);

    /**
     * Description: 查询二级部门List列表
     * @param req
     * @return
     * @see
     */
    SecondLevelDeptListDtoQueryRsp secondLevelDeptListDtoQuery(SecondLevelDeptListDtoQueryReq req);

    /**
     * 查询机构树图
     * @param bomanger
     * @return OrgTreeQueryRsp
     */
    OrgTreeQueryRsp oneSecondOrgTreeQuery(OrgTreeQueryReq req);

    /**
     * 员工详情List查询
     * @param request
     * @return
     */
    EmployeeInfoListDtoQueryRsp employeeInfoListDtoQuery(EmployeeInfoListDtoQueryReq request);

    /**
     * 查询所有部门经理的userId
     * @param 
     * @return OrgUserQueryRsp
     */
    DeptManagerUserQueryRsp getDeptManagerAll(DeptManagerUserQueryReq request);

    /**
     * 根据员工id查询所属部门
     * @param 
     * @return OrgManagerQueryRsp
     */
    OrgManagerQueryRsp orgManagerQuery(OrgManagerQueryReq message);

}
