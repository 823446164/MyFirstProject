package com.amarsoft.app.ems.system.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.aecd.system.constant.ArchitectureType;
import com.amarsoft.aecd.system.constant.ChangeEventType;
import com.amarsoft.aecd.system.constant.CompanyType;
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.aecd.system.constant.OrgStatus;
import com.amarsoft.aecd.system.constant.OrgType;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.aecd.system.constant.UserRoles;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.employee.template.cs.client.EmployeeInfoDtoClient;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDto;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoReq;
import com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno.EmployeeListByEmplNoRsp;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryReq;
import com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.ConditionalOrgsQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.coperateorganizationquery.CoperateOrg;
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
import com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.DeptManagerUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.DeptManagerUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.DeptUserInfo;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.Filter;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.UserTeamOrgInfo;
import com.amarsoft.app.ems.system.entity.ChangeEvent;
import com.amarsoft.app.ems.system.entity.Department;
import com.amarsoft.app.ems.system.entity.OrgInfo;
import com.amarsoft.app.ems.system.entity.OrgTeam;
import com.amarsoft.app.ems.system.entity.TeamInfo;
import com.amarsoft.app.ems.system.entity.UserBelong;
import com.amarsoft.app.ems.system.entity.UserInfo;
import com.amarsoft.app.ems.system.entity.UserTeam;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto.DeleteInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDto;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto.EmployeeInfoListDtoSearchReq;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.oneleveldeptdto.OneLevelDeptDtoSaveReq;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDto;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto.SearchSecondLevelDeptListDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptinfodto.SecondLevelDeptInfoDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptinfodto.SecondLevelDeptInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDto;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDtoQueryReq;
import com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto.SecondLevelDeptListDtoQueryRsp;

/**
 * 机构服务的接口实现类
 * @author hzhang23
 */
@Service
@RefreshScope
public class OrgServiceImpl implements OrgService {

    @Autowired
    EmployeeInfoDtoClient employeeInfoDtoClient;
    
    @Value("${global.business.org.default-length}")
    private int orgDefaultLength;
    
    public final static String ORG_PRULELENGTH = "4"; 
    private final static String ROOT_ORG_PARENTORGID = "root"; 
    

    @Override
    public OrgInfoQueryRsp getOrgInfo(OrgInfoQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, req.getOrgId());
        OrgInfoQueryRsp response = new OrgInfoQueryRsp();

