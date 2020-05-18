/*
 * 文件名：RankStandardItemsListQueryReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：职级指标list模板查询请求实体类
 * 修改人：xphe
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.rankstandarditemslist;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈技能指标查询请求实体〉
 * @author xphe
 * @version 2020年5月13日
 * @see RankStandardItemsListQueryReq
 * @since
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class RankStandardItemsListQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("指标编号")
    @Length(max=40)
    @ActualColumn("RSI.serialNo")
    private String serialNo;

    @Description("标签编号")
    @Length(max=40)
    @ActualColumn("RSI.labelNo")
    private String labelNo;
    
    @Description("职级编号")
    @Length(max=40)
    @ActualColumn("RSI.rankNo")
    private String rankNo;
    
}
