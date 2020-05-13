package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labeldescribeinfo.LabelDescribeInfoSaveReq;

/**
 * 标签能力描述InfoService接口
 * @author yrong
 */
public interface LabelDescribeInfoService {
    /**
     * 标签能力描述Info查询
     * @param request
     * @return
     */
    public LabelDescribeInfoQueryRsp labelDescribeInfoQuery(@Valid LabelDescribeInfoQueryReq labelDescribeInfoQueryReq);

    /**
     * 标签能力描述Info保存
     * @param request
     * @return
     */
    public void labelDescribeInfoSave(@Valid LabelDescribeInfoSaveReq labelDescribeInfoSaveReq);
}