        if(orgInfo == null) return response;
        response.setOrgId(orgInfo.getOrgId());
        response.setSortNo(orgInfo.getSortNo());
        response.setOrgName(orgInfo.getOrgName());
        response.setOrgLevel(orgInfo.getOrgLevel());
        response.setOrgType(orgInfo.getOrgType());
        response.setParentOrgId(orgInfo.getParentOrgId());// 上级机构编号
        OrgInfo parentOrg = bomanager.keyLoadBusinessObject(OrgInfo.class, orgInfo.getParentOrgId());
        if (parentOrg == null) {
            response.setParentOrgName("");
        } else {
            response.setParentOrgName(parentOrg.getOrgName());
        }
        String rootOrgId = orgInfo.getRootOrgId();// 法人机构编号
        OrgInfo rootOrg = bomanager.keyLoadBusinessObject(OrgInfo.class, rootOrgId);
        if (rootOrg != null) {
            response.setBelongRootOrgName(rootOrg.getOrgName());
        }
        response.setBelongRootOrg(rootOrgId);
        response.setBranchOrgId(orgInfo.getBranchOrgId());
        response.setCountry(orgInfo.getCountry());
        response.setBankId(orgInfo.getBankId());
        response.setBelongArea(orgInfo.getBelongArea());
        response.setCoreOrgId(orgInfo.getCoreOrgId());
        response.setOrgAddress(orgInfo.getOrgAddress());
        response.setStatus(orgInfo.getStatus());
        return response;
    }

    @Override
    public OrgInfoAllQueryRsp getOrgAll(OrgInfoAllQueryReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgInfoAllQueryRsp response = new OrgInfoAllQueryRsp();
        response.setOrgInfos(new ArrayList<com.amarsoft.app.ems.system.cs.dto.orginfoallquery.OrgInfo>());

        String status = request.getStatus();
        if (StringUtils.isEmpty(status)) {
            status = SystemStatus.Normal.id;
        }
        List<OrgInfo> orgInfoList = null;
        if (request.getAllFlag().equals(YesNo.Yes.id)) {
            orgInfoList = bomanager.loadBusinessObjects(OrgInfo.class, 0, Integer.MAX_VALUE, "1 = 1 order by sortNo")
                    .getBusinessObjects();
        } else {
            orgInfoList = bomanager.loadBusinessObjects(OrgInfo.class, 0, Integer.MAX_VALUE,
                    "status=:status order by sortNo", "status", status).getBusinessObjects();
        }

        if (!CollectionUtils.isEmpty(orgInfoList)) {
            for (OrgInfo orgInfo : orgInfoList) {
                com.amarsoft.app.ems.system.cs.dto.orginfoallquery.OrgInfo orgInfoResponse = new com.amarsoft.app.ems.system.cs.dto.orginfoallquery.OrgInfo();
                orgInfoResponse.setOrgId(orgInfo.getOrgId());
                orgInfoResponse.setSortNo(orgInfo.getSortNo());
                orgInfoResponse.setOrgName(orgInfo.getOrgName());
                orgInfoResponse.setOrgLevel(orgInfo.getOrgLevel());
                orgInfoResponse.setOrgType(orgInfo.getOrgType());
                orgInfoResponse.setParentOrgId(orgInfo.getParentOrgId());
                orgInfoResponse.setBranchOrgId(orgInfo.getBranchOrgId());
                orgInfoResponse.setRootOrgId(orgInfo.getRootOrgId());
                orgInfoResponse.setCountry(orgInfo.getCountry());
                orgInfoResponse.setBankId(orgInfo.getBankId());
                orgInfoResponse.setBelongArea(orgInfo.getBelongArea());
                orgInfoResponse.setCoreOrgId(orgInfo.getCoreOrgId());
                orgInfoResponse.setOrgAddress(orgInfo.getOrgAddress());
                orgInfoResponse.setStatus(orgInfo.getStatus());
                response.getOrgInfos().add(orgInfoResponse);
            }
        }
        return response;
    }

    /**
     * 更新部门机构的状态
     * @param req
     * @param Map
     */
    @Override
    public Map<String, String> setOrgInfo(OrgInfoUpdateReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        String orgId = req.getOrgId();
        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, orgId);
        if (orgInfo == null) {
            throw new ALSException("EMS6012");
        }

        //判断完成　还是　停用　　1:完成;2:停用;
        if(OrgStatus.Completed.id.equals(req.getChangeId())) {//完成功能
            if(OrgStatus.Disabled.id.equals(orgInfo.getStatus()) || OrgStatus.New.id.equals(orgInfo.getStatus())) {
                orgInfo.setStatus(OrgStatus.Completed.id);
            }else {
                throw new ALSException("EMS6007");
            }
        }else if(OrgStatus.Disabled.id.equals(req.getChangeId()))  {//停用功能
            if(!OrgStatus.Completed.id.equals(orgInfo.getStatus())){
                throw new ALSException("EMS6001");
                }
            Department department = bomanager.keyLoadBusinessObject(Department.class, orgId);
            if(department == null){ //部门附属表不存在
                throw new ALSException("900201");
            }else if(!StringUtils.isEmpty(department.getDeptManager())) {//未获取到部门经理
                throw new ALSException("EMS6002");              
            }
            List<UserBelong> ubs = bomanager.loadBusinessObjects(UserBelong.class, "orgId like :orgId", "orgId",orgId+"%");
            if(!CollectionUtils.isEmpty(ubs)) {//部门下存在员工
                throw new ALSException("EMS6006");
            }
            orgInfo.setStatus(OrgStatus.Disabled.id);
        }else {
            throw new ALSException("EMS6013",req.getChangeId());
        }

        // 更新到数据库
        bomanager.updateBusinessObject(orgInfo);
        bomanager.updateDB();
        Map<String, String> map=new HashMap<String, String>();
        map.put("status","Y");
        return map;
    }

    /**
     * 更新下级机构的状态
     * 
     * @param bomanager
     * @param parentOrgId
     */
    private void updateChildrenOrgStatus(BusinessObjectManager bomanager, String parentOrgId, String status) {
        List<OrgInfo> childrenOrgs = bomanager.loadBusinessObjects(OrgInfo.class, 0, Integer.MAX_VALUE,
                "parentOrgId=:parentOrgId", "parentOrgId", parentOrgId).getBusinessObjects();
        childrenOrgs.stream().forEach(childrenOrg -> {
            childrenOrg.setStatus(SystemStatus.Locked.id);
            bomanager.updateBusinessObject(childrenOrg);
            updateChildrenOrgStatus(bomanager, childrenOrg.getOrgId(), status);
        });
    }

    /**
     * 新增机构的信息
     * 
     * @param req
     */
    @Override
    public void addOrgInfo(OrgInfoAddReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();

        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, req.getOrgId());
        if (orgInfo != null)
            throw new ALSException("900204");
        orgInfo = new OrgInfo();
        orgInfo.setOrgId(req.getOrgId());
        orgInfo.setSortNo(req.getOrgId());
        orgInfo.setOrgName(req.getOrgName());
        orgInfo.setOrgLevel(req.getOrgLevel());
        orgInfo.setOrgType(req.getOrgType());
        if (req.getOrgLevel().equals(OrgLevel.LEVEL_1.id)) {
            orgInfo.setParentOrgId(ROOT_ORG_PARENTORGID);
            orgInfo.setRootOrgId(orgInfo.getOrgId());// 设置法人机构编号为自己
        } else {
            orgInfo.setParentOrgId(req.getParentOrgId());
            // 查找法人机构
            orgInfo.setRootOrgId(getRootOrgId(req.getParentOrgId()));
        }
        if (orgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_3.id) >= 0) { //二级分行以下设置所属分行字段
            orgInfo.setBranchOrgId(getBranchOrgId(req.getParentOrgId()));
        }
        orgInfo.setCountry(req.getCountry());
        orgInfo.setBankId(req.getBankId());
        orgInfo.setBelongArea(req.getBelongArea());
        orgInfo.setCoreOrgId(req.getCoreOrgId());
        orgInfo.setOrgAddress(req.getOrgAddress());
        orgInfo.setStatus(SystemStatus.Normal.id);
        // 更新到数据库
        bomanager.updateBusinessObject(orgInfo);
        bomanager.updateDB();
    }

    @Override
    /**
     * 获取当前机构的法人机构编号
     */
    public String getRootOrgId(String orgId) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, orgId);
        if (orgInfo == null) {// 上级机构不存在
            throw new ALSException("900207", orgId);
        }

        if (ROOT_ORG_PARENTORGID.equals(orgInfo.getParentOrgId())) {// 法人机构
            return orgInfo.getOrgId();
        } else {
            return getRootOrgId(orgInfo.getParentOrgId());
        }
    }
    
    /**
     * 获取当前机构的所属分行编号
     * @param orgId
     * @return
     */
    public String getBranchOrgId(String orgId) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, orgId);
        if (orgInfo == null) {// 上级机构不存在
            throw new ALSException("900207", orgId);
        }

        if (OrgLevel.LEVEL_1.id.equals(orgInfo.getOrgLevel())) {//法人机构
            return null;
        } else if (OrgLevel.LEVEL_2.id.equals(orgInfo.getOrgLevel())) {//一级分行
            return orgInfo.getOrgId();
        } else {
            return getBranchOrgId(orgInfo.getParentOrgId());
        }
    }

    @Override
    public boolean isParentOrg(String parentOrgId, String orgId) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, orgId);
        if (orgInfo == null) {// 上级机构不存在
            return false;
        }

        if (parentOrgId.equals(orgInfo.getParentOrgId())) {// 上级机构与目标传入机构相同
            return true;
        } else {
            return isParentOrg(parentOrgId, orgInfo.getParentOrgId());
        }
    }

    /**
     * 删除机构的信息
     * 
     * @param req
     */
    @Override
    public void deleteOrgInfo(OrgInfoDeleteReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, req.getOrgId());

        if (orgInfo == null)
            throw new ALSException("900206");

        List<UserBelong> ubs = bomanager.loadBusinessObjects(UserBelong.class, "orgId = :orgId", "orgId",
                orgInfo.getOrgId());
        if (!CollectionUtils.isEmpty(ubs)) {
            throw new ALSException("900208");
        }

        List<OrgInfo> childrenOrgs = bomanager.loadBusinessObjects(OrgInfo.class, 0, Integer.MAX_VALUE,
                "parentOrgId=:parentOrgId", "parentOrgId", orgInfo.getOrgId()).getBusinessObjects();
        childrenOrgs.forEach(childrenOrgInfo -> {
            OrgInfoDeleteReq childrenReq = new OrgInfoDeleteReq();
            childrenReq.setOrgId(childrenOrgInfo.getOrgId());
            deleteOrgInfo(childrenReq);
        });

        bomanager.deleteBusinessObject(orgInfo);
        bomanager.updateDB();
    }

    @Override
    public ConditionalOrgsQueryRsp orgInfoQueryByCondition(ConditionalOrgsQueryReq reqMsg) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        ConditionalOrgsQueryRsp rsp = new ConditionalOrgsQueryRsp();
        rsp.setOrgInfos(new ArrayList<com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo>());

        List<OrgInfo> orgInfoList = new ArrayList<>();
        if (StringUtils.isEmpty(reqMsg.getOrgLevel()) && StringUtils.isEmpty(reqMsg.getParentOrgId())) {// 如果没有传入机构级别和父机构，就直接查询法人机构
            orgInfoList = bomanager.loadBusinessObjects(OrgInfo.class, 0, Integer.MAX_VALUE,
                    "orgLevel = :orgLevel and parentOrgId = :parentOrgId order by sortNo", "orgLevel",
                    OrgLevel.LEVEL_1.id, "parentOrgId", ROOT_ORG_PARENTORGID).getBusinessObjects();
        } else if (StringUtils.isEmpty(reqMsg.getOrgLevel()) && !StringUtils.isEmpty(reqMsg.getParentOrgId())) {// 查找指定机构编号的所有下级子机构（不包含其本身）
            orgInfoList = this.queryChildrenOrg(bomanager, reqMsg.getParentOrgId());
        } else {// 查找指定机构的某一级子机构（不包含其本身）
            if (StringUtils.isEmpty(reqMsg.getOrgType())) {
                orgInfoList = this.queryChildrenOrgByOrgLevel(bomanager, reqMsg.getParentOrgId(), reqMsg.getOrgLevel());
                if (reqMsg.getEntireFlag().equals(YesNo.Yes.id)) {// 再获取指定机构级别项下的机构信息
                    List<OrgInfo> orgs = new ArrayList<>();
                    orgInfoList.forEach(orgInfo -> {
                        orgs.addAll(this.queryChildrenOrg(bomanager, orgInfo.getOrgId()));
                    });
                    orgInfoList.addAll(orgs);
                }
            } else {
                orgInfoList = this.queryChildrenOrgByOrgLevelAndOrgType(bomanager, reqMsg.getParentOrgId(),
                        reqMsg.getOrgLevel(), reqMsg.getOrgType());
                if (reqMsg.getEntireFlag().equals(YesNo.Yes.id)) {// 再获取指定机构级别项下的机构信息
                    List<OrgInfo> orgs = new ArrayList<>();
                    orgInfoList.forEach(orgInfo -> {
                        orgs.addAll(this.queryChildrenOrg(bomanager, orgInfo.getOrgId()));
                    });
                    orgInfoList.addAll(orgs);
                }
            }
        }
        rsp.setTotalCount(CollectionUtils.isEmpty(orgInfoList) ? 0 : orgInfoList.size());
        Stream<OrgInfo> orgStream = null;
        if (StringUtils.isEmpty(reqMsg.getBegin()) || StringUtils.isEmpty(reqMsg.getPageSize())) {
            orgStream = orgInfoList.stream();
        } else {
            orgStream = orgInfoList.stream().skip(reqMsg.getBegin()).limit(reqMsg.getPageSize());
        }
        orgStream.forEach(orgInfo -> {
            com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo info = new com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo();
            info.setOrgId(orgInfo.getOrgId());
            info.setSortNo(orgInfo.getSortNo());
            info.setOrgName(orgInfo.getOrgName());
            info.setOrgLevel(orgInfo.getOrgLevel());
            info.setOrgType(orgInfo.getOrgType());
            info.setParentOrgId(orgInfo.getParentOrgId());
            info.setBranchOrgId(orgInfo.getBranchOrgId());
            info.setCountry(orgInfo.getCountry());
            info.setBankId(orgInfo.getBankId());
            info.setBelongArea(orgInfo.getBelongArea());
            info.setCoreOrgId(orgInfo.getCoreOrgId());
            info.setOrgAddress(orgInfo.getOrgAddress());
            info.setRootOrg(orgInfo.getRootOrgId());
            info.setStatus(orgInfo.getStatus());
            if (orgInfo.getOrgLevel().equals(OrgLevel.LEVEL_5.id)) {// 是否为子节点
                info.setIsLeaf(Boolean.TRUE);
            } else {
                List<OrgInfo> orgs = bomanager.loadBusinessObjects(OrgInfo.class, 0, Integer.MAX_VALUE,
                        "parentOrgId = :parentOrgId", "parentOrgId", orgInfo.getOrgId()).getBusinessObjects();
                if (CollectionUtils.isEmpty(orgs)) {
                    info.setIsLeaf(Boolean.TRUE);
                }
            }
            // 展示子节点个数
            List<OrgInfo> list = bomanager.loadBusinessObjects(OrgInfo.class, 0, Integer.MAX_VALUE,
                    "parentOrgId = :parentOrgId", "parentOrgId", orgInfo.getOrgId()).getBusinessObjects();
            info.setChildNum(list.size());
            rsp.getOrgInfos().add(info);
        });
        return rsp;
    }

    /**
     * 查询指定父机构和OrgLevel的机构信息列表，不含其本身
     * @param bomanager   数据实体管理器
     * @param parentOrgId 父机构编号
     * @param orgLevel    机构级别
     * @return orgInfos 查询机构结果
     */
    public List<OrgInfo> queryChildrenOrgByOrgLevel(BusinessObjectManager bomanager, String parentOrgId,
            String orgLevel) {
        List<OrgInfo> orgInfos = new ArrayList<>();
        List<OrgInfo> childrenOrgInfos = bomanager.loadBusinessObjects(OrgInfo.class, 0, Integer.MAX_VALUE,
                "parentOrgId = :parentOrgId order by sortNo", "parentOrgId", parentOrgId).getBusinessObjects();
        childrenOrgInfos.forEach(childrenOrgInfo -> {
            if (childrenOrgInfo.getOrgLevel().equals(orgLevel)) {
                orgInfos.add(childrenOrgInfo);
            }
            orgInfos.addAll(queryChildrenOrgByOrgLevel(bomanager, childrenOrgInfo.getOrgId(), orgLevel));
        });

        return orgInfos;
    }

    /**
     * 查询指定父机构和OrgLevel的机构信息列表，不含其本身
     * 
     * @param bomanager   数据实体管理器
     * @param parentOrgId 父机构编号
     * @param orgLevel    机构级别
     * @param orgType     机构类型
     * @return orgInfos 查询机构结果
     */
    public List<OrgInfo> queryChildrenOrgByOrgLevelAndOrgType(BusinessObjectManager bomanager, String parentOrgId,
            String orgLevel, String orgType) {
        List<OrgInfo> orgInfos = new ArrayList<>();
        List<OrgInfo> childrenOrgInfos = bomanager.loadBusinessObjects(OrgInfo.class, 0, Integer.MAX_VALUE,
                "parentOrgId = :parentOrgId and orgType = :orgType order by sortNo", "parentOrgId", parentOrgId,
                "orgType", orgType).getBusinessObjects();
        childrenOrgInfos.forEach(childrenOrgInfo -> {
            if (childrenOrgInfo.getOrgLevel().equals(orgLevel) && childrenOrgInfo.getOrgType().equals(orgType) && childrenOrgInfo.getParentOrgId().equals(parentOrgId)) {
                orgInfos.add(childrenOrgInfo);
            }
            if (!childrenOrgInfo.getOrgLevel().equals(orgLevel)) {
                return;
            }
            orgInfos.addAll(queryChildrenOrgByOrgLevelAndOrgType(bomanager, childrenOrgInfo.getOrgId(), orgLevel, orgType));
        });

        return orgInfos;
    }

    /**
     * 查询指定父机构的项下的机构信息列表
     * 
     * @param bomanager   数据实体管理器
     * @param parentOrgId 父机构编号
     * @return orgInfos 查询机构结果
     */
    public List<OrgInfo> queryChildrenOrg(BusinessObjectManager bomanager, String parentOrgId) {
        List<OrgInfo> orgInfos = new ArrayList<>();
        List<OrgInfo> childrenOrgInfos = bomanager.loadBusinessObjects(OrgInfo.class, 0, Integer.MAX_VALUE,
                "parentOrgId = :parentOrgId order by sortNo", "parentOrgId", parentOrgId).getBusinessObjects();
        childrenOrgInfos.forEach(childrenOrgInfo -> {
            orgInfos.add(childrenOrgInfo);
            orgInfos.addAll(queryChildrenOrg(bomanager, childrenOrgInfo.getOrgId()));
        });
        return orgInfos;
    }

    /**
     * Description:　查询部门或者团队下员工Id List
     * @param req
     * @return  rsp
     * @see
     */
    @Override
    public OrgUserQueryRsp orgUserQuery(OrgUserQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgUserQueryRsp rsp = new OrgUserQueryRsp();
        List<UserTeamOrgInfo> userTeamOrgInfos = new ArrayList<UserTeamOrgInfo>();//新建返回list
        if (ArchitectureType.Department.id.equals(req.getRoleId())) {//部门
            String orgId = req.getId();//orgId
            List<BusinessObject> businessObjects = bomanager.selectBusinessObjectsBySql(
                "select UB.userId as userId,UB.orgId as orgId,OI.orgName as orgName,TI.teamId as teamId,"
                + "TI.teamName as teamName from UserBelong UB,UserTeam UT,OrgInfo OI,TeamInfo TI where "
                + "UB.userId = UT.userId and UB.orgId = :orgId and UT.teamId = TI.teamId and OI.orgId = UB.orgId","orgId",orgId
                ).getBusinessObjects();
            if (CollectionUtils.isEmpty(businessObjects)) {
                throw new ALSException("EMS6014");
            }
            UserTeamOrgInfo userTeamOrgInfo = null;
            for (BusinessObject businessObject : businessObjects) {
                userTeamOrgInfo =new UserTeamOrgInfo();
                userTeamOrgInfo.setOrgId(businessObject.getString("orgId"));
                userTeamOrgInfo.setOrgName(businessObject.getString("orgName"));
                userTeamOrgInfo.setTeamId(businessObject.getString("teamId"));
                userTeamOrgInfo.setTeamName(businessObject.getString("teamName"));
                userTeamOrgInfo.setUserId(businessObject.getString("userId"));
                userTeamOrgInfos.add(userTeamOrgInfo);
            }
        }else if (ArchitectureType.Team.id.equals(req.getRoleId())) {//团队
            String userId = req.getId();//部门负责人的userId
            //查询对应的团队所有员工的团队，部门信息（唯一）
            UserTeam userTeam = bomanager.loadBusinessObject(UserTeam.class,"userId", userId);//根据部门负责人查询团队
            String teamId = userTeam.getTeamId();
            OrgTeam orgTeam = bomanager.loadBusinessObject(OrgTeam.class,"teamId", teamId);//查询所属部门
            TeamInfo teamInfo = bomanager.keyLoadBusinessObject(TeamInfo.class, teamId);//查询团队名称
            OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, orgTeam.getOrgId());//查询部门名称
            List<UserTeam> userTeams = bomanager.loadBusinessObjects(UserTeam.class,"teamId= :teamId","teamId",teamId);
            UserTeamOrgInfo userTeamOrgInfo = null;
            for (UserTeam ut : userTeams) {
                userTeamOrgInfo =new UserTeamOrgInfo();
                userTeamOrgInfo.setOrgId(orgTeam.getOrgId());
                userTeamOrgInfo.setOrgName(orgInfo.getOrgName());
                userTeamOrgInfo.setTeamId(teamId);
                userTeamOrgInfo.setTeamName(teamInfo.getTeamName());
                userTeamOrgInfo.setUserId(ut.getUserId());
                userTeamOrgInfos.add(userTeamOrgInfo);
            }
        }else if (UserRoles.Admin.id.equals(req.getRoleId())) {//查询所有员工
            List<BusinessObject> businessObjects = bomanager.selectBusinessObjectsBySql(
                "select UB.userId as userId,UB.orgId as orgId,OI.orgName as orgName,TI.teamId as teamId,"
                + "TI.teamName as teamName from UserBelong UB,UserTeam UT,OrgInfo OI,TeamInfo TI where "
                + "UB.userId = UT.userId and UT.teamId = TI.teamId and OI.orgId = UB.orgId"
                ).getBusinessObjects();
            if (CollectionUtils.isEmpty(businessObjects)) {
                throw new ALSException("EMS6014");
            }
            UserTeamOrgInfo userTeamOrgInfo = null;
            for (BusinessObject businessObject : businessObjects) {
                userTeamOrgInfo =new UserTeamOrgInfo();
                userTeamOrgInfo.setOrgId(businessObject.getString("orgId"));
                userTeamOrgInfo.setOrgName(businessObject.getString("orgName"));
                userTeamOrgInfo.setTeamId(businessObject.getString("teamId"));
                userTeamOrgInfo.setTeamName(businessObject.getString("teamName"));
                userTeamOrgInfo.setUserId(businessObject.getString("userId"));
                userTeamOrgInfos.add(userTeamOrgInfo);
            }
        }
        rsp.setUserTeamOrgInfos(userTeamOrgInfos);
        return rsp;
    }

    @Override
    public OrgTreeQueryRsp orgTreeQuery() {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgTreeQueryRsp rsp = new OrgTreeQueryRsp();
        rsp.setTrees(new ArrayList<com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree>());
        List<OrgInfo> allOrgs = new ArrayList<>();
        if (StringUtils.isEmpty(GlobalShareContextHolder.getOrgId())) {
            allOrgs = bomanager.loadBusinessObjects(OrgInfo.class, 0, Integer.MAX_VALUE, "1 = 1 order by sortNo,orgType desc")
                    .getBusinessObjects();
        } else {// 取当前用户机构项下的机构信息
            OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, GlobalShareContextHolder.getOrgId());
            allOrgs.add(orgInfo);
            if (orgInfo.getOrgType().contentEquals(OrgType.Department.id)) {
                allOrgs.addAll(this.queryChildrenOrg(bomanager, orgInfo.getParentOrgId()));
            } else {
                allOrgs.addAll(this.queryChildrenOrg(bomanager, orgInfo.getOrgId()));
            }
        }

        for (OrgInfo orgInfo : allOrgs) {
            if (GlobalShareContextHolder.getOrgId().equals(orgInfo.getOrgId())) {// 根据当前登陆作为跟节点生成树图
                com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree rootNode = new com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree();
                rootNode.setChildren(new ArrayList<com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree>());
                rootNode.setTitle(orgInfo.getOrgName());
                rootNode.setSortNo(orgInfo.getSortNo());
                rootNode.setOrgType(orgInfo.getOrgType());
                rootNode.setKey(orgInfo.getOrgId());
                rootNode.setExpanded(CollectionUtils.isEmpty(rsp.getTrees()));// 展开第一个根节点
                rootNode.setLeaf(Boolean.FALSE);
                if (orgInfo.getStatus().equals(SystemStatus.Normal.id)) {// 有效、停用展示，无效则不展示
                    rootNode.setDisable(Boolean.FALSE);
                } else if (orgInfo.getStatus().equals(SystemStatus.Locked.id)) {
                    rootNode.setDisable(Boolean.TRUE);
                }
                this.addChildren(rootNode, orgInfo, allOrgs);
                this.sortTreeNode(rootNode);
                if (rsp.getTrees().stream().anyMatch(tree -> tree.getKey().equals(rootNode.getKey()))) { // 去重
                    continue;
                }
                rsp.getTrees().add(rootNode);
            }
        }
        return rsp;
    }

    /**
     * 获取机构项下层级，并生成节点数图
     * 
     * @param rootNode
     * @param orgInfo
     * @param allOrgs
     */
    private void addChildren(Tree rootNode, OrgInfo orgInfo, List<OrgInfo> allOrgs) {
        for (OrgInfo org : allOrgs) {
            if (org.getParentOrgId().equals(orgInfo.getOrgId()) && !org.getParentOrgId().equals(org.getOrgId())) {
                com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree node = new com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree();
                node.setChildren(new ArrayList<com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree>());
                node.setTitle(org.getOrgName());
                node.setSortNo(org.getSortNo());
                node.setOrgType(org.getOrgType());
                node.setKey(org.getOrgId());
                node.setExpanded(CollectionUtils.isEmpty(rootNode.getChildren()));// 展开第一个根节点
                if (org.getStatus().equals(SystemStatus.Normal.id)) {// 前端Disable为null则不展示该数据，前端为true、false展示
                    node.setDisable(Boolean.FALSE);
                } else if (org.getStatus().equals(SystemStatus.Locked.id)) {
                    node.setDisable(Boolean.TRUE);
                }
                this.addChildren(node, org, allOrgs);
                this.sortTreeNode(node);
                rootNode.getChildren().add(node);
                node.setLeaf(CollectionUtils.isEmpty(node.getChildren()));
            }
        }
    }
    
    private void sortTreeNode(com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree node) {
        Collections.sort(node.getChildren(), new Comparator<com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree>() {

            @Override
            public int compare(Tree o1, Tree o2) {
                int levelDifference = o2.getOrgType().compareTo(o1.getOrgType());
                if (levelDifference == 0) {
                    return o1.getSortNo().compareTo(o2.getSortNo());
                } else {
                    return levelDifference;
                }
            }
        });
    }

    @Override
    public synchronized OrgIdQueryRsp getOrgId(OrgIdQueryReq req) {
        OrgIdQueryRsp rsp = new OrgIdQueryRsp();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> orgIdAggregate = bomanager.selectBusinessObjectsBySql(
                "select max(orgId) as orgId from OrgInfo where parentOrgId = :parentOrgId and orgLevel = :orgLevel",
                "parentOrgId", req.getParentOrgId(), "orgLevel", req.getOrgLevel());
        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, req.getParentOrgId());
        int parentOrgLevel = 0;
        if (orgInfo != null)
            parentOrgLevel = Integer.parseInt(orgInfo.getOrgLevel());
        String originOrgId = "";
        int levelDiffer = Math.abs(Integer.parseInt(req.getOrgLevel()) - parentOrgLevel);

        if (!CollectionUtils.isEmpty(orgIdAggregate.getBusinessObjects())
                && !StringUtils.isEmpty(orgIdAggregate.getBusinessObjects().get(0).getString("orgId"))) {
            originOrgId = orgIdAggregate.getBusinessObjects().get(0).getString("orgId");
            int subOrgId = Integer.parseInt(originOrgId.substring(originOrgId.length() - orgDefaultLength)) + 1;// 机构编号最后几位数值截取加一

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < orgDefaultLength; i++) {
                sb.append("0");
            }
            DecimalFormat decimalformat = new DecimalFormat(sb.toString());
            rsp.setOrgId(
                    originOrgId.substring(0, originOrgId.length() - orgDefaultLength) + decimalformat.format(subOrgId));
        } else {// 新增机构项下第一个下级机构
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < levelDiffer * orgDefaultLength - 1; i++) {
                sb.append("0");
            }
            String orgId = (ROOT_ORG_PARENTORGID.equals(req.getParentOrgId()) ? "" : req.getParentOrgId())
                    + sb.append("1").toString();
            rsp.setOrgId(orgId);
            return rsp;
        }
        return rsp;
    }

    @Override
    public CoperateOrganizationQueryRsp getCoperateOrganization(CoperateOrganizationQueryReq req) {
        CoperateOrganizationQueryRsp rsp = new CoperateOrganizationQueryRsp();
        rsp.setCoperateOrgs(new ArrayList<CoperateOrg>());
        List<com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo> rootOrgs = this
                .orgInfoQueryByCondition(new ConditionalOrgsQueryReq()).getOrgInfos();

        OrgInfoQueryReq orgReq = new OrgInfoQueryReq();// 查询登陆机构详情
        orgReq.setOrgId(GlobalShareContextHolder.getOrgId());
        OrgInfoQueryRsp loginOrgInfo = this.getOrgInfo(orgReq);
        boolean showAllFlag = loginOrgInfo.getOrgLevel().equals(OrgLevel.LEVEL_1.id) ;
        boolean isDepartmentFlag = loginOrgInfo.getOrgLevel().equals(OrgLevel.LEVEL_2.id) && loginOrgInfo.getOrgType().equals(OrgType.Department.id);

        rootOrgs.forEach(rootOrg -> {
            if (!StringUtils.isEmpty(GlobalShareContextHolder.getOrgId()) && !GlobalShareContextHolder.getOrgId().equals("root")) {
                if (!rootOrg.getOrgId().equals(this.getRootOrgId(GlobalShareContextHolder.getOrgId()))) {
                    return;
                }
            }

            CoperateOrg coperateOrg = new CoperateOrg();
            coperateOrg.setOrgId(rootOrg.getOrgId());
            coperateOrg.setOrgName(rootOrg.getOrgName());
            coperateOrg.setOrgType(rootOrg.getOrgType());

            if (coperateOrg.getOrgType().equals(OrgType.Company.id)) {
                if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_1.id) <= 0
                        || showAllFlag && (StringUtils.isEmpty(req.getOrgLevel()) ? true : req.getOrgLevel().equals(OrgLevel.LEVEL_1.id))) {// 根据传入机构级别查询或者根据登陆机构直接查询
                    coperateOrg.setHeadComponies(new ArrayList<com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo>());
                    coperateOrg.setFirstTotalCount(this.setComponyData(req, coperateOrg.getHeadComponies(), rootOrg, CompanyType.Head_Compony.id,
                            OrgLevel.LEVEL_1.id));
                }
                if (loginOrgInfo.getOrgLevel().compareTo(OrgLevel.LEVEL_2.id) <= 0
                        || showAllFlag && (StringUtils.isEmpty(req.getOrgLevel()) ? true
                                : req.getOrgLevel().equals(OrgLevel.LEVEL_2.id))) {// 根据传入机构级别查询或者根据登陆机构直接查询
                    coperateOrg.setComponies(new ArrayList<com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo>());
                    coperateOrg.setFirstTotalCount(this.setComponyData(req, coperateOrg.getComponies(), rootOrg, CompanyType.Branch_Compony.id,
                            OrgLevel.LEVEL_2.id));
                }
            } else {
                if (loginOrgInfo.getOrgType().equals(OrgType.Department.id)
                        || (StringUtils.isEmpty(req.getOrgType()) ? true
                                : req.getOrgType().equals(OrgType.Department.id))) {// 根据传入机构类型查询或者根据登陆机构直接查询
                    coperateOrg.setDepartments(new ArrayList<com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo>());
                    coperateOrg.setFirstTotalCount(this.setOrgData(req, coperateOrg.getDepartments(), rootOrg, OrgType.Department.id, showAllFlag));
                }
                if (loginOrgInfo.getOrgType().equals(OrgType.Organization.id)
                        || isDepartmentFlag || showAllFlag && (StringUtils.isEmpty(req.getOrgType()) ? true
                                : req.getOrgType().equals(OrgType.Organization.id))) {// 根据传入机构类型查询或者根据登陆机构直接查询
                    coperateOrg.setBranchOrgs(new ArrayList<com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo>());
                    coperateOrg.setSecondTotalCount(this.setOrgData(req, coperateOrg.getBranchOrgs(), rootOrg, OrgType.Organization.id, showAllFlag || isDepartmentFlag));
                }
            }
            rsp.getCoperateOrgs().add(coperateOrg);
        });

        return rsp;
    }

    /**
     * 设置法人机构的下级机构
     * 
     * @param list
     * @param rootOrg
     * @param orgType
     * @param showAllFlag
     */
    private int setOrgData(CoperateOrganizationQueryReq orgReq,
            List<com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo> list,
            com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo rootOrg, String orgType, boolean showAllFlag) {
        int[] totalCount = {0};
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();

        ConditionalOrgsQueryReq req = new ConditionalOrgsQueryReq();
        req.setEntireFlag(YesNo.No.id);
        req.setParentOrgId(rootOrg.getOrgId());
        req.setOrgLevel(OrgLevel.LEVEL_2.id);
        ConditionalOrgsQueryRsp rsp = this.orgInfoQueryByCondition(req);

        if (!StringUtils.isEmpty(GlobalShareContextHolder.getOrgId()) && !showAllFlag) {
            OrgInfo logonOrgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, GlobalShareContextHolder.getOrgId());
            boolean isLogonDepartment = logonOrgInfo.getOrgType().equals(OrgType.Department.id);
            List<OrgInfo> currentOrgs = new ArrayList<>();
            currentOrgs.add(logonOrgInfo);
            if (isLogonDepartment) {
                currentOrgs.addAll(this.queryChildrenOrg(bomanager, logonOrgInfo.getParentOrgId()));
            } else {
                currentOrgs.addAll(this.queryChildrenOrg(bomanager, logonOrgInfo.getOrgId()));
            }

            currentOrgs.stream().filter(org -> {
                if (!org.getOrgType().equals(orgType)) {
                    return false;
                }
                if (isLogonDepartment) {
                    if (org.getOrgType().equals(OrgType.Organization.id)) {//登陆为部室时添加所有机构
                        return true;
                    }else {
                        if (org.getOrgId().equals(logonOrgInfo.getOrgId())) {//登陆为部室时只添加本部室
                            return true;
                        }
                    }
                    return false;
                }else {
                    if (org.getOrgId().equals(logonOrgInfo.getOrgId())) {//登陆为部室时只添加本部室
                        return true;
                    }else {
                        return false;
                    }
                }
            }).distinct().skip(orgReq.getBegin())// stream分页
            .limit(orgReq.getPageSize()).map(o -> this.castToConditionalOrgInfo(o)).forEach(org -> list.add(org));
            totalCount[0] = (int)currentOrgs.stream().filter(org -> {
                if (!org.getOrgType().equals(orgType)) {
                    return false;
                }
                if (isLogonDepartment) {
                    if (org.getOrgType().equals(OrgType.Organization.id)) {//登陆为部室时添加所有机构
                        return true;
                    }else {
                        if (org.getOrgId().equals(logonOrgInfo.getOrgId())) {//登陆为部室时只添加本部室
                            return true;
                        }
                    }
                    return false;
                }else {
                    if (org.getOrgId().equals(logonOrgInfo.getOrgId())) {//登陆为部室时只添加本部室
                        return true;
                    }else {
                        return false;
                    }
                }
            }).distinct().count();
        } else {
            rsp.getOrgInfos().stream().filter(org -> org.getOrgType().equals(orgType)).skip(orgReq.getBegin())// stream分页
                    .limit(orgReq.getPageSize()).forEach(org ->  list.add(org));
            totalCount[0] = (int) rsp.getOrgInfos().stream().filter(org -> org.getOrgType().equals(orgType)).count();
        }
        return totalCount[0];
    }
    
    private com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo castToConditionalOrgInfo(OrgInfo orgInfo){
        com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo org = new com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo();
        org.setOrgId(orgInfo.getOrgId());
        org.setSortNo(orgInfo.getSortNo());
        org.setOrgName(orgInfo.getOrgName());
        org.setOrgLevel(orgInfo.getOrgLevel());
        org.setOrgType(orgInfo.getOrgType());
        org.setParentOrgId(orgInfo.getParentOrgId());
        org.setBranchOrgId(orgInfo.getBranchOrgId());
        org.setCountry(orgInfo.getCountry());
        org.setBankId(orgInfo.getBankId());
        org.setBelongArea(orgInfo.getBelongArea());
        org.setCoreOrgId(orgInfo.getCoreOrgId());
        org.setOrgAddress(orgInfo.getOrgAddress());
        org.setRootOrg(orgInfo.getRootOrgId());
        org.setStatus(orgInfo.getStatus());
        return org;
    }

    /**
     * 设置法人机构的指定级别的机构
     * 
     * @param req
     * @param list
     * @param rootOrg
     * @param orgType
     * @param level
     */
    private int setComponyData(CoperateOrganizationQueryReq orgReq,
            List<com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo> list,
            com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery.OrgInfo rootOrg, String orgType, String level) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        int[] totalCount = {0};
        
        ConditionalOrgsQueryReq req = new ConditionalOrgsQueryReq();
        req.setEntireFlag(YesNo.No.id);
        req.setParentOrgId(rootOrg.getOrgId());
        req.setOrgLevel(level);
        ConditionalOrgsQueryRsp rsp = this.orgInfoQueryByCondition(req);

        if (!StringUtils.isEmpty(GlobalShareContextHolder.getOrgId())) {
            OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, GlobalShareContextHolder.getOrgId());
            List<OrgInfo> currentOrgs = new ArrayList<>();
            currentOrgs.add(orgInfo);
            if (orgInfo.getOrgType().contentEquals(OrgType.Department.id)) {
                currentOrgs.addAll(this.queryChildrenOrg(bomanager, orgInfo.getParentOrgId()));
            } else {
                currentOrgs.addAll(this.queryChildrenOrg(bomanager, orgInfo.getOrgId()));
            }

            totalCount[0] = (int) rsp.getOrgInfos().stream().filter(org -> {
                boolean flag = org.getOrgType().equals(orgType) || org.getOrgType().equals(OrgType.Company.id);
                if (flag) {
                    for (OrgInfo currentOrg : currentOrgs) {
                        if (currentOrg.getOrgId().equals(org.getOrgId())) {
                            return true;
                        }
                    }
                    return false;
                }
                return flag;
            }).count();
            rsp.getOrgInfos().stream().filter(org -> {
                boolean flag = org.getOrgType().equals(orgType) || org.getOrgType().equals(OrgType.Company.id);
                if (flag) {
                    for (OrgInfo currentOrg : currentOrgs) {
                        if (currentOrg.getOrgId().equals(org.getOrgId())) {
                            return true;
                        }
                    }
                    return false;
                }
                return flag;
            }).distinct().skip(orgReq.getBegin())// stream分页
            .limit(orgReq.getPageSize()).forEach(org ->  list.add(org));
        } else {
            rsp.getOrgInfos().stream()
                    .filter(org -> org.getOrgType().equals(orgType) || org.getOrgType().equals(OrgType.Company.id))
                    .forEach(org ->  list.add(org));
            totalCount[0] = (int) rsp.getOrgInfos().stream()
                    .filter(org -> org.getOrgType().equals(orgType) || org.getOrgType().equals(OrgType.Company.id)).count();
        }
        return totalCount[0];
    }

    /**
     * Description: 查询一级部门
     * @param req
     * @return response
     * @see
     */
    @Override
    public OneLevelDeptDtoQueryRsp oneLevelDeptDtoQuery(OneLevelDeptDtoQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OneLevelDeptDtoQueryRsp rsp = new OneLevelDeptDtoQueryRsp();

        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, req.getOrgId());
        Department department = bomanager.keyLoadBusinessObject(Department.class, req.getOrgId());
        OrgInfo parentOrgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, orgInfo.getParentOrgId());
        if(orgInfo == null || department == null) {
            throw new ALSException("EMS6012");     
        }
        rsp.setOrgId(orgInfo.getOrgId());
        rsp.setParentOrgId(orgInfo.getParentOrgId());
        rsp.setOrgName(orgInfo.getOrgName());
        rsp.setDeptManagerId(department.getDeptManager());//设置部门经理编号
        UserInfo UI = bomanager.keyLoadBusinessObject(UserInfo.class, department.getDeptManager());
        rsp.setDeptManagerName(UI.getUserName());
        rsp.setParentOrgName(parentOrgInfo.getOrgName());
        //查询下一级部门list
        List<OrgInfo> ois = bomanager.loadBusinessObjects(OrgInfo.class, "parentOrgId = :parentOrgId", "parentOrgId",
            orgInfo.getOrgId());
        rsp.setSecondOrgNumber(String.valueOf(ois.size()));
        return rsp;
    }

    /**
     * Description: 新增、保存一级部门的信息
     * @param req
     * @return 
     * @see
     */
    @Override
    public Map<String, String> oneLevelDeptDtoSave(OneLevelDeptDtoSaveReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgInfo orgInfo = null;
        Department department = null;
        if (StringUtils.isEmpty(req.getOrgId())) {//新增
          //根据名称查询部门，判断是否有重名的部门名称
            OrgInfo OI = bomanager.loadBusinessObject(OrgInfo.class,"orgName",req.getOrgName());
            if(OI != null) {
                throw new ALSException("EMS6015",req.getOrgName());
            }
            List<BusinessObject> businessObjects = bomanager.selectBusinessObjectsBySql(
                "select orgId as orgId from OrgInfo where orgId like :orgId and orgId <> :orgId and "
                + "orgLevel = :orgLevel order by orgId desc","orgId",req.getParentOrgId()+"%","orgLevel",req.getOrgLevel()
                ).getBusinessObjects();
            OrgInfo oInfo = bomanager.keyLoadBusinessObject(OrgInfo.class,req.getParentOrgId());//查询父部门的部门等级
            String ruleLengthString = ORG_PRULELENGTH;//部门编号规则：限定部门编号一级长度为４
            int ruleLength = Integer.parseInt(ruleLengthString);//部门编号长度
            String orgId = null;
            if (!CollectionUtils.isEmpty(businessObjects)) {//当前部门级别存在子部门
                String temp = businessObjects.get(0).getString("orgId");//获取最大子部门编号
                //分成前后两部门，对后半部分＋１后与前面合并
                String orgId1 = temp.substring(0,temp.length()-ruleLength);
                int orgId2 = Integer.parseInt(temp.substring(temp.length()-ruleLength, temp.length()))+1;
                StringBuilder sBuilder = new StringBuilder();
                for(int i=0; i<ruleLength;i++) {
                    sBuilder.append("0");
                }
                DecimalFormat decimalFormat = new DecimalFormat(sBuilder.toString());
                String format = decimalFormat.format(orgId2);
                orgId = orgId1 + format;
            }else {//当前部门不存在子部门
                int lengthString = ruleLength*(Integer.parseInt(req.getOrgLevel())-Integer.parseInt(oInfo.getOrgLevel()));//新增部门编号长度×等级差距，判断需要加入的０的个数
                StringBuilder sBuilder = new StringBuilder();
                for(int i=0; i<lengthString;i++) {
                    sBuilder.append("0");
                }
                DecimalFormat decimalFormat = new DecimalFormat(sBuilder.toString());
                String format = decimalFormat.format(1);
                orgId =req.getParentOrgId()+format;
            }
            department = new Department();
            department.setDeptId(orgId);
            department.setDeptName(req.getOrgName());
            department.setDeptManager(req.getDeptManagerId());
            department.setDeptEquipment(req.getDeptEquipment());
            department.setDeptAddress(req.getDeptAddress());
            department.setRemark(req.getRemark());
            department.setInputUserId(GlobalShareContextHolder.getOrgId());
            
            orgInfo = new OrgInfo();
            //设置部门所属上级
            orgInfo.setParentOrgId(req.getParentOrgId());
            orgInfo.setOrgId(orgId);
            orgInfo.setSortNo(orgId);
            orgInfo.setOrgName(req.getOrgName());
            orgInfo.setStatus(OrgStatus.New.id);
            //设置部门等级，可修改  2为一级部门  3为二级部门
            if(OrgLevel.LEVEL_2.id.equals(req.getOrgLevel())) {//一级部门
                orgInfo.setOrgLevel(OrgLevel.LEVEL_2.id); 
            }else if(OrgLevel.LEVEL_3.id.equals(req.getOrgLevel())){//二级部门
                orgInfo.setOrgLevel(OrgLevel.LEVEL_3.id); 
            }else {
                throw new ALSException("EMS6016",req.getOrgLevel());
            }
        }else {//保存功能
            orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, req.getOrgId());
            department = bomanager.keyLoadBusinessObject(Department.class, req.getOrgId());
            department.setDeptId(req.getOrgId());
            department.setDeptName(req.getOrgName());
            department.setDeptManager(req.getDeptManagerId());
            department.setDeptEquipment(req.getDeptEquipment());
            department.setDeptAddress(req.getDeptAddress());
            department.setRemark(req.getRemark());
            department.setInputUserId(GlobalShareContextHolder.getOrgId());

            orgInfo.setParentOrgId(req.getParentOrgId());
            orgInfo.setOrgId(req.getOrgId());
            orgInfo.setSortNo(req.getOrgId());
            orgInfo.setOrgName(req.getOrgName());
        }       

        // 更新到数据库
        bomanager.updateBusinessObject(department);
        bomanager.updateBusinessObject(orgInfo);
        bomanager.updateDB();
        Map<String, String> map=new HashMap<String, String>();
        map.put("status","Y");
        return map;
    }

    /**
     * Description: 搜索部门
     * @param req
     * @return response
     * @see
     */
    @Override
    public SearchSecondLevelDeptListDtoQueryRsp searchSecondLevelDeptListDtoQuery(SearchSecondLevelDeptListDtoQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        SearchSecondLevelDeptListDtoQueryRsp response = new SearchSecondLevelDeptListDtoQueryRsp();
        List<SearchSecondLevelDeptListDto> ssds = new ArrayList<SearchSecondLevelDeptListDto>();
        List<BusinessObject> businessObjects = bomanager.selectBusinessObjectsBySql(
            "select DT.deptName as deptName,UI.userName as deptManagerName,OI.orgId as orgId from UserInfo UI, OrgInfo OI,Department DT where"
            + " DT.deptId = OI.orgId and OI.orgLevel= :orgLevel and DT.deptName like :deptName and UI.userId = DT.deptManager","orgLevel",req.getOrgLevel(),"deptName",req.getOrgName()+"%"
            ).getBusinessObjects();
        if (CollectionUtils.isEmpty(businessObjects)) {
            throw new ALSException("EMS6012");
        }
        SearchSecondLevelDeptListDto searchSecondLevelDeptListDto = null;
        for (BusinessObject businessObject : businessObjects) {
            searchSecondLevelDeptListDto = new SearchSecondLevelDeptListDto();
            searchSecondLevelDeptListDto.setDeptName(businessObject.getString("deptName"));//员工的团队名称
            searchSecondLevelDeptListDto.setDeptManager(businessObject.getString("deptManagerName"));//员工的部门经理名称
            List<UserBelong> userBelongs = bomanager.loadBusinessObjects(UserBelong.class, "orgId = :orgId","orgId",businessObject.getString("orgId"));
            searchSecondLevelDeptListDto.setDeptUserNumber(String.valueOf(userBelongs.size()));//部门下人数
            ssds.add(searchSecondLevelDeptListDto);
        }
        response.setTotalCount(ssds.size());
        response.setSearchSecondLevelDeptListDtos(ssds);
        return response;
    }

    /**
     * Description: 删除部门
     * @param req
     * @return 
     * @see
     */
    @Override
    public Map<String, String> deleteInfoDtoQuery(DeleteInfoDtoQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();

        Department department = bomanager.keyLoadBusinessObject(Department.class, req.getObjectNo());
        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, req.getObjectNo());
        ChangeEvent changeEvent = new ChangeEvent();
        //判断删除部门是一级还是二级部门
        if(OrgLevel.LEVEL_2.id.equals(orgInfo.getOrgLevel())) {//一级部门  
            //部门存在部门经理时，不能删除
            if (department.getDeptManager()!=null &&department.getDeptManager().trim().length()>0) {
                throw new ALSException("EMS6002");           
            }
            //上海安硕信息股份有限公司为固定节点，此为固定节点，不予删除
            if (("上海安硕信息股份有限公司").equals(orgInfo.getOrgName())) {
                throw new ALSException("EMS6003");          
            }
            //部门存在下级部门时，不能删除
            List<OrgInfo> ois = bomanager.loadBusinessObjects(OrgInfo.class, "parentOrgId = :parentOrgId", "parentOrgId",
                    orgInfo.getOrgId());
            if (!CollectionUtils.isEmpty(ois)) {
                throw new ALSException("EMS6004");
            }
        }else if(OrgLevel.LEVEL_3.id.equals(orgInfo.getOrgLevel())) {//二级部门
            if(OrgStatus.Completed.id.equals(orgInfo.getStatus())) {//未停用
                throw new ALSException("EMS6005");
            }
        }
        //在changeEvent表插入数据
        changeEvent.setObjectNo(req.getObjectNo());
        changeEvent.setRemark(req.getRemark());
        changeEvent.setChangeContext("删除部门信息:"+orgInfo.getOrgId());
        changeEvent.setObjectType(ChangeEventType.Delete.id);
        changeEvent.setInputDate(LocalDateTime.now());
        changeEvent.setInputUserId(GlobalShareContextHolder.getUserId());
        changeEvent.setOccurDate(LocalDateTime.now());
        changeEvent.setInputOrgId( GlobalShareContextHolder.getOrgId());
        
        bomanager.deleteBusinessObject(orgInfo);
        bomanager.deleteBusinessObject(department);
        bomanager.updateBusinessObject(changeEvent);
        bomanager.updateDB();
        
        Map<String, String> map=new HashMap<String, String>();
        map.put("status","Y");
        return map;
    }

    /**
     * Description: 查询二级部门
     * @param req
     * @return response
     * @see
     */
    @Override
    public SecondLevelDeptInfoDtoQueryRsp secondLevelDeptInfoDtoQuery(SecondLevelDeptInfoDtoQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        SecondLevelDeptInfoDtoQueryRsp rsp = new SecondLevelDeptInfoDtoQueryRsp();

        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, req.getOrgId());
        Department department = bomanager.keyLoadBusinessObject(Department.class, req.getOrgId());
        UserInfo uInfo = bomanager.keyLoadBusinessObject(UserInfo.class, department.getDeptManager());//查询部门经理姓名
        if(orgInfo == null || department == null) {
            throw new ALSException("EMS6012");         
        }
        rsp.setOrgId(orgInfo.getOrgId());
        rsp.setParentOrgId(orgInfo.getParentOrgId());
        OrgInfo orgInfoParent = bomanager.keyLoadBusinessObject(OrgInfo.class, orgInfo.getParentOrgId());
        if(orgInfoParent == null) {
            throw new ALSException("EMS6012");      
        }
        rsp.setParentOrgName(orgInfoParent.getOrgName());
        rsp.setOrgName(orgInfo.getOrgName());
        rsp.setDeptManagerName(uInfo.getUserName());//获取部门经理姓名
        rsp.setDeptManagerId(department.getDeptManager());//获取部门经理编号
        rsp.setDeptAddress(department.getDeptAddress());
        rsp.setRemark(department.getRemark());
        rsp.setDeptEquipment(department.getDeptEquipment());
        //查询部门人数
        List<UserBelong> ubs = bomanager.loadBusinessObjects(UserBelong.class, "orgId like :orgId", "orgId",orgInfo.getOrgId()+"%");  
        rsp.setDeptUserNumber(String.valueOf(ubs.size()));
        return rsp;
    }

    /**
     * Description: 查询二级部门List
     * @param req
     * @return response
     * @see
     */
    @Override
    public SecondLevelDeptListDtoQueryRsp secondLevelDeptListDtoQuery(SecondLevelDeptListDtoQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        SecondLevelDeptListDtoQueryRsp rsp = new SecondLevelDeptListDtoQueryRsp();
        //输入一级部门的id，获取二级部门的list
        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, req.getOrgId());
        if(orgInfo == null) {
            throw new ALSException("EMS6012");         
        }
        
        List<SecondLevelDeptListDto> secondLevelDeptListDtos = new ArrayList<SecondLevelDeptListDto>(); //新建返回list
        //查询二级部门list
        List<BusinessObject> businessObjects = bomanager.selectBusinessObjectsBySql(
            "select OI.status as status, UI.userName as deptManagerName,DT.deptName as deptName,DT.deptId as orgId from UserInfo UI, OrgInfo OI,Department DT where"
            + " UI.userId = DT.deptManager and DT.deptId = OI.orgId and OI.parentOrgId = :parentOrgId", "parentOrgId",orgInfo.getOrgId()
            ).getBusinessObjects();
        SecondLevelDeptListDto secondLevelDeptListDto = null;
        for (BusinessObject businessObject : businessObjects) {
            secondLevelDeptListDto = new SecondLevelDeptListDto();
            secondLevelDeptListDto.setDeptName(businessObject.getString("deptName"));
            secondLevelDeptListDto.setDeptManager(businessObject.getString("deptManagerName"));
            secondLevelDeptListDto.setOrgId(businessObject.getString("orgId"));
            //插入部门状态
            secondLevelDeptListDto.setStatus(businessObject.getString("status"));
            List<UserBelong> ubs = bomanager.loadBusinessObjects(UserBelong.class, "orgId like :orgId", "orgId", businessObject.getString("orgId") + "%");
            secondLevelDeptListDto.setDeptUserNumber(String.valueOf(ubs.size()));
            secondLevelDeptListDtos.add(secondLevelDeptListDto);
        }
        rsp.setTotalCount(secondLevelDeptListDtos.size());
        rsp.setSecondLevelDeptListDtos(secondLevelDeptListDtos);
        return rsp;
    }

    /**
     * Description: 获取一、二级部门的信息
     * @param 
     * @return  rsp
     * @see
     */
    @Override
    public OrgTreeQueryRsp oneSecondOrgTreeQuery(OrgTreeQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgTreeQueryRsp rsp = new OrgTreeQueryRsp();
        rsp.setTrees(new ArrayList<com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree>());
        List<OrgInfo> allOrgs = new ArrayList<>();
        int param = (req.getParam()) == null?2:Integer.valueOf(req.getParam());
        allOrgs = bomanager.loadBusinessObjects(OrgInfo.class, 0, Integer.MAX_VALUE,
            "orglevel between 1 and :param order by sortNo,orgType desc","param",param).getBusinessObjects();

        for (OrgInfo orgInfo : allOrgs) {
            if (OrgLevel.LEVEL_1.id.equals(orgInfo.getOrgLevel())) {//将一级部门作为根节点
                com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree rootNode = new com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree();
                rootNode.setChildren(new ArrayList<com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree>());
                rootNode.setTitle(orgInfo.getOrgName());
                rootNode.setSortNo(orgInfo.getSortNo());
                rootNode.setOrgType(orgInfo.getOrgType());
                rootNode.setKey(orgInfo.getOrgId());
                rootNode.setExpanded(CollectionUtils.isEmpty(rsp.getTrees()));// 展开第一个根节点
                rootNode.setLeaf(Boolean.FALSE);
                if (OrgStatus.Completed.id.equals(orgInfo.getStatus()) || OrgStatus.New.id.equals(orgInfo.getStatus())) {// 有效、停用展示，无效则不展示
                    rootNode.setDisable(Boolean.FALSE);
                } else if (orgInfo.getStatus().equals(OrgStatus.Disabled.id)) {
                    rootNode.setDisable(Boolean.TRUE);
                }
                this.addChildren(rootNode, orgInfo, allOrgs);
                this.sortTreeNode(rootNode);
                if (rsp.getTrees().stream().anyMatch(tree -> tree.getKey().equals(rootNode.getKey()))) { // 去重
                    continue;
                }
                rsp.getTrees().add(rootNode);
            }
        }
        return rsp;
    }

    /**
     * Description: 查询二级部门员工List
     * @param req
     * @return  rsp
     * @see
     */
    @Override
    public EmployeeInfoListDtoQueryRsp employeeInfoListDtoQuery(EmployeeInfoListDtoQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeInfoListDtoQueryRsp rsp = new EmployeeInfoListDtoQueryRsp();

        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, req.getOrgId());
        //查询部门员工
        List<UserBelong> ubs = bomanager.loadBusinessObjects(UserBelong.class, "orgId like :orgId", "orgId",
            orgInfo.getOrgId()+"%");  
        //新建员工id的list
        List<String> ids = new ArrayList<String>();
        for(UserBelong oi : ubs) {
            ids.add(oi.getUserId()); 
        }
        //给id的list，返回员工对象List
        //根据返回list循环 新建dto对象　赋值
        EmployeeListByEmplNoReq  elbNoReq = new EmployeeListByEmplNoReq();
        elbNoReq.setEmployeeNoList(ids);
        ResponseMessage<EmployeeListByEmplNoRsp> response = employeeInfoDtoClient.employeeListByEmployeeNoQuery(new RequestMessage<>(elbNoReq)).getBody();
        List<EmployeeInfoListDto> employeeInfoListDtos = new ArrayList<EmployeeInfoListDto>();
        List<EmployeeInfoDto> employeeInfoDtos = response.getMessage().getEmployeeInfoList();
        EmployeeInfoListDto employeeInfoListDto = null;
        for (EmployeeInfoDto employeeInfoDto : employeeInfoDtos) {
            employeeInfoListDto = new EmployeeInfoListDto();
            employeeInfoListDto.setEmployeeName(employeeInfoDto.getEmployeeName());
            employeeInfoListDto.setEmployeeAcct(employeeInfoDto.getEmployeeAcct());
            employeeInfoListDto.setEmployeeNo(employeeInfoDto.getEmployeeNo());
            employeeInfoListDto.setEmployeeRank(employeeInfoDto.getNowRank());
            employeeInfoListDto.setRntryTime(employeeInfoDto.getRntryTime());
            employeeInfoListDto.setSex(employeeInfoDto.getSex());
            //增加员工部门团队  员工id:employeeInfoDto.getEmployeeNo()
            Map<String, String> map = getEmployeeMap(employeeInfoDto.getEmployeeNo());
            String teamName = map.get("teamName");
            String orgName = map.get("orgName");
            employeeInfoListDto.setTeamName(teamName);
            employeeInfoListDto.setOrgName(orgName);
            employeeInfoListDtos.add(employeeInfoListDto);
        }
        rsp.setTotalCount(employeeInfoListDtos.size());
        rsp.setEmployeeInfoListDtos(employeeInfoListDtos);
        return rsp;
    }

    /**
     * Description: 搜索二级部门员工List
     * @param req
     * @return  rsp
     * @see
     */
    @Override
    public EmployeeInfoListDtoQueryRsp employeeInfoListDtoSearch(EmployeeInfoListDtoSearchReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        EmployeeInfoListDtoQueryRsp rsp = new EmployeeInfoListDtoQueryRsp();
        List<String> ids = new ArrayList<String>();

        String eNo = null;
        String eName = null;
        if (req.getFilters() == null) {
            OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, req.getOrgId());
            //查询部门员工
            List<UserBelong> ubs = bomanager.loadBusinessObjects(UserBelong.class, "orgId like :orgId", "orgId",
                orgInfo.getOrgId()+"%");  
            //新建员工id的list
            for(UserBelong oi : ubs) {
                ids.add(oi.getUserId()); 
            }
        }else{
            for (Filter filter : req.getFilters()) {//获取前段传递的filter数组，遍历获取employeeNo,employeeName
                if ("employeeNo".equals(filter.getName())) {
                    eNo = filter.getValue()[0];
                }else if ("employeeName".equals(filter.getName())) {
                    eName = filter.getValue()[0];
                }
            }
            //模糊搜索 
            String userId = StringUtils.isEmpty(eNo) ? "%" : eNo+"%";// 模糊查询用户编号
            String userName = StringUtils.isEmpty(eName) ? "%" : eName+"%";// 模糊查询用户名字
            //查询当前部门下指定员工的userId
            List<BusinessObject> businessObjects = bomanager.selectBusinessObjectsBySql(
                "select UB.userId as userId from UserBelong UB,UserInfo UI where userName like :userName and "
                + " userId like :userId and UI.userId = UB.userId UB.orgId = :orgId ","orgId",req.getOrgId(),"userName", userName, "userId", userId
                ).getBusinessObjects();
            for (BusinessObject businessObject : businessObjects) {
                ids.add(businessObject.getString("userId"));
                }
        }
        
        //根据ids调用获取员工List
        EmployeeListByEmplNoReq  elbNoReq = new EmployeeListByEmplNoReq();
        ResponseMessage<EmployeeListByEmplNoRsp> response = employeeInfoDtoClient.employeeListByEmployeeNoQuery(new RequestMessage<>(elbNoReq)).getBody();
        List<EmployeeInfoListDto> employeeInfoListDtos = new ArrayList<EmployeeInfoListDto>();
        List<EmployeeInfoDto> list = response.getMessage().getEmployeeInfoList();
        EmployeeInfoListDto employeeInfoListDto = null;
        for (EmployeeInfoDto employeeInfoDto : list) {
            employeeInfoListDto = new EmployeeInfoListDto();
            employeeInfoListDto.setEmployeeName(employeeInfoDto.getEmployeeName());
            employeeInfoListDto.setEmployeeAcct(employeeInfoDto.getEmployeeAcct());
            employeeInfoListDto.setEmployeeNo(employeeInfoDto.getEmployeeNo());
            employeeInfoListDto.setEmployeeRank(employeeInfoDto.getNowRank());
            employeeInfoListDto.setRntryTime(employeeInfoDto.getRntryTime());
            employeeInfoListDto.setSex(employeeInfoDto.getSex());
            //增加员工部门团队  员工id:employeeInfoDto.getEmployeeNo()
            Map<String, String> map = getEmployeeMap(employeeInfoDto.getEmployeeNo());
            String teamName = map.get("teamName");
            String orgName = map.get("orgName");
            employeeInfoListDto.setTeamName(teamName);
            employeeInfoListDto.setOrgName(orgName);
            employeeInfoListDtos.add(employeeInfoListDto);
        }
        rsp.setTotalCount(employeeInfoListDtos.size());
        rsp.setEmployeeInfoListDtos(employeeInfoListDtos);
        return rsp;
    }
    
    /**
     * Description: 传入员工编号，获取部门、团队名称
     * @param 员工编号employeeNo
     * @return  map
     * @see
     */
    public Map<String, String> getEmployeeMap(String employeeNo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        //增加员工部门团队  员工id:employeeInfoDto.getEmployeeNo()
        List<BusinessObject> businessObjects = bomanager.selectBusinessObjectsBySql(
            "select TI.teamName as teamName,OI.orgName as orgName from UserTeam UT,OrgInfo OI,TeamInfo TI,OrgTeam OT where OI.orgId = OT.orgId"
            + " and TI.teamId = UT.teamId and UT.teamId = OT.teamId and UT.userId= :userId","userId",employeeNo
            ).getBusinessObjects();
        String teamName = null;
        String orgName = null;
        for (BusinessObject businessObject : businessObjects) {
            teamName = businessObject.getString("teamName");//员工的团队名称
            orgName = businessObject.getString("orgName");//员工的部门名称
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("teamName", teamName);
        map.put("orgName", orgName);
        return map;
    }

    /**
     * Description:　查询所有不是部门经理的userId
     * @param  req
     * @return  rsp
     * @see
     */
    @Override
    public DeptManagerUserQueryRsp getDeptManagerAll(DeptManagerUserQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        String eNo = null;
        String eName = null;
        for (Filter filter : req.getFilters()) {//获取前段传递的filter数组，遍历获取employeeNo,employeeName
            if ("employeeNo".equals(filter.getName())) {
                eNo = filter.getValue()[0];
            }else if ("employeeName".equals(filter.getName())) {
                eName = filter.getValue()[0];
            }
        }
        String employeeNo = StringUtils.isEmpty(eNo)?"%":(eNo+"%");//判空，若为空则查询所有，不为空则模糊搜索
        String employeeName = StringUtils.isEmpty(eName)?"%":(eName+"%");//同上
        List<BusinessObject> businessObjects = bomanager.selectBusinessObjectsBySql(
            "select userId as userId,userName as userName from UserInfo where userId like :userId"
            + " and userName like :userName and userId not in (select deptManager from Department)","userId",employeeNo,"userName",employeeName
            ).getBusinessObjects();
        DeptManagerUserQueryRsp rsp = new DeptManagerUserQueryRsp();
        List<DeptUserInfo> userInfos = new ArrayList<DeptUserInfo>();
        DeptUserInfo deptUserInfo =null;
        for (BusinessObject businessObject : businessObjects) {
            deptUserInfo = new DeptUserInfo();
            deptUserInfo.setEmployeeNo(businessObject.getString("userId"));
            deptUserInfo.setEmployeeName(businessObject.getString("userName"));
            userInfos.add(deptUserInfo);
        }
        rsp.setUserInfos(userInfos);
        return rsp;
    }
}
