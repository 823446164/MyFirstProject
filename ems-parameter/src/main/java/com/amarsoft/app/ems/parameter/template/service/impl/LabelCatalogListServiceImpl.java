/*
 * 文件名：LabelCatalogListServiceImpl 
 * 版权：Copyright by www.amarsoft.com 
 * 描述：为LabelCatalogInfo模板提供方法
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

import org.apache.commons.io.CopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogListService;
import com.amarsoft.aecd.parameter.constant.LabelType;
import com.amarsoft.aecd.system.constant.LabelStatus;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.app.ems.parameter.entity.LabelCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfo;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListDeleteReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListDeleteReq;


/**
 * 标签目录树图Service实现类
 * 
 * @author yrong
 */
@Slf4j
@Service
public class LabelCatalogListServiceImpl implements LabelCatalogListService {
    /**
     * 根据标签serialNo查询标签内容
     * 
     * @param labelCatalogListDeleteReq
     * @return void
     */
    @Override
    @Transactional
    public LabelCatalogListQueryRsp selectLabelBySerialNos(@Valid LabelCatalogListQueryReq labelCatalogListQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        String serialNos = "";
        List<String> serialNolist = labelCatalogListQueryReq.getSerialNoList();
        for(String serialNo:serialNolist) {
            serialNos+="'"+serialNo+"',";
        }
        if(!StringUtils.isEmpty(serialNos)) {
            serialNos = serialNos.substring(0, serialNos.length()-1);
        }
        List<LabelCatalog> labelCatalogs = bomanager.loadBusinessObjects(LabelCatalog.class, "serialNo in (" + serialNos +") and labelType=:labelType", "labelType",LabelType._3.id);        
        List<LabelCatalogInfo> labelCatalogInfos =new ArrayList<>();
        LabelCatalogInfo  labelCatalogInfo = new LabelCatalogInfo();
        for (LabelCatalog labelCatalogTemp : labelCatalogs) {
            BeanUtils.copyProperties(labelCatalogTemp, labelCatalogInfo);
            labelCatalogInfos.add(labelCatalogInfo);
        }
        LabelCatalogListQueryRsp labelCatalogListQueryRsp =new LabelCatalogListQueryRsp();
        labelCatalogListQueryRsp.setLableCatalogInfos(labelCatalogInfos);
        return labelCatalogListQueryRsp;
    }            
    
    /**
     * 标签目录树图删除
     * 
     * @param labelCatalogListDeleteReq
     * @return void
     */
    @Override
    @Transactional
    public void labelCatalogListDelete(@Valid LabelCatalogListDeleteReq labelCatalogListDeleteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        LabelListServiceImpl labelListServiceImpl = new LabelListServiceImpl();
        boolean isNotHaveStatusOk = selectLabelByLabelCatalog(labelCatalogListDeleteReq.getSerialNo());
        //isNotHaveStatusOk 为true则说明有生效标签，不能删除   false为没有生效标签，可以删除
        if (!isNotHaveStatusOk) {
            //获得目录实体类删除
            LabelCatalog labelCatalog = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelCatalogListDeleteReq.getSerialNo());
            bomanager.deleteBusinessObject(labelCatalog);                       
            bomanager.updateDB();
            //获得该目录下所有指标和标签
            List<LabelCatalog> childrenCatalogs = queryChildrenCatalog(bomanager,labelCatalogListDeleteReq.getSerialNo());
            for (LabelCatalog labelCatalogTemp : childrenCatalogs) {
                if(LabelType._2.id.equals(labelCatalogTemp.getLabelType() )){//执行指标删除方法(删除指标方法包括删除标签)
                    LabelListDeleteReq labelListDeleteReq = new LabelListDeleteReq();
                    labelListDeleteReq.setLabelType(labelCatalogTemp.getLabelType());  
                    labelListDeleteReq.setSerialNo(labelCatalogTemp.getSerialNo());
                    labelListServiceImpl.indexDelete(labelListDeleteReq);
                }else {//删除该目录下的其他目录
                    bomanager.deleteBusinessObject(labelCatalogTemp);                       
                    bomanager.updateDB();
                }                
            }
        }
        else {
            throw new ALSException("EMS2010");
        }
    }
         
    /**
     * 查询出选中目录下所有标签
     * 
     * @param labelDescribeInfo
     * @return List<LabelCatalog>
     */
    public List<LabelCatalog> queryChildrenCatalog(BusinessObjectManager bomanager, String serialNo) {
        // 存放查询到的目录
        List<LabelCatalog> LabelCatalogs = new ArrayList<>();
        List<LabelCatalog> childrenLabelCatalogs = bomanager.loadBusinessObjects(LabelCatalog.class, "parentNo=:serialNo and (labelType=:labelType2 or labelType=:labelType3) order by serialNo",
            "labelType2",LabelType._3.id, "labelType3",LabelType._2.id,"serialNo", serialNo);
        childrenLabelCatalogs.forEach(childrenLabelCatalog -> {
            LabelCatalogs.add(childrenLabelCatalog);
            LabelCatalogs.addAll(queryChildrenCatalog(bomanager, childrenLabelCatalog.getSerialNo()));
        });
        return LabelCatalogs;
    }
    
    /**
     * 判断标签目录能否删除标签目录树图 
     * 标签目录树图(若该目录下有已经生效的标签，则不能删除，返回true。若可以删除，返回false)
     * @param serialNo
     * @return isNotHaveStatusOk
     */
    public boolean selectLabelByLabelCatalog(String serialNo) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        boolean isHaveStatusOk = false;
        List<LabelCatalog> labelCatalogs = queryChildrenCatalog(bomanager, serialNo);
        for (LabelCatalog labelCatalogTemp : labelCatalogs) {
            if(LabelStatus.Enabled.id.equals(labelCatalogTemp.getLabelStatus())) {
                isHaveStatusOk = true;
            } 
        }
        return isHaveStatusOk;
    }
}