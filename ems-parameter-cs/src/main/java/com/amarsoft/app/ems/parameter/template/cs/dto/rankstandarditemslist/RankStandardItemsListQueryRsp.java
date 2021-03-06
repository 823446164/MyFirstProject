/*
 * 文件名：RankStandardItemsListQueryReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈技能指标查询响应实体〉
 * @author xphe
 * @version 2020年5月13日
 * @see RankStandardItemsListQueryRsp
 * @since
 */
@Getter
@Setter
@ToString
public class RankStandardItemsListQueryRsp   implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("职级指标列表")
    private List<RankStandardItemsList> rankStandardItemsList;
    
    //子职级下的处理审批中或者要审批的当月流程个数
    private int flowCount;
}
