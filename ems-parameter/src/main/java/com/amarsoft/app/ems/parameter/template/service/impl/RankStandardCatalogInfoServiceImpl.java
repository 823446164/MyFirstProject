package com.amarsoft.app.ems.parameter.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamInfo;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.parameter.template.service.RankStandardCatalogInfoService;
import com.amarsoft.app.ems.parameter.entity.RankStandardCatalog;
import com.amarsoft.app.ems.parameter.entity.RankStandardItems;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoSaveRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogsoninfo.RankStandardCatalogSonInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogsoninfo.RankStandardCatalogSonInfoQueryRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogsoninfo.RankStandardCatalogSonInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfo;


/**
 * 职级标准详情Service实现类
 * @author ylgao
 */
@Slf4j
@Service
public class RankStandardCatalogInfoServiceImpl implements RankStandardCatalogInfoService {

    /**
     * 
     * Description: 职级标准详情单记录查询
     * @param rankStandardCatalogInfoQueryReq
     * @return　RankStandardCatalogInfoQueryRsp
     * @see
     */
    @Override
    @Transactional
    public RankStandardCatalogInfoQueryRsp rankStandardCatalogInfoQuery(@Valid RankStandardCatalogInfoQueryReq rankStandardCatalogInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();

        RankStandardCatalog rankStandardCatalog = bomanager.loadBusinessObject(RankStandardCatalog.class, "serialNo",
            rankStandardCatalogInfoQueryReq.getSerialNo());
        if (null!=rankStandardCatalog ) {
            RankStandardCatalogInfoQueryRsp rankStandardCatalogInfo = new RankStandardCatalogInfoQueryRsp();
            rankStandardCatalogInfo.setRankStandard(rankStandardCatalog.getRankStandard());
            rankStandardCatalogInfo.setRankName(rankStandardCatalog.getRankName());
            rankStandardCatalogInfo.setRankDescribe(rankStandardCatalog.getRankDescribe());
            rankStandardCatalogInfo.setResponeDescribe(rankStandardCatalog.getResponeDescribe());
            rankStandardCatalogInfo.setAbilityDescribe(rankStandardCatalog.getAbilityDescribe());
            return rankStandardCatalogInfo;
        }

        return null;
    }

    /**
     * 
     * Description:  职级标准详情单记录保存
     * @param rankStandardCatalogInfoSaveReq
     * @return　RankStandardCatalogInfoSaveRsq
     * @see
     */
    @Override
    public RankStandardCatalogInfoSaveRsq rankStandardCatalogInfoSave(@Valid RankStandardCatalogInfoSaveReq rankStandardCatalogInfoSaveReq) {
        String serialAndTeam = rankStandardCatalogInfoSaveAction(rankStandardCatalogInfoSaveReq);

        RankStandardCatalogInfoSaveRsq response = new RankStandardCatalogInfoSaveRsq();
        // 输出
        response.setBelongTeam(serialAndTeam.split("/")[1]);
        response.setSerialNo(serialAndTeam.split("/")[0]);
        return response;
    }

