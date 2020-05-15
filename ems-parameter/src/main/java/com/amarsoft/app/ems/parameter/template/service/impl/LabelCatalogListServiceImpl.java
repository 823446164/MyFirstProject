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
import javax.validation.Valid;
import org.springframework.stereotype.Service;

import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.parameter.template.service.LabelCatalogListService;
import com.amarsoft.aecd.parameter.constant.LabelType;
import com.amarsoft.aecd.system.constant.LabelStatus;
import java.util.List;
import java.util.ArrayList;
import com.amarsoft.app.ems.parameter.entity.LabelCatalog;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListDeleteReq;


/**
 * 标签目录树图Service实现类
 * 
 * @author yrong
 */
@Slf4j
@Service
public class LabelCatalogListServiceImpl implements LabelCatalogListService {
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
        boolean isNotHaveStatusOk = selectLabelByLabelCatalog(labelCatalogListDeleteReq.getSerialNo());
        //isNotHaveStatusOk 为true则说明有生效标签，不能删除   false为没有生效标签，可以删除
        if (!isNotHaveStatusOk) {
            LabelCatalog labelCatalog = bomanager.keyLoadBusinessObject(LabelCatalog.class, labelCatalogListDeleteReq.getSerialNo());
            bomanager.deleteBusinessObject(labelCatalog);
            bomanager.updateDB();
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
//        bomanager.selectBusinessObjectsBySql("select * from LabelCatalog where parentNo=:serialNo and (labelType=2 or labelType=3)", parameters)
        childrenLabelCatalogs.forEach(childrenLabelCatalog -> {
            LabelCatalogs.add(childrenLabelCatalog);
            LabelCatalogs.addAll(queryChildrenCatalog(bomanager, childrenLabelCatalog.getSerialNo()));
        });
        return LabelCatalogs;
    }
    
    /**
     * 判断标签目录能否删除标签目录树图 
     * 标签目录树图(若该目录下有已经生效的标签，则不能删除，返回false。若可以删除，返回true)
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