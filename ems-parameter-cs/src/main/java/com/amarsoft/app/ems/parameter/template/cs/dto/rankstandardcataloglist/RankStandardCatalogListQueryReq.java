/*
 * 文件名：RankStandardCatalogListQueryReq.java
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

import com.amarsoft.aecd.parameter.constant.RankStandard;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Range;
import com.amarsoft.amps.acsc.query.annotation.QueryBegin;
import com.amarsoft.amps.acsc.query.annotation.QueryOrderBy;
import com.amarsoft.amps.acsc.query.annotation.QueryPageSize;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.avta.annotation.TemplateBody;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈职级标准列表查询请求实体类〉
 * 
 * @author xphe
 * @version 2020年5月8日
 * @see RankStandardCatalogChildQueryReq
 * @since
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {}, orderBy = {"serialNo", })
public class RankStandardCatalogListQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("所属团队")
    @ActualColumn("RSC.belongTeam")
    private String belongTeam;
    
    @Description("职级类型")
    @ActualColumn("RSC.rankType")
    private String rankType;

    @Enum(RankStandard.class)
    @Description("职等")
    @Length(max=40)
    @ActualColumn("RSC.rankStandard")
    @TemplateBody(sortNo = 1, suffix = "", alignType = com.amarsoft.aecd.common.constant.TemplateAlignType.Left, editType = com.amarsoft.aecd.common.constant.TemplateEditType.Select, htmlStyle = "", isVisible = true, isReadOnly = false,groupIndex = -1)
    private String rankStandard;
    
    @Description("起始条数")
    @NotEmpty
    @QueryBegin
    private Integer begin;

    @Description("查询笔数")
    @Range(min=1,max=10)
    @NotEmpty
    @QueryPageSize
    private Integer pageSize;

    @Description("排序数组")
    @QueryOrderBy
    private String[] orderBy;
}
