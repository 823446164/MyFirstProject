package com.amarsoft.app.ems.parameter.template.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LableDescribeInfoService;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;

import java.util.ArrayList;
import java.util.List;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeList;
import com.amarsoft.app.ems.parameter.entity.LableCatalog;
import com.amarsoft.app.ems.parameter.entity.LableDescribe;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfo;


/**
 * 
 * 为LabelDescribeInfo模板提供方法
 * 标签详情查询,标签生效,标签失效
 * @author amarsoft
 * @version 2020年5月9日
 * @see LableDescribeInfoServiceImpl
 * @since
 */

@Slf4j
@Service
public class LableDescribeInfoServiceImpl implements LableDescribeInfoService {
    /**
     * 标签详情查询
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public LableDescribeInfoQueryRsp lableDescribeInfoQuery(@Valid LableDescribeInfoQueryReq lableDescribeInfoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LableDescribeInfoQueryRsp lableDescribeInfoQueryRsp = new LableDescribeInfoQueryRsp();
        BusinessObjectAggregate<BusinessObject> lableDescribeInfoQueryRspBoa = bomanager.selectBusinessObjectsBySql(
            "select LC.labelName as labelName,LC.codeNo as codeNo,LC.labelStatus as labelStatus,LC.belongCataLog as belongCataLog,LC.rootNo as rootNo,LC.abilityType as abilityType,LC.labelDescribe as labelDescribe,LC.labelVersion as labelVersion,LC.inputUserId as inputUserId,LC.inputTime as inputTime,LC.inputOrgId as inputOrgId,LC.updateUserId as updateUserId,LC.updateTime as updateTime,LC.updateOrgId as updateOrgId,"
             + "LD.serialNo as serialNo,LD.labelNo as labelNo,LD.labelLevel as labelLevel,LD.levelDescribe as levelDescribe"
             + " from  LableCatalog LC,LableDescribe LD" + " where LD.labelNo = LC.serialNo and LC.serialNo=:serialNo",
            "serialNo", lableDescribeInfoQueryReq.getSerialNo());
//        System.out.println("111");
        List<BusinessObject> lableDescribeInfoQueryRspBoList = lableDescribeInfoQueryRspBoa.getBusinessObjects();
//        System.out.println("222");       
        if (lableDescribeInfoQueryRspBoList != null && lableDescribeInfoQueryRspBoList.size() > 0) {
            List<LableDescribeInfo> lableDescribeLists = new ArrayList<LableDescribeInfo>();
            for(int i =0;i<lableDescribeInfoQueryRspBoList.size();i++) {
            BusinessObject lableDescribeInfoQueryRspBo = lableDescribeInfoQueryRspBoList.get(i);
            LableDescribeInfoQueryRsp lableDescribeInfo = new LableDescribeInfoQueryRsp();
            lableDescribeInfo.setLabelName(lableDescribeInfoQueryRspBo.getString("LabelName"));
            lableDescribeInfo.setCodeNo(lableDescribeInfoQueryRspBo.getString("CodeNo"));
            lableDescribeInfo.setLabelStatus(lableDescribeInfoQueryRspBo.getString("LabelStatus"));
            lableDescribeInfo.setBelongCataLog(lableDescribeInfoQueryRspBo.getString("BelongCataLog"));
            lableDescribeInfo.setRootNo(lableDescribeInfoQueryRspBo.getString("RootNo"));
            lableDescribeInfo.setAbilityType(lableDescribeInfoQueryRspBo.getString("AbilityType"));
            lableDescribeInfo.setLabelDescribe(lableDescribeInfoQueryRspBo.getString("LabelDescribe"));
            lableDescribeInfo.setLabelVersion(lableDescribeInfoQueryRspBo.getString("LabelVersion"));
            lableDescribeInfo.setInputUserId(lableDescribeInfoQueryRspBo.getString("InputUserId"));
            lableDescribeInfo.setLC_inputTime(lableDescribeInfoQueryRspBo.getString("InputTime"));
            lableDescribeInfo.setLC_inputOrgId(lableDescribeInfoQueryRspBo.getString("InputOrgId"));
            lableDescribeInfo.setLC_updateUserId(lableDescribeInfoQueryRspBo.getString("UpdateUserId"));
            lableDescribeInfo.setLC_updateTime(lableDescribeInfoQueryRspBo.getString("UpdateTime"));
            lableDescribeInfo.setLC_updateOrgId(lableDescribeInfoQueryRspBo.getString("UpdateOrgId"));
            lableDescribeInfo.setSerialNo(lableDescribeInfoQueryRspBo.getString("SerialNo"));
            lableDescribeInfo.setLabelNo(lableDescribeInfoQueryRspBo.getString("LabelNo"));
            lableDescribeInfo.setLabelLevel(lableDescribeInfoQueryRspBo.getString("LabelLevel"));
            lableDescribeInfo.setLevelDescribe(lableDescribeInfoQueryRspBo.getString("LevelDescribe"));
            lableDescribeLists.add(lableDescribeInfo);
            }
            lableDescribeInfoQueryRsp.setLableDescribeInfos(lableDescribeLists);            
        }
        return lableDescribeInfoQueryRsp;
    }
   
    /**
     * 标签树图单记录保存
     * 
     * @param request
     * @return
     * @Override
     */
    public void lableDescribeInfoSave(@Valid LableDescribeInfoSaveReq lableDescribeInfoSaveReq) {
        lableDescribeInfoSaveAction(lableDescribeInfoSaveReq);
    }

    /**
     * 标签树图单记录保存
     * 
     * @param
     * @return
     */
    @Transactional
    public void lableDescribeInfoSaveAction(LableDescribeInfo lableDescribeInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (lableDescribeInfo != null) {}
        bomanager.updateDB();
    }

    /**
     * 标签生效
     * 
     * @param request
     * @return
     */
    @Override
    public void lableStatusOk(@Valid LableDescribeInfoSaveReq lableDescribeInfoSaveReq) {
        lableStatusOkAction(lableDescribeInfoSaveReq);
    }

    /**
     * 标签生效
     * 
     * @param
     * @return
     */
    @Transactional
    public void lableStatusOkAction(LableDescribeInfo lableDescribeInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (lableDescribeInfo != null) {
            LableCatalog lc = bomanager.keyLoadBusinessObject(LableCatalog.class, lableDescribeInfo.getSerialNo());
            System.out.println(lc.getLabelStatus());
            // 根据标签状态码值 1为新增 2 为生效 3 为失效
            if ("1".equals(lc.getLabelStatus()) || "3".equals(lc.getLabelStatus())) {
                lc.setLabelStatus("2");
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
    public void lableStatusNo(@Valid LableDescribeInfoSaveReq lableDescribeInfoSaveReq) {
        lableStatusNoAction(lableDescribeInfoSaveReq);
    }

    /**
     * 标签失效
     * 
     * @param
     * @return
     */
    @Transactional
    public void lableStatusNoAction(LableDescribeInfo lableDescribeInfo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if (lableDescribeInfo != null) {
            LableCatalog lc = bomanager.keyLoadBusinessObject(LableCatalog.class, lableDescribeInfo.getSerialNo());
            // 根据标签状态码值 1为新增 2 为生效 3 为失效
            if ("2".equals(lc.getLabelStatus())) {
                lc.setLabelStatus("3");
                bomanager.updateBusinessObject(lc);
            }
            else {
                System.out.println("必须生效状态的标签才可以置为失效");
            }
        }
        bomanager.updateDB();
    }
}
