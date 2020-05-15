package com.amarsoft.app.ems.system.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryReq;

import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoSaveReq;
import com.amarsoft.app.ems.system.template.cs.dto.deleteinfodto.DeleteInfoDtoQueryReq;

/**
 * 团队信息Service接口
 * 
 * @author hpli
 */
public interface TeamListDtoService {


    /**
     * 团队信息查询
     * 
     * @param request
     * @return rsp
     */
    public TeamListDtoQueryRsp teamListDtoQuery(@Valid TeamListDtoQueryReq teamListDtoQueryReq);

    /**
     * 团队信息保存
     * 
     * @param request
     * @return
     */
    public void teamListDtoSave(@Valid TeamListDtoSaveReq teamListDtoSaveReq);

    /**
     * 团队信息删除
     * 
     * @param request
     * @return 
     */
    public void  teamListDtoDelete(@Valid DeleteInfoDtoQueryReq req);

    /**
     * 根据部门查询团队信息
     * 
     * @param request
     * @return rsp
     */
    TeamListDtoQueryRsp teamQueryById(TeamListDtoQueryReq request);


}
