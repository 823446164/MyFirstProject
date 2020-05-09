/*
 * 文件名：RankStandardCatalogSonInfoQueryReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandardcatalogsoninfo;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈子职级info界面查询请求实体〉
 * @author xphe
 * @version 2020年5月9日
 * @see RankStandardCatalogSonInfoQueryReq
 * @since
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {}, orderBy = {"serialNo", })
public class RankStandardCatalogSonInfoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("职级编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("RSC.serialNo")
    private String serialNo;
}
