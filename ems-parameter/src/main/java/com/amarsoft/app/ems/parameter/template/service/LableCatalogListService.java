package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.lablecataloglist.LableCatalogListDeleteReq;

/**
 * 标签目录树图Service接口
 * @author ylgao
 */
public interface LableCatalogListService {
    /**
     * 标签目录树图查询
     * @param request
     * @return
     */
    public LableCatalogListQueryRsp lableCatalogListQuery(@Valid LableCatalogListQueryReq lableCatalogListQueryReq);

    /**
     * 标签目录树图保存
     * @param request
     * @return
     */
    public void lableCatalogListSave(@Valid LableCatalogListSaveReq lableCatalogListSaveReq);

    /**
     * 标签目录树图删除
     * @param request
     * @return
     */
    public void lableCatalogListDelete(@Valid LableCatalogListDeleteReq lableCatalogListDeleteReq);
}
