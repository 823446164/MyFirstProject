package com.amarsoft.app.ems.system.service;


import javax.validation.Valid;

import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoDeleteRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryRoleRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamlistdto.TeamListDtoSaveReq;


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
     * @return
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
    public TeamListDtoDeleteRsp teamListDtoDelete(@Valid TeamListDtoDeleteReq teamListDtoDeleteReq);

    /**
     * 根据部门查询团队信息
     * 
     * @param request
     * @return
     */
    TeamListDtoQueryRsp teamQueryById(TeamListDtoQueryReq request);

    /**
     * Description:团队角色 <br>
     * 
     * @param request
     * @see
     */
    public TeamListDtoQueryRoleRsp teamListDtoRole(TeamListDtoQueryReq request);

}
