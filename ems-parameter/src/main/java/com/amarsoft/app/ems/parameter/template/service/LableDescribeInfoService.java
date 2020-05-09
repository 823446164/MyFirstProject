package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoSaveReq;

/**
 * 标签树图Service接口
 * @author ylgao
 */
public interface LableDescribeInfoService {
    /**
     * 标签树图查询
     * @param request
     * @return
     */
    public LableDescribeInfoQueryRsp lableDescribeInfoQuery(@Valid LableDescribeInfoQueryReq lableDescribeInfoQueryReq);

    /**
     * 标签树图保存
     * @param request
     * @return
     */
    public void lableDescribeInfoSave(@Valid LableDescribeInfoSaveReq lableDescribeInfoSaveReq);
    
    /**
     * 标签生效
     * @param request
     * @return
     */
    public void lableStatusOk(@Valid LableDescribeInfoSaveReq lableDescribeInfoSaveReq);
    
    /**
     * 标签失效
     * @param request
     * @return
     */
    public void lableStatusNo(@Valid LableDescribeInfoSaveReq lableDescribeInfoSaveReq);
}
