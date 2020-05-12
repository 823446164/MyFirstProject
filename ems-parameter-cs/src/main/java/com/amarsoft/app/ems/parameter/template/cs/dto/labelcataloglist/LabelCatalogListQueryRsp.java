/*
 * 文件名：LabelCatalogListQueryRsp.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：标签目录列表查询的响应体
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloglist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 标签目录树图查询响应实体类
 * @author yrong
 */
@Getter
@Setter
@ToString
public class LabelCatalogListQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("标签目录树图")
    private List<LabelCatalogList> labelCatalogLists;
}
