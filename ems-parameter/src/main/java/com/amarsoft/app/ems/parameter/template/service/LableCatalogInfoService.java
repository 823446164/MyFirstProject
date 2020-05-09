package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloginfo.LableCatalogInfoSaveReq;

/**
 * 标签目录详情Service接口
 * @author ylgao
 */
public interface LableCatalogInfoService {
    /**
     * 标签目录详情查询
     * @param request  
     * @return   
     */
    public LableCatalogInfoQueryRsp lableCatalogInfoQuery(@Valid LableCatalogInfoQueryReq lableCatalogInfoQueryReq);

    /**
     * 标签目录详情保存
     * @param request
     * @return
     */
    public void lableCatalogInfoSave(@Valid LableCatalogInfoSaveReq lableCatalogInfoSaveReq);
    
    /**
     * 选中目录查询标签
     * @param request
     * @return
     */
    public LableCatalogInfoQueryRsp labelBelongCatalogQuery(@Valid LableCatalogInfoQueryReq lableCatalogInfoQueryReq);
}
