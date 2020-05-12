package com.amarsoft.app.ems.system.service;

import javax.validation.Valid;

import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teaminfodto.TeamInfoDtoSaveReq;

/**
 * 团队信息Service接口
 * @author hpli
 */
public interface TeamInfoDtoService {
    
    /**
     * 团队信息查询
     * @param request
     * @return
     */
    public TeamInfoDtoQueryRsp teamInfoDtoQuery(@Valid TeamInfoDtoQueryReq teamInfoDtoQueryReq);

    /**
     * 团队信息保存
     * @param request
     * @return
     */
    public void teamInfoDtoSave(@Valid TeamInfoDtoSaveReq teamInfoDtoSaveReq);
}
