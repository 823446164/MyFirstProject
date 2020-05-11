package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelinfo.LabelInfoSaveReq;

/**
 * 标签InfoService接口
 * @author ylgao
 */
public interface LabelInfoService {
    /**
     * 标签Info查询
     * @param request
     * @return
     */
    public LabelInfoQueryRsp labelInfoQuery(@Valid LabelInfoQueryReq labelInfoQueryReq);

    /**
     * 标签Info保存
     * @param request
     * @return
     */
    public void labelInfoSave(@Valid LabelInfoSaveReq labelInfoSaveReq);
}
