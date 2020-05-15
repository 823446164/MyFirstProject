/*
 * 文件名：selectLabelByLabelCatalogReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：yrong
 * 修改时间：2020年5月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist;

import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.query.annotation.QueryBegin;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author amarsoft
 * @version 2020年5月12日
 * @see SelectLabelByLabelCatalogReq
 * @since
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {}, orderBy = {"serialNo", })
public class SelectLabelByLabelCatalogReq {
    private static final long serialVersionUID = 1L;
    @Description("标签目录编号")
    @NotEmpty
    @QueryBegin
    private String serialNo;
}
