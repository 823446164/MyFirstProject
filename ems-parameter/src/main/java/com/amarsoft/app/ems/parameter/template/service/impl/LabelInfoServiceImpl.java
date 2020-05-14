/*
 * 文件名：LabelInfoServiceImpl 版权：Copyright by www.amarsoft.com 描述：为LabelInfo模板提供方法 修改人：yrong
 * 修改时间：2020年5月11日 跟踪单号： 修改单号： 修改内容：标签详情查询、标签详情保存、标签生效、标签失效
 */
package com.amarsoft.app.ems.parameter.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LabelInfoService;
import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.aecd.system.constant.LabelStatus;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
     * 标签详情查询
     * 
     * @param
     * @return
     */
    @Override
    @Transactional
    public LabelInfoQueryRsp labelInfoQuery(@Valid LabelInfoQueryReq labelInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> labelInfoQueryRspBoa = bomanager.selectBusinessObjectsBySql(
            "select LC.serialNo as serialNo,LC.labelName as labelName,LC.codeNo as codeNo,LC.labelStatus as labelStatus,LC.belongCataLog as belongCataLog,LC.rootNo as rootNo,LC.abilityType as abilityType,LC.labelDescribe as labelDescribe,LC.labelVersion as labelVersion,LC.inputUserId as inputUserId,LC.inputTime as inputTime,LC.inputOrgId as inputOrgId,LC.updateUserId as updateUserId,LC.updateTime as updateTime,LC.updateOrgId as updateOrgId,"
                                                                                                            + "LD.levelDescribe as levelDescribe,LD.labelNo as labelNo,LD.labelLevel as labelLevel,LD.inputUserId as inputUserId,LD.inputTime as inputTime,LD.inputOrgId as inputOrgId,LD.updateUserId as updateUserId,LD.updateTime as updateTime,LD.updateOrgId as updateOrgId"
                                                                                                            + " from LabelCatalog LC,LabelDescribe LD"
                                                                                                            + " where LD.labelNo = LC.serialNo and LC.serialNo=:serialNo",
            "serialNo", labelInfoQueryReq.getSerialNo());
        List<BusinessObject> labelInfoQueryRspBoList = labelInfoQueryRspBoa.getBusinessObjects();
        LabelInfoQueryRsp labelInfo = new LabelInfoQueryRsp();
        if (labelInfoQueryRspBoList != null && labelInfoQueryRspBoList.size() > 0) {
            BusinessObject labelInfoQueryRspBo = labelInfoQueryRspBoList.get(0);
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
            labelInfo.setLC_InputTime(labelInfoQueryRspBo.getString("InputTime"));
            labelInfo.setLC_InputOrgId(labelInfoQueryRspBo.getString("InputOrgId"));
            labelInfo.setLC_UpdateUserId(labelInfoQueryRspBo.getString("UpdateUserId"));
            labelInfo.setLC_UpdateTime(labelInfoQueryRspBo.getString("UpdateTime"));
            labelInfo.setLC_UpdateOrgId(labelInfoQueryRspBo.getString("UpdateOrgId"));
            labelInfo.setLabelNo(labelInfoQueryRspBo.getString("LabelNo"));
            labelInfo.setLD_InputUserId(labelInfoQueryRspBo.getString("InputUserId"));
            labelInfo.setLD_InputTime(labelInfoQueryRspBo.getString("InputTime"));
            labelInfo.setLD_InputOrgId(labelInfoQueryRspBo.getString("InputOrgId"));
            labelInfo.setLD_UpdateUserId(labelInfoQueryRspBo.getString("UpdateUserId"));
            labelInfo.setLD_UpdateTime(labelInfoQueryRspBo.getString("UpdateTime"));
            labelInfo.setLD_UpdateOrgId(labelInfoQueryRspBo.getString("UpdateOrgId"));
        }
        for (int i = 0; i < labelInfoQueryRspBoList.size(); i++ ) {
            BusinessObject labelInfoQueryRspBo = labelInfoQueryRspBoList.get(i);
            System.out.println(labelInfoQueryRspBo.getString("levelDescribe"));
            if ("精通".equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setMasterDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
            if ("熟练使用".equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setSkilledDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
            if ("熟悉".equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setFamiliarDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
            if ("了解".equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setKnowDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
            if ("优秀".equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setExcellentDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
            if ("良好".equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setGoodDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
            if ("一般".equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setCommonlyDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
        }
        return labelInfo;
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

//    /**
//     * 标签Info单记录修改
//     * 
//     * @param request
//     * @return
//     */
//    @Override
//    public void labelInfoUpdate(@Valid LabelInfoSaveReq labelInfoSaveReq) {
//        labelInfoUpdate(labelInfoSaveReq);
//    }

    /**
     * 标签Info单记录新增
     * 
     * @param
     * @return
     */
    @Transactional
    public void labelInfoSaveAction(LabelInfo labelInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (labelInfo != null) {
            LabelCatalog labelCatalog = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelInfo.getSerialNo());
            LocalDateTime localDateTime = LocalDateTime.now();
            if (labelCatalog == null) {
                labelCatalog = new LabelCatalog();
                labelCatalog.generateKey();
                labelCatalog.setInputTime(localDateTime);
            }
            labelCatalog.setUpdateTime(localDateTime);
            labelCatalog.setLabelName(labelInfo.getLabelName());
            labelCatalog.setCodeNo(labelInfo.getCodeNo());
            labelCatalog.setLabelStatus(labelInfo.getLabelStatus());
            labelCatalog.setBelongCataLog(labelInfo.getBelongCataLog());
            labelCatalog.setRootNo(labelInfo.getRootNo());
            labelCatalog.setAbilityType(labelInfo.getAbilityType());
            labelCatalog.setLabelDescribe(labelInfo.getLabelDescribe());
            labelCatalog.setLabelVersion(labelInfo.getLabelVersion());
            labelCatalog.setInputUserId(labelInfo.getLC_InputUserId());
            labelCatalog.setInputOrgId(labelInfo.getLC_InputOrgId());
            labelCatalog.setLabelType(labelInfo.getLC_labelType());
            bomanager.updateBusinessObject(labelCatalog);
            List<LabelDescribe> labelDescribess = bomanager.loadBusinessObjects(LabelDescribe.class, "labelNo=:serialNo", "serialNo",
                labelInfo.getSerialNo());
            String inputTime = null;
            LocalDateTime ldt = null;
            // 空说明是新增信息，非空说明是修改
            if (labelDescribess != null) {
                inputTime = labelDescribess.get(0).getInputTime();
                DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FormatType.DateFormat.format);
                ldt = LocalDateTime.parse(inputTime, sdf);
                for (LabelDescribe labelDescribesTemp : labelDescribess) {
                    LabelDescribe labelDescribe = bomanager.keyLoadBusinessObject(LabelDescribe.class, labelDescribesTemp.getSerialNo());
                    bomanager.deleteBusinessObject(labelDescribe);
                }
            }
            List<LabelDescribe> labelDescribes = new ArrayList<LabelDescribe>();
            if (!StringUtils.isEmpty(labelInfo.getMasterDescribe())) {
                LabelDescribe labelDescribe = new LabelDescribe();
                labelDescribe.generateKey();
                labelDescribe.setLabelLevel("精通");
                labelDescribe.setLevelDescribe(labelInfo.getMasterDescribe());
                labelDescribes.add(labelDescribe);
            }

            if (!StringUtils.isEmpty(labelInfo.getSkilledDescribe())) {
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
            if (!StringUtils.isEmpty(labelInfo.getGoodDescribe())) {
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
                labelDescribeTemp.setInputUserId(GlobalShareContextHolder.getUserId());
                labelDescribeTemp.setInputTime(ldt);
                labelDescribeTemp.setInputOrgId(GlobalShareContextHolder.getOrgId());
                labelDescribeTemp.setUpdateTime(LocalDateTime.now());
                bomanager.updateBusinessObject(labelDescribeTemp);
            }
        }
        bomanager.updateDB();
    }

    /**
     * 标签生效
     * 
     * @param request
     * @return
     */
    @Override
    public void lableStatusOk(@Valid LabelInfoSaveReq labelInfoSaveReq) {
        lableStatusOkAction(labelInfoSaveReq);
    }

    /**
     * 标签生效
     * 
     * @param
     * @return
     */
    @Transactional
    public void lableStatusOkAction(LabelInfo labelInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (labelInfo != null) {
            LabelCatalog lc = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelInfo.getSerialNo());
            System.out.println(lc.getLabelStatus());
            // 根据标签状态码值 1为新增 2 为生效 3 为失效
            if (LabelStatus.New.id.equals(lc.getLabelStatus()) || LabelStatus.Disabled.id.equals(lc.getLabelStatus())) {
                lc.setLabelStatus(LabelStatus.Enabled.id);
                bomanager.updateBusinessObject(lc);
            }
            else {
                System.out.println("必须新增或失效状态的标签才可以置为生效");
            }
        }
        bomanager.updateDB();
    }

    /**
     * 标签失效
     * 
     * @param request
     * @return
     */
    @Override
    public void lableStatusNo(@Valid LabelInfoSaveReq labelInfoSaveReq) {
        lableStatusNoAction(labelInfoSaveReq);
    }

    /**
     * 标签失效
     * 
     * @param
     * @return
     */
    @Transactional
    public void lableStatusNoAction(LabelInfo labelInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (labelInfo != null) {
            LabelCatalog lc = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelInfo.getSerialNo());
            // 根据标签状态码值 1为新增 2 为生效 3 为失效
            if (LabelStatus.Enabled.id.equals(lc.getLabelStatus())) {
                lc.setLabelStatus(LabelStatus.Disabled.id);
                bomanager.updateBusinessObject(lc);
            }
            else {
                System.out.println("必须生效状态的标签才可以置为失效");
            }
        }
        bomanager.updateDB();
    }
}