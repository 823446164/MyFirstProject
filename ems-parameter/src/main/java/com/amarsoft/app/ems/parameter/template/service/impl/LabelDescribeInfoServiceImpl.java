package com.amarsoft.app.ems.parameter.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import javax.validation.Valid;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LabelDescribeInfoService;
import com.amarsoft.app.ems.parameter.entity.LabelDescribe;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfo;


/**
 * 标签能力描述InfoService实现类
 * 
 * @author yrong
 */
@Slf4j
@Service
public class LabelDescribeInfoServiceImpl implements LabelDescribeInfoService {
    /**
     * 标签能力描述Info单记录查询
     * 
     * @param labelDescribeInfoQueryReq
     * @return
     */
    @Override
    @Transactional
    public LabelDescribeInfoQueryRsp labelDescribeInfoQuery(@Valid LabelDescribeInfoQueryReq labelDescribeInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();

        LabelDescribe labelDescribe = bomanager.loadBusinessObject(LabelDescribe.class, "serialNo",
            labelDescribeInfoQueryReq.getSerialNo());
        if (labelDescribe != null) {
            LabelDescribeInfoQueryRsp labelDescribeInfo = new LabelDescribeInfoQueryRsp();
            labelDescribeInfo.setSerialNo(labelDescribe.getSerialNo());
            labelDescribeInfo.setLabelNo(labelDescribe.getLabelNo());
            labelDescribeInfo.setLabelLevel(labelDescribe.getLabelLevel());
            labelDescribeInfo.setLevelDescribe(labelDescribe.getLevelDescribe());
            labelDescribeInfo.setInputUserId(labelDescribe.getInputUserId());
            labelDescribeInfo.setInputTime(labelDescribe.getInputTime());
            labelDescribeInfo.setInputOrgId(labelDescribe.getInputOrgId());
            labelDescribeInfo.setUpdateUserId(labelDescribe.getUpdateUserId());
            labelDescribeInfo.setUpdateTime(labelDescribe.getUpdateTime());
            labelDescribeInfo.setUpdateOrgId(labelDescribe.getUpdateOrgId());
            return labelDescribeInfo;
        }

        return null;
    }

    /**
     * 标签能力描述Info单记录保存
     * 
     * @param labelDescribeInfoSaveReq
     * @return
     */
    @Override
    public void labelDescribeInfoSave(@Valid LabelDescribeInfoSaveReq labelDescribeInfoSaveReq) {
        labelDescribeInfoSaveAction(labelDescribeInfoSaveReq);
    }

    /**
     * 标签能力描述Info单记录保存
     * 
     * @param labelDescribeInfo
     * @return
     */
    @Transactional
    public void labelDescribeInfoSaveAction(LabelDescribeInfo labelDescribeInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (labelDescribeInfo != null) {}
        bomanager.updateDB();
    }
}
