/*
 * 文件名：LabelCatalogInfoQueryReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：标签目录查询的请求体
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.ActualColumn;

/**
 * 标签目录详情查询请求实体类
 * @author yrong
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class LabelCatalogInfoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("标签编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("LC.serialNo")
    private String serialNo;
}
