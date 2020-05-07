package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListDeleteReq;

/**
 * 职级标准列表Service接口
 * @author ylgao
 */
public interface RankStandardCatalogListService {
    /**
     * 职级标准列表查询
     * @param request
     * @return
     */
    public RankStandardCatalogListQueryRsp rankStandardCatalogListQuery(@Valid RankStandardCatalogListQueryReq rankStandardCatalogListQueryReq);

    /**
     * 职级标准列表保存
     * @param request
     * @return
     */
    public void rankStandardCatalogListSave(@Valid RankStandardCatalogListSaveReq rankStandardCatalogListSaveReq);

    /**
     * 职级标准列表删除
     * @param request
     * @return
     */
    public void rankStandardCatalogListDelete(@Valid RankStandardCatalogListDeleteReq rankStandardCatalogListDeleteReq);
}
