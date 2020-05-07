package com.amarsoft.app.ems.parameter.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.RankStandardItemsInfoService;
import com.amarsoft.app.ems.parameter.entity.RankStandardItems;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfo;

/**
 * 技能详情Service实现类
 * @author ylgao
 */
@Slf4j
@Service
public class RankStandardItemsInfoServiceImpl implements RankStandardItemsInfoService{
    /**
     * 技能详情单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public RankStandardItemsInfoQueryRsp rankStandardItemsInfoQuery(@Valid RankStandardItemsInfoQueryReq rankStandardItemsInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        RankStandardItems rankStandardItems = bomanager.loadBusinessObject(RankStandardItems.class,"serialNo",rankStandardItemsInfoQueryReq.getSerialNo(),"rankNo",rankStandardItemsInfoQueryReq.getRankNo());
        if(rankStandardItems!=null){
            RankStandardItemsInfoQueryRsp rankStandardItemsInfo = new RankStandardItemsInfoQueryRsp();
            rankStandardItemsInfo.setSerialNo(rankStandardItems.getSerialNo());
            rankStandardItemsInfo.setRankNo(rankStandardItems.getRankNo());
            rankStandardItemsInfo.setLabelName(rankStandardItems.getLabelName());
            rankStandardItemsInfo.setLabelLevel(rankStandardItems.getLabelLevel());
            rankStandardItemsInfo.setInputUserId(rankStandardItems.getInputUserId());
            rankStandardItemsInfo.setInputTime(rankStandardItems.getInputTime());
            rankStandardItemsInfo.setInputOrgId(rankStandardItems.getInputOrgId());
            rankStandardItemsInfo.setUpdateUserId(rankStandardItems.getUpdateUserId());
            rankStandardItemsInfo.setUpdateTime(rankStandardItems.getUpdateTime());
            rankStandardItemsInfo.setUpdateOrgId(rankStandardItems.getUpdateOrgId());
            return rankStandardItemsInfo;
        }

        return null;
    }

    /**
     * 技能详情单记录保存
     * @param request
     * @return
     */
    @Override
    public void rankStandardItemsInfoSave(@Valid RankStandardItemsInfoSaveReq rankStandardItemsInfoSaveReq) {
        rankStandardItemsInfoSaveAction(rankStandardItemsInfoSaveReq);
    }
    /**
     * 技能详情单记录保存
     * @param
     * @return
     */
    @Transactional
    public void rankStandardItemsInfoSaveAction(RankStandardItemsInfo rankStandardItemsInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(rankStandardItemsInfo!=null){
        }
        bomanager.updateDB();
    }
}
