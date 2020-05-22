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
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Service;

import com.amarsoft.aecd.employee.constant.ParentNo;
import com.amarsoft.aecd.parameter.constant.LabelType;
import com.amarsoft.aecd.system.constant.LabelStatus;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogInfoService;
import lombok.extern.slf4j.Slf4j;
import com.amarsoft.app.ems.parameter.entity.LabelCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.powertolabel.PowerToLabel;
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
        LabelCatalogTreeServiceImpl labelCatalogTreeServiceImpl = new LabelCatalogTreeServiceImpl();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LabelCatalog labelCatalog = bomanager.loadBusinessObject(LabelCatalog.class, "serialNo", labelCatalogInfoQueryReq.getSerialNo());
        if (labelCatalog != null) {
            LabelCatalogInfoQueryRsp labelCatalogInfo = new LabelCatalogInfoQueryRsp();
            labelCatalogInfo.setSerialNo(labelCatalog.getSerialNo());
            labelCatalogInfo.setLabelType(labelCatalog.getLabelType());
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
            labelCatalogInfo.setPower(labelCatalogTreeServiceImpl.powerToLabel());
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
        // 判断前端是否填写标签名称
        if (StringUtils.isEmpty(labelCatalogInfoSaveReq.getLabelName())) {
            throw new ALSException("EMS2012");
        }
        else {
            labelCatalogInfoSaveAction(labelCatalogInfoSaveReq);
        }
    }

    /**
     * 标签目录新增判重 标签目录新增时判断名称是否重复
     * 
     * @param labelCatalogInfo
     * @param bomanager
     */
    public boolean isAddRepeat(BusinessObjectManager bomanager, LabelCatalogInfo labelCatalogInfo) {
        List<LabelCatalog> labelCatalogs = bomanager.loadBusinessObjects(LabelCatalog.class, "labelName=:labelName", "labelName",
            labelCatalogInfo.getLabelName());
        if (CollectionUtils.isEmpty(labelCatalogs)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 标签目录新增判重 标签目录新增时判断名称是否重复
     * 
     * @param labelCatalogInfo
     * @param bomanager
     */
    public boolean isUpdateRepeat(BusinessObjectManager bomanager, LabelCatalogInfo labelCatalogInfo) {
        // 更新需要判断修改后的数据是否和自身以外的数据的名称是否重复
        List<LabelCatalog> labelCatalogs = bomanager.loadBusinessObjects(LabelCatalog.class,
            "labelName=:labelName  and serialNo <> :serialNo", "labelName", labelCatalogInfo.getLabelName(), "serialNo",
            labelCatalogInfo.getSerialNo());
        if (CollectionUtils.isEmpty(labelCatalogs)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 标签目录详情单记录保存/新增
     * 
     * @param labelCatalogInfo
     * @return void
     */
    @Transactional
    public void labelCatalogInfoSaveAction(LabelCatalogInfo labelCatalogInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 获得新增目录的父目录信息
        LabelCatalog parentLc = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelCatalogInfo.getParentNo());

        // 判断其父目录下是否有指标，如果有，则父目录不能拥有子目录
        List<LabelCatalog> labelCatalogs = bomanager.loadBusinessObjects(LabelCatalog.class, "parentNo=:parentNo and labelType=:labelType", "parentNo",
            labelCatalogInfo.getParentNo(),"labelType",LabelType._2.id);
        boolean add = true;
        for (LabelCatalog labelCatalogTemp : labelCatalogs) {
            if (LabelType._2.id.equals(labelCatalogTemp.getLabelType())) {
                add = false;
            }
        }
        if (false == add) {
            throw new ALSException("EMS2029");
        }
        else {

            if (ParentNo._1.name.equals(parentLc.getParentNo())) {
                throw new ALSException("EMS2016");
            }
            else {
                boolean a = true;
                if (labelCatalogInfo != null) {
                    LabelCatalog lc = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelCatalogInfo.getSerialNo());
                    // 如果lc不为空，则说明数据库中已有此数据，此次是更新操作
                    if (lc == null) {
                        // 判重
                        boolean isRepeat = isAddRepeat(bomanager, labelCatalogInfo);
                        if (false == isRepeat) {
                            a = isRepeat;
                            throw new ALSException("EMS2013", labelCatalogInfo.getLabelName());
                        }
                        else {
                            lc = new LabelCatalog();
                            lc.generateKey();
                            lc.setLabelType(labelCatalogInfo.getLabelType());
                            lc.setLabelStatus(LabelStatus.New.id);
                            lc.setInputTime(LocalDateTime.now());
                            lc.setInputOrgId(GlobalShareContextHolder.getOrgId());
                            lc.setInputUserId(GlobalShareContextHolder.getUserId());
                        }
                    }
                    // 更新判重
                    else {
                        boolean isRepeat = isUpdateRepeat(bomanager, labelCatalogInfo);
                        if (false == isRepeat) {
                            a = isRepeat;
                            throw new ALSException("EMS2013", labelCatalogInfo.getLabelName());
                        }
                    }
                    // 如果未抛过异常，则说明没有问题
                    if (true == a) {
                        lc.setLabelName(labelCatalogInfo.getLabelName());
                        lc.setCatalogRemark(labelCatalogInfo.getCatalogRemark());
                        lc.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
                        lc.setUpdateTime(LocalDateTime.now());
                        lc.setUpdateUserId(GlobalShareContextHolder.getUserId());
                        lc.setParentNo(labelCatalogInfo.getParentNo());
                        lc.setRootNo(parentLc.getRootNo());
                        bomanager.updateBusinessObject(lc);
                    }
                }
                bomanager.updateDB();
            }
        }
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
        String labelName = StringUtils.isEmpty(
            labelByLabelCatalogQueryReq.getLabelName()) ? "%" : labelByLabelCatalogQueryReq.getLabelName() + "%";
        BusinessObjectAggregate<BusinessObject> labelBelongCatalogQueryRspBoa = bomanager.selectBusinessObjectsBySql(
            "select LC.serialNo as serialNo,LC.labelName as labelName,LC.codeNo as codeNo,LC.labelStatus as labelStatus,LC.belongCataLog as belongCataLog,"
            + "LC.rootNo as rootNo,LC.parentNo as parentNo, LC.labelType as labelType, "
            + "LC.abilityType as abilityType,LC.labelDescribe as labelDescribe,LC.labelVersion as labelVersion from LabelCatalog LC"
            + " where LC.parentNo =:serialNo and labelName like :labelName",
            "serialNo", labelByLabelCatalogQueryReq.getSerialNo(), "labelName", labelName);
        List<BusinessObject> labelBelongCatalogQueryRspBoList = labelBelongCatalogQueryRspBoa.getBusinessObjects();
        if (!CollectionUtils.isEmpty(labelBelongCatalogQueryRspBoList)) {
            List<LabelCatalogInfo> lableCatalogInfoLists = new ArrayList<LabelCatalogInfo>();
            for (int i = 0; i < labelBelongCatalogQueryRspBoList.size(); i++ ) {
                BusinessObject labelBelongCatalogQueryRspBo = labelBelongCatalogQueryRspBoList.get(i);
                LabelByLabelCatalogQueryRsp lableCatalogInfo = new LabelByLabelCatalogQueryRsp();
                lableCatalogInfo.setLabelName(labelBelongCatalogQueryRspBo.getString("LabelName"));
                lableCatalogInfo.setSerialNo(labelBelongCatalogQueryRspBo.getString("SerialNo"));
                lableCatalogInfo.setRootNo(labelBelongCatalogQueryRspBo.getString("RootNo"));
                lableCatalogInfo.setParentNo(labelBelongCatalogQueryRspBo.getString("ParentNo"));
                lableCatalogInfo.setCatalogRemark(labelBelongCatalogQueryRspBo.getString("CatalogRemark"));
                lableCatalogInfo.setLabelVersion(labelBelongCatalogQueryRspBo.getString("LabelVersion"));
                lableCatalogInfo.setLabelType(labelBelongCatalogQueryRspBo.getString("LabelType"));
                lableCatalogInfo.setLabelStatus(labelBelongCatalogQueryRspBo.getString("LabelStatus"));
                lableCatalogInfoLists.add(lableCatalogInfo);
            }
            lableCatalogInfoQueryRsp.setLableCatalogInfos(lableCatalogInfoLists);
        }
        return lableCatalogInfoQueryRsp;
    }

}