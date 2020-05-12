/*
 * 文件名：LabelInfoServiceImpl
 * 版权：Copyright by www.amarsoft.com
 * 描述：为LabelInfo模板提供方法
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LabelInfoService;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoSaveReq;
import com.amarsoft.app.ems.parameter.entity.LabelCatalog;
import com.amarsoft.app.ems.parameter.entity.LabelDescribe;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfo;


/**
 * 标签InfoService实现类
 * 
 * @author yrong
 */
@Slf4j
@Service
public class LabelInfoServiceImpl implements LabelInfoService {
    /**
     * 标签Info单记录查询
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public LabelInfoQueryRsp labelInfoQuery(@Valid LabelInfoQueryReq labelInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();

        BusinessObjectAggregate<BusinessObject> labelInfoQueryRspBoa = bomanager.selectBusinessObjectsBySql(
            "select LC.serialNo as serialNo,LC.labelName as labelName,LC.codeNo as codeNo,LC.labelStatus as labelStatus,LC.belongCataLog as belongCataLog,LC.rootNo as rootNo,LC.abilityType as abilityType,LC.labelDescribe as labelDescribe,LC.labelVersion as labelVersion,LC.inputUserId as inputUserId,LC.inputTime as inputTime,LC.inputOrgId as inputOrgId,LC.updateUserId as updateUserId,LC.updateTime as updateTime,LC.updateOrgId as updateOrgId,"
                                                                                                            + "LD.labelNo as labelNo,LD.labelLevel as labelLevel,LD.inputUserId as inputUserId,LD.inputTime as inputTime,LD.inputOrgId as inputOrgId,LD.updateUserId as updateUserId,LD.updateTime as updateTime,LD.updateOrgId as updateOrgId"
                                                                                                            + " from LabelCatalog LC,LabelDescribe LD"
                                                                                                            + " where 1=1 and LD.serialNo = :serialNo",
            "serialNo", labelInfoQueryReq.getSerialNo());
        List<BusinessObject> labelInfoQueryRspBoList = labelInfoQueryRspBoa.getBusinessObjects();
        if (labelInfoQueryRspBoList != null && labelInfoQueryRspBoList.size() > 0) {
            BusinessObject labelInfoQueryRspBo = labelInfoQueryRspBoList.get(0);
            LabelInfoQueryRsp labelInfo = new LabelInfoQueryRsp();
            LocalDateTime localDateTime = LocalDateTime.now();
            labelInfo.setSerialNo(labelInfoQueryRspBo.getString("SerialNo"));
            labelInfo.setLabelName(labelInfoQueryRspBo.getString("LabelName"));
            labelInfo.setCodeNo(labelInfoQueryRspBo.getString("CodeNo"));
            labelInfo.setLabelStatus(labelInfoQueryRspBo.getString("LabelStatus"));
            labelInfo.setBelongCataLog(labelInfoQueryRspBo.getString("BelongCataLog"));
            labelInfo.setRootNo(labelInfoQueryRspBo.getString("RootNo"));
            labelInfo.setAbilityType(labelInfoQueryRspBo.getString("AbilityType"));
            labelInfo.setLabelDescribe(labelInfoQueryRspBo.getString("LabelDescribe"));
            labelInfo.setLabelVersion(labelInfoQueryRspBo.getString("LabelVersion"));
            labelInfo.setLC_InputUserId(labelInfoQueryRspBo.getString("InputUserId"));
            labelInfo.setLC_InputTime(localDateTime);
            labelInfo.setLC_InputOrgId(labelInfoQueryRspBo.getString("InputOrgId"));
            labelInfo.setLC_UpdateUserId(labelInfoQueryRspBo.getString("UpdateUserId"));
            labelInfo.setLC_UpdateTime(localDateTime);
            labelInfo.setLC_UpdateOrgId(labelInfoQueryRspBo.getString("UpdateOrgId"));
            labelInfo.setLabelNo(labelInfoQueryRspBo.getString("LabelNo"));
            labelInfo.setLabelLevel(labelInfoQueryRspBo.getString("LabelLevel"));
            labelInfo.setLD_InputUserId(labelInfoQueryRspBo.getString("InputUserId"));
            labelInfo.setLD_InputTime(localDateTime);
            labelInfo.setLD_InputOrgId(labelInfoQueryRspBo.getString("InputOrgId"));
            labelInfo.setLD_UpdateUserId(labelInfoQueryRspBo.getString("UpdateUserId"));
            labelInfo.setLD_UpdateTime(localDateTime);
            labelInfo.setLD_UpdateOrgId(labelInfoQueryRspBo.getString("UpdateOrgId"));
            return labelInfo;
        }

        return null;
    }

    /**
     * 标签Info单记录保存
     * 
     * @param request
     * @return
     */
    @Override
    public void labelInfoSave(@Valid LabelInfoSaveReq labelInfoSaveReq) {
        labelInfoSaveAction(labelInfoSaveReq);
    }

