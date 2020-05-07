package com.amarsoft.app.ems.parameter.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.RankStandardCatalogInfoService;
import com.amarsoft.app.ems.parameter.entity.RankStandardCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfo;

/**
 * 职级标准详情Service实现类
 * @author ylgao
 */
@Slf4j
@Service
public class RankStandardCatalogInfoServiceImpl implements RankStandardCatalogInfoService{
    /**
     * 职级标准详情单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public RankStandardCatalogInfoQueryRsp rankStandardCatalogInfoQuery(@Valid RankStandardCatalogInfoQueryReq rankStandardCatalogInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        RankStandardCatalog rankStandardCatalog = bomanager.loadBusinessObject(RankStandardCatalog.class,"serialNo",rankStandardCatalogInfoQueryReq.getSerialNo());
        if(rankStandardCatalog!=null){
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
    public void rankStandardCatalogInfoSave(@Valid RankStandardCatalogInfoSaveReq rankStandardCatalogInfoSaveReq) {
        rankStandardCatalogInfoSaveAction(rankStandardCatalogInfoSaveReq);
    }
    /**
     * 职级标准详情单记录保存
     * @param
     * @return
     */
    @Transactional
    public void rankStandardCatalogInfoSaveAction(RankStandardCatalogInfo rankStandardCatalogInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(rankStandardCatalogInfo!=null){
        }
        bomanager.updateDB();
    }
}
