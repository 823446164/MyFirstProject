package com.amarsoft.app.ems.parameter.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LableCatalogInfoService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

import com.amarsoft.app.ems.parameter.entity.LableCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfo;


/**
 * 标签目录详情Service实现类
 * 
 * @author yrong
 */
@Slf4j
@Service
public class LableCatalogInfoServiceImpl implements LableCatalogInfoService {
    /** 
     * 标签目录详情单记录查询
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public LableCatalogInfoQueryRsp lableCatalogInfoQuery(@Valid LableCatalogInfoQueryReq lableCatalogInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();

        LableCatalog lableCatalog = bomanager.loadBusinessObject(LableCatalog.class, "serialNo", lableCatalogInfoQueryReq.getSerialNo());
        if (lableCatalog != null) {
            LableCatalogInfoQueryRsp lableCatalogInfo = new LableCatalogInfoQueryRsp();
            lableCatalogInfo.setSerialNo(lableCatalog.getSerialNo());
            lableCatalogInfo.setLabelName(lableCatalog.getLabelName());
            lableCatalogInfo.setParentNo(lableCatalog.getParentNo());
            lableCatalogInfo.setCatalogRemark(lableCatalog.getCatalogRemark());
            lableCatalogInfo.setAuthor(lableCatalog.getAuthor());
            lableCatalogInfo.setLabelVersion(lableCatalog.getLabelVersion());
            lableCatalogInfo.setInputUserId(lableCatalog.getInputUserId());
            lableCatalogInfo.setInputTime(lableCatalog.getInputTime());
            lableCatalogInfo.setInputOrgId(lableCatalog.getInputOrgId());
            lableCatalogInfo.setUpdateUserId(lableCatalog.getUpdateUserId());
            lableCatalogInfo.setUpdateTime(lableCatalog.getUpdateTime());
            lableCatalogInfo.setUpdateOrgId(lableCatalog.getUpdateOrgId());
            return lableCatalogInfo;
        }

        return null;
    }

    /**
     * 标签目录或标签保存
     * 
     * @param request
     * @return
     */
    @Override
    public void lableCatalogInfoSave(@Valid LableCatalogInfoSaveReq lableCatalogInfoSaveReq) {
        lableCatalogInfoSaveAction(lableCatalogInfoSaveReq);

    }

    /**
     * 标签目录保存
     *  
     * @param 
     * @return
     */
    @Transactional
    public void lableCatalogInfoSaveAction(LableCatalogInfo lableCatalogInfo) {       
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (lableCatalogInfo != null) {
            LableCatalog lc = bomanager.keyLoadBusinessObject(LableCatalog.class, lableCatalogInfo.getSerialNo());
            if (lc == null) {
                lc = new LableCatalog();
                    lc.generateKey();
            }
            BeanUtils.copyProperties(lableCatalogInfo, lc);
//            lc.setSerialNo(lableCatalogInfoSaveReq.getSerialNo());
//            lc.setLabelName(lableCatalogInfoSaveReq.getLabelName());
//            lc.setParentNo(lableCatalogInfoSaveReq.getParentNo());
//            lc.setCatalogRemark(lableCatalogInfoSaveReq.getCatalogRemark());
            bomanager.updateBusinessObject(lc);           
        }        
        bomanager.updateDB(); 
    }
}
