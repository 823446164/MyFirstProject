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
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfo;

import java.util.List;

/**
 * 标签目录树图查询响应实体类
 * @author yrong
 */
@Getter
@Setter
@ToString
public class LabelCatalogListQueryRsp extends LabelCatalogInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("查询到的LabelCatalogInfo")
    private List<LabelCatalogInfo> LableCatalogInfos;
}
