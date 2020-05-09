package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeListDeleteReq;

/**
 * 标签树图Service接口
 * @author ylgao
 */
public interface LableDescribeListService {
    /**
     * 标签树图查询
     * @param request
     * @return
     */
    public LableDescribeListQueryRsp lableDescribeListQuery(@Valid LableDescribeListQueryReq lableDescribeListQueryReq);

    /**
     * 标签树图保存
     * @param request
     * @return
     */
    public void lableDescribeListSave(@Valid LableDescribeListSaveReq lableDescribeListSaveReq);

    /**
     * 标签树图删除
     * @param request
     * @return
     */
    public void lableDescribeListDelete(@Valid LableDescribeListDeleteReq lableDescribeListDeleteReq);
}
