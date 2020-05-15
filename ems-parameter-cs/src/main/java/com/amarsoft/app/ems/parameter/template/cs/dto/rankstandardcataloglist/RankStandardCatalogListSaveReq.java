/*
 * 文件名：RankStandardCatalogListSaveReq.java
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
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 〈职级标准列表保存请求实体类〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see 
 * @since
 */
@Getter
@Setter
@ToString
public class RankStandardCatalogListSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("职级标准列表")
    @NotEmpty
    private List<RankStandardCatalogList> rankStandardCatalogLists;

    @Description("总笔数")
    private Integer totalCount = 0;
}
