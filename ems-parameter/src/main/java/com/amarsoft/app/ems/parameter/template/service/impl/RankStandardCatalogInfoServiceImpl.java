package com.amarsoft.app.ems.parameter.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.RankStandardCatalogInfoService;
import com.amarsoft.app.ems.parameter.entity.RankStandardCatalog;
import com.amarsoft.app.ems.parameter.entity.RankStandardItems;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoSaveRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfo;


/**
 * 职级标准详情Service实现类
 * @author ylgao
 */
@Slf4j
@Service
public class RankStandardCatalogInfoServiceImpl implements RankStandardCatalogInfoService {
    /**
     * 职级标准详情单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public RankStandardCatalogInfoQueryRsp rankStandardCatalogInfoQuery(@Valid RankStandardCatalogInfoQueryReq rankStandardCatalogInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();

        RankStandardCatalog rankStandardCatalog = bomanager.loadBusinessObject(RankStandardCatalog.class, "serialNo",
            rankStandardCatalogInfoQueryReq.getSerialNo());
        System.out.println(rankStandardCatalog);
        if (rankStandardCatalog != null) {
            RankStandardCatalogInfoQueryRsp rankStandardCatalogInfo = new RankStandardCatalogInfoQueryRsp();
            rankStandardCatalogInfo.setSerialNo(rankStandardCatalog.getSerialNo());
            rankStandardCatalogInfo.setRankStandard(rankStandardCatalog.getRankStandard());
            rankStandardCatalogInfo.setRankName(rankStandardCatalog.getRankName());
            rankStandardCatalogInfo.setParentRankNo(rankStandardCatalog.getParentRankNo());
            rankStandardCatalogInfo.setAbility(rankStandardCatalog.getAbility());
            rankStandardCatalogInfo.setRankDescribe(rankStandardCatalog.getRankDescribe());
            rankStandardCatalogInfo.setResponeDescribe(rankStandardCatalog.getResponeDescribe());
            rankStandardCatalogInfo.setAbilityDescribe(rankStandardCatalog.getAbilityDescribe());
            rankStandardCatalogInfo.setBelongTeam(rankStandardCatalog.getBelongTeam());
            rankStandardCatalogInfo.setRankType(rankStandardCatalog.getRankType());
            rankStandardCatalogInfo.setInputUserId(rankStandardCatalog.getInputUserId());
            rankStandardCatalogInfo.setInputTime(rankStandardCatalog.getInputTime());
            rankStandardCatalogInfo.setInputOrgId(rankStandardCatalog.getInputOrgId());
            rankStandardCatalogInfo.setUpdateUserId(rankStandardCatalog.getUpdateUserId());
            rankStandardCatalogInfo.setUpdateTime(rankStandardCatalog.getUpdateTime());
            rankStandardCatalogInfo.setUpdateOrgId(rankStandardCatalog.getUpdateOrgId());
            return rankStandardCatalogInfo;
        }

        return null;
    }

    /**
     * 职级标准详情单记录保存
     * @param request
     * @return
     */
    @Override
    public RankStandardCatalogInfoSaveRsq rankStandardCatalogInfoSave(@Valid RankStandardCatalogInfoSaveReq rankStandardCatalogInfoSaveReq) {
        rankStandardCatalogInfoSaveAction(rankStandardCatalogInfoSaveReq);
        RankStandardCatalogInfoSaveRsq response = new RankStandardCatalogInfoSaveRsq();
        //输出
        response.setBelongTeam(rankStandardCatalogInfoSaveReq.getBelongTeam());
        response.setSerialNo(rankStandardCatalogInfoSaveReq.getSerialNo());
        return response;
    }

    /**
     * 职级标准详情单记录保存
     * @param
     * @return
     */
    @Transactional
    public void rankStandardCatalogInfoSaveAction(RankStandardCatalogInfo rankStandardCatalogInfo) {
        //定义业务对象管理容器
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (rankStandardCatalogInfo != null) {
            //保存校验－重复性校验
            if (saveValidate(rankStandardCatalogInfo.getRankName(), rankStandardCatalogInfo.getRankStandard()) == false) {
                System.out.println("当前重复");
                throw new ALSException("900204");
            }
            else {
                System.out.println("可以保存");
                //根据主键获取实体类信息
                RankStandardCatalog rankStandardCatalog = bomanager.keyLoadBusinessObject(RankStandardCatalog.class,
                    rankStandardCatalogInfo.getSerialNo());
                if (rankStandardCatalog == null) {
                    rankStandardCatalog = new RankStandardCatalog();
                    if (rankStandardCatalogInfo.getSerialNo() == null) {
                        rankStandardCatalog.generateKey();
                    }
                }
                //属性复制
                BeanUtils.copyProperties(rankStandardCatalogInfo, rankStandardCatalog);
                //更新业务对象
                bomanager.updateBusinessObject(rankStandardCatalog);

            }
        }
        //事务提交
        bomanager.updateDB();
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
     * 职级标准详情单记录删除
     * @param
     * @return
     */
    @Override
    @Transactional
    public void rankStandardCatalogInfoDelete(@Valid RankStandardCatalogInfoQueryReq rankStandardCatalogInfoQueryReq) {
        //获取主表id
        String serialNo = rankStandardCatalogInfoQueryReq.getSerialNo();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        //根据主键匹配主表信息
        RankStandardCatalog rankStandardCatalog = bomanager.keyLoadBusinessObject(RankStandardCatalog.class, serialNo);
        if (null == rankStandardCatalog) {
            if (log.isErrorEnabled()) {
                log.error(serialNo + "此职级不存在!");
            }
            throw new ALSException("204420");
        }
        //删除对应子职级别
        bomanager.deleteObjectBySql(RankStandardCatalog.class, "parentRankNo=:serialNo", "serialNo", serialNo);
        //删除对应职级指标
        bomanager.deleteObjectBySql(RankStandardItems.class, "rankNo=:serialNo", "serialNo", serialNo);
        bomanager.updateDB();
    }
}
