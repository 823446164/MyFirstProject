/*
 * 文件名：LabelListService
 * 版权：Copyright by www.amarsoft.com
 * 描述：LabelListServiceImp的Service接口
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListDeleteReq;

/**
 * 标签ListService接口
 * @author yrong
 */
public interface LabelListService {
    /**
     * 标签List删除
     * @param request
     * @return
     */
    public void labelListDelete(@Valid LabelListDeleteReq labelListDeleteReq);
}