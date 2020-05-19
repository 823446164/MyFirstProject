/*
 * 文件名：LabelInfoService
 * 版权：Copyright by www.amarsoft.com
 * 描述：LabelInfoServiceImp的Service接口
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;

import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoCopyReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoRepeatReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoRepeatRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoSaveReq;

/**
 * 标签InfoService接口
 * @author yrong
 */
public interface LabelInfoService {
    /**
     * 标签Info查询
     * @param labelInfoQueryReq
     * @return
     */
    public LabelInfoQueryRsp labelInfoQuery(@Valid LabelInfoQueryReq labelInfoQueryReq);

    /**
     * 标签Info保存 
     * @param labelInfoSaveReq
     * @return
     */
    public void labelInfoSave(@Valid LabelInfoSaveReq labelInfoSaveReq);
        
    /**
     * 标签生效 
     * 
     * @param lableDescribeInfoSaveReq
     * @return 
     */
    public void lableStatusOk(@Valid LabelInfoSaveReq lableDescribeInfoSaveReq);
    
    /**
     * 新增判重 
     * 
     * @param labelInfoRepeatReq
     * @return LabelInfoRepeatRsp
     */
    public LabelInfoRepeatRsp isRepeat(@Valid LabelInfoRepeatReq labelInfoRepeatReq);
    

}