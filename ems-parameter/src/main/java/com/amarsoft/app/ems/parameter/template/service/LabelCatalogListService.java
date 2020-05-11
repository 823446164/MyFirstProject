package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListDeleteReq;

/**
 * 标签目录树图Service接口
 * @author ylgao
 */
public interface LabelCatalogListService {
    /**
     * 标签目录树图查询
     * @param request
     * @return
     */
    public LabelCatalogListQueryRsp labelCatalogListQuery(@Valid LabelCatalogListQueryReq labelCatalogListQueryReq);

    /**
     * 标签目录树图保存
     * @param request
     * @return
     */
    public void labelCatalogListSave(@Valid LabelCatalogListSaveReq labelCatalogListSaveReq);

    /**
     * 标签目录树图删除
     * @param request
     * @return
     */
    public void labelCatalogListDelete(@Valid LabelCatalogListDeleteReq labelCatalogListDeleteReq);
}
