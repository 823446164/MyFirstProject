/*
 * 文件名：LabelListServiceImpl 
 * 版权：Copyright by www.amarsoft.com 
 * 描述：为LabelList模板提供方法 
 * 修改人：yrong
 * 修改时间：2020年5月11日 
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.service.impl;


import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.amarsoft.aecd.parameter.constant.LabelType;
import com.amarsoft.aecd.system.constant.LabelStatus;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.parameter.entity.LabelCatalog;
import com.amarsoft.app.ems.parameter.entity.LabelDescribe;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListDeleteReq;
import com.amarsoft.app.ems.parameter.template.service.LabelListService;
import lombok.extern.slf4j.Slf4j;


/**
 * 标签ListService实现类
 * 
 * @author yrong
 */
@Slf4j
@Service
public class LabelListServiceImpl implements LabelListService {
    /**
     * 标签详情删除删除 需要删除Label_Catalog表和Label_Describe表的数据
     * 
     * @param labelListDeleteReq
     */
    @Override
    @Transactional
    // TODO 待调试
    public void labelListDelete(@Valid LabelListDeleteReq labelListDeleteReq) {
        // 执行删除标签方法
        if (LabelType._3.id.equals(labelListDeleteReq.getLabelType())) {
            labelDelete(labelListDeleteReq);
        }
        else {// 删除指标
            indexDelete(labelListDeleteReq);
        }
    }

    /**
     * 标签详情删除删除 需要删除Label_Catalog表和Label_Describe表的数据
     * 
     * @param labelListDeleteReq
     */
    @Transactional
    public void labelDelete(@Valid LabelListDeleteReq labelListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LabelCatalog labelCatalog = bomanager.loadBusinessObject(LabelCatalog.class, "serialNo", labelListDeleteReq.getSerialNo());
        if (LabelStatus.Enabled.id.equals(labelCatalog.getLabelStatus())) {
            throw new ALSException("EMS2027", labelCatalog.getLabelName());
        }
        else {

            bomanager.deleteBusinessObject(labelCatalog);

            String sql = "select LD.serialNo as serialNo,LD.labelNo as labelNo,LD.labelLevel as labelLevel,LD.levelDescribe as levelDescribe,LD.inputUserId as inputUserId,"
                         + "LD.inputTime as inputTime,LD.inputOrgId as inputOrgId,LD.updateUserId as updateUserId,LD.updateTime as updateTime,LD.updateOrgId as updateOrgId"
                         + " from LabelDescribe LD where labelNo=:serialNo";
            BusinessObjectAggregate<BusinessObject> selectBusinessObjectsBySql = bomanager.selectBusinessObjectsBySql(sql, "serialNo",
                labelListDeleteReq.getSerialNo());
            List<BusinessObject> BusinessObjects = selectBusinessObjectsBySql.getBusinessObjects();
            for (int i = 0; i < BusinessObjects.size(); i++ ) {
                LabelDescribe labelDescribe = bomanager.keyLoadBusinessObject(LabelDescribe.class,
                    BusinessObjects.get(i).getString("serialNo"));
                bomanager.deleteBusinessObject(labelDescribe);
            }
            bomanager.updateDB();
        }
    }

    /**
     * 指标删除 需要删除Label_Catalog表和Label_Describe表的数据
     * 
     * @param labelListDeleteReq
     */
    @Transactional
    public void indexDelete(@Valid LabelListDeleteReq labelListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        // 查询要删除指标的信息
        LabelCatalog index = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelListDeleteReq.getSerialNo());
        if (LabelStatus.Enabled.id.equals(index.getLabelStatus())) {
            throw new ALSException("EMS2025", index.getLabelName());
        }
        else {
            // 查询该指标下所有标签的信息
            List<LabelCatalog> labels = bomanager.loadBusinessObjects(LabelCatalog.class, "parentNo=:serialNo", "serialNo",
                index.getSerialNo());
            LabelListDeleteReq labelDeleteReq = new LabelListDeleteReq();
            boolean isDelete = true;
            LabelCatalog enabledLabel = null;
            for (LabelCatalog labelTemp : labels) {
                if (LabelStatus.Enabled.id.equals(labelTemp.getLabelStatus())) {
                    enabledLabel = labelTemp;
                    isDelete = false;
                }
            }
            if (false == isDelete) {
                throw new ALSException("EMS2026", index.getLabelName(), enabledLabel.getLabelName());
            }
            else {
                for (LabelCatalog labelTemp : labels) {
                    labelDeleteReq.setSerialNo(labelTemp.getSerialNo());
                    labelDelete(labelDeleteReq);
                }
                // 删除指标
                labelDelete(labelListDeleteReq);
            }
        }
    }
}