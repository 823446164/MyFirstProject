/*
 * 文件名：LableCatalogInfoService
 * 版权：Copyright by www.amarsoft.com
 * 描述：LableCatalogInfoServiceImp的Service接口
 * 修改人：yrong
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：增加选中目录查询标签接口
 */
package com.amarsoft.app.ems.parameter.template.service;


import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoSaveReq;


/**
 * 标签目录详情Service接口
 * 
 * @author ylgao
 */
public interface LableCatalogInfoService {
    /**
     * 标签目录详情查询
     * 
     * @param request
     * @return
     */
    public LableCatalogInfoQueryRsp lableCatalogInfoQuery(@Valid LableCatalogInfoQueryReq lableCatalogInfoQueryReq);

    /** 
     * 标签目录详情保存
     * 
     * @param request
     * @return
     */
    public void lableCatalogInfoSave(@Valid LableCatalogInfoSaveReq lableCatalogInfoSaveReq);

    /**
     * 选中目录查询标签
     * 
     * @param request
     * @return
     */
    public LableCatalogInfoQueryRsp labelBelongCatalogQuery(@Valid LableCatalogInfoQueryReq lableCatalogInfoQueryReq);
}
