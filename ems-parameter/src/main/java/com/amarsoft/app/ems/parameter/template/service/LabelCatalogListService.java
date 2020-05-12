/*
 * 文件名：LabelCatalogListService
 * 版权：Copyright by www.amarsoft.com
 * 描述：LabelCatalogListServiceImp的Service接口
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：增加选中目录查询标签接口
 */
package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListDeleteReq;

/**
 * 标签目录树图Service接口
 * @author yrong
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
