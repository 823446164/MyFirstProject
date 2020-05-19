/*
 * 文件名：LabelByLabelCatalogQueryReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：根据目录查找标签的请求体
 * 修改人：yrong
 * 修改时间：2020年5月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */

package com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo;

import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

import java.util.List;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
/**
 * 标签目录详情查询请求实体类
 * @author yrong
 * @version 2020年5月11日
 * @see LabelByLabelCatalogQueryReq
 */
@Getter
@Setter
@ToString
public class LabelByLabelCatalogQueryReq {
    private static final long serialVersionUID = 1L;
    @Description("标签编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("LC.serialNo")
    private String serialNo;
    
    @Description("标签名称")
    @Length(max=80)
    @ActualColumn("LC.labelName")
    private String labelName;
    
    @Description("标签类型")
    @Length(max=80)
    @ActualColumn("LC.labelType")
    private String labelType;
}
