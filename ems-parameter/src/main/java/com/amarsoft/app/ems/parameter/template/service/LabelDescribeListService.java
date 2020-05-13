package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribelist.LabelDescribeListDeleteReq;

/**
 * 标签能力描述ListService接口
 * @author yrong
 */
public interface LabelDescribeListService {
    /**
     * 标签能力描述List查询
     * @param request
     * @return
     */
    public LabelDescribeListQueryRsp labelDescribeListQuery(@Valid LabelDescribeListQueryReq labelDescribeListQueryReq);

    /**
     * 标签能力描述List保存
     * @param request
     * @return
     */
    public void labelDescribeListSave(@Valid LabelDescribeListSaveReq labelDescribeListSaveReq);

    /**
     * 标签能力描述List删除
     * @param request
     * @return
     */
    public void labelDescribeListDelete(@Valid LabelDescribeListDeleteReq labelDescribeListDeleteReq);
}
