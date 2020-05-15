/*
 * 文件名：RankStandardCatalogListService.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.service;

import javax.validation.Valid;

import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogChildQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogChildQueryRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist.RankStandardCatalogListQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryReq;
import com.amarsoft.app.ems.system.cs.dto.teamquery.TeamQueryRsp;

/**
 * 〈职级标准列表Service接口〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
public interface RankStandardCatalogListService {
    /**
     * 
     * Description: 职级标准查询
                     * 1、…开发/管理职级标准查询
     *
     * @param rankStandardCatalogListQueryReq
     * @return
     * @see
     */
    public RankStandardCatalogListQueryRsp rankStandardCatalogListQuery(@Valid RankStandardCatalogListQueryReq rankStandardCatalogListQueryReq);



    /**
     * 
     * Description: 子职级标准查询
     *
     * @param rankStandardCatalogSonQueryReq
     * @return
     * @see
     */
    public RankStandardCatalogChildQueryRsq rankStandardCatalogChildQuery(@Valid RankStandardCatalogChildQueryReq rankStandardCatalogSonQueryReq);
   

    /**
     * 
     * Description: 展示团队列表
     *
     * @param rankStandardCatalogListQueryReq
     * @return
     * @see
     */ 
    public TeamQueryRsp rankStandardCatalogTeamQuery(@Valid TeamQueryReq teamQueryRep);
}
