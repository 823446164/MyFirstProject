/*
 * 文件名：LabelCatalogTreeService.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：标签目录树图的service接口
 * 修改人：yrong
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.service;

import com.amarsoft.app.ems.parameter.template.cs.dto.labelcatalogtreequery.LabelCatalogTreeQueryRsp;

/**
 * 标签目录树图的service接口
 * @author yrong
 * @version 2020年5月13日
 * @see LabelCatalogTreeService
 * @since
 */

public interface LabelCatalogTreeService {
    /**
     * 标签目录树图显示
     * @param request
     * @return
     */
    public LabelCatalogTreeQueryRsp labelCatalogTreeQuery();

}
