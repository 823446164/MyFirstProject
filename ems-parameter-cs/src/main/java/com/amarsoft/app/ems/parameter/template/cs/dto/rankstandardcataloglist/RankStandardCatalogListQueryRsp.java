/*
 * 文件名：RankStandardCatalogListQueryRsp.java
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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;
import java.util.Map;

/**
 * 〈职级标准列表查询响应实体类〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
@Getter
@Setter
@ToString
public class RankStandardCatalogListQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("职级标准列表")
    private List<RankStandardCatalogList> rankStandardCatalogLists;
    
    @Description("开发职等列表")
    private List<Map<String,String>> rankStandardList;
    
    @Description("开发职级列表")
    private List<Map<String,String>> rankNameList;
    
    @Description("管理职等列表")
    private List<Map<String,String>> manaRankStandardList;
    
    @Description("管理职级列表")
    private List<Map<String,String>> manaRankNameList;
}
