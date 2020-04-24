package com.amarsoft.app.ems.system.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.aecd.system.constant.CompanyType;
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.aecd.system.constant.OrgType;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
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
import com.amarsoft.app.ems.system.cs.dto.orgtreequery.OrgTreeQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgtreequery.Tree;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.OrgUserQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orguserquery.UserInfo;
import com.amarsoft.app.ems.system.entity.OrgInfo;
import com.amarsoft.app.ems.system.entity.UserBelong;
import com.amarsoft.app.ems.system.service.OrgService;
import com.amarsoft.app.ems.system.service.UserService;

/**
 * 机构服务的接口实现类
 * 
 * @author hzhang23
 *
 */
@Service
@RefreshScope
public class OrgServiceImpl implements OrgService {

    @Value("${global.business.org.default-length}")
    private int orgDefaultLength;
    
    
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

    @Override
    public void setOrgInfo(OrgInfoUpdateReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        String orgId = req.getOrgId();

        OrgInfo orgInfo = bomanager.keyLoadBusinessObject(OrgInfo.class, orgId);

        if (orgInfo == null)
            throw new ALSException("900210");

        // 设置参数值
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

        orgInfo.setCountry(req.getCountry());
        orgInfo.setBankId(req.getBankId());
        orgInfo.setBelongArea(req.getBelongArea());
        orgInfo.setCoreOrgId(req.getCoreOrgId());
        orgInfo.setOrgAddress(req.getOrgAddress());
        if (req.getStatus().equals(SystemStatus.Locked.id)) {// 锁定机构时，同时锁定其子机构
            updateChildrenOrgStatus(bomanager, req.getOrgId(), req.getStatus());
        }
        orgInfo.setStatus(req.getStatus());
        // 更新到数据库
        bomanager.updateBusinessObject(orgInfo);
        bomanager.updateDB();
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
     * 
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

    @Override
    public OrgUserQueryRsp orgUserQuery(OrgUserQueryReq req, UserService userService) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgUserQueryRsp rsp = new OrgUserQueryRsp();
        rsp.setUsers(new ArrayList<UserInfo>());
        String[] searchAttributes = { "userId", "userName" ,"logonId", "status"};// 查询条件
        BusinessObjectAggregate<com.amarsoft.app.ems.system.entity.UserInfo> orgUserAggregate = null;
        BusinessObjectAggregate<com.amarsoft.app.ems.system.entity.UserInfo> departUserAggregate = null;
        int userCount = 0;
        String condition = "";
        if (req.getEntireFlag().equals(YesNo.Yes.id)) {
            condition = req.getOrgId() + "%";
        }else {
            condition = req.getOrgId();
        }
        if (StringUtils.isEmpty(req.getSearchAttribute()) && StringUtils.isEmpty(req.getSearchContent())) {// 不走查询条件
            if (StringUtils.isEmpty(req.getOrgLevel())) {// 机构级别为空
                orgUserAggregate = bomanager.loadBusinessObjects(com.amarsoft.app.ems.system.entity.UserInfo.class,
                        req.getBegin(), req.getPageSize(),
                        " userId in ( select distinct(userId) from UserBelong where orgId in (select orgId from OrgInfo where orgId like :orgId))",
                        "orgId", condition);
            } else {
                orgUserAggregate = bomanager.loadBusinessObjects(com.amarsoft.app.ems.system.entity.UserInfo.class,
                        req.getBegin(), req.getPageSize(),
                        " userId in ( select distinct(userId) from UserBelong where orgId in (select orgId from OrgInfo where orgId like :orgId and orgLevel = :orgLevel and orgType <> :orgType))",
                        "orgId", condition, "orgLevel", req.getOrgLevel(), "orgType", OrgType.Department.id);
                departUserAggregate = bomanager.loadBusinessObjects(com.amarsoft.app.ems.system.entity.UserInfo.class,
                        req.getBegin(), req.getPageSize(),
                        " userId in ( select distinct(userId) from UserBelong where orgId in (select orgId from OrgInfo where orgId like :orgId and orgLevel = :orgLevel and orgType = :orgType))",
                        "orgId", condition, "orgLevel", OrgLevel.getNextLevel(req.getOrgLevel()),  "orgType", OrgType.Department.id);

            }

            userCount = orgUserAggregate.getAggregate("count(userId) as cnt").getInt("cnt") + (departUserAggregate == null ? 0 :departUserAggregate.getAggregate("count(userId) as cnt").getInt("cnt"));
        } else {
            if (Stream.of(searchAttributes)
                    .anyMatch(searchAttribute -> searchAttribute.equalsIgnoreCase(req.getSearchAttribute()))) {// 验证查询条件
                if (StringUtils.isEmpty(req.getOrgLevel())) {// 机构级别为空
                    orgUserAggregate = bomanager.loadBusinessObjects(com.amarsoft.app.ems.system.entity.UserInfo.class,
                            req.getBegin(), req.getPageSize(),
                            " userId in(select distinct(userId) from UserBelong where orgId in (select orgId from OrgInfo where orgId like :orgId)) and "
                                    + req.getSearchAttribute() + " like :searchContent",
                            "orgId", condition, "searchContent", "%" + req.getSearchContent() + "%");
                } else {
                    orgUserAggregate = bomanager.loadBusinessObjects(com.amarsoft.app.ems.system.entity.UserInfo.class,
                            req.getBegin(), req.getPageSize(),
                            " userId in(select distinct(userId) from UserBelong where orgId in (select orgId from OrgInfo where orgId like :orgId and orgLevel=:orgLevel and orgType <> :orgType)) and "
                                    + req.getSearchAttribute() + " like :searchContent",
                            "orgId", condition, "orgLevel", req.getOrgLevel(),"orgType", OrgType.Department.id, "searchContent",
                            "%" + req.getSearchContent() + "%");
                    departUserAggregate = bomanager.loadBusinessObjects(com.amarsoft.app.ems.system.entity.UserInfo.class,
                            req.getBegin(), req.getPageSize(),
                            " userId in(select distinct(userId) from UserBelong where orgId in (select orgId from OrgInfo where orgId like :orgId and orgLevel=:orgLevel and orgType = :orgType )) and "
                                    + req.getSearchAttribute() + " like :searchContent",
                            "orgId", condition, "orgLevel", OrgLevel.getNextLevel(req.getOrgLevel()), "orgType", OrgType.Department.id, "searchContent",
                            "%" + req.getSearchContent() + "%");
                }

                userCount = orgUserAggregate.getAggregate("count(userId) as cnt").getInt("cnt") + (departUserAggregate == null ? 0 :departUserAggregate.getAggregate("count(userId) as cnt").getInt("cnt"));
            } else {
                throw new ALSException("900215");
            }
        }
        ArrayList<com.amarsoft.app.ems.system.entity.UserInfo> allUsers = new ArrayList<>();
        for (com.amarsoft.app.ems.system.entity.UserInfo user : orgUserAggregate.getBusinessObjects()) {
            if (allUsers.stream().anyMatch(u -> u.getUserId().equals(user.getUserId()))) {
                continue;
            }
            allUsers.add(user);
        }
        if (departUserAggregate != null) {
            for (com.amarsoft.app.ems.system.entity.UserInfo user : departUserAggregate.getBusinessObjects()) {
                if (allUsers.stream().anyMatch(u -> u.getUserId().equals(user.getUserId()))) {
                    continue;
                }
                allUsers.add(user);
            }
        }
        for (com.amarsoft.app.ems.system.entity.UserInfo user : allUsers) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(user.getUserId());
            userInfo.setLogonId(user.getLogonId());
            userInfo.setUserName(user.getUserName());
            userInfo.setCounter(user.getCounter());
            userInfo.setEmail(user.getEmail());
            userInfo.setEmpNo(user.getEmpNo());
            userInfo.setPhoneNo(user.getPhoneNo());
            userInfo.setJobTitle(user.getJobTitle());
            userInfo.setOfficeTel(user.getOfficeTel());
            userInfo.setStatus(user.getStatus());
            rsp.getUsers().add(userInfo);
        }
        rsp.setTotalCount(userCount);
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
}
