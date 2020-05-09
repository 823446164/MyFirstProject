/*
 * 文件名：LableCatalogInfoServiceImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：为LableCatalogInfo模板提供方法
 * 修改人：yrong
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新增标签目录详情查询，选中目录查询标签，标签目录保存
 */
package com.amarsoft.app.ems.parameter.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.parameter.template.service.LableCatalogInfoService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

import com.amarsoft.app.ems.parameter.entity.LableCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfo;

@Slf4j
@Service
public class LableCatalogInfoServiceImpl implements LableCatalogInfoService {
    /** 
     * 标签目录详情查询
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
     * 选中目录查询标签
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public LableCatalogInfoQueryRsp labelBelongCatalogQuery(@Valid LableCatalogInfoQueryReq lableCatalogInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LableCatalogInfoQueryRsp lableCatalogInfoQueryRsp = new LableCatalogInfoQueryRsp();
        System.out.println(lableCatalogInfoQueryReq.getLabelName());
        BusinessObjectAggregate<BusinessObject> labelBelongCatalogQueryRspBoa = bomanager.selectBusinessObjectsBySql(
            "select LC.serialNo as serialNo,LC.labelName as labelName,LC.belongCataLog as belongCataLog from LableCatalog LC where LC.belongCataLog =:labelName",
            "labelName",
             lableCatalogInfoQueryReq.getLabelName());
        System.out.println("1111");
        List<BusinessObject> labelBelongCatalogQueryRspBoList = labelBelongCatalogQueryRspBoa.getBusinessObjects();
        System.out.println("2222");
        if (labelBelongCatalogQueryRspBoList != null) {
            List<LableCatalogInfo> lableCatalogInfoLists = new ArrayList<LableCatalogInfo>();
            for(int i =0 ;i<labelBelongCatalogQueryRspBoList.size();i++) {
                BusinessObject labelBelongCatalogQueryRspBo = labelBelongCatalogQueryRspBoList.get(i);
               
                LableCatalogInfoQueryRsp lableCatalogInof = new LableCatalogInfoQueryRsp();
                lableCatalogInof.setLabelName(labelBelongCatalogQueryRspBo.getString("LabelName"));
                lableCatalogInfoLists.add(lableCatalogInof);
            }
            lableCatalogInfoQueryRsp.setLableCatalogInfos(lableCatalogInfoLists);
        }
        return lableCatalogInfoQueryRsp;
    } 
    
    /**
     * 标签目录保存
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
                    lableCatalogInfo.getSerialNo();
                    
            }
            BeanUtils.copyProperties(lableCatalogInfo, lc);
            bomanager.updateBusinessObject(lc);           
        }        
        bomanager.updateDB(); 
    }
}
