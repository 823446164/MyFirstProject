package com.amarsoft.app.ems.parameter.template.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.parameter.entity.RankStandardCatalog;
import com.amarsoft.app.ems.parameter.entity.RankStandardItems;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoQueryRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.service.RankStandardCatalogInfoService;

import lombok.extern.slf4j.Slf4j;


/**
 * 职级标准详情Service实现类
 * @author xphe
 */
@Slf4j
@Service
public class RankStandardCatalogInfoServiceImpl implements RankStandardCatalogInfoService {

    /**
     * 
     * Description: 职级/子职级标准详情查询
     * @param rankStandardCatalogSonInfoQueryReq
     * @return　RankStandardCatalogSonInfoQueryRsq
     * @see
     */
    @Override
    @Transactional
    public RankStandardCatalogChildInfoQueryRsq rankStandardCatalogChildInfoQuery(@Valid RankStandardCatalogChildInfoQueryReq rankStandardCatalogChildInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();

        RankStandardCatalog rankStandardCatalog = bomanager.loadBusinessObject(RankStandardCatalog.class, "serialNo",
            rankStandardCatalogChildInfoQueryReq.getSerialNo());
        if (rankStandardCatalog != null) {
            RankStandardCatalogChildInfoQueryRsq rankStandardCatalogInfo = new RankStandardCatalogChildInfoQueryRsq();
            rankStandardCatalogInfo.setSerialNo(rankStandardCatalogChildInfoQueryReq.getSerialNo());
            rankStandardCatalogInfo.setBelongTeam(rankStandardCatalog.getBelongTeam());
            rankStandardCatalogInfo.setRankType(rankStandardCatalog.getRankType());
            rankStandardCatalogInfo.setRankStandard(rankStandardCatalog.getRankStandard());
            rankStandardCatalogInfo.setRankName(rankStandardCatalog.getRankName());
            rankStandardCatalogInfo.setChildRankNo(rankStandardCatalog.getChildRankNo());
            rankStandardCatalogInfo.setRankDescribe(rankStandardCatalog.getRankDescribe());
            rankStandardCatalogInfo.setResponeDescribe(rankStandardCatalog.getResponeDescribe());
            rankStandardCatalogInfo.setAbilityDescribe(rankStandardCatalog.getAbilityDescribe());
            rankStandardCatalogInfo.setAbility(rankStandardCatalog.getAbility());
            return rankStandardCatalogInfo;
        }

        return null;
    }

