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
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labellist.LabelListDeleteReq;

/**
 * 标签ListService接口
 * @author yrong
 */
public interface LabelListService {
    /**
     * 标签List查询
     * @param request
     * @return
     */
    public LabelListQueryRsp labelListQuery(@Valid LabelListQueryReq labelListQueryReq);

    /**
     * 标签List保存
     * @param request
     * @return
     */
    public void labelListSave(@Valid LabelListSaveReq labelListSaveReq);

    /**
     * 标签List删除
     * @param request
     * @return
     */
    public void labelListDelete(@Valid LabelListDeleteReq labelListDeleteReq);
}