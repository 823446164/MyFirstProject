/*
 * 文件名：LabelCatalogInfoService
 * 版权：Copyright by www.amarsoft.com
 * 描述：LabelCatalogInfoServiceImp的Service接口
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：增加选中目录查询标签接口
 */
package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;

import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelByLabelCatalogQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelByLabelCatalogQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoSaveReq;

/**
 * 标签目录详情Service接口
 * @author yrong
 */
public interface LabelCatalogInfoService {
    /**
     * 标签目录详情查询
     * @param request
     * @return
     */
    public LabelCatalogInfoQueryRsp labelCatalogInfoQuery(@Valid LabelCatalogInfoQueryReq labelCatalogInfoQueryReq);

    /**
     * 标签目录详情保存
     * @param request
     * @return
     */
    public void labelCatalogInfoSave(@Valid LabelCatalogInfoSaveReq labelCatalogInfoSaveReq);
    
    
    /**
     * 选中目录查询标签
     * 
     * @param request
     * @return
     */
    public LabelByLabelCatalogQueryRsp labelBelongCatalogQuery(@Valid LabelByLabelCatalogQueryReq lableCatalogInfoQueryReq);
}
