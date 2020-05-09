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
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoQueryRsp;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoSaveReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloginfo.RankStandardCatalogInfoSaveRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogsoninfo.RankStandardCatalogSonInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogsoninfo.RankStandardCatalogSonInfoQueryRsq;
import com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogsoninfo.RankStandardCatalogSonInfoSaveReq;

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
     * Description: 职级标准详情查询
     *
     * @param rankStandardCatalogInfoQueryReq
     */
    public RankStandardCatalogInfoQueryRsp rankStandardCatalogInfoQuery(@Valid RankStandardCatalogInfoQueryReq rankStandardCatalogInfoQueryReq);


    /**
     * 
     * Description: 职级标准详情保存
     *
     * @param rankStandardCatalogInfoSaveReq
     */
    public RankStandardCatalogInfoSaveRsq rankStandardCatalogInfoSave(@Valid RankStandardCatalogInfoSaveReq rankStandardCatalogInfoSaveReq);

    /**
     * 
     * Description: 职级标准详情删除
     *
     * @param rankStandardCatalogInfoSaveReq
     */
    public void rankStandardCatalogInfoDelete(@Valid RankStandardCatalogInfoQueryReq rankStandardCatalogInfoQueryReq);
    
    /**
     * 
     * Description: 子职级标准详情删除
     *
     * @param rankStandardCatalogInfoSaveReq
     */
    public void rankStandardCatalogSonInfoDelete(@Valid RankStandardCatalogInfoQueryReq rankStandardCatalogInfoQueryReq);    
    
    /**
     * 
     * Description: 子职级标准详情保存
     *
     * @param rankStandardCatalogInfoSaveReq
     */
    public  Map<String,String> rankStandardCatalogSonInfoSave(@Valid RankStandardCatalogSonInfoSaveReq rankStandardCatalogSonInfoSaveReq);
    
    /**
     * 
     * Description: 子职级标准详情查询
     *
     * @param rankStandardCatalogInfoSaveReq
     */
    public RankStandardCatalogSonInfoQueryRsq rankStandardCatalogSonInfoQuery(@Valid RankStandardCatalogSonInfoQueryReq rankStandardCatalogSonInfoQueryReq);
}
