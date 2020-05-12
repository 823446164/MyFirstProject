package com.amarsoft.app.ems.system.service;

import javax.validation.Valid;

import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoSaveReq;


/**
 * 团队信息Service接口
 * @author hpli
 */
public interface TeamListDtoService {
    
    /**
     * 团队信息查询
     * @param request
     * @return
     */
    public TeamListDtoQueryRsp teamListDtoQuery(@Valid TeamListDtoQueryReq teamListDtoQueryReq);

    /**
     * 团队信息保存
     * @param request
     * @return
     */
    public void teamListDtoSave(@Valid TeamListDtoSaveReq teamListDtoSaveReq);

    /**
     * 团队信息删除
     * @param request
     * @return
     */
    public void teamListDtoDelete(@Valid TeamListDtoDeleteReq teamListDtoDeleteReq);
    /**
     * 团队员工信息查询
     * @param request
     * @return
     */
    public TeamListDtoQueryRsp teamUserQuery(@Valid TeamListDtoQueryReq teamListDtoQueryReq);

}
