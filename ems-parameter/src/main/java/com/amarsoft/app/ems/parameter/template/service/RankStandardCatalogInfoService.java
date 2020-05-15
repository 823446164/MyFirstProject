/*
 * 文件名：RankStandardCatalogInfoService.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.service;

import java.util.Map;

import javax.validation.Valid;

import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoQueryRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo.RankStandardCatalogChildInfoSaveReq;

/**
 * 〈职级标准详情Service接口〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
public interface RankStandardCatalogInfoService {

    /**
     * 
     * Description: 职级标准详情删除
     *
     * @param rankStandardCatalogChildInfoQueryReq
     */
    public void rankStandardCatalogInfoDelete(@Valid RankStandardCatalogChildInfoQueryReq rankStandardCatalogChildInfoQueryReq);
    
    /**
     * 
     * Description: 子职级标准详情保存
     *
     * @param rankStandardCatalogChildInfoSaveReq
     */
    public  Map<String,String> rankStandardCatalogChildInfoSave(@Valid RankStandardCatalogChildInfoSaveReq rankStandardCatalogChildInfoSaveReq);
    
    /**
     * 
     * Description: 子职级标准详情查询
     *
     * @param rankStandardCatalogChildInfoQueryReq
     */
    public RankStandardCatalogChildInfoQueryRsq rankStandardCatalogChildInfoQuery(@Valid RankStandardCatalogChildInfoQueryReq rankStandardCatalogChildInfoQueryReq);
}
