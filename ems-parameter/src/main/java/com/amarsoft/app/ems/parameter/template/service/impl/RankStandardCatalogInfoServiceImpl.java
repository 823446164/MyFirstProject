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
    public RankStandardCatalogInfoSaveRsq rankStandardCatalogInfoSave(@Valid RankStandardCatalogInfoSaveReq rankStandardCatalogInfoSaveReq) {
        rankStandardCatalogInfoSaveAction(rankStandardCatalogInfoSaveReq);
        RankStandardCatalogInfoSaveRsq response=new RankStandardCatalogInfoSaveRsq();
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
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (rankStandardCatalogInfo != null) {
            if (saveValidate(rankStandardCatalogInfo.getRankName(), rankStandardCatalogInfo.getRankStandard()) == false) {
                System.out.println("当前重复");
                throw new ALSException("900204");
            }
            else {
                System.out.println("可以保存");
                RankStandardCatalog rankStandardCatalog = bomanager.keyLoadBusinessObject(RankStandardCatalog.class,
                    rankStandardCatalogInfo.getSerialNo());
                if (rankStandardCatalog == null) {
                    rankStandardCatalog = new RankStandardCatalog();
                    if(rankStandardCatalogInfo.getSerialNo()==null) {
                    rankStandardCatalog.generateKey();}
                }
                BeanUtils.copyProperties(rankStandardCatalogInfo, rankStandardCatalog);
                bomanager.updateBusinessObject(rankStandardCatalog);
                
            }
        }
        bomanager.updateDB();
    }
    /**
     * 
     * Description: 保存之后的输出
     * 1、…<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     *
     * @param req
     * @return
     * @see
     */
    public RankStandardCatalogInfoSaveRsq getSRankStandardCatalogInfoSaveRsq(@Valid RankStandardCatalogInfoSaveReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
                  RankStandardCatalog ranCatalog=bomanager.keyLoadBusinessObject(RankStandardCatalog.class, req.getSerialNo());
                  RankStandardCatalogInfoSaveRsq response=new RankStandardCatalogInfoSaveRsq();
            if(ranCatalog!=null) {
                response.setBelongTeam(req.getBelongTeam());
                response.setSerialNo(req.getSerialNo());
                return response;
            }
        return null;
        
    }

    /**
     * 
     * Description: 校验职级＋职等是否在系统中已经存在
     * 1、…<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     *
     * @param rankName
     * @param rankStandard
     * @return
     * @see
     */
    public boolean saveValidate(String rankName, String rankStandard) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<RankStandardCatalog> rankStandardCatalogs = bomanager.loadBusinessObjects(RankStandardCatalog.class,
            "rankName=:rankName and rankStandard=:rankStandard", "rankName", rankName, "rankStandard", rankStandard);
        if ( rankStandardCatalogs.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }
}