    /**
     * 标签Info单记录保存
     * 
     * @param
     * @return
     */
    @Transactional
    public void labelInfoSaveAction(LabelInfo labelInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (labelInfo != null) {
            LabelCatalog labelCatalog = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelInfo.getSerialNo());
            if (labelCatalog == null) {
                labelCatalog = new LabelCatalog();
                labelCatalog.generateKey();
            }
            LocalDateTime localDateTime = LocalDateTime.now();
            labelCatalog.setLabelName(labelInfo.getLabelName());
            labelCatalog.setCodeNo(labelInfo.getCodeNo());
            labelCatalog.setLabelStatus(labelInfo.getLabelStatus());
            labelCatalog.setBelongCataLog(labelInfo.getBelongCataLog());
            labelCatalog.setRootNo(labelInfo.getRootNo());
            labelCatalog.setAbilityType(labelInfo.getAbilityType());
            labelCatalog.setLabelDescribe(labelInfo.getLabelDescribe());
            labelCatalog.setLabelVersion(labelInfo.getLabelVersion());
            labelCatalog.setInputUserId(labelInfo.getLC_InputUserId());
            labelCatalog.setInputTime(localDateTime);
            labelCatalog.setInputOrgId(labelInfo.getLC_InputOrgId());
            labelCatalog.setUpdateUserId(labelInfo.getLC_UpdateUserId());

            labelCatalog.setUpdateTime(localDateTime);
            labelCatalog.setUpdateOrgId(labelInfo.getLC_UpdateOrgId());
            labelCatalog.setLabelType(labelInfo.getLC_labelType());
            bomanager.updateBusinessObject(labelCatalog);

//            LabelDescribe labelDescribe = bomanager.loadBusinessObjects(LabelDescribe.class, "labelNo=:serialNo", "serialNo",
//                labelInfo.getSerialNo());
//             List<BusinessObject> loadBusinessObjects = bomanager.loadBusinessObjects(LabelDescribe.class, "labelNo=:serialNo", labelInfo.getSerialNo());
            List<LabelDescribe> labelDescribess = bomanager.loadBusinessObjects(LabelDescribe.class, "labelNo=:serialNo", "serialNo",
                labelInfo.getSerialNo());

            List<LabelDescribe> labelDescribes = new ArrayList<LabelDescribe>();
            if (!StringUtils.isEmpty(labelInfo.getMasterDescribe())) {
                LabelDescribe labelDescribe = new LabelDescribe();
                labelDescribe.generateKey();
                labelDescribe.setLabelLevel("精通");
                labelDescribe.setLevelDescribe(labelInfo.getMasterDescribe());
                labelDescribes.add(labelDescribe);
            }

            if ( !StringUtils.isEmpty(labelInfo.getSkilledDescribe())) {
                LabelDescribe labelDescribe = new LabelDescribe();
                labelDescribe.generateKey();
                labelDescribe.setLabelLevel("熟练使用");
                labelDescribe.setLevelDescribe(labelInfo.getSkilledDescribe());
                labelDescribes.add(labelDescribe);
            }
            if (!StringUtils.isEmpty(labelInfo.getFamiliarDescribe())) {
                LabelDescribe labelDescribe = new LabelDescribe();
                labelDescribe.generateKey();
                labelDescribe.setLabelLevel("熟悉");
                labelDescribe.setLevelDescribe(labelInfo.getFamiliarDescribe());
                labelDescribes.add(labelDescribe);
            }
            if (!StringUtils.isEmpty(labelInfo.getKnowDescribe())) {
                LabelDescribe labelDescribe = new LabelDescribe();
                labelDescribe.generateKey();
                labelDescribe.setLabelLevel("了解");
                labelDescribe.setLevelDescribe(labelInfo.getKnowDescribe());
                labelDescribes.add(labelDescribe);
            }
            if (!StringUtils.isEmpty(labelInfo.getExcellentDescribe())) {
                LabelDescribe labelDescribe = new LabelDescribe();
                labelDescribe.generateKey();
                labelDescribe.setLabelLevel("优秀");
                labelDescribe.setLevelDescribe(labelInfo.getExcellentDescribe());
                labelDescribes.add(labelDescribe);
            }
            if ( !StringUtils.isEmpty(labelInfo.getGoodDescribe())) {
                LabelDescribe labelDescribe = new LabelDescribe();
                labelDescribe.generateKey();
                labelDescribe.setLabelLevel("良好");
                labelDescribe.setLevelDescribe(labelInfo.getGoodDescribe());
                labelDescribes.add(labelDescribe);
            }
            if (null != labelInfo.getCommonlyDescribe()) {
                LabelDescribe labelDescribe = new LabelDescribe();
                labelDescribe.generateKey();
                labelDescribe.setLabelLevel("一般");
                labelDescribe.setLevelDescribe(labelInfo.getCommonlyDescribe());
                labelDescribes.add(labelDescribe);
            }

            for (LabelDescribe labelDescribeTemp : labelDescribes) {
                labelDescribeTemp.setLevelDescribe(labelDescribeTemp.getLevelDescribe());
                labelDescribeTemp.setLabelLevel(labelDescribeTemp.getLabelLevel());
                labelDescribeTemp.setLabelNo(labelInfo.getSerialNo());
                labelDescribeTemp.setInputUserId(labelInfo.getLC_InputUserId());
                bomanager.updateBusinessObject(labelDescribeTemp);
            }
        } 
        bomanager.updateDB();
    }
}
