package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoSaveReq;

/**
 * 标签目录详情Service接口
 * @author ylgao
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
}
