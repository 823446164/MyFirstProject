/*
 * 文件名：TreeLabelQueryReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.ranklabel;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈标签列表查询请求实体类〉
 * @author xphe
 * @version 2020年5月13日
 * @see TreeLabelQueryReq
 * @since
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {}, orderBy = {"serialNo", })
public class TreeLabelQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;
    @Description("标签编号")
    @Length(max=40)
    @ActualColumn("LC.serialNo")
    private String serialNo;
}
