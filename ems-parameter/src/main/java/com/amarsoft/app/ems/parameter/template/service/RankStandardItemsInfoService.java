package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemsinfo.RankStandardItemsInfoSaveReq;

/**
 * 技能详情Service接口
 * @author ylgao
 */
public interface RankStandardItemsInfoService {
    /**
     * 技能详情查询
     * @param request
     * @return
     */
    public RankStandardItemsInfoQueryRsp rankStandardItemsInfoQuery(@Valid RankStandardItemsInfoQueryReq rankStandardItemsInfoQueryReq);

    /**
     * 技能详情保存
     * @param request
     * @return
     */
    public void rankStandardItemsInfoSave(@Valid RankStandardItemsInfoSaveReq rankStandardItemsInfoSaveReq);
}