    /**
     * 
     * Description:  职级标准详情单记录保存
     * @param rankStandardCatalogInfo
     * @return　
     * @see
     */
    @Transactional
    public String rankStandardCatalogInfoSaveAction(RankStandardCatalogInfo rankStandardCatalogInfo) {
        LocalDateTime inputDate = LocalDateTime.now();
        String serialAndTeam = null;
        // 定义业务对象管理容器
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (rankStandardCatalogInfo != null) {
            // 保存校验－重复性校验
            if (saveValidate(rankStandardCatalogInfo.getRankName(), rankStandardCatalogInfo.getRankStandard()) == false) {
                throw new ALSException("EMS2001", rankStandardCatalogInfo.getRankName(), rankStandardCatalogInfo.getRankStandard());
            }
            else {
                // 根据主键获取实体类信息
                RankStandardCatalog rankStandardCatalog = bomanager.keyLoadBusinessObject(RankStandardCatalog.class,
                    rankStandardCatalogInfo.getSerialNo());
                if (rankStandardCatalog == null ) {
                    rankStandardCatalog = new RankStandardCatalog();
                    if (StringUtils.isEmpty(rankStandardCatalogInfo.getSerialNo()) ) {
                        rankStandardCatalog.generateKey();
                    }
                    else {
                        rankStandardCatalog.setSerialNo(rankStandardCatalogInfo.getSerialNo());
                    }
                    rankStandardCatalog.setInputTime(inputDate);
                    rankStandardCatalog.setInputUserId(GlobalShareContextHolder.getUserId());
                    rankStandardCatalog.setInputOrgId(GlobalShareContextHolder.getOrgId());
                }
                // 属性复制
                rankStandardCatalog.setUpdateTime(inputDate);
                rankStandardCatalog.setUpdateUserId(GlobalShareContextHolder.getUserId());
                rankStandardCatalog.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
                rankStandardCatalog.setBelongTeam(rankStandardCatalogInfo.getBelongTeam());
                rankStandardCatalog.setRankStandard(rankStandardCatalogInfo.getRankStandard());
                rankStandardCatalog.setRankName(rankStandardCatalogInfo.getRankName());
                rankStandardCatalog.setRankDescribe(rankStandardCatalogInfo.getRankDescribe());
                rankStandardCatalog.setResponeDescribe(rankStandardCatalogInfo.getResponeDescribe());
                rankStandardCatalog.setAbilityDescribe(rankStandardCatalogInfo.getAbilityDescribe());
                rankStandardCatalog.setRankType(rankStandardCatalogInfo.getRankType());

                // 更新业务对象
                bomanager.updateBusinessObject(rankStandardCatalog);
                serialAndTeam = rankStandardCatalog.getSerialNo() + "/" + rankStandardCatalog.getBelongTeam();

            }
        }
        // 事务提交
        bomanager.updateDB();
        return serialAndTeam;

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
     * Description: 校验子职级＋职等是否在系统中已经存在
             * 1、存在即不可新增
             * 2、不存在即新增成功
     * @param rankName
     * @param rankStandard
     * @return　boolean
     * @see
     */
    public boolean sonSaveValidate(String sonRank, String rankStandard) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<RankStandardCatalog> rankStandardCatalogs = bomanager.loadBusinessObjects(RankStandardCatalog.class,
            "childRankNo=:sonRank and rankStandard=:rankStandard", "sonRank", sonRank, "rankStandard", rankStandard);
        if (rankStandardCatalogs.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

 // 删除校验,待做
    // TODO xphe 删除校验－－－输入：所属团队 当前时间（本月内）团队的职级体系是否有人在申请（目标申请表，状态审批中，待处理）
    public boolean checkDeleteRandStand(String belongTeam,String type) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        //TeamInfo teaminfo = bomanager.keyLoadBusinessObject(TeamInfo.class, belongTeam);
       // String teamManager = teaminfo.getRoleA();
        BusinessObjectAggregate<BusinessObject> flowCount = bomanager.selectBusinessObjectsBySql(
            "select count(1) as cnt from EmployeeTargetRank er,FlowObject fo where" +
        " (er.teamManager=:teamManager and date_format(er.inputTime,'%Y%m')=date_format(NOW(),'%Y%m') and er.rankSerialNo=fo.objectNo and fo.phaseName in ('审批中','待处理'))");
        // List<EmployeeTargetRank> employeeTargetRanks =
        // bomanager.loadBusinessObjects(EmployeeTargetRank.class,
        // "teamManager=:teamManager and date_format(inputTime,'%Y%m')=date_format(NOW(),'%Y%m')",
        // "teamManager", teamManager);
        BusinessObjectAggregate<BusinessObject> addCount=bomanager.selectBusinessObjectsBySql("select count(1) as cnt from ");
        int count = flowCount.getBusinessObjects().get(0).getInt("cnt");
        int addcount=addCount.getBusinessObjects().get(0).getInt("cnt");
        if (count == 0 || addcount==0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 
     * Description: 职级标准详情单记录删除
     * @param rankName
     * @param rankStandard
     * @return　boolean
     * @see
     */
    @Override
    @Transactional
    public void rankStandardCatalogInfoDelete(@Valid RankStandardCatalogInfoQueryReq rankStandardCatalogInfoQueryReq) {
        // 获取主表id
        String serialNo = rankStandardCatalogInfoQueryReq.getSerialNo();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 根据主键匹配主表信息
        RankStandardCatalog rankStandardCatalog = bomanager.keyLoadBusinessObject(RankStandardCatalog.class, serialNo);
        if (null == rankStandardCatalog) {
            if (log.isErrorEnabled()) {
                log.error(serialNo + "此职级不存在!");
            }
            throw new ALSException("EMS2003");
        }
        // 删除对应子职级别
        bomanager.deleteObjectBySql(RankStandardCatalog.class, "rankName=:rankName", "rankName", rankStandardCatalog.getRankName());
        // 删除对应职级指标
        bomanager.deleteObjectBySql(RankStandardItems.class, "rankNo=:serialNo", "serialNo", serialNo);
        bomanager.updateDB();
    }

    /**
     * 
     * Description: 子职级标准详情单记录删除
     * @param rankStandardCatalogInfoQueryReq
     * @return　boolean
     * @see
     */
    @Override
    @Transactional
    public void rankStandardCatalogSonInfoDelete(@Valid RankStandardCatalogInfoQueryReq rankStandardCatalogInfoQueryReq) {
        // 获取主表id
        String serialNo = rankStandardCatalogInfoQueryReq.getSerialNo();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 根据主键匹配主表信息
        RankStandardCatalog rankStandardCatalog = bomanager.keyLoadBusinessObject(RankStandardCatalog.class, serialNo);
        if (null == rankStandardCatalog) {
            if (log.isErrorEnabled()) {
                log.error(serialNo + "此职级不存在!");
            }
            throw new ALSException("EMS2003");
        }
        bomanager.deleteBusinessObject(rankStandardCatalog);
        // 删除对应职级指标
        bomanager.deleteObjectBySql(RankStandardItems.class, "rankNo=:serialNo", "serialNo", serialNo);
        bomanager.updateDB();
    }

    /**
     * 
     * Description: 子职级标准详情查询
     * @param rankStandardCatalogSonInfoQueryReq
     * @return　RankStandardCatalogSonInfoQueryRsq
     * @see
     */
    @Override
    @Transactional
    public RankStandardCatalogSonInfoQueryRsq rankStandardCatalogSonInfoQuery(@Valid RankStandardCatalogSonInfoQueryReq rankStandardCatalogSonInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();

        RankStandardCatalog rankStandardCatalog = bomanager.loadBusinessObject(RankStandardCatalog.class, "serialNo",
            rankStandardCatalogSonInfoQueryReq.getSerialNo());
        if (rankStandardCatalog != null) {
            RankStandardCatalogSonInfoQueryRsq rankStandardCatalogInfo = new RankStandardCatalogSonInfoQueryRsq();
            rankStandardCatalogInfo.setSerialNo(rankStandardCatalogSonInfoQueryReq.getSerialNo());
            rankStandardCatalogInfo.setRankStandard(rankStandardCatalog.getRankStandard());
            rankStandardCatalogInfo.setRankName(rankStandardCatalog.getRankName());
            rankStandardCatalogInfo.setChildRankNo(rankStandardCatalog.getChildRankNo());
            rankStandardCatalogInfo.setRankDescribe(rankStandardCatalog.getRankDescribe());
            rankStandardCatalogInfo.setResponeDescribe(rankStandardCatalog.getResponeDescribe());
            rankStandardCatalogInfo.setAbilityDescribe(rankStandardCatalog.getAbilityDescribe());
            return rankStandardCatalogInfo;
        }

        return null;
    }

    /**
     * 
     * Description: 子职级标准详情保存
     * @param rankStandardCatalogSonInfoSaveReq
     * @return　map
     * @see
     */
    @Override
    @Transactional
    public Map<String, String> rankStandardCatalogSonInfoSave(@Valid RankStandardCatalogSonInfoSaveReq rankStandardCatalogSonInfoSaveReq) {
        LocalDateTime inputDate = LocalDateTime.now();
        // 定义业务对象管理容器
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (rankStandardCatalogSonInfoSaveReq != null) {
            // 保存校验－重复性校验
            if (sonSaveValidate(rankStandardCatalogSonInfoSaveReq.getChildRankNo(),
                rankStandardCatalogSonInfoSaveReq.getRankStandard()) == false) {
                log.info("当前重复");
                throw new ALSException("EMS2002", rankStandardCatalogSonInfoSaveReq.getChildRankNo(),
                    rankStandardCatalogSonInfoSaveReq.getRankStandard());
            }
            else {
                log.info("可以保存");
                // 根据主键获取实体类信息
                RankStandardCatalog rankStandardCatalog = bomanager.keyLoadBusinessObject(RankStandardCatalog.class,
                    rankStandardCatalogSonInfoSaveReq.getSerialNo());
                String parentNo= rankStandardCatalog.getParentRankNo();
                if (StringUtils.isEmpty(parentNo)) {
                    log.info("执行至新增");
                    RankStandardCatalog   newrankStandardCatalog = new RankStandardCatalog();
                    newrankStandardCatalog.generateKey();
                        log.info("生成主键");
                        newrankStandardCatalog.setInputTime(inputDate);
                        newrankStandardCatalog.setInputUserId(GlobalShareContextHolder.getUserId());
                        newrankStandardCatalog.setInputOrgId(GlobalShareContextHolder.getOrgId());
                        newrankStandardCatalog.setParentRankNo(rankStandardCatalogSonInfoSaveReq.getSerialNo());
                        newrankStandardCatalog.setRankStandard(rankStandardCatalogSonInfoSaveReq.getRankStandard());
                        newrankStandardCatalog.setRankName(rankStandardCatalogSonInfoSaveReq.getRankName());
                        newrankStandardCatalog.setRankDescribe(rankStandardCatalogSonInfoSaveReq.getRankDescribe());
                        newrankStandardCatalog.setAbilityDescribe(rankStandardCatalogSonInfoSaveReq.getAbilityDescribe());
                        newrankStandardCatalog.setResponeDescribe(rankStandardCatalogSonInfoSaveReq.getResponeDescribe());
                        newrankStandardCatalog.setUpdateTime(inputDate);
                        newrankStandardCatalog.setUpdateUserId(GlobalShareContextHolder.getUserId());
                        newrankStandardCatalog.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
                        newrankStandardCatalog.setChildRankNo(rankStandardCatalogSonInfoSaveReq.getChildRankNo());
                        newrankStandardCatalog.setAbility(rankStandardCatalogSonInfoSaveReq.getAbility());
                        bomanager.updateBusinessObject(newrankStandardCatalog);
                }else {
                // 属性复制
                rankStandardCatalog.setUpdateTime(inputDate);
                rankStandardCatalog.setUpdateUserId(GlobalShareContextHolder.getUserId());
                rankStandardCatalog.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
                rankStandardCatalog.setChildRankNo(rankStandardCatalogSonInfoSaveReq.getChildRankNo());
                rankStandardCatalog.setAbility(rankStandardCatalogSonInfoSaveReq.getAbility());
                // 更新业务对象
                bomanager.updateBusinessObject(rankStandardCatalog);
                }

            }
        }
        // 事务提交
        bomanager.updateDB();
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "Y");
        return map;

    }
}
