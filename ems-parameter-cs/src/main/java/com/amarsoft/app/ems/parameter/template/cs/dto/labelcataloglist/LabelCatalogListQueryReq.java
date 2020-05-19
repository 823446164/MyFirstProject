/*
 * 文件名：LabelCatalogListQueryReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：标签目录列表查询的请求体
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.query.annotation.QueryOrderBy;

/**
 * 标签目录树图查询请求实体类
 * @author yrong
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {}, orderBy = {"serialNo", })
public class LabelCatalogListQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("serialNo集合")
    @QueryOrderBy
    private List<String> serialNoList;
}
