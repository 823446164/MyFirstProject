/*
 * 文件名：TreeLabelQueryRsp.java
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
import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈标签列表查询响应实体类〉
 * @author xphe
 * @version 2020年5月13日
 * @see TreeLabelQueryRsp
 * @since
 */
@Getter
@Setter
@ToString
public class TreeLabelQueryRsp implements Serializable{
    @Description("总笔数")
    private Integer totalCount = 0;
    
    @Description("标签目录List")
    private List<TreeLabel> TreeLabel;
}
