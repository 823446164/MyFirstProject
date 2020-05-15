/*
 * 文件名：LableCatalogInfoServiceImpl 
 * 版权：Copyright by www.amarsoft.com 
 * 描述：为LableCatalogInfo模板提供方法
 * 修改人：yrong 
 * 修改时间：2020年5月11日 
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：新增标签目录详情查询，选中目录查询标签，标签目录保存
 */
package com.amarsoft.app.ems.parameter.template.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogInfoService;
import lombok.extern.slf4j.Slf4j;
import com.amarsoft.app.ems.parameter.entity.LabelCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelByLabelCatalogQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelByLabelCatalogQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfo;


/**
 * 标签目录详情Service实现类
 * 
 * @author yrong
 */
@Slf4j
@Service
public class LabelCatalogInfoServiceImpl implements LabelCatalogInfoService {
    /**
     * 标签目录详情单记录查询
     * 
     * @param labelCatalogInfoQueryReq
     * @return LabelCatalogInfoQueryRsp
     */
    @Override
    @Transactional
    public LabelCatalogInfoQueryRsp labelCatalogInfoQuery(@Valid LabelCatalogInfoQueryReq labelCatalogInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LabelCatalog labelCatalog = bomanager.loadBusinessObject(LabelCatalog.class, "serialNo", labelCatalogInfoQueryReq.getSerialNo());
        if (labelCatalog != null) {
            LabelCatalogInfoQueryRsp labelCatalogInfo = new LabelCatalogInfoQueryRsp();
            labelCatalogInfo.setSerialNo(labelCatalog.getSerialNo());
            labelCatalogInfo.setLabelName(labelCatalog.getLabelName());
            labelCatalogInfo.setParentNo(labelCatalog.getParentNo());
            labelCatalogInfo.setCatalogRemark(labelCatalog.getCatalogRemark());
            labelCatalogInfo.setLabelVersion(labelCatalog.getLabelVersion());
            labelCatalogInfo.setInputUserId(labelCatalog.getInputUserId());
            labelCatalogInfo.setInputTime(labelCatalog.getInputTime());
            labelCatalogInfo.setInputOrgId(labelCatalog.getInputOrgId());
            labelCatalogInfo.setUpdateUserId(labelCatalog.getUpdateUserId());
            labelCatalogInfo.setUpdateTime(labelCatalog.getUpdateTime());
            labelCatalogInfo.setUpdateOrgId(labelCatalog.getUpdateOrgId());
            labelCatalogInfo.setRootNo(labelCatalog.getRootNo());
            return labelCatalogInfo;
        }
        return null;
    }

    /**
     * 标签目录详情单记录保存
     * 
     * @param labelCatalogInfoSaveReq
     * @return void
     */
    @Override
    public void labelCatalogInfoSave(@Valid LabelCatalogInfoSaveReq labelCatalogInfoSaveReq) {
        labelCatalogInfoSaveAction(labelCatalogInfoSaveReq);
    }

    /**
     * 标签目录详情单记录保存
     * 
     * @param labelCatalogInfo
     * @return void
     */
    @Transactional
    public void labelCatalogInfoSaveAction(LabelCatalogInfo labelCatalogInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (labelCatalogInfo != null) {
            LabelCatalog lc = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelCatalogInfo.getSerialNo());
            if (lc == null) {
                lc = new LabelCatalog();
                lc.generateKey();
                lc.setInputTime(LocalDateTime.now());
                lc.setInputOrgId(GlobalShareContextHolder.getOrgId());
                lc.setInputUserId(GlobalShareContextHolder.getUserId());
            }
            BeanUtils.copyProperties(labelCatalogInfo, lc);
            lc.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
            lc.setUpdateTime(LocalDateTime.now());
            lc.setUpdateUserId(GlobalShareContextHolder.getUserId());
            //TODO 放入父目录parentNo           
            bomanager.updateBusinessObject(lc);
        }
        bomanager.updateDB();
    }

    /**
     * 根据当前目录，展示所有属于该目录的标签
     * 
     * @param labelByLabelCatalogQueryReq
     * @return LabelByLabelCatalogQueryRsp
     */
    @Override
    @Transactional
    public LabelByLabelCatalogQueryRsp labelBelongCatalogQuery(@Valid LabelByLabelCatalogQueryReq labelByLabelCatalogQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LabelByLabelCatalogQueryRsp lableCatalogInfoQueryRsp = new LabelByLabelCatalogQueryRsp();
        BusinessObjectAggregate<BusinessObject> labelBelongCatalogQueryRspBoa = bomanager.selectBusinessObjectsBySql(
            "select LC.serialNo as serialNo,LC.labelName as labelName,LC.belongCataLog as belongCataLog from LabelCatalog LC where LC.belongCataLog =:serialNo",
            "serialNo", labelByLabelCatalogQueryReq.getSerialNo());
        List<BusinessObject> labelBelongCatalogQueryRspBoList = labelBelongCatalogQueryRspBoa.getBusinessObjects();
        if (!CollectionUtils.isEmpty(labelBelongCatalogQueryRspBoList)) {
            List<LabelCatalogInfo> lableCatalogInfoLists = new ArrayList<LabelCatalogInfo>();
            for (int i = 0; i < labelBelongCatalogQueryRspBoList.size(); i++ ) {
                BusinessObject labelBelongCatalogQueryRspBo = labelBelongCatalogQueryRspBoList.get(i);
                LabelByLabelCatalogQueryRsp lableCatalogInof = new LabelByLabelCatalogQueryRsp();
                lableCatalogInof.setLabelName(labelBelongCatalogQueryRspBo.getString("LabelName"));
                lableCatalogInof.setSerialNo(labelBelongCatalogQueryRspBo.getString("SerialNo"));
                lableCatalogInfoLists.add(lableCatalogInof);
            }
            lableCatalogInfoQueryRsp.setLableCatalogInfos(lableCatalogInfoLists);
        }
        return lableCatalogInfoQueryRsp;
    }   
}