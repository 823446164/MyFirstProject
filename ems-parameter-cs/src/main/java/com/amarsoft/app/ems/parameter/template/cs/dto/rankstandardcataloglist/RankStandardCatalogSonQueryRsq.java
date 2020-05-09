/*
 * 文件名：RankStandardCatalogSonQueryRsq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcataloglist;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈子职级列表查询响应实体〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see RankStandardCatalogSonQueryRsq
 * @since
 */
@Getter
@Setter
@ToString
public class RankStandardCatalogSonQueryRsq implements Serializable{
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("职级标准列表")
    private List<RankStandardCatalogList> rankStandardCatalogLists;
}
