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
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListDeleteReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist.LabelCatalogListQueryRsp;

/**
 * 标签目录树图Service接口
 * @author yrong
 */
public interface LabelCatalogListService {
    /**
     * 标签目录树图删除
     * @param request
     * @return
     */
    public void labelCatalogListDelete(@Valid LabelCatalogListDeleteReq labelCatalogListDeleteReq);
    
    /**
     * 标签查询
     * 根据请求中的serialNo集合查询出所有相关的标签数据
     * @param request
     * @return
     */
    public LabelCatalogListQueryRsp selectLabelBySerialNos(@Valid LabelCatalogListQueryReq labelCatalogListQueryReq);
}