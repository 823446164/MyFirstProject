/*
 * 文件名：LabelByLabelCatalogQueryRsp.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：根据目录查找标签的响应体
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 标签目录详情查询响应实体类
 * @author yrong
 */
@Getter
@Setter
@ToString
public class LabelByLabelCatalogQueryRsp extends LabelCatalogInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("根据目录查询到的标签")
    private List<LabelCatalogInfo> LableCatalogInfos;
}
