package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoSaveReq;

/**
 * 职级标准详情Service接口
 * @author ylgao
 */
public interface RankStandardCatalogInfoService {
    /**
     * 职级标准详情查询
     * @param request
     * @return
     */
    public RankStandardCatalogInfoQueryRsp rankStandardCatalogInfoQuery(@Valid RankStandardCatalogInfoQueryReq rankStandardCatalogInfoQueryReq);

    /**
     * 职级标准详情保存
     * @param request
     * @return
     */
    public void rankStandardCatalogInfoSave(@Valid RankStandardCatalogInfoSaveReq rankStandardCatalogInfoSaveReq);
}