    /**
     * 
     * Description: 子职级标准详情保存
     * @param rankStandardCatalogChildInfoSaveReq
     * @return　map
     * @see
     */
    @Override
    @Transactional
    public Map<String, String> rankStandardCatalogChildInfoSave(@Valid RankStandardCatalogChildInfoSaveReq rankStandardCatalogChildInfoSaveReq) {
        // 定义业务对象管理容器
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (rankStandardCatalogChildInfoSaveReq != null) {
            // 根据主键获取实体类信息
            RankStandardCatalog rankStandardCatalog = bomanager.keyLoadBusinessObject(RankStandardCatalog.class,
                rankStandardCatalogChildInfoSaveReq.getSerialNo());
            // 空判断
            if (rankStandardCatalog == null) {
                String childRankNo = rankStandardCatalogChildInfoSaveReq.getChildRankNo();
                String rankStandard = rankStandardCatalogChildInfoSaveReq.getRankStandard();
                String rankName = rankStandardCatalogChildInfoSaveReq.getRankName();
                if (!StringUtils.isEmpty(childRankNo)) {
                    // 职级保存校验－重复性校验
                    if (childSaveValidate(childRankNo, rankStandard) == false) {
                        throw new ALSException("EMS2002", childRankNo, rankStandard);
                    }
                }
                else {
                    // 职等保存校验－重复性校验
                    if (saveValidate(rankName, rankStandard) == false) {
                        throw new ALSException("EMS2001", rankName, rankStandard);
                    }
                }
                rankStandardCatalog = new RankStandardCatalog();
                rankStandardCatalog.generateKey();
                rankStandardCatalog.setInputOrgId(GlobalShareContextHolder.getOrgId());
                rankStandardCatalog.setParentRankNo(rankStandardCatalogChildInfoSaveReq.getParentRankNo());
                rankStandardCatalog.setRankStandard(rankStandardCatalogChildInfoSaveReq.getRankStandard());
                rankStandardCatalog.setRankName(rankStandardCatalogChildInfoSaveReq.getRankName());
                rankStandardCatalog.setRankDescribe(rankStandardCatalogChildInfoSaveReq.getRankDescribe());
                rankStandardCatalog.setAbilityDescribe(rankStandardCatalogChildInfoSaveReq.getAbilityDescribe());
                rankStandardCatalog.setResponeDescribe(rankStandardCatalogChildInfoSaveReq.getResponeDescribe());
                rankStandardCatalog.setChildRankNo(rankStandardCatalogChildInfoSaveReq.getChildRankNo());
                rankStandardCatalog.setAbility(rankStandardCatalogChildInfoSaveReq.getAbility());
                rankStandardCatalog.setBelongTeam(rankStandardCatalogChildInfoSaveReq.getBelongTeam());
                rankStandardCatalog.setRankType(rankStandardCatalogChildInfoSaveReq.getRankType());
            }
            else {
                rankStandardCatalog.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
                rankStandardCatalog.setRankStandard(rankStandardCatalogChildInfoSaveReq.getRankStandard());
                rankStandardCatalog.setRankName(rankStandardCatalogChildInfoSaveReq.getRankName());
                rankStandardCatalog.setRankDescribe(rankStandardCatalogChildInfoSaveReq.getRankDescribe());
                rankStandardCatalog.setAbilityDescribe(rankStandardCatalogChildInfoSaveReq.getAbilityDescribe());
                rankStandardCatalog.setResponeDescribe(rankStandardCatalogChildInfoSaveReq.getResponeDescribe());
                rankStandardCatalog.setChildRankNo(rankStandardCatalogChildInfoSaveReq.getChildRankNo());
                rankStandardCatalog.setAbility(rankStandardCatalogChildInfoSaveReq.getAbility());
            }
            bomanager.updateBusinessObject(rankStandardCatalog);
        }
        // 事务提交
        bomanager.updateDB();
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "Y");
        return map;

    }

    /**
     * 
     * Description: 校验职级＋职等是否在系统中已经存在
             * 1、存在即不可新增
             * 2、不存在即新增成功
     * @param rankName
     * @param rankStandard
     * @return　boolean
     * @see
     */
    public boolean saveValidate(String rankName, String rankStandard) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<RankStandardCatalog> rankStandardCatalogs = bomanager.loadBusinessObjects(RankStandardCatalog.class,
            "rankName=:rankName and rankStandard=:rankStandard", "rankName", rankName, "rankStandard", rankStandard);
        if (rankStandardCatalogs.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 
     * Description: 校验子职级是否在系统中已经存在
             * 1、存在即不可新增
             * 2、不存在即新增成功
     * @param rankName
     * @param rankStandard
     * @return　boolean
     * @see
     */
    public boolean childSaveValidate(String childRankNo, String rankStandard) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<RankStandardCatalog> rankStandardCatalogs = bomanager.loadBusinessObjects(RankStandardCatalog.class,
            "childRankNo=:childRankNo and rankStandard=:rankStandard", "childRankNo", childRankNo, "rankStandard", rankStandard);
        if (rankStandardCatalogs.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    // 删除校验,待做
    // TODO xphe 删除校验－－－输入：所属团队 当前时间（本月内）团队的职级体系是否有人在申请（目标申请表，状态审批中，待处理）
    public boolean checkDeleteRandStand(String belongTeam, String type) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // TeamInfo teaminfo = bomanager.keyLoadBusinessObject(TeamInfo.class, belongTeam);
        // String teamManager = teaminfo.getRoleA();
        BusinessObjectAggregate<BusinessObject> flowCount = bomanager.selectBusinessObjectsBySql(
            "select count(1) as cnt from EmployeeTargetRank er,FlowObject fo where" + " (er.teamManager=:teamManager and date_format(er.inputTime,'%Y%m')=date_format(NOW(),'%Y%m') and er.rankSerialNo=fo.objectNo and fo.phaseName in ('审批中','待处理'))");
        /*
         * List<EmployeeTargetRank> employeeTargetRanks =
         * bomanager.loadBusinessObjects(EmployeeTargetRank.class,
         * "teamManager=:teamManager and date_format(inputTime,'%Y%m')=date_format(NOW(),'%Y%m')",
         * "teamManager", teamManager);
         */
        BusinessObjectAggregate<BusinessObject> addCount = bomanager.selectBusinessObjectsBySql("select count(1) as cnt from ");
        int count = flowCount.getBusinessObjects().get(0).getInt("cnt");
        int addcount = addCount.getBusinessObjects().get(0).getInt("cnt");
        if (count == 0 || addcount == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 
     * Description: 职等/子职级详情单记录删除
     * @param rankName
     * @param rankStandard
     * @return　boolean
     * @see
     */
    @Override
    @Transactional
    public void rankStandardCatalogInfoDelete(@Valid RankStandardCatalogChildInfoQueryReq rankStandardCatalogChildInfoQueryReq) {
        // 获取主表id
        String serialNo = rankStandardCatalogChildInfoQueryReq.getSerialNo();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 根据主键匹配主表信息
        RankStandardCatalog rankStandardCatalog = bomanager.keyLoadBusinessObject(RankStandardCatalog.class, serialNo);
        if (null == rankStandardCatalog) {
            if (log.isErrorEnabled()) {
                log.error(serialNo + "此职级不存在!");
            }
            throw new ALSException("EMS2003");
        }
        // 删除对应职等及子职级
        bomanager.deleteObjectBySql(RankStandardCatalog.class, "serialNo=:serialNo or parentRankNo=:serialNo", "serialNo", serialNo);
        // 删除对应职级指标
        bomanager.deleteObjectBySql(RankStandardItems.class, "rankNo=:serialNo", "serialNo", serialNo);
        bomanager.updateDB();
    }

}
