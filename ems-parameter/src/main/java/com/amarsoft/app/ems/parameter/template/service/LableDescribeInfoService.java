/*
 * 文件名：LableDescribeInfoService
 * 版权：Copyright by www.amarsoft.com
 * 描述：LableDescribeInfoServiceImp的Service接口
 * 修改人：yrong
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：增加标签生效、失效接口
 */
package com.amarsoft.app.ems.parameter.template.service;


import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo.LableDescribeInfoSaveReq;

public interface LableDescribeInfoService {
    /**
     * 标签树图查询
     * 
     * @param request
     * @return
     */
    public LableDescribeInfoQueryRsp lableDescribeInfoQuery(@Valid LableDescribeInfoQueryReq lableDescribeInfoQueryReq);

    /**
     * 标签树图保存
     * 
     * @param request
     * @return
     */
    public void lableDescribeInfoSave(@Valid LableDescribeInfoSaveReq lableDescribeInfoSaveReq);

    /**
     * 标签生效 
     * 
     * @param request
     * @return
     */
    public void lableStatusOk(@Valid LableDescribeInfoSaveReq lableDescribeInfoSaveReq);

    /**
     * 标签失效
     * 
     * @param request
     * @return
     */
    public void lableStatusNo(@Valid LableDescribeInfoSaveReq lableDescribeInfoSaveReq);
}
