package com.amarsoft.app.ems.parameter.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogInfoService;
import com.amarsoft.app.ems.parameter.entity.LabelCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfo;

/**
 * 标签目录详情Service实现类
 * @author ylgao
 */
@Slf4j
@Service
public class LabelCatalogInfoServiceImpl implements LabelCatalogInfoService{
    /**
     * 标签目录详情单记录查询
     * @param request
     * @return
     */
    @Override
    @Transactional
    public LabelCatalogInfoQueryRsp labelCatalogInfoQuery(@Valid LabelCatalogInfoQueryReq labelCatalogInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        LabelCatalog labelCatalog = bomanager.loadBusinessObject(LabelCatalog.class,"serialNo",labelCatalogInfoQueryReq.getSerialNo());
        if(labelCatalog!=null){
            LocalDateTime localDateTime = LocalDateTime.now();
            LabelCatalogInfoQueryRsp labelCatalogInfo = new LabelCatalogInfoQueryRsp();
            labelCatalogInfo.setSerialNo(labelCatalog.getSerialNo());
            labelCatalogInfo.setLabelName(labelCatalog.getLabelName());
            labelCatalogInfo.setParentNo(labelCatalog.getParentNo());
            labelCatalogInfo.setCatalogRemark(labelCatalog.getCatalogRemark());
            labelCatalogInfo.setAuthor(labelCatalog.getAuthor());
            labelCatalogInfo.setLabelVersion(labelCatalog.getLabelVersion());
            labelCatalogInfo.setInputUserId(labelCatalog.getInputUserId());
            labelCatalogInfo.setInputTime(localDateTime);
            labelCatalogInfo.setInputOrgId(labelCatalog.getInputOrgId());
            labelCatalogInfo.setUpdateUserId(labelCatalog.getUpdateUserId());
            labelCatalogInfo.setUpdateTime(localDateTime);
            labelCatalogInfo.setUpdateOrgId(labelCatalog.getUpdateOrgId());
            return labelCatalogInfo;
        }
        return null;
    }

    /**
     * 标签目录详情单记录保存
     * @param request
     * @return
     */
    @Override
    public void labelCatalogInfoSave(@Valid LabelCatalogInfoSaveReq labelCatalogInfoSaveReq) {
        labelCatalogInfoSaveAction(labelCatalogInfoSaveReq);
    }
    /**
     * 标签目录详情单记录保存
     * @param
     * @return
     */
    @Transactional
    public void labelCatalogInfoSaveAction(LabelCatalogInfo labelCatalogInfo){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(labelCatalogInfo!=null){
        }
        bomanager.updateDB();
    }
}
