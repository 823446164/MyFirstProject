/*
 * 文件名：LabelInfoServiceImpl 
 * 版权：Copyright by www.amarsoft.com 
 * 描述：为LabelInfo模板提供方法 修改人：yrong
 * 修改时间：2020年5月11日 
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：标签详情查询、标签详情保存、标签生效、标签失效
 */
package com.amarsoft.app.ems.parameter.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LabelInfoService;
import com.amarsoft.aecd.employee.constant.MasteryOne;
import com.amarsoft.aecd.employee.constant.MasteryThree;
import com.amarsoft.aecd.parameter.constant.ButtonType;
import com.amarsoft.aecd.parameter.constant.LabelType;
import com.amarsoft.aecd.system.constant.LabelStatus;
import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoRepeatReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoRepeatRsp;
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
     * @param labelInfoQueryReq
     * @return LabelInfoQueryRsp
     */
    @Override
    @Transactional
    public LabelInfoQueryRsp labelInfoQuery(@Valid LabelInfoQueryReq labelInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<BusinessObject> labelInfoQueryRspBoa = bomanager.selectBusinessObjectsBySql(
            "select LC.serialNo as serialNo,LC.parentNo as parentNo,LC.labelType as labelType,LC.labelType as labelType,LC.labelName as labelName,LC.codeNo as codeNo,"
            + "LC.labelStatus as labelStatus,LC.belongCataLog as belongCataLog,LC.rootNo as rootNo,LC.abilityType as abilityType,LC.labelDescribe as labelDescribe,LC.labelVersion as labelVersion,"
             + "LC.inputUserId as inputUserId,LC.inputTime as inputTime,LC.inputOrgId as inputOrgId,LC.updateUserId as updateUserId,LC.updateTime as updateTime,LC.updateOrgId as updateOrgId,"
             + "LD.levelDescribe as levelDescribe,LD.labelNo as labelNo,LD.labelLevel as labelLevel,LD.inputUserId as inputUserId,LD.inputTime as inputTime,LD.inputOrgId as inputOrgId,"
             + "LD.updateUserId as updateUserId,LD.updateTime as updateTime,LD.updateOrgId as updateOrgId"
             + " from LabelCatalog LC,LabelDescribe LD"
             + " where LD.labelNo = LC.serialNo and LC.serialNo=:serialNo",
            "serialNo", labelInfoQueryReq.getSerialNo());
        List<BusinessObject> labelInfoQueryRspBoList = labelInfoQueryRspBoa.getBusinessObjects();
        LabelInfoQueryRsp labelInfo = new LabelInfoQueryRsp();
        if (labelInfoQueryRspBoList != null && labelInfoQueryRspBoList.size() > 0) {
            apply(labelInfo, labelInfoQueryRspBoList);
        }
        for (int i = 0; i < labelInfoQueryRspBoList.size(); i++ ) {
            BusinessObject labelInfoQueryRspBo = labelInfoQueryRspBoList.get(i);
            // MasteryOne枚举类： _1("1","了解"), _2("2","熟悉"),_3("3","熟练"),_4("4","精通")
            if (MasteryOne._4.name.equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setMasterDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
            if (MasteryOne._3.name.equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setSkilledDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
            if (MasteryOne._2.name.equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setFamiliarDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
            if (MasteryOne._1.name.equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setKnowDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
            // MasteryThree枚举类：_1("1","一般"), _2("2","良好"),_3("3","优秀")
            if (MasteryThree._3.name.equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setExcellentDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
            if (MasteryThree._2.name.equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setGoodDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
            if (MasteryThree._1.name.equals(labelInfoQueryRspBo.getString("labelLevel"))) {
                labelInfo.setCommonlyDescribe(labelInfoQueryRspBo.getString("levelDescribe"));
            }
        }
        return labelInfo;
    }

    /**
     * 把请求到的数据转化为响应实体类
     * 
     * @param labelInfo
     * @param labelInfoQueryRspBoList
     * @return labelInfo
     */
    public LabelInfo apply(LabelInfoQueryRsp labelInfo, List<BusinessObject> labelInfoQueryRspBoList) {
        BusinessObject labelInfoQueryRspBo = labelInfoQueryRspBoList.get(0);
        labelInfo.setSerialNo(labelInfoQueryRspBo.getString("SerialNo"));
        labelInfo.setLabelName(labelInfoQueryRspBo.getString("LabelName"));
        labelInfo.setCodeNo(labelInfoQueryRspBo.getString("CodeNo"));
        labelInfo.setLabelStatus(labelInfoQueryRspBo.getString("LabelStatus"));
        labelInfo.setParentNo(labelInfoQueryRspBo.getString("ParentNo"));
        labelInfo.setLabelType(labelInfoQueryRspBo.getString("LabelType"));
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
        return labelInfo;
    }

    /**
     * 标签详情新增,修改
     * 
     * @param labelInfoSaveReq
     */
    @Override
    public void labelInfoSave(@Valid LabelInfoSaveReq labelInfoSaveReq) {
        labelInfoSaveAction(labelInfoSaveReq);
    }

    /**
     * 标签详情新增、修改、复制，指标详情新增、修改、复制
     * 
     * @param labelInfo
     */
    public void labelInfoSaveAction(LabelInfo labelInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (ButtonType._3.name.equals(labelInfo.getButtonType())) {
                // 保留发过来的serialNo
                String indexSerialNo = labelInfo.getSerialNo();
                String indexName = labelInfo.getLabelName();
                String indexCodeNo = labelInfo.getCodeNo();
                // 在label_Catalog表中查出指标数据
                LabelCatalog labelCatalog = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelInfo.getSerialNo());
                // 复制指标
                BeanUtils.copyProperties(labelCatalog, labelInfo);
                labelInfo.setSerialNo(null);
                labelInfo.setLabelName(indexName + "_副本");
                labelInfo.setCodeNo(indexCodeNo);
                String labelInfoSave = labelInfoSave(labelInfo);
                // 查询该指标下的标签
                List<LabelCatalog> labelCatalogs = bomanager.loadBusinessObjects(LabelCatalog.class, "parentNo=:serialNo", "serialNo",
                    indexSerialNo);
                // 复制标签
                for (LabelCatalog labelCatalogTemp : labelCatalogs) {
                    // 要复制的标签的serialNo
                    String copyLabelSerialNo = labelCatalogTemp.getSerialNo();
                    LabelInfo labelInfo1 = new LabelInfo();
                    BeanUtils.copyProperties(labelCatalogTemp, labelInfo1);
                    labelInfo1.setLabelName(labelCatalogTemp.getLabelName() + "_副本");
                    labelInfo1.setSerialNo(null);
                    labelInfo1.setParentNo(labelInfoSave);
                    labelInfo1.setButtonType(labelInfo.getButtonType());
                    // 新生成的标签的serialNo
                    String labelSerialNo = labelInfoSave(labelInfo1);
                    // 为复制的标签复制标签能力描述
                    copyLabelDescribe(labelCatalogTemp, labelSerialNo, copyLabelSerialNo);
                }            
        }
        else {
            labelInfoSave(labelInfo);
        }
    }

    /**
     * 复制新指标下标签
     * 
     * @param labelInfoSaveReq
     */
    public void copyLabelDescribe(LabelCatalog labelCatalogTemp, String labelSerialNo, String copyLabelSerialNo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<LabelDescribe> labelDescribes = bomanager.loadBusinessObjects(LabelDescribe.class, "labelNo=:serialNo", "serialNo",
            copyLabelSerialNo);
        for (LabelDescribe labelDescribeTemp : labelDescribes) {
            LabelDescribe labelDescribe = new LabelDescribe();
            labelDescribe.generateKey();
            labelDescribe.setLabelNo(labelSerialNo);
            labelDescribe.setLabelLevel(labelDescribeTemp.getLabelLevel());
            labelDescribe.setLevelDescribe(labelDescribeTemp.getLevelDescribe());
            labelDescribe.setInputUserId(labelDescribeTemp.getInputUserId());
            labelDescribe.setInputTime(LocalDateTime.now());
            labelDescribe.setInputOrgId(labelDescribeTemp.getInputOrgId());
            bomanager.updateBusinessObject(labelDescribe);
            bomanager.updateDB();
        }

    }

    /**
     * 标签详情新增,修改action 把要新增或修改的标签数据跟新进LABEL_Describe表和Label_Catalog表
     * 
     * @param labelInfo
     */
    @Transactional
    public String labelInfoSave(LabelInfo labelInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (labelInfo != null) {
            LabelCatalog labelCatalog = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelInfo.getSerialNo());
            LocalDateTime localDateTime = LocalDateTime.now();
            if (labelCatalog == null) {
                labelCatalog = new LabelCatalog();
                labelCatalog.generateKey();
                labelCatalog.setInputTime(localDateTime);
                labelCatalog.setInputUserId(GlobalShareContextHolder.getUserId());
                labelCatalog.setInputOrgId(GlobalShareContextHolder.getOrgId());
                labelCatalog.setLabelStatus(LabelStatus.New.id);
                labelCatalog.setLabelType(labelInfo.getLabelType());
            }
            labelInfo.setLabelNo(labelCatalog.getSerialNo());
            labelCatalog.setUpdateTime(localDateTime);
            labelCatalog.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
            labelCatalog.setUpdateUserId(GlobalShareContextHolder.getUserId());
            labelCatalog.setLabelName(labelInfo.getLabelName());
            labelCatalog.setCodeNo(labelInfo.getCodeNo());
            labelCatalog.setParentNo(labelInfo.getParentNo());
            labelCatalog.setRootNo(labelInfo.getRootNo());
            labelCatalog.setAbilityType(labelInfo.getAbilityType());
            labelCatalog.setLabelDescribe(labelInfo.getLabelDescribe());
            labelCatalog.setLabelVersion(labelInfo.getLabelVersion());
            bomanager.updateBusinessObject(labelCatalog);
            bomanager.updateDB();
            // 调用labelDescribeSave方法，把数据存放进LABEL_Describe表
            labelDescribeSave(bomanager, labelInfo, labelCatalog);
            return labelCatalog.getSerialNo();
        }
        return null;
    }

    /**
     * 标签Info单记录新增,修改
     * 
     * @param labelInfo
     * @param bomanager
     */
    public void labelDescribeSave(BusinessObjectManager bomanager, LabelInfo labelInfo, LabelCatalog labelCatalog) {
        List<LabelDescribe> labelDescribess = bomanager.loadBusinessObjects(LabelDescribe.class, "labelNo=:serialNo", "serialNo",
            labelInfo.getSerialNo());
        if (ButtonType._3.name.equals(labelInfo.getButtonType())) {}
        // 空说明是新增信息，非空说明是修改
        else if (!CollectionUtils.isEmpty(labelDescribess)) {
            for (LabelDescribe labelDescribesTemp : labelDescribess) {
                LabelDescribe labelDescribe = bomanager.keyLoadBusinessObject(LabelDescribe.class, labelDescribesTemp.getSerialNo());
                bomanager.deleteBusinessObject(labelDescribe);
            }
        }
        labelDescribeSaveAction(bomanager, labelInfo, labelCatalog);
    }

    /**
     * 标签能力描述保存入数据库
     * 
     * @param labelInfo
     * @param bomanager
     */
    public void labelDescribeSaveAction(BusinessObjectManager bomanager, LabelInfo labelInfo, LabelCatalog labelCatalog) {
        List<LabelDescribe> labelDescribes = new ArrayList<LabelDescribe>();
        if (!StringUtils.isEmpty(labelInfo.getMasterDescribe())) {
            LabelDescribe labelDescribe = new LabelDescribe();
            labelDescribe.generateKey();
            labelDescribe.setLabelLevel(MasteryOne._4.name);
            labelDescribe.setLevelDescribe(labelInfo.getMasterDescribe());
            labelDescribes.add(labelDescribe);
        }

        if (!StringUtils.isEmpty(labelInfo.getSkilledDescribe())) {
            LabelDescribe labelDescribe = new LabelDescribe();
            labelDescribe.generateKey();
            labelDescribe.setLabelLevel(MasteryOne._3.name);
            labelDescribe.setLevelDescribe(labelInfo.getSkilledDescribe());
            labelDescribes.add(labelDescribe);
        }
        if (!StringUtils.isEmpty(labelInfo.getFamiliarDescribe())) {
            LabelDescribe labelDescribe = new LabelDescribe();
            labelDescribe.generateKey();
            labelDescribe.setLabelLevel(MasteryOne._2.name);
            labelDescribe.setLevelDescribe(labelInfo.getFamiliarDescribe());
            labelDescribes.add(labelDescribe);
        }
        if (!StringUtils.isEmpty(labelInfo.getKnowDescribe())) {
            LabelDescribe labelDescribe = new LabelDescribe();
            labelDescribe.generateKey();
            labelDescribe.setLabelLevel(MasteryOne._1.name);
            labelDescribe.setLevelDescribe(labelInfo.getKnowDescribe());
            labelDescribes.add(labelDescribe);
        }
        if (!StringUtils.isEmpty(labelInfo.getExcellentDescribe())) {
            LabelDescribe labelDescribe = new LabelDescribe();
            labelDescribe.generateKey();
            labelDescribe.setLabelLevel(MasteryThree._3.name);
            labelDescribe.setLevelDescribe(labelInfo.getExcellentDescribe());
            labelDescribes.add(labelDescribe);
        }
        if (!StringUtils.isEmpty(labelInfo.getGoodDescribe())) {
            LabelDescribe labelDescribe = new LabelDescribe();
            labelDescribe.generateKey();
            labelDescribe.setLabelLevel(MasteryThree._2.name);
            labelDescribe.setLevelDescribe(labelInfo.getGoodDescribe());
            labelDescribes.add(labelDescribe);
        }
        if (null != labelInfo.getCommonlyDescribe()) {
            LabelDescribe labelDescribe = new LabelDescribe();
            labelDescribe.generateKey();
            labelDescribe.setLabelLevel(MasteryThree._1.name);
            labelDescribe.setLevelDescribe(labelInfo.getCommonlyDescribe());
            labelDescribes.add(labelDescribe);
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(labelCatalog.getInputTime(), df);
        for (LabelDescribe labelDescribeTemp : labelDescribes) {
            if (ButtonType._3.name.equals(labelInfo.getButtonType())) {
                labelDescribeTemp.setLabelNo(labelCatalog.getSerialNo());
            }
            else {
                labelDescribeTemp.setLabelNo(labelInfo.getLabelNo());
            }
            labelDescribeTemp.setLevelDescribe(labelDescribeTemp.getLevelDescribe());
            labelDescribeTemp.setLabelLevel(labelDescribeTemp.getLabelLevel());
            labelDescribeTemp.setInputUserId(GlobalShareContextHolder.getUserId());
            labelDescribeTemp.setInputTime(parse);
            labelDescribeTemp.setInputOrgId(GlobalShareContextHolder.getOrgId());
            labelDescribeTemp.setUpdateTime(LocalDateTime.now());
            labelDescribeTemp.setUpdateOrgId(GlobalShareContextHolder.getOrgId());
            labelDescribeTemp.setUpdateUserId(GlobalShareContextHolder.getUserId());
            bomanager.updateBusinessObject(labelDescribeTemp);
        }
        bomanager.updateDB();
    }

    /**
     * 标签生效/失效
     * 
     * @param labelInfoSaveReq
     */
    @Override
    public void lableStatusOk(@Valid LabelInfoSaveReq labelInfoSaveReq) {
        lableStatusOkAction(labelInfoSaveReq);
    }

    /**
     * 标签生效/失效
     * 
     * @param labelInfo
     */
    @Transactional
    public void lableStatusOkAction(LabelInfo labelInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LabelCatalog lc = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelInfo.getSerialNo());
        if (LabelStatus.Enabled.id.equals(labelInfo.getButtonType())) {
            // 根据标签状态码值 1为新增 2 为生效 3 为失效
            if (LabelStatus.New.id.equals(lc.getLabelStatus()) || LabelStatus.Disabled.id.equals(lc.getLabelStatus())) {
                lc.setLabelStatus(LabelStatus.Enabled.id);
                bomanager.updateBusinessObject(lc);
            }
            else {
                throw new ALSException("EMS2008");
            }
        }
        else {
            if (LabelStatus.Enabled.id.equals(lc.getLabelStatus())) {
                lc.setLabelStatus(LabelStatus.Disabled.id);
                bomanager.updateBusinessObject(lc);
            }
            else {
                throw new ALSException("EMS2009");
            }

        }
        bomanager.updateDB();
    }

    /**
     * 标签目录新增判重 标签目录新增时判断名称是否重复
     * 
     * @param labelInfoRepeatReq
     */
    @Override
    public LabelInfoRepeatRsp isRepeat(LabelInfoRepeatReq labelInfoRepeatReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LabelInfoRepeatRsp isRepeatRsp = new LabelInfoRepeatRsp();
        //新增判重
        if (ButtonType._1.id.equals(labelInfoRepeatReq.getButtonType())) {
            List<LabelCatalog> labelCatalogs = bomanager.loadBusinessObjects(LabelCatalog.class, "labelName=:labelName", "labelName",
                labelInfoRepeatReq.getLabelName());
            if (CollectionUtils.isEmpty(labelCatalogs)) {
                isRepeatRsp.setRepeat(true);
                return isRepeatRsp;
            }
            else {
                isRepeatRsp.setRepeat(false);
                return isRepeatRsp;
            }
        }
        //更新判重
        else {
            List<LabelCatalog> labelCatalogs = bomanager.loadBusinessObjects(LabelCatalog.class,
                "labelName=:labelName and serialNo <> :serialNo", "labelName", labelInfoRepeatReq.getLabelName(), "serialNo",
                labelInfoRepeatReq.getSerialNo());
            
            if (CollectionUtils.isEmpty(labelCatalogs)) {
                isRepeatRsp.setRepeat(true);
                return isRepeatRsp;
            }
            else {
                isRepeatRsp.setRepeat(false);
                return isRepeatRsp;
            }
        }
    }



}