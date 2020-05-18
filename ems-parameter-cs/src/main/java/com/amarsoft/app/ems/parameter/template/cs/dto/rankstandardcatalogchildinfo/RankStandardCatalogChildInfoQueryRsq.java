/*
 * 文件名：RankStandardCatalogSonInfoQueryRsq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogchildinfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈子职级info界面查询响应〉
 * @author xphe
 * @version 2020年5月9日
 * @see RankStandardCatalogChildInfoQueryRsq
 * @since
 */
@Getter
@Setter
@ToString
public class RankStandardCatalogChildInfoQueryRsq  implements Serializable {
    private static final long serialVersionUID = 1L;
    private RankStandardCatalogChildInfo info;
    private List<Map<String,String>> list;
}
